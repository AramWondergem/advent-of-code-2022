package nl.WonderGem.aoc.days;


import nl.WonderGem.aoc.common.Day;
import nl.WonderGem.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day06Test  {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(06);

    @Test
    public void part1() {
        Day<Integer> day = new Day06();
        assertEquals(Integer.valueOf(7), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer> day = new Day06();
        assertEquals(Integer.valueOf(19), day.part2(input.getLines()));
    }


}