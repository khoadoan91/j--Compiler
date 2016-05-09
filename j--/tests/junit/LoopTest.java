/**
 * 
 */
package junit;

import junit.framework.TestCase;
import pass.Loop;

/**
 * @author KyleD
 *
 */
public class LoopTest extends TestCase {
	private Loop mLoop;
	
	protected void setUp() throws Exception {
		super.setUp();
		mLoop = new Loop();
	}
	
	public void testCompute() {
		assertEquals(mLoop.powerItselfToGreaterThan1000(2), 65536);
		assertEquals(mLoop.powerItselfUsingForLoop(2), 65536);
		assertEquals(mLoop.powerItselfToGreaterThan1000UsingWhileLoop(2), 65536);
	}
	
	protected void tearDown() throws Exception {
        super.tearDown();
    }
}
