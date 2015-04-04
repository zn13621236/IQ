package linkedlist;

import org.junit.Test;

public class LinklistSort {

    /**
     * Sort a linked list using insertion sort.
     */
    public static void main (String[] ... args) {
        ListNode a = new ListNode (0);
        insertionSort (a);
    }

    @Test
    public void testTry () {
        ListNode a = new ListNode (3);
        ListNode b = new ListNode (5);
        ListNode c = new ListNode (3);
        // ListNode d= new ListNode(4);
        a.next = b;
        // b.next=c;
        // c.next=d;
        // ListNode e= swapPairs(a);
        // deleteDuplicates(a);
        reverseBetween (a, 1, 2);
    }

    public ListNode rotateRight (ListNode head, int n) {
        if (head == null || n <= 0 || head.next == null)
            return head;
        ListNode cur = head;
        int length = 0;
        while (cur != null && cur.next != null) {
            length++;
            cur = cur.next;
        }
        length = length + 1;
        if (n >= length) {
            n = n % length;
        }
        if (n == 0)
            return head;
        ListNode begin = head;
        ListNode end = null;
        int count = 1;
        while (begin != null) {
            if (count == length - n) {
                end = begin;
                begin = begin.next;
                break;
            }
            count++;
            begin = begin.next;
        }
        end.next = null;
        cur.next = head;
        return begin;
    }

    public ListNode reverseBetween (ListNode head, int m, int n) {
        if (head.next == null || head == null || m == n)
            return head;
        int count = 1;
        ListNode cur = head;
        while (count < m - 1) {
            cur = cur.next;
            count++;
        }
        ListNode start = null;
        if (m == 1) {
            start = new ListNode (0);
        } else {
            start = cur;
            cur = cur.next;
            start.next = null;
        }
        ListNode begin = cur;
        count = m;
        do {
            ListNode next = cur.next;
            ListNode newTail = start.next;
            start.next = cur;
            if (newTail != null)
                cur.next = newTail;
            else {
                cur.next = null;
            }
            cur = next;
            count++;
        } while (count <= n && cur != null);
        begin.next = cur;
        if (m == 1)
            return start.next;
        else {
            return head;
        }
    }

    public ListNode swapPairs (ListNode head) {
        if (head == null)
            return null;
        ListNode c = head;
        ListNode mark = head.next == null ? head : head.next;
        while (c != null && c.next != null) {
            ListNode next = c.next;
            c.next = next.next;
            next.next = c;
            c = c.next;
        }
        return mark;
    }

    public ListNode deleteDuplicates (ListNode head) {
        ListNode r = new ListNode (0);
        ListNode result = r;
        ListNode c = head;
        while (c != null) {
            if (c.next == null || c.val != c.next.val) {
                r.next = c;
                r = r.next;
                r.next = null;
            } else {
                while (c.next != null && c.val == c.next.val) {
                    c = c.next;
                }
            }
            c = c.next;
        }
        return result.next;
    }

    public static ListNode insertionSort (ListNode head) {
        ListNode current = head;
        ListNode helper = new ListNode (0);
        ListNode pre = helper;
        while (current != null) {
            while (pre.next != null && pre.next.val < current.val) {// asc order
                pre = pre.next;
            }
            ListNode next = current.next;
            current.next = pre.next;
            pre.next = current;
            current = next;
        }
        return helper.next;
    }

    // Definition for singly-linked list.
    static class ListNode {

        int      val;
        ListNode next;

        ListNode (int x) {
            val = x;
            next = null;
        }
    }
}
