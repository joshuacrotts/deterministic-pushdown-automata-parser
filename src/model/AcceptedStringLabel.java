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
public class AcceptedStringLabel extends JLabel
{
    private MainWindow mainWindow;

    public AcceptedStringLabel ( MainWindow window )
    {
        super.setText( "String is not accepted." );
        this.mainWindow = window;
    }

    @Override
    public void paintComponent ( Graphics g )
    {
        super.paintComponent( g );
        this.setText( "String accepted?: " + this.mainWindow.dpda.isCurrentStateFinal());
        this.mainWindow.buttonPanel.repaint();
    }
}
