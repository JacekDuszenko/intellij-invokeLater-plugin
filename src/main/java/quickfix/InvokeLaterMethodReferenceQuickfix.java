package quickfix;

import static util.Constants.QUICKFIX_INVOKE_LATER_REFERENCE_NAME;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiMethodReferenceExpression;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class InvokeLaterMethodReferenceQuickfix implements LocalQuickFix {
  private static final Logger LOG = Logger.getInstance("#InvokeLaterMethodQuickfix");

  @Nls(capitalization = Nls.Capitalization.Sentence)
  @NotNull
  @Override
  public String getName() {
    return QUICKFIX_INVOKE_LATER_REFERENCE_NAME;
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
      replaceSwingRefWithApplicationRef(project, descriptor);
    } catch (IncorrectOperationException ex) {
      LOG.error(ex);
    }
  }

  private void replaceSwingRefWithApplicationRef(Project project, ProblemDescriptor descriptor) {
    PsiMethodReferenceExpression swingInvokeLaterRef = (PsiMethodReferenceExpression)  descriptor.getPsiElement();
    PsiMethodCallExpression applicationInvokeLaterCall = invokeLaterMethodFactory.createInvokeLaterMethodCall(project, oldMethodRunnable);
    swingInvokeLaterCall.replace(applicationInvokeLaterCall);
  }
}
