import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

public class X {
  public void invokeLaterReferenceTest() {
    List<Runnable> listOfRunnables = new ArrayList<>();
    listOfRunnables.stream().forEach(ApplicationManager.getApplication()::invokeLater);
  }
}