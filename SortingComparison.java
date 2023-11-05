package DAA;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class SortingComparison {
    public static void main(String[] args) {
        String inputFileName = "dataset_20000_random.txt"; // disesuaikan dengan input file
        // MemoryUsage beforeMemoryUsage = getMemoryUsage();

        int[] inputData = SortingComparison.readInputFile(inputFileName);

        int[] inputDataCopy = Arrays.copyOf(inputData, inputData.length);

        

        long insertionSortStartTime = System.currentTimeMillis();
        binaryInsertionSort(inputDataCopy);
        long insertionSortEndTime = System.currentTimeMillis();
        long insertionSortTime = insertionSortEndTime - insertionSortStartTime;
        long insertionSortMemoryUsed = measureMemoryUsage();
        
        String outputFileName = "output_insertion_" + inputFileName;
        writeToFile(outputFileName, inputDataCopy);

        // MemoryUsage afterInsertionSortMemoryUsage = getMemoryUsage();

        inputDataCopy = Arrays.copyOf(inputData, inputData.length);

        long quickSortStartTime = System.currentTimeMillis();
        SortingComparison.quicksort(inputDataCopy, 0, inputDataCopy.length - 1);
        long quickSortEndTime = System.currentTimeMillis();
        long quickSortTime = quickSortEndTime - quickSortStartTime;

        // MemoryUsage afterQuickSortMemoryUsage = getMemoryUsage();

        long quickSortMemoryUsed = measureMemoryUsage();

        String outputQuicksortFileName = "output_quicksort_" + inputFileName;
        writeToFile(outputQuicksortFileName, inputDataCopy);
        

        // System.out.println("Sorted Data (Insertion Sort):");
        // System.out.println(Arrays.toString(inputDataCopy));
        System.out.println("Insertion Sort Time: " + insertionSortTime + " milliseconds\n");

        // System.out.println("Sorted Data (Quick Sort):");
        // System.out.println(Arrays.toString(inputDataCopy));
        System.out.println("Quick Sort Time: " + quickSortTime + " milliseconds\n");

        // long insertionSortMemoryUsed = (afterInsertionSortMemoryUsage.getMax() - beforeMemoryUsage.getMax());
        // long quickSortMemoryUsed = (afterQuickSortMemoryUsage.getMax() - afterInsertionSortMemoryUsage.getMax());

        System.out.println("Memory used by Binary Insertion Sort: " + insertionSortMemoryUsed  + " bytes");
        System.out.println("Memory used by Quick Sort: " + quickSortMemoryUsed  + " bytes");
    }

    public static int binaryLocFinder(int[] aList, int start, int end, int key) {
        if (start == end) {
            if (aList[start] > key) {
                return start;
            } else {
                return start + 1;
            }
        }
        if (start > end) {
            return start;
        } else {
            int middle = (start + end) / 2;
            if (aList[middle] < key) {
                return binaryLocFinder(aList, middle + 1, end, key);
            } else if (aList[middle] > key) {
                return binaryLocFinder(aList, start, middle - 1, key);
            } else {
                return middle;
            }
        }
    }

    public static int[] binaryInsertionSort(int[] aList) {
        for (int i = 1; i < aList.length; i++) {
            int cop = i;
            int key = aList[cop];
            int pop = 0;

            int place;
            if (key >= aList[pop]) {
                place = binaryLocFinder(aList, pop + 1, cop - 1, key);
            } else {
                place = binaryLocFinder(aList, 0, pop - 1, key);
            }

            placeInserter(aList, place, cop);
        }

        return aList;
    }

    public static int[] placeInserter(int[] aList, int start, int end) {
        int temp = aList[end];
        for (int k = end; k > start; k--) {
            aList[k] = aList[k - 1];
        }
        aList[start] = temp;
        return aList;
    }

    public static void quicksort(int[] arr, int start, int stop) {
        if (start < stop) {
            int pivotindex = partitionrand(arr, start, stop);
            quicksort(arr, start, pivotindex - 1);
            quicksort(arr, pivotindex + 1, stop);
        }
    }

    public static int partitionrand(int[] arr, int start, int stop) {
        Random random = new Random();
        int randpivot = random.nextInt(stop - start + 1) + start;
        int temp = arr[start];
        arr[start] = arr[randpivot];
        arr[randpivot] = temp;
        return partition(arr, start, stop);
    }

    public static int partition(int[] arr, int start, int stop) {
        int pivot = start;
        int i = start + 1;
        for (int j = start + 1; j <= stop; j++) {
            if (arr[j] <= arr[pivot]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i - 1];
        arr[i - 1] = arr[pivot];
        arr[pivot] = temp;
        return i - 1;
    }

    public static int[] readInputFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int[] data = new int[20000]; // size array sesuaikan dengan size input
            int i = 0;
            while ((line = reader.readLine()) != null && i < data.length) {
                data[i] = Integer.parseInt(line);
                i++;
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
        }
    }


    public static long measureMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        return totalMemory - freeMemory;
    }

    public static void writeToFile(String fileName, int[] data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int value : data) {
                writer.write(Integer.toString(value));
                writer.newLine(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
