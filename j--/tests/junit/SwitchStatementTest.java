/**
 * 
 */
package junit;

import junit.framework.TestCase;
import pass.SwitchStatement;

/**
 * @author KyleD
 *
 */
public class SwitchStatementTest extends TestCase {
	private SwitchStatement mSwitch;
	
	protected void setUp() throws Exception {
		super.setUp();
		mSwitch = new SwitchStatement();
	}
	
	public void testCompute() {
		assertEquals("2342", mSwitch.print1ToUsingSwitch(2));
		assertEquals("5", mSwitch.print1ToUsingSwitch(5));
	}
	
	protected void tearDown() throws Exception {
        super.tearDown();
    }
}
