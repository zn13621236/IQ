package search;

import org.junit.Test;

public class BinarySearch {

    @Test
    public void test () {
        // int[] aArray = { 5, 4, 3, 3, 6, 7, 8, 12, 9 };
        // quickSort (aArray, 0, aArray.length - 1);
        // for (int a: aArray) {
        // System.out.println (a);
        // }
    }

    public int binarySearch (int[] a, int target) {
        int start = 0;
        int end = a.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (a[mid] == target)
                return mid;
            if (a[mid] > target) {
                // go left
                end = mid - 1;
            } else {
                // go right
                start = mid + 1;
            }
        }
        return -1;
    }

    public boolean binarySearch2 (int[] a, int target) {
        int start = 0;
        int end = a.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] == target)
                return true;
            if (a[start] < a[mid]) {// left is sorted
                if (target < a[mid] && target >= a[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (a[start] < a[mid]) {// right is sorted
                if (target > a[mid] && target <= a[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                start++;
            }
        }
        return false;
    }
}
