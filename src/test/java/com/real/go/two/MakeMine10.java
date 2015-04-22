package com.real.go.two;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import misc.Tree.TreeNode;

public class MakeMine10 {

    /*
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center). For example, this binary tree is symmetric: 1 /
     * \ 2 2 / \ / \ 3 4 4 3 But the following is not: 1 / \ 2 2 \ \ 3 3 Note: Bonus points if you could solve it both recursively and iteratively.
     */
    public boolean isSymmetric (TreeNode root) {
        return verify (root.left, root.right);
    }

    public boolean verify (TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 != null)
            return false;
        if (n2 == null && n1 != null)
            return false;
        if (n1.val != n2.val)
            return false;
        return verify (n1.left, n2.right) && verify (n1.right, n2.left);
    }

    /*
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level). For example: Given binary
     * tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7 return its level order traversal as: [ [3], [9,20], [15,7] ]
     */
    public List <List <Integer>> levelOrder (TreeNode root) {
        Queue <TreeNode> q = new LinkedList <> ();
        q.add (root);
        Queue <TreeNode> q2 = new LinkedList <> ();
        List <List <Integer>> result = new ArrayList <> ();
        List <Integer> aList = new ArrayList <> ();
        while (!q.isEmpty ()) {
            TreeNode n = q.poll ();
            aList.add (n.val);
            if (n.left != null) {
                q2.add (n.left);
            }
            if (n.right != null) {
                q2.add (n.right);
            }
            if (q.isEmpty ()) {
                result.add (aList);
                aList = new ArrayList <> ();
                q = q2;
                q2 = new LinkedList <> ();
            }
        }
        return result;
    }

    /*
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next
     * level and alternate between). For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7 return its zigzag level order traversal as:
     * [ [3], [20,9], [15,7] ]
     */
    public List <List <Integer>> zigzagLevelOrder (TreeNode root) {
        List <List <Integer>> result = new ArrayList <> ();
        List <Integer> aList = new ArrayList <> ();
        boolean flag = true;
        Stack <TreeNode> s = new Stack <> ();
        s.push (root);
        Stack <TreeNode> s2 = new Stack <> ();
        while (!s.isEmpty ()) {
            TreeNode n = s.pop ();
            aList.add (n.val);
            if (flag) {
                if (n.left != null) {
                    s2.push (n.left);
                }
                if (n.right != null) {
                    s2.push (n.right);
                }
            } else {
                if (n.right != null) {
                    s2.push (n.right);
                }
                if (n.left != null) {
                    s2.push (n.left);
                }
            }
            if (s.isEmpty ()) {
                s = s2;
                s2 = new Stack <> ();
                flag = !flag;
                result.add (aList);
                aList = new ArrayList <> ();
            }
        }
        return result;
    }

    /*
     * Given a binary tree, find its maximum depth. The maximum depth is the number of nodes along the longest path from the root node down to the
     * farthest leaf node.
     */
    public int maxDepth (TreeNode root) {
        if (root == null)
            return 0;
        return Math.max (maxDepth (root.left), maxDepth (root.right)) + 1;
    }

    /*
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     */
    public TreeNode buildTree (int[] preorder, int[] inorder) {
        return buildTree (preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree (int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        TreeNode re = new TreeNode (preorder[ps]);
        int index = 0;
        for (int i = is; i <= ie; i++) {
            if (inorder[i] == re.val) {
                index = i;
                break;
            }
        }
        re.left = buildTree (preorder, ps + 1, ps + 1 + index - is, inorder, is, index - 1);
        re.right = buildTree (preorder, ps + 1 + index - is + 1, pe, inorder, index + 1, ie);
        return re;
    }

    /*
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     */
    public TreeNode buildTree2 (int[] inorder, int[] postorder) {
        return buildTree2 (inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree2 (int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        TreeNode n = new TreeNode (postorder[pe]);
        int index = 0;
        for (int i = is; i <= ie; i++) {
            if (n.val == inorder[i]) {
                index = i;
                break;
            }
        }
        n.right = buildTree (inorder, index + 1, ie, postorder, pe - 1 - (ie - index + 1), pe - 1);
        n.left = buildTree (inorder, is, index - 1, postorder, ps, ps + (index - is + 1));
        return n;
    }

    /*
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to
     * root). For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7 return its bottom-up level order traversal as: [ [15,7], [9,20],
     * [3] ]
     */
    public List <List <Integer>> levelOrderBottom (TreeNode root) {
        Queue <TreeNode> q = new LinkedList <> ();
        q.add (root);
        Queue <TreeNode> q2 = new LinkedList <> ();
        List <List <Integer>> result = new ArrayList <> ();
        List <Integer> aList = new ArrayList <> ();
        while (!q.isEmpty ()) {
            TreeNode n = q.poll ();
            aList.add (n.val);
            if (n.left != null)
                q2.add (n.left);
            if (n.right != null)
                q2.add (n.right);
            if (q.isEmpty ()) {
                result.add (0, aList);
                aList.clear ();
                q = q2;
                q2 = new LinkedList <> ();
            }
        }
        return result;
    }

    /*
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public TreeNode sortedArrayToBST (int[] num) {
        return sortedArrayToBST (num,0, num.length - 1);
    }

    public TreeNode sortedArrayToBST (int[] num, int start, int end) {
        if (start == end)
            return new TreeNode (start);
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        TreeNode n = new TreeNode (mid);
        n.left = sortedArrayToBST (num, start, mid - 1);
        n.right = sortedArrayToBST (num, mid + 1, end);
        return n;
    }
    /*
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    // public TreeNode sortedListToBST(ListNode head) {
    //
    // }
    /*
     * Given a binary tree, determine if it is height-balanced. For this problem, a height-balanced binary tree is defined as a binary tree in which
     * the depth of the two subtrees of every node never differ by more than 1.
     */
     public boolean isBalanced(TreeNode root) {
         if(root.left==null&&root.right!=null) return false;
         if(root.right==null&&root.left!=null) return false;
         if(root.left==null&&root.right==null) return true;
         return isBalanced(root.left)&&isBalanced(root.right);
     }
}
