package view;

import java.awt.Color;
import javax.swing.JPanel;
import model.AcceptedStringLabel;
import model.InputStringField;
import model.StepButton;

/**
 *
 * @author Joshua
 */
public class ButtonPanel extends JPanel
{

    private MainWindow mainFrame;
    private StepButton stepButton;
    private InputStringField inputStringField;
    private AcceptedStringLabel acceptedStringLabel;

    public ButtonPanel ( MainWindow frame )
    {
        this.mainFrame = frame;
        this.stepButton = new StepButton( mainFrame );
        this.inputStringField = new InputStringField( mainFrame , this );
        this.acceptedStringLabel = new AcceptedStringLabel( mainFrame );
        this.add( this.stepButton );
        this.add( this.inputStringField );
        this.add( this.acceptedStringLabel );
        this.setBackground( Color.GRAY );
    }

}