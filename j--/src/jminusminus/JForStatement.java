/**
 * 
 */
package jminusminus;

import java.util.ArrayList;
import static jminusminus.CLConstants.*;

/**
 * @author KyleD
 *
 */
public class JForStatement extends JStatement {
	
	private JVariableDeclaration init;
	private JExpression condition;
	private ArrayList<JExpression> increments;
	private JStatement body;
	
	public JForStatement(int line, JVariableDeclaration init, 
			JExpression condition, ArrayList<JExpression> increments, 
			JStatement body) {
		super(line);
		this.init = init;
		this.condition = condition;
		this.increments = increments;
		this.body = body;
	}

	/* (non-Javadoc)
	 * @see jminusminus.JAST#analyze(jminusminus.Context)
	 */
	@Override
	public JAST analyze(Context context) {
		init = (JVariableDeclaration) init.analyze(context);
		condition = condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
		for (JExpression inc : increments) {
			inc = (JExpression) inc.analyze(context);
		}
		body = (JStatement) body.analyze(context);
		return this;
	}

	/* (non-Javadoc)
	 * @see jminusminus.JAST#codegen(jminusminus.CLEmitter)
	 */
	@Override
	public void codegen(CLEmitter output) {
		init.codegen(output);
		String test = output.createLabel();
		String out = output.createLabel();

		output.addLabel(test);
		condition.codegen(output, out, false);
		
		body.codegen(output);
		for (JStatement inc : increments) {
			inc.codegen(output);
		}
		output.addBranchInstruction(GOTO, test);
		
		output.addLabel(out);
	}

	/* (non-Javadoc)
	 * @see jminusminus.JAST#writeToStdOut(jminusminus.PrettyPrinter)
	 */
	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JForStatement line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<ForExpression>\n");
        p.indentRight();
        init.writeToStdOut(p);
        condition.writeToStdOut(p);
        for (JStatement inc : increments) {
        	inc.writeToStdOut(p);
        }
        p.indentLeft();
        p.printf("</ForExpression>\n");
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JForStatement>\n");
	}

}
