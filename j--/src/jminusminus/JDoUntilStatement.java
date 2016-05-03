/**
 * 
 */
package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * @author KyleD
 *
 */
public class JDoUntilStatement extends JWhileStatement {
	
	public JDoUntilStatement(int line, JExpression condition, JStatement body) {
		super(line, condition, body);
	}
	
	public void codegen(CLEmitter output) {
		String loop = output.createLabel();
		String out = output.createLabel();
		output.addLabel(loop);
		getBody().codegen(output);
		getCondition().codegen(output, loop, true);
		output.addLabel(out);
	}
}
