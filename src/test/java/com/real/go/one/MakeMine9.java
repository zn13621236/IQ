package com.real.go.one;

import java.util.ArrayList;
import java.util.List;
import com.real.go.one.MakeMine8.ListNode;

public class MakeMine9 {
    
/*
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 */
// public int numDecodings(String s) {
//        
//    }
   
    /*
     * Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
     */
    
// public ListNode reverseBetween(ListNode head, int m, int n) {
//        
//    }
    
    /*
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     */
    
//  public List<String> restoreIpAddresses(String s) {
//        
//    }
    
   /*
    * Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
    */
  
    
// public List<Integer> inorderTraversal(TreeNode root) {
//        
//    }
    
  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  
  /*
   * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   */
  
//  public List<TreeNode> generateTrees(int n) {
//      
//  }
  
  /*
   * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   */
//  public int numTrees(int n) {
//      
//  }


  /*
   * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
   */

//  public boolean isInterleave(String s1, String s2, String s3) {
//      
//  }
  
  /*
   * Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
   */
  
//  public boolean isValidBST(TreeNode root) {
//      
//  }
  
  
  /*
   * Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
   */
  
  public void recoverTree(TreeNode root) {
      
  }
  
  
  /*
   * Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
   */
  
//  public boolean isSameTree(TreeNode p, TreeNode q) {
//      
//  }
  
  
  
  
}
