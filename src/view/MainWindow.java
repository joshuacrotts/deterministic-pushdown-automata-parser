package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import dpda.DPDA;
import java.awt.Dimension;

/**
 * This class is the core window/frame where all the JPanels are added to.
 *
 * @author Joshua
 */
public class MainWindow extends JFrame
{
    private static final int height = 800;
    private static final int width = 800;

    private ButtonPanel buttonPanel;
    private DPDA dpda;
    private DrawingPanel drawingPanel;
    private TransitionPanel transitionPanel;

    public MainWindow ( DPDA dpda )
    {
        this.dpda = dpda;
        this.buttonPanel = new ButtonPanel( this );
        this.drawingPanel = new DrawingPanel( this );
        this.transitionPanel = new TransitionPanel( this );

        this.add( this.transitionPanel, BorderLayout.PAGE_START );
        this.add( this.drawingPanel, BorderLayout.CENTER );
        this.add( this.buttonPanel, BorderLayout.PAGE_END );

        this.setMaximumSize( new Dimension( width, height ) );
        this.setMinimumSize( new Dimension( width, height ) );
        this.setPreferredSize( new Dimension( width, height ) );

        this.setDefaultCloseOperation( 3 );
        this.setLocationRelativeTo( null );
        this.setVisible( true );
    }

    public int getCircleCenterX ()
    {
        return width / 2;
    }

    public int getCircleCenterY ()
    {
        return height / 2;
    }

    /**
     * @return the buttonPanel
     */
    public ButtonPanel getButtonPanel ()
    {
        return buttonPanel;
    }

    /**
     * @param buttonPanel the buttonPanel to set
     */
    public void setButtonPanel ( ButtonPanel buttonPanel )
    {
        this.buttonPanel = buttonPanel;
    }

    /**
     * @return the dpda
     */
    public DPDA getDpda ()
    {
        return dpda;
    }

    /**
     * @param dpda the dpda to set
     */
    public void setDpda ( DPDA dpda )
    {
        this.dpda = dpda;
    }

    /**
     * @return the drawingPanel
     */
    public DrawingPanel getDrawingPanel ()
    {
        return drawingPanel;
    }

    /**
     * @param drawingPanel the drawingPanel to set
     */
    public void setDrawingPanel ( DrawingPanel drawingPanel )
    {
        this.drawingPanel = drawingPanel;
    }

    /**
     * @return the transitionPanel
     */
    public TransitionPanel getTransitionPanel ()
    {
        return transitionPanel;
    }

    /**
     * @param transitionPanel the transitionPanel to set
     */
    public void setTransitionPanel ( TransitionPanel transitionPanel )
    {
        this.transitionPanel = transitionPanel;
    }
}
