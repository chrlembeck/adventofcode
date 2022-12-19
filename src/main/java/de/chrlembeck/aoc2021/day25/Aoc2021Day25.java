package de.chrlembeck.aoc2021.day25;

import de.chrlembeck.aoccommon.AbstractAocBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aoc2021Day25 extends AbstractAocBase {

    public static void main(final String[] args) {
        new Aoc2021Day25().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        List<Cucumber> eastCucumbers = new ArrayList<>();
        List<Cucumber> southCucumbers = new ArrayList<>();
        List<boolean[]> rows = new ArrayList<>();
        int y = 0;
        while (input.hasNext()) {
            final String line = input.nextLine();
            boolean[] row = new boolean[line.length()];
            for (int i = 0; i < line.length(); i++) {
                row[i] = line.charAt(i) != '.';
                if (line.charAt(i) == 'v') {
                    southCucumbers.add(new Cucumber(i, y, false));
                }
                if (line.charAt(i) == '>') {
                    eastCucumbers.add(new Cucumber(i, y, true));
                }
            }
            rows.add(row);
            y++;
        }
        boolean[][] area = rows.toArray(boolean[][]::new);

        int step = 0;
        boolean moved;
        do {
            step++;
            Cucumber[] eastMovingCucumbers = eastCucumbers.stream().filter(c -> c.canMove(area)).toArray(Cucumber[]::new);
            for (Cucumber cucumber : eastMovingCucumbers) {
                cucumber.move(area);
            }
            Cucumber[] southMovingCucumbers = southCucumbers.stream().filter(c -> c.canMove(area)).toArray(Cucumber[]::new);
            for (Cucumber cucumber : southMovingCucumbers) {
                cucumber.move(area);
            }
            moved = eastMovingCucumbers.length > 0 || southMovingCucumbers.length > 0;
        } while (moved);
        return step;
    }

    @Override
    public String part2(final Scanner input) {
        return "";
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day25.txt";
    }

    static class Cucumber {

        private int x;

        private int y;

        private final boolean movesEast;

        public Cucumber(final int x, final int y, final boolean movesEast) {
            this.x = x;
            this.y = y;
            this.movesEast = movesEast;
        }

        public boolean canMove(final boolean[][] area) {
            return movesEast ? !area[y][(x + 1) % area[y].length] : !area[(y + 1) % area.length][x];
        }

        public void move(final boolean[][] area) {
            area[y][x] = false;
            area[y = (y + (movesEast ? 0 : 1)) % area.length][x = (x + (movesEast ? 1 : 0)) % area[y].length] = true;
        }
    }
}