import javax.swing.SwingUtilities;

public class X {
  public void invokeLaterTest() {
    ApplicationManager.getApplication().invokeLater(new Runnable() {
      @Override
      public void run() {

      }
    });
  }
}