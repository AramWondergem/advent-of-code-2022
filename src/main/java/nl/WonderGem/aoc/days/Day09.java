package nl.WonderGem.aoc.days;

import nl.WonderGem.aoc.common.Day;

import java.util.*;


public class Day09 implements Day<Integer> {

    public class Knot {
        private int x;
        private int y;
        private int rowNumber;
        private Set<String> touchedFields;

        public Knot(int rowNumber) {
            this.x = 0;
            this.y = 0;
            this.rowNumber = rowNumber;
            this.touchedFields = new HashSet<>();
            addTouchedField();
        }

        public void addTouchedField() {
            String field = "x:" + this.x + " y:" + this.y;
            touchedFields.add(field);
        }

        public void goUp(){
            y++;
        }

        public void goDown(){
            y--;
        }

        public void goLeft(){
            x--;
        }

        public void goRight(){
            x++;
        }

        public void goLeftUp(){
            x--;
            y++;
        }

        public void goRightUp(){
            x++;
            y++;
        }

        public void goRightDown(){
            x++;
            y--;
        }

        public void goLeftDown(){
            x--;
            y--;
        }



    }






    int xhead = 0;
    int xtall = 0;

    int yhead = 0;
    int ytall = 0;


    public List<String> makeAMove2(String move, int amountOfTimes) {

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

    public void makeAMove(String move, int amountOftimes, List<Knot> rope) {

        for (int j = 0; j < amountOftimes; j++) {

            Knot firstKnot = rope.get(0);

            // move the first knot
            switch (move) {
                case "U" -> {
                    firstKnot.goUp();
                }
                case "D" -> {
                    firstKnot.goDown();
                }
                case "R" -> {
                    firstKnot.goRight();
                }
                case "L" -> {
                    firstKnot.goLeft();
                }
            }

            firstKnot.addTouchedField();

            // move the following knots based on the difference in position with the knot before



            for (int k = 1; k < rope.size(); k++) {

                Knot knotBefore = rope.get(k-1);
                Knot knotAfter = rope.get(k);



                int differenceX = knotBefore.x - knotAfter.x;
                int differenceY = knotBefore.y - knotAfter.y;


                switch (differenceY) {
                    case 2:
                        if(differenceX == -2 || differenceX == -1){
                            knotAfter.goLeftUp();
                        } else if (differenceX == 2 || differenceX == 1) {
                            knotAfter.goRightUp();
                        } else if (differenceX == 0 ) {
                            knotAfter.goUp();
                        }
                        break;
                    case 1:
                        if (differenceX == -2){
                            knotAfter.goLeftUp();
                        } else if (differenceX == 2) {
                            knotAfter.goRightUp();
                        }
                        break;
                    case 0:
                        if (differenceX == -2){
                            knotAfter.goLeft();
                        } else if (differenceX == 2) {
                            knotAfter.goRight();
                        }
                        break;
                    case -1:
                        if (differenceX == -2){
                            knotAfter.goLeftDown();
                        } else if (differenceX == 2) {
                            knotAfter.goRightDown();
                        }
                        break;
                    case -2:
                        if(differenceX == -2 || differenceX == -1){
                            knotAfter.goLeftDown();
                        } else if (differenceX == 2 || differenceX == 1) {
                            knotAfter.goRightDown();
                        } else if (differenceX == 0 ) {
                            knotAfter.goDown();
                        }
                        break;
                }
               knotAfter.addTouchedField();
            }
        }



    }

    @Override
    public Integer part1(List<String> input) {


        //make a rope

        List<Knot> rope = new ArrayList<>();

        for (int i = 0; i < 2; i++) {

            Knot knot = new Knot(i);
            rope.add(knot);

        }

        // make am move based on instructions in the list
        for (String s :
                input) {


            String[] splitString = s.split(" ");

            makeAMove(splitString[0],Integer.parseInt(splitString[1]),rope);

        }


        return rope.get(1).touchedFields.size();
    }

    @Override
    public Integer part2(List<String> input) {

        //make a rope

        List<Knot> rope = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Knot knot = new Knot(i);
            rope.add(knot);

        }

        // make am move based on instructions in the list
        for (String s :
                input) {


            String[] splitString = s.split(" ");

            makeAMove(splitString[0],Integer.parseInt(splitString[1]),rope);

        }




            return rope.get(9).touchedFields.size();
    }
}
