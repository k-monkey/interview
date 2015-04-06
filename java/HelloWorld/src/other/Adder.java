package other;

/*
 * implement the following js function using java:
 * var adder = function (x) {
               return function (y) {
                   return x+y;
           }
}
var add7 = adder(7);
add7(3) == ?
passSomewhere(add7);
var add6 = adder(6);


public abstract // ← hint // class Number {
…
}

public class MyCoolNumber extends Number { // is the + operator defined for this type in Java
  public MyCoolNumber add(MyCoolNumber x, MyCoolNumber y) {
  
}
  
  */

public class Adder {
	private final boolean isDouble;
	private final Number value;
	
	public Adder(long v) {
		isDouble = false;
		value = new Long(v);
	}
	
	public Adder(double v) {
		isDouble = true;
		value = new Double(v);
	}
	
	public Adder(int v) {
		this((long) v);
	} 

	public Adder(short v) {
		this((long) v);
	}
	
	public Adder(float v) {
		this((double) v);
	}
	
	public Number add(int num) {
		return this.add((long) num);
	}
	
	public Number add(short num) {
		return this.add((long) num);
	}
	
	public Number add(float num) {
		return this.add((double) num);
	}
	
	public Number add(long num) {
		if (this.isDouble) {
			return new Double(value.doubleValue() + num);
		}
		else {
			return new Long(value.longValue() + num);
		}
	}
	
	public Number add(double num) {
		return new Double(value.doubleValue() + num);
	}
}


public class MyCoolNumber extends Number {
	private final Number value;
	
	public MyCoolNumber(double v) {
		this.value = new Double(v);
	}
	
	public MyCoolNumber(long v) {
		this.value = new Long(v);
	}
	
	public MyCoolNumber(float v) {
		this((double)v);
	}

	public MyCoolNumber(int v) {
		this((long) v);
	}
	
	public boolean isDouble() {
		return value instanceof Double;
	}
	
	public MyCoolNumber add(MyCoolNumber y) {
		  if ( this.isDouble() || y.isDouble() ) {
			  return new MyCoolNumber(value.doubleValue() + y.doubleValue());
		  }
		  else {
			  return new MyCoolNumber(value.longValue() + y.longValue());
		  }
	}

	@Override
	public double doubleValue() {
		return value.doubleValue();
	}
	
	@Override
	public long longValue() {
		return value.longValue();
	}
	
	@Override
	public float floatValue() {
		return value.floatValue();
	}

	@Override
	public int intValue() {
		return value.intValue();
	}
}
