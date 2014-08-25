package linkedlist;

public class LinklistSort {

    /**
     * Sort a linked list using insertion sort.
     */
    public static void main (String[] ... args) {
        ListNode a = new ListNode (0);
        insertionSort (a);
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
