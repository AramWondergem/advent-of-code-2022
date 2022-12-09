package nl.WonderGem.aoc.days;

import nl.WonderGem.aoc.common.Day;

import java.util.*;
import java.util.function.Predicate;

public class Day07 implements Day<Integer> {

    public Map<String, Integer> makeListWithSize (List<String> input){
        input.removeIf(listItem -> (listItem.equals("$ cd .."))); // remove all instructions for switching dirs

        //some directories have the same name, which I had to rename. My code excepts unique names.
        // Below is code to collect the locations and names of those directories in the list
        // and to collect the instructions to change to the dir with locations in the list

        HashMap<Integer, String> jrhp = new HashMap<>();
        HashMap<Integer, String> zgqjbf = new HashMap<>();
        HashMap<Integer, String> grcj = new HashMap<>();
        HashMap<Integer, String> mfmps = new HashMap<>();
        HashMap<Integer, String> vzgpfd = new HashMap<>();


        for (int i = 0; i < input.size(); i++) {

            if (input.get(i).contains("$ cd jrhp") || input.get(i).contains("dir jrhp")) {
                jrhp.put(i, input.get(i));
            }

            if (input.get(i).contains("$ cd zgqjbf") || input.get(i).contains("dir zgqjbf")) {
                zgqjbf.put(i, input.get(i));
            }
            if (input.get(i).contains("$ cd grcj") || input.get(i).contains("dir grcj")) {
                grcj.put(i, input.get(i));
            }
            if (input.get(i).contains("$ cd mfmps") || input.get(i).contains("dir mfmps")) {
                mfmps.put(i, input.get(i));
            }
            if (input.get(i).contains("$ cd vzgpfd") || input.get(i).contains("dir vzgpfd")) {
                vzgpfd.put(i, input.get(i));
            }
        }

        List<HashMap<Integer,String>> listOfUitzonderingen = new ArrayList<>();
        listOfUitzonderingen.add(jrhp);
        listOfUitzonderingen.add(zgqjbf);
        listOfUitzonderingen.add(grcj);
        listOfUitzonderingen.add(mfmps);
        listOfUitzonderingen.add(vzgpfd);

        // Below the dirs with matching names are renamed.

        for (HashMap<Integer, String> uitzondering :
                listOfUitzonderingen) {


            // sorting the names of the dir on location
            TreeMap<Integer, String> sorted = new TreeMap<>();
            sorted.putAll(uitzondering);

            int counter = 0; // counter to get an unique tag for the names

            while (!sorted.isEmpty()) { // the loop looks for dir [name] directly followed by $ cd [name]. They belong together. The names are renamed by adding a unique tag. Then they are removed from the list with uitzonderingen.

                List<Integer> removeEntries = new ArrayList<>(); // collect the keys/location in the inputlist of names that are changed


                // to map the TreeMap
                Set<Map.Entry<Integer, String>> entries = sorted.entrySet();
                Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();

                Map.Entry<Integer, String> firstEntry = sorted.firstEntry();

                while (iterator.hasNext()) {

                    Map.Entry<Integer, String> secondEntry = iterator.next();

                    if (firstEntry.getValue().contains("dir") && secondEntry.getValue().contains("cd")) {

                        removeEntries.add(firstEntry.getKey());
                        removeEntries.add(secondEntry.getKey());
                        input.set(firstEntry.getKey(), input.get(firstEntry.getKey()) + counter);
                        input.set(secondEntry.getKey(), input.get(secondEntry.getKey()) + counter);
                        counter++;
                    }

                    firstEntry = secondEntry;

                }

                // remove the changed names

                for (Integer i :
                        removeEntries) {
                    sorted.remove((i));
                }
            }
        }


        // finding all the unique directoryNames

        List<String> directoryNames = new ArrayList<>();

        for (String commandLine :
                input) {
            if (commandLine.contains("dir")) {

                directoryNames.add(commandLine);
            }
        }



        // get a HashMap with all directories names and what is in the directory
        Map<String, List<String>> directories = new HashMap<>();
        // The names of the directories and where they are located in the original list
        HashMap<String, Integer> directoryLocations = new HashMap<>();
        // A hashmap to save the original order of the above Hashmap
        HashMap<Integer, String> rowOfDirectoylocations = new HashMap<>();
        int counter = 0;

        // fill the above hashmaps

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains("$ cd") && input.get(i + 1).contains("$ ls")) {
                directoryLocations.put(input.get(i), i);
                rowOfDirectoylocations.put(counter, input.get(i));
                counter++;
            }
        }



        // filling the directories list with the name of directory and the directory

        for (int i = 0; i < (rowOfDirectoylocations.size() - 1); i++) {

            int startOfDirectory = directoryLocations.get(rowOfDirectoylocations.get(i)) + 2;
            int endOfDirectory = directoryLocations.get(rowOfDirectoylocations.get(i + 1));

            String[] partsOfCommandLine = rowOfDirectoylocations.get(i).split(" ");

            String nameOfDirectory = "dir " + partsOfCommandLine[2];

            List<String> directory = input.subList(startOfDirectory, endOfDirectory);

            directories.put(nameOfDirectory, directory);

        }
        //adding the last directory
        int startOfDirectory = directoryLocations.get(rowOfDirectoylocations.get(rowOfDirectoylocations.size() - 1)) + 2;

        String[] partsOfCommandLine = rowOfDirectoylocations.get(rowOfDirectoylocations.size() - 1).split(" ");

        String nameOfDirectory = "dir " + partsOfCommandLine[2];

        List<String> directory = input.subList(startOfDirectory, input.size());

        directories.put(nameOfDirectory, directory);



        // loop in which the directories list is mapped. The first round is looks for directories without other directories
        // and calculates the size. Then in all the other loops, it looks for directories with directories in it of which the size is know. Then it calculates the size of that directory.

        Map<String, Integer> directorySizeList = new HashMap<>();

        String nameOfDIrectory2 = "";

        while (directoryNames.size()>1) {


            for (Map.Entry<String, List<String>> mapElement :
                    directories.entrySet()) {

                List<String> placeholderDirectory = mapElement.getValue();
                String nameDirectory = mapElement.getKey();

                boolean doesNotContainANotKnowDirectory = true;

                // checks if directoryNames contains the names of directories in the directory. The directoryNames is the list with directories without the size of the directory.
                for (String name :
                        placeholderDirectory) {

                    boolean containsName = directoryNames.contains(name);
                    if (containsName) {

                        doesNotContainANotKnowDirectory = false;
                        break;
                    }

                }

                // if all the sizes of the files and directories are known in the directory, then the size is calculated and saved.

                if (doesNotContainANotKnowDirectory) {
                    int sumOfValuesDirectory = 0;



                    directoryNames.remove(nameDirectory); // The size of the directory is now known and must be removed from the unknownList
                    nameOfDIrectory2 = nameDirectory;

                    for (String e :
                            placeholderDirectory) {
                        if (e.contains("dir")) {
                            Integer valueOfDirectory = directorySizeList.get(e);
                            sumOfValuesDirectory += valueOfDirectory;


                        } else {
                            String[] twoHalfs = e.split(" ");

                            sumOfValuesDirectory += Integer.parseInt(twoHalfs[0]);
                        }


                    }
                    directorySizeList.put(nameDirectory, sumOfValuesDirectory);
                }


            }
            // The directory is removed from directories to prevent that it will be calculated in the next round.
            directories.remove(nameOfDIrectory2);
        }

        return directorySizeList;

    }


    @Override
    public Integer part1(List<String> input) {

        Map<String,Integer> directorySizeList = makeListWithSize(input);//returns all directories with their size

        int answer = 0;

        // map through the list and add all dir with sizes below 10.0001
        for (Map.Entry<String, Integer> listItem :
                directorySizeList.entrySet()) {

            if(listItem.getValue() < 100001) {
                answer += listItem.getValue();
            }

        }

        return answer;
    }

    @Override
    public Integer part2(List<String> input) {

        Map<String,Integer> directorySizeList = makeListWithSize(input); //returns all directories with their size

// The list does not include the main folder. The main folder contains the following dir and one file
// 246027 gldg.jrd
//dir qffvbf
//dir qjjgh
//dir vpjqpqfm

        int total = 246027;


        for (Map.Entry<String, Integer> listItem :
                directorySizeList.entrySet()) {

            if (listItem.getKey().equals("dir qffvbf")) {
                total += listItem.getValue();
            }

            if (listItem.getKey().equals("dir qjjgh")) {
                total += listItem.getValue();
            }

            if (listItem.getKey().equals("dir vpjqpqfm")) {
                total += listItem.getValue();
            }



        }


        // Calculations how much space should be freed up.

        int spaceToFreeUp = 30000000 - (70000000 - total);

        // make a list of candidate dir that are large enough to be deleted

        List<Integer> candidatesToBeDeleted = new ArrayList<>();

        for (Map.Entry<String, Integer> listItem :
                directorySizeList.entrySet()) {

            if(listItem.getValue() > spaceToFreeUp) {
                candidatesToBeDeleted.add(listItem.getValue());
            }

        }

        Collections.sort(candidatesToBeDeleted);


        return candidatesToBeDeleted.get(0);
    }
}
