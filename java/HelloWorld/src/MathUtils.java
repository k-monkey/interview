import java.security.InvalidParameterException;

public class MathUtils {
	public static long fib(long i) {
		if ( i < 0 ) {
			throw new InvalidParameterException();
		} 
		else if ( i == 0 || i == 1) {
			return 1L;
		}
		else {
			return i + fib(i-1);
		}
	} 
}
