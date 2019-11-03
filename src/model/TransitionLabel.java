/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;
import javax.swing.JLabel;
import view.MainWindow;

/**
 *
 * @author Joshua
 */
public class TransitionLabel extends JLabel
{

    private MainWindow mainWindow;

    public TransitionLabel ( MainWindow window )
    {
        super.setText( "DPDA Parser" );
        this.mainWindow = window;
    }

    @Override
    public void paintComponent ( Graphics g )
    {
        super.paintComponent( g );
        if ( this.mainWindow.dpda.inputString == null || this.mainWindow.dpda.currTrans == null )
        {
            this.setText( "DPDA Parser" );
        }
        else
        {
            this.setText( "String: " + this.mainWindow.dpda.inputString + ", "
                    + this.mainWindow.dpda.currTrans + ", current string: "
                    + this.mainWindow.dpda.inputString.substring( this.mainWindow.dpda.charPos ) );
        }
    }
}
