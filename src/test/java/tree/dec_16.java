package tree;

import org.junit.Test;

public class dec_16 {

    public int compareVersion (String version1, String version2) {
        String[] a = version1.split (".");
        String[] b = version2.split (".");
        int length = Math.min (a.length, b.length);
        for (int i = 0; i < length; i++) {
            if (Integer.valueOf (a[i]) > Integer.valueOf (b[i]))
                return 1;
            if (Integer.valueOf (a[i]) < Integer.valueOf (b[i]))
                return -1;
        }
        if (a.length > b.length)
            return 1;
        if (a.length < b.length)
            return -1;
        return 0;
    }

    @Test
    public void tryTest () {
        String a = "12.3.2.1";
        String b = "12.3.1.1";
        System.out.println (compareVersion (a, b));
    }

    public int maximumGap (int[] num) {
        boolean[] temp = new boolean[Integer.MAX_VALUE];
        for (int i: num) {
            temp[i] = true;
        }
        int i = 0;
        int pre = -1;
        int cur = -1;
        int max = 0;
        while (i < temp.length) {
            while (temp[i] != true) {
                i++;
            }
            if (pre == -1) {
                pre = i;
            }
            cur = i;
            max = Math.max (max, cur - pre);
        }
        return max;
    }

    @Test
    public void tryTest2 () {
        System.out.println (Integer.MAX_VALUE);
    }
}
