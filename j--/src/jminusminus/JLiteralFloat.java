package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for an long literal.
 */

class JLiteralFloat extends JExpression {

    /** String representation of the int. */
    private String text;

    /**
     * Construct an AST node for an int literal given its line number and string
     * representation.
     * 
     * @param line
     *            line in which the literal occurs in the source file.
     * @param text
     *            string representation of the literal.
     */

    public JLiteralFloat(int line, String text) {
        super(line);
        this.text = text;
    }

    /**
     * Analyzing an int literal is trivial.
     * 
     * @param context
     *            context in which names are resolved (ignored here).
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JExpression analyze(Context context) {
        type = Type.FLOAT;
        return this;
    }

    /**
     * Generating code for an int literal means generating code to push it onto
     * the stack.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        float i = Float.parseFloat(text);
        if (i == 0.0) {
            output.addNoArgInstruction(FCONST_0);
        } else if (i == 1.0) {
            output.addNoArgInstruction(FCONST_1);
        } else if (i == 2.0) {
            output.addNoArgInstruction(FCONST_2);
        } else {
        	
        }
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JLiteralFloat line=\"%d\" type=\"%s\" " + "value=\"%s\"/>\n",
                line(), ((type == null) ? "" : type.toString()), text);
    }

}
