package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    // Given a binary tree, return the postorder traversal of its nodes' values.
    //
    // For example:
    // Given binary tree {1,#,2,3},
    // 1
    // \
    // 2
    // /
    // 3
    // return [3,2,1].
    //
    // Note: Recursive solution is trivial, could you do it iteratively?
    /**
     * Definition for binary tree public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
     */
    public static void main (String[] ... args) {
        
        
        
    }

    public List <Integer> postorderTraversal (TreeNode root) {
        List <Integer> aList = new ArrayList <Integer> ();
        postOrder (root, aList);
        return aList;
    }

    void postOrder (TreeNode root, List <Integer> aList) {
        if(root==null) return;
        while (root.left != null || root.right != null) {
            postOrder (root.left, aList);
            postOrder (root.right, aList);         
            aList.add (root.val);
        }
    }

    
    
    public List <Integer> preorderTraversal (TreeNode root) {
        List <Integer> aList = new ArrayList <Integer> ();
        postOrder (root, aList);
        return aList;
    }

    void preOrder (TreeNode root, List <Integer> aList) {
        if(root==null) return;
        while (root.left != null || root.right != null) {
            aList.add (root.val);
            postOrder (root.left, aList);
            postOrder (root.right, aList);
        }
    }
    
    public List <Integer> inorderTraversal (TreeNode root) {
        List <Integer> aList = new ArrayList <Integer> ();
        inOrder (root, aList);
        return aList;
    }

    void inOrder (TreeNode root, List <Integer> aList) {
        if(root==null) return;
        while (root.left != null || root.right != null) {
            postOrder (root.left, aList);
            aList.add (root.val);
            postOrder (root.right, aList);
        }
    }
    
    
    
    
    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode (int x) {
            val = x;
        }
    }
}
