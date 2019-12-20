package inspection;

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import factory.InvokeLaterFactory;
import org.jetbrains.annotations.NotNull;
import quickfix.InvokeLaterMethodQuickfix;
import visitor.InvokeLaterVisitor;

/**
 * @author Jack Duszenko
 */
public class InvokeLaterInspection extends AbstractBaseJavaLocalInspectionTool {

  private InvokeLaterMethodQuickfix invokeLaterMethodQuickfix = new InvokeLaterMethodQuickfix(new InvokeLaterFactory());

  @NotNull
  @Override
  public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new InvokeLaterVisitor(holder, invokeLaterMethodQuickfix);
  }
}
