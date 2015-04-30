package com.real.go.one;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import misc.Tree.ListNode;

public class MakeMine0 {

    /*
     * Given an array of integers, find two numbers such that they add up to a specific target number. The function twoSum should return indices of
     * the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1
     * and index2) are not zero-based. You may assume that each input would have exactly one solution. Input: numbers={2, 7, 11, 15}, target=9 Output:
     * index1=1, index2=2
     */
    public int[] twoSum (int[] numbers, int target) {
        int[] re = new int[2];
        Map <Integer, Integer> aMap = new HashMap <> ();
        for (int i = 0; i < numbers.length; i++) {
            if (aMap.containsKey (numbers[i])) {
                re[0] = aMap.get (numbers[i]) + 1;
                re[1] = i + 1;
            } else {
                aMap.put (target - numbers[i], i);
            }
        }
        return re;
    }

    /*
     * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a
     * single digit. Add the two numbers and return it as a linked list. Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
     */
    public ListNode addTwoNumbers (ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dum = new ListNode (0);
        ListNode pre = dum;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int value = v1 + v2 + carry;
            if (value >= 10) {
                value = value % 10;
                carry = 1;
            } else {
                carry = 0;
            }
            ListNode node = new ListNode (value);
            pre.next = node;
            pre = pre.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (carry != 0) {
            ListNode node = new ListNode (carry);
            pre.next = node;
        }
        return dum.next;
    }

    /*
     * Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating
     * letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
     */
    public int lengthOfLongestSubstring (String s) {
        StringBuilder sb = new StringBuilder ();
        int result = 0;
        for (int i = 0; i < s.length (); i++) {
            char c = s.charAt (i);
            if (sb.toString ().contains (String.valueOf (c))) {
                result = Math.max (result, sb.toString ().length ());
                StringBuilder sb2 = new StringBuilder ();
                sb2.append (sb.toString ().substring (sb.toString ().indexOf (c) + 1));
                sb = sb2;
            }
            sb.append (c);
        }
        return Math.max (result, sb.toString ().length ());
    }

    /*
     * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity
     * should be O(log (m+n)).
     */
    public double findMedianSortedArrays (int A[], int B[]) {
        int m = A.length;
        int n = B.length;
        int k = m + n / 2;
        if ((m + n) % 2 == 0) {
            return (findMedianSortedArrays (A, 0, m - 1, B, 0, n - 1, k) + findMedianSortedArrays (A, 0, m - 1, B, 0, n - 1, k + 1)) / 2;
        } else {
            return findMedianSortedArrays (A, 0, m - 1, B, 0, n - 1, k);
        }
    }

    public double findMedianSortedArrays (int A[], int as, int ae, int B[], int bs, int be, int k) {
        int m = ae - as + 1;
        int n = be - bs + 1;
        if (m > n) {
            return findMedianSortedArrays (B, bs, be, A, as, ae, k);
        }
        if (m == 0) {
            return B[k - 1];
        }
        if (k == 1)
            return Math.min (A[as], B[bs]);
        int index1 = Math.min (m, k / 2);
        int index2 = k - index1;
        if (A[index1 - 1] == B[index2 - 1]) {
            return A[index1 - 1];
        }
        if (A[index1 - 1] > B[index2 - 1]) {
            return findMedianSortedArrays (A, as, ae, B, index2, be, k - index2);
        } else {
            return findMedianSortedArrays (A, index1, ae, B, bs, be, k - index1);
        }
    }

    /*
     * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique
     * longest palindromic substring.
     */
    public String longestPalindrome (String s) {
        String result = null;
        int max = 0;
        for (int i = 0; i < s.length (); i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < s.length () && s.charAt (left) == s.charAt (right)) {
                left--;
                right++;
            }
            String result2 = s.substring (left, right + 1);
            if (result2.length () > max) {
                result = result2;
                max = result.length ();
            }
        }
        return result;
    }

    // another solution O(2n) solution
    public String longestPalindrome2 (String s) {
        StringBuilder sb = new StringBuilder (s);
        sb.reverse ();
        String s2 = sb.toString ();
        int max = 0;
        String result = null;
        int i = 0;
        while (i < s.length ()) {
            int start = i;
            while (s.charAt (i) == s2.charAt (i)) {
                i++;
            }
            String s3 = s.substring (start, i + 1);
            if (s3.length () > max) {
                max = s3.length ();
                result = s3;
            }
        }
        return result;
    }

    public boolean check (String s) {
        int start = 0;
        int end = s.length () - 1;
        while (start > end) {
            if (s.charAt (start) != s.charAt (end)) {
                return false;
            }
        }
        return true;
    }

    /*
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a
     * fixed font for better legibility) P A H N A P L S I I G Y I R And then read line by line: "PAHNAPLSIIGYIR" Write the code that will take a
     * string and make this conversion given a number of rows: string convert(string text, int nRows); convert("PAYPALISHIRING", 3) should return
     * "PAHNAPLSIIGYIR".
     */
    public String convert (String s, int nRows) {
        return null;
    }

    /*
     * Reverse digits of an integer. Example1: x = 123, return 321 Example2: x = -123, return -321
     */
    public int reverse (int x) {
        int result = 0;
        while (x != 0) {
            result = 10 * result + x % 10;
            x = x / 10;
        }
        return result;
    }

    /*
     * Implement atoi to convert a string to an integer. Hint: Carefully consider all possible input cases. If you want a challenge, please do not see
     * below and ask yourself what are the possible input cases. Notes: It is intended for this problem to be specified vaguely (ie, no given input
     * specs). You are responsible to gather all the input requirements up front.
     */
    public int myAtoi (String str) {
        str = str.trim ();
        int result = 0;
        for (int i = str.length () - 1; i >= 0; i--) {
            char c = str.charAt (i);
            if (c >= '0' && c <= '9') {
                result = result * 10 + c - '0';
            }
            if (i == 0) {
                if (c == '-') {
                    result = 0 - result;
                }
            }
        }
        return result;
    }

    /*
     * Determine whether an integer is a palindrome. Do this without extra space.
     */
    public boolean isPalindrome (int x) {
        int start = x;
        int result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return start - result == 0;
    }

    /*
     * Implement regular expression matching with support for '.' and '*'. '.' Matches any single character. '*' Matches zero or more of the preceding
     * element. The matching should cover the entire input string (not partial). The function prototype should be: bool isMatch(const char *s, const
     * char *p) Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true isMatch("aaa","aa") → false isMatch("aa", "a*") → true
     * isMatch("aa", ".*") → true isMatch("ab", ".*") → true isMatch("aab", "c*a*b") → true
     */
    public boolean isMatch (String s, String p) {
        if (s.length () <= 0) {
            if (p.length () != 0 && !p.contains ("*")) {
                return false;
            }
        }
        int si = 0;
        int pi = 0;
        while (si < s.length ()) {
            if (pi + 1 < p.length () && p.charAt (pi) == '*') {
                pi += 2;
                for (int i = si; i < s.length (); i++) {
                    if (isMatch (s.substring (i), p.substring (pi))) {
                        return true;
                    }
                }
            } else {
                if (s.charAt (si) == p.charAt (pi) || p.charAt (pi) == '.') {
                    si++;
                    pi++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
