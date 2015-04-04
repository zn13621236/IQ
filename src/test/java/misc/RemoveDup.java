package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class RemoveDup {

    public int removeDuplicates (int[] A) {
        int start = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] != A[i + 1]) {
                A[start] = A[i];
                start++;
            }
        }
        A[start] = A[A.length - 1];
        int l = start + 1;
        return l;
    }

    public int removeDuplicates (int A[], int n) {
        if (n == 0) {
            return n;
        }
        int k = 0;
        for (int i = 1; i < n; i++) {
            if (A[i] != A[k]) {
                A[++k] = A[i];
            }
        }
        int j = k + 1;
        return j;
    }

    public int removeDuplicates1 (int[] A) {
        if (A.length <= 2)
            return A.length; // no need to deal with n<=2 case.
        int len = 2, itor = 2;
        while (itor < A.length) {
            if (A[itor] != A[len - 2])
                A[len++] = A[itor];
            itor++;
        }
        return len;
    }

    @Test
    public void testAbc () {
        int[] a = new int[4];
        a[0] = 1;
        a[1] = 2;
        a[2] = 2;
        a[3] = 2;
        int y = removeDuplicates1 (a);
        for (int i: a) {
            System.out.println (i);
        }
        System.out.println ("this is y: " + y);
    }

    @Test
    public void try1 () {
        System.out.println (simplifyPath ("/a/./b/../c/"));
    }

    public String simplifyPath (String input) {
        if (input == null)
            return null;
        String[] output = input.split ("/");
        Deque <String> re = new LinkedList <> ();
        for (String a: output) {
            if (a.equalsIgnoreCase (".") || a.equalsIgnoreCase ("") || a.equalsIgnoreCase (" ")) {
                // do nothing
            } else if (a.equalsIgnoreCase ("..")) {
                re.pollLast ();
                re.pollLast ();
            } else {
                re.add (a);
            }
        }
        if (re.isEmpty ())
            return "/";
        StringBuffer sb = new StringBuffer ();
        while (!re.isEmpty ()) {
            sb.append ("/");
            sb.append (re.pollFirst ());
        }
        return sb.toString ();
    }

    @Test
    public void try2 () {
        int[] a = {0,0,0,0 };
        List <List <Integer>> aList= new ArrayList<>();
        aList=sum (a, 0);
//        for(List<Integer> bList:aList){
//            Arrays.toString (bList.toArray ());
//        }
    }

    public List <List <Integer>> sum (int[] num, int target) {
        Arrays.sort (num);
      List <List <Integer>> result = new ArrayList <> ();
         if(num==null||num.length<4)return result;
         
  for(int s=0;s<num.length-3;s++){
      if(s>0&&num[s]==num[s-1]){
          continue;
      }
      for(int e=num.length-1;e>=s+3;e--){
          if(e<num.length-1&&num[e]==num[e+1]){
              continue;
          }
          int i=s+1;
          int j=e-1;
          int newTarget=target-num[s]-num[e];
          while(i<j){
              if(i>s+1&&num[i]==num[i-1]){
                  i++;
                  continue;
              }
              
                if(j<e-1&&num[j]==num[j+1]){
                  j--;
                  continue;
              } 
              if(num[i]+num[j]<newTarget){
                  i++;
              }else if(num[i]+num[j]>newTarget){
                  j--;
              }else{
                  result.add(Arrays.asList(num[s],num[i],num[j],num[e]));
              }
          }
      }
  }
      return result;
  }

    void findSum (int[] input, int target, int index, List <Integer> buffer, List <List <Integer>> result) {
       if(index>input.length-3)return ;
        for (int i = index; i < input.length; i++) {
            List<Integer> aList=new ArrayList<>();
            aList.addAll (buffer);
            aList.add (input[i]);
            int newTarget = target - input[i];
            if (newTarget == 0 && aList.size () == 4) {
                result.add (aList);
            } else if (aList.size () < 4) {
                findSum (input, newTarget, i + 1, aList, result);
            } 
        }
    }
}
