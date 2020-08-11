package model;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import view.MainWindow;

/**
 *
 * @author Joshua
 */
public class TransitionInputStringLabel extends JLabel {

  private final MainWindow mainWindow;
  
  private final int FONT_SIZE = 16;

  public TransitionInputStringLabel(MainWindow window) {
    this.mainWindow = window;
    super.setText(" ");
    super.setForeground(Color.WHITE);
    super.setLayout(new FlowLayout(FlowLayout.LEFT));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setFont(new Font("Dialog", Font.TRUETYPE_FONT, FONT_SIZE));

    if (this.mainWindow.getDpda().getInputString() == null
            || this.mainWindow.getDpda().getCurrTrans() == null) {
      this.setText(" ");
    } else {
      if (this.mainWindow.getDpda().getInputString().isEmpty()) {
        this.setText("Input String: e");
      } else {
        this.setText("Input String: " + this.mainWindow.getDpda().getInputString());
      }
    }
  }

  /**
   * Clears the text of the component.
   */
  public void resetText() {
    super.setText(" ");
  }
}
