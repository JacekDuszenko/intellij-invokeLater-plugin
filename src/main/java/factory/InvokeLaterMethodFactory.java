package factory;

import static util.Constants.QUICKFIX_REPLACE_TEMPLATE;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiMethodCallExpression;

public class InvokeLaterMethodFactory {

  public PsiMethodCallExpression createInvokeLaterMethodCall(Project project, PsiElement oldMethodRunnable) {
    PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
    PsiMethodCallExpression invokeLaterCallExpression = (PsiMethodCallExpression) factory.createExpressionFromText(QUICKFIX_REPLACE_TEMPLATE, null);
    invokeLaterCallExpression.getArgumentList().getExpressions()[0].replace(oldMethodRunnable);
    return invokeLaterCallExpression;
  }
}
