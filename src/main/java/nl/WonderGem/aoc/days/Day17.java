package nl.WonderGem.aoc.days;

import lombok.Getter;
import lombok.Setter;
import nl.WonderGem.aoc.common.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Day17 implements Day<Integer> {

    public class Row {
        @Getter @Setter
        private int y;

        @Getter @Setter
        List<Character> fieldRow;

        public Row (int y) {
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

        public void setFields(Map<Integer,Character> coordinatesStone){


            for (Map.Entry<Integer, Character> listItem : coordinatesStone.entrySet()
            ) {

                if (fieldRow.get(listItem.getKey()) != 'X') {

                    fieldRow.set(listItem.getKey(), listItem.getValue());
                } else {
                    System.out.println("The field is already taken! fieldNumber: " + listItem.getKey() + " rowNumber:" + y);
                }

            }

        }

    }
    
    public List<Character> windDirectionGenerator(String windDirections) {

        List<Character> windDirectionList = new ArrayList<>();

        for (int i = 0; i < windDirections.length(); i++) {

            windDirectionList.add(windDirections.charAt(i));
        }

        return windDirectionList;
        
    }

    public abstract class Stone {
        private Map<Integer,Row> stoneRows;

        public Stone (int stoneNumber, int y) {

            switch (stoneNumber) {
                case 1:
                    setRowsforStone(1,y);
                    Map<Integer, Character> row1 = new HashMap<>();
                    row1.put(3,'X');
                    row1.put(4,'X');
                    row1.put(5,'X');
                    row1.put(6,'X');
                    stoneRows.get(y).setFields(row1);
                    break;
                case 2:
                    setRowsforStone(3,y);
                    Map<Integer, Character> row1 = new HashMap<>();
                    row1.put(3,'X');
                    row1.put(4,'X');
                    row1.put(5,'X');
                    row1.put(6,'X');
                    stoneRows.get(y).setFields(row1);
                    break;
// zet plekken waar de stenen moeten komen


            }

        }

        public void setRowsforStone(int numberOfRows, int y) {

            for (int i = 0; i < numberOfRows; i++) {
                Row stoneRow = new Row(y + i);
                stoneRows.put(y+i,stoneRow);
            }
        }


    }

    public class Stone1 {

        Row row;

        public Stone1 (Character windDirection1, Character windDirection2, Character windDirection3, int y) {
            this.row = new Row(y);
            Map<Integer, Character> initialStartStone = new HashMap<>();
            initialStartStone.put(3,'X');
            initialStartStone.put(4,'X');
            initialStartStone.put(5,'X');
            initialStartStone.put(6,'X');
            row.setFields(initialStartStone);

        }

        public void moveStoneWithWind(Character windDirection) {
            if (windDirection == '>' && row.getFieldRow().get(7) != 'X') {

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

    
    
    @Override
    public Integer part1(List<String> input) {
        
        return null;
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
