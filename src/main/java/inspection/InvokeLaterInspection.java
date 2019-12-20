package inspection;

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import quickfix.InvokeLaterMethodQuickfix;
import quickfix.InvokeLaterMethodReferenceQuickfix;
import visitor.InvokeLaterVisitor;

/**
 * @author Jack Duszenko
 */
public class InvokeLaterInspection extends AbstractBaseJavaLocalInspectionTool {

  @NotNull
  @Override
  public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new InvokeLaterVisitor(holder, InvokeLaterMethodQuickfix.create(), InvokeLaterMethodReferenceQuickfix.create());
  }
}
