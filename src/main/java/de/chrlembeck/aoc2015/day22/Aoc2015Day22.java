package de.chrlembeck.aoc2015.day22;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.util.algorithm.Algorithms;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Aoc2015Day22 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2015Day22().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        return calc(input, false);
    }

    @Override
    public Integer part2(final Scanner input) {
        return calc(input, true);
    }

    private Integer calc(final Scanner input, boolean hardMode) {
        int hitPoints = Integer.parseInt(matchRegex("Hit Points: (\\d+)", input.nextLine()).group(1));
        int damage = Integer.parseInt(matchRegex("Damage: (\\d+)", input.nextLine()).group(1));
        Boss boss = new Boss(hitPoints, damage);
        Player player = new Player(50, 500, 0);
        AtomicInteger best = new AtomicInteger(Integer.MAX_VALUE);
        State state = new State(player, boss, new int[Spell.values().length], hardMode, best);

        Algorithms.breadthFirstSearch(state, this::producer, (x -> {
            if (x.getState().player.hitPoints >= 0 && x.getState().boss.hitPoints <= 0) {
                best.getAndAccumulate(x.getState().player.manaSpent, Math::min);
            }
        }), s -> false);

        return best.get();
    }

    public Iterable<State> producer(Algorithms.StateWrapper<State> wrappedState) {
        State state = wrappedState.getState();
        if (state.player.hitPoints <= 0 || state.boss.hitPoints <= 0) {
            return Collections.emptyList();
        }
        Spell[] spells = Spell.getPossibleSpells(state.player.mana, state.spellDuration);
        List<State> result = new ArrayList<>();
        for (Spell spell : spells) {
            State test = state.apply(spell);
            if (test.player.manaSpent < test.best.get()) {
                result.add(test);
            }
        }
        if (result.stream().anyMatch(s -> s.player.hitPoints > 0 & s.boss.hitPoints <= 0)) {
            result.removeIf(s -> s.player.hitPoints <= 0 || s.boss.hitPoints > 0);
        }
        return result;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2015/aoc2015day22.txt";
    }


    record Player(int hitPoints, int mana, int manaSpent) {
    }

    record Boss(int hitPoints, int damage) {
    }

    static class State {

        final boolean hardMode;

        final Player player;

        final Boss boss;

        final AtomicInteger best;

        private final int[] spellDuration;

        public State(Player player, Boss boss, int[] spellDuration, boolean hardMode, AtomicInteger best) {
            this.player = player;
            this.boss = boss;
            this.spellDuration = spellDuration;
            this.hardMode = hardMode;
            this.best = best;
        }

        public State apply(final Spell spell) {
            int p_hitPoints = player.hitPoints;
            int p_mana = player.mana - spell.costs;
            int b_hitPoints = boss.hitPoints;
            int b_damage = boss.damage;

            if (hardMode) {
                p_hitPoints--;
                if (p_hitPoints <= 0) {
                    return new State(new Player(p_hitPoints, p_mana, player.manaSpent + spell.costs), new Boss(b_hitPoints, b_damage), spellDuration, hardMode, best);
                }
            }

            final int[] newSpellDuration = Arrays.copyOf(spellDuration, spellDuration.length);

            // players turn
            if (newSpellDuration[Spell.POISON.ordinal()] > 0) {
                b_hitPoints -= 3;
            }
            if (newSpellDuration[Spell.RECHARGE.ordinal()] > 0) {
                p_mana += 101;
            }
            for (int i = 0; i < newSpellDuration.length; i++) {
                newSpellDuration[i] = Math.max(0, newSpellDuration[i] - 1);
            }
            newSpellDuration[spell.ordinal()] = spell.duration();

            if (spell == Spell.MAGIC_MISSILE) {
                b_hitPoints -= 4;
            }
            if (spell == Spell.DRAIN) {
                b_hitPoints -= 2;
                p_hitPoints += 2;
            }

            if (b_hitPoints <= 0) { // win
                return new State(new Player(p_hitPoints, p_mana, player.manaSpent + spell.costs), new Boss(b_hitPoints, b_damage), newSpellDuration, hardMode, best);
            }

            // boss turn
            if (newSpellDuration[Spell.POISON.ordinal()] > 0) {
                b_hitPoints -= 3;
            }
            if (newSpellDuration[Spell.RECHARGE.ordinal()] > 0) {
                p_mana += 101;
            }
            for (int i = 0; i < newSpellDuration.length; i++) {
                newSpellDuration[i] = Math.max(0, newSpellDuration[i] - 1);
            }
            if (b_hitPoints <= 0) { // win
                return new State(new Player(p_hitPoints, p_mana, player.manaSpent + spell.costs), new Boss(b_hitPoints, b_damage), newSpellDuration, hardMode, best);
            }

            p_hitPoints -= Math.max(1, b_damage - (newSpellDuration[Spell.SHIELD.ordinal()] > 0 ? 7 : 0));

            return new State(new Player(p_hitPoints, p_mana, player.manaSpent + spell.costs), new Boss(b_hitPoints, b_damage), newSpellDuration, hardMode, best);
        }
    }

    enum Spell {
        MAGIC_MISSILE(53), DRAIN(73), SHIELD(113), POISON(173), RECHARGE(229);

        private final int costs;

        Spell(int costs) {
            this.costs = costs;
        }

        static Spell[] getPossibleSpells(int budget, int[] durations) {
            return Arrays.stream(values()).filter(sp -> sp.costs <= budget && durations[sp.ordinal()] <= 1).toArray(Spell[]::new);
        }

        public int duration() {
            return switch (this) {
                case MAGIC_MISSILE, DRAIN -> 0;
                case SHIELD, POISON -> 6;
                case RECHARGE -> 5;
            };
        }
    }
}