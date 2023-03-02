package nl.WonderGem.aoc.days;

import nl.WonderGem.aoc.common.Day;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day08 implements Day<Integer> {

    // function to fill the field --> 2d arraylist
    public class Tree {
        int x;
        int y;
        int height;
        boolean top;
        boolean bottom;
        boolean left;
        boolean right;
        boolean visible;
        boolean checked;

        public Tree(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
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

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public boolean isTop() {
            return top;
        }

        public void setTop(boolean top) {
            this.top = top;
        }

        public boolean isBottom() {
            return bottom;
        }

        public void setBottom(boolean bottom) {
            this.bottom = bottom;
        }

        public boolean isLeft() {
            return left;
        }

        public void setLeft(boolean left) {
            this.left = left;
        }

        public boolean isRight() {
            return right;
        }

        public void setRight(boolean right) {
            this.right = right;
        }

        public boolean isVisible() {
            return visible;
        }


        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public void setDeclaredField(String direction, boolean value) {
            switch (direction) {
                case "up" -> bottom = value;
                case "down" -> top = value;
                case "right" -> right = value;
                case "left" -> left = value;
            }
        }

        public boolean visible() {
            if (top || bottom || left || right) {
                this.visible = true;
            }
            return true;
        }
    }

    public List<List<Tree>> fillFieldWithTrees(List<String> input) {
        List<List<Tree>> field = new ArrayList<List<Tree>>();

        for (int i = 0; i < input.size(); i++) {
            List<Tree> row = new ArrayList<>();
            for (int j = 0; j < input.get(i).length(); j++) {
                int height = Character.getNumericValue(input.get(i).charAt(j));
                Tree tree = new Tree(j, i, height);
                row.add(tree);
            }
            field.add(row);
        }

        return field;

    }


    // function to check which tree's are visible
    // find highest --> all tree after not visible
    // find following highest visible tree and all still visible tree after that tree are not visible
    // go one until all trees are done
    // take into account if it should focus on y or x as
    // take into account the direction
    // function takes a list<Tree>

    public void checkVisibilityTrees(List<Tree> lineOfTree, String direction) {

        List<Tree> lineOfTrees = new ArrayList<>(lineOfTree);

        while(lineOfTrees.size()>0) {

            int highestTreeHeight = lineOfTrees.stream().max(Comparator.comparing(tree -> tree.height)).get().height;
            Tree highestTree = lineOfTrees.stream().filter(tree -> tree.height == highestTreeHeight).findFirst().get();
            highestTree.setDeclaredField(direction, true);
            highestTree.setChecked(true);
            int indexHighestTree = lineOfTrees.indexOf(highestTree);

            List<Tree> listToBeRemoved = new ArrayList<>();

            for (int i = indexHighestTree + 1; i < lineOfTrees.size(); i++) {
                lineOfTrees.get(i).setDeclaredField(direction, false);
                lineOfTrees.get(i).setChecked(true);
                listToBeRemoved.add(lineOfTrees.get(i));
            }


                lineOfTrees.removeAll(listToBeRemoved);


            lineOfTrees.remove(highestTree);
        }
    }



    @Override
    public Integer part1(List<String> input) {

        List<List<Tree>> field = fillFieldWithTrees(input);

        for (List<Tree> lineOfTree :
                field) {
            checkVisibilityTrees(lineOfTree, "left");
        }

        //todo next step: sorting the list in other directions



        return 0;
    }

    @Override
    public Integer part2(List<String> input) {
        return 0;
    }

}
