/**
 * 
 */
package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * @author KyleD
 *
 */
public class JDoUntilStatement extends JStatement {
	
	private JExpression condition;
	
	private JStatement body;
	
	public JDoUntilStatement(int line, JExpression condition, JStatement body) {
		super(line);
		this.condition = condition;
		this.body = body;
	}
	
	/**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JDoUntilStatement analyze(Context context) {
        condition = condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        body = (JStatement) body.analyze(context);
        return this;
    }
	
	public void codegen(CLEmitter output) {
		String loop = output.createLabel();
		output.addLabel(loop);
		body.codegen(output);
		condition.codegen(output, loop, false);
	}
	
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JDoUntil line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<Body>\n");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");
        p.indentLeft();
        p.printf("</JDoUntilStatement>\n");
	}
}
