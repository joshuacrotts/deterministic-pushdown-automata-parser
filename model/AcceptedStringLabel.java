package model;

import java.awt.Graphics;
import javax.swing.JLabel;
import view.MainWindow;

/**
 * This class represents the label in the bottom-right corner of the screen,
 * letting the user know when the parsing is complete, and if the string is
 * accepted or not.
 *
 * @author Joshua
 */
public class AcceptedStringLabel extends JLabel {

  private final MainWindow mainWindow;
  private final String PARSE_COMPLETE = "\u2713";
  private final String PARSE_INCOMPLETE = "\u0058";

  public AcceptedStringLabel(MainWindow window) {
    super.setText("String is not accepted.");
    this.mainWindow = window;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    String color = this.mainWindow.getDpda().isCurrentStateFinal() ? "green" : "red";
    String symbol = this.mainWindow.getDpda().doneParsing() ? PARSE_COMPLETE : PARSE_INCOMPLETE;
    this.setText(String.format("<html><font size='3'>%s<font color='%s'>%B</font>&nbsp;%s</html>",
            "String Accepted: ", color, this.mainWindow.getDpda().isCurrentStateFinal(), symbol));
    this.mainWindow.getButtonPanel().getStepButton().setEnabled(!this.mainWindow.getDpda().doneParsing() || this.mainWindow.getDpda().hasEpsilonTransition());
    this.mainWindow.getButtonPanel().repaint();
  }
}
