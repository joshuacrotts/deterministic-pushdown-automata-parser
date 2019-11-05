package dpda;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
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
    //  Transition information regarding the DPDA moves.
    private final DPDA dpda;
    private final int fromState;
    private final int toStateID;
    private final char inputSymbol;
    private final char popChar;
    private final char pushChar;
    private boolean visited = false;

    //  Positioning information for tbe bend in the arcs.
    private int yOffset = 0;
    private static final int DEFAULT_Y_OFFSET = 20;
    private static final double BEND_POS_OFFSET_FACTOR = 1.5;
    private static final int CUBIC_X_OFFSET = 65;
    private static final int CUBIC_Y_OFFSET = 75;

    public static final char EPSILON = '\u03b5';

    public Transition ( DPDA dpda, int fromState, int toStateID, char inputSymbol, char popChar, char pushChar )
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
        CircleComponent srcState = this.dpda.getStateObject( this.fromState ).getCircleComponent();
        CircleComponent destState = this.dpda.getStateObject( this.toStateID ).getCircleComponent();

        //  Calculates the mid point of the line.
        int lineMidPointX = ( ( srcState.getX() + destState.getX() ) >> 1 ) + ( srcState.getWidth() << 1 );
        int lineMidPointY = ( ( srcState.getY() + destState.getY() ) >> 1 ) + ( srcState.getHeight() >> 1 );

        int bendPointOffset = this.yOffset == 0 ? -Transition.DEFAULT_Y_OFFSET : this.yOffset;

        //  If there is NOT a cycle on this transition...
        if ( this.fromState != this.toStateID )
        {
            QuadCurve2D line = new QuadCurve2D.Double();

            line.setCurve( srcState.getX() + srcState.getWidth(),
                    srcState.getY() + srcState.getHeight(), lineMidPointX,
                    lineMidPointY + bendPointOffset * Transition.BEND_POS_OFFSET_FACTOR,
                    destState.getX() + srcState.getWidth(),
                    destState.getY() + srcState.getHeight() );

            g2.draw( line );
        }
        else
        {
            //  This code draws the loopback (transition goes to itself) curve.
            //  If there are multiple transitions to the state from itself, we
            //  just keep increasing the curve side to differentiate between the
            //  transitions. It's not pretty, but it works.
            CubicCurve2D line = new CubicCurve2D.Double();

            int stateMidX = srcState.getX() + srcState.getWidth();
            int stateMidY = srcState.getY() + srcState.getHeight();

            line.setCurve( stateMidX, stateMidY, stateMidX - ( CUBIC_X_OFFSET + bendPointOffset ),
                    stateMidY - ( CUBIC_Y_OFFSET + bendPointOffset ), stateMidX + ( CUBIC_X_OFFSET + bendPointOffset ),
                    stateMidY - ( CUBIC_Y_OFFSET + bendPointOffset ), stateMidX, stateMidY );

            g2.draw( line );
        }

        //  Just to make it look pretty, we can draw the transitions on the
        //  lines of the DPDA. Further, we replace the "e" to an actual epsilon
        //  symbol in unicode.
        this.drawTransitionText( g2, lineMidPointX, lineMidPointY );
    }

    private void drawTransitionText ( Graphics2D g2, int midPointX, int midPointY )
    {
        g2.setColor( Color.BLACK );
        Font oldFont = g2.getFont();
        g2.setFont( new Font( "Arial", Font.TRUETYPE_FONT, 15 ) );
        String epsilonReplacedStr = ( this.inputSymbol + ", " + this.popChar + " \u2192 " + this.pushChar ).replaceAll( "e", "\u03b5" );
        g2.drawString( epsilonReplacedStr, midPointX, midPointY + yOffset );
        g2.setFont( oldFont );
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
                + ( this.fromState == 'e' ? EPSILON : fromState )
                + " to State "
                + this.toStateID + ": \t\t"
                + ( inputSymbol == 'e' ? EPSILON : inputSymbol )
                + ", "
                + ( this.popChar == 'e' ? EPSILON : this.popChar )
                + " \u2192 "
                + ( this.pushChar == 'e' ? EPSILON : this.pushChar );
    }
}
