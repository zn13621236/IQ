package com.real.go.one;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MakeMine4 {

    /*
     * Given an unsorted integer array, find the first missing positive integer. For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2. Your
     * algorithm should run in O(n) time and uses constant space.
     */
    public int firstMissingPositive (List <Integer> nums) {
        int i = 0;
        while (i < nums.size ()) {
            if (i >= 0 && i < nums.size () && nums.get (i) != i + 1 && nums.get (nums.get (i) - 1) != nums.get (i)) {
                int tmp = nums.get (nums.get (i) - 1);
                nums.set (nums.get (i) - 1, nums.get (i));
                nums.set (i, tmp);
            } else {
                i++;
            }
        }
        for (int j = 0; j < nums.size (); j++) {
            if (nums.get (j) != j + 1)
                return j + 1;
        }
        return -1;
    }

    /*
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after
     * raining. For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
     */
    public int trap (int[] A) {
        int max = 0;
        int[] C = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            C[i] = Math.max (max, A[i]);
            max = C[i];
        }
        int[] B = new int[A.length];
        for (int i = A.length - 1; i >= 0; i--) {
            B[i] = Math.max (max, A[i]);
            max = B[i];
        }
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result += Math.min (B[i], C[i]) - A[i];
        }
        return result;
    }

    /*
     * Given two numbers represented as strings, return multiplication of the numbers as a string. Note: The numbers can be arbitrarily large and are
     * non-negative.
     */
    public String multiply (String num1, String num2) {
        int carry = 0;
        int i = num1.length ();
        int j = num2.length ();
        StringBuilder sb = new StringBuilder ();
        while (i >= 0 || j >= 0) {
            int v1 = i >= 0 ? num1.charAt (i) : 1;
            int v2 = j >= 0 ? num2.charAt (j) : 1;
            int val = v1 * v2 + carry;
            if (val > 9) {
                val = val % 10;
                carry = val / 10;
            } else {
                carry = 0;
            }
            sb.insert (0, val);
            i--;
            j--;
        }
        if (carry != 0) {
            sb.insert (0, carry);
        }
        return sb.toString ();
    }

    /*
     * Implement wildcard pattern matching with support for '?' and '*'. '?' Matches any single character. '*' Matches any sequence of characters
     * (including the empty sequence). The matching should cover the entire input string (not partial). The function prototype should be: bool
     * isMatch(const char *s, const char *p) Some examples: isMatch("aa","a") ��� false isMatch("aa","aa") ��� true isMatch("aaa","aa") ��� false
     * isMatch("aa", "*") ��� true isMatch("aa", "a*") ��� true isMatch("ab", "?*") ��� true isMatch("aab", "c*a*b") ��� false
     */
    public boolean isMatch (String s, String p) {
        int i = 0;
        int j = 0;
        while (i < s.length ()) {
            if (s.charAt (i) == p.charAt (j) || p.charAt (j) == '?') {
                i++;
                j++;
            } else if (j + 1 < p.length () && p.charAt (j + 1) == '*') {
                for (int start = i + 1; start < s.length (); start++) {
                    if (isMatch (s.substring (start), p.substring (j + 2))) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        while (j < p.length ()) {
            if (p.charAt (j) != '*') {
                return false;
            }
            j++;
        }
        return true;
    }

    /*
     * Given an array of non-negative integers, you are initially positioned at the first index of the array. Each element in the array represents
     * your maximum jump length at that position. Your goal is to reach the last index in the minimum number of jumps. For example: Given array A =
     * [2,3,1,1,4] The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
     */
    public int jump (List <Integer> nums) {
        int edge = 0;
        int step = 1;
        int maxReach = nums.get (0);
        for (int i = 1; i < nums.size (); i++) {
            if (i > edge) {
                step++;
                edge = maxReach;
            }
            maxReach = Math.max (nums.get (i), maxReach);
            if (maxReach > nums.size ())
                return step;
        }
        return step;
    }

    /*
     * Given a collection of numbers, return all possible permutations. For example, [1,2,3] have the following permutations: [1,2,3], [1,3,2],
     * [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    public List <List <Integer>> permute (int[] num) {
        return permute (num, 0);
    }

    public List <List <Integer>> permute (int[] num, int index) {
        List <List <Integer>> result = new ArrayList <> ();
        if (index == num.length - 1) {
            List <Integer> re = new ArrayList <> ();
            re.add (num[index]);
            result.add (re);
            return result;
        }
        List <List <Integer>> tResult = permute (num, index + 1);
        for (List <Integer> re: tResult) {
            for (int i = 0; i < re.size (); i++) {
                List <Integer> ar = new ArrayList <> (re);
                ar.add (i, num[index]);
                result.add (ar);
            }
        }
        return result;
    }

    /*
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations. For example, [1,1,2] have the following
     * unique permutations: [1,1,2], [1,2,1], and [2,1,1].
     */
    public List <List <Integer>> permuteUnique (int[] num) {
        Set <List <Integer>> aSet = permuteUnique (num, 0);
        List <List <Integer>> result = new ArrayList <> (aSet);
        return result;
    }

    public Set <List <Integer>> permuteUnique (int[] num, int index) {
        Set <List <Integer>> result = new HashSet <> ();
        if (index == num.length - 1) {
            List <Integer> aList = new ArrayList <> ();
            aList.add (num[index]);
            result.add (aList);
            return result;
        }
        Set <List <Integer>> tResult = permuteUnique (num, index + 1);
        for (List <Integer> aRe: tResult) {
            for (int i = 0; i < aRe.size (); i++) {
                List <Integer> aList = new ArrayList <> (aRe);
                aList.add (i, num[index]);
                result.add (aList);
            }
        }
        return result;
    }

    /*
     * You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees (clockwise). Follow up: Could you do this in-place?
     */
    public void rotate (int[][] matrix) {
        rotate (matrix, 0);
    }

    public void rotate (int[][] matrix, int offset) {
        int row = matrix.length - offset;
        int col = matrix[0].length - offset;
        if (row <= 0 || col <= 0)
            return;
        for (int i = offset; i < row; i++) {
            for (int j = offset; j < col; j++) {
                // int tmp=matrix[i][offset];
                int tmp = matrix[offset][j];
                matrix[offset][j] = matrix[row - 1 - i][offset];
                matrix[row - 1 - i][offset] = matrix[row - 1 - offset][col - 1 - j];
                matrix[row - 1 - offset][col - 1 - j] = matrix[i][col - 1 - offset];
                matrix[i][col - 1 - offset] = tmp;
            }
        }
        rotate (matrix, offset + 1);
    }

    /*
     * Given an array of strings, return all groups of strings that are anagrams. Note: All inputs will be in lower-case.
     */
    public List <String> anagrams (String[] strs) {
        for (String s: strs) {}
        return null;
    }

    /*
     * Implement pow(x, n).
     */
    public double pow (double x, int n) {
        if (n == 1)
            return x;
        if (n == 0)
            return 1;
        double tmp = pow (x, n / 2);
        double result = 0;
        if (n % 2 == 0) {
            result = tmp * tmp;
        } else {
            result = tmp * tmp * x;
        }
        return result;
    }
}
