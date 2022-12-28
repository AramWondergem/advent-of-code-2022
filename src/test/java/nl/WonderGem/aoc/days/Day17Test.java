package nl.WonderGem.aoc.days;

import nl.WonderGem.aoc.common.Day;
import nl.WonderGem.aoc.common.DayInputExternalResource;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class Day17Test {
    @Rule
    public DayInputExternalResource input = new DayInputExternalResource(17);

    @Test
    public void part1() {
        Day<Integer> day = new Day17();
        assertEquals(Integer.valueOf(3068), day.part1(input.getLines()));
    }

    @Test
    public void part2() {
        Day<Integer> day = new Day17();
        assertEquals(BigInteger.valueOf(1514285714288L), day.part2(input.getLines()));
    }

    @Test
    public void part3() {
        Day17 day = new Day17();
        assertEquals(BigInteger.valueOf(1514285714288L), day.part3(input.getLines()));
    }

    ;

}
