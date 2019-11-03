package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import dpda.DPDA;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author Joshua
 */
public class MainWindow extends JFrame
{

    public DPDA dpda;

    public ButtonPanel buttonPanel;
    public DrawingPanel drawingPanel;
    public TransitionPanel transitionPanel;

    private static final int width = 800;
    private static final int height = 800;

    public MainWindow ( DPDA dpda )
    {
        this.dpda = dpda;
        this.buttonPanel = new ButtonPanel( this );
        this.drawingPanel = new DrawingPanel( this );
        this.transitionPanel = new TransitionPanel( this );

        this.add( this.transitionPanel , BorderLayout.PAGE_START );
        this.add( this.drawingPanel , BorderLayout.CENTER );
        this.add( this.buttonPanel , BorderLayout.PAGE_END );

        this.setMaximumSize( new Dimension( width , height ) );
        this.setMinimumSize( new Dimension( width , height ) );
        this.setPreferredSize( new Dimension( width , height ) );

        this.setDefaultCloseOperation( 3 );
        this.setLocationRelativeTo( null );
        this.setVisible( true );
    }

    @Override
    public void paintComponents ( Graphics g )
    {
        super.paintComponents( g );
    }

    public int getCircleCenterX ()
    {
        return width / 2;
    }

    public int getCircleCenterY ()
    {
        return height / 2;
    }
}
