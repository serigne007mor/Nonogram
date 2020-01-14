package app;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;

class Utilities {
    public static String readFileAsString(final String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static int[][] removeRow(final int[][] mat, final int row) {
        final int[][] realCopy = new int[mat.length - 1][mat[0].length];
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {
                if (r != row)
                    realCopy[r][c] = mat[r][c];
            }
        }
        return realCopy;
    }

    public static boolean isRow(final int row, final int[][] array) {
        boolean flag = false;

        if (array != null && array.length >= 0) {
            if (array[row] != null) {
                flag = true;
            }
        }
        return flag;
    }

    public static int[] removeTheElement(final int[] arr, final int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }
        final int[] anotherArray = new int[arr.length - 1];

        for (int i = 0, k = 0; i < arr.length; i++) {

            if (i == index) {
                continue;
            }
            anotherArray[k++] = arr[i];
        }

        return anotherArray;
    }

    public static String[] setDifference(final String[] first, final String[] second) {
        final String[] sortedFirst = Arrays.copyOf(first, first.length);
        final String[] sortedSecond = Arrays.copyOf(second, second.length);
        Arrays.sort(sortedFirst);
        Arrays.sort(sortedSecond);

        int firstIndex = 0;
        int secondIndex = 0;

        final LinkedList<String> diffs = new LinkedList<String>();

        while (firstIndex < sortedFirst.length && secondIndex < sortedSecond.length) {
            final int compare = (int) Math.signum(sortedFirst[firstIndex].compareTo(sortedSecond[secondIndex]));

            switch (compare) {
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

        if (firstIndex < sortedFirst.length) {
            append(diffs, sortedFirst, firstIndex);
        } else if (secondIndex < sortedSecond.length) {
            append(diffs, sortedSecond, secondIndex);
        }

        final String[] strDups = new String[diffs.size()];

        return diffs.toArray(strDups);
    }

    private static void append(final LinkedList<String> diffs, final String[] sortedArray, int index) {
        while (index < sortedArray.length) {
            diffs.add(sortedArray[index]);
            index++;
        }
    }
}
