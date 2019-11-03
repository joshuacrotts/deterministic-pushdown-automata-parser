package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import view.ButtonPanel;
import view.MainWindow;

/**
 *
 * @author Joshua
 */
public class InputStringField extends JTextField implements KeyListener
{

    private MainWindow mainFrame;
    private ButtonPanel buttonPanel;

    public InputStringField ( MainWindow mainFrame , ButtonPanel buttonPanel )
    {
        this.mainFrame = mainFrame;
        this.buttonPanel = buttonPanel;

        this.addKeyListener( this );
        this.setText( "Input String" );
        this.setColumns( 40 );
        this.setVisible( true );
    }

    @Override
    public void keyPressed ( KeyEvent e )
    {
        if ( e.getKeyCode() == KeyEvent.VK_ENTER )
        {
            this.mainFrame.dpda.inputString = this.getText();
        }
    }

    @Override
    public void keyReleased ( KeyEvent _e )
    {
    }

    @Override
    public void keyTyped ( KeyEvent _e )
    {
    }
}
