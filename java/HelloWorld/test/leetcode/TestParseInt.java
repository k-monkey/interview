package leetcode;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/* name: String to Integer (atoi)
    Implement atoi to convert a string to an integer.

    Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

    Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
*/ 

/*
 * Tricks:
 * 1. specify all test cases first.
 * 2. trim white space
 * 3. handle -/+ signs
 * 4. handle leading "0" 
 * 5. *handle positive and negative numbers differently because MAX and MIN value in java
 * has DIFFERENT absolute values.
 */
public class TestParseInt {
    private static int ERROR = 0;
    
    @Test
    public void test1() {
    	assertEquals(0, atoi(null));
    	assertEquals(0, atoi(""));
    	assertEquals(0, atoi("ab123"));
    	assertEquals(-12, atoi("-12ab123"));
    	assertEquals(1234, atoi("1234"));
    	assertEquals(1234, atoi("+1234"));
    	assertEquals(1234, atoi("+01234"));
    	assertEquals(-1234, atoi("-1234"));
    	assertEquals(-1234, atoi("-01234"));
    	assertEquals(0, atoi("0"));
    	assertEquals(0, atoi("-0"));
    	assertEquals(1, atoi("1.09"));
    	assertEquals(-109, atoi(" -109 "));
    	assertEquals(Integer.MAX_VALUE, atoi("999999999999999999999999999"));
    	assertEquals(Integer.MIN_VALUE, atoi("-999999999999999999999999999"));
    }

    
    public int atoi(String str) {
        if ( str == null ) {
            return ERROR;
        }
        
        String input = str.trim();
        int result = 0;
        int sign = 1;
        int startingIdx = 0;
        
        if ( input.length() > 0 ) {
            if ( input.charAt(0) == '-' ) {
                sign = -1;
                startingIdx = 1;
            }
            else if ( input.charAt(0) == '+' ) {
                startingIdx = 1;
            }
        }
        else {
            return ERROR;
        }

        for (int i=startingIdx; i<input.length();i++) {
            if ( input.charAt(i) >= '0' && input.charAt(i) <= '9' ) {
                long num = ((long) result) * 10L;
                long digit = Long.parseLong(input.charAt(i)+"");
                if ( sign == 1 ) {
                    num = num + digit;
                    if ( num >= ((long) Integer.MAX_VALUE ) ) {
                        result = Integer.MAX_VALUE;
                        break;
                    }
                    else {
                        result = (int) num;
                    }
                }
                else { //handle negative range
                    num = num - digit;
                    if ( num <= ((long) Integer.MIN_VALUE) ) {
                        result = Integer.MIN_VALUE;
                        break;
                    }
                    else {
                        result = (int) num;
                    }
                }
            }
            else {
                break;
            }
        }
        
        return result;
    }
}
