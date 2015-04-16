package com.real.go.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

public class MakeMine5 {

    /*
     * Given an integer n, return all distinct solutions to the n-queens puzzle. Each solution contains a distinct board configuration of the
     * n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively. For example, There exist two distinct solutions
     * to the 4-queens puzzle: [ [".Q..", // Solution 1 "...Q", "Q...", "..Q."], ["..Q.", // Solution 2 "Q...", "...Q", ".Q.."] ]
     */
    @Test
    public void testGo () {
        List <String[]> res = solveNQueens (3);
        System.out.println (res.toArray ().toString ());
    }

    public List <String[]> solveNQueens (int n) {
        return solveNQueeens (0, n, new String[n], new ArrayList <String[]> ());
    }

    public List <String[]> solveNQueeens (int row, int n, String[] result, List <String[]> re) {
        StringBuilder sb = new StringBuilder ();
        for (int i = 0; i < n - 1; i++) {
            sb.append ('.');
        }
        for (int i = 0; i < n; i++) {
            sb.insert (i, 'n');
            String a = sb.toString ();
            if (isValid (result, row, i)) {
                result[row] = a;
                if (row == n - 1) {
                    re.add (result);
                } else {
                    solveNQueeens (row + 1, n, result, re);
                }
            }
            sb.deleteCharAt (i);
        }
        return re;
    }

    private boolean isValid (String[] result, int row, int i) {
        for (String s: result) {
            if (s.charAt (i) == 'n') {
                return false;
            }
        }
        while (row >= 0 && i >= 0) {
            if (result[row].charAt (i) == 'n')
                return false;
            row--;
            i--;
        }
        return false;
    }

    /*
     * Follow up for N-Queens problem. Now, instead outputting board configurations, return the total number of distinct solutions.
     */
    public int totalNQueens (int n) {
        return -1;
    }

    /*
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order. For example, Given the following
     * matrix: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] You should return [1,2,3,6,9,8,7,4,5].
     */
    public List <Integer> spiralOrder (int[][] matrix) {
        int column = matrix[0].length;
        int row = matrix.length;
        return spiralOrder (matrix, 0, row - 1, 0, column - 1, new ArrayList <Integer> ());
    }

    public List <Integer> spiralOrder (int[][] matrix, int rs, int re, int cs, int ce, List <Integer> result) {
        if (rs > re || cs > ce) {
            return result;
        }
        for (int i = cs; i <= ce; i++) {
            result.add (matrix[rs][i]);
        }
        for (int i = rs; i < re; i++) {
            result.add (matrix[i][ce]);
        }
        for (int i = ce; i >= cs; i--) {
            result.add (matrix[re][i]);
        }
        for (int i = re; i >= rs; i--) {
            result.add (matrix[i][cs]);
        }
        spiralOrder (matrix, rs + 1, re - 1, cs + 1, ce - 1, result);
        return result;
    }

    /*
     * Given a collection of intervals, merge all overlapping intervals. For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
     */
    public List <Interval> merge (List <Interval> intervals) {
        Collections.sort (intervals, new Comparator <Interval> () {

            @Override
            public int compare (Interval o1, Interval o2) {
                if (o1.start > o2.start)
                    return 1;
                else if (o1.start < o2.start)
                    return -1;
                else
                    return 0;
            }
        });
        for (int i = 0; i < intervals.size () - 1; i++) {
            if (intervals.get (i).end > intervals.get (i + 1).start) {
                intervals.remove (i + 1);
                intervals.get (i).end = Math.max (intervals.get (i).end, intervals.get (i + 1).end);
            }
        }
        return intervals;
    }

    /*
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary). You may assume that the intervals were
     * initially sorted according to their start times. Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9]. Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16]. This is because the new interval [4,9] overlaps with
     * [3,5],[6,7],[8,10].
     */
    // public List <Interval> insert (List <Interval> intervals, Interval newInterval) {
    //
    //
    //
    //
    //
    // }
    public class Interval {

        int start;
        int end;

        Interval () {
            start = 0;
            end = 0;
        }

        Interval (int s, int e) {
            start = s;
            end = e;
        }
    }

    /*
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string. If the
     * last word does not exist, return 0. Note: A word is defined as a character sequence consists of non-space characters only. For example, Given s
     * = "Hello World", return 5.
     */
    // public int lengthOfLastWord(String s) {
    //
    //
    //
    //
    //
    // }
    /*
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order. For example, Given n = 3, You should return the
     * following matrix: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
     */
    public int[][] generateMatrix (int n) {
        int[][] matrix = new int[n][n];
        return generateMatrix1 (matrix, n, 0, 1);
    }

    public int[][] generateMatrix1 (int[][] matrix, int n, int offset, int num) {
        if (offset > n / 2)
            return matrix;
        for (int i = offset; i <= n - offset; i++) {
            matrix[offset][i] = num++;
        }
        for (int i = offset; i <= n - offset; i++) {
            matrix[i][n - offset] = num++;
        }
        for (int i = n - offset; i >= offset; i--) {
            matrix[n - offset][i] = num++;
        }
        for (int i = n - offset; i >= offset; i--) {
            matrix[i][offset] = num++;
        }
        generateMatrix1 (matrix, n, offset + 1, num);
        return matrix;
    }

    @Test
    public void testalsdf(){
       System.out.println ( getPermutation(3,2,""));
    }
    
    /*
     * The set [1,2,3,…,n] contains a total of n! unique permutations. By listing and labeling all of the permutations in order, We get the following
     * sequence (ie, for n = 3): "123" "132" "213" "231" "312" "321" Given n and k, return the kth permutation sequence. Note: Given n will be between
     * 1 and 9 inclusive.
     */
    public String getPermutation (int n, int k, String s) {
        if(n==0) return s;
        int num = doCal (n - 1);
        int remain = k % num;
        int val = k % num > 0 ? k / num + 1 : k / num;
        s = s + String.valueOf (val);
        return getPermutation (n - 1, remain, s);
    }

    private int doCal (int i) {
        int re = 1;
        while (i - 1 > 0) {
            re = i * (i - 1);
            i--;
        }
        return re;
    }
}
