/**
 * 
 */
package jminusminus;

/**
 * @author KyleD
 *
 */
public class JThrowStatement extends JStatement {
	
	private JExpression exception;
	
	public JThrowStatement(int line, JExpression exception) {
		super(line);
		this.exception = exception;
	}
	
	public JThrowStatement analyze(Context context) {
		exception = (JExpression) exception.analyze(context);
		return this;
	}
	
	public void codegen(CLEmitter output) {
		exception.codegen(output);
	}
	
	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JThrowStatement line=\"%d\">\n", line());
		p.indentRight();
		exception.writeToStdOut(p);
		p.indentLeft();
		p.printf("</JThrowStatement>\n");
	}
}
