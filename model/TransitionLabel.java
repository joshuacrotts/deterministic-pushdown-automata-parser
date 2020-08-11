package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import view.MainWindow;

/**
 * This text displays, at the top-center of the screen, what transition just
 * occurred in the graph.
 *
 * @author Joshua
 */
public class TransitionLabel extends JLabel {

  private final MainWindow mainWindow;
  
  private final int FONT_SIZE = 16;

  public TransitionLabel(MainWindow window) {
    this.mainWindow = window;
    super.setText("DPDA Parser");
    super.setForeground(Color.WHITE);
    super.setHorizontalAlignment(JLabel.CENTER);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setFont(new Font("Dialog", Font.TRUETYPE_FONT, FONT_SIZE));

    if (this.mainWindow.getDpda().getInputString() == null
            || this.mainWindow.getDpda().getCurrTrans() == null) {
      this.setText("DPDA Parser");
    } else {
      this.setText("" + this.mainWindow.getDpda().getCurrTrans());
    }
  }

  public void resetText() {
    this.setText("DPDA Parser");
  }
}
