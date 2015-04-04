package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MakeMine {

    /*
     * Implement pow(x, n).
     */
    public double pow (double x, int n) {
        if (x == 0)
            return 0;
        if (x == 0)
            return 1;
        double tmp = pow (x, n / 2);
        if (n % 2 != 0) {
            if (n > 0) {
                return tmp * tmp * x;
            } else {
                return tmp * tmp / x;
            }
        } else {
            return tmp * tmp;
        }
    }

    /*
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations. For example, [1,1,2] have the following
     * unique permutations: [1,1,2], [1,2,1], and [2,1,1].
     */
    public List <List <Integer>> permuteUnique (int[] num) {
        List <List <Integer>> result = new ArrayList <> ();
        result.addAll (permute (num, num.length));
        return result;
    }

    public Set <List <Integer>> permute (int[] num, int end) {
        if (end == 0) {
            Set <List <Integer>> result = new HashSet <> ();
            result.add (new ArrayList <> (Arrays.asList (num[0])));
            return result;
        }
        Set <List <Integer>> result = permute (num, end - 1);
        Set <List <Integer>> newResult = new HashSet <> ();
        for (List <Integer> aList: result) {
            for (int i = aList.size () - 1; i >= 0; i--) {
                aList.add (i, num[end]);
            }
            newResult.add (aList);
        }
        return newResult;
    }

    public List <List <Integer>> permuteUnique2 (int[] num) {
        List <List <Integer>> result = new ArrayList <> ();
        List <Integer> aList = new ArrayList <> (Arrays.asList (num[0]));
        result.add (aList);
        int n = 1;
        while (n < num.length) {
            List <List <Integer>> newResult = new ArrayList <> ();
            for (List <Integer> ar: result) {
                for (int i = ar.size () - 1; i >= 0; i--) {
                    aList.add (i, num[n]);
                    newResult.add (aList);
                }
            }
            result = newResult;
            n++;
        }
        return result;
    }

    public List <List <Integer>> permuteUnique3 (int[] num) {
        Set <List <Integer>> result = new HashSet <> ();
        List <Integer> aList = new ArrayList <> (Arrays.asList (num[0]));
        result.add (aList);
        int n = 1;
        while (n < num.length) {
            Set <List <Integer>> newResult = new HashSet <> ();
            for (List <Integer> ar: result) {
                for (int i = ar.size () - 1; i >= 0; i--) {
                    aList.add (i, num[n]);
                    newResult.add (aList);
                }
            }
            result = newResult;
            n++;
        }
        return new ArrayList <> (result);
    }

    public int jump (int[] A) {
        int edge = 0;
        int maxReach = A[0];
        int step = 0;
        for (int i = 1; i < A.length; i++) {
            if (i > edge) {
                step++;
                edge = maxReach;
                if (edge >= A.length - 1) {
                    return step;
                }
            }
            maxReach = Math.max (maxReach, i + A[i]);
        }
        return step;
    }

    /*
     * Implement wildcard pattern matching with support for '?' and '*'. '?' Matches any single character. '*' Matches any sequence of characters
     * (including the empty sequence). The matching should cover the entire input string (not partial). The function prototype should be: bool
     * isMatch(const char *s, const char *p) Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true isMatch("aaa","aa") → false
     * isMatch("aa", "*") → true isMatch("aa", "a*") → true isMatch("ab", "?*") → true isMatch("aab", "c*a*b") → false
     */
    public boolean isMatch (String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length ()) {
            // advancing both pointers
            if (p < pattern.length () && (pattern.charAt (p) == '?' || str.charAt (s) == pattern.charAt (p))) {
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length () && pattern.charAt (p) == '*') {
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer, 照着走不下去, reset p, s 指针前进一位，接着试
            else if (starIdx != -1) {
                p = starIdx + 1;
                match++;
                s = match;
            }
            // current pattern pointer is not star, last patter pointer was not *
            // characters do not match
            else
                return false;
        }
        // check for remaining characters in pattern
        while (p < pattern.length () && pattern.charAt (p) == '*')
            p++;
        return p == pattern.length ();
    }

    public boolean isMatch2 (String s, String p) {
        int si = 0;
        int pi = 0;
        int fixStarIndex = -1;
        int match = 0;
        while (si < s.length ()) {
            if (pi < p.length () && (s.charAt (si) == p.charAt (pi) || p.charAt (pi) == '?')) {
                si++;
                pi++;
            } else if (pi < p.length () && p.charAt (pi) == '*') {
                match = si;
                fixStarIndex = pi;
                pi++;
            } else if (fixStarIndex != -1) {// 
                pi = fixStarIndex + 1;             
                match++;
                si = match;
            } else {
                return false;
            }
        }
        while (pi < p.length ()) {
            if (p.charAt (pi) != '*')
                return false;
            pi++;
        }
        return false;
    }
}
