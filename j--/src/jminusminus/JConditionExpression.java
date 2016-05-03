/**
 * 
 */
package jminusminus;

import static jminusminus.CLConstants.GOTO;

/**
 * @author KyleD
 *
 */
public class JConditionExpression extends JExpression {

	/** Test expression. */
	private JExpression condition;
	
	/** Then clause. */
	private JExpression thenPart;
	
	/** Else clause. */
	private JExpression elsePart;
	
	/**
	 * Construct an AST node for a condition expression given its line number, the test
	 * expression, the consequent, and the alternate.
	 * 
	 * @param line
	 * 			line in which the condition expression occurs in the source file.
	 * @param condition
	 * 			list expression.
	 * @param thenPart
	 * 			then clause.
	 * @param elsePart
	 * 			else clause.
	 */
	public JConditionExpression(int line, JExpression condition, JExpression thenPart,
			JExpression elsePart) {
		super(line);
		this.condition = condition;
		this.thenPart = thenPart;
		this.elsePart = elsePart;
	}
	
	/**
     * Analyzing the condition expression means analyzing its components and checking
     * that the test is boolean.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
	public JExpression analyze(Context context) {
		condition = (JExpression) condition.analyze(context);
		condition.type().mustMatchExpected(line(), Type.BOOLEAN);
		thenPart = (JExpression) thenPart.analyze(context);
		elsePart = (JExpression) elsePart.analyze(context);
		return this;
	}
	
	/**
     * Code generation for an if-statement. We generate code to branch over the
     * consequent if !test; the consequent is followed by an unconditonal branch
     * over (any) alternate.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        String elseLabel = output.createLabel();
        String endLabel = output.createLabel();
        condition.codegen(output, elseLabel, false);
        thenPart.codegen(output);
        output.addBranchInstruction(GOTO, endLabel);
        output.addLabel(elseLabel);
        elsePart.codegen(output);
        output.addLabel(endLabel);
    }
    
    /**
     * @inheritDoc
     */
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JConditionExpression line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");
        p.printf("<ThenClause>\n");
        p.indentRight();
        thenPart.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ThenClause>\n");
        p.printf("<ElseClause>\n");
        p.indentRight();
        elsePart.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ElseClause>\n");
        p.indentLeft();
        p.printf("</JIfStatement>\n");
    }
}
