package tree;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {

    public static class TreeNode {

        int      val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode (int x) {
            val = x;
        }
    }
    private List <Integer> aList;
    private int            pointer = 0;

    public BSTIterator (TreeNode root) {
        aList = new ArrayList <> ();
        // in order traverse..
        traverse (root, aList);
    }

    private void traverse (TreeNode root, List <Integer> aList) {
        if (root == null)
            return;
        traverse (root.left, aList);
        aList.add (root.val);
        traverse (root.right, aList);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext () {
        if (pointer >= aList.size ())
            return false;
        else
            return false;
    }

    /** @return the next smallest number */
    public int next () {
        int result = aList.get (pointer);
        pointer++;
        return result;
    }
}
