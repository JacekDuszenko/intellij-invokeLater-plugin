package quickfix;

import static util.Constants.QUICKFIX_INVOKE_LATER_NAME;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.util.IncorrectOperationException;
import factory.InvokeLaterMethodFactory;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class InvokeLaterMethodQuickfix implements LocalQuickFix {
  private static final Logger LOG = Logger.getInstance("#InvokeLaterMethodQuickfix");

  private InvokeLaterMethodFactory invokeLaterMethodFactory;

  public InvokeLaterMethodQuickfix(InvokeLaterMethodFactory invokeLaterMethodFactory) {
    this.invokeLaterMethodFactory = invokeLaterMethodFactory;
  }

  @Nls(capitalization = Nls.Capitalization.Sentence)
  @NotNull
  @Override
  public String getName() {
    return QUICKFIX_INVOKE_LATER_NAME;
  }

  @Nls(capitalization = Nls.Capitalization.Sentence)
  @NotNull
  @Override
  public String getFamilyName() {
    return getName();
  }

  @Override
  public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
    try {
      replaceSwingWithApplication(project, descriptor);
    } catch (IncorrectOperationException ex) {
      LOG.error(ex);
    }
  }

  private void replaceSwingWithApplication(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
    PsiMethodCallExpression swingInvokeLaterCall = (PsiMethodCallExpression) descriptor.getPsiElement();
    PsiElement oldMethodRunnable = swingInvokeLaterCall.getArgumentList().getExpressions()[0];
    PsiMethodCallExpression applicationInvokeLaterCall = invokeLaterMethodFactory.createInvokeLaterMethodCall(project, oldMethodRunnable);
    swingInvokeLaterCall.replace(applicationInvokeLaterCall);
  }
}
