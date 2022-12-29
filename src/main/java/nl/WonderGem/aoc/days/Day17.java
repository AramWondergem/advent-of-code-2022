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
            return fieldRow.get(cor.getX() + 1) != 'X';
        }

        public boolean checkFieldLeft(StoneCoordinate cor) {
            return fieldRow.get(cor.getX() - 1) != 'X';
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
            System.out.print(y);
            System.out.println();

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

        // The two functions below is to check if the wall is to the right or left falling the first three steps.

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
                    if (field.size() - stoneCoordinate.getY() >= 0) { //otherwise it will check fields that do not exist, because only the part of the stone in the field and one layer above the field should be checked if the coordinates below are free. If other forms of stones get into the list, this should be reconsidered.
                        if (!field.get(stoneCoordinate.getY() - 1).checkFieldBelow(stoneCoordinate)) {
                            return false;
                        }
                    }
                    break;
                case '>':
                    if (stoneCoordinate.isTheWallToTheRight()) { // if the star stone is one stone deep into the field and this is not checked, it will move into the "wall"
                        return false;
                    }
                    if (field.size() - stoneCoordinate.getY() > 0) { //otherwise it will checkFields that do not exist. it can only check the stones in the field.
                        if (!field.get(stoneCoordinate.getY()).checkFieldRight(stoneCoordinate)) {
                            return false;
                        }
                    }
                    break;
                case '<':
                    if (stoneCoordinate.isTheWallToTheLeft()) {// if the star stone is one stone deep into the field and this is not checked, it will move into the "wall"
                        return false;
                    }
                    if (field.size() - stoneCoordinate.getY() > 0) { //otherwise it will checkFields that do not exist. it can only check the stones in the field.
                        if (!field.get(stoneCoordinate.getY()).checkFieldLeft(stoneCoordinate)) {
                            return false;
                        }
                    }
                    break;
            }

        }
        return true;

    }

    public Map<Integer, Row> fillingFieldWithStones(String windInput, int numberOfStones, boolean print) {
        HashMap<Integer, Row> field = new HashMap<>();

        // construct the first row

        field.put(0, new Row(0));
        field.get(0).constructStartRow();

// wind directions and stone pattern are loaded in a queue
        Queue<Character> windDirections = windDirectionGenerator(windInput);
        Queue<Integer> stoneNumbers = new LinkedList<>();

        for (int i = 1; i < 6; i++) {
            stoneNumbers.add(i);
        }

// One go of the loop will add a stone to the tower of stones
        for (int i = 0; i < numberOfStones; i++) {

            //getting first stone out queue
            int stoneNumber = stoneNumbers.remove();
            stoneNumbers.add(stoneNumber);

            int startRowStone = field.size();

            // the stone is created and the startRowStone indicates the start coordinates of the stone.
            Stone fallingStone = new Stone(stoneNumber, startRowStone);

            // first three steps down. It will not have to take into account the tower of stones, because it always starts three steps above the tower of stones
            for (int j = 0; j < 4; j++) {
                char windDirection = windDirections.remove();
                windDirections.add(windDirection);
                fallingStone.moveStoneSidewaysBeforeEnteringField(windDirection);

            }

            boolean canStoneMove = true;

            // In this loop, it will first check if the stone can move down. If so, it will check the move directed by the wind. If the stone can not move down. The coordinates of the stone will be added to the field.

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

            // option to visualize the tower of stones

            if (print) {

                System.out.println(i + 1);

                if (field.size() > 35) {

                    for (int k = field.size() - 1; k >= field.size() - 30; k--) {

                        field.get(k).printRow();

                    }
                }

                System.out.println();

            }


        }

        return field;
    }


    public List<List<Row>> findStartOfEqualPart(Map<Integer, Row> field, int sizeOfComparePart, int startPosition) {

        List<List<Row>> equalPartList = new ArrayList<>();
        boolean rowIsTheSame = true;
        boolean equalPart = true;
        boolean equalPartNotFound = true;
        int counter = 0;


        while (equalPartNotFound) {


            List<Row> firstPart = new ArrayList<>();
            List<Row> comparePart = new ArrayList<>();


            equalPart = true;

            // loop to fill the List with parts which should be compared

            for (int i = startPosition; i <= (sizeOfComparePart + startPosition - 1); i++) {

                firstPart.add(field.get(i)); // takes the first part of the tower
                comparePart.add(field.get(i + sizeOfComparePart + counter)); // takes the next part which should be compared with the first part

            }

            // loop to check if the rows are the same. If not it will break out of the loop.

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
                equalPartNotFound = false;
                equalPartList.add(firstPart);
                equalPartList.add(comparePart);
            }

            // if statement to catch the null pointer exception

            if (comparePart.get(0).getY() > field.size() - 100) {
                System.out.println("no equal part is found" + firstPart.get(0).getY());
                break;
            }

        }

        return equalPartList;

    }

    public boolean checkOfWholeFieldHasPattern(Map<Integer, Row> field, List<Row> firstPart, int lengthPattern) {

        // function the check if the patterns goes on in the tower. I assumed that the pattern will not change after occurring five times.
        boolean rowIsTheSame = true;
        int startPosition = firstPart.get(0).getY();


        for (int k = 0; k < 5; k++) {

            // startposition to get the part of the tower which should be the same as the first part

            startPosition = startPosition + lengthPattern;


            List<Row> comparePart = new ArrayList<>();


            for (int i = startPosition; i < (firstPart.size() + startPosition); i++) {


                comparePart.add(field.get(i));

            }

            // comparing the two parts of the tower

            for (int i = 0; i < firstPart.size(); i++) {
                rowIsTheSame = firstPart.get(i).isRowTheSame(comparePart.get(i).getFieldRow());
                if (!rowIsTheSame) {
                    break;
                }
            }

            if (!rowIsTheSame) {
                return false;
            }


        }

        return true;
    }


    @Override
    public Integer part1(List<String> input) {

        return fillingFieldWithStones(input.get(0), 2022, false).size() - 1;
    }

    @Override
    public Integer part2(List<String> input) {


        return null;
    }

    public BigInteger part3(List<String> input) {

        // to find a pattern the field should be tall enough. To be sure, I multiplied the lenght of the wind pattern with 25.

        int numberOfStones = input.get(0).length() * 25;
        int numberOfStonesFirstPart = 0;
        int numberOfStonesAfterOnePattern = 0;
        int numberOfStonePerPattern = 0;

        Map<Integer, Row> field = fillingFieldWithStones(input.get(0), numberOfStones, false);

        List<List<Row>> pattern = new ArrayList<>();

// loop to find a pattern. There is a change that the pattern does not start at the beginning of the tower, so j increases until a pattern is found.
        for (int j = 1; j > 0; j++) {


            pattern = findStartOfEqualPart(field, 11, j);

            if (pattern.size() > 0) { // the function returns the pattern occuring in two places of the tower. If the list is not empty, than there is a pattern found.

                break;
            }

        }



        List<Row> basicPattern = pattern.get(0);
        List<Row> nextPattern = pattern.get(1);


        int lenghtPattern = nextPattern.get(0).getY() - basicPattern.get(0).getY();


        if (checkOfWholeFieldHasPattern(field, basicPattern, lenghtPattern)) { // check pattern in whole field
            System.out.println("The field has a pattern" + lenghtPattern);


            boolean firstGoStart = true;
            boolean firstGoEnd = true;

            // loop which add one stone more each loop and then checks the height of the tower with the y coordinates of the first four rows of the first occurrences and second occurrences of the pattern. It check the first four because it can be that the pattern does not exactly start with a stone. In order words, the start of the pattern can be half a stone.

            for (int i = 1; i < numberOfStones; i++) {


                Map<Integer, Row> sizeOfFieldTestField = fillingFieldWithStones(input.get(0), i, false);


// checks how many stones fall before the pattern occurs

                if (firstGoStart) {
                    if (sizeOfFieldTestField.size() - 1 == basicPattern.get(0).getY()) {
                        numberOfStonesFirstPart = i;
                        firstGoStart = false;
                    } else if (sizeOfFieldTestField.size() - 1 == basicPattern.get(1).getY()) {
                        numberOfStonesFirstPart = i;
                        firstGoStart = false;
                    } else if (sizeOfFieldTestField.size() - 1 == basicPattern.get(2).getY()) {
                        numberOfStonesFirstPart = i;
                        firstGoStart = false;
                    } else if (sizeOfFieldTestField.size() - 1 == basicPattern.get(3).getY()) {
                        numberOfStonesFirstPart = i;
                        firstGoStart = false;
                    }
                }

                // check how many stones fall at the end of the first occurrences of the pattern

                if (firstGoEnd) {
                    if (sizeOfFieldTestField.size() - 1 == basicPattern.get(0).getY() + lenghtPattern) {
                        numberOfStonesAfterOnePattern = i;
                        firstGoEnd = false;
                    } else if (sizeOfFieldTestField.size() - 1 == basicPattern.get(1).getY() + lenghtPattern) {
                        numberOfStonesAfterOnePattern = i;
                        firstGoEnd = false;
                    } else if (sizeOfFieldTestField.size() - 1 == basicPattern.get(2).getY() + lenghtPattern) {
                        numberOfStonesAfterOnePattern = i;
                        firstGoEnd = false;
                    } else if (sizeOfFieldTestField.size() - 1 == basicPattern.get(3).getY() + lenghtPattern) {
                        numberOfStonesAfterOnePattern = i;
                        firstGoEnd = false;
                    }
                }

                if (!firstGoStart && !firstGoEnd) {
                    break;
                }


            }


            System.out.println(numberOfStonesFirstPart);
            System.out.println(numberOfStonesAfterOnePattern);
            numberOfStonePerPattern = numberOfStonesAfterOnePattern - numberOfStonesFirstPart;
            System.out.println(numberOfStonePerPattern);


        } else {
            System.out.println("The field does not have a pattern");
        }








        // The following formula is used o find out how many times the pattern occurs. occurrences of pattern = (100.000.000.000 - (numberOfStonesFirstPart)) / numberOfStonesInPattern.

        BigInteger total = new BigInteger("1000000000000");

        BigInteger dividedPart = total.subtract(BigInteger.valueOf(numberOfStonesFirstPart));

        BigInteger[] divideAndRemainderResults = dividedPart.divideAndRemainder(BigInteger.valueOf(numberOfStonePerPattern));

        System.out.println(divideAndRemainderResults[0]);
        System.out.println(divideAndRemainderResults[1]);

        int remainder = Integer.parseInt(divideAndRemainderResults[1].toString());

        // The remainder of the division is added to the number of stones of the first part. The heigt of the tower after simulation will be added to the height of the pattern part of the tower.

        int numberOfStonesFirstPartAndRemainder = numberOfStonesFirstPart + remainder;


        // simulate the last part with first part with it
        Map<Integer, Row> startAndEndField = fillingFieldWithStones(input.get(0), numberOfStonesFirstPartAndRemainder, false);

        // size = 28 + times the pattern + size last part

        BigInteger sizeofPatternPart = divideAndRemainderResults[0].multiply(BigInteger.valueOf(lenghtPattern));

        BigInteger sizeOfTower = BigInteger.valueOf(startAndEndField.size() - 1).add(sizeofPatternPart);


        return sizeOfTower;
    }
}
