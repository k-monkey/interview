import org.junit.Test;

import junit.framework.TestCase;

/*
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

 */

public class TestNew extends TestCase {
	int[] a1 = {19,21,1,4,5,7,13};
	int[] a2 = {13,19,21,1,4,5,7,13,13};
	int[] a3 = {1,1,4,5,7,13,13,1};
	int[] a4= {3,3,3,3,3,3,3,3,3,3,3};
	int[] a5 = {1};
	
	
	@Test
	public void test1() {
		assertTrue(true);
	}	
}


HashMap<String, Integer> numMap = new HashMap<String, Integer>();

int init() {
    int i=0;
    for (char c='0'; c <= '9'; c++) {
        numMap.put(c+"", new Integer(i));
        i++;
    }
}

int parseInt(String s) throws Exception {
    if ( s == null ) {
        throw new Exception("Invalid input");
    }
    
    String seq = s.trim();
    if ( seq.length == 0 ) {
       throw new Exception("Invalid input");
    }
    
    int result = 0;
    int sign = 1;
    for (int i; i< seq.length; i++) {
        char ch = seq.charAt(i);
        if ( ch == '-' && i==0) {
            sign = -1;
        }
        else if ( ch <= '9' && ch >= '0' ) {
            if ( ch == '0' && i == 0 && seq.lengh == 1) {
               throw new Exception("Invalid input");
            }
            
            int num = Integer.parseIntValue(ch + "");
            result = attachNum(result, num);
            
            if ( result == Integer.MAX_VALUE ) {
               throw new Exception("execeed max value");
            }
        }
        else {
            throw Exception("Invalid input");
        }
    }
    
    return result * sign;
}

//1294967296 * 10 = 64771072

int MY_MAX = 1294967296;

(MY_MAX - addOn ) /10 

private int attachNum2(int orig, int addOn) {
    if ( (MY_MAX - addOn) <= orig * 10 ) {
        return orig * 10 + addOn;
    }
    else {
        return Integer.MAX_VALUE;
    }
}

private int attachNum(int orig, int addOn) {
    if ( orig == 0 ) {
        return addOn;
    }
    else {
        int temp = orig * 10;
        if ( temp <= 0 || temp + addOn <= 0 ) {
            return Integer.MAX_VALUE;
        }
        else {
            return temp + addOn;
        }
    }
}