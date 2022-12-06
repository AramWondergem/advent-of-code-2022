package nl.WonderGem.aoc.days;

import nl.WonderGem.aoc.common.Day;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;

public class Day06 implements Day<Integer> {
    @Override
    public Integer part1(List<String> input) {

        String inputstring = input.get(0);

        int result = 0;

        for(int i = 4; i < inputstring.length(); i++) {
            String partIndex = inputstring.substring((i-4),i);
            System.out.println(partIndex);
            boolean firstCompare = partIndex.charAt(0) != partIndex.charAt(1) && partIndex.charAt(0) != partIndex.charAt(2) && partIndex.charAt(0) != partIndex.charAt(3);
            boolean secondCompare = partIndex.charAt(1) != partIndex.charAt(2) && partIndex.charAt(1) != partIndex.charAt(3);
            boolean thirdCompare = partIndex.charAt(2) != partIndex.charAt(3);

            if (firstCompare&&secondCompare&&thirdCompare) {

                result = i;
                break;

            }
        }
        // take the part from the first until the fourth --> substring
        // compare the first with the second, third and fourth
        // compare the second with the third and fourth
        //compare the third with the fourth
        // go on with the plus one untill all different
        return result;
    }

    @Override
    public Integer part2(List<String> input) {

        boolean firstloopPassed = true;

        String inputstring = input.get(0);

        int result = 0;

        for(int i = 14; i < inputstring.length(); i++) {
            String partIndex = inputstring.substring((i - 14), i);

            firstloopPassed = true;

            for(int j = 0; j < partIndex.length(); j++) {

                String comparePart = partIndex.substring(j+1,partIndex.length());
//                System.out.println(comparePart);

                if (comparePart.contains(String.valueOf(partIndex.charAt(j)))) {
                    firstloopPassed = false;
//                    System.out.println("false: j loop");
                    break;
                }

            }

            if(firstloopPassed == true) {
                result = i;
                System.out.println("True: if statement");
                break;
            }
        }
        return result;
    }
}
