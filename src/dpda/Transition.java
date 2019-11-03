package dpda;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import model.CircleComponent;

/**
 * This class represents the verbatim file structure for the DPDA; it has a
 * starting state, an ending state, the input symbol, the char tbe popped off
 * the stack, and a char to push onto the stack. NOTE: the latter 3 parameters
 * CAN be epsilon.
 *
 * @author Joshua
 */
public class Transition
{

    private final DPDA dpda;
    private final int fromState;
    private final int toStateID;
    private final char inputSymbol;
    private final char popChar;
    private final char pushChar;
    private boolean visited = false;

    private int yOffset = 0;

    public Transition ( DPDA dpda , int fromState , int toStateID , char inputSymbol , char popChar , char pushChar )
    {
        this.dpda = dpda;
        this.fromState = fromState;
        this.toStateID = toStateID;
        this.inputSymbol = inputSymbol;
        this.popChar = popChar;
        this.pushChar = pushChar;
    }

    public void paintComponent ( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
        g2.setColor( this.visited ? Color.BLUE : Color.BLACK );

        CircleComponent srcState = this.dpda.getStateObject( fromState ).getCircleComponent();
        CircleComponent destState = this.dpda.getStateObject( toStateID ).getCircleComponent();

        g2.drawLine( srcState.getX() + srcState.getWidth() ,
                srcState.getY() + srcState.getWidth() ,
                destState.getX() + srcState.getWidth() ,
                destState.getY() + srcState.getWidth() );

        int midPointX = ( srcState.getX() + destState.getX() ) >> 1;
        int midPointY = ( srcState.getY() + destState.getY() ) >> 1;

        g2.drawString( this.inputSymbol + ", " + this.popChar + " -> " + this.pushChar , midPointX , midPointY + yOffset );
    }

    public int getFromState ()
    {
        return this.fromState;
    }

    public char getPopSymbol ()
    {
        return this.popChar;
    }

    public char getPushSymbol ()
    {
        return this.pushChar;
    }

    public int getToStateID ()
    {
        return this.toStateID;
    }

    public char getInputSymbol ()
    {
        return this.inputSymbol;
    }

    public int getYOffset ()
    {
        return this.yOffset;
    }

    public void setYOffset ( int y )
    {
        this.yOffset += y;
    }

    public boolean isVisited ()
    {
        return this.visited;
    }

    public void setVisited ( boolean v )
    {
        this.visited = v;
    }

    @Override
    public String toString ()
    {
        return "Transition from State "
                + this.fromState
                + " to State "
                + this.toStateID + ": \t\t"
                + inputSymbol
                + ", "
                + this.popChar
                + " -> "
                + this.pushChar;
    }
}
