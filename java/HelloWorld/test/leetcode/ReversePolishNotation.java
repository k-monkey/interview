package leetcode;

import java.util.Stack;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * name: Evaluate Reverse Polish Notation
 * @author Initiator
 * 
 *  Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:

  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

 *
 */
/*
 * Trick: is to use a stack, and process the array from left (starting at index 0).
 * 
 */
public class ReversePolishNotation extends TestCase {

	@Test
	public void test1() {
		String[] input1 = {"2", "1", "+", "3", "*"};
		assertEquals(9, evalRPN(input1));
		
		String[] input2 = {"4", "13", "5", "/", "+"};
		assertEquals(6, evalRPN(input2));
	}
	
	/*
	 * this was the given solution.
	 */
    public int evalRPN(String[] tokens) {
        int a,b;
        Stack<Integer> S = new Stack<Integer>();
        for (String s : tokens) {
            if(s.equals("+")) {
                S.add(S.pop()+S.pop());
            }
            else if(s.equals("/")) {
                b = S.pop();
                a = S.pop();
                S.add(a / b);
            }
            else if(s.equals("*")) {
                S.add(S.pop() * S.pop());
            }
            else if(s.equals("-")) {
                b = S.pop();
                a = S.pop();
                S.add(a - b);
            }
            else {
                S.add(Integer.parseInt(s));
            }
        }   
        return S.pop();
    }
}
