/**
 * 
 */
package jminusminus;

import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * @author KyleD
 *
 */
public class JTryCatchFinallyStatement extends JStatement {
	
	private JBlock body;
	private ArrayList<JCatchStatement> catchesBody;
	private JStatement finallyBody;
	
	public JTryCatchFinallyStatement(int line, JBlock body, ArrayList<JCatchStatement> catchesBody,
			JStatement finallyBody) {
		super(line);
		this.body = body;
		this.catchesBody = catchesBody;
		this.finallyBody = finallyBody;
	}
	
	public JStatement analyze(Context context) {
		body = (JBlock) body.analyze(context);
		for (JCatchStatement catchSt : catchesBody) {
			catchSt = (JCatchStatement) catchSt.analyze(context);
		}
		if (finallyBody != null) {
			finallyBody = (JStatement) finallyBody.analyze(context);
		}
		return this;
	}
	
	public void codegen(CLEmitter output) {
		body.codegen(output);
		for (JCatchStatement catchSt : catchesBody) {
			catchSt.codegen(output);
		}
		if (finallyBody != null) {
			finallyBody.codegen(output);
		}
	}
	
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JTryCatchFinallyStatement line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<TryBlock>\n");
		p.indentRight();
		body.writeToStdOut(p);
		p.indentLeft();
		p.printf("/TryBlock>\n");
		p.printf("<CatchBlock>\n");
		p.indentRight();
		for (JCatchStatement catchSt : catchesBody) {
			catchSt.writeToStdOut(p);
		}
		p.indentLeft();
		p.printf("</CatchBlock>\n");
		if (finallyBody != null) {
			p.printf("<FinallyBlock>\n");
			finallyBody.writeToStdOut(p);
			p.indentLeft();
			p.printf("</FinallyBlock>\n");
		}
		p.indentLeft();
		p.printf("</JTryCatchFinallyStatement>\n");
	}
}


/**
 * @author KyleD
 *
 */
class JCatchStatement extends JStatement {
	private JFormalParameter exception;
	private JBlock body;
	
	public JCatchStatement(int line, JFormalParameter exception, JBlock body) {
		super(line);
		this.exception = exception;
		this.body = body;
	}
	
	public JCatchStatement analyze(Context context) {
		exception = (JFormalParameter) exception.analyze(context);
		body = (JBlock) body.analyze(context);
		return this;
	}
	
	public void codegen(CLEmitter output) {
		// TODO implement codegen for CatchStatement
		exception.codegen(output);
		body.codegen(output);
	}
	
	@Override
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JCatchStatement line=\"%d\">\n", line());
		p.indentRight();
		p.printf("<Exception>\n");
		p.indentRight();
		exception.writeToStdOut(p);
		p.indentLeft();
		p.printf("</Exception>\n");
		p.printf("<Body>\n");
		p.indentRight();
		body.writeToStdOut(p);
		p.indentLeft();
		p.printf("</Body>\n");
		p.indentLeft();
		p.printf("</JCatchStatement>\n");
	}
}