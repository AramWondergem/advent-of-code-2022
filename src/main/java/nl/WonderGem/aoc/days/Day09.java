package nl.WonderGem.aoc.days;

import nl.WonderGem.aoc.common.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Day09 implements Day<Integer> {

    int xhead = 0;
    int xtall = 0;

    int yhead = 0;
    int ytall = 0;

    public List<String> makeAMove(String move, int amountOfTimes) {

        List<String> placeholderAnswer = new ArrayList<>();



        for (int i = 0; i < amountOfTimes; i++) {

            int differenceX = xhead - xtall;
            int differenctY = yhead - ytall;




            switch (move) {

                case "U":
                    if(differenceX == 1 && differenctY == 1){
                        xtall++;
                        ytall++;
                    } else if (differenceX == 0 && differenctY == 1) {
                        ytall++;
                    } else if (differenceX == -1 && differenctY == 1) {
                        xtall--;
                        ytall++;
                    }
                    yhead++;
                    break;
                case "D":
                    if(differenceX == 1 && differenctY == -1){
                        xtall++;
                        ytall--;
                    } else if (differenceX == 0 && differenctY == -1) {
                        ytall--;
                    } else if (differenceX == -1 && differenctY == -1) {
                        xtall--;
                        ytall--;
                    }
                    yhead--;
                    break;
                case "R":
                    if(differenceX == 1 && differenctY == -1){
                        xtall++;
                        ytall--;
                    } else if (differenceX == 1 && differenctY == 0) {
                        xtall++;
                    } else if (differenceX == 1 && differenctY == 1) {
                        xtall++;
                        ytall++;
                    }
                    xhead++;
                    break;
                case "L":
                    if(differenceX == -1 && differenctY == -1){
                        xtall--;
                        ytall--;
                    } else if (differenceX == -1 && differenctY == 0) {
                        xtall--;
                    } else if (differenceX == -1 && differenctY == 1) {
                        xtall--;
                        ytall++;
                    }
                    xhead--;
                    break;



            }

            placeholderAnswer.add("x:" + Integer.toString(xtall) + " y:" + Integer.toString(ytall));
        }

        return placeholderAnswer;

    }


    @Override
    public Integer part1(List<String> input) {

        HashSet<String> locationsOfTall = new HashSet<>();



        for (String s :
                input) {

            s.trim();

           String[] splitString =  s.split(" ");



            locationsOfTall.addAll(makeAMove(splitString[0],Integer.parseInt(splitString[1])));
//            System.out.println(splitString[1]);

        }

        System.out.println(locationsOfTall);

        return locationsOfTall.size();
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
