package nl.WonderGem.aoc.days;

import nl.WonderGem.aoc.common.Day;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;

public class Day03 implements Day<Integer> {

    @Override
    public Integer part1(List<String> input) {

        int sumOfImportants = 0;

        for (String s :
                input) {
            int sizeString = s.length();
            String firstPart = s.substring(0,(sizeString/2));
            String secondPart = s.substring((sizeString/2));
//            System.out.println(firstPart);
//            System.out.println(secondPart);
//            System.out.println();

            CharacterIterator itr = new StringCharacterIterator(firstPart);
            char itemInBothPackages = 'a';
//            System.out.println("voor");
            while (itr.current() != CharacterIterator.DONE) {
//                System.out.println("buiten");
                if (secondPart.contains(String.valueOf(itr.current()))) {
//                    System.out.println("binnen");
                    itemInBothPackages = itr.current();
                    break;

                }
                itr.next();
            }

            if ((int)itemInBothPackages>96) {

                sumOfImportants += ((int)itemInBothPackages - 96);

            } else {
                sumOfImportants += ((int)itemInBothPackages - 38);
            }


        }

        // split strings in half
        // loop on of the two string and compare it with the other
        // get char value of one char that is in de other line
        //return sum of char value
        return sumOfImportants;
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
