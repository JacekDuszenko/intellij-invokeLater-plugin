package factory;

import static util.Constants.QUICKFIX_INVOKE_LATER_METHOD_REFERENCE_REPLACE_TEMPLATE;
import static util.Constants.QUICKFIX_INVOKE_LATER_METHOD_REPLACE_TEMPLATE;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiMethodReferenceExpression;

public class InvokeLaterFactory {

  public PsiMethodCallExpression createInvokeLaterMethodCall(Project project, PsiElement oldMethodRunnable) {
    PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
    PsiMethodCallExpression invokeLaterCallExpression = (PsiMethodCallExpression) factory.createExpressionFromText(QUICKFIX_INVOKE_LATER_METHOD_REPLACE_TEMPLATE, null);
    invokeLaterCallExpression.getArgumentList().getExpressions()[0].replace(oldMethodRunnable);
    return invokeLaterCallExpression;
  }

  public PsiMethodReferenceExpression createInvokeLaterMethodReference(Project project) {
    PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
    return (PsiMethodReferenceExpression) factory.createExpressionFromText(QUICKFIX_INVOKE_LATER_METHOD_REFERENCE_REPLACE_TEMPLATE, null);
  }
}
