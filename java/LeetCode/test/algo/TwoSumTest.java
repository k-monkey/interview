package algo;

import junit.framework.TestCase;
import org.junit.Test;


public class TwoSumTest extends TestCase {

	@Test
	public void testTwoSum() {
		TwoSum ts = new TwoSum();
		int[] a = {0,1,3,4,24,2,23};
		int[] result = ts.twoSum(a,  25); 
		assertEquals(2, result.length);
		assertEquals(1, a[result[0]-1]);
		assertEquals(24, a[result[1]-1]);		
	}

}
