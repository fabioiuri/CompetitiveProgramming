package Saved;

import java.util.Arrays;

public class binary_search {

    public static void main(String[] args) {
        int[] arr = {-4, -2, -2, 1, 3, 7, 7, 7, 10};
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("binary_search(-2) = " + binary_search(arr, -2)); // 1
        System.out.println("lowerBound(-2) = " + lowerBound(arr, -2)); // 1
        System.out.println("upperBound(-2) = " + upperBound(arr, -2)); // 3
        System.out.println("binary_search(-3) = " + binary_search(arr, -3)); // -1
        System.out.println("lowerBound(-3) = " + lowerBound(arr, -3)); // 1
        System.out.println("upperBound(-3) = " + upperBound(arr, -3)); // 1
        System.out.println("binary_search(3) = " + binary_search(arr, 3)); // 4
        System.out.println("lowerBound(3) = " + lowerBound(arr, 3)); // 4
        System.out.println("upperBound(3) = " + upperBound(arr, 3)); // 5
        System.out.println("binary_search(-5) = " + binary_search(arr, -5)); // -1
        System.out.println("lowerBound(-5) = " + lowerBound(arr, -5)); // 0
        System.out.println("upperBound(-5) = " + upperBound(arr, -5)); // 0

    }

    private static int binary_search(int[] arr, int p){
        int low = 0; int high = arr.length - 1;
        while(low <= high) {
            int middle = ((high + low) / 2);
            if (p == arr[middle]) return middle;
            else if (p > arr[middle]) low = middle + 1;
            else high = middle - 1;
        }
        return -1; // element p not present in arr
    }

    private static int lowerBound(int[] arr, int p){
        int low = 0; int high = arr.length;
        while(low < high) {
            int middle = ((high + low) / 2);
            if (p > arr[middle]) low = middle + 1;
            else high = middle;
        }
        return low; // lowest idx to insert w/o breaking ordering
    }

    private static int upperBound(int[] arr, int p){
        int low = 0; int high = arr.length;
        while(low < high) {
            int middle = ((high + low) / 2);
            if (arr[middle] > p) high = middle;
            else low = middle + 1;
        }
        return low; // highest idx to insert w/o breaking ordering
    }
}
