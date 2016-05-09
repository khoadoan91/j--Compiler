/**
 * 
 */
package pass;

import java.lang.IllegalArgumentException;

/**
 * @author KyleD
 *
 */
public class ThrowsAndThrow {
	public void checkNegativeArgument(int a) {
		if (a < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	public void sample() throws NullPointException, IOException {
		
	}
}
