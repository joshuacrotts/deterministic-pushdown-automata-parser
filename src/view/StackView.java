/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

/**
 *
 * @author Joshua
 */
public class StackView
{

    private final MainWindow mainFrame;
    private final int bottomStackXPos = 40;
    private final int bottomStackYPos = 600;
    
    private final int stackXPos = 20;
    private final int stackYPos = 400;

    public StackView ( MainWindow mainFrame )
    {
        this.mainFrame = mainFrame;
    }

    public void paintComponent ( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
        g2.setColor( Color.BLACK );

        g2.drawRect( stackXPos , stackYPos , bottomStackXPos + stackXPos , stackYPos - 120 );
        this.drawStackElements( g );
    }

    public void drawStackElements ( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;

        int charOffset = 0;

        Iterator it = this.mainFrame.dpda.getStack().iterator();

        for ( int i = 0 ; i < this.mainFrame.dpda.getStackSize() ; i++ , charOffset += 20 )
        {
            System.out.println( "here fuker" );
            g2.drawString( this.mainFrame.dpda.getStack().get( i ).toString() , bottomStackXPos , bottomStackYPos - charOffset );
        }
    }
}
