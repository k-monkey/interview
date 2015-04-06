package leetcode;
import org.junit.Test;

import junit.framework.TestCase;

/* name: 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

 */

public class TestSearchRotatedSortedArray extends TestCase {
	int[] a1 = {19,21,1,4,5,7,13};
	int[] a2 = {13,19,21,1,4,5,7,13,13};
	int[] a3 = {1,1,4,5,7,13,13,1};
	int[] a4= {3,3,3,3,3,3,3,3,3,3,3};
	int[] a5 = {1};
	
	
	@Test
	public void test1() {
		assertTrue(search(a5, 1) == 0);
		assertTrue(search(a5, 0) == -1);
		
		assertTrue(a4[search(a4, 3)] == 3);
		assertTrue(search(a4, 0) == -1);
	}
	
	@Test
	public void test2() {
		doTest(a1);
		doTest(a2);
		doTest(a3);
		
		assertTrue(search(a1, 0) == -1);
		assertTrue(search(a2, 0) == -1);
		assertTrue(search(a3, 0) == -1);
	}
	
	private void doTest(int[] a) {
		for (int i=0; i<a.length; i++) {
			int found = search(a, a[i]);
			assertTrue(found != -1);
			assertTrue(a[i] == a[found]);
		}
	}
	
	
    public int search(int[] A, int target) {
        if ( A == null || A.length == 0 ) {
	        return -1;
	    }
	    
	    return doSearch(target, A, 0, A.length-1);
    }

	int doSearch(int e, int[] input, int startIdx, int endIdx) {
		if ( startIdx > endIdx ) {
			return -1;
		}
		
	    int midPt = (endIdx - startIdx)/2 + startIdx;	    
	    if ( e == input[midPt] ) {
	        return midPt;
	    }
	    else if ( e > input[midPt] ) {
	        if ( e > input[endIdx] ) {
	        	if ( input[endIdx] >= input[midPt] ) {
	        		return doSearch(e, input, startIdx, midPt -1);
	        	}
	        	else {
	        		return doSearch(e, input, midPt+1, endIdx);
	        	}
	        }
	        else {
	        	return doSearch(e, input, midPt+1, endIdx);
	        }
	    }
	    else {
	        if ( e < input[startIdx] ) {
	        	if ( input[startIdx] <= input[midPt] ) {
	        		return doSearch(e, input, midPt+1, endIdx);
	        	}
	        	else {
	        		return doSearch(e, input, startIdx, midPt-1);
	        	}
	        }
	        else {
	        	return doSearch(e, input, startIdx, midPt-1);
	        }
	    }
	}
}
