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
                    if(stoneCoordinate.isTheWallToTheRight()){
                        return false;
                    }
                    if (field.size() - stoneCoordinate.getY() > 0) { //otherwise it will checkFields that do not exist
                        if (!field.get(stoneCoordinate.getY()).checkFieldRight(stoneCoordinate)) {
                            return false;
                        }
                    }
                    break;
                case '<':
                    if(stoneCoordinate.isTheWallToTheLeft()){
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


    @Override
    public Integer part1(List<String> input) {

        HashMap<Integer, Row> field = new HashMap<>();

        field.put(0, new Row(0));
        field.get(0).constructStartRow();


        Queue<Character> windDirections = windDirectionGenerator(input.get(0));
        Queue<Integer> stoneNumbers = new LinkedList<>();

        for (int i = 1; i < 6; i++) {
            stoneNumbers.add(i);
        }

        BigInteger value = new BigInteger(String.valueOf(1000000000000L));
        BigInteger one = new BigInteger(String.valueOf(1));



        for (BigInteger i = new BigInteger(String.valueOf(0)); i.compareTo(value)>-1; i.add(one)) {

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
//                field.get(k).printRow();
//            }
//
//            System.out.println();





        }




        return field.size() -1 ;
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
