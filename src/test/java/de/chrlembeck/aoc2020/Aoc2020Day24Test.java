package de.chrlembeck.aoc2020;

import de.chrlembeck.aoc2020.day24.Aoc2020Day24;
import org.junit.jupiter.api.Test;

import static de.chrlembeck.aoccommon.TestUtils.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aoc2020Day24Test {

    @Test
    public void test1() {
        final Aoc2020Day24 day = new Aoc2020Day24();
        assertEquals("1", test("esew", day::part1));
        assertEquals("1", test("nwwswee", day::part1));
        assertEquals("10", test("""
                                sesenwnenenewseeswwswswwnenewsewsw
                                neeenesenwnwwswnenewnwwsewnenwseswesw
                                seswneswswsenwwnwse
                                nwnwneseeswswnenewneswwnewseswneseene
                                swweswneswnenwsewnwneneseenw
                                eesenwseswswnenwswnwnwsewwnwsene
                                sewnenenenesenwsewnenwwwse
                                wenwwweseeeweswwwnwwe
                                wsweesenenewnwwnwsenewsenwwsesesenwne
                                neeswseenwwswnwswswnw
                                nenwswwsewswnenenewsenwsenwnesesenew
                                enewnwewneswsewnwswenweswnenwsenwsw
                                sweneswneswneneenwnewenewwneswswnese
                                swwesenesewenwneswnwwneseswwne
                                enesenwswwswneneswsenwnewswseenwsese
                                wnwnesenesenenwwnenwsewesewsesesew
                                nenewswnwewswnenesenwnesewesw
                                eneswnwswnwsenenwnwnwwseeswneewsenese
                                neswnwewnwnwseenwseesewsenwsweewe
                                wseweeenwnesenwwwswnew""", day::part1));
        assertEquals("469", test(day.getInput1(), day::part1));
    }

    @Test
    public void test2() {
        final Aoc2020Day24 day = new Aoc2020Day24();
        assertEquals("2208", test("""
                                  sesenwnenenewseeswwswswwnenewsewsw
                                  neeenesenwnwwswnenewnwwsewnenwseswesw
                                  seswneswswsenwwnwse
                                  nwnwneseeswswnenewneswwnewseswneseene
                                  swweswneswnenwsewnwneneseenw
                                  eesenwseswswnenwswnwnwsewwnwsene
                                  sewnenenenesenwsewnenwwwse
                                  wenwwweseeeweswwwnwwe
                                  wsweesenenewnwwnwsenewsenwwsesesenwne
                                  neeswseenwwswnwswswnw
                                  nenwswwsewswnenenewsenwsenwnesesenew
                                  enewnwewneswsewnwswenweswnenwsenwsw
                                  sweneswneswneneenwnewenewwneswswnese
                                  swwesenesewenwneswnwwneseswwne
                                  enesenwswwswneneswsenwnewswseenwsese
                                  wnwnesenesenenwwnenwsewesewsesesew
                                  nenewswnwewswnenesenwnesewesw
                                  eneswnwswnwsenenwnwnwwseeswneewsenese
                                  neswnwewnwnwseenwseesewsenwsweewe
                                  wseweeenwnesenwwwswnew""", day::part2));
        assertEquals("4353", test(day.getInput1(), day::part2));
    }
}