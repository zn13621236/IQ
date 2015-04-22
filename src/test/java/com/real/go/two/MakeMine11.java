package com.real.go.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import misc.Tree.TreeNode;

public class MakeMine11 {

    /*
     * Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path from the root node down to the
     * nearest leaf node.
     */
    public int minDepth (TreeNode root) {
        if (root == null)
            return 0;
        return Math.min (minDepth (root.left), minDepth (root.right)) + 1;
    }

    /*
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given
     * sum. For example: Given the below binary tree and sum = 22, 5 / \ 4 8 / / \ 11 13 4 / \ \ 7 2 1 return true, as there exist a root-to-leaf path
     * 5->4->11->2 which sum is 22.
     */
    public boolean hasPathSum (TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.val == sum && root.left == null && root.right == null)
            return true;
        else {
            return hasPathSum (root.left, sum - root.val) || hasPathSum (root.right, sum - root.val);
        }
    }

    /*
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum. For example: Given the below binary tree
     * and sum = 22, 5 / \ 4 8 / / \ 11 13 4 / \ / \ 7 2 5 1 return [ [5,4,11,2], [5,8,4,5] ]
     */
    public List <List <Integer>> pathSum (TreeNode root, int sum) {
        List <List <Integer>> result = new ArrayList <> ();
        pathSum (root, sum, new ArrayList <Integer> (), result);
        return result;
    }

    public void pathSum (TreeNode root, int sum, List <Integer> aList, List <List <Integer>> result) {
        if (root == null) {
            if (sum == 0) {
                result.add (aList);
            }
            return;
        }
        aList.add (root.val);
        int newSum = sum - root.val;
        pathSum (root.left, newSum, aList, result);
        pathSum (root.left, newSum, aList, result);
    }

    /*
     * Given a binary tree, flatten it to a linked list in-place. For example, Given 1 / \ 2 5 / \ \ 3 4 6 The flattened tree should look like: 1 \ 2
     * \ 3 \ 4 \ 5 \ 6
     */
    public void flatten (TreeNode root) {
        while (root != null) {
            TreeNode left = root.left;
            if (left != null) {
                while (left.right != null) {
                    left = left.right;
                }
                left.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

    /*
     * Given a string S and a string T, count the number of distinct subsequences of T in S. A subsequence of a string is a new string which is formed
     * from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining
     * characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not). Here is an example: S = "rabbbit", T = "rabbit" Return 3.
     */
    public int numDistinct (String S, String T) {
        int row = T.length ();
        int col = S.length ();
        int[][] dp = new int[row][col];
        dp[0][0] = S.charAt (0) == T.charAt (0) ? 1 : 0;
        for (int i = 1; i < col; i++) {
            dp[0][i] = S.charAt (i) == T.charAt (0) ? dp[0][i - 1] + 1 : dp[0][i - 1];
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (S.charAt (j) == T.charAt (i)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    /*
     * Given a binary tree struct TreeLinkNode { TreeLinkNode *left; TreeLinkNode *right; TreeLinkNode *next; } Populate each next pointer to point to
     * its next right node. If there is no next right node, the next pointer should be set to NULL. Initially, all next pointers are set to NULL.
     * Note: You may only use constant extra space. You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every
     * parent has two children). For example, Given the following perfect binary tree, 1 / \ 2 3 / \ / \ 4 5 6 7 After calling your function, the tree
     * should look like: 1 -> NULL / \ 2 -> 3 -> NULL / \ / \ 4->5->6->7 -> NULL
     */
    public void connect (TreeLinkNode root) {
        if (root != null) {
            if (root.left != null) {
                root.left.next = root.right;
            }
            if (root.next != null) {
                root.right.next = root.next.left;
            }
            connect (root.left);
            connect (root.right);
        }
    }

    public class TreeLinkNode {

        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode (int x) {
            val = x;
        }
    }

    /*
     * Follow up for problem "Populating Next Right Pointers in Each Node". What if the given tree could be any binary tree? Would your previous
     * solution still work? Note: You may only use constant extra space. For example, Given the following binary tree, 1 / \ 2 3 / \ \ 4 5 7 After
     * calling your function, the tree should look like: 1 -> NULL / \ 2 -> 3 -> NULL / \ \ 4-> 5 -> 7 -> NULL
     */
    public void connect2 (TreeLinkNode root) {
        if (root != null) {
            if (root.left != null) {
                if (root.right != null) {
                    root.left.next = root.right;
                }
            }
            if (root.next != null) {
                TreeLinkNode t = root.right == null ? root.left : root.right;
                TreeLinkNode t2 = root.next.left == null ? root.next.right : root.next.left;
                t.next = t2;
            }
            connect2 (root.left);
            connect2 (root.right);
        }
    }

    /*
     * Given numRows, generate the first numRows of Pascal's triangle. For example, given numRows = 5, Return [ [1], [1,1], [1,2,1], [1,3,3,1],
     * [1,4,6,4,1] ]
     */
    public List <List <Integer>> generate (int numRows) {
        List <List <Integer>> result = new ArrayList <> ();
        if (numRows == 1) {
            List <Integer> aList = Arrays.asList (1);
            result.add (aList);
            return result;
        }
        List <List <Integer>> re1 = generate (numRows - 1);
        result.addAll (re1);
        List <Integer> bList = re1.get (re1.size () - 1);
        List <Integer> aList = new ArrayList <> ();
        aList.add (1);
        for (int i = 0; i < bList.size () - 1; i++) {
            aList.add (bList.get (i) + bList.get (i + 1));
        }
        aList.add (1);
        result.add (aList);
        return result;
    }

    /*
     * Given an index k, return the kth row of the Pascal's triangle. For example, given k = 3, Return [1,3,3,1].
     */
    public List <Integer> getRow (int rowIndex) {
        List <Integer> result = new ArrayList <> ();
        if (rowIndex == 1) {
            return Arrays.asList (1);
        }
        List <Integer> bList = getRow (rowIndex - 1);
        result.add (1);
        for (int i = 0; i < bList.size () - 1; i++) {
            result.add (bList.get (i) + bList.get (i + 1));
        }
        result.add (1);
        return result;
    }

    /*
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below. For example, given
     * the following triangle [ [2], [3,4], [6,5,7], [4,1,8,3] ] The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     */
    public int minimumTotal (List <List <Integer>> triangle) {
        for (int i = triangle.size () - 2; i >= 0; i--) {
            List <Integer> aList = triangle.get (i);
            List <Integer> bList = triangle.get (i + 1);
            for (int j = 0; j <= aList.size (); j++) {
                aList.set (j, aList.get (i) + Math.min (bList.get (i), bList.get (i + 1)));
            }
        }
        return triangle.get (0).get (0);
    }
}
