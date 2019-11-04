package view;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * This class allows the user to navigate to a file on their computer that
 * matches the DPDA-schema. Then, it is loaded into the frame for user-input.
 *
 * @author Joshua
 */
public class DPDAFileChooser extends JFileChooser
{
    private final MainWindow mainWindow;

    public DPDAFileChooser ( MainWindow mainWindow )
    {
        super();
        this.mainWindow = mainWindow;
        this.setCurrentDirectory( new File( System.getProperty( "user.home" ) ) );
    }

    public File openFile ()
    {
        JOptionPane.showMessageDialog( this.mainWindow, "Select a file with the DPDA scheme." );
        int result = this.showOpenDialog( this.mainWindow );
        if ( result == JFileChooser.APPROVE_OPTION )
        {
            // user selects a file
            File selectedFile = this.getSelectedFile();
            return selectedFile;
        }
        return null;
    }
}
