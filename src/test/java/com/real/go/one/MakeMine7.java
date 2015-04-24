package com.real.go.one;

import java.util.ArrayList;
import java.util.List;

public class MakeMine7 {

    /*
     * Given an absolute path for a file (Unix-style), simplify it. For example, path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c"
     */
    public String simplifyPath (String path) {
        path = path.replace (" ", "");
        String[] ss = path.split ("/");
        int i = ss.length - 1;
        StringBuilder sb = new StringBuilder ();
        while (i >= 0) {
            if ("..".equalsIgnoreCase (ss[i])) {
                i -= 2;
            } else if (".".equalsIgnoreCase (ss[i])) {
                i -= 1;
            } else if ("~".equalsIgnoreCase (ss[i])) {
                break;
            } else {
                sb.insert (0, ss[i]);
                sb.insert (0, "/");
            }
        }
        return sb.toString ();
    }

    /*
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
     * You have the following 3 operations permitted on a word: a) Insert a character b) Delete a character c) Replace a character
     */
    public int minDistance (String word1, String word2) {
        int row = word1.length ();
        int col = word2.length ();
        int[][] dp = new int[row][col];
        // dp[0][0]=0;
        for (int i = 0; i < row; i++) {
            dp[i][0] = i + 1;
        }
        for (int i = 0; i < col; i++) {
            dp[0][i] = i + 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (i == j && word1.charAt (i) == word2.charAt (j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min (dp[i - 1][j] + 1, Math.min (dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    /*
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     */
    public void setZeroes (int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /*
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties: Integers in each row are
     * sorted from left to right. The first integer of each row is greater than the last integer of the previous row. For example, Consider the
     * following matrix: [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] Given target = 3, return true.
     */
    public boolean searchMatrix (int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0;
        int end = row - 1;
        int row1 = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] > target) {
                end = mid - 1;
            } else if (matrix[mid][0] < target) {
                if (matrix[mid][col] > target) {
                    row1 = mid;
                    break;
                } else {
                    start = mid + 1;
                }
            } else {
                return true;
            }
        }
        start = 0;
        end = col - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (matrix[row1][mid] > target) {
                end = mid - 1;
            } else if (matrix[row1][mid] < target) {
                start = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /*
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the
     * order red, white and blue. Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively. Note: You are
     * not suppose to use the library's sort function for this problem.
     */
    public void sortColors (int[] A) {
        int i1 = 0, i2 = 0, i3 = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                A[i3] = 3;
                A[i2] = 2;
                A[i1] = 1;
                i1++;
                i2++;
                i3++;
            } else if (A[i] == 2) {
                A[i3] = 3;
                A[i2] = 2;
                i2++;
                i3++;
            } else {
                A[i3] = 3;
                i3++;
            }
        }
    }

    /*
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n). For example, S =
     * "ADOBECODEBANC" T = "ABC" Minimum window is "BANC". Note: If there is no such window in S that covers all characters in T, return the emtpy
     * string "". If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
     */
    // public String minWindow(String S, String T) {
    //
    // }
    /*
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n. For example, If n = 4 and k = 2, a solution is: [
     * [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
     */
    public List <List <Integer>> combine (int n, int k) {
        List <List <Integer>> result = new ArrayList <> ();
        combine (n, k, 1, new ArrayList <Integer> (), result);
        return result;
    }

    public void combine (int n, int k, int index, List <Integer> res, List <List <Integer>> result) {
        if(index>n) return;
    	if (res.size () == k) {
            result.add (res);
            return;
        }
        for (int i = index; i <= n; i++) {
            res.add (i);
            combine (n, k, i + 1, res, result);
            res.remove (i);
        }
    }

    /*
     * Given a set of distinct integers, S, return all possible subsets. Note: Elements in a subset must be in non-descending order. The solution set
     * must not contain duplicate subsets. For example, If S = [1,2,3], a solution is: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
     */
    public List <List <Integer>> subsets (int[] S) {
        List <List <Integer>> result = subsets (S, 0);
        result.add (new ArrayList <Integer> ());
        return result;
    }

    public List <List <Integer>> subsets (int[] S, int index) {
        if (index == S.length - 1) {
            List <Integer> a = new ArrayList <> ();
            a.add (S[index]);
            List <List <Integer>> aRe = new ArrayList <> ();
            aRe.add (a);
            return aRe;
        }
        List <List <Integer>> result = subsets (S, index + 1);
        List <List <Integer>> newResult = new ArrayList <> (result);
        for (List <Integer> aList: result) {
            List <Integer> tList = new ArrayList <> (aList);
            tList.add (S[index]);
            newResult.add (tList);
        }
        return newResult;
    }

    /*
     * Given a 2D board and a word, find if the word exists in the grid. The word can be constructed from letters of sequentially adjacent cell, where
     * "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once. For example, Given
     * board = [ ["ABCE"], ["SFCS"], ["ADEE"] ] word = "ABCCED", -> returns true, word = "SEE", -> returns true, word = "ABCB", -> returns false.
     */
    public boolean exist (char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean[][] visited = new boolean[row][col];
                if (exist (board, visited, i, j, word)) {
                    return true;
                };
            }
        }
        return false;
    }

    public boolean exist (char[][] board, boolean[][] visited, int i, int j, String word) {
        int row = board.length;
        int col = board[0].length;
        if (board[i][j] == word.charAt (0)) {
            if (word.length () == 1)
                return true;
            else {
                if (i + 1 < row && visited[i + 1][j] == false) {
                    if (exist (board, visited, i + 1, j, word.substring (1))) {
                        return true;
                    };
                }
                if (i - 1 >= 0 && visited[i - 1][j] == false) {
                    if (exist (board, visited, i - 1, j, word.substring (1))) {
                        return true;
                    };
                }
                if (j + 1 < col && visited[i][j + 1] == false) {
                    if (exist (board, visited, i, j + 1, word.substring (1))) {
                        return true;
                    };
                }
                if (j - 1 >= 0 && visited[i][j - 1] == false) {
                    if (exist (board, visited, i, j - 1, word.substring (1))) {
                        return true;
                    };
                }
            }
        }
        return false;
    }

    /*
     * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice? For example, Given sorted array A = [1,1,1,2,2,3], Your
     * function should return length = 5, and A is now [1,1,2,2,3].
     */
    public int removeDuplicates (int[] A) {
        int i1 = 0, i2 = 0;
        // int i=0;
        // while(){
        int i = 0;
        while (i < A.length) {
            if (i2 <= A.length - 1 && A[i2 + 1] == A[i2]) {
                while (i2 <= A.length - 1 && A[i2 + 1] == A[i2]) {
                    i2++;
                }
                A[i++] = A[i1];
                A[i] = A[i2];
            }
            i2++;
            i1 = i2;
            i++;
        }
        return i + 1;
        // }
    }
}
