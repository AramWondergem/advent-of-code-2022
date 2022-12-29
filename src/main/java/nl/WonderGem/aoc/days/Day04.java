package nl.WonderGem.aoc.days;

import lombok.Getter;
import nl.WonderGem.aoc.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day04 implements Day<Integer> {

    public class Elve {

        @Getter
        private int lowerLimit;
        @Getter
        private int upperLimit;
        private Elve buddyElve;

        public Elve(int lowerLimit, int upperLimit) {
            this.lowerLimit = lowerLimit;
            this.upperLimit = upperLimit;
        }

        public void setBuddyElve(Elve buddyElve) {
            this.buddyElve = buddyElve;
        }

        public boolean checkCompleteOverlapping () {
            if(this.lowerLimit == buddyElve.getLowerLimit() || this.upperLimit== buddyElve.getUpperLimit()) {
                return true;
            } else if (this.lowerLimit > buddyElve.getLowerLimit() && this.upperLimit < buddyElve.getUpperLimit()) {
                return true;
            }else if (this.lowerLimit < buddyElve.getLowerLimit() && this.upperLimit > buddyElve.getUpperLimit()) {
                return true;
            } else {
                return false;
            }
        }

        public boolean checkPartlyOverlapping () {
            if(!(upperLimit < buddyElve.getLowerLimit()) && !(buddyElve.upperLimit < this.lowerLimit)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public List<Elve> getBuddyList (List<String> input) {
       List<Elve> elveContainingBuddyList = new ArrayList<>();

        for (String s :
                input) {
            String[] informationElves = s.split(",");
            String[] informationFirstElve = informationElves[0].split("-");
            String[] informationBuddyElve = informationElves[1].split("-");

            Elve firstElve = new Elve(Integer.parseInt(informationFirstElve[0]),Integer.parseInt(informationFirstElve[1]));
            Elve buddyElve = new Elve(Integer.parseInt(informationBuddyElve[0]),Integer.parseInt(informationBuddyElve[1]));

            firstElve.setBuddyElve(buddyElve);

            elveContainingBuddyList.add(firstElve);
        }

        return elveContainingBuddyList;
    }
    @Override
    public Integer part1(List<String> input) {
        List<Elve> elveContainingBuddyList =getBuddyList(input);

        int counterOverlapping= 0;
        for (Elve elve :
                elveContainingBuddyList) {

           if( elve.checkCompleteOverlapping()) {
               counterOverlapping++;
           }

        }
        // split line based on , -
        // make elves with fields, other elves, and lower and upper limit
        // map through all elves and check who has the highest lower limit and if so, if higest limit is lower
        // count
        return counterOverlapping;
    }

    @Override
    public Integer part2(List<String> input) {
        List<Elve> elveContainingBuddyList =getBuddyList(input);

        int partlyOverlapping= 0;
        for (Elve elve :
                elveContainingBuddyList) {

            if( elve.checkPartlyOverlapping()) {
                partlyOverlapping++;
            }

        }


        return partlyOverlapping;
    }
}
