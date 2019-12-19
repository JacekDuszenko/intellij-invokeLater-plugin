import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class HelloAction extends AnAction {

    public HelloAction() {
        super("yo");
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
       // ApplicationManager.getApplication().invokeLater(); proper way
        Messages.showMessageDialog(project, "Hello fellow!", "Asd", Messages.getQuestionIcon());
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
