/**
 * Made by Kyle Doan
 */
package pass;

public class OperatorsScanner {
	
	/**
	 * Multiline comment should not cause any errors.
	 */
	public int divide(int x, int y) {
		return x / y;
	}
	
	public int minusAssign(int x, int y) {
		int result = x;
		result -= y;
		return result;
	}
	
	public int reminder(int x, int y) {
		return x % y;
	}

	public int preDecrement(int x) {
		--x;
		return x;
	}
	
	public int preIncrement(int x) {
		++x;
		return x;
	}
	
	public int postDecrement(int x) {
		x--;
		return x;
	}
	
	public int postIncrement(int x) {
		x++;
		return x;
	}
	
	public boolean notEqual(int x, int y) {
		return x != y;
	}
	
	public boolean equal(int x, int y) {
		return x == y;
	}
	
	public boolean greaterThan(int x, int y) {
		return x > y;
	}
	
	public boolean greaterEqual(int x, int y) {
		return x >= y;
	}
	
	public boolean lessThan(int x, int y) {
		return x < y;
	}
	
	public boolean lessEqual(int x, int y) {
		return x <= y;
	}
	
	public boolean logicalAnd(boolean x, boolean y) {
		return x && y;
	}
	
	public boolean logicalOr(boolean x, boolean y) {
		return x || y;
	}
	
	public boolean logicalAndThenOr(boolean x, boolean y, boolean z) {
		return x && y || z;
	}
	
	public boolean logicalOrThenAnd(boolean x, boolean y, boolean z) {
		return x || y && z;
	}
	
	public int bitwiseNot(int x) {
		return ~x;
	}
	
	public int leftShift(int x, int n) {
		return x << n;
	}
	
	public int signedRightShift(int x, int n) {
		return x >> n;
	}
	
	public int unsignedRightShift(int x, int n) {
		return x >>> n;
	}
	
	public int bitwiseAnd(int x, int y) {
		return x & y;
	}
	
	public int bitwiseOr(int x, int y) {
		return x | y;
	}
	
	public int bitwiseXor(int x, int y) {
		return x ^ y;
	}
	
	public int maxUsingConditionExpression(int a, int b) {
		int c = (a > b) ? a : b;
		return c;
	}
}
