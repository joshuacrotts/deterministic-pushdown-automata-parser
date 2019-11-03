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
public class StepButton extends JButton implements ActionListener
{

    private MainWindow mainWindow;

    public StepButton ( MainWindow window )
    {
        super.setText( "Next Step" );
        this.mainWindow = window;
        this.addActionListener( this );
    }

    @Override
    public void actionPerformed ( ActionEvent _e )
    {
        this.mainWindow.dpda.stepString();
        this.mainWindow.drawingPanel.repaint();
    }
}
