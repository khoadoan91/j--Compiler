package pass;

public class Loop {
	public int powerItselfToGreaterThan1000(int x) {
		int result = x;
		if (x > 1) {
			do {
				result *= result;
			} until (result > 1000);
		}
		return result;
	}
	
	public int powerItselfUsingForLoop(int x) {
		for (int result = x; result < 1000; result *= result) {
		}
		return result;
	}
	
	public int powerItselfToGreaterThan1000UsingWhileLoop(int x) {
		int result = x;
		while (result <= 1000) {
			result *= result;
		}
		return result;
	}
}
