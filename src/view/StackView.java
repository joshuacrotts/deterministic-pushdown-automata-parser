package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

/**
 * This class is the visual representation of the stack for the corresponding
 * DPDA.
 *
 * @author Joshua
 */
public class StackView
{
    private final MainWindow mainFrame;
    private final int bottomStackXPos = 40;
    private final int bottomStackYPos = 600;

    private final int stackXPos = 20;
    private final int stackYPos = 50;

    public StackView ( MainWindow mainFrame )
    {
        this.mainFrame = mainFrame;
    }

    public void paintComponent ( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
        this.drawStackVisual( g2 );
        this.drawStackElements( g2 );
    }

    private void drawStackVisual ( Graphics2D g2 )
    {
        g2.setColor( Color.BLACK );
        Font oldFont = g2.getFont();
        g2.setFont( new Font( "Arial", Font.TRUETYPE_FONT, 15 ) );
        g2.drawString( "STACK", stackXPos + ( stackXPos + bottomStackXPos ) / 8, stackYPos - 10 );
        g2.drawRect( stackXPos, stackYPos, bottomStackXPos + stackXPos, bottomStackYPos );
    }

    private void drawStackElements ( Graphics2D g2 )
    {
        int charOffset = 0;

        Iterator it = this.mainFrame.getDpda().getStack().iterator();

        for ( int i = 0 ; i < this.mainFrame.getDpda().getStackSize() ; i++, charOffset += 20 )
        {
            g2.drawString( this.mainFrame.getDpda().getStack().get( i ).toString(), bottomStackXPos + 5, bottomStackYPos + 40 - charOffset );
        }
    }
}
