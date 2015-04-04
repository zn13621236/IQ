package search;

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
        String a = "c";
        String b = "*?*";
        isMatch(a,b);
    }

    
    
    
    
    public List<List<Integer>>  doSubSet(int[]a,List<List<Integer>> result,int index){
        if(index==result.size ()) {
            return result;
            } 
        List<List<Integer>> okre=doSubSet(a,result,index+1);
        List<Integer> bList=null;
        
        result.add (Arrays.asList (a[index]));
        for(int i=0;i<okre.size ();i++){
           bList= okre.get (i);
           bList.add (a[index]);
           result.add (bList);             
        }
        
        
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
    
    
    
    public void tryFind(int[]a){
        int i=0;
        int i1=1;
        int dif=0;
        while(i<a.length){
            
            int rdif=Math.abs(a[i]-a[i-1]);
            
            
            
            i++;
        }
        
        
        
    }
    
}
