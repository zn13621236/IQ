package com.real.go.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import misc.Tree.ListNode;

public class MakeMine1 {

    /*
     * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the
     * two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container
     * contains the most water. Note: You may not slant the container.
     */
    public int maxArea (int[] height) {
        int low = 0;
        int high = height.length - 1;
        int max = 0;
        while (low < high) {
            int area = (high - low) * Math.min (height[high], height[low]);
            max = Math.max (area, max);
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return max;
    }

    /*
     * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
     */
    public String intToRoman (int num) {
        return null;
    }

    /*
     * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
     */
    public int romanToInt (String s) {
        return -1;
    }

    /*
     * Write a function to find the longest common prefix string amongst an array of strings.
     */
    public String longestCommonPrefix (String[] strs) {
        if (strs == null) {
            return null;
        }
        return longestCommon (strs, 0, strs.length - 1);
    }

    public String longestCommon (String[] strs, int start, int end) {
        if (start == end)
            return strs[start];
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        String s1 = longestCommon (strs, start, mid);
        String s2 = longestCommon (strs, mid + 1, end);
        StringBuilder sb = new StringBuilder ();
        for (int i = 0; i < Math.min (s1.length (), s2.length ()); i++) {
            if (s1.charAt (i) == s2.charAt (i)) {
                sb.append (s1.charAt (i));
            } else {
                break;
            }
        }
        return sb.toString ();
    }

    /*
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the
     * sum of zero. Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c) The solution set must not contain duplicate
     * triplets. For example, given array S = {-1 0 1 2 -1 -4}, A solution set is: (-1, 0, 1) (-1, -1, 2)
     */
    public List <List <Integer>> threeSum (int[] num) {
        Set <List <Integer>> result = new HashSet <> ();
        Arrays.sort (num);
        int len = num.length;
        for (int i = 0; i < len - 2; i++) {
            int x = i + 1;
            int y = len - 1;
            int t = 0 - num[i];
            while (x < y) {
                int newT = t - num[x];
                if (num[y] == newT) {
                    List <Integer> aList = new ArrayList <> ();
                    aList.addAll (Arrays.asList (num[i], num[x], num[y]));
                    result.add (aList);
                    x++;
                    y--;
                } else if (num[y] < newT) {
                    x++;
                    y = len - 1;
                } else {
                    y--;
                }
            }
        }
        List <List <Integer>> aList = new ArrayList <> (result);
        return aList;
    }

    public List <List <Integer>> kSum (int[] num, int index, int k, int target) {
        if (index >= num.length)
            return null;
        List <List <Integer>> result = new ArrayList <> ();
        if (k == 1 && num[index] == target) {
            List <Integer> aList = new ArrayList <> ();
            aList.add (num[index]);
            result.add (aList);
            return result;
        }
        for (int i = index; i < num.length - k + 1; i++) {
            int newTarget = target - num[i];
            List <List <Integer>> aResult = kSum (num, i + 1, k - 1, newTarget);
            if (aResult != null) {
                for (List <Integer> tList: aResult) {
                    tList.add (0, num[i]);
                    result.add (tList);
                }
            }
        }
        return result;
    }

    /*
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three
     * integers. You may assume that each input would have exactly one solution. For example, given array S = {-1 2 1 -4}, and target = 1. The sum
     * that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public int threeSumClosest (int[] num, int target) {
        return -1;
    }

    /*
     * Given a digit string, return all possible letter combinations that the number could represent. A mapping of digit to letters (just like on the
     * telephone buttons) is given below. Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public List <String> letterCombinations (String digits) {
        return null;
    }

    /*
     * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the
     * array which gives the sum of target. Note: Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d) The solution
     * set must not contain duplicate quadruplets. For example, given array S = {1 0 -1 0 -2 2}, and target = 0. A solution set is: (-1, 0, 0, 1) (-2,
     * -1, 1, 2) (-2, 0, 0, 2)
     */
    public List <List <Integer>> fourSum (int[] num, int target) {
        return null;
    }

    /*
     * Given a linked list, remove the nth node from the end of list and return its head. For example, Given linked list: 1->2->3->4->5, and n = 2.
     * After removing the second node from the end, the linked list becomes 1->2->3->5. Note: Given n will always be valid. Try to do this in one
     * pass.
     */
    public ListNode removeNthFromEnd (ListNode head, int n) {
        return null;
    }

    /*
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. The brackets must close
     * in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     */
    public boolean isValid (String s) {
        return false;
    }
}
