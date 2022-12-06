package nl.WonderGem.aoc.days;


import static org.junit.Assert.assertEquals;

import nl.WonderGem.aoc.common.Day;
import org.junit.Rule;
import org.junit.Test;

import nl.WonderGem.aoc.common.DayInputExternalResource;


public class Day03Test {

    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(03);

    @Test
    public void part1() {
        Day<Integer> day = new Day03();
        assertEquals(Integer.valueOf(157), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer> day = new Day03();
        assertEquals(Integer.valueOf(70), day.part2(input.getLines()));
    }
}
