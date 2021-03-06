package compiler488.ast.stmt;

import java.util.ListIterator;

import compiler488.ast.ASTList;
import compiler488.ast.PrettyPrinter;
import compiler488.ast.expn.Expn;
import compiler488.semantics.ASTVisitor;

/**
 * Represents a loop in which the exit condition is evaluated after each pass.
 */
public class RepeatUntilStmt extends LoopingStmt {
	public RepeatUntilStmt(Expn expn, ASTList<Stmt> body) {
		super(expn, body);
	}

	@Override
	public void prettyPrint(PrettyPrinter p) {
		p.print("repeat ");
		body.prettyPrintBlock(p);
		p.println(" until ");
		expn.prettyPrint(p);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitEnter(this);
		ListIterator<Stmt> bod_lst = body.listIterator();
		while (bod_lst.hasNext()) {
			bod_lst.next().accept(visitor);
		}
		this.expn.accept(visitor);
		visitor.visitLeave(this);
	}
}
