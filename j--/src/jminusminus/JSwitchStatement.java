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
	private ArrayList<JEqualOp> testsCase;
	
	public JSwitchStatement(int line, JExpression test, ArrayList<JCaseStatement> cases) {
		super(line);
		this.test = test;
		this.cases = cases;
		this.testsCase = new ArrayList<JEqualOp>();
	}
	
	public JStatement analyze(Context context) {
		test = (JExpression) test.analyze(context);
		for (int i = 0; i < cases.size(); i++) {
			JCaseStatement temp = cases.get(i);
			temp = (JCaseStatement) temp.analyze(context);
			cases.set(i, temp);
			if (temp.getLabel() != null) {
				JEqualOp testCase = new JEqualOp(line(), test, temp.getLabel());
				testCase = (JEqualOp) testCase.analyze(context);
				testsCase.add(testCase);
				// testsCase doesn't include the default label.
			}
		}
		return this;
	}
	
	public void codegen(CLEmitter output) {
		// create label
		ArrayList<String> caseLabel = new ArrayList<String>();
		for (int i = 0; i < testsCase.size() + 1; i++) {	// create one more label for default
			caseLabel.add(output.createLabel());
		}
		
		// build branch table
		for (int i = 0; i < testsCase.size(); i++) {
			JEqualOp testcase = testsCase.get(i);
			testcase.codegen(output, caseLabel.get(i), true);
//			cases.get(i).codegen(output);
//			output.addLabel(caseLabel.get(i));
		}
		output.addBranchInstruction(GOTO, caseLabel.get(caseLabel.size() - 1));	// go to default case;
		
		// build target table
		for (int i = 0; i < cases.size(); i++) {
			output.addLabel(caseLabel.get(i));
			cases.get(i).codegen(output);
		}
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
	
	private JExpression label;
	private ArrayList<JStatement> body;
	
	public JCaseStatement(int line, JExpression label, ArrayList<JStatement> body) {
		super(line);
		this.label = label;
		this.body = body;
	}
	
	public JStatement analyze(Context context) {
		if (label != null) {
			label = (JExpression) label.analyze(context);
		}
		for (int i = 0; i < body.size(); i++) {
			JStatement temp = body.get(i);
			temp = (JStatement) temp.analyze(context);
			body.set(i, temp);
		}
		return this;
	}
	
	JExpression getLabel() {return label;}
	
	public void codegen(CLEmitter output) {
		for (JStatement statement : body) {
			statement.codegen(output);
		}
	}
	
	public void writeToStdOut(PrettyPrinter p) {
		p.printf("<JCaseStatement line=\"%d\">\n", line());
		p.indentRight();
		if (label != null) {
			label.writeToStdOut(p);
		}
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
