package jminusminus;

import static jminusminus.CLConstants.*;

import java.util.ArrayList;

/**
 * The AST node for an switch-statement.
 * @author KyleD
 *
 */
public class JSwitchStatement extends JStatement {
	
	/** Variable */
	private JExpression test;
	private ArrayList<JCaseStatement> cases;
	private ArrayList<JNotEqualOp> testsCase;
	
	public JSwitchStatement(int line, JExpression test, ArrayList<JCaseStatement> cases) {
		super(line);
		this.test = test;
		this.cases = cases;
		this.testsCase = new ArrayList<JNotEqualOp>();
	}
	
	public JStatement analyze(Context context) {
		test = (JExpression) test.analyze(context);
		for (JCaseStatement caseState : cases) {
			caseState = (JCaseStatement) caseState.analyze(context);
			if (caseState.getLiteral() != null) {
				JNotEqualOp testCase = new JNotEqualOp(line(), test, caseState.getLiteral());
				testCase = (JNotEqualOp) testCase.analyze(context);
				testsCase.add(testCase);
			}
		}
		return this;
	}
	
	public void codegen(CLEmitter output) {
		ArrayList<String> caseLabel = new ArrayList<String>();
		for (int i = 1; i < testsCase.size(); i++) {
			caseLabel.add(output.createLabel());
		}
		for (int i = 0; i < testsCase.size(); i++) {
			JNotEqualOp testcase = testsCase.get(i);
			testcase.codegen(output, caseLabel.get(i), false);
			cases.get(i).codegen(output);
			output.addLabel(caseLabel.get(i));
		}
		cases.get(cases.size() - 1).codegen(output);
	}
	
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JSwitchStatement line=\"%d\">\n", line());
		p.indentRight();
		for (JCaseStatement caseTest : cases) {
			caseTest.writeToStdOut(p);
		}
		p.indentLeft();
		p.printf("</JSwitchStatement>");
	}
}

class JCaseStatement extends JStatement {
	
	private JExpression literal;
	private ArrayList<JStatement> body;
	
	public JCaseStatement(int line, JExpression literal, ArrayList<JStatement> body) {
		super(line);
		this.literal = literal;
		this.body = body;
	}
	
	public JStatement analyze(Context context) {
		literal = (JExpression) literal.analyze(context);
		for (JStatement state : body) {
			state = (JStatement) state.analyze(context);
		}
		return this;
	}
	
	public void codegen(CLEmitter output) {
		for (JStatement statement : body) {
			statement.codegen(output);
		}
	}
	
	public JExpression getLiteral() {
		return literal;
	}
	
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JCaseStatement line=\"%d\">\n", line());
		p.indentRight();
		literal.writeToStdOut(p);
		p.indentRight();
		p.printf("<CaseBody>\n");
		p.indentRight();
		for (JStatement statement : body) {
			statement.writeToStdOut(p);
		}
		p.indentLeft();
		p.printf("</CaseBody>\n");
		p.indentLeft();
		p.printf("</JCaseStatement>\n");
		
	}
}
