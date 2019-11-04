package model;

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
public class TransitionLabel extends JLabel
{
    private final MainWindow mainWindow;

    public TransitionLabel ( MainWindow window )
    {
        this.mainWindow = window;
        this.setText( "DPDA Parser" );
        this.setHorizontalAlignment( JLabel.CENTER );
    }

    @Override
    public void paintComponent ( Graphics g )
    {
        super.paintComponent( g );
        this.setFont( new Font( "Dialog", Font.TRUETYPE_FONT, 16 ) );

        if ( this.mainWindow.getDpda().getInputString() == null
                || this.mainWindow.getDpda().getCurrTrans() == null )
        {
            this.setText( "DPDA Parser" );
        }
        else
        {
            this.setText( "Transition: " + this.mainWindow.getDpda().getCurrTrans() );
        }
    }

    public void resetText ()
    {
        this.setText( "DPDA Parser" );
    }
}
