import org.junit.Test;

import junit.framework.TestCase;


public class ThreadTest extends TestCase {
	//int[] a = {19,21,1,4,5,7,13};
	//int[] a = {13,19,21,1,4,5,7,13,13};
	//int[] a = {1,1,4,5,7,13,13,1};
	//int[] a = {3,3,3,3,3,3,3,3,3,3,3};
	int[] a = {1};
	
	
	@Test
	public void test2() {
		//System.out.println(search(4, a));
		//System.out.println(search(a, 0));
	}
	
	@Test
	public void testIdo() {	
		for (int i=0; i<a.length; i++) {
			int found = search(a, a[i]);
			//System.out.println(a[i] + " is at " + search(a, a[i]));
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
