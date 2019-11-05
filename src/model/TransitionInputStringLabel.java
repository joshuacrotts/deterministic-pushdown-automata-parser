package model;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import view.MainWindow;

/**
 *
 * @author Joshua
 */
public class TransitionInputStringLabel extends JLabel
{

    private MainWindow mainWindow;

    public TransitionInputStringLabel ( MainWindow window )
    {
        this.mainWindow = window;
        this.setText( " " );
        this.setForeground( Color.WHITE );
        this.setLayout( new FlowLayout( FlowLayout.LEFT ) );

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
            if ( this.mainWindow.getDpda().getInputString().isEmpty() )
            {
                this.setText( "Input String: e" );
            }
            else
            {
                this.setText( "Input String: " + this.mainWindow.getDpda().getInputString() );
            }
        }
    }

    public void resetText ()
    {
        this.setText( " " );

    }
}
