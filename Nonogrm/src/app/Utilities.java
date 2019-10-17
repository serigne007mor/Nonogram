package app;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

class Utilities{
    public static String readFileAsString(String fileName)throws Exception{
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static String[] setDifference(String[] first, String[] second) {
        String[] sortedFirst = Arrays.copyOf(first, first.length);
        String[] sortedSecond = Arrays.copyOf(second, second.length);
        Arrays.sort(sortedFirst);
        Arrays.sort(sortedSecond);

        int firstIndex = 0;
        int secondIndex = 0;

        LinkedList<String> diffs = new LinkedList<String>();

        while (firstIndex < sortedFirst.length && secondIndex < sortedSecond.length) {
            int compare = (int) Math.signum(sortedFirst[firstIndex].compareTo(sortedSecond[secondIndex]));

            switch(compare) {
            case -1:
                diffs.add(sortedFirst[firstIndex]);
                firstIndex++;
                break;
            case 1:
                diffs.add(sortedSecond[secondIndex]);
                secondIndex++;
                break;
            default:
                firstIndex++;
                secondIndex++;
            }
        }

        if(firstIndex < sortedFirst.length) {
            append(diffs, sortedFirst, firstIndex);
        } else if (secondIndex < sortedSecond.length) {
            append(diffs, sortedSecond, secondIndex);
        }

        String[] strDups = new String[diffs.size()];

        return diffs.toArray(strDups);
    }

    private static void append(LinkedList<String> diffs, String[] sortedArray, int index) {
        while(index < sortedArray.length) {
            diffs.add(sortedArray[index]);
            index++;
        }
    }
}