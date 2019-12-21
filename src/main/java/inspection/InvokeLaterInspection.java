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
 *
 * This local inspection scans for SwingUtilities#invokeLater methods or method references and marks them as erroneous.
 * Two fixes, one for method, the other for method reference are implemented. They change SwingUtilities#invokeLater method
 * to ApplicationManager#getApplication#invokeLater as suggested in ApplicationManager docs.
 */
public class InvokeLaterInspection extends AbstractBaseJavaLocalInspectionTool {

  @NotNull
  @Override
  public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new InvokeLaterVisitor(holder, InvokeLaterMethodQuickfix.create(), InvokeLaterMethodReferenceQuickfix.create());
  }
}
