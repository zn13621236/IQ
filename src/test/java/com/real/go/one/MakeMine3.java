package com.real.go.one;

import java.util.List;
import misc.Tree.ListNode;


public class MakeMine3 {
	
	/*
	 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 ��� 1,3,2
3,2,1 ��� 1,2,3
1,1,5 ��� 1,5,1
	 */
	  public void nextPermutation(int[] num) {
	        int start=num.length-1;
	        while(start-1>=0&&num[start-1]<num[start]){
	        	break;
	        }
		   reverse(num,start,num.length-1);
		  //swap
		   int tmp=num[start-1];
		   num[start-1]=num[start];
		   num[start]=tmp;
	    }
	  
	  private void reverse(int[] num, int start, int end) {
		   while(start<end){
			   int tmp=num[start];
			   num[start]=num[end];
			   num[end]=tmp;
			   start++;
			   end--;
		   }
	}

	/*
	   * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
	   */
	 
	  public int longestValidParentheses(String s) {
	        return -1;
	    }
	  
	  
	  /*
	   * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
	   */
	 
	  public int search(int[] A, int target) {
	        return -1;
	    }
	  
	  /*
	   * Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
	   */
	  
	  
	  public int[] searchRange(int[] A, int target) {
		  return null;
	  }
	  
	  
	  
	  /*
	   * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 ��� 2
[1,3,5,6], 2 ��� 1
[1,3,5,6], 7 ��� 4
[1,3,5,6], 0 ��� 0
	   */
	  public int searchInsert(int[] A, int target) {
	    return -1;
	} 
	  
	  
	  
	  /*
	   * Valid Sudoku 
	   */
	  
	  public boolean isValidSudoku(char[][] board) {
	        return false;
	    }
	  
	  
	  /*
	   * Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
	   */
	  
	  
	  public void solveSudoku(char[][] board) {
	        
	    }
	  
	  
	  /*
	   * The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
	   */

	  public String countAndSay(int n) {
		  return null;
	  }

/*
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, ��� , ak) must be in non-descending order. (ie, a1 ��� a2 ��� ��� ��� ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
 */

	  public List<List<Integer>> combinationSum(int[] candidates, int target) {
	        return null;
	    }
	  
	  
	  /*
	   * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, ��� , ak) must be in non-descending order. (ie, a1 ��� a2 ��� ��� ��� ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
	   */
	  public List<List<Integer>> combinationSum2(int[] num, int target) {
	      return null;  
	    }  
	  
}
