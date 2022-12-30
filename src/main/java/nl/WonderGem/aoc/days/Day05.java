package nl.WonderGem.aoc.days;

import nl.WonderGem.aoc.common.Day;

import javax.xml.stream.events.Characters;
import java.util.*;
import java.util.stream.Collectors;

public class Day05 {


    // function to fill the crates
    public List<Deque<Character>> stackCratesInStartPosition(List<String> input) {
        List<Deque<Character>> listOfStacks = new ArrayList<>();

        List<String> cratesList = input.stream().filter(string -> string.contains("[")).toList();

        String containingAllCratesStack = cratesList.stream().max(Comparator.comparing(String::length)).get().toString();

        int numberOfCrateStacks = containingAllCratesStack.length() / 4 + 1;

        for (int i = 0; i < numberOfCrateStacks; i++) {
            Deque<Character> crateStack = new LinkedList<>();
            listOfStacks.add(crateStack);
        }

        for (String s :
                cratesList) {

            for (int i = 1; i < s.length(); i = i + 4) {

                if (s.charAt(i) != ' ') {
                    listOfStacks.get(i / 4).addLast(s.charAt(i));
                }

            }

        }

        return listOfStacks;
    }


    // function to get the instructions

    public List<String> getInstructions(List<String> input) {

        return input.stream().filter(string -> string.contains("move")).collect(Collectors.toList());

    }


    // function to read the instruction
    // function to excecute the instruction

    public List<Deque<Character>> moveCrates(List<Deque<Character>> listOfStacks, List<String> instructions) {

        for (String s :
                instructions) {
            String numbers = s.replaceAll("move ", "").replaceAll("from ", "").replaceAll("to ", "");
            String[] instructionNumbers = numbers.split(" ");

            for (int i = 0; i < Integer.parseInt(instructionNumbers[0]); i++) {

                Character crate = listOfStacks.get(Integer.parseInt(instructionNumbers[1]) - 1).removeFirst();
                listOfStacks.get(Integer.parseInt(instructionNumbers[2]) - 1).addFirst(crate);

            }
        }

        return listOfStacks;
    }

    public List<Deque<Character>> moveCratesVersion2(List<Deque<Character>> listOfStacks, List<String> instructions) {

        for (String s :
                instructions) {
            String numbers = s.replaceAll("move ", "").replaceAll("from ", "").replaceAll("to ", "");
            String[] instructionNumbers = numbers.split(" ");

            Deque<Character> movecrates = new LinkedList<>();

            for (int i = 0; i < Integer.parseInt(instructionNumbers[0]); i++) {

                Character crate = listOfStacks.get(Integer.parseInt(instructionNumbers[1]) - 1).removeFirst();

                movecrates.addLast(crate);
            }

            for (int i = 0; i < Integer.parseInt(instructionNumbers[0]); i++) {

                Character crate = movecrates.removeLast();

                listOfStacks.get(Integer.parseInt(instructionNumbers[2]) - 1).addFirst(crate);

            }


        }

        return listOfStacks;
    }


    // get the first of each staple and that is the answer


    public String part1(List<String> input) {

        List<Deque<Character>> listOfStacks = stackCratesInStartPosition(input);
        List<String> instructions = getInstructions(input);
        List<Deque<Character>> endResult = moveCrates(listOfStacks, instructions);

        StringBuilder builder = new StringBuilder();

        endResult.stream().map(list -> builder.append(list.removeFirst())).collect(Collectors.toList());

        String answer = builder.toString();
        return answer;
    }


    public String part2(List<String> input) {
        List<Deque<Character>> listOfStacks = stackCratesInStartPosition(input);
        List<String> instructions = getInstructions(input);
        List<Deque<Character>> endResult = moveCratesVersion2(listOfStacks, instructions);

        StringBuilder builder = new StringBuilder();

        endResult.stream().map(list -> builder.append(list.removeFirst())).collect(Collectors.toList());

        String answer = builder.toString();
        return answer;
    }

}
