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
	
	    /*
	     * Given an array of strings, return all groups of strings that are anagrams. Note: All inputs will be in lower-case.
	     */
	    public List <String> anagrams (String[] strs) {
	        for (String s: strs) {}
	        return null;
	    }
	    
	    /*
	     * Given a string S and a string T, count the number of distinct subsequences of T in S. A subsequence of a string is a new string which is formed
	     * from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining
	     * characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not). Here is an example: S = "rabbbit", T = "rabbit" Return 3.
	     */
//	     public int numDistinct(String S, String T) {
//	    
//	     }
	    
	    /*
	     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence. For example, Given [100, 4, 200, 1, 3, 2],
	     * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4. Your algorithm should run in O(n) complexity.
	     */
	    // public int longestConsecutive(int[] num) {
	    //
	    // }
	    
	    /*
	     * Given a string s, partition s such that every substring of the partition is a palindrome. Return the minimum cuts needed for a palindrome
	     * partitioning of s. For example, given s = "aab", Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
	     */
//	     public int minCut(String s) {
//	         
//	     } 
	    
	    
	    /*
	     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i]. You have a car with an unlimited gas tank and
	     * it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas
	     * stations. Return the starting gas station's index if you can travel around the circuit once, otherwise return -1. Note: The solution is
	     * guaranteed to be unique.
	     */
	    // public int canCompleteCircuit(int[] gas, int[] cost) {
	    //
	    //
	    //
	    //
	    // }
	    
}
