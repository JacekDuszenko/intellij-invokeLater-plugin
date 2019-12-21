import javax.swing.SwingUtilities;

public class X {
  public void invokeLaterTest() {
    <caret>SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {

      }
    });
  }
}