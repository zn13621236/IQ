package com.real.go.two;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import misc.Tree.ListNode;
import misc.Tree.TreeNode;

public class MakeMine14 {

    /*
     * Given a linked list, determine if it has a cycle in it. Follow up: Can you solve it without using extra space?
     */
    public boolean hasCycle (ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    /*
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null. Follow up: Can you solve it without using extra
     * space?
     */
    public ListNode detectCycle (ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /*
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→… You must do this in-place without altering the nodes'
     * values. For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
     */
    public void reorderList (ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode h2 = slow.next;
        ListNode h1 = head;
        slow.next = null;
        ListNode dum = new ListNode (-1);
        int count = 0;
        while (h1 != null || h2 != null) {
            if (h1 == null) {
                dum.next = h2;
                h2 = h2.next;
            } else if (h2 == null) {
                dum.next = h1;
                h1 = h1.next;
            } else {
                if (count % 2 == 0) {
                    ListNode next = h1.next;
                    dum.next = h1;
                    dum = dum.next;
                    dum.next = null;
                    h1 = next;
                } else {
                    ListNode next = h2.next;
                    dum.next = h2;
                    dum = dum.next;
                    dum.next = null;
                    h2 = next;
                }
            }
            count++;
        }
    }

    /*
     * Given a binary tree, return the preorder traversal of its nodes' values. For example: Given binary tree {1,#,2,3}, 1 \ 2 / 3 return [1,2,3].
     * Note: Recursive solution is trivial, could you do it iteratively?
     */
    public List <Integer> preorderTraversal (TreeNode root) {
        List <Integer> result = new ArrayList <> ();
        Stack <TreeNode> stack = new Stack <> ();
        stack.add (root);
        while (stack.isEmpty ()) {
            TreeNode cur = stack.pop ();
            result.add (cur.val);
            stack.push (cur.right);
            stack.push (cur.left);
        }
        return result;
    }

    /*
     * Given a binary tree, return the postorder traversal of its nodes' values. For example: Given binary tree {1,#,2,3}, 1 \ 2 / 3 return [3,2,1].
     * Note: Recursive solution is trivial, could you do it iteratively?
     */
    public List <Integer> postorderTraversal (TreeNode root) {
        List <Integer> result = new ArrayList <> ();
        Stack <TreeNode> stack = new Stack <> ();
        stack.push (root);
        TreeNode cur = root.left;
        while (!stack.isEmpty ()) {
            if (cur != null) {
                stack.push (cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.peek ();
                if (node.right != null) {
                    cur = node.right;
                    node.right = null;
                } else {
                    result.add (stack.pop ().val);
                }
            }
        }
        return result;
    }

    /*
     * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set. get(key) -
     * Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1. set(key, value) - Set or insert the
     * value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before
     * inserting a new item.
     */
    public class LRUCache {

        private Map <Integer, Node> aMap;
        private Node                head;
        private Node                tail;
        private int                 capacity;

        public LRUCache (int capacity) {
            aMap = new HashMap <> ();
            this.capacity = capacity;
        }

        public int get (int key) {
            if (aMap.containsKey (key)) {
                Node n = aMap.get (key);
                tail.next = n;
                n.pre.next = null;
                n.pre = tail;
                n.next = null;
                return n.val;
            } else
                return -1;
        }

        public void set (int key, int value) {
            if (aMap.containsKey (key)) {
                Node n = aMap.get (key);
                n.val = value;
                aMap.put (key, n);
            } else {
                if (aMap.size () == capacity) {// remove head
                    aMap.remove (head.val);
                    head = head.next;
                } else {
                    Node n = new Node (value);
                    aMap.put (key, n);
                    if (aMap.size () == 0) {
                        head = n;
                        tail = n;
                    } else {
                        tail.next = n;
                        n.pre = tail;
                        tail = tail.next;
                    }
                }
            }
        }
    }

    public class Node {

        public Node pre;
        public Node next;
        private int val;

        Node (int val) {
            this.val = val;
        }
    }

    /*
     * Sort a linked list using insertion sort.
     */
    public ListNode insertionSortList (ListNode head) {
        ListNode dum = new ListNode (-1);
        ListNode mark = dum;
        while (head != null) {
            ListNode next = head.next;
            while (dum.next != null && dum.next.val < head.val) {
                dum = dum.next;
            }
            ListNode tmp = dum.next;
            dum.next = head;
            head.next = tmp;
            dum = mark;
            head = next;
        }
        return dum.next;
    }

    /*
     * Sort a linked list in O(n log n) time using constant space complexity.
     */
    public ListNode sortList (ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode h2 = slow.next;
        slow.next = null;
        ListNode n1 = sortList (head);
        ListNode n2 = sortList (h2);
        // merge..
        ListNode dum = new ListNode (-1);
        ListNode mark = dum;
        while (n1 != null || n2 != null) {
            if (n1 == null) {
                dum.next = n2;
                n2 = n2.next;
            } else if (n2 == null) {
                dum.next = n1;
                n1 = n1.next;
            } else {
                if (n1.val > n2.val) {
                    dum.next = n2;
                    n2 = n2.next;
                } else {
                    dum.next = n1;
                    n1 = n1.next;
                }
                dum = dum.next;
            }
        }
        return mark.next;
    }

    /*
     * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
     */
    public int maxPoints (Point[] points) {
        int max = 0;
        for (Point p: points) {
            Map <Double, Integer> aMap = new HashMap <> ();
            for (Point p2: points) {
                if (p.x == p2.x && p.y == p2.y) {
                    if (aMap.containsKey (Double.MIN_VALUE)) {
                        aMap.put (Double.MIN_VALUE, aMap.get (Double.MIN_VALUE) + 1);
                    } else {
                        aMap.put (Double.MIN_VALUE, 1);
                    }
                } else if (p.y == p2.y && p.x != p2.x) {
                    if (aMap.containsKey (Double.MAX_VALUE)) {
                        aMap.put (Double.MAX_VALUE, aMap.get (Double.MAX_VALUE) + 1);
                    } else {
                        aMap.put (Double.MAX_VALUE, 1);
                    }
                } else {
                    double slope = (p.x - p2.x) / (p.y - p2.y);
                    if (aMap.containsKey (slope)) {
                        aMap.put (slope, aMap.get (slope) + 1);
                    } else {
                        aMap.put (slope, 1);
                    }
                }
            }
            for (Double d: aMap.keySet ()) {
                int num = aMap.get (d);
                if (d == Double.MIN_VALUE)
                    num--;
                max = Math.max (max, num);
            }
        }
        return max;
    }

    /*
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation. Valid operators are +, -, *, /. Each operand may be an integer or
     * another expression. Some examples: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     */
    public int evalRPN (String[] tokens) {
        Stack <String> stack = new Stack <> ();
        stack.push (tokens[0]);
        for (int i = 1; i < tokens.length; i++) {
            String s = tokens[i];
            if (s.equals ("+") || s.equals ("-") || s.equals ("/")) {
                String s1 = stack.pop ();
                String s2 = stack.pop ();
                int val1 = Integer.valueOf (s1);
                int val2 = Integer.valueOf (s2);
                int val = 0;
                if (s.equals ("+")) {
                    val = val1 + val2;
                }
                if (s.equals ("-")) {
                    val = val2 - val1;
                }
                if (s.equals ("/")) {
                    val = val2 / val1;
                }
                s = String.valueOf (val);
            }
            stack.push (s);
        }
        return Integer.valueOf (stack.pop ());
    }
}
