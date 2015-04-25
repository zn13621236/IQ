package com.real.go.two;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import misc.Tree.ListNode;

public class MakeMine15 {

    /*
     * Given an input string, reverse the string word by word. For example, Given s = "the sky is blue", return "blue is sky the". Update
     * (2015-02-12): For C programmers: Try to solve it in-place in O(1) space.
     */
    public String reverseWords (String s) {
        StringBuilder sb = new StringBuilder ();
        int index = 0;
        for (int i = s.length () - 1; i >= 0; i--) {
            if (s.charAt (i) == ' ') {
                sb.append (" ");
                i++;
                sb.append (s.charAt (i));
                index = sb.length ();
            } else {
                sb.insert (index, s.charAt (i));
            }
        }
        return sb.toString ();
    }

    /*
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product. For example, given the array
     * [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
     */
    public int maxProduct (int[] A) {
        int max = A[0];
        int min = A[0];
        for (int i = 1; i < A.length; i++) {
            max = Math.max (min * A[i], Math.max (A[i], A[i] * max));
            min = Math.min (A[i] * max, Math.min (A[i] * min, A[i]));
        }
        return max;
    }

    /*
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). Find the minimum
     * element. You may assume no duplicate exists in the array.
     */
    public int findMin (int[] num) {
        int start = 0;
        int end = num.length - 1;
        int min = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (num[start] < num[mid]) {
                min = Math.min (min, num[start]);
                start = mid + 1;
            } else {
                min = Math.min (min, num[mid]);
                end = mid - 1;
            }
        }
        return min;
    }

    /*
     * Follow up for "Find Minimum in Rotated Sorted Array": What if duplicates are allowed? Would this affect the run-time complexity? How and why?
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). Find the minimum
     * element. The array may contain duplicates.
     */
    public int findMin2 (int[] num) {
        int start = 0;
        int end = num.length - 1;
        int min = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            while (mid >= start && num[mid] == num[start]) {
                mid--;
            }
            if (mid < start) {
                start = mid + 1;
                continue;
            }
            while (mid <= end && num[mid] == num[end]) {
                mid++;
            }
            if (mid > end) {
                end = mid - 1;
                continue;
            }
            if (num[start] < num[mid]) {
                min = Math.min (min, num[start]);
                start = mid + 1;
            } else {
                min = Math.min (min, num[mid]);
                end = mid - 1;
            }
        }
        return min;
    }

    /*
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time. push(x) -- Push element x onto stack. pop()
     * -- Removes the element on top of the stack. top() -- Get the top element. getMin() -- Retrieve the minimum element in the stack.
     */
    class MinStack {

        Stack <Integer> s1;
        Stack <Integer> s2;

        MinStack () {
            s1 = new Stack <> ();
            s2 = new Stack <> ();
        }

        public void push (int x) {
            s1.push (x);
            s2.push (Math.min (s2.peek (), x));
        }

        public void pop () {
            s1.pop ();
            s2.pop ();
        }

        public int top () {
            int result = s1.peek ();
            return result;
        }

        public int getMin () {
            return s2.peek ();
        }
    }

    /*
     * Write a program to find the node at which the intersection of two singly linked lists begins. For example, the following two linked lists: A:
     * a1 → a2 ↘ c1 → c2 → c3 ↗ B: b1 → b2 → b3 begin to intersect at node c1. Notes: If the two linked lists have no intersection at all, return
     * null. The linked lists must retain their original structure after the function returns. You may assume there are no cycles anywhere in the
     * entire linked structure. Your code should preferably run in O(n) time and use only O(1) memory.
     */
    public ListNode getIntersectionNode (ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        int c1 = 0;
        int c2 = 0;
        while (a != null || b != null) {
            if (a != null) {
                c1++;
                a = a.next;
            }
            if (b != null) {
                c2++;
                b = b.next;
            }
        }
        ListNode n = null;
        ListNode n2 = null;
        int dif = Math.abs (c1 - c2);
        if (c1 > c2) {
            n = headA;
            n2 = headB;
        } else {
            n = headB;
            n2 = headA;
        }
        while (dif > 0) {
            n = n.next;
        }
        while (n != n2) {
            n = n.next;
            n2 = n2.next;
        }
        return n;
    }
    /*
     * A peak element is an element that is greater than its neighbors. Given an input array where num[i] ≠ num[i+1], find a peak element and return
     * its index. The array may contain multiple peaks, in that case return the index to any one of the peaks is fine. You may imagine that num[-1] =
     * num[n] = -∞. For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
     */
    // public int findPeakElement(int[] num) {
    //
    // }
    /*
     * Given an unsorted array, find the maximum difference between the successive elements in its sorted form. Try to solve it in linear time/space.
     * Return 0 if the array contains less than 2 elements. You may assume all elements in the array are non-negative integers and fit in the 32-bit
     * signed integer range.
     */
//     public int maximumGap(int[] num) {
//    
//         Map<Integer,Integer> aMap= new TreeMap<> ();
//         
//     }
    /*
     * Compare two version numbers version1 and version2. If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0. You
     * may assume that the version strings are non-empty and contain only digits and the . character. The . character does not represent a decimal
     * point and is used to separate number sequences. For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth
     * second-level revision of the second first-level revision. Here is an example of version numbers ordering: 0.1 < 1.1 < 1.2 < 13.37 Credits:
     * Special thanks to @ts for adding this problem and creating all test cases.
     */
    // public int compareVersion(String version1, String version2) {
    //
    // }
    /*
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format. If the fractional part is
     * repeating, enclose the repeating part in parentheses. For example, Given numerator = 1, denominator = 2, return "0.5". Given numerator = 2,
     * denominator = 1, return "2". Given numerator = 2, denominator = 3, return "0.(6)".
     */
    // public String fractionToDecimal(int numerator, int denominator) {
    //
    // }
}
