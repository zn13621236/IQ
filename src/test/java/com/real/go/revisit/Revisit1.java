package com.real.go.revisit;

import java.util.ArrayList;
import java.util.List;

import com.real.go.one.MakeMine8.ListNode;
import com.real.go.one.MakeMine9.TreeNode;

public class Revisit1 {
	
	/*
	 * Suppose a sorted array is rotated at some pivot unknown to you
	 * beforehand.
	 * 
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * 
	 * You are given a target value to search. If found in the array return its
	 * index, otherwise return -1.
	 * 
	 * You may assume no duplicate exists in the array.
	 */

	public int search(int[] A, int target) {
		int start = 0;
		int end = A.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (A[mid] == target)
				return mid;
			if (A[start] < A[mid]) {
				if (A[mid] > target && A[start] < target) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			} else {
				if (A[mid] < target && A[end] >= target) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
		}
		return -1;
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
	
	/*
	 * Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
	 */
	 public int firstMissingPositive(List<Integer> nums) {
	        return -1;
	    }
	
	
}
