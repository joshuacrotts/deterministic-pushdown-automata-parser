package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import model.TransitionCurrentStringLabel;
import model.TransitionInputStringLabel;
import model.TransitionLabel;

/**
 * This class is the transition panel at the top of the screen. It shows the
 * input string, the transition that just occurred, and the current state of the
 * string in parsing.s
 *
 * @author Joshua
 */
public class TransitionPanel extends JPanel {

  private final MainWindow mainFrame;
  private TransitionCurrentStringLabel currStringLabel;
  private TransitionInputStringLabel inputStringLabel;
  private TransitionLabel transLabel;

  public TransitionPanel(MainWindow frame) {
    this.mainFrame = frame;
    super.setLayout(new BorderLayout());
    
    this.transLabel = new TransitionLabel(mainFrame);
    this.inputStringLabel = new TransitionInputStringLabel(mainFrame);
    this.currStringLabel = new TransitionCurrentStringLabel(mainFrame);

    super.add(this.inputStringLabel, BorderLayout.LINE_START);
    super.add(this.transLabel, BorderLayout.CENTER);
    super.add(this.currStringLabel, BorderLayout.LINE_END);
    super.setBackground(Color.GRAY);
  }

  /**
   * @return the currStringLabel
   */
  public TransitionCurrentStringLabel getCurrStringLabel() {
    return currStringLabel;
  }

  /**
   * @param currStringLabel the currStringLabel to set
   */
  public void setCurrStringLabel(TransitionCurrentStringLabel currStringLabel) {
    this.currStringLabel = currStringLabel;
  }

  /**
   * @return the inputStringLabel
   */
  public TransitionInputStringLabel getInputStringLabel() {
    return inputStringLabel;
  }

  /**
   * @param inputStringLabel the inputStringLabel to set
   */
  public void setInputStringLabel(TransitionInputStringLabel inputStringLabel) {
    this.inputStringLabel = inputStringLabel;
  }

  /**
   * @return the transLabel
   */
  public TransitionLabel getTransLabel() {
    return transLabel;
  }

  /**
   * @param transLabel the transLabel to set
   */
  public void setTransLabel(TransitionLabel transLabel) {
    this.transLabel = transLabel;
  }

}
