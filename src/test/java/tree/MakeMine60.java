package tree;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MakeMine60 {

    /*
     * Given a list, rotate the list to the right by k places, where k is non-negative. For example: Given 1->2->3->4->5->NULL and k = 2, return
     * 4->5->1->2->3->NULL.
     */
    public ListNode rotateRight (ListNode head, int k) {
        ListNode cur = head;
        ListNode fast = head;
        while (k >= 0) {
            fast = fast.next;
            k--;
        }
        while (fast.next != null) {
            cur = cur.next;
            fast = fast.next;
        }
        ListNode re = cur.next;
        cur.next = null;
        fast.next = head;
        return re;
    }

    public class ListNode {

        int      val;
        ListNode next;

        ListNode (int x) {
            val = x;
            next = null;
        }
    }

    /*
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below). The robot can only move either down or right
     * at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below). How many
     * possible unique paths are there?
     */
    public int uniquePaths (int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * Follow up for "Unique Paths": Now consider if some obstacles are added to the grids. How many unique paths would there be? An obstacle and
     * empty space is marked as 1 and 0 respectively in the grid. For example, There is one obstacle in the middle of a 3x3 grid as illustrated below.
     * [ [0,0,0], [0,1,0], [0,0,0] ] The total number of unique paths is 2. Note: m and n will be at most 100.
     */
    public int uniquePathsWithObstacles (int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            } else {
                dp[0][i] = 1;
            }
        }
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    /*
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its
     * path. Note: You can only move either down or right at any point in time.
     */
    public int minPathSum (int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min (dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /*
     * Validate if a given string is numeric. Some examples: "0" => true " 0.1 " => true "abc" => false "1 a" => false "2e10" => true Note: It is
     * intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
     */
    // public boolean isNumber(String s) {
    //
    // }
    /*
     * Given a non-negative number represented as an array of digits, plus one to the number. The digits are stored such that the most significant
     * digit is at the head of the list.
     */
    public int[] plusOne (int[] digits) {
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int val = 0;
            if (i == digits.length - 1) {
                val = digits[i] + 1 + carry;
            } else {
                if (carry == 0)
                    return digits;
                else {
                    val = digits[i] + carry;
                }
            }
            if (val > 9) {
                carry = 1;
                val = val % 10;
            } else {
                carry = 0;
            }
            digits[i] = val;
        }
        return digits;
    }

    @Test
    public void testasldfalsdkn () {
        System.out.println (addBinary ("11", "1"));
    }

    /*
     * Given two binary strings, return their sum (also a binary string). For example, a = "11" b = "1" Return "100".
     */
    public String addBinary (String a, String b) {
        int carry = 0;
        int ai = a.length () - 1;
        int bi = b.length () - 1;
        StringBuilder sb = new StringBuilder ();
        while (ai >= 0 || bi >= 0) {
            int bv = bi >= 0 ? Integer.valueOf (b.charAt (bi) - '0') : 0;
            int av = ai >= 0 ? Integer.valueOf (a.charAt (ai) - '0') : 0;
            int val = av + bv + carry;
            if (val > 1) {
                carry = val - 1;
                val = val % 2;
            }
            sb.insert (0, val);
            ai--;
            bi--;
        }
        while (carry > 0) {
            sb.insert (0, carry % 2);
            carry--;
        }
        return sb.toString ();
    }

    /*
     * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
     * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so
     * that each line has exactly L characters. Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a
     * line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right. For the last
     * line of text, it should be left justified and no extra space is inserted between words. For example, words: ["This", "is", "an", "example",
     * "of", "text", "justification."] L: 16. Return the formatted lines as: [ "This    is    an", "example  of text", "justification.  " ] Note: Each
     * word is guaranteed not to exceed L in length.
     */
     public List<String> fullJustify(String[] words, int L) {
         List<String> result=new ArrayList<>();
         StringBuilder sb= new StringBuilder();
         for(String s:words){             
             if(sb.length ()+s.length()+1<=L){
                 sb.append (" ").append (s);
             }else{
                 String tba=transform(sb.toString (),L);
                 result.add (tba);
                 sb= new StringBuilder();
             }
         }
         if(sb.length()!=0){
             while(sb.length ()!=L){
                 sb.append (" ");
             }
         }
         result.add(sb.toString ());
         return result;
     }
    private String transform (String s,int L) {
        int re=16-s.length ();
        String[] ss=s.split (" ");
        int avg=re/(ss.length-1);
        int remain=re%(ss.length-1);        
        StringBuilder sb= new StringBuilder();
        for(String a:ss){
            sb.append (a);
            for(int i=0;i<avg;i++){
                sb.append (" ");
            }
            if(remain!=0){
                remain--;
                sb.append(" ");
            }
        }
        return sb.toString ();
    }

    /*
     * Implement int sqrt(int x). Compute and return the square root of x.
     */
     public int sqrt(int x) {
    
         long start=0;
         long end=x/2+1;
         while(start<=end){
             long mid=start+(end-start)/2;
             if(mid*mid<x){
                 start=mid+1;
             }else if(mid*mid>x){
                 end=mid-1;
             }else{
                 return (int)mid;
             }
         }
         
         return (int)end;
     }
    /*
     * You are climbing a stair case. It takes n steps to reach to the top. Each time you can either climb 1 or 2 steps. In how many distinct ways can
     * you climb to the top?
     */
    public int climbStairs (int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 3; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
    
    
    public int climbStairs2 (int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
       return climbStairs2(n-1)+climbStairs2(n-2);
    }
}
