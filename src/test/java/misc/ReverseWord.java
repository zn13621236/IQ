package misc;

import java.util.Stack;
import org.junit.Test;

public class ReverseWord {

    public String reverseWords (String s) {
        Stack <String> sStack = new Stack <String> ();
        char[] aAry = s.trim ().toCharArray ();
        String temp = "";
        for (int i = 0; i < aAry.length; i++) {
            if (!" ".equalsIgnoreCase (String.valueOf (aAry[i]))) {
                temp += aAry[i];
            } else {
                if (temp != "") {
                    sStack.push (temp);
                }
                temp = "";
            }
            if (i == aAry.length - 1) {
                sStack.push (temp);
            }
        }
        StringBuffer sb = new StringBuffer ();
        while (!sStack.isEmpty ()) {
            sb.append (sStack.pop ());
            if (!sStack.isEmpty ()) {
                sb.append (" ");
            }
        }
        return sb.toString ();
    }

    // total reverse
    public String reverse2 (String input) {
        StringBuffer sb = new StringBuffer (input.trim ());
        String reverse = sb.reverse ().toString ();
        char[] a = reverse.toCharArray ();
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (" ".equalsIgnoreCase (String.valueOf (a[i]))) {
                String old = reverse.substring (j, i);
                String newString = new StringBuffer (old).reverse ().toString ();
                reverse = reverse.replace (old, newString);
                j = i;
            }
            if (i == a.length - 1) {
                String old = reverse.substring (j, i + 1);
                String newString = new StringBuffer (old).reverse ().toString ();
                reverse = reverse.replace (old, newString);
            }
        }
        return reverse;
    }

    public String reverse3 (String input) {
        Stack <String> aS = new Stack <> ();        
        String[] a=input.trim ().split (" ");        
        for(String b:a){
            if(!"".equalsIgnoreCase (b)){
                aS.push (b);
            }            
        }
        StringBuffer sb = new StringBuffer ();
        while (!aS.isEmpty ()) {
            sb.append (aS.pop ());
            if (!aS.isEmpty ()) {
                sb.append (" ");
            }
        }
        return sb.toString ();
    }


    @Test
    public void testAbc () {
        String s = "asdf azxcvzsdf awqersdf asdqwexqf   asaaaaadfa";
        System.out.println (reverse3 (s));
    }
}
