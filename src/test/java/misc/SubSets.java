package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class SubSets {

    /*
     * Given a collection of integers that might contain duplicates, S, return all possible subsets. Note: Elements in a subset must be in
     * non-descending order. The solution set must not contain duplicate subsets. For example, If S = [1,2,2], a solution is: [ [2], [1], [1,2,2],
     * [2,2], [1,2], [] ]
     */
    @Test
    public void testTry () {
        int[] num= {1,2,2};
        
        List <List <Integer>> result=  subsetsWithDup(num);
        System.out.println (result);
    }

    public List <List <Integer>> subsetsWithDup (int[] num) {
        Set <List <Integer>> result = new HashSet <> ();
        for (int i = 0; i < num.length; i++) {
            //get all current results
            Set <List <Integer>> r1 = new HashSet <> ();
            r1.addAll (result);
            for (List <Integer> tmp:r1) {
                List <Integer> tt = new ArrayList <> ();
                tt.addAll (tmp);
                tt.add (num[i]);
                result.add (tt);
            }
            List <Integer> t = new ArrayList <> ();
            t.add (num[i]);
            result.add (t);
        }
        result.add (new ArrayList <Integer> ());
        List<List<Integer>> r= new ArrayList<>();
        r.addAll (result);
        return r;
    }
}
