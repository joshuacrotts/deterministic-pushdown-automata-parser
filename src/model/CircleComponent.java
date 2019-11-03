package model;

import dpda.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import view.DrawingPanel;

/**
 *
 * @author Joshua
 */
public class CircleComponent
{

    private DrawingPanel parentPanel;
    private State parentStateObj;

    private int x;
    private int y;
    private static final int radius = 20;

    private final int FINAL_X_OFFSET = 3;
    private final int FINAL_Y_OFFSET = 3;

    public CircleComponent ( DrawingPanel panel , int x , int y , State parentStateObj )
    {
        this.x = x;
        this.y = y;
        this.parentPanel = panel;
        this.parentStateObj = parentStateObj;
        this.parentStateObj.setCircleComponent( this );
    }

    public void paintComponent ( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
        g2.setColor( this.parentStateObj.isCurrent() ? Color.ORANGE : Color.BLACK );
        g2.fillOval( this.x , this.y , this.radius , this.radius );

        if ( parentStateObj.isFinal() )
        {
            g2.drawOval( this.x - FINAL_X_OFFSET , this.y - FINAL_Y_OFFSET , radius + FINAL_X_OFFSET * 2 , radius + FINAL_Y_OFFSET * 2 );
        }

        g2.setColor( Color.WHITE );
        g2.drawString( "q" + this.parentStateObj.getStateID() , this.x + FINAL_X_OFFSET , this.y + FINAL_Y_OFFSET * 4 );
    }

    public int getWidth ()
    {
        return radius / 2;
    }

    public int getHeight ()
    {
        return radius / 2;
    }

    public int getX ()
    {
        return x;
    }

    public void setX ( int _x )
    {
        this.x = _x;
    }

    public int getY ()
    {
        return y;
    }

    public void setY ( int _y )
    {
        this.y = _y;
    }

}
