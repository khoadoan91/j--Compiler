package fail;

public class UndeclaredVariable {
	public static void main(String[] args) {
		int a;
		if (x == 0) {
			a = x * 100;
		} else if (x == 10) {
			a = x - 10;
		}
	}
}
