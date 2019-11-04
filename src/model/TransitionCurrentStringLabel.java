package model;

import dpda.Transition;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import view.MainWindow;

/**
 * This class shows the current state of the string in parsing.
 *
 * @author Joshua
 */
public class TransitionCurrentStringLabel extends JLabel
{
    private final MainWindow mainWindow;

    public TransitionCurrentStringLabel ( MainWindow window )
    {
        this.mainWindow = window;
        this.setText( " " );
    }

    @Override
    public void paintComponent ( Graphics g )
    {
        super.paintComponent( g );
        this.setFont( new Font( "Dialog", Font.TRUETYPE_FONT, 16 ) );

        if ( this.mainWindow.getDpda().getInputString() == null
                || this.mainWindow.getDpda().getCurrTrans() == null )
        {
            this.setText( " " );
        }
        else
        {
            if ( this.mainWindow.getDpda().getInputString().substring( this.mainWindow.getDpda().getCharPos() ).isEmpty() )
            {
                this.setText( "Current String: " + Transition.EPSILON );
            }
            else
            {
                this.setText( "Current String: " + this.mainWindow.getDpda().getInputString().substring( this.mainWindow.getDpda().getCharPos() ) );
            }
        }
    }

    public void resetText ()
    {
        this.setText( " " );
    }
}
