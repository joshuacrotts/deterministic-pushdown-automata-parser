package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import view.MainWindow;

/**
 * This class is a reset button; it resets the DPDA to its initial position,
 * clears the current state, all transitions currently made, and removes all
 * coloration from the graph.
 *
 * @author Joshua
 */
public class ResetButton extends JButton implements ActionListener
{
    private final MainWindow mainWindow;

    public ResetButton ( MainWindow window )
    {
        super.setText( "Reset Diagram" );
        this.mainWindow = window;
        this.addActionListener( this );
    }

    @Override
    public void actionPerformed ( ActionEvent _e )
    {
        this.mainWindow.getDpda().resetDPDA();
        this.mainWindow.getTransitionPanel().getInputStringLabel().setText( " " );
        this.mainWindow.getTransitionPanel().getCurrStringLabel().setText( " " );
        this.mainWindow.getTransitionPanel().getTransLabel().setText( "DPDA Parser" );
        this.mainWindow.getDrawingPanel().repaint();
        this.mainWindow.getButtonPanel().getSetStringButton().setEnabled( true );
        this.mainWindow.getButtonPanel().getStepButton().setEnabled( false );
    }
}
