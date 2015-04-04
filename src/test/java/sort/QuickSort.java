package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class QuickSort {

    @Test
    public void test () {
        // int[] aArray = { 5, 4, 3, 3, 6, 7, 8, 12, 9 };
        // quickSort (aArray, 0, aArray.length - 1);
        // for (int a: aArray) {
        // System.out.println (a);
        // }
        // String a = "c";
        // String b = "*?*";
        // isMatch(a,b);
        //
        int[] a = { 1, 2 };
        subsets (a);
    }

    public List <List <Integer>> subsets (int[] S) {
        if (S == null)
            return null;
        Arrays.sort (S);
        List <List <Integer>> result = new ArrayList <List <Integer>> ();
        result = getSubSet (S, S.length-1);
        result.add (new ArrayList <Integer> ());
        return result;
    }

    List <List <Integer>> getSubSet (int[] s, int index) {
        List<List<Integer>> result= new ArrayList<List<Integer>>();
        if (index <0) {
            return result;
        }
        List<List<Integer>> subResult=getSubSet (s, index -1);
        result.addAll (subResult);
        for (int i = 0; i < subResult.size (); i++) {
            List <Integer> bList = new ArrayList <> ();
            bList.addAll ( subResult.get (i));
            bList.add (s[index]);
            result.add (bList);
        }
        result.add (Arrays.asList (s[index]));
        return result;
    }

    public static void quickSort (int[] array, int left, int right) {
        int index = partition (array, left, right);
        if (index == -1)
            return;
        quickSort (array, left, index - 1);
        quickSort (array, index, right);
    }

    public static int partition (int[] array, int left, int right) {
        if (array.length - 1 < right || right <= left)
            return -1;
        // choose pivot
        int pivot = array[left];
        while (left < right) {
            while (array[right] > pivot) {
                right--;
            }
            while (array[left] < pivot) {
                left++;
            }
            if (left <= right) {
                swap (array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void swap (int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public boolean isMatch (String s, String p) {
        if (p != null && s != null) {
            char[] ps = p.toCharArray ();
            char[] ss = s.toCharArray ();
            if (p.contains ("*")) {
                String[] pss = p.split ("\\*");
                for (String pss1: pss) {
                    if (pss1.contains ("?")) {
                        if (pss.length > s.length ())
                            return false;
                        String[] sub = pss1.split ("\\?");
                        for (String sb: sub) {
                            if (!s.contains (sb))
                                return false;
                        }
                    } else {
                        if (!s.contains (pss1))
                            return false;
                    }
                }
                return true;
            }
            if (p.contains ("?")) {
                if (p.length () != s.length ())
                    return false;
                // compare two string with "?"
                for (int i = 0; i < ps.length; i++) {
                    if ((!String.valueOf (ps[i]).equalsIgnoreCase ("?")) && (ps[i] != ss[i])) {
                        return false;
                    }
                }
                return true;
            }
            return p.equalsIgnoreCase (s);
        }
        if (s == null)
            return true;
        else
            return false;
    }

    public int findProfit (int[] prices) {
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > profit) {
                profit = prices[i] - min;
            }
        }
        return profit;
    }
}
