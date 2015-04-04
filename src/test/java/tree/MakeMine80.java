package tree;

import java.util.ArrayList;
import java.util.List;

public class MakeMine80 {

    /*
     * Follow up for "Search in Rotated Sorted Array": What if duplicates are allowed? Would this affect the run-time complexity? How and why? Write a
     * function to determine if a given target is in the array.
     */
    //
    // public boolean search(int[] A, int target) {
    //
    // }
    /*
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. For example,
     * Given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3, return 2->3.
     */
    // public ListNode deleteDuplicates (ListNode head) {}
    public class ListNode {

        int      val;
        ListNode next;

        ListNode (int x) {
            val = x;
            next = null;
        }
    }

    /*
     * Given a sorted linked list, delete all duplicates such that each element appear only once. For example, Given 1->1->2, return 1->2. Given
     * 1->1->2->3->3, return 1->2->3.
     */
    public ListNode deleteDuplicates (ListNode head) {
        ListNode dum = new ListNode (0);
        ListNode mark = dum;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur = cur.next;
            } else {
                mark.next = cur;
                mark = mark.next;
            }
        }
        if (cur != null) {
            mark.next = cur;
        }
        return dum.next;
    }
    
    /*
     * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
     * The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
     */
    
// public int largestRectangleArea(int[] height) {
//        
//    }
// 
 
 /*
  * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
  * 
  */
   
// public int maximalRectangle(char[][] matrix) {
//        
//    } 
    
    
   /*
    * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
    */
// public ListNode partition(ListNode head, int x) {
//        
//    }
   
    /*
     * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
     * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
     * 
     */
// public boolean isScramble(String s1, String s2) {
//        
//    }
    
    /*
     * Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
     */
    
 public void merge(int A[], int m, int B[], int n) {
        
    }
    
 /*
  * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
  */
 
 
// public List<Integer> grayCode(int n) {
//     
// }
 
 
 /*
  * Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
  */
// public List<List<Integer>> subsetsWithDup(int[] num) {
//     
// }
 
}
