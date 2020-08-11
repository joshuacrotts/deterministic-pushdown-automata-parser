package view;

import java.awt.Color;
import javax.swing.JPanel;
import model.AcceptedStringLabel;
import model.InputStringField;
import model.ResetButton;
import model.SetStringButton;
import model.StepButton;

/**
 * This class is the panel for the buttons/interactors at the bottom of the
 * screen.
 *
 * @author Joshua
 */
public class ButtonPanel extends JPanel {

  private final MainWindow mainFrame;
  private final StepButton stepButton;
  private final SetStringButton setStringButton;
  private final InputStringField inputStringField;
  private final AcceptedStringLabel acceptedStringLabel;
  private final ResetButton resetButton;

  public ButtonPanel(MainWindow frame) {
    this.mainFrame = frame;
    this.resetButton = new ResetButton(mainFrame);
    this.setStringButton = new SetStringButton(mainFrame);
    this.stepButton = new StepButton(mainFrame);
    this.inputStringField = new InputStringField(mainFrame, this);
    this.acceptedStringLabel = new AcceptedStringLabel(mainFrame);
    
    super.add(this.resetButton);
    super.add(this.setStringButton);
    super.add(this.stepButton);
    super.add(this.inputStringField);
    super.add(this.acceptedStringLabel);
    super.setBackground(Color.GRAY);
  }

  public String getInputString() {
    return this.inputStringField.getText();
  }

  public InputStringField getInputField() {
    return this.inputStringField;
  }

  public StepButton getStepButton() {
    return this.stepButton;
  }

  public SetStringButton getSetStringButton() {
    return this.setStringButton;
  }
}
