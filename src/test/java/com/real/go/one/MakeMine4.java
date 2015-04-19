package com.real.go.one;

import java.util.List;
import misc.Tree.ListNode;

public class MakeMine4 {

	/*
	 * Given an unsorted integer array, find the first missing positive integer.
	 * 
	 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
	 * 
	 * Your algorithm should run in O(n) time and uses constant space.
	 */
	public int firstMissingPositive(List<Integer> nums) {
		int i = 0;

		while (i < nums.size()) {
			if (nums.get(i) != i + 1) {
				int tmp = nums.get(nums.get(i) - 1);
				nums.set(nums.get(i) - 1, tmp);
				
			}
            
		}

		return -1;
	}

	/*
	 * Given n non-negative integers representing an elevation map where the
	 * width of each bar is 1, compute how much water it is able to trap after
	 * raining.
	 * 
	 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	 */

	public int trap(int[] A) {
		int max = 0;
		int[] C = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			C[i] = Math.max(max, A[i]);
			max = C[i];
		}
		int[] B = new int[A.length];
		for (int i = A.length - 1; i >= 0; i--) {
			B[i] = Math.max(max, A[i]);
			max = B[i];
		}
		int result = 0;
		for (int i = 0; i < A.length; i++) {
			result += Math.min(B[i], C[i]) - A[i];

		}
		return result;
	}

	/*
	 * Given two numbers represented as strings, return multiplication of the
	 * numbers as a string.
	 * 
	 * Note: The numbers can be arbitrarily large and are non-negative.
	 */

	public String multiply(String num1, String num2) {
		return null;
	}

	/*
	 * Implement wildcard pattern matching with support for '?' and '*'.
	 * 
	 * '?' Matches any single character. '*' Matches any sequence of characters
	 * (including the empty sequence).
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * The function prototype should be: bool isMatch(const char *s, const char
	 * *p)
	 * 
	 * Some examples: isMatch("aa","a") ��� false isMatch("aa","aa") ��� true
	 * isMatch("aaa","aa") ��� false isMatch("aa", "*") ��� true isMatch("aa",
	 * "a*") ��� true isMatch("ab", "?*") ��� true isMatch("aab", "c*a*b") ���
	 * false
	 */
	public boolean isMatch(String s, String p) {
		return false;
	}

	/*
	 * Given an array of non-negative integers, you are initially positioned at
	 * the first index of the array.
	 * 
	 * Each element in the array represents your maximum jump length at that
	 * position.
	 * 
	 * Your goal is to reach the last index in the minimum number of jumps.
	 * 
	 * For example: Given array A = [2,3,1,1,4]
	 * 
	 * The minimum number of jumps to reach the last index is 2. (Jump 1 step
	 * from index 0 to 1, then 3 steps to the last index.)
	 */

	public int jump(List<Integer> nums) {
		return -1;
	}

	/*
	 * Given a collection of numbers, return all possible permutations.
	 * 
	 * For example, [1,2,3] have the following permutations: [1,2,3], [1,3,2],
	 * [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	 */

	public List<List<Integer>> permute(int[] num) {
		return null;
	}

	/*
	 * Given a collection of numbers that might contain duplicates, return all
	 * possible unique permutations.
	 * 
	 * For example, [1,1,2] have the following unique permutations: [1,1,2],
	 * [1,2,1], and [2,1,1].
	 */

	public List<List<Integer>> permuteUnique(int[] num) {
		return null;
	}

	/*
	 * You are given an n x n 2D matrix representing an image.
	 * 
	 * Rotate the image by 90 degrees (clockwise).
	 * 
	 * Follow up: Could you do this in-place?
	 */

	public void rotate(int[][] matrix) {

	}

	/*
	 * Given an array of strings, return all groups of strings that are
	 * anagrams.
	 * 
	 * Note: All inputs will be in lower-case.
	 */

	public List<String> anagrams(String[] strs) {
		return null;
	}

	/*
	 * Implement pow(x, n).
	 */

	public double pow(double x, int n) {
		return 0.0;
	}
}
