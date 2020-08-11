package model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.JTextField;
import view.ButtonPanel;
import view.MainWindow;

/**
 * This class represents the text field where the user inputs their string to
 * test on the DPDA.
 *
 * Upon clicking/pressing the mouse button, the text field is cleared.
 *
 * @author Joshua
 */
public class InputStringField extends JTextField implements MouseListener {

  private final MainWindow mainFrame;
  private final ButtonPanel buttonPanel;

  private final int COLUMNS = 20;

  public InputStringField(MainWindow mainFrame, ButtonPanel buttonPanel) {
    this.mainFrame = mainFrame;
    this.buttonPanel = buttonPanel;

    super.addMouseListener(this);
    super.setText("Input String, Alphabet: " + Arrays.toString(mainFrame.getDpda().getAlphabet()));
    super.setColumns(COLUMNS);
    super.setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent _e) {
    this.setText("");
  }

  @Override
  public void mousePressed(MouseEvent _e) {
    this.setText("");
  }

  @Override
  public void mouseEntered(MouseEvent _e) {
  }

  @Override
  public void mouseExited(MouseEvent _e) {
  }

  @Override
  public void mouseReleased(MouseEvent _e) {
  }
}
