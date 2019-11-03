package view;

import java.awt.Color;
import javax.swing.JPanel;
import model.TransitionLabel;

/**
 *
 * @author Joshua
 */
public class TransitionPanel extends JPanel
{
    private MainWindow mainFrame;
    private TransitionLabel transLabel;

    public TransitionPanel ( MainWindow frame )
    {
        this.mainFrame = frame;
        this.transLabel = new TransitionLabel( mainFrame );
        this.add( this.transLabel );
        this.setBackground( Color.GRAY );
    }

}
