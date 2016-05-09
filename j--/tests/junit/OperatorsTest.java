/**
 * 
 */
package junit;

import junit.framework.TestCase;
import pass.OperatorsScanner;

/**
 * @author KyleD
 *
 */
public class OperatorsTest extends TestCase {
	private OperatorsScanner op;
	
	protected void setUp() throws Exception {
        super.setUp();
        op = new OperatorsScanner();
    }

    public void testCompute() {
    	assertEquals(op.divide(8, 2), 4);
    	assertEquals(op.minusAssign(8, 2), 6);
    	assertEquals(op.reminder(7, 2), 1);
    	assertEquals(op.preDecrement(8), 7);
    	assertEquals(op.preIncrement(8), 9);
    	assertEquals(op.postDecrement(8), 7);
    	assertEquals(op.postIncrement(8), 9);
    	assertTrue(op.notEqual(1, 2));
    	assertTrue(op.equal(2, 2));
    	assertTrue(op.greaterThan(6, 2));
    	assertTrue(op.greaterEqual(6, 6));
    	assertTrue(op.lessThan(2, 6));
    	assertTrue(op.lessEqual(2, 2));
    	
    	assertTrue(op.logicalAnd(true, true));
    	assertFalse(op.logicalAnd(false, false));
    	assertFalse(op.logicalAnd(true, false));
    	assertFalse(op.logicalAnd(false, true));
    	
    	assertTrue(op.logicalOr(true, true));
    	assertTrue(op.logicalOr(false, true));
    	assertTrue(op.logicalOr(true, false));
    	assertFalse(op.logicalOr(false, false));
    	
    	assertTrue(op.logicalAndThenOr(true, true, true));
    	assertFalse(op.logicalAndThenOr(true, false, false));
    	assertTrue(op.logicalOrThenAnd(true, true, true));
    	assertFalse(op.logicalAndThenOr(true, false, false));
    	
    	assertEquals(op.maxUsingConditionExpression(5, 10), 10);
    	assertEquals(op.maxUsingConditionExpression(10, 5), 10);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
	
}
