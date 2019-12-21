package visitor;

import static util.Constants.INVOKE_LATER_QUALIFIED_NAME;
import static util.Constants.INVOKE_LATER_REFERENCE_QUALIFIED_NAME;
import static util.Constants.INVOKE_LATER_VISITOR_TEMPLATE;

import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiMethodReferenceExpression;
import com.intellij.psi.PsiReferenceExpression;
import quickfix.InvokeLaterMethodQuickfix;
import quickfix.InvokeLaterMethodReferenceQuickfix;

/**
 * InvokeLaterVisitor looks for SwingUtilities#invokeLater method calls and method references
 * registering a problem when any of those two are found.
 *
 * @author Jack Duszenko
 */
public class InvokeLaterVisitor extends JavaElementVisitor {

  private ProblemsHolder problemsHolder;
  private InvokeLaterMethodQuickfix invokeLaterMethodQuickfix;
  private InvokeLaterMethodReferenceQuickfix invokeLaterMethodReferenceQuickfix;

  public InvokeLaterVisitor(ProblemsHolder problemsHolder,
                            InvokeLaterMethodQuickfix invokeLaterMethodQuickfix,
                            InvokeLaterMethodReferenceQuickfix invokeLaterMethodReferenceQuickfix) {
    this.problemsHolder = problemsHolder;
    this.invokeLaterMethodQuickfix = invokeLaterMethodQuickfix;
    this.invokeLaterMethodReferenceQuickfix = invokeLaterMethodReferenceQuickfix;
  }

  /**
   * Visits method call expressions looking for SwingUtilities#invokeLater and registers a problem when found.
   *
   * @param expression - SwingUtilities#invokeLater method call expression.
   */
  @Override
  public void visitMethodCallExpression(PsiMethodCallExpression expression) {
    super.visitMethodCallExpression(expression);
    PsiReferenceExpression methodCallExpression = expression.getMethodExpression();
    String methodName = methodCallExpression.getQualifiedName();
    if (INVOKE_LATER_QUALIFIED_NAME.equals(methodName)) {
      problemsHolder.registerProblem(expression, INVOKE_LATER_VISITOR_TEMPLATE, invokeLaterMethodQuickfix);
    }
  }

  /**
   * Visits method reference expressions looking for SwingUtilities#invokeLater and registers a problem when found.
   *
   * @param expression - visited method reference expression.
   */
  @Override
  public void visitMethodReferenceExpression(PsiMethodReferenceExpression expression) {
    super.visitMethodReferenceExpression(expression);
    String qualifiedName = expression.getQualifiedName();
    if (INVOKE_LATER_REFERENCE_QUALIFIED_NAME.equals(qualifiedName)) {
      problemsHolder.registerProblem(expression, INVOKE_LATER_VISITOR_TEMPLATE, invokeLaterMethodReferenceQuickfix);
    }
  }
}
