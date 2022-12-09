package nl.WonderGem.aoc.days;

import nl.WonderGem.aoc.common.Day;

import java.util.*;
import java.util.function.Predicate;

public class Day07 implements Day<Integer> {


    @Override
    public Integer part1(List<String> input) {

        input.removeIf(listItem -> (listItem.equals("$ cd ..")));
        List<String> directoryNames = new ArrayList<>();





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

        for (HashMap<Integer, String> uitzondering :
                listOfUitzonderingen) {


            TreeMap<Integer, String> sorted = new TreeMap<>();

            // Copy all data from hashMap into TreeMap
            sorted.putAll(uitzondering);

            int counter = 0;
            while (!sorted.isEmpty()) {
//                System.out.println(sorted);
                List<Integer> removeEntries = new ArrayList<>();


                Set<Map.Entry<Integer, String>> entries = sorted.entrySet();

                Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();
                Map.Entry<Integer, String> firstEntry = sorted.firstEntry();
                while (iterator.hasNext()) {

                    Map.Entry<Integer, String> secondEntry = iterator.next();


                    if (firstEntry.getValue().contains("dir") && secondEntry.getValue().contains("cd")) {
//                        System.out.println("boe");
                        removeEntries.add(firstEntry.getKey());
                        removeEntries.add(secondEntry.getKey());
                        input.set(firstEntry.getKey(), input.get(firstEntry.getKey()) + counter);
                        input.set(secondEntry.getKey(), input.get(secondEntry.getKey()) + counter);
                        counter++;
                    }

                    firstEntry = secondEntry;

                }

                for (Integer i :
                        removeEntries) {
//                    System.out.println("boe");
                    sorted.remove((i));
                }

//            // Display the TreeMap which is naturally sorted
//            for (Map.Entry<Integer, String> entry : sorted.entrySet()) {
//                System.out.println("Key = " + entry.getKey() +
//                        ", Value = " + entry.getValue());
//
//            }
            }
        }


//        for (String s :
//                input) {
//            System.out.println(input.indexOf(s) + s);
//        }
//        System.out.println(input.size());

//        for (int i = 0; i < input.size(); i++) {
//            input.set(i, input.get(i) + i);
//        }


//        System.out.println(input);

//        // find the number of directories --> setmap with dir

        for (String commandLine :
                input) {
            if (commandLine.contains("dir")) {

                directoryNames.add(commandLine);
            }
        }


        Set<String> uniqueDirectoryNames = new HashSet<>();
//        Set<String> nameUsedMultiple = new HashSet<>();
//
//        for (String name :
//                directoryNames) {
//
//            if (!uniqueDirectoryNames.add(name)) {
//                nameUsedMultiple.add(name);
//            }
//
//        }
//        System.out.println(nameUsedMultiple);



        int numberOfDir = directoryNames.size();
        System.out.println(numberOfDir);

        // get a HashMap with all directories
        Map<String, List<String>> directories = new HashMap<>();
        HashMap<String, Integer> directoryLocations = new HashMap<>();
        HashMap<Integer, String> rowOfDirectoylocations = new HashMap<>();
        int counter = 0;

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains("$ cd") && input.get(i + 1).contains("$ ls")) {
                directoryLocations.put(input.get(i), i);
                rowOfDirectoylocations.put(counter, input.get(i));
                counter++;
            }
        }

//        System.out.println(directoryLocations);
//        System.out.println(rowOfDirectoylocations);


        for (int i = 0; i < (rowOfDirectoylocations.size() - 1); i++) {

            int startOfDirectory = directoryLocations.get(rowOfDirectoylocations.get(i)) + 2;
            int endOfDirectory = directoryLocations.get(rowOfDirectoylocations.get(i + 1));

            String[] partsOfCommandLine = rowOfDirectoylocations.get(i).split(" ");

            String nameOfDirectory = "dir " + partsOfCommandLine[2];

            List<String> directory = input.subList(startOfDirectory, endOfDirectory);

            directories.put(nameOfDirectory, directory);
//            System.out.println(directories);
        }
        //adding the last directory
        int startOfDirectory = directoryLocations.get(rowOfDirectoylocations.get(rowOfDirectoylocations.size() - 1)) + 2;

        String[] partsOfCommandLine = rowOfDirectoylocations.get(rowOfDirectoylocations.size() - 1).split(" ");

        String nameOfDirectory = "dir " + partsOfCommandLine[2];

        List<String> directory = input.subList(startOfDirectory, input.size());

        directories.put(nameOfDirectory, directory);

//        System.out.println(directories);


        // find all directories with no directories in it

        Map<String, Integer> directorySizeList = new HashMap<>();
        int answer = 0;
        String nameOfDIrectory2 = "";

        while (directoryNames.size()>1) {

            for (String n:
                 directoryNames){

//                System.out.println(n);

            }



            for (Map.Entry<String, List<String>> mapElement :
                    directories.entrySet()) {

                List<String> placeholderDirectory = mapElement.getValue();
//            System.out.println(placeholderDirectory);
                String nameDirectory = mapElement.getKey();

                boolean doesNotContainNotKnowDirectory = true;
                for (String name :
                        placeholderDirectory) {
//                System.out.println(directoryNames);
                    boolean containsName = directoryNames.contains(name);
                    if (containsName) {
//                    System.out.println("false");
                        doesNotContainNotKnowDirectory = false;
                        break;
                    }

                }

                if (doesNotContainNotKnowDirectory) {
                    int sumOfValuesDirectory = 0;

//                    System.out.println(nameDirectory);
//                    System.out.println(directoryNames.contains("866"));

                    directoryNames.remove(nameDirectory);
                    nameOfDIrectory2 = nameDirectory;

                    for (String e :
                            placeholderDirectory) {
                        boolean replaced = false;
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
            directories.remove(nameOfDIrectory2);
        }
        for (Map.Entry<String, Integer> listItem :
                directorySizeList.entrySet()) {

            if(listItem.getValue() < 100001) {
                answer += listItem.getValue();
            }

        }
//
//
//        // sum of all files
//        // save in a hashset file
//        // look for dir that only has the directories in the hashset
//        // sum of all files and dir
//        //save in the hashset file
//        // go on untill you have found all dir --> list has same size as setmap
//        // iterate hashsetfile --> count the dir lower than 10.000

        return answer;
    }

    @Override
    public Integer part2(List<String> input) {
        return null;
    }
}
