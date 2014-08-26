package misc;

import java.util.Stack;
import org.junit.Test;

public class ReversePolishNotation {

    private int reversePolishNotation (String[] tokens) {
        Stack <Integer> aS = new Stack <> ();
        for (String s: tokens) {
            if (s.equalsIgnoreCase ("+")) {
                aS.push (aS.pop () + aS.pop ());
            } else if (s.equalsIgnoreCase ("-")) {
                aS.push (-aS.pop () + aS.pop ());
            } else if (s.equalsIgnoreCase ("*")) {
                aS.push (aS.pop () * aS.pop ());
            } else if (s.equalsIgnoreCase ("/")) {
                try {
                    int i1 = aS.pop ();
                    int i2 = aS.pop ();
                    aS.push (i2 / i1);
                } catch (Exception ex) {
                    return 0;
                }
            } else {
                aS.push (Integer.valueOf (s));
            }
        }
        if (aS.size () != 1)
            return 0;
        return aS.pop ();
    }

    @Test
    public void testAbc () {
        String[] a = new String[] { "2", "1", "+", "3", "*" };
        System.out.println (reversePolishNotation (a));
    }
}
