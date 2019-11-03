/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import view.MainWindow;

/**
 *
 * @author Joshua
 */
public class ResetButton extends JButton implements ActionListener
{

    private MainWindow mainWindow;

    public ResetButton ( MainWindow window )
    {
        super.setText( "Reset Diagram" );
        this.mainWindow = window;
        this.addActionListener( this );
    }

    @Override
    public void actionPerformed ( ActionEvent _e )
    {
        this.mainWindow.dpda.resetDPDA();
        this.mainWindow.drawingPanel.repaint();
    }
}
