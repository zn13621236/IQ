package com.real.go.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import misc.Tree.TreeNode;

public class MakeMine12 {

    /*
     * Say you have an array for which the ith element is the price of a given stock on day i. If you were only permitted to complete at most one
     * transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     */
    public int maxProfit (int[] prices) {
        int min = prices[0];
        int max = min;
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min (min, prices[i]);
            max = Math.max (max, prices[i]);
            result = Math.max (result, max - min);
        }
        return result;
    }

    /*
     * Say you have an array for which the ith element is the price of a given stock on day i. Design an algorithm to find the maximum profit. You may
     * complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple
     * transactions at the same time (ie, you must sell the stock before you buy again).
     */
    public int maxProfit2 (int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                result += prices[i] - prices[i - 1];
            }
        }
        return result;
    }

    /*
     * Say you have an array for which the ith element is the price of a given stock on day i. Design an algorithm to find the maximum profit. You may
     * complete at most two transactions. Note: You may not engage in multiple transactions at the same time (ie, you must sell the stock before you
     * buy again).
     */
    public int maxProfit3 (int[] prices) {
        int min = prices[0];
        int[] a = new int[prices.length];
        int re = 0;
        for (int i = 0; i < a.length; i++) {
            min = Math.min (min, prices[i]);
            a[i] = Math.max (prices[i] - min, re);
        }
        int max = prices[prices.length - 1];
        int[] b = new int[a.length];
        re = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            max = Math.max (prices[i], max);
            b[i] = Math.max (re, max - prices[i]);
        }
        re = 0;
        for (int i = 0; i < a.length; i++) {
            re = Math.max (re, a[i] + b[i]);
        }
        return re;
    }

    /*
     * Given a binary tree, find the maximum path sum. The path may start and end at any node in the tree. For example: Given the below binary tree, 1
     * / \ 2 3 Return 6.
     */
    public int maxPathSum (TreeNode root) {
        if (root == null)
            return 0;
        int v1 = maxPathSum (root.left);
        int v2 = maxPathSum (root.right);
        int result = Math.max (root.val, root.val + v1);
        result = Math.max (result, root.val + v1 + v2);
        result = Math.max (result, root.val + v2);
        return result;
    }

    /*
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases. For example,
     * "A man, a plan, a canal: Panama" is a palindrome. "race a car" is not a palindrome. Note: Have you consider that the string might be empty?
     * This is a good question to ask during an interview. For the purpose of this problem, we define empty string as valid palindrome.
     */
    public boolean isPalindrome (String s) {
        int start = 0;
        int end = s.length () - 1;
        while (start < end) {
            if (s.charAt (start) > 'Z' || s.charAt (start) < 'a') {
                start++;
            } else if (s.charAt (end) > 'Z' || s.charAt (end) < 'a') {
                end--;
            } else {
                if (s.charAt (start) != s.charAt (end))
                    return false;
            }
        }
        return true;
    }

    /*
     * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that: Only one letter
     * can be changed at a time Each intermediate word must exist in the dictionary For example, Given: start = "hit" end = "cog" dict =
     * ["hot","dot","dog","lot","log"] Return [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ] Note: All words have the same
     * length. All words contain only lowercase alphabetic characters.
     */
    public List <List <String>> findLadders (String start, String end, Set <String> dict) {
        Queue <String> aq = new LinkedList <> ();
        // Queue <String> aq2= new LinkedList<>();
        List <List <String>> result = new ArrayList <> ();
        Map <String, String> aMap = new HashMap <> ();
        aq.add (start);
        while (!aq.isEmpty ()) {
            String cur = aq.poll ();
            List <String> sList = transform (cur);
            for (String s: sList) {
                if (dict.contains (s)) {
                    if (s.equalsIgnoreCase (end)) {
                        aMap.put (s, cur);
                        List <String> aList = new ArrayList <> ();
                        while (aMap.containsKey (s)) {
                            aList.add (0, s);
                            s = aMap.get (s);
                            result.add (aList);
                        }
                    }
                    aq.add (s);
                    dict.remove (s);
                }
            }
        }
        return result;
    }

    private List <String> transform (String start) {
        List <String> re = new ArrayList <> ();
        for (int i = 0; i < start.length (); i++) {
            for (int j = 0; j < 26; i++) {
                if (start.charAt (i) != 'a' + j) {
                    String s = start.substring (0, i) + 'a' + j + start.substring (i + 1);
                    re.add (s);
                }
            }
        }
        return re;
    }

    /*
     * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that: Only one
     * letter can be changed at a time Each intermediate word must exist in the dictionary For example, Given: start = "hit" end = "cog" dict =
     * ["hot","dot","dog","lot","log"] As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5. Note: Return
     * 0 if there is no such transformation sequence. All words have the same length. All words contain only lowercase alphabetic characters.
     */
    public int ladderLength (String start, String end, Set <String> dict) {
        int result = 0;
        Queue <String> aq = new LinkedList <> ();
        Map <String, String> aMap = new HashMap <> ();
        aq.add (start);
        while (!aq.isEmpty ()) {
            String cur = aq.poll ();
            List <String> sList = transform (cur);
            for (String s: sList) {
                if (dict.contains (s)) {
                    aMap.put (s, cur);
                    if (end.equals (s)) {
                        while (aMap.containsKey (s)) {
                            result++;
                            s = aMap.get (s);
                        }
                    }
                    aq.add (s);
                    dict.remove (s);
                }
            }
        }
        return result;
    }

    /*
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence. For example, Given [100, 4, 200, 1, 3, 2],
     * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4. Your algorithm should run in O(n) complexity.
     */
    public int longestConsecutive (int[] num) {
        Set <Integer> aSet = new HashSet <> ();
        for (int i: num) {
            aSet.add (i);
        }
        int result = 0;
        for (int i = 0; i < num.length; i++) {
            int val = num[i];
            int count = 0;
            int mark = val;
            while (aSet.contains (val)) {
                aSet.remove (val);
                val--;
                count++;
            }
            while (aSet.contains (mark)) {
                aSet.remove (val);
                mark++;
                count++;
            }
            result = Math.max (result, count + 1);
        }
        return result;
    }

    /*
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number. An example is the root-to-leaf path
     * 1->2->3 which represents the number 123. Find the total sum of all root-to-leaf numbers. For example, 1 / \ 2 3 The root-to-leaf path 1->2
     * represents the number 12. The root-to-leaf path 1->3 represents the number 13. Return the sum = 12 + 13 = 25.
     */
    public int sumNumbers (TreeNode root) {
        if (root == null)
            return 0;
        int left = sumNumbers (root.left);
        int right = sumNumbers (root.right);
        int ml = left;
        int mr = right;
        int v1 = root.val;
        int v2 = root.val;
        while (left > 0) {
            left /= 10;
            v1 *= 10;
        }
        while (right > 0) {
            right /= 10;
            v2 *= 10;
        }
        return v1 + v2 + ml + mr;
    }

    /*
     * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'. A region is captured by flipping all 'O's into 'X's in that
     * surrounded region. For example, X X X X X O O X X X O X X O X X After running your function, the board should be: X X X X X X X X X X X X X O X
     * X
     */
    public void solve (char[][] board) {
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            if (board[i][0] == '0')
                markBoard (board, i, 0);
        }
        for (int i = 0; i < col; i++) {
            if (board[0][i] == '0')
                markBoard (board, 0, i);
        }
        for (int i = 0; i < col; i++) {
            if (board[row - 1][i] == '0')
                markBoard (board, row - 1, i);
        }
        for (int i = 0; i < row; i++) {
            if (board[i][col - 1] == '0')
                markBoard (board, i, col - 1);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'Y') {
                    board[i][j] = '0';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void markBoard (char[][] board, int i, int j) {
        boolean[][] isVisit = new boolean[board.length][board[0].length];
        Queue <Cor> q = new LinkedList <> ();
        q.add (new Cor (i, j));
        while (!q.isEmpty ()) {
            Cor c = q.poll ();
            board[c.x][c.y] = 'Y';
            isVisit[c.x][c.y] = true;
            if (c.x - 1 >= 0 && board[c.x - 1][c.y] == '0' && isVisit[c.x - 1][c.y]) {
                q.add (new Cor(c.x-1,c.y));
            }
            if (c.x + 1<board.length && board[c.x + 1][c.y] == '0' && isVisit[c.x + 1][c.y]) {
                q.add (new Cor(c.x+1,c.y));
            }
            if (c.y + 1 >= 0 && board[c.x][c.y+1] == '0' && isVisit[c.x][c.y+1]) {
                q.add (new Cor(c.x,c.y+1));
            }
            if (c.y - 1 >= 0 && board[c.x][c.y-1] == '0' && isVisit[c.x][c.y-1]) {
                q.add (new Cor(c.x,c.y-1));
            }
        }
    }

    public class Cor {

        int x;
        int y;

        Cor (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
