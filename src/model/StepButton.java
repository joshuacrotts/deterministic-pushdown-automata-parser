package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import view.MainWindow;

/**
 * This button allows the user to take the next step in terms of parsing the
 * string in the DPDA.
 *
 * @author Joshua
 */
public class StepButton extends JButton implements ActionListener
{
    private MainWindow mainWindow;

    public StepButton ( MainWindow window )
    {
        super.setText( "Step DPDA" );
        this.mainWindow = window;
        this.setEnabled( false );
        this.addActionListener( this );
    }

    @Override
    public void actionPerformed ( ActionEvent _e )
    {
        if ( this.mainWindow.getDpda().getInputString() == null )
        {
            this.mainWindow.getDpda().setInputString( this.mainWindow.getButtonPanel().getInputString() );
        }
        this.mainWindow.getDpda().stepString();
        this.mainWindow.getDrawingPanel().repaint();
        this.mainWindow.getTransitionPanel().repaint();
    }
}
