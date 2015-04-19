package com.real.go.one;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import misc.Tree.ListNode;

public class MakeMine2 {
	/*
	 * Merge two sorted linked lists and return it as a new list. The new list
	 * should be made by splicing together the nodes of the first two lists.
	 */

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				dummy.next = l2;
				break;
			} else if (l2 == null) {
				dummy.next = l1;
				break;
			} else {
				if (l1.val > l2.val) {
					dummy.next = l2;
					l2 = l2.next;
				} else {
					dummy.next = l1;
					l1 = l1.next;
				}
			}
		}
		return dummy.next;
	}

	/*
	 * Given n pairs of parentheses, write a function to generate all
	 * combinations of well-formed parentheses.
	 * 
	 * For example, given n = 3, a solution set is:
	 * 
	 * "((()))", "(()())", "(())()", "()(())", "()()()"
	 */

	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		if (n == 1) {
			result.add("()");
			return result;
		}
		List<String> tResult = generateParenthesis(n - 1);
		Set<String> aResult = new HashSet<>();
		for (String s : tResult) {
			StringBuilder sb = new StringBuilder(s);
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(' || i == 0) {
					sb.insert(i, "()");
				}
			}
			aResult.add(sb.toString());
		}
		result.addAll(aResult);
		return result;
	}

	/*
	 * Merge k sorted linked lists and return it as one sorted list. Analyze and
	 * describe its complexity.
	 */

	public ListNode mergeKLists(List<ListNode> lists) {

		return null;
	}

	public ListNode mergeKLists(List<ListNode> lists, int start, int end) {
		if (start == end)
			return lists.get(start);
		if (start > end)
			return null;
		int mid = start + (end - start) / 2;
		ListNode l1 = mergeKLists(lists, start, mid - 1);
		ListNode l2 = mergeKLists(lists, mid, end);
		ListNode dummy = new ListNode(0);
		while (l1 != null || l2 != null) {
			if (l1 == null || l2 == null) {
				dummy.next = l2 == null ? l1 : l2;
				break;
			}
			if (l1.val > l2.val) {
				dummy.next = l2;
				l2 = l2.next;
			} else {
				dummy.next = l1;
				l1 = l1.next;
			}

		}
		return dummy.next;
	}

	/*
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * 
	 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * Your algorithm should use only constant space. You may not modify the
	 * values in the list, only nodes itself can be changed.
	 */

	public ListNode swapPairs(ListNode head) {
		ListNode dum = new ListNode(0);
		ListNode mark = dum;
		int count = 0;
		while (head != null) {
			ListNode next = head.next;
			dum.next = head;
			head.next = null;
			head = next;
			count++;
			if (count == 2) {
				dum = dum.next.next;
				count = 0;
			}
		}
		return mark.next;
	}

	/*
	 * Given a linked list, reverse the nodes of a linked list k at a time and
	 * return its modified list.
	 * 
	 * If the number of nodes is not a multiple of k then left-out nodes in the
	 * end should remain as it is.
	 * 
	 * You may not alter the values in the nodes, only nodes itself may be
	 * changed.
	 * 
	 * Only constant memory is allowed.
	 * 
	 * For example, Given this linked list: 1->2->3->4->5
	 * 
	 * For k = 2, you should return: 2->1->4->3->5
	 * 
	 * For k = 3, you should return: 3->2->1->4->5
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dum = new ListNode(0);
		ListNode mark = dum;
		ListNode next1 = head;
		int count = 0;
		while (head != null) {
			ListNode next = head.next;
			head.next = null;
			dum.next = head;
			head = next;
			count++;
			if (count == k) {
				count = 0;
				dum = next1;
				next1 = head;
			}
		}
		return mark.next;
	}

	/*
	 * Given a sorted array, remove the duplicates in place such that each
	 * element appear only once and return the new length.
	 * 
	 * Do not allocate extra space for another array, you must do this in place
	 * with constant memory.
	 * 
	 * For example, Given input array A = [1,1,2],
	 * 
	 * Your function should return length = 2, and A is now [1,2].
	 */
	public int removeDuplicates(int[] A) {
		int i = 0;
		int j = 1;
		while (j < A.length) {
			if (A[i] == A[j]) {
				j++;
			} else {
				A[++i] = A[j++];
			}
		}
		return i + 1;
	}

	/*
	 * Given an array and a value, remove all instances of that value in place
	 * and return the new length.
	 * 
	 * The order of elements can be changed. It doesn't matter what you leave
	 * beyond the new length.
	 */
	public int removeElement(int[] A, int elem) {
		int i = 0;
		int j = 0;
		while (j < A.length) {
			if (A[j] == elem) {
				j++;
			} else {
				A[i++] = A[j++];
			}
		}
		return i + 1;
	}

	/*
	 * Implement strStr().
	 * 
	 * Returns the index of the first occurrence of needle in haystack, or -1 if
	 * needle is not part of haystack.
	 */

	public int strStr(String haystack, String needle) {
		return -1;
	}

	/*
	 * Divide two integers without using multiplication, division and mod
	 * operator.
	 * 
	 * If it is overflow, return MAX_INT.
	 */

	public int divide(int dividend, int divisor) {
		int i = 0;
		int mark = divisor;
		while (divisor < dividend) {
			divisor <<= 1;
			i++;
		}
		i -= 1;
		double a = Math.pow(2, i);
		dividend -= (divisor / 2);
		int b = divide(dividend, mark);
		return (int) (a + b);
	}

	/*
	 * You are given a string, S, and a list of words, L, that are all of the
	 * same length. Find all starting indices of substring(s) in S that is a
	 * concatenation of each word in L exactly once and without any intervening
	 * characters.
	 * 
	 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
	 * 
	 * You should return the indices: [0,9]. (order does not matter).
	 */

	public List<Integer> findSubstring(String S, List<String> L) {
		List<Integer> result = new ArrayList<>();
		
		return result;
	}

}
