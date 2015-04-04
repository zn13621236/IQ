package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.junit.Test;

public class WordLadder2 {

    /*
     * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that: Only one letter
     * can be changed at a time Each intermediate word must exist in the dictionary For example, Given: start = "hit" end = "cog" dict =
     * ["hot","dot","dog","lot","log"] Return [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ] Note: All words have the same
     * length. All words contain only lowercase alphabetic characters.
     */
    @Test
    public void trytest () {
        Set <String> a = new HashSet <> ();
        a.add ("ted");
        a.add ("tex");
        a.add ("red");
        a.add ("tax");
        a.add ("tad");
        a.add ("den");
        a.add ("rex");
        a.add ("pee");
        System.out.println (findLadders ("red", "tax", a));
    }

    public List <List <String>> findLadders (String start, String end, Set <String> dict) {
        List <List <String>> result = new ArrayList <> ();
        Map <String, String> bMap = new HashMap <> ();
        Queue <String> bq = new LinkedList <> ();
        Set <String> visited = new HashSet <> ();
        bq.add (start);
        visited.add (start);
        while (!bq.isEmpty ()) {
            String word = bq.poll ();
            Set<String> aSet=transform (word);
            for (String a:aSet) {
                if (a.equalsIgnoreCase (end)) {
                    // gather()
                    List <String> t = new ArrayList <> ();
                    t.add (a);
                    while (word != null) {
                        t.add (0, word);
                        word = bMap.get (word);
                    }
                    if (result.size () > 0 && t.size () > result.get (0).size ()) {
                        return result;
                    }
                    result.add (t);
                }  
                if (dict.contains (a)) {
                    if( !visited.contains (a)){
                    bq.add (a);
                    visited.add (a);
                    bMap.put (a, word);
                }}
            }
        }
        return result;
    }

    public Set <String> transform (String input) {
        Set <String> aList = new HashSet <> ();
        for (int i = 0; i < input.length (); i++) {
            char[] a = input.toCharArray ();
            for (char c = 'a'; c <= 'z'; c++) {
                if(c!=input.charAt(i)){
                a[i] = c;
                String ns = String.valueOf (a);
                aList.add (ns);}
            }
        }
        return aList;
    }
}
