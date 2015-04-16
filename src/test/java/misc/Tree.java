package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import org.junit.Test;

public class Tree {

    // Given a binary tree, return the postorder traversal of its nodes' values.
    //
    // For example:
    // Given binary tree {1,#,2,3},
    // 1
    // \
    // 2
    // /
    // 3
    // return [3,2,1].
    //
    // Note: Recursive solution is trivial, could you do it iteratively?
    /**
     * Definition for binary tree public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
     */
    @Test
    public void test1 () {
        TreeNode a = new TreeNode (0);
        TreeNode a1 = new TreeNode (1);
        TreeNode a2 = new TreeNode (1);
        a.left = a1;
        a.right = a2;
        pathSum (a, -1);
    }

    public List <List <Integer>> pathSum (TreeNode root, int sum) {
        List <Integer> path = new ArrayList <> ();
        List <List <Integer>> result = new ArrayList <> ();
        traverse (root, sum, 0, path, result);
        return result;
    }

    public void traverse (TreeNode root, int sum, int total, List <Integer> path, List <List <Integer>> result) {
        if (root == null)
            return;
        path.add (root.val);
        total = total + root.val;
        if (total == sum && root.left == null && root.right == null) {
            List <Integer> a = new ArrayList <Integer> ();
            a.addAll (path);
            result.add (a);
        }
        traverse (root.left, sum, total, path, result);
        traverse (root.right, sum, total, path, result);
    }

    // based on level order traversal
    public void connect (TreeNode root) {
        TreeNode head = null; // head of the next level
        TreeNode prev = null; // the leading node on the next level
        TreeNode cur = root; // current node of current level
        while (cur != null) {
            while (cur != null) { // iterate on the current level
                // left child
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                // right child
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                // move to next node
                cur = cur.next;
            }
            // move to next level
            cur = head;
            head = null;
            prev = null;
        }
    }

    public List <Integer> postorderTraversal (TreeNode root) {
        List <Integer> aList = new ArrayList <Integer> ();
        postOrder (root, aList);
        return aList;
    }

    void postOrder (TreeNode root, List <Integer> aList) {
        if (root == null)
            return;
        postOrder (root.left, aList);
        postOrder (root.right, aList);
        aList.add (root.val);
    }

    public List <Integer> preorderTraversal (TreeNode root) {
        List <Integer> aList = new ArrayList <Integer> ();
        postOrder (root, aList);
        return aList;
    }

    void preOrder (TreeNode root, List <Integer> aList) {
        if (root == null)
            return;
        aList.add (root.val);
        postOrder (root.left, aList);
        postOrder (root.right, aList);
    }

    public List <Integer> inorderTraversal (TreeNode root) {
        List <Integer> aList = new ArrayList <Integer> ();
        inOrder (root, aList);
        return aList;
    }

    void inOrder (TreeNode root, List <Integer> aList) {
        if (root == null)
            return;
        postOrder (root.left, aList);
        aList.add (root.val);
        postOrder (root.right, aList);
    }

    public static class TreeNode {

        int      val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode (int x) {
            val = x;
        }
    }

    public String find (String s) {
        if (s == null)
            return null;
        if (s.length () == 1)
            return s;
        String result = null;
        char[] c = s.toCharArray ();
        for (int start = 0; start < c.length - 1; start++) {
            for (int e = c.length - 1; e >= start + 1; e--) {
                if (result != null && result.length () > e - start + 1) {
                    break;
                }
                if (check (c, start, e)) {
                    if (result == null) {
                        result = s.substring (start, e + 1);
                    }
                    if (e - start + 1 > result.length ()) {
                        result = s.substring (start, e + 1);
                    }
                }
            }
        }
        return result;
    }

    private boolean check (char[] c, int s, int e) {
        while (s < e) {
            if (c[s] == c[e]) {
                s++;
                e--;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void try1 () {
        int[] a = { 4, 5, 2, 6, 3, 1 };
        int[] b = { 4, 2, 5, 1, 3, 6 };
        buildTree (b, a);
    }
    // public TreeNode buildTree (int[] inorder, int[] postorder) {
    // return build (inorder, postorder, inorder.length - 1, 0, inorder.length - 1);
    // }
    int pInorder;  // index of inorder array
    int pPostorder; // index of postorder array

    private TreeNode buildTree (int[] inorder, int[] postorder, TreeNode end) {
        System.out.println ("pInorder: =" + pInorder);
        System.out.println ("pPostorder: =" + pPostorder);
        if (pPostorder < 0) {
            return null;
        }
        // create root node
        TreeNode n = new TreeNode (postorder[pPostorder--]);
        // if right node exist, create right subtree
        if (inorder[pInorder] != n.val) {
            n.right = buildTree (inorder, postorder, n);
        }
        pInorder--;
        // if left node exist, create left subtree
        if ((end == null) || (inorder[pInorder] != end.val)) {
            n.left = buildTree (inorder, postorder, end);
        }
        return n;
    }

    public TreeNode buildTree (int[] inorder, int[] postorder) {
        pInorder = inorder.length - 1;
        pPostorder = postorder.length - 1;
        return buildTree (inorder, postorder, null);
    }

    private TreeNode build (int[] inorder, int[] postorder, int postIndex, int inStart, int inEnd) {
        if (postIndex < 0 || inStart > inEnd)
            return null;
        TreeNode root = new TreeNode (postorder[postIndex]);
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = build (inorder, postorder, inIndex - 1, inStart, inIndex - 1);
        root.right = build (inorder, postorder, postIndex - 1, inIndex + 1, inEnd);
        return root;
    }

    public int longestValidParentheses (String s) {
        if (s == null || s.length () < 2)
            return 0;
        char[] c = s.toCharArray ();
        int max = 0;
        for (int i = 0; i < c.length - 1; i++) {
            int j = i;
            int lastCount0 = 0;
            int count = 0;
            if (i + max > c.length) {
                break;
            }
            while (j < c.length) {
                if (s.charAt (j) == '(') {
                    count++;
                } else {
                    count--;
                }
                if (c.length / 2 + 1 < count) {
                    break;
                }
                if (count == 0) {
                    lastCount0 = j;
                    max = Math.max (max, s.substring (i, j).length ());
                }
                if (count < 0) {
                    max = Math.max (max, s.substring (i, j).length ());
                    break;
                }
                j++;
            }
            if (count > 0 && lastCount0 > i + 1) {
                max = Math.max (max, s.substring (i, lastCount0 + 1).length ());
            }
        }
        return max;
    }

    private Integer largest (int[] height) {
        if (height == null || height.length == 0)
            return 0;
        Stack <Integer> s = new Stack <> ();
        s.push (-1);
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            while (s.peek () > -1 && s.peek () > height[i]) {
                int top = s.pop ();
                max = Math.max (max, height[top] * (i - 1 - s.peek ()));
            }
            s.push (i);
        }
        while (s.peek () != -1) {
            int top = s.pop ();
            max = Math.max (max, height[top] * (height.length - 1 - s.peek ()));
        }
        return max;
    }

    @Test
    public void try2 () {
        int[] a = { 4, 5, 6, 1, 2 };
        largestRectangleArea (a);
    }

    public int largestRectangleArea (int[] height) {
        if (height == null || height.length == 0)
            return 0;
        Stack <Integer> index = new Stack <Integer> ();
        index.push (-1);
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            // Start calculate the max value
            while (index.peek () > -1) {
                if (height[index.peek ()] > height[i]) {
                    int top = index.pop ();
                    max = Math.max (max, height[top] * (i - 1 - index.peek ()));
                } else
                    break;
            }
            index.push (i);
        }
        while (index.peek () != -1) {
            int top = index.pop ();
            int count = height.length - 1 - index.peek ();
            max = Math.max (max, height[top] * (count));
        }
        return max;
    }

    public int canCompleteCircuit (int[] gas, int[] cost) {
        int previous = 0;
        int index = 0;
        int restGas = 0;
        for (int i = 0; i < gas.length; ++i) {
            restGas += gas[i] - cost[i];
            if (restGas < 0) {
                previous += restGas;
                restGas = 0;
                index = i + 1;
            }
        }
        if (restGas + previous >= 0) {
            return index;
        } else {
            return -1;
        }
    }

    public String multiply (String num1, String num2) {
        long a = Long.valueOf (num1);
        long b = Long.valueOf (num2);
        a = a * b;
        return longToString (a);
    }

    public String longToString (long num) {
        if (num == 0)
            return "0";
        String result = "";
        while (num != 0) {
            long a = num % 10;
            result = String.valueOf (a) + result;
            num = num / 10;
        }
        return result;
    }

    @Test
    public void testTry () {
        int[] num = { 2, 1 };
        System.out.println (findMin (num));
        Map <String, String> aMap = new HashMap <> ();
    }

    public List <String> dosome (String digits) {
        Map <String, String> aMap = new HashMap <> ();
        aMap.put ("2", "abc");
        aMap.put ("3", "def");
        aMap.put ("4", "ghi");
        aMap.put ("5", "jkl");
        aMap.put ("6", "mno");
        aMap.put ("7", "pqrs");
        aMap.put ("8", "tuv");
        aMap.put ("9", "wxyz");
        List <String> result = new ArrayList <> ();
        if (digits == null)
            return result;
        if (digits.length () == 0) {
            result.add ("");
            return result;
        }
        for (char d: digits.toCharArray ()) {
            if (aMap.containsKey (String.valueOf (d))) {
                String letter = aMap.get (String.valueOf (d));
                List <String> newResult = new ArrayList <> ();
                for (char a: letter.toCharArray ()) {
                    String st = String.valueOf (a);
                    for (String s: result) {
                        st = st + s;
                    }
                    newResult.add (st);
                }
                result = newResult;
            }
        }
        return result;
    }

    public int findMin (int[] num) {
        if (num == null)
            return -1;
        int start = 0;
        int end = num.length - 1;
        int min = Integer.MAX_VALUE;
        while (start < end) {
            int mid = (start + end) / 2;
            if (num[start] > num[mid]) {
                // go left
                min = Math.min (min, num[mid]);
                end = mid - 1;
            } else {
                min = Math.min (min, num[start]);
                start = mid + 1;
            }
        }
        return min;
    }

    public void comSum (int[] a, int target) {}

    public int trap (int[] A) {
        Stack <Integer> s = new Stack <> ();
        boolean low = false;
        boolean calculate = false;
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (s.isEmpty ()) {
                s.push (A[i]);
                continue;
            }
            if ((s.peek () > A[i] && !low) || (s.peek () < A[i] && low)) {
                s.push (A[i]);
                calculate = true;
            } else if ((s.peek () < A[i] && !low && calculate)) {
                s.push (A[i]);
                low = true;
            } else if (s.peek () < A[i] && !low && !calculate) {
                s.pop ();
                s.push (A[i]);
            } else {// do calculate then restart
                int cur = doCalculate (s);
                result += cur;
                calculate = false;
                low = false;
            }
        }
        return result;
    }

    private int doCalculate (Stack <Integer> s) {
        int first = s.pop ();
        int last = first;
        int difSum = 0;
        int count = 0;
        while (!s.isEmpty ()) {
            count++;
            last = s.pop ();
            int dif = first - last;
            if (dif > 0) {
                difSum = difSum + dif;
            } else {
                break;
            }
        }
        if (last < first) {
            difSum = difSum - (first - last) * count;
        }
        return difSum;
    }

    @Test
    public void testTrap () {
        int[] A = { 2, 0, 2 };
        trap (A);
    }

    public boolean isInterleave (String s1, String s2, String s3) {
        boolean[][] ma = new boolean[s1.length () + 1][s2.length () + 1];
        ma[0][0] = true;
        for (int i = 1; i < ma.length; i++) {
            if (ma[i - 1][0] == true && s1.charAt (i) == s3.charAt (i)) {
                ma[i][0] = true;
            }
        }
        for (int j = 1; j < ma[0].length; j++) {
            if (ma[0][j - 1] == true && s2.charAt (j) == s3.charAt (j)) {
                ma[0][j] = true;
            }
        }
        for (int i = 1; i < ma.length; i++) {
            for (int j = 0; j < ma[0].length; j++) {
                if (ma[i - 1][j] == true && s1.charAt (i) == s3.charAt (i + j)) {
                    ma[i][j] = true;
                }
                if (ma[i][j - 1] == true && s2.charAt (j) == s3.charAt (i + j)) {
                    ma[i][j] = true;
                }
            }
        }
        return ma[ma.length][ma[0].length];
    }

    public class Interval {

        int start;
        int end;

        Interval () {
            start = 0;
            end = 0;
        }

        Interval (int s, int e) {
            start = s;
            end = e;
        }
    }

    public List <Interval> insert (List <Interval> intervals, Interval newInterval) {
        List <Interval> result = new ArrayList <> ();
        if (intervals == null || intervals.size () == 0) {
            result.add (newInterval);
            return result;
        }
        int start = 0;
        int end = 0;
        for (Interval i: intervals) {
            if (newInterval.end >= i.start) {
                end++;
            }
            if (newInterval.start > i.end)
                start++;
        }
        if (start == end) {
            result.addAll (intervals);
            result.add (start, newInterval);
            return result;
        }
        for (int i = 0; i < start; i++) {
            result.add (intervals.get (i));
        }
        result.add (new Interval (Math.min (intervals.get (start).start, newInterval.start), Math.max (intervals.get (end - 1).end, newInterval.end)));
        for (int i = end; i < intervals.size (); i++) {
            result.add (intervals.get (i));
        }
        return result;
    }

    public List <Interval> merge (List <Interval> intervals) {
        Collections.sort (intervals, new Comparator <Interval> () {// need to know asc or desc

                    public int compare (Interval a, Interval b) {
                        return a.start > b.start ? 1 : (a.start == b.start ? 0 : -1);
                    }
                });
        List <Interval> result = new ArrayList <> ();
        int i = 0;
        while (i < intervals.size () - 1) {
            int start = intervals.get (i).start;
            int end = intervals.get (i).end;
            while (intervals.get (i).end >= intervals.get (i + 1).start && i < intervals.size () - 1) {
                i++;
                end = Math.max (end, intervals.get (i + 1).end);
            }
            result.add (new Interval (start, end));
            i++;
        }
        return result;
    }

    public boolean isScramble (String s1, String s2) {
        if (s1.length () != s2.length ())
            return false;
        if (s1.length () == 1 && s2.length () == 1 && s1.charAt (0) == s2.charAt (0)) {
            return true;
        }
        char[] c1 = s1.toCharArray ();
        char[] c2 = s2.toCharArray ();
        Arrays.sort (c1);
        Arrays.sort (c2);
        if (!new String (c1).equals (new String (c2))) {
            return false;
        }
        for (int i = 1; i < s1.length (); i++) {
            String s11 = s1.substring (0, i);
            String s12 = s1.substring (i, s1.length ());
            String s21 = s2.substring (0, i);
            String s22 = s2.substring (i, s1.length ());
            if (isScramble (s11, s21) && isScramble (s12, s22)) {
                return true;
            }
            s21 = s2.substring (s2.length () - i, s2.length ());
            s22 = s2.substring (0, s2.length () - i);
            if (isScramble (s11, s21) && isScramble (s12, s22)) {
                return true;
            }
        }
        return false;
    }

    public String largestNumber (int[] num) {
        int[] buf = new int[10];
        for (int i: num) {
            String a = String.valueOf (i);
            char[] cArray = a.toCharArray ();
            for (char c: cArray) {
                int dif = c - '0';
                buf[dif] = buf[dif] + 1;
            }
        }
        StringBuilder sb = new StringBuilder ();
        for (int j = buf.length - 1; j >= 0; j--) {
            int repeat = buf[j];
            while (repeat > 0) {
                sb.append (j);
                repeat--;
            }
        }
        return sb.toString ();
    }

    @Test ()
    public void testGo () {
        System.out.println (Math.pow (3, 3));
    }

    public String largestNumber2 (int[] num) {
        quickSort (0, num.length - 1, num);
        StringBuilder sb = new StringBuilder ();
        for (int i: num) {
            sb.append (i);
        }
        return sb.toString ();
    }

    void quickSort (int start, int end, int[] num) {
        if (start <= end)
            return;
        int ben = start;
        int left = start + 1;
        int right = end;
        while (left < right) {
            while (compare (num[right], num[ben])) {
                right--;
            }
            while (compare (num[ben], num[left])) {
                left++;
            }
            // swap
            int temp = num[left];
            num[left] = num[right];
            num[right] = temp;
        }
        int temp = num[ben];
        num[ben] = num[right];
        num[right] = temp;
        quickSort (start, right - 1, num);
        quickSort (right + 1, end, num);
    }

    public boolean compare (int a, int b) {
        String m = String.valueOf (a);
        String n = String.valueOf (b);
        int length = Math.min (m.length (), n.length ());
        for (int i = 0; i < length; i++) {
            int re = m.charAt (i) - n.charAt (i);
            if (re > 0)
                return true;
            if (re < 0)
                return false;
        }
        String result = m.length () > n.length () ? m : n;
        for (int i = length; i < result.length (); i++) {
            int re = result.charAt (i) - result.charAt (i - 1);
            if (re > 0)
                return true;
            if (re < 0)
                return false;
        }
        return false;
    }

    public String largestNumber1 (int[] num) {
        if (num == null || num.length == 0) {
            return "";
        }
        String[] str = new String[num.length];
        for (int i = 0; i < str.length; i++) {
            str[i] = String.valueOf (num[i]);
        }
        Arrays.sort (str, new Comparator <String> () {

            @Override
            public int compare (String s, String t) {
                int i = 0;
                while (i < s.length () && i < t.length ()) {
                    if (s.charAt (i) < t.charAt (i)) {
                        return -1;
                    } else if (s.charAt (i) > t.charAt (i)) {
                        return 1;
                    } else {
                        i++;
                    }
                }
                if (i == s.length () && i == t.length ()) {
                    return 0;
                } else if (i == s.length ()) {
                    // s is shorter
                    return compare (s, t.substring (i));
                } else {
                    // t is shorter
                    return compare (s.substring (i), t);
                }
            }
        });
        if (str[str.length - 1].equals ("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder ();
        for (int i = str.length - 1; i >= 0; i--) {
            sb.append (str[i]);
        }
        return str.toString ();
    }

    //
    // public BSTIterator(TreeNode root) {
    //
    // }
    //
    // /** @return whether we have a next smallest number */
    // public boolean hasNext() {
    //
    // }
    //
    // /** @return the next smallest number */
    // public int next() {
    //
    // }
    @Test
    public void testGoasdf () {
        String[] words = { "a", "b", "c", "d", "e" };
        List <String> result = fullJustify (words, 1);
        for (String s: result) {
            System.out.println (s);
        }
    }

    public int maximumGap (int[] num) {
        if (num.length < 2)
            return 0;
        Map <Integer, Integer> map = new TreeMap <Integer, Integer> ();
        for (int i: num) {
            if (map.containsKey (i))
                continue;
            map.put (i, 1);
        }
        int pre = Integer.MAX_VALUE;
        int max = 0;
        for (int i: map.keySet ()) {
            max = Math.max (max, i - pre);
            pre = i;
        }
        return max;
    }

    @Test
    public void goTest () {
        Map <Integer, String> map = new TreeMap <Integer, String> ();
        // Add Items to the TreeMap
        map.put (new Integer (1), "One");
        map.put (new Integer (3), "Three");
        map.put (new Integer (2), "Two");
        // Iterate over them
        for (Map.Entry <Integer, String> entry: map.entrySet ()) {
            System.out.println (entry.getKey () + " => " + entry.getValue ());
        }
    }

    public static class ListNode {

        public int      val;
        public ListNode next;

        public ListNode (int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode (ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        int countA = 0;
        int countB = 0;
        ListNode a = headA;
        ListNode b = headB;
        while (a.next != null) {
            countA++;
            a = a.next;
        }
        while (b.next != null) {
            countB++;
            b = b.next;
        }
        a = headA;
        b = headB;
        if (countA > countB) {
            int dif = countA - countB;
            while (dif != 0) {
                a = a.next;
                dif--;
            }
        } else {
            int dif = countB - countA;
            while (dif != 0) {
                b = b.next;
                dif--;
            }
        }
        while (a != null && a.val != b.val) {
            a = a.next;
            b = b.next;
        }
        return a;
    }

    public String getPermutation (int n, int k) {
        int[] nums = new int[1000];
        int pCount = 1;
        for (int i = 0; i < n; ++i) {
            nums[i] = i + 1;
            pCount *= (i + 1);
        }
        k--;
        String res = "";
        for (int i = 0; i < n; i++) {
            pCount = pCount / (n - i);
            int selected = k / pCount;
            res += ('0' + nums[selected]);
            for (int j = selected; j < n - i - 1; j++)
                nums[j] = nums[j + 1];
            k = k % pCount;
        }
        return res;
    }

    /**
     * 那么有没有办法优化呢？ 这个我们就得使用位运算。我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合，即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。基于以上这个公式以及左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基。
     * 然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止。因为这个方法的迭代次数是按2的幂知道超过结果，所以时间复杂度为O(logn)。
     * 思路是把被除数最高位找出来，再把除数按照他们俩个数最高位的差移位，如果小于被除数，就用被除数减之，再让除数少移一位，继续比较大小，以此类推，每当小于等于被除数时，结果就加上1<<移位量 的数，大于时跳过
     * 基本思想是用一个hashcode来表示一个字符串，为了保证hash的唯一性，我们用比字符集大的素数为底
     * ，以这个素数的幂为基。举例来说，字符集是小写字母集，取素数29为底。比如字符串“abacd",转化为hashcode=1+2*29+1*29^2+3*29^3+4*29^4。然后是如何在前进一步的时候计算新的hashcode
     * ，比如匹配串是原串是”abacde“，匹配串长度为5，根据以上的方法计算
     * ”abacd“的hashcode=h，那么下一步”bacde“的hashcode=h/29+5*29^4。这是一个constant的操作，所以检测所有子串的时间复杂度只需要O(m+n-m)=O(n)，也是一个线性算法。代码如下：
     * 
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide2 (int dividend, int divisor) {
        if (divisor == 0)
            return Integer.MAX_VALUE;
        int res = 0;
        if (dividend == Integer.MIN_VALUE) {
            res = 1;
            dividend += Math.abs (divisor);
        }
        if (divisor == Integer.MIN_VALUE)
            return res;
        boolean isNeg = ((dividend ^ divisor) >>> 31 == 1) ? true : false;
        dividend = Math.abs (dividend);
        divisor = Math.abs (divisor);
        int digit = 0;
        while (divisor <= (dividend >> 1)) {
            divisor <<= 1;
            digit++;
        }
        while (digit >= 0) {
            if (dividend >= divisor) {
                dividend -= divisor;
                res += 1 << digit;
            }
            divisor >>= 1;
            digit--;
        }
        return isNeg ? -res : res;
    }

    public int candy2 (int[] ratings) {
        if (ratings == null || ratings.length == 0)
            return 0;
        int totalCnt = 1;
        int preCandyCnt = 1; // previous child's candy number
        int i = 1;
        while (i < ratings.length) {
            if (ratings[i] == ratings[i - 1]) {
                totalCnt += 1;
                preCandyCnt = 1;
                i++;
            } else if (ratings[i] > ratings[i - 1]) {
                totalCnt += preCandyCnt + 1;
                preCandyCnt = preCandyCnt + 1;
                i++;
            } else {
                // desent length of Array from i-1, lenth is j - (i-1)
                int j = i;
                while (j < ratings.length && ratings[j] < ratings[j - 1])
                    j++;
                int lenDesc = j - (i - 1);
                if (preCandyCnt < lenDesc)
                    totalCnt += lenDesc - preCandyCnt;
                totalCnt += (lenDesc - 1 + 1) * (lenDesc - 1) / 2;
                preCandyCnt = 1;
                i = j;
            }
        }
        return totalCnt;
    }

    public int candy123 (int[] ratings) {
        int[] dp = new int[ratings.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            if (ratings[i] == ratings[i - 1]) {
                dp[i] = dp[i - 1];
            }
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && dp[i] <= dp[i + 1]) {
                dp[i] = dp[i + 1] + 1;
            }
        }
        int result = 0;
        for (int i: dp) {
            result += i;
        }
        return result;
    }

    public static class Point {

        int x;
        int y;

        Point (int a, int b) {
            x = a;
            y = b;
        }
    }

    public int maxPoints (Point[] points) {
        if (points == null)
            return 0;
        if (points.length <= 2)
            return points.length;
        Map <Double, Integer> aMap = new HashMap <> ();
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            aMap.clear ();
            Point x = points[i];
            int dup = 0;
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;
                Point y = points[j];
                if (x.x == y.x && y.y == x.y) {
                    dup++;
                    continue;
                }
                double slop = 0.0;
                if (y.x == x.x) {
                    slop = Integer.MAX_VALUE;
                } else {
                    slop = (double) ((y.y - x.y) / (y.x - x.x));
                }
                aMap.put (slop, aMap.containsKey (slop) ? aMap.get (slop) + 1 : 1);
            }
            if (aMap.isEmpty ()) {
                max = dup + 1;
            }
            for (Double d: aMap.keySet ()) {
                max = Math.max (max, aMap.get (d) + dup + 1);
            }
        }
        return max;
    }

    public int maxPoints123 (Point[] points) {
        if (points == null)
            return 0;
        if (points.length <= 2)
            return points.length;
        int max = 0;
        int duplicate = 1;// this field setting is amazing
        Map <Double, Integer> map = new HashMap <Double, Integer> ();
        for (int i = 0; i < points.length; i++) {
            map.clear ();
            duplicate = 1;
            Point p = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;
                Point tem = points[j];
                double slope = 0.0;
                if (tem.x == p.x && tem.y == p.y) {
                    duplicate++;
                    continue;
                } else if (tem.x == p.x) {
                    slope = Integer.MAX_VALUE;
                } else {
                    slope = tem.y == p.y ? 0 : 1.0 * (tem.y - p.y) / (tem.x - p.x);
                }
                map.put (slope, map.containsKey (slope) ? map.get (slope) + 1 : 1);
            }
            if (map.keySet ().size () == 0) {
                max = duplicate;
            }
            for (double key: map.keySet ()) {
                max = Math.max (max, duplicate + map.get (key));
            }
        }
        return max;
    }

    @Test
    public void testAddBinary () {}

    public String addBinary (String a, String b) {
        char[] a1 = a.toCharArray ();
        char[] b1 = b.toCharArray ();
        StringBuffer re = new StringBuffer ();
        int carry = 0;
        for (int i = a1.length - 1, j = b1.length - 1; i >= 0 || j >= 0; i--, j--) {
            int aa = i >= 0 ? a1[i] - '0' : 0;
            int bb = j >= 0 ? b1[j] - '0' : 0;
            int val = (aa + bb + carry) % 2;
            carry = aa + bb + carry > 1 ? 1 : 0;
            re.insert (0, val);
        }
        if (carry > 0) {
            re.insert (0, '1');
        }
        return re.toString ();
    }

    @Test
    public void testasdf () {
        System.out.println (divide (-2147483648, -1));
    }

    public int divide (int dividend, int divisor) {
        long result = 0;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            result = devidePositive (Math.abs ((long) dividend), Math.abs ((long) divisor));
        } else {
            result = 0 - devidePositive (Math.abs ((long) dividend), Math.abs ((long) divisor));
        }
        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        else
            return (int) result;
    }

    public int devidePositive (long a, long b) {
        if (b == 1)
            return a > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) a;
        if (a == b)
            return 1;
        if (a < b)
            return 0;
        int i = 0;
        long temp = b;
        while (a > temp) {
            temp <<= 1;
            i++;
        }
        if (a == temp)
            return (int) Math.pow ((double) 2, (double) i);
        else {
            temp = temp >> 1;
            a = a - temp;
            int re = devidePositive (a, b);
            return re + (int) Math.pow ((double) 2, (double) i - 1);
        }
    }

    @Test
    public void testasdfn () {
        String s = "GATGGATACTGCATTCGAACCAGAGCCGGCTTTTGCGGGACTAGCATGAGGGACTTGGCTGTTGAGGCTGTACGAGGTCAGTCCTCCGGCAGTGCTATCGCAGGAATTTTTGCAACTCCACTGCTTATAATCCACCAAGTCCAGACTCAAAGCTCAACTCGGGGATCGCACGGTATGGTCACTGTCGCGCATGCAGTAATAGTCCAGACGAACGCACATTGGTCGTCCCCTGAGCCCGTGCCAGCCTAATACTTCTTATGCCTGCGTAAGTAGACTTTGCCAACGTAATCTCATCCTTATGCCAGATTATTAGTTCATTGAATGTCGGTCGCCGGCGCTCCCGCATTTCTTATCCGCGTATCTTGGGGTCAAGACGTCCCCAGCTTGTTAATACAAGCTACTTTCCCTCGCAATTACTAAGTTCGTGACAAGCGAATCACGCTAAGATGTTATTGGACTCTACAGAAATATTGAATTGACAACATTCGTCTGTTCAGATCGTCGTTCACGCCACTGATAGCGCAGCTCGAGCACTCTGGAGCCACAATGCGGAATGTCCAGAACCTTTGCGCAAGAGTCCGTGGAAAGCATAATCGTGAACAGAATGGCTAGCCGAGGTGCGCAAGGATAGGACCGTCTACACAAAGTATGGGCACCACGCACATCGACACCCCGTAGTGTGTCAGTCGGCTCAGCGGCTAATGGGTTCGGCGTGAGGAATAGAAATAATAGGCAGTGGTGCCAATTGTGGGGTCTTCTTTTGACTTTCTCATCTCTCTATGAATCAGATCGGCCTCTCGCCCCCGCCGGCCCTCTGGCTTTTTTAAATCCTAGATTGTGCACGTGCCCCGGTTTCCTTCAAGGCAAGTGAAGCGCGTCTTTGCTCTAAACCCACGGCCGTTGCACGGCGCCGAACAGGTGTCTCGGTGCGACCGGAGTGAGCAAGTTCTGTCCGCATCGTATGATTATACCCCCTCCTGTCACGGCTCGGGACTTATCGCACCACAGATCAGCTCGCAGCCCCGCGCGAGTACTAGGGACGGGAGGGAAACCAAAGATAATCGTCTTTGCATGGGCCGGCATGTGAATCATTCGTATCATCTTCTGGAGTCTTGTCACGACGATTTTCGATACAGACTGTTGACCCATCTAATCGTGTTGTCAGTCTGGGAACCGTACTTTTTAACCCGTCGTTCGAGCGGCCCGATCAGGGATGCCCGCAGTGTACGGGCACATCGTCGTCTTGGGAACAAAAGCTTGACGGACACCTCTATGCAGACATGAGACGTGAGGCCCCTGCAATAGTGCGGTCACAGGGACCGGCTGTCGATCAGTAGGTATAATCTTGATGTTTGCTGGGAGATTAACAGAGGGCGGAGTTCCGCATCGCCTAACCACTGACAGTCATTGATAGACGCCTAAGTTTGTCCCTGTAGCTACAGTGGTGGCAAAGTGGCCTTGGACGGTTCTGCGCTTGTCAATAAGTCTGTCCCAATCACGAGTGAAAAACTAGCTAGGGTCGGTGATGTGTTTTCAATCATATTTCTCCATCCATCCGGGGCTCCCTAGTACGGAGGAAATCTCCGGGTAACTCTGGATCTCCAGCATTGCGTAAGCAAACCGCCAATCGGGCCGCAGTGAGTTCTTAAACTACGGTTTGGCCCTAATCGCACTATTGGGTGTTGTAGATACGATAGCAAGGTGATTATGAAATCAAGGCACGCACGACCTGTACGTTGATCGTCGAGTGCTCTCGAGTTACTTCATGGGTCAGCCATGCGATTGTCCAAATGGACCGGAAAGTACACTACAAATTGTACCACTGTGCGTTGTACCTCACAAGAACTGTTTGGGTCTACTTACTTTTTACTTGGATCTTTCCTGGTCTCCCTCAGCGTAATTATTCGACACAATGCTGCAGCTGCGTTGTAGTTTTGGCGGTACAGGAAAAATTCTTGTGAGCAACCAGGCCATTCCCTGGAAGGCAGTCCTTGCGAGTATGTTGAGATATATGCTGGGGATGAATTAGAACATTATGCCATCTAAAGTTTGGATTACCGGGGATTCGGCATACCAAATGGATTCGTTGATTATAGCCCCCCCACCTCCTTTTAGGTAAAATGCCCAATCCTGGCGCTGAGCAGGAGGCATGTTGGCCTCTTGTCCGGTAGTACGCTTGACTAGTTCCTAGCGGCGCAAATCACTTGGTCTCTGTCCGTCCTGAATGTTACAAAGCCATATACATGTGTGGAGGTCAAGACATTCCTTATCCGCCCCCCTCGCGATGCAGTTAGATTCGCATTCAAGGTTGAGACCGGAGACCTTCTAACCGGATTTTGGAGTATAGCCCCTTGATAAGAGAAGGAACCATGCTGGGTCTCACGACTATTGAGTTCGGGAAAAGGTGAATGCTCAACGCTAACGCAGTTTGTTACGCCTGGCGGAATAACGTCAGGGACAAACTATATTCTGGCGCCCCAGTGTGGGCTCTTTGACGACATAGGACGGATTAGGCCGGTCTCAACCGCCTCGTAACCCAGGAAGCGGTTCTACTCCCGGCTACTTTTTTGGAGTGTGCAAGGACGTTGCACACAGTGGGTGTCAGATCTAGCCCGTCACATGCAAGTGGTCATATGGATCCCATAATACTCACTGAGTGTCTCGCCAACGGGACTATTAATAGACACGGTGATAGACGGTAGGAATTGTGAGATTCATAATTAGTAACAGTAGGAGCGCCGTAGGCCACGGACCGATACATCGGAACCCTTCGCCGAATACGTTAAGGGTTTGCAACCGGGGGTGCATCTAATCCTTGGGGCTGATCTGTCAAAGGCGTCTCATGCGTGATTATATTAGCGAGAACCTGCATCAATGCTTTAAATAGACAAACCGGTTAATTTGCCAAGAGTTGGGACTACCTGACGGCAAGAGATGGTTAATTGGGCGATTACTTCACGGGTTTGTCTCGATTAAAGAACTAGGATTGCTGTTGTCTTACAAGTTCAGTCATTATCCTTCTGCTATAGCTCTGGATTAGCAATTGGTTTGGGGAAAACCTTTCTCCGTACGAGTAAGGCTGTCGGTAGCCATACTGACTGATCCCGAACACAAAACACTATTCTGTGGAACCAGCAGGTATTAGCATACTGCCCAGTATTCCCGAGTTACGGTTGACTCGGGTCTTAAGCATTTTAAACTGTCCCGATAACCCATTGACTAAGTCCATACATCAGACAAGCTCATGCATCCACTTATACCCGGTGAGAGTAGTTTATGACCAGTAACCGTTACCGAGCCACAGCCACCGCGTGGTGTTGCGGCGCTGTACTATATTATTACTGATGCGGTCCCTAGAGGACTACGCTCCAAGCAAACTTATCTTATTGAAAGTATACACCTAGCAACCTGGGCCGGATTCAATCCGGGATCTGCTCCCCTAGAGCGTTTAATTCGGACCCCTAGTACATAACATTCGGAAATTGGTTCTGGCTTTATGTGGCAATCTGTAATGCAGAAAACATGCTATCGCGCATAAAACCAGTTAAGCTTGATCTCAGATAGTAAAATTCCTGTACCCTCTACAATCCCCCAGAGAAGCTCAGTATACGAACGGAGGAGTAGACTTCGTTACTTGATTCTGGCTACCTCAAAGTCCTCCCTCGAGTTAGAACATGGTGCTCCGTCAGAGGGTTCATTATGGCCGACACGCGAGCTTGCGCTCTGTATGTATGCCGGGGTCCTCTGTCCTGGTGGAGACTGACCATGTACCCCTTATGCCCGGAGAATACCACCTGCTCCACGTGATGCAGCACTGATTGCTCATGGGCGCTCAGCATAGAGATATCTGAGTAGTCCGATACACTTACAGACCGTAAATGCCACTCGGGTTCCTAGCAGAGTGAGGGGTTGCGAATTTTCTGCACATGTAAATTGGGCGTAGACACAACTGTAAAATGCGTCGTAAAAATACAGCTAAGCACCGAACAACGGGATAGGTATTTAGCTAACGTAATTAGTTGTAGAGACACGTAAGCATCCTAGGTCTGACGCTCCCACTATTACGGTGAGTTTGACGCAGAAGTCTTGGTTATGATGCCCTGGATCTGTGAGCTCGGCCTTCCCTGTACAGTGGAAAGATCTCGTTGCTCCGATTGACGGGTTCTGTTTTGTTCGTCCGCGGGGAGTTTCAGATTAACGTCAAGTAGTGCTGGACAGGTCAATAAGATCGCCTCCCTCAACCGTAAGGTCTCGAAGCAAAAGCTCGGTTTGGCATGGAAAACGGAGATTGGCGCTAAGCGACACGGACTGGGGAAGGAAGGAGAGCACAATCTCAGCTTCAAAATGTCCCACCCAGGCTGGGCCCTTCCACGACCTGATTATAATATCTGTATGTGCAAGCGGAAGATTCCCGGTACGTAGAAGAACTCAGCACAGAGACCCCCCGGGACATCGACATCGATTGAACTGCGGGGATGTCAACATAGAACGCGGCGCTCGTAATAAGGGATACCCGGGATAATACACGTCAGGAGGTAACCTGAAGGCGAAGCTCTACAGAGAAAGCGAACCTACGCGTTGATTTAGACCAGCCTACATCACGCTGAGCGCTAATCCACCCGGTAGATAAGGTGAAACACAGATATGGCTGGATTAGATACGGGCGTCCCGTTGCTCATTGGAGTGCGTGTTCGTAACAGACCCCAATAAGGCCGCGTCTGACCAATCATGCGAGAACAAAGACATCCGAACGTCATAAACTATAGATACAAACCACAGGGGAACGTTGCATTGGGCTTGGTCGAAGAATCAATTGAGGTCTGATTGGGAGGCGTCCTGTATAGGTCAATTCTTCTATGGGTACTTCCCCCAGGGCTCCCCATCGACCACTATGCCTCGAGTCTGTCTTGATTTCTGCATGTGCAGACAAGCCTTGACCATACGGGCAATTACTGAGGTCGCCATTCGTGCTACTATATCGTCTACTATTTGAGTGCAAATTTGCCAGACCTGTGGCCCCTTGTGTCAGGGGAGCACATATTAACTAGACCTAGACCCATACGGGTCACACATCTCGGCCGGGAAAATTGGTTATCTGGGTCTATTCTGTACATACTTCGATTTCCATACAAGGTGGATAACTTTTGGCACAGAACGTAGCAGTCGATCTCTACATCCCACGACCTAGATAATCTCCTACCCGTGGTGCGCTATCGGTGGCATCGAGTTGTAATTTTCAGTAGTCCTGTGCACCCGGGGGTGTAGTGACAGCGATTCATCCCCAACGATGGGGGCCACGGATCCACAGCACGGCCACCCGCGGGCCCTAGGCCGACCTATCTACATTGGATCTACCCCGACGAGGCCATGATATAAATAATCCATAGCTAAGCGCATGATTGACTCTATGTCACTCCGTTCTCTAGTGTCTCCATTTCTACTACTTTCGTCGAGCAAGTGTAACAAGAATGCAGTACCTCTTCCAGTTCAGTGGAATCAGGAGCATTGTGCGGCTAAGTCATTAGACCTCATCGCTCCGCGCCGCCATTGTATTAATTAGACGAGGTACTACCCTGTGTCTTAACCGTCGCAGTGGCAGACACCAAGTAAAGTCATGGGGTAGTTCTATCACTAACCAACGGGATCAAGGAGTAGGGACCAGACCACCTATGACGTCCCATCCAACGCGAAGCGCCGGCTCTGACGGGGAGCCGGTAGAATAGGGCCGGCAATCGGGGTCTTGAGGGAGTGTCGAACATCGCGACATGCGAATTCAGTGTTTGGCAGGATTGGGTAGGCAGGCTTCCTGGGGCTGTGCTTTCTTGTTAGTGTACGGACCATACCTTGGGTGAGAGTCTGGGGCCATAGCCTGGATAAAGCATTCCCGGCGCCTCCTTATGCTGTGGCAAGCGCTCCTACACAATGCATGATAACAACCGAGGTCGTCGCACTCACGCTCATGAACAAGAATCTCTACTAGGATCGCTTGCCGGGGGTACTTCGCACCGACTAGGTTCTGTCTTTATCGCTGATAATTTTTACTCAGGGTGCTCCGGCTCTGCACTAGTAATCATCCATGAACCTTTGGCGCGAGAGGCGGTCTTATTACCACGTTGTCATTGAGTACATACTCCGCCGGTCACGTGGTAAGACCAACCACGGACTGTTAGATTTAATGGGGACATATAGGTCCACACAATCAGTCAGACTTAACAGAACGGGCTCTTTTCGCTTTTGGAGCTCCTCTGTATAGGACATCGCTATTGAGCAAAGCATCCGATCGTAGTCGAATACCTCAAGTGAAGGCTTTGCCAAGGATTTACTCCCTGGTTACGTGATAGAGGTTCACCGCGTAGGTGAGGACCTGTGTGTGAAGTTTGTGCGGATTTCGCTGAATGATAGATTCATGGACTATCCGTCCTTTGAGATAGCGATTAGTCATCCTGCGAGACGTTACCATGTGCTACTAAAGAGACTATGAACCCAGCCACGTCATTAATCCGCAGGTAGTCTTGGACTTTTTGCTCGCAACCAACGTTTTTCTAATCCAAAGTTCCGTGCAAGACACGTACCATGCATGCAGCCAGAGTGCTGTCTCCCACGTTCGATGAAGGTAATAAGTGGGTCAAAGAGGGGGGACAGATTACGGGAGCGGAACTTTGTATGACTTATGAAATTAAGACTAGTTACGGGGATGCTCTGGTTATTGAACTGTACAATTTCGATAATGGCCGCGGTCTCGAATTTGCGTTCGCGGGCTCTTGACAATCTCCGTACAAATATACTCGGATACGATTGAGACCAATTAGCAGTCTGCGTTGGGGTTGTAGTCGCCGGTAGACATATCCCGCTAAAGAACGCGGGCGTATGCAGTAGTCAGCAATGCCGGTTGTCTCAAAGTAGTGCGTCCGAATTCATAAGGTATCGCCAGTCTCCCCCACAGTCTGCTTGAGAAGACGTGTAAGGGTCGCGACGGGTAGCTCTACTGGACTGTAATCGGTCGATTAGCACGGGAGGCTATCATACCTACCTCTTGCCTTGAATCGACTTATGGCTAGGCAACGTCACCGGGACTTACTTATCTGGTTTGCAGTGTTCTTTATAGTCAGGGTCCGAGGGTTCCTCTGCGAAGGCCCATCCGCAGAGTTACATAAGTTCCCCTTCGTGCCACAGCTCAGGTAGTTTTTAGAACATAACTAAAAAGGAATCAAGGCGCGGTCCTTCATACGAAATCATTTATAGGCCGGGATGGTGGTAAGGCCGCTACTTCAAGGTGCCTAGGAGAATTAGTACGGTTGTGGCCAATCTCTTCCGAAACAACATAAGATGGCACAGTCACTTTACGTAAGATCACGCCAAGTTGCGGAATGCATTATGGGTCGTCATCCCTGATTGCCTGACACGGATCTTATCTGCGCGTTCGTGTACGCGGTCAGTGTATCGAGTCGGCACGTATTCAGGGAGTTCTTAGTTGCAAAAGGCAGAAACCCTTTCCTCTCGGTCAGATCTCAAAAAATCGTCAGTTGGCTTTCCAGTATCAAAATAAGCGGAGTAAACTAGGGCCCCTTCCAGGAGGGGATGCGATATTTCTCTAAGATACCCGAGGAGCGAGTCCCGGCTAGTTCGGACCCACCGTTATTCAATATAACCGCCGTCTTTTCCTGCGATCAGGGGAAAGAGACTCCAGGTTTGGAGCACACTTGACGATATATAAACTGGCCTTGGTCCTTAAGACGTCCTAGAACGAAGTTTCAACACGGAGGTTCTCTGGAGATAGCCTGATTACCGAGCGGTCGCCCCTCCGGATCCACCAAACGGCTACCGTTCGACTCGTGCTATACTATTGTACGCGAGACAGGCCACAGAACTCGAAGGGGTATAATGTTGTTGCACCTGGCCTTATTACACCACTGACGGATTAACCAGCGCGTATAGTTACGGCGAACCAGCCTGTGCTAATTGATTTCTCGGCTCCACTTTGGTCAGGTCACTAAATGAGGATACACGCTTTATCAGCAAGAATTTAAATACTCAGCGAACGCGGTGAAGATCGTATCCCGGCCGTAGGGGAGGCGTTAGTGCTGCTATAGGGACAATTCATTGCCGAATTTTTTGCGCGCCAATCTAACCATGTGACGCGTCTTTACGCATGCTGCGACGAAGAAAGCCCGTTTAGGAATCCTCACAGCACTTACCATAGGCGCGAATCCTTCCGGTGACGACGCAACATACTGACCTGTGGACGGTTTGTATTTGCGCCGCATGCTTGATTCTTGCGGCGTAAGGGAGCGGCTTCGCAGACTACTGTAACCGGTCATAACGTCACTAAAGAAAGCTTACCCGGGCCAGAAACGGTTCCTCTCAATAGCCCACGAGGAATATTCGACCCTGTCGTCCCCGCCTCCGGCGGTCTTTACGACCCTCATCTTACTCTAATCCTACGTCCCGAATGTCTGTAAGCGTTAAGGCAGAGATACGATGGTGCCTTGCTAGCTTTCTTTATGATAATCGGAATTATGCGCACTCACCTTACGCCGGCAAATCACGGTAAGCCTTCCCCGAGACTATACACCCCATGTGAGCGACAGCAGTGAGTCAATTCGCAAAGAAAAGACCCTCCGTTGCGGATTCGGCGTCACGGGCGAAAGTCGTACAAGTAGGAACTACCCTGCTCCAAGTTACCTGTGTGAAACCTTTAGGGTGCAGCTTCAGATAACTACGGTCACGTTTTTTTCCATCCATGCACTAGGTAGGTTCAATACTCCAAACTGACTCTTGCGGCCAGAGGTGTTCGGAAGTGTACTGAGATTCGCACCCCGCCGAGATTGTCTACGATCAGTTGTTAGGCGGAATGAATGGGACTGTAACGGACCCACGCGCGTGTATACTTTTCCCTTAGGTCCTAAGATGGTTAGCATGTCCTGATAGTGCATCACCGTGCCTTGTATTTAGAGATACACTCAAGTCTGAAGGATCTTTTTCTATAGGATCGCAATAGGACGTGTCTATAGTAAGTACCAGGGCTTCCGTGCGTTCCGATCCGGGACGGCACTCTAATGTCGGAGAATCTGTCGGGGATATGAGGAATTTTACCACTATCACCTTGATCATTGGCCACGGCATGGCTGTGTATGGTCTGGAATTTATACATGAATTTCACATTCGAAACTATGCCGCAGACTCCCTGCGATCCCCTGAACCTGTCGGGGCTCGCGAGATTAAGTGCTTAGTCGTGTACGTAGCATTGGGCCATTCCAGAGCGGGAGCTGAGGGCCGAAGCCAACCCAAAGTTTTAAAAGGCGTCCTTAGGACGAGAGGCTAGTCATTAACCACGCCTTTGCGCGCATTAGTCACACCCGCCCTCTAGTGTTTCAGTGTTCACCGCCGCTATCGTGCTTATTGGCAACGGCGACACCCCATATATGGGACCTGGAACAGAAAGACGCGACAAATTATCCACGAGGCGTTCTCTACAATTTGTTATATCAGTCTAGGTTGTGGCGCCGCATTCTAACTAAGTTGCCACACCGCTCACAGCAGGCTTCTGTGCTAGTGTACGCGTGGCTCCGTGGCCAAAGAGTGCCAAACTTGGTTTGCACGCCATTACGTTTTCAGCTAGTCATAAACCAGGCATACACTTCACAAACTACACTTGCCATTCATTCTCTGCCAGAGATTTGCTTCAAAAGCAGAAGGTAGAACAGTTTCTCTAGGGGTTAAGAACGGGGCCGTCTACTAACCAGCTCCAACGTTATGTCACGTCACAAACGTATAATGTACGAAGTAGCATCGACCAGGCCGGCTATCTCAAACAGACGTGCCATCTTCCTCGCCTTAGGGGGTTCAGCTTCGAATTAGATCCCTTGATCTCGTCCACCGCCTCGAGTGATGTATATAGCTTGTCCTGGAAGCGTGTCCGATTTAGGCATAACAATGATGATATCTTACACCCCCACTCGTCTAATCTCTCTCACGCCCAATAGTTTTTCGTCCGCCCTCTGATGCCAGCACGCGGACAGTGGTCGTTTGCTCGCGATTCGGGTTGTCACTGCAATCACATGCCGGACTTGCAATTGCGCATGACGACAGGTCGACCTCAAACTATAATACTAGCCTCCAGTCTGTCGAAACTTGCGTCTTACCCGCCATGTTGGCGCGGTGATGGGGCAGATGGTGTAGAATCGTGGATTAGTGAGATTCTGGGCGTCAAGGTGATAACCTCATGGGGAAGGTCTTACCTTCCGAGGAAAGATGCTATTGTGAGTATCATCACTTTCTGATAAGTGTAGGGCTATTCTAACCTCAGAGGCGGTGAATCCGCCTGTAGACGCCTCGTGGCGTGCCGCGTTGAACAGGACACTATCCTCCCAGTAATGGAAGTTGGAATATATCACTACAGCCTACCGAGTCAAGCTCTGCAAGCGCCGTTGCTCCAAAGCTCTTTTATCTGCTTTGCCCTTCAGCGGCTGCTAGTATTGGACCCGTGAAAGAATCTCCCAGCACACTAAAACAGTCCGGTAAGTGCCTTACGCAGGCGCCCGTCACCTGCCTATGAGGTTAAGTCAGCCGTGATATAATTCCGCGCCGGAATACTTAACCAGGGAATCGAATGTATCCAGTCGTCTTCACTGGACGAGTCTGCACTGGAGCGCATCTACCGACGCCACTTAGCAAGTGCAACATATGCATTAGAGGATCAATACGTGAGAAGGAAGCATTATGGAGGTTAAGCCTTCTGGGATTGTATGGCTACAGCACCGAAGCAGAACTTCAGTGGAGAGCTGATACTCTACCTCTTTAGACGGTATACTGGCTCTGAGAGACATAGATATTCAAGGGATGAGGTTTGGATGTGAACCCTTCCTGGGAAAGACAGGTGTCATCAAGGTTTCCTGAAACGGCCGCATAGCCAGAACGTCTGGGGGCTTGACCACTAACACTACTTATAGCACATCGCCGAGCCTGCTACACTGGCATGCGATCAAGTCCAGGATTACGCCCTAAGCCCTTAGCCGCTTAGAAGCCGGAGTTAGGAGTATTGACTAGCCATGAGCGACCATCGAGTTACGATTCTCACCTGTGTCTGTGTCTGCAGAAATGTCAGGTTGCGTACGATCGATAACAAGGTGCCAGCATAAGAACGCAAACTGTTTCCGTACCGACTAACTCATAAGATTCTAATGAATGTAGTTAGTGCTTATACATCACACATCGATGCAGTCCGCTCAGACTAGCGAATCCTGCTTTAGGGATGAGTAGCCCCTTAATGCACGTCTCAGCACTCATGTCACTAAACACACGCGGCGCGAACTTTAGCTCTAGCGATCGATTTTGGCCTCTGTGCCTATTCATGAATTCGTCACCGACACACTGCCCGCACGGCATAGAGTACGCCATAGAGTCTTTGTTCCAACTCTTTTAAGTGGCCATGCACTCGTTCTAAGGATAAGCTCGTAACCGTGTCCCATTTTCGAGCACACGGGCCTTCCTTATAATAGTATCCGGGCTGTTGTCCCGTGATGGATTGGTAGGTAATTTTTATCAAATCCGATGCCACTATCTGGAAGTTACCGGAACAACTCTGATCAGCCGTCTATAATGAAAATAGATGAGGACGGCGTACGACCCGGCCGATTGAAGAACTTCATAAGTCCAACTTTACCAAGAGTACATCGTTCACGTCATTCATCTTACACGACCTGCTCTAGGCATCGACCGCTGCCAACGACCTGAGATTCAGCCTTTTCATGTTGTTGTTGTGCGGTCGGTGAGTGGTCAAAACAAGGCCTCGGTCTAAACCCTCTACGAAGCTCGTGGCTTCAACTTCCTAGCGCACTAGTCATCGAGAGCAGATACATCTATTCTTCTAAGGGCATTGCGGGTCAGACCTTGTTTATGTCGAAATAGGGTTTGGTTAGCAAAACCGGAGACGGTTTATGAGAGGTCTTTGTGATTCAAAGAACTCGCAGCCTTGGCCGGTCCCGGGGACGAAGGGGGCTAATGCATTACACAAGCCTCATCCGACCAGTTTCGCATCGGGTTACTGTAAGGAGAACCAATAAAATGGAACTCCCTGGCATGGCTCGCGGAAACCCTCCCAAGCATGGGGCCGGACAACGCTCTTTGGTATAATCCCACACCGAACTTAGGGGTTTAAGCTCGCGGTGACTGTCATGTGTATTCGGAAAGCTAACTCGGCTATAAGTATGCACCTCATCTGGGAGCGTCCCGGTCGAAGACTTAATTCACGCATATGGTTAATCAGCAAGGCACTTGTCACCCAGTTGTGGGCCCCAGACGGAGTAACGGCAATCTGCGGATGCTTCGTAACCCGCCCAATTCGAACCGCTTGAAACTAACGTTCTAGTGTTAACTTGTACCGAAAGATCCACCATTATAGGCGTCGGGACTGTCGCGTGTACATGGCCTTTGGGCTCGTGTATGAGTGTCATACGGCAAGTGCGGGACAATCACTTCGATGGCTACGCGAACAAAGCTCGGCAAGTCCCGAGCGCGCTTTCTCATGCGAGGGTCTCTCATGCTTCACCATTACGTCGTCCACCACGTTGCCGCCCGGGATCGATTACCCAGTTGCGGTCCGAACCTCAGTCATACAATACCCCCGCTTGGCCTATACGCATTTAACTGGATATCAGTGAATGGAAAGGATCAGATTTCCGGGCCGCTCACGGTAGTCTTTCTGATCCCGTCACTTCCTACGATTTCCGCAAGGCATTCATTATATCTGTGATGTCTCACGGAATCTAGTCGTGCATGAGAAGTAAACCGTTTAGCATTCCATAGCAGGATGGCCCCTAGACTCTGTCACTGTCCCGTTTATCTCTGTGCTGAATACGGCCGTGTTGCCCATTAGTTATGACGCCAGACCACGTAGTTGTCATGGACGCCGTCATATGGTTCACTCGCCCCTGGGATGCCCCCCGGGCTACCCACATCGGGCCATCCGATCATCAGTTATTGGTTTCTCAGCCGATAGGCTTTCGACGGCGGTGCCTAGTCGGGTTGCAGCAACGACGCACATGCCGCTCGGGAACCACAGGCACGAGTAAAATTTACATTCCTATTAGGCTCGCGGCGGCTGAGCTCACTCGATCGGTGTCTCTATCTCGCCTTCGCGAGAAACACCAAGAGACATACATCATCCAGCTTGGGGAGACGCGGGTCGGATGTCCCATAATGCAGTCCAAGATGCACCTTCACGATAGAATTACTCCATAAGCGGAATTGGAGCTGTGCAGGTGCACGCAGCATGCTCACAAAGCTCACACACTAGTTCTGATACCGACCCCGAGTGATGGGATGTGATAAAGGTAATTGGGTTAGTCGAATTCTTTTCTTTCGAGGCGTTGCCGGCCTATCTAACTGAAATAATAAAGGTCTCTAGTCGGTCACCGCTAAAGGCGGCGGTATAGGACCAACTGGCGACCGTTTATAACTTGTTTCTGGTCACTTGTTTGAACCGTGCCTGCTGCTATTTGCTGTGAGACAGCAACGGAAAAGAGTGGTCAGTCCAGAGACCTACGAGATCGCTGATCAGTTACCGTGTAAAGTCGCGTCTACCCTTTGCCCGAGTGTGTATCCTGATGAGGTCTGGTCACATACTGAGGACAGCAATGAGACAGACTAGGTCACCGGTATTGACATATAAGAAAACCGATAATTCATTCAGTTGAGGCAGGTAATCTAGTTACAACTCTCTCCTTTCTAGGTGCAAGATATACGATGCCAGGCATTAGTGGAATTGTGTCTTTTGTTACTCACATATGAATCCTCTTTATTGAGTGTTCCAGATGGAGTGAAGATAACCAGTCCCAAAGCGAATTACATGACAGCCGTCATAAAACTGTGCCTCTGGGCGCATTGGGATCACGACGCACGTTAAAGCTCATACACGAACTAACAGTGCTTAAAATGGTCAGTAGCCAAAGTTTCACCTTTGAACCGCTATTTAATAAAATAGCAAGATGAGGTATACTACTCTGCGGTCCTAGATGCATACGGCCGGCATACAACCCGAAACGAGTCAATGTAGTCCAATGACTCACAATATGTAGCAAGAAGCGCCAGTTTGATCCTTACGGAAAGGTCAATCCACGGCACTGGTTTGCTGTAGGTCCGAACTCGATTCGGACCACTGCACCCGCCCCGGTAGGCGCCGGCGCGCCGGCAAGCTCAGCCACTTCTAACATGGTAGGGTAGATTGTGTAAGCGCGTGTTCTGCGACAGAGCGAGGGCATCTAGACATATGATATAGTAGACCATTGGCAGCATACACGGATGGTTCGGCGAGCGGCTCGCGAACGAATTGCTCCACATGCCACTGATTTGTCCTCCATGTCCTATGTCACGCAGATTTCACAGTAGACGCAAACGCGGCTCCGGCTTAGTTGAGGCCGTCACACGCCGTGGCAGATTTGTCTACGTAGGTTAGGATGGGCGCTGACTATGCCATAAAAGGGGGATTGCTAATACGCAGGGCCGCTCCTGGATTTCGGACTAGACGGATGATCACCAATGGACCATAATAGGTCCAGCCCTACAGATGTCGGGGGCCCTAGCGAAGGAACGCACAGTAAAAGTGTCTCGTCTTAAAGCGAATAGTCTCGTACCCCACGCTGTACCAAGTGCACACCCTCTGTTGGCCGGTTCACGCATGAGCTAATGATACCGGTGTCCTGCCTCATGTGTTATCGATGGGAGTACTGGCCGATTCTGTCGCCTAGATCCAGGCATAGGACGTTACTGTTAACAAAACTCGCGGAAGGGTTCTTTCGTCGGGGCGGCTATAGTTCTCACCGCGGGCTAAGGTTATGTAGGCAGCCTGTTCAGGGTCCCCATAATGGTATTTTGTCACGGTCCGCGACTTGACGACACTAAACGACGTGCAGACCATGACTGCAACGTCCGTGACTTACCTTTGTGTACTTCCGCGAAGTCTAACAGGCCTGAGGACTCAGTCGCCTAAGGCATAAGCGCTCTGTGTGCGGCGCTCCTTTCCGACTGACAACAGCCACCTAAATCTATTGTTTGCGATAGGACGGGGTCACCCATAGAGCTTCGGGTATTTGCAGCGCTATCCGTGGTGTAGAGGATCTCTCTTCATGCGCTTGCCATTTCTGCCAACGGGTTTGGTATCGTGCTGTCGTCTGAATTGCTCGGTCAGTATTCTGTCCGGCGGCGAAAGTTACTCTCAAATCCAAGCTTATTGTTGTTAATCCGTGGGTGTTAACTAAGTAGCAGGGTGCTGTCTTGAATATGATTGTACACGTTTATTCTTCTCAGACGGTCGGAGGACCTCTAGTTAACAAACTCGGGTTAGCTGCGGTCACGCGGTGGGTTGTATTCTAACTCTCTATATGGGCTTGCCCATTCGCACACTGTCAGTACTTTCTGCCTATGTTGGCGTTCAAACCAGATTGGGGAATAAGAGCTGAGGTAAAAGATCGAAATAACGAGGTATTCTTGTCCGTTTGTCAAGGGATCGCGCCCTTGCCTAAACTGGATACAGGATTCCTGGAAACGCCGCTCGGTTCGCGCACAGACTGTCTGCGTCCACTCGCCGGTGTGCATGTATCAATTTCCCCAATTCGGAATGCTGGTATTTGTCCAGCCTAAAAACGGATTGGCCCCGATACTCGCTCGTGTCCGCACGCGACGACCAGTTCAGCGAGAACGGGCAAAGGCGTACGCAATAATTTCGACCATCCCGTAACGTGATTGGCGCACATGTACAATACAGTCCCAAGAGATACTATAAGCACTCGAGTAGGCTGCCTAGACGCCGTATTCCTCCAAGCTTGCTCGTGAAAGATTCGCCTTAAAAGGTATGAAGGAAGACTGAGACCTGTCCCGCTCCGCGTCATCACGACGACTTACCAAGGGCTATGATCCTCACCGTTTGAGATCACGTTACTCCGCCCCTGTCGCGAGATGATCAACGTTGGCTTTGGAGTACCAGGCTTACAAGTATAATCGGACTTATTATTTAAGGGACGAGTTTCCATCGCGTACCAAGACTAATGGCATATGATACCAGGGATAGTAAGTGCGTATCCTTTTTAACGTACCGTTGAGCTTCTCACTAACAAGGACTCCATGTATTCTACCACCGAGCGCCTAGCGGACGTAGTTCGCTCAACGATGCCTAGAATAACTACGAGTTACACCTGGAGAACCTCCAGTCCCCGCGATTTCGCGATCTCAGCCGTATACCATGACATTAACTGTCATCGTTCAGCTTATCCTACGTTACCGGGGGTCTATTAACCTGATTTGTGCGAGTCCCACATGGCCGGATAGCCACCAGCTCAACATACCCCGTCCAGCTGTGTAGTCGGTCCGAGATCTGCGTTATGCTTTCTCTTAGTCAAGAGATAAGTAGTGTAACTTACTTTCTGTGCTCGGAAATCAGAAACTGGTCTCTGAGTAGGTCCTGAAGGTGGTTCGAATTAAGTTGATACCATTGGAGGAGCTAATCGTAGGCCATGTATCCCGCTGAGCGTGGGCTTTATGGCACGTAATTACAGAGGAGCAGTAGTACTCAAAGCAGCGACCCCGGTTAGTCTGTCGCCCTCTCGAGGAACAATTACGGCTGTAAGGGCGCGACAGTTCGTAGTACTGCGTCGGACAGTGACCAAGACCAAGACTTAACTGCAGTAGTGACCATTGTACGTGTCAATGGCGTCTAGATGCAGGTTGACCGTGATCCTAAATAGTCTTTACCAGTATCCGCGGATCACCCTGGTAAGAGCTTACGCGATAACCTAGCGATTACTATGTCTGGATGCGATGATTTCAGTCATGCCCGGGACGTCGGGAAAGCATGCGCTTATATGTCGCAGAGTTGATTTGCATTTGTAGTGTCCTCTATTCGTGGTACCAATCGAACCTAGATGTTCTGAGGTCTTAACTGATGCCTCTCAATCAACTTGCCTGATAGGCCGACGCAGTGTGCCCGATTGTGGTTAACGGTAGTTAAGCAGCGCGGCGAGAGGCACGATGGCCCATTACAATAAGCATCTGTAGAGTTCACGGACAACTAGTGTTGGAATGTGCCTCAGGGCTACAATCAGGTGCCTCTGCCACCCGACTAATAGCAACATCGTACCGCCCCCGGTTGGAGCCCAGGGCGGTGTCGGCGTCGTGGCACGTACGAAGACATTCTCTGGGAAGGATGGTCCAGCCACGCATGCGTTTTAGGCTACTGTCATAGGATAGTATATCGGTAATGCTACTAATTTTACCGACATGTTCATCGCTTCCGTTCAATAGTGGTCCATGAGTGCATTTTCCCCTCAAAGATTAGATTCCCATGTAGCCCTCCAATTCACTATCTAGGCGATATGGAGGGCGGGGATTCTTGACCGACACGACCAAGCTTCTCGTCCTCAGGTCCTAGTCCCCGCATATAGAACCCTGGACCGAGAAGTGATCGCGGCCGACTTTGTAGGCCGGGGGTTCATTTGAACTAGCTCGCCGATTATAGATGTGAGTGAACCCGGATATATCGCTCCGACTTAGCCCCGTGAGCCGAATCTACTTTTCTCTCAACGCACGTACGTGAGCTCCGGATTCTTTCTCACTTCCCTCGTCTAAATTGAGAGTCACGCCCATCTTAAACGCGAGCAGGCCGTCAAAGTCCACAGGCCCTAATTTTTCCCGCAGTCTGGAAGTGAGTGTTCTTATTTAGGTTTCAACCCTTCGATACATGTCGTTTCTCCTACCGAATGTGATGCGTGCGCTCCTTCGGGTCAGTATAAATGATGACGGACATTGGCACCTACTCTATTTTGATCTGTTGACAAACGTTATCCACTCGATGGACAAAGCGCACTCAACACTGCTGCCGGCACATTACACACTTCCTTTAGGTCCTCAATGCCGATCTATCTACAGGCCTGTGAGGGTTACGTGGGTACAGTGTGCTAGGAGTATGTCAAATCTGATCTACCTCGAACAGGAACCCAGAAAATAAAATTAGGGGCTCCTACTCCTAATCTATTCTGAGGGAACCCGTTTATGGCCAGTGCGCTAAATTATCCTGTGAGTCGAGTGAGCATGGCAGACGTTGACGCCAGGAACCATCTTGGCTGGAGTAATCCGGTTTCTCCATGCACACCCCCTAGTCATTCAGTCGGAAGCAGAATCTCCGTGGCACAAAGTAATTACAAGGACTACCTTTAGATCGGGAGGCCCGGCCATTCTCCTGTTGGACTGGAAGGTTCCTGTAATAGAAACTAAGATGAGTCGTCAACGCTAGGAGCTGATTGAGGATTAGTGCTGATACGGAAGCGTGGTTTGGGTATCGCAGAGTGGCTAGGATTATCCTACTCGGTGATGGACGGCACGAGCAGTGCCCCCGGATTCAGGTCCTTTGAACTTAAAACGCGCAATCCTAAAAGCTACACTTCAAAATCGTAGCTGGAACTAAACTTTACCCACGAAAATGCAGAATTTAATTCTCGAAGCGGCCTGGGTAGTGGGATTAGATTATCCATTTATGAGGTGTAATGCGCATTATCTTTGTGTCTGCTGTCCTGCATCACGAGCGGTTCGACTATTTTTGAACAGTGAGTGCTTTGCCATTGGGCACGCTTCCGTGTTGGTTTAAGAGATGGGTTCCAGTAGCTGCGCTAAAGGGGAAACATGCGCTACTATGCTCACCGCATAGGTTCGGTCGTTTGAGGATACCATCAGGCAAAACAGCGAGTCGCGACATCCCGACCGGCACGACGAATAGTGAGAACCTGTTCCCGTCAGGTGTCGATCGAGTTTCGCTATAAGTGAGCTTCGACCGCATGTGCACGGTCAGCCTTCCCAAACCATAGACAGCCCATTGTCATACTAACGGAATCTGTGGGGACTGCTGCGTGATTTAAGAGAACTGGGAAGAGCGAGGCATAGCAAAGACACAGATAATTTAAACTTCTGTCCCTTATGTCGGGAACCTGGTTATCAAATAGCGGTGGGCATACATGGAAGAGCAATTGTTTCGATATTGTTATGACGACGATGAATCGCGTGTGCGATGCCCGGGGATACCGTCGGAGCCTAACGCTTTAGATGATGAGATTAGTGACGGATCTTGCAGAGCGGTAATACTCAGTATTACTAGTGCCGCTCGATTCAGCAGTAAATTTTGAGGTCACCCGTGTCATTAAAGAAGCAACTTCTTTCCTCCCAACTTGTACGAAAATTAATACGAGATCCTTTACTTATCAAAACAAAGCGCCATTGTTTTGCAGATCCTCGCGGTTGGCCCAATGCCAGATCTGCTACGATCGGGTCTAGCGATACCATGGCGATCGCTTTGATGATCACTTATACTTAGGAAACGTCTTTATCATTCTAAAGGCATGTCGCATCTACAGTTACTGTATCTCAGGGAACTGCTCAGCACTGCAAACTTTGGGACAAGTCAGAGCCAGTTGTTGCGAGGTAAATTAGTGGTCCGAAGGAGTACGTGTTCCTCTTCGTTGTAAATTCAAAGGCATATCAATTGGAAAAAGCGGCCGCAGTTGTAACTGCGGTCTCAGCGCAACGCCGCCTTCCGAGTACCAGCACCCCTAGCTGTTCATTTGCCGAAATGTCCATGGGTCGTTAAACTGGGCTTAGTGAACCAAGGCATCAGTCGAGTCTCAGGCGAGATCCCTTCCTCATATGGTATGTGTGGCCTGACACATTCCTACACCCCGACGGGCGGGCCAACCTGGTAATTTTCGTCCTGCGGCCACCTGCGAATACGACATCGTGCATGGAGAGGCCTAGGCGCGTGTGGGTCCCCAAGTCACCATACTTTGATCCCCTTCGGATCTATTGCATGTTCAAGACGCAGCCTGCGCTCTCGGGACTCTAGTCGATCCGTTTATCATTTGTTGGGTGCAGCGGGGTTGAATTCCCTCCTAGTGTATCTTAATCAGTCGAATGGACCGCCTCCTTGGCTCTGACTCAGTTGACGGCTTAATCCTCAGTATAAGGGTAACGTCTTCTAGCAACAGACCTGCAGCCAATGAAGCCTCAAACCGGTATCCCTTCAGTATTAGTCTAACCCGTAGTTTATATAAATGCATAGCCGATCATTATATATAGTATGACGAAAGCGAAAAGAACCGGGAGGTATAGGGTTTCGTCATAAAGATGATAAGTTTGAGATACAGTCTAGCGCCGCCCGAAGACCCGATCGACCGGGGCGTCGGGCATAATTGAGTCAGCGTTTGCGGGCCTGATTAATGACCGAATGGATCTTCTCTCGTACTGTAACTTCTCTACAGTGTGTTTCAGCAGTGGATGCGAATAGGCAATTTGACAACTAGGTCAATATCTCGGTTGTTTCTATTAGGTGACCTACTGTGAAGAGCTAACTACCAAGCCGCGCTCCTCTTGGAGTGCTTTTGGTTTCCTAATGATGCTTTGGACAACCTTCTGCATGCGTAAAATCTGACAAAGAAGACATGAATAGGCATAGCCTCACTAAATTTGCGGACCTGGACCATCACTTGCGTTTCCACGCCCTATCGTTGATTGATGAGTGTTGGTCTAGTCCTGGCACCGCTCTTCCAATAGAGGCCCTTCGATGATGTCGTGTAGCCACCGACAGTTCATGGGGATCACTGCAAAGAATGTACACCGAGAAGAGTATATAAGTTAGAGCGAAAACAAGAGGCCCGTGCATGTTTATCTCGAGACCGTTGAAGCGAATTTCAGCTATGTCTTCGTCGTTTACTGTAATGGTCTTTAACCTGTTGAACTGAACACGATGGCTGGTACCACGGAATATGTTATGTCTCGTTCATATGCACTGCCAAGGTTGCATCCTCATGACTCTACTAGCACCAGCTTGGTTTAATTAAGGCGGGGGCTGCGGGCAATCGCGGCCCGCTATCGCGGTAAATTCCAGTACCAATGAGATGGTAACAACTAAAGTGCGAGATAGTATACGCTGGTTACGAAGCTGCAAGCGGTCGACAATGTGGGCAACTAATGACTACGAAAACAATATAGCGGGACCGGCGACGCTCCAATACCACGTACTTGGATGGGTTGAGAGATCGCTTCACAAATTGTGTACTGGCACTGTATTGGAAACTCGACATTGTGGAGGGTTGAAACCCTTCTAGTCAGACGTTTGCCTCGTGGATACGCTTGCACCAAGCATTACCGTTCCGGCCATGTCATAACATGCATGTGCGGACTTCGTTCGTTGTCAATGACTGACACCCCAGGAGGGATCTCACCGTCCGATACTGCGTAATTACTTAGAGTATTCAAACAGGGGTTGAGTACAGGCTACGGCGGGAGCGGTTGTCGTGAGCTCTTTGCCTTCTCCGCTATTTGAGATAACATGCCTAACCCTAGCGGAGACCTCATAGGCTAGTCCCCTCTCAAGAAACGATAATCTCAACCGACCGTACCACCACTTACGCTAGCGCTCCGAGCGCTATCTTAGGCAGACCGCTTCTCCGGCTCGCTAGATAGTTAGGGGGTCTACAAAGCTTTACCTACGTTAATCAACACTTAGTGAGCAGCGGGACATTTTGTATTAGAACAAGGCCATTGGACTAAGATCAGTAGTGAATCCGTATACGTCCGGCCCAGCGCTCCATGTGTTGTTCCAGTCAGAGTAAGTATCACAGGCCAATTCACCGGCGTCCCCAAACTGGCCCCCCCTCTTGAACTCAGGCGGCCAGCTTTGGTTACCGAAGTACACGCACAGGTTTGCTGGAGAGAGCTCAATCCAAGTTGCAACGCTACCGTTGAACACACCTCCTCATGCCTACTATGGTGAACTTTTCTAGCTAGACTAATCAAATAAACAATCTGCAAGCACCAGCCGAGAGGTGGGCGATGGACATGTTCTCTTCTTATCGGTCCGTGCCATTCACATACAGTACCCGGCTGGCGACGGTACATATAGTTGATTGTGGGTACTGCTCGCATCATCCGACACTAGGTTATACTCTTCTTTGTCGGAATGACCCTATATCGATTCGATGGGGTAGGTGGCAGCATGCCCGGCTATTAGCAAATGGGAATCATGAACCTGGGACCCTACCGGATTGAGGTGGCAGATCTGTAGTCTTATGTCTTAATTTTCGAAGCATTCCAGGGTAAGCGACGCACCCCGTAAGATCTGGACCTAGTGACTTATCCCCCACCATGTTCGAACTATGTTGAAATAGCTGTTGGGCTCAATAATTGCGCTTTTCTAATGAGATACACACGCTCTCCTCAGGACCAGCTAGGAATCGCTGTCCTCCGCAGAAGCATTCGGCAGCACTTAAAGACAACGATGATTGCCAAAAGTACTCCAGCGCTGTGGTTTTTTACGCGCTGTTCTGGCCAGTCTTTGACTAAACGCCAACGCAATAGAAAGCCACGGGTGTTTCTGACAAAAGTCATAGACTAGCTTCACATCTGCCTCTGGAGTAAAGCCTCAACCTATCGTTTTTCTCAAATCATCCTCCGCGTTGCGATGCAGCAGCTAAAAAACAAGCACGATAAGACTAGATGGTATAGACACGATCACTATACCTCGATCGTACGTATCACATGCGGGAGCAGTTATATCTGGGGCGCCAACGGTGCTGGACTTCACCAATTCAAAGTTGGTATGTGATCCTCGTCACGATGGATATTTTGGCCGTCGGCAGCTTGGCGGCGGAAAAGACCGCGGCCAGTTGAGTTTACGAACCTCAAGAGGGCCGACCGCATATATACGCAATTATCCTTTCAGCGAAACCTATAAGTGCGCCGTTGACTCATTTGTGTGACAAGAACTCATCGTAGAACCCGCTGTAGCCTCGCTGGCTCGTGGGTGGATTATGGGACAGCTTGCGGTGACACTGACAAGATATTAATCCACCGTCAACAGGCAGAACCCATATATAGGGTAGACCAATGTCATCTTCAACCGGTAACCTGCCACCCATGTTGCTTGGCCTCCAGTTTTCATAAGACGCAGGTCTTTATGCCAAACTGTACGGATCATCATGATTCGCGGATAACGGACCAGGCTGCTGAGCTAGGGGTATGCAGTCCCTGTACTGATTATACGTGCAATGAACTCGAAATACCACTCTGACGGCTGACTGGTCCTCCACAACGAATATAGACGATCGGCATGAGCTCTAGAGAAAGGGTCGAATCTGCCAGGCACGTATCATAAATCTCTTCTTTCTTTAAGCCGGTTGGGTGTATGAGAAGACTTCGGGCGTCACAATACCTGGCGCCGTACCCCATACGTCTTTACTGACGAGCCGAAACGCTATGGCAGGCATCTTGCCACGGAGCCTATAGCAAGCCCCTAAGAGGCGGGATAAATCATGGACTTGAACCCTGTCGCAGTACGTAACCATGGTCTAATCCCTCGGTTTAGCCAAGTAGCTGGGCGACATTGCCATTAAAGCTTCCCTTAAGCGATGAATATCACTCTGTGCCCCTATCGCGGCTCACTACTTTCTGAACTGCGATAGAATGCCTCTCTATCTACCCTACACACACAACTGCGCTGGTGCCCCACGTGTAATTTTGATAAGCACTACTTAACCCGGCCAACCGATATGTTGAAGTTACATCTATCTGGGGGTGGTGCACCAGAAGACCGTATGTGATTTATGGGGGTCACACGTCACGAAAAACATTTCCCCCGGCCAGTGGTGCAGCCACTACTATACCCTATTGAGTCTATAAATGCCAAAGGCAGTAATGCGTCTACCTACACCGTGGCCTGTTCGACCGACCCCACGTAAAGTAATCTGACTCAACGCTGCTCCACTAAATTCGGAAACATATCAGCGTTGATGACAACGGGTTCGAGAGCATACATCTACCAGGCATCTCAGTCCTTAGTACTTGATGCTTGTATGGGCTTATGACTGGACATTTGGGTATGCCCTCCTTATCGATGATGGTTGATGACCCATGGTGAATGGTTAGATCGCGGGGAAGGTTGAGCCATCTCGATAAAGACCGGCTTGGGAGTGCGAATCGATTATACAGAAGGTATAGCAAGGGTGTAGCGCAGACACAAAGCGGTTCACAGCTTTTCTGTTGGAGATTTTAGCATTGTACGAGTCAGAAAGCAACAGCAGTAAGTGTCGTACAACGAGTCCACCATACCGAAGAACGTAACGTCATCGGGCGTTGGGATAGAGGGTCCATCACGGCCCGGAAAACGTCGTCCTGCAAATAAGAGCGTCGTATTGATGTATAAGAGAGCGCGTAGGCTACAGAGCAACAGTCTGACACAAGACTATGAGCTCACATCAAAGTATTACACCTCTTCTAGCCTTGGCAATACTTCGGCATTCGGTAGCCTATGGTCATTGATGCTGCTTCCGCATCCGGGATTTACTGTCTCGATCTCTGTATTTTTCACGTCACGGGTGGGTATAGAAGCGCTGGACCGCCACCCCTGAGGGGATGTGTGCGCGAGGGGTACGCTGGAATCTTGGCCAAAAAGCGCGGGTAAGGGCCGCGGACTCATCTAGTGGTGCCTAGAGCTTCATGATTGAGAGAAGTAGTCCTACATTCACGCCCCGACTTTGGGTCCATCCCAGGAACCCTTAGATTTGGCTAATGGTGGAACGCCCCGAGTAGCCAGGAAGGTGTCCCATCAAAAACGCGAATTCAACGCGAGGTTGCTTCTTAAATGTTCGTCTCGTATGCTCAGACCGACCATCGCCTACCGTGGAAGGCTAGTGGCCGTACTGGGTGACAGAACAAAGGTGGGTTCACCGTGGGCCTGGGTTCGGGTGTACGACGTTGTACTAGTCTATCCGCCCTACTAGTGACATACAGGCAAAATCTACCCAGATTACTGTGAACCGATGACTAGCACCGAGCCTGTTCGTGTTCACAGGAACGGTCTCGATATGCCCCAATCCCGCCATTATCCAATATGATAAGGAGTGACCCTCTATCCTCAGATAAGGTCGCGCGTTGTAACCAGGTCGAACCCTATACTTACTACTTGGCTGCCTTAGGACAGTTGTGTGTATTTGTAGCTCAGCTTTTATTACATCATGAATCACTTTGAAAAACCATATGTTGCCGTTCCTCACGTGTCAACACTGGTTTTTCCTCACCCAGAACGCTGTGCTCTACAGGCGCAGAGGTATGAGGACTGGCGCGACTTAGTTCGCGGTTCATCTGCAATTATACGTAGGGTATACACAGATCTTCTCGGGAAAAAGAGAATTCTCGCTGTCGTCGCATTGGGGACTCAAGTGTGGCCTCCGACCACATTAAAGTGATGGCGGTAAGCTGTTAAGCAACGAGAGCAAGTTAATTTCAGTGGCATCCACCAATTAGTCATAGCATGCGTGTTCAATATATGTTCTCGAACACAACTCTGCAGGGACGACGAATGAGAGTGTGTTAACTCACAAAATGTGCTCAATAAAACCTCTTACTTACCAATTGAACTAATTATAGAATATGGATTTGTTTGTTCCTGTTAAGTATCCCTCTAGCAGAGCCAAGCTGCGTTACCTTTACTAAAGTTGACATGAAACATGCACGGGTGGCGTCCAGCCTTGCGCAACCTGGATGAAGCGTAACTAGGGTAAAACAACGTACATGAGGGATGAGATTTAATAGTCTCTGTGCCATAAGGAGCTTGGTATTTAAGATCGCTCAGGATGTGGTGTCCGGAGCCATCGGCGTGCCGAAGGTCGACGCTCATCATTATTCATTTTTGAATCTGCTTGACTGTTTGCGCACACCAGTGCTTGCCATCGCATAATGGGAATGTGTGACTGAGTTTTGGTCGCATGTGATTGTCAGCCCTACGCTTCGAGGCGCATTGCTAGCGTCGACAACTCGGCGCGGTGTTACGCTTTCAAGCGCTCAAATTGCGAATTCCTAGCTACTCGTTATGCCGTAAAGGCTCACGAATACCAGAGCCAACGAGACTACTATTCTCAAAGTGCATACTCACTAGAAGACATAACGGTCATATGTTGGTACGGTCGCTGAAGGTGTCTAGGAAAGTCATCGTCAGTACGTTTAAATTGCAATATACCCTACGCAATATATAGCAATTGAGTTGAGCGCGATTGTTTGTGAACGGTGGCGGTGCAGATTAGCTCGTAGGCCCGTGGTATAAAATCGGAGCGCCGGTGGAGTCGCCGCGAGCGAGGTACCCATAGGACGATTTAGAAATAGGGGGAGACCTCTTTGATCGCCTGGTAATCGTAATTAGCCTACCAAGTGTGCAAATGTGATCAGTGGACAGCTACATTCTATCGCCATAGTGGAGCGTGATCCTTAAACAACACGCAAGTAACAGATAACACGTCGTCCCTGCGCGATTAAGAGAGATGATAATATCCGAAAATGTGGAGTGGGCTTAACAGTCCGAATGATCCTAATAATGCGACCGCCTCTTAATGGACAAATTAAGTCAAGGTCATCTCCACGCATGGCCCCTTCAACGGATTGATTGACGCTACCGCGTCTTGTCCAGGGAAAGTCGGGTGAACTCGACGAGTGAACCTGTGTTCTGCAGTACAAGCCTGCAGCGTTAACATACGGTGCCCGAGTTAAATCGACGGCCCATGGCGACACTAAGTGTACCTGATTCTTGTTCAGGTGAGGCGGTATGCGGTTAATTACGACAATTACGCGGCACCAGTCGCCGGCTATTCGTGATTCACTCGGGATGCGCACACTTGTGTAGTTTATCTGCACTGCGCGTCGGTACTGCGCTTTGGCGGAGTCTGCGCCAGAGACACTGGACTGCCTTCTGACCCCTACCGATATGCACCACTAACGGATGATGCCGACATCGACCATCACACAAGGTAATCATCACTACTGGAGGGAGGGTTTGGGTTCCGCTTATCGCGAGCAACTGTATGACTGAAGACCTCATGCAGAAATACTCTCTATGGCATCCAACTTTGTCGAGGACACTAAATTAGTCGTTTACAAGCCACACCGTCACGCCGCGCAATCGAGGATTGTACATGTGAATATATTTTAGGCCCCGTCATCTCGGCTTGACGGTTTCCAACGCGGACCCCCGAATCTCTTCGCATCCCGCACTGTGTCCACGTCAGCAAGTGGTAGCCACTGATGTCCTCACCCTTCGAGCTGATCCGTAATCATGTATAGAGTGCGTAGATTACCTAGTTCGCTTTAGTTCGTTGGAAAGCAACTCCGCAGCATTCTATCTCGTGGGTATTGAAAGATTTCGTTTTACGCTAATTGAGTACGCTGTCCTGTTGGCTTCTCAAGGTGGTCTACCTAAGGGCTTTCCCTGATCTTTCAAACCGACTCCCCGAACGAAAACCACAGATTCTGTCAGTATGCCCGTGTGAATTCCCATAAACCTTGCAGAAAGCGCGTCGCAATCATACTGCTATGGTAGTGTATCAGGAACTAGTGTGCTATTGCCGCGGGATAGCCTAGGAAACGACGTACAGTACTGCTCTGGCACCCATGAATTGCAAATTGGAAGTAATTAGACATAGTACTCCGGAGTTCAGCGGTTCTCTTTATTAACTAAATGGAAAAAGGGGGTTAAAAGATACTTTCTGGCCTAGTCACATAATGGCGGAACACTATGGGATCAACGCCCACAAGACAGTGGGGTTTTTGATCCGTGATGTCTTAAGTAGTAAAGGCGACACATCATAGACTGCGGGGGCCTAAAGCTCCCAATCTCACGGCTACTGCCGTCTTTGTCTTCACAGCCTTAGACATGTAATTGCGGTGTGTGGTGACTTAAAGAAGGAGACGATACATCCTGGTAACCTCCGGAAGGTGATATATGTGTAAAACTGGCTACGTTCTTAGCCGGGCGAGTCCCTGACTTCTAACGGCAAACATAACGACGCTGCTTATGGCTCTTGGGCATACTGGACATACCTCAGCCTAGGCAACCTTCCATGGCTCACCGTCGGCCTGAATCACTCAGGCCCCACTGAGGCGCTGGGATATTGTCATGGGGAGGGATTAGCAGTATGTAAGAACGAAATCGGACTAACATTGGGGCAGGACGCTGAGCCTGAGTCATCACTTACGAAGGCGCGTAGCGTTACTCCCTACATAAGTATGGAACCTTAGAAGACAGAGCCGCCTCACTAGTTCGCCCATTTAAGGATGGAGAATTTATGCATGACTGGTGCGAGTATTTGGCCCACTGTTTCTCCTTGGCCTCAAGTCTTTGAGGTCTCACGTCGAACGTAATTCCCGGTGTCCCGATCGAATTACCAGGCGCTGCCATTAAGCACGCGTGATCTCGAATGCTTCTACATAAACGCGTTATGGCTTTCAGCGGGATTTGAGACCACGGTATCTTTACAAACGCCCAAGCCAAATAATAGAGCTTTGCCAGTGTGAGCCTAACAGGGCACTCTCTTTGATGCAACCCCAACGTTCCGCGGGGTGATCTTTCTCTGTAACGCACCGTTTAATATGGAAGTCTTTCTCTCATAATTCGTGGATCGCAAATATGTTAAGTACGTTCGTCCACGGTCGCATTTCGCTCACACAAGCTAAGAGGGGATGATTTTTGTTGTTGCGTACATCGTGTCAAGCGGTGGCCAAACGGGTACGCGTTGTACAATGGCCTAGAATGCTGGATCTGGAAGAATGGTTGGACTAATCCGTCGCTTGGTCTGGTTATCCGGAACTCGCACAGTAGAGTCGGCAGGGCAGCTATTCCACGGAAATAACATTAGGTAGATTCGGAAAATAGGTACATTACGCAAGCGGCCGGGCTTTATTCATGCGAACGGCTCCATATGAGCGAGCTAGCACTTGGGCAATGCCGAGAAAACTCACTTTCAGAAATGCCGGCCGTTAACCTTGAACGAGCTAATGTTAATGCCATGTTCGCCGGCAATAGGGACAGTAGCTCCCACAACACGTTGGCCGGAATCTCCCGTATCTCGAATCTAGAACGAGACAAATTTGAAATTCGTAATGCGGTCCAAACTGTCTGATTTCATAGGGCAGTTGTCTCCGCTTAAAAATCACGATAGTTTCTCCCTGCCTGCAGTATAAGAGGATCACCGTCTGACGCCCTCTCCATTATACAGTTTATGGACGGCGCTTGAACTATCTCCGCCCTTTTCGCTCGTCTACTCGCGCGGCACGATCCAATTCATCCTTGTTGCGGGGAACTTGTCCGCGATTTGACGGAGCCTCGTGGTAGCGAAGTGGAAGATTCGCGATTATCACACTAATGAAAAAAGTTTTCGTGAAGAGTGTGACCGTCTGTGGCAATTGCTCTTTCTATTCGAACAAGTCCGGTATAATAGCGTTCGGCACAAGTACAATTCCGATGCGAATGGTTGACATTGCTGGCCTGCCTCCTCCTATCGCGCGATGTTCCTACAAGTTAAATATAACTATGCCTGACCGGCCGCAGAAGAAAAGGCACATATCCTATTATCAGTTCGCCTTATATCGCAGCCGAAAAGTCCGCCTCTTGATTAGCCTTCTTTTGCTTGGCAACAGGGCTCTAAGGGAAAATTCCATGCCTGGACTTCCGATTAGTGTCCACCCGTTATGTAAAAGCCTAGTAACCTTAACCTACGGTCGGAAAGGGACTCAGTTTGCCCTCAGGCTTACTTTAGTGATGCATTGGGGCGTGACATCAGCGCCAGTGAGTAAGATTTTGGCGTTTAGCAGCGTACGCCACCCGCTCTCGTTTGCCCGCGAACGCATACTTCTTTGTGGCATGAGCCAAGTGGGAAGCTCGACAGCCGTACTCATTCAAGCATGTAACTGATCATAAAAGCCATTTATTGGCTGTCTTGCAGAAGGAGGACCGAGGGAGATCTGACAACGAGAGACGCACAGCGCCCCCTGACCCCATGGGGACCCCAGGGATATACTTCGACGCATTCAGTTTTGCGTGACCCGCGGTGTGCCAGGCGGAGGGCCCTGAGCGTGATGCCTGATGGCCCTGGTATCGACGAGCCACGGCTACGAATGTTGGCTATCATGTTCATACGGTAAGGAAAGAGGAGGACTTAACTTCATGCGTTTCGTAGCGTTGTCCAATCGACTTGCTTGCTCCTTGCGTAAATCCCACAAAACGGCACGGTTGCCGGTGTGTCAAAATGGGAGTAAGTTGTCCCTCAGGAGATCGTAGCTAAATATCAATGGAAATTCCTTCTCTGATCGCCCAACCGTGAGTTTAGGTCCCTTGGAGACGCAGGCTTCGCGAGTCACCCATCTCCCATCCCCACCTACCGCAACGTATGGGCAGCCCTGTGACTTATTGTATCACCATGCTTTGCTTGACAAAGTAGGGTCTATTTTCCATCATACGGCCCTGACTGCATGTACCCTTAATCCCGGTAAGAAGTGAACCCCTCGGTTGGTCATTTGTCGTTGGACTAGAACTTTTCAGCGTCGCAAACTTACACGGACCCGACGCCTGAACTCCCTTCTGATACAGGCCGGAGTAGTAGTACTTATCATCCACTTGTCCTCATTGTTACTGTGTGTCGCGGGTCGCTAACGAACCCTAACAGTCCTCCCACAGACTTTCGGGTTAGACTAGACGTTGGCTCGACTCCATACGGTGGACGGTACCGAGCAATGGTAGACTGTACAGCACCACCGAATTTTCATAAAGCCCTTGGTCGACATCGGTGAATGGACCCAGAAACTAAACTACGATAGTTAATTTGGGTCTTCCGGACACTAAGAGCTGCTCCTATGAACCGAGCTATAAACCCGCCCTACGCAGGACAAAGAAAGAGGTCGATTTTCGGTGAGCGTCGACCGCGTGGAGTCATCCTTGCTGGGGCCGCGACATGAGGCGCTTGGCAGAATCCATCAACTGGCGTCCGGGAGATGAGGCTCACTCCCTGATAGGGGTAGCATCTCCGACATGATCACGTAAATGTGATGTGTCAGCATGTCGAACCCCTACTCCCTGTGCGCAGTCCGTAATTCGGGGGCAAAGTGTACATCCAACTGAGATCGCCTATAGCCGGAACACAAAGGCGCGTGGAACGCTGTCGAAGTCCTTAAAGAACGTTTCTCTACGAGGACGTTATGATAAGAAGGTGCCCCCAACTGAGGGATCGGAGCAAGACACAAGAAAGGTGCTCGGTGTTCAATGGACTGAGCAGTCTTAAGTGCCGACCCGCTTGGTCATGGATGCTTCTGAGCAAATTGCGGGCATCACGAAGGTTCTCCAGTCGCGTAGTAAAGAATATGTGAGTGCTATCACGCTACGTGCTGGTCCGTCATGAGAAGAATTGACTATCCACTGTCGTTAGCTCAATCTATGTGGATATACTCTGGAAACTTGCATAAATATCTTGAATCGTAGGTAGACACCAGTGATTGGAATTGACAGCTTTACTAAGAACATCTGCTTGGTTCTGAGTCAGGGTTTTAAGTAAAGAAGGGAGGGGTTGGCTGGACAGAGTGAATACGTTGCACTCGAGCAAGGCAACACAAATGAGGACTTAGGTGGGTGATATCCCTTGCTCACCTAACGGCGCCTACGTAAATGCGATACCGTGTTACCAGCAGGCTGCCTTCGCTTTCGCGGCTGGTTTAATATAAAATCATGAAAACGACCTCTACTGAGTGTATATACCAATGGGACAATTGTTGTATAGATTTAGATTTGTACTAGCATGGAACGTCACAGCCGAGTCGCAGGGACTATGAATGACGGTCCTCACTTAGCCTTCCAAGCGGGTTGAAGAGCAGCGGCAAGACGAGGTCGCGTTCGGTTGCTATAATTCACAAGGAGTGCCGTTGTCAGAACCTTAGGCTTTACGAGTCCTAAGTAAGATTATAGAAGTTGGTACGACCTTGATAAACCACAGTTCTGTCTCGGAGCTACGTGATTCTATGCAGTACGCAGTCAGCACTTGTCTCTTGCCCAGTTTACGAGTAGCTTAATCTTTATCGTGGTCAACGGCCCCCTGTGCCCAGGATAAGTGACCGAATTTATGTCCAGTTCCTAGAGGCAGATTTCCTAAAGAGAGTACGAGACTTTAAGCCCGTACCAAGGACTCAAGCGACTAAAGCCTCGCTACCGACGAGTGGTATGGGGGCGTTAGTATCGTTACATTCCGGCACGGATAAGCGTATTAGCAACCTTTTTTATATCTAGGGTTTGAATTGGGAGTGGCTAATTTTAACGTGGACTTCTAGACCAAACGGGATCTATCATACATCTTCCTGGACAGTTGCAATGGCAGGCGGCAGGCTTGGGCAGGGGTGAGTCTGTCATACAGCACGAAGAATAGGAGGGGCCTGGCTCTTGTCCCCAGTGTACTCGTATAGGCGAATACGCGGGGCTTTGATGCGTGGCGTAAGAGCTCAGAAATAGGTGGTGATACAATGTGTAGTGAAGGACCCAGGGTTAGCCCCCTACGGTTATTGTGTGGCGGTCGTGGCGCCCGTGCTGCCCTTGGCCAACCGTCACCATTCTTTACGAGGTTCATGAGGCCCACTCAATACCGCGAGGGTTACTAAAAGACTGTCTAACAATACATGGCTAAAGTCCCAAGGGCGATGCGTGATGGGTATACGAGACCACTGAAAGAGCCTTTATTGAAATCGAGCAGCGGAATCATGCTATCCATTCTAAAAGGAGAACACAGGGCCGGATATGTGTATATAAGGACCTACGTGTCATGGTGTGAGAAGCCGGTCCTGCATGCCCAGGTAGGGACCGGGATACACTACGCTATCTTCTGTTAATCGGATTGAATCTGTTTGCCGCGTCCAGACCCGAAGGCTCTGGCAGCACTGTCTGGCTCACACGACTACGCTTCCGATTCCGGAGTTGGCAGTTAATTATGTTTTGGCCAAGAACGTATTCCTGTCTTGCGTAAGAGGAAACGAACCTATGTGTCGCGCTTGACATGCTGCCGCTGAGAAATGGGGAGCGTTAACTTCAGTAGCCTATCTCGGGAACAGTGGACCTCATCATACCTTCGGCTTATTAGACACTACACGAAGTAACCGCGTCGACCCAATCCGTCTCAGACCGTCTATGAGGTCCGCCATGCCTCCATCAAAATGGAAACTTACATGGGGTCGTGGGGATAGCTATCAAAACGTAGGCCCTCGATAACGCTGTTGAAAGATAACGCGAGGGTCCTCTCTTGGAGCAACGATCTCAACGCCGACACCAATCGGGGGCTACACGACCCGTTTCGCTTGGAAGTGGCAACTCGAGTTATTCGCGGCAACTGTGAGTTTCAAAGAATGCGCTCTAGGTCCTTAGCAGTGATTATCCCGACAATCCCAGGAGTCGTGGTTGCTCGCACTAGCCGCCCGGACTGATCACGCGGCCCCATCCACGCCTTAGCTTAATAACCAATCAAGGCTTAAATACAGTCTGTGTAACCAGAACAACGACATGTTAGAGAGGACTCCTCCCTAGACAGAAGAAGTATGGTAGCCCCCTGCGGAAGTCAGGCCGGGCAACTAGTGCCACGCAAGCCGCCAATCTGTGAGTGTGCGGACGATGGTGAATCGGCTCAGGCCACCAATGAGGTAGCGAAACAAACTCTCAAAATCTCGCAATTGGCGACTAACAAACATCGAAGTGCGGCCCGAACGTGTTTGAATGAGGTGAATTACAACCTGCATTGGACAGATTGGACTGCGTCCAAGTCAAAACCTCAAACCCTCCAAGCGCCAATTATCGAGGCCTATCTTTTTCATAACCCTTCACTTAATTGGTGGGCGTTGGATAGACTCAATTTAGCACTCGTGTCTGCGCAGGCTGTCATTTGGCGTGGGGGTATCGTCCAAAGCGCGACAGAAGGCGCAGTTGGCTGAGGAGAAATTGATCGCCGTTTTCTTTCTACCTACTGGGTGCTGGTGAGCTTGTGCAATTGCTTGCTGATTGTTCTAAGGGTCGATTCACTGAGTATGGAGTCATGGGAGACGACAAAGTAACACCATATGGGTCATAGAAGGCGTAACTCATTCACTAGCGGCAGCATCAACCGATCTCCTTGGTGTTAAGTGGGCATCCTTAGGGGCAGGCGGAGAGTCACCAAGCTCTAGTAGTGGCCCACCAATCGCGGGCTTAGAGTAAGCTTTAAGCGAGGGGACAAGACCTTCAAACCTTGCGCCCACGGATCGCTTATTGGTTAAGCCACGGACTTGTGTGACCACAACCTATTAGTCTCTTCGTGCTTAGTTTAGGTTGCTATTGAGAGCAACCTCGGTGTTTAACGTCCAGATTCTTACAAATTTGTGGGGTACCATCCCGACAGGGGAGTGGTTTAAGGTTCACAAATATATACTCTAATATTGTGGTCCTAAGTCTGCAGTGCTTTATCTTTACCTTGAAAGTCAGCCATCTTAGCAAGCAATGCGTTAGGAACCTAGAGCCGGATGGTGTCGGAAGAATCATGTAAAGTCAGACGCGTTATCGATTGTACTACATCTCCTCCCAAGAATAACAGCGATCAAGCACATTTGTGCCGGACTA";
        findRepeatedDnaSequences (s);
    }

    public List <String> findRepeatedDnaSequences (String s) {
        Set <String> aSet = new HashSet <> ();
        for (int i = 0; i < s.length () - 10; i++) {
            String p = s.substring (i, i + 10);
            String c = s.substring (i + 10);
            if (c.contains (p)) {
                aSet.add (p);
            }
        }
        List <String> result = new ArrayList <> (aSet);
        return result;
    }

    @Test
    public void testasdlkfn () {
        String[] words = { "Here", "is", "an", "example", "of", "text", "justification." };
        List <String> result = fullJustify (words, 14);
        System.out.println ("asdf " + "asdfasdf ");
    }

    public List <String> fullJustify (String[] words, int L) {
        List <String> result = new ArrayList <> ();
        StringBuilder sb = new StringBuilder ();
        int count = 0;
        for (String s: words) {
            if (sb.toString ().length () == 0) {
                sb.append (s);
            } else if (sb.toString ().length () > 0 && sb.toString ().length () + s.length () + 1 <= L) {
                sb.append (" ").append (s);
            } else {
                String a = sb.toString ();
                if (a.length () != L) {
                    a = adjustString (a, L);
                }
                result.add (a);
                sb = new StringBuilder ();
                sb.append (s);
            }
        }
        while (sb.toString ().length () < L) {
            sb.append (" ");
        }
        result.add (sb.toString ());
        return result;
    }

    private String adjustString (String a, int len) {
        // handle no " " in the string
        if (!a.contains (" "))
            a = a + " ";
        StringBuilder sb = new StringBuilder (a);
        int i = 0;
        int round = 0;
        while (sb.toString ().length () < len && i < a.length ()) {
            if (a.charAt (i) == ' ') {
                sb.insert (i, " ");
                i = i + 1 + round;// insert one space, needs to update index too.
            }
            i++;
            a = sb.toString ();
            if (i == a.length ()) {// start over
                i = 0;
                round++;
            }
        }
        return sb.toString ();
    }

    public int minDistance (String word1, String word2) {
        int[][] dp = new int[word1.length ()][word2.length ()];
        dp[0][0] = 0;
        int row = dp.length;
        int col = dp[0].length;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < row; i++) {
            char c1 = word1.charAt (i);
            for (int j = 1; j < col; j++) {
                char c2 = word2.charAt (j);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int result = dp[i - 1][j - 1] + 1;
                    result = Math.min (result, dp[i][j - 1] + 1);
                    result = Math.min (result, dp[i - 1][j] + 1);
                    dp[i][j] = result;
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    @Test
    public void goFind () {
        int[] a = { 1, 1, 3, 3 };
        int[] b = { 1, 1, 3, 3 };
        findMedianSortedArrays (b, a);
    }

    public double findMedianSortedArrays (int A[], int B[]) {
        if (A.length + B.length == 0)
            return 0.0;
        int m = A.length;
        int n = B.length;
        int total = m + n;
        if (total % 2 == 0) {
            return (dohelper (A, B, total / 2) + dohelper (A, B, total / 2 + 1)) / 2;
        } else {
            return dohelper (A, B, total / 2 + 1);
        }
    }

    public double dohelper (int[] a, int[] b, int k) {
        if (a.length > b.length) {
            return dohelper (b, a, k);
        }
        if (a.length == 0) {
            return b[k - 1];
        }
        if (k == 1)
            return Math.min (a[0], b[0]);
        int index1 = Math.min (a.length, k / 2);
        int index2 = k - index1;
        if (a[index1 - 1] == b[index2 - 1]) {
            return a[index1 - 1];
        } else if (a[index1 - 1] > b[index2 - 1]) {
            // remove b 0-index2
            return dohelper (a, Arrays.copyOfRange (b, index2, b.length), k - index2);
        } else {
            return dohelper (Arrays.copyOfRange (a, index1, a.length), b, k - index1);
        }
    }

    @Test
    public void testalsdkfjasdf () {
        System.out.println (lengthOfLongestSubstring ("abcdabc"));
    }

    public int lengthOfLongestSubstring (String s) {
        if (s == null || s.length () == 0)
            return 0;
        StringBuilder sb = new StringBuilder ();
        sb.append (s.charAt (0));
        int maxL = 0;
        for (int i = 1; i < s.length (); i++) {
            if (!sb.toString ().contains (String.valueOf (s.charAt (i)))) {
                sb.append (s.charAt (i));
            } else {
                maxL = Math.max (sb.toString ().length (), maxL);
                sb = new StringBuilder (sb.toString ().substring (sb.toString ().indexOf (s.charAt (i)) + 1) + s.charAt (i));
            }
        }
        return Math.max (maxL, sb.toString ().length ());
    }

    public int findM (int[] a, int[] b) {
        int total = a.length + b.length;
        if (total % 2 == 0) {
            return (findMedian (a, b, total / 2) + findMedian (a, b, total / 2 + 1)) / 2;
        } else {
            return findMedian (a, b, total / 2 + 1);
        }
    }

    public int findMedian (int[] a, int[] b, int k) {
        int m = a.length;
        int n = b.length;
        if (m > n)
            findMedian (b, a, k);
        if (m == 0)
            return b[k - 1];
        if (k == 1) {
            return Math.min (a[0], b[0]);
        }
        int aIndex = Math.min (m, k / 2);
        int bIndex = m + n - aIndex;
        if (a[aIndex - 1] == b[bIndex - 1]) {
            return a[aIndex - 1];
        } else if (a[aIndex - 1] > b[bIndex - 1]) {
            // remove b first half
            return findMedian (a, Arrays.copyOfRange (b, bIndex, n), k - bIndex);
        } else {
            return findMedian (Arrays.copyOfRange (a, aIndex, m), b, k - aIndex);
        }
    }

    @Test
    public void testgoasdf () {
        isValid ("()");
    }

    public boolean isValid (String s) {
        if (s == null || s.length () == 0)
            return true;
        Stack <Character> stack = new Stack <> ();
        for (int i = 0; i < s.length (); i++) {
            char c = s.charAt (i);
            if (stack.isEmpty () || !compareChar (c, stack.peek ())) {
                stack.push (c);
            } else {
                stack.pop ();
            }
        }
        return stack.isEmpty ();
    }

    public boolean compareChar (char a, char b) {
        if (a == '{' && b == '}')
            return true;
        if (a == '[' && b == ']')
            return true;
        if (a == '(' && b == ')')
            return true;
        return false;
    }

    public List <List <Integer>> a3Sum (int[] num, int target) {
        List <List <Integer>> result1 = new ArrayList <> ();
        Arrays.sort (num);
        for (int i = 0; i < num.length - 2; i++) {
            int x = i + 1;
            int y = num.length - 1;
            int newTarget = target - num[i];
            List <Integer> result = new ArrayList <> ();
            result.add (num[i]);
            while (x < y) {
                while (num[x] + num[y] > newTarget) {
                    y--;
                }// compare 2 mins
                while (num[x] + num[y] < newTarget) {
                    x++;
                }// compare 2 mins
                if (num[x] + num[y] == newTarget) {
                    result.add (num[x]);
                    result.add (num[y]);
                    result1.add (result);
                }
            }
        }
        return result1;
    }

    public ListNode reverseKGroup (ListNode head, int k) {
        ListNode dummy = new ListNode (0);
        ListNode mark = dummy;
        ListNode pre = null;
        int count = k;
        while (head.next != null) {
            ListNode next = head.next;
            if (count == 0) {
                dummy = pre;
                count = k;
            }
            ListNode save = dummy.next;
            dummy.next = head;
            head.next = save;
            pre = head;
            head = next;
            count--;
        }
        return mark.next;
    }

    public void doSom (int[] num) throws Exception {
        if (num == null || num.length < 2) {
            throw new Exception ("whatever");
        }
        int changeIndex = -1;
        for (int i = num.length - 1; i >= 1; i--) {
            if (num[i] > num[i - 1]) {
                changeIndex = i - 1;
            }
        }
        if (changeIndex == -1) {
            reverse (num, 0, num.length - 1);
        }
        int tcIndex = -1;
        int minDiff = Integer.MAX_VALUE;
        for (int i = num.length - 1; i > changeIndex; i--) {
            if (num[i] > num[changeIndex] && num[i] - num[changeIndex] < minDiff) {
                tcIndex = i;
            }
        }
        swap (num, changeIndex, tcIndex);
        reverse (num, changeIndex + 1, num.length - 1);
    }

    private void swap (int[] num, int changeIndex, int tcIndex) {
        int tmp = num[changeIndex];
        num[changeIndex] = num[tcIndex];
        num[tcIndex] = tmp;
    }

    private void reverse (int[] num, int i, int j) {
        while (i < j) {
            swap (num, i, j);
            i++;
            j--;
        }
    }

    /*
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). You are given a
     * target value to search. If found in the array return its index, otherwise return -1. You may assume no duplicate exists in the array. *
     */
    public int doFind (int[] num, int target) {
        int start = 0;
        int end = num.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (num[mid] == target) {
                return mid;
            } else if (num[start] < num[mid]) {
                if (num[mid] > target && num[start] < target) {
                    // go to left
                    end = mid - 1;
                } else {// right
                    start = mid + 1;
                }
            } else {// go to right
                if (num[mid] < target && num[end] > target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * Given an array of strings, return all groups of strings that are anagrams. Note: All inputs will be in lower-case.
     */
    /**
     * Implement pow(x, n).
     */
    /*
     * You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees (clockwise). Follow up: Could you do this in-place?
     */
    /**
     * Given a collection of numbers, return all possible permutations. For example, [1,2,3] have the following permutations: [1,2,3], [1,3,2],
     * [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    /**
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations. For example, [1,1,2] have the following
     * unique permutations: [1,1,2], [1,2,1], and [2,1,1].
     */
    /**
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were
     * inserted in order. You may assume no duplicates in the array. Here are few examples. [1,3,5,6], 5 → 2 [1,3,5,6], 2 → 1 [1,3,5,6], 7 → 4
     * [1,3,5,6], 0 → 0 00-- 0,n 0n --nn nn -- n0 n0 -- 00 01 -- 1.n 1n- n-1 n
     */
    @Test
    public void goasdfjk () {
        System.out.println (Math.pow (2, 3));
    }

    @Test
    public void rotate (int[][] matrix) {
        int start = 0;
        int end = matrix.length - 1;
        while (start < end) {
            for (int i = start; i <= end; i++) {
                int offset = end - i;
                int temp = matrix[i][start];
                matrix[i][start] = matrix[end - offset][i];
                matrix[end - offset][i] = matrix[end - offset][end - offset];
                matrix[end - offset][end - offset] = matrix[i][end - offset];
                matrix[i][end - offset] = temp;
            }
            start--;
            end--;
        }
    }

    @Test
    public List <List <Integer>> permute (int n) {
        if (n == 1) {
            List <List <Integer>> result = new ArrayList <> ();
            result.add (new ArrayList <> (Arrays.asList (1)));
            return result;
        }
        List <List <Integer>> tResult = permute (n - 1);
        List <List <Integer>> result = new ArrayList <> ();
        for (List <Integer> tr: tResult) {
            for (int i = tr.size () - 1; i >= 0; i--) {
                tr.add (i, n);
                result.add (tr);
            }
        }
        return result;
    }

    public List <String> anagrams (String[] strs) {
        Map <String, List <String>> aMap = new HashMap <> ();
        for (String s: strs) {
            char[] c = s.toCharArray ();
            Arrays.sort (c);
            if (aMap.containsKey (new String (c))) {
                aMap.get (new String (c)).add (s);
            } else {
                aMap.put (new String (c), new ArrayList <> (Arrays.asList (s)));
            }
        }
        List <String> result = new ArrayList <> ();
        for (List <String> r: aMap.values ()) {
            if (r.size () > 1) {
                result.addAll (r);
            }
        }
        return result;
    }
    
    
    
    
}
