package nl.WonderGem.aoc.days;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nl.WonderGem.aoc.common.Day;

import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.*;


public class Day17 implements Day<Integer> {

    public class Row {
        @Getter
        @Setter
        private int y;

        @Getter
        @Setter
        List<Character> fieldRow;

        public Row(int y) {
            this.y = y;
            this.fieldRow = new ArrayList<>();
            fieldRow.add('X');
            fieldRow.add('.');
            fieldRow.add('.');
            fieldRow.add('.');
            fieldRow.add('.');
            fieldRow.add('.');
            fieldRow.add('.');
            fieldRow.add('.');
            fieldRow.add('X');


        }

        public boolean isRowTheSame(List<Character> fieldRow2) {
            boolean rowsAreTheSame = true;
            for (int i = 0; i < fieldRow.size(); i++) {

                if (fieldRow.get(i) != fieldRow2.get(i)) {
                    rowsAreTheSame = false;
                    break;
                }
            }
            return rowsAreTheSame;
        }

        public void constructStartRow() {
            for (int i = 0; i < fieldRow.size(); i++) {

                if (fieldRow.get(i) != 'X') {
                    fieldRow.set(i, 'X');
                }

            }
        }

        public void setFields(List<StoneCoordinate> coordinates) {
            for (StoneCoordinate cor :
                    coordinates) {
                if (cor.getY() == y) {
                    fieldRow.set(cor.getX(), 'X');
                }

            }
        }

        public boolean checkFieldRight(StoneCoordinate cor) {
            if (fieldRow.get(cor.getX() + 1) != 'X') {
                return true;
            } else
                return false;
        }

        public boolean checkFieldLeft(StoneCoordinate cor) {
            if (fieldRow.get(cor.getX() - 1) != 'X') {
                return true;
            } else {
                return false;
            }
        }

        public boolean checkFieldBelow(StoneCoordinate cor) {
            if (fieldRow.get(cor.getX()) != 'X' && cor.getY() - 1 == y) {
                return true;
            } else if (!(cor.getY() - 1 == y)) {
                System.out.print("the wrong row is checked");
                return false;
            } else {
                return false;
            }
        }

        public void printRow() {
            for (Character x :
                    fieldRow) {
                System.out.print(x);
            }

        }
    }

    public Queue<Character> windDirectionGenerator(String windDirections) {

        Queue<Character> windDirectionList = new LinkedList<>();

        for (int i = 0; i < windDirections.length(); i++) {

            windDirectionList.add(windDirections.charAt(i));
        }

        return windDirectionList;

    }

    public class StoneCoordinate {

        private int x;
        private int y;

        private StoneCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void goRight() {
            x++;
        }

        public void goLeft() {
            x--;
        }

        public void goDown() {
            y--;
        }

        public boolean isTheWallToTheRight() {
            if (x == 7) {
                return true;
            } else if (x >= 0 && x < 7) {
                return false;
            } else {
                System.out.println("Something wrong with check before entering field");
                return false;
            }
        }

        public boolean isTheWallToTheLeft() {
            if (x == 1) {
                return true;
            } else if (x > 1 && x <= 7) {
                return false;
            } else {
                System.out.println("Something wrong with check before entering field");
                return false;
            }
        }
    }


    public class Stone {

        @Getter
        private List<StoneCoordinate> stoneCoordinates = new ArrayList<>();

        public Stone(int stoneNumber, int y) {

            switch (stoneNumber) {
                case 1:
                    stoneCoordinates.add(new StoneCoordinate(3, y));
                    stoneCoordinates.add(new StoneCoordinate(4, y));
                    stoneCoordinates.add(new StoneCoordinate(5, y));
                    stoneCoordinates.add(new StoneCoordinate(6, y));
                    break;
                case 2:
                    stoneCoordinates.add(new StoneCoordinate(4, y));
                    stoneCoordinates.add(new StoneCoordinate(3, y + 1));
                    stoneCoordinates.add(new StoneCoordinate(4, y + 1));
                    stoneCoordinates.add(new StoneCoordinate(5, y + 1));
                    stoneCoordinates.add(new StoneCoordinate(4, y + 2));
                    break;
                case 3:
                    stoneCoordinates.add(new StoneCoordinate(3, y));
                    stoneCoordinates.add(new StoneCoordinate(4, y));
                    stoneCoordinates.add(new StoneCoordinate(5, y));
                    stoneCoordinates.add(new StoneCoordinate(5, y + 1));
                    stoneCoordinates.add(new StoneCoordinate(5, y + 2));
                    break;
                case 4:
                    stoneCoordinates.add(new StoneCoordinate(3, y));
                    stoneCoordinates.add(new StoneCoordinate(3, y + 1));
                    stoneCoordinates.add(new StoneCoordinate(3, y + 2));
                    stoneCoordinates.add(new StoneCoordinate(3, y + 3));
                    break;
                case 5:
                    stoneCoordinates.add(new StoneCoordinate(3, y));
                    stoneCoordinates.add(new StoneCoordinate(4, y));
                    stoneCoordinates.add(new StoneCoordinate(3, y + 1));
                    stoneCoordinates.add(new StoneCoordinate(4, y + 1));
                    break;
            }

        }

        public void moveStoneSidewaysBeforeEnteringField(char windDirection) {
            boolean canMove = true;
            for (StoneCoordinate stoneCoordinate :
                    stoneCoordinates) {


                if (windDirection == '>') {
                    if (stoneCoordinate.isTheWallToTheRight()) {
                        canMove = false;
                        break;
                    }


                } else if (windDirection == '<') {
                    if (stoneCoordinate.isTheWallToTheLeft()) {
                        canMove = false;
                        break;
                    }

                } else {
                    System.out.println("windDirection is failing");
                }
            }

            if (canMove) {

                if (windDirection == '>') {
                    moveRight();


                } else if (windDirection == '<') {
                    moveLeft();
                }

            }
        }


        public void moveRight() {
            for (StoneCoordinate cor :
                    stoneCoordinates) {
                cor.goRight();
            }
        }

        public void moveLeft() {
            for (StoneCoordinate cor :
                    stoneCoordinates) {
                cor.goLeft();
            }
        }

        public void moveDown() {
            for (StoneCoordinate cor :
                    stoneCoordinates) {
                cor.goDown();
            }
        }

        public void printStone() {

            HashMap<Integer, Row> field = new HashMap<>();
            field.put(4, new Row(4));
            field.put(3, new Row(3));
            field.put(2, new Row(2));
            field.put(1, new Row(1));

            for (Map.Entry<Integer, Row> entry : field.entrySet()) {
                entry.getValue().setFields(stoneCoordinates);
                entry.getValue().printRow();

            }


        }


    }


    // layer element of field --> in array-> start with only bottom row
    // element has y number and 9 x values with 0 en 8 and x
    // string reader which reads the string
    // stone element with coordinates of all blocks and function that moves the block to the side, and down. functions that checks if it is possible
    // checks the position after 3 steps -->
    // checks if it can go down
    // if so, it checks of it can move right or left
    // untill it can not go down
    // checks if field is large enough. if so it sets the fields with x where the stone is.
    // if not, it increase the field with the needed height --> sets the fields
    // next stone is going down.

    public boolean canStoneMove(Stone fallingStone, HashMap<Integer, Row> field, char direction) {
        List<StoneCoordinate> fallingStoneCoordinates = fallingStone.getStoneCoordinates();
        for (StoneCoordinate stoneCoordinate :
                fallingStoneCoordinates) {


            switch (direction) {
                case 'd':
                    if (field.size() - stoneCoordinate.getY() >= 0) { //otherwise it will checkFields that do not exist
                        if (!field.get(stoneCoordinate.getY() - 1).checkFieldBelow(stoneCoordinate)) {
                            return false;
                        }
                    }
                    break;
                case '>':
                    if (stoneCoordinate.isTheWallToTheRight()) {
                        return false;
                    }
                    if (field.size() - stoneCoordinate.getY() > 0) { //otherwise it will checkFields that do not exist
                        if (!field.get(stoneCoordinate.getY()).checkFieldRight(stoneCoordinate)) {
                            return false;
                        }
                    }
                    break;
                case '<':
                    if (stoneCoordinate.isTheWallToTheLeft()) {
                        return false;
                    }
                    if (field.size() - stoneCoordinate.getY() > 0) { //otherwise it will checkFields that do not exist
                        if (!field.get(stoneCoordinate.getY()).checkFieldLeft(stoneCoordinate)) {
                            return false;
                        }
                    }
                    break;
            }

        }
        return true;

    }

    public Map<Integer, Row> fillingFieldWithStones(String windInput, int numberOfStones) {
        HashMap<Integer, Row> field = new HashMap<>();

        field.put(0, new Row(0));
        field.get(0).constructStartRow();


        Queue<Character> windDirections = windDirectionGenerator(windInput);
        Queue<Integer> stoneNumbers = new LinkedList<>();

        for (int i = 1; i < 6; i++) {
            stoneNumbers.add(i);
        }


        for (int i = 0; i < numberOfStones; i++) {

            //getting first stone out queue
            int stoneNumber = stoneNumbers.remove();
            stoneNumbers.add(stoneNumber);

            int startRowStone = field.size();

            Stone fallingStone = new Stone(stoneNumber, startRowStone);

            for (int j = 0; j < 4; j++) {
                char windDirection = windDirections.remove();
                windDirections.add(windDirection);
                fallingStone.moveStoneSidewaysBeforeEnteringField(windDirection);

            }

            boolean canStoneMove = true;

            do {

                canStoneMove = canStoneMove(fallingStone, field, 'd');

                if (canStoneMove) {
                    fallingStone.moveDown();


                    char windDirection = windDirections.remove();
                    windDirections.add(windDirection);
                    boolean canStoneMoveHorizontal = canStoneMove(fallingStone, field, windDirection);

                    if (canStoneMoveHorizontal && windDirection == '>') {
                        fallingStone.moveRight();
                    } else if (canStoneMoveHorizontal && windDirection == '<') {
                        fallingStone.moveLeft();
                    }

                }


            } while (canStoneMove);

            for (StoneCoordinate stoneCoordinate :
                    fallingStone.getStoneCoordinates()) {
                if (!field.containsKey(stoneCoordinate.getY())) {
                    field.put(stoneCoordinate.getY(), new Row(stoneCoordinate.getY()));
                }
                field.get(stoneCoordinate.getY()).setFields(fallingStone.getStoneCoordinates());


            }

//            System.out.println(i);
//
//            for (int k = field.size()-1; k >= 0; k--) {
//
//                field.get(k).printRow();
//
//            }
//
//            System.out.println();


        }

        return field;
    }

    public List<Row> findStartOfEqualPart(Map<Integer, Row> field, int sizeOfComparePart, int startPosition) {

        List<Row> equalPartList = new ArrayList<>();
        boolean rowIsTheSame = true;
        boolean equalPart = true;
        boolean equalPartNotFound = true;
        int counter = 0;
        // get compare part of field

        while (equalPartNotFound) {


            List<Row> firstPart = new ArrayList<>();
            List<Row> comparePart = new ArrayList<>();


            equalPart = true;

            for (int i = startPosition; i <= (sizeOfComparePart + startPosition - 1); i++) {

                firstPart.add(field.get(i));
                comparePart.add(field.get(i + sizeOfComparePart + counter));

            }

            for (int i = 0; i < firstPart.size(); i++) {
                rowIsTheSame = firstPart.get(i).isRowTheSame(comparePart.get(i).getFieldRow());
                if (!rowIsTheSame) {
                    break;
                }
            }

            if (!rowIsTheSame) {
                equalPart = false;
                counter++;
            }

            if (equalPart) {
                for (int i = 0; i < firstPart.size(); i++) {
                    System.out.println("");
                    firstPart.get(i).printRow();
                    System.out.print("  ");
                    comparePart.get(i).printRow();
                    System.out.print("  " + firstPart.get(i).getY() + ":" + comparePart.get(i).getY());


                }
                System.out.println("");
                equalPartNotFound = false;
                equalPartList = firstPart;
            }

            if (comparePart.get(0).getY() > field.size() - 100) {
                System.out.println("no equal part is found");
                break;
            }

        }

        return equalPartList;

    }

    public void checkOfWholeFieldHasPattern(Map<Integer, Row> field, List<Row> firstPart) {


        boolean rowIsTheSame = true;
        boolean equalPart = true;
        boolean equalPartNotFound = true;
        int counter = 0;
        int startPosition = firstPart.get(firstPart.size() - 1).getY() +1;
        // get compare part of field

        while (equalPartNotFound) {


            List<Row> comparePart = new ArrayList<>();


            equalPart = true;


            for (int i = startPosition + counter; i < (firstPart.size() + startPosition + counter); i++) {


                comparePart.add(field.get(i));

            }

            for (int i = 0; i < firstPart.size(); i++) {
                rowIsTheSame = firstPart.get(i).isRowTheSame(comparePart.get(i).getFieldRow());
                if (!rowIsTheSame) {
                    break;
                }
            }

            if (!rowIsTheSame) {
                equalPart = false;
                counter++;
            }

            if (equalPart) {


                System.out.print(firstPart.get(0).getY() + ":" + comparePart.get(0).getY());


                System.out.println("");

                counter++;


            }

            if (comparePart.get(0).getY() > field.size() - 100) {
                equalPartNotFound = false;
                break;
            }

        }



    }


    @Override
    public Integer part1(List<String> input) {

        return fillingFieldWithStones(input.get(0), 50).size() - 1;
    }

    @Override
    public Integer part2(List<String> input) {


        return null;
    }

    public BigInteger part3(List<String> input) {


// todo opnieuw schrijven code voor vergelijken


        Map<Integer, Row> field = fillingFieldWithStones(input.get(0), 100000);

        List<Row> pattern = new ArrayList<>();

// find pattern
        for (int j = 1; j < 200; j++) {


            pattern = findStartOfEqualPart(field, 20, j);

            if (pattern.size() > 0) {

                break;
            }

        }

        // check pattern in whole field

//        checkOfWholeFieldHasPattern(field, pattern);

        // check how many stone are the first part

        for (int i = 1; i < 100; i++) {

            Map<Integer, Row> fields = fillingFieldWithStones(input.get(0),i);

            if(fields.size()==26){

                System.out.println(i);
                break;

            }

        }

        // check how many stone is the pattern

        for (int i = 1; i < 100; i++) {

            Map<Integer, Row> fields = fillingFieldWithStones(input.get(0),i);

            if(fields.size()==79){

                System.out.println(i);
                break;

            }

        }



        // divide the 1.000.000.000.000 minus first part by number of stones in pattern

        BigInteger total = new BigInteger("1000000000000");

       BigInteger dividedPart =  total.subtract(BigInteger.valueOf(15));

       BigInteger[] divideAndRemainderResults = dividedPart.divideAndRemainder(BigInteger.valueOf(33));

        System.out.println(divideAndRemainderResults[0]);
        System.out.println(divideAndRemainderResults[1]);





        // simulate the last part with first part with it
        Map<Integer, Row> startAndEndField = fillingFieldWithStones(input.get(0),34);

        // size = 24 + times the pattern + size last part

        BigInteger sizeofPatternPart = divideAndRemainderResults[0].multiply(BigInteger.valueOf(50));

        BigInteger sizeOfTower = BigInteger.valueOf(startAndEndField.size()).add(sizeofPatternPart);


        return sizeOfTower;
    }
}
