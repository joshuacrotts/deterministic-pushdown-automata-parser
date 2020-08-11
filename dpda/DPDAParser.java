package dpda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainWindow;

/**
 * This class acts as the driver class for testing DPDAs.
 *
 * @author Joshua
 */
public class DPDAParser {

  private static BufferedReader lineReader;

  public static void main(String[] args) {
    MainWindow mainWindow = new MainWindow();
  }

  /**
   * Spawns the DPDA object.
   *
   * @param fileName
   * @return DPDA object with class properties filled.
   */
  public static DPDA createDPDA(String fileName) {
    if (!openFile(fileName)) {
      return null;
    }

    DPDA dpda = parseDPDAFile();

    return dpda;
  }

  /**
   * Opens the file with the DPDA information in it.
   *
   * @param fileName
   * @return true if we successfully open the file, false otherwise.
   */
  private static boolean openFile(String fileName) {
    try {
      lineReader = new BufferedReader(new FileReader(fileName));
    } catch (FileNotFoundException ex) {
      System.err.println("Error, file %s not found.\n");
      return false;
    }
    return true;
  }

  /**
   * Parses the DPDA file by adding the alphabet and state transitions.
   *
   * @return DPDA object.
   */
  private static DPDA parseDPDAFile() {
    String line = null;

    //  Read in comments and empty lines
    try {
      line = lineReader.readLine();
      while (line.contains("%") || line.isEmpty()) {
        line = lineReader.readLine();

      }
    } catch (IOException ex) {
      Logger.getLogger(DPDAParser.class
              .getName()).log(Level.SEVERE, null, ex);
    }

    //  Read in the number of states.
    if (line != null && !line.contains("Number of states:")) {
      return null;
    }
    //  Connect all of the data to the DPDA object.
    DPDA dpda = new DPDA(Integer.parseInt(line.substring(line.indexOf(": ") + 2)));
    dpda.setAlphabet(getDPDAAlphabet());
    parseDPDATransitions(dpda);
    dpda.setStartState(getDPDAStartState());
    dpda.setFinalStates(getDPDAFinalStates(dpda));

    return dpda;
  }

  private static void parseDPDATransitions(DPDA dpda) {
    String line = null;
    try {
      line = lineReader.readLine();

      //  If we're not on the right line, just quit.
      if (!line.contains("Transitions begin")) {
        return;
      }

      //  Otherwise, parse through every transition and add it
      //  appropriately.
      do {
        line = lineReader.readLine();
        //  Break if we are at the end.
        if (line.contains("Transitions end")) {
          break;
        }
        //  Split the line into array elements by the space delimiter.
        String[] inputData = line.split(" ");

        //  Read in the current state ID
        int currentStateID = Integer.parseInt(inputData[0]);

        //  Read in input symbol
        char inputSymbol = inputData[1].charAt(0);

        //  Read in the top of the stack (pop) symbol
        char popSymbol = inputData[2].charAt(0);

        //  Read in next state id
        int nextStateID = Integer.parseInt(inputData[3]);

        //  Read in push symbol
        char pushSymbol = inputData[4].charAt(0);

        //  Get the state from the DPDA and add its transition values.
        State state = dpda.getStateObject(currentStateID);
        state.addTransition(dpda, currentStateID, nextStateID, inputSymbol, popSymbol, pushSymbol);
      } while (!line.contains("Transitions end"));

    } catch (IOException ex) {
      Logger.getLogger(DPDAParser.class
              .getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Reads in the current line from the file reader object and gets the ID of
   * the start state.
   *
   * @return ID of start state.
   */
  private static int getDPDAStartState() {
    String line = null;

    try {
      line = lineReader.readLine();

    } catch (IOException ex) {
      Logger.getLogger(DPDAParser.class
              .getName()).log(Level.SEVERE, null, ex);
    }

    if (line != null && !line.contains("Start state:")) {
      return -1;
    }
    return Integer.parseInt(line.substring(line.indexOf(":") + 2));
  }

  /**
   * Reads in the IDs of all start states for this DPDA.
   *
   * @param dpda
   * @return
   */
  private static ArrayList<State> getDPDAFinalStates(DPDA dpda) {
    String line = null;
    ArrayList<State> finalStates = new ArrayList<>();

    try {
      line = lineReader.readLine();

    } catch (IOException ex) {
      Logger.getLogger(DPDAParser.class
              .getName()).log(Level.SEVERE, null, ex);
    }

    if (!line.contains("Accept states:")) {
      return null;
    }

    String[] stateString = line.substring(line.indexOf(":") + 2).split(" ");

    for (String _stateString : stateString) {
      finalStates.add(dpda.getStateObject(Integer.parseInt(_stateString)));
      dpda.getStateObject(Integer.parseInt(_stateString)).setFinal(true);
    }

    return finalStates;
  }

  private static char[] getDPDAAlphabet() {
    String line = null;
    char[] alphabet = null;

    try {
      line = lineReader.readLine();

    } catch (IOException ex) {
      Logger.getLogger(DPDAParser.class
              .getName()).log(Level.SEVERE, null, ex);
    }

    if (!line.contains("Alphabet:")) {
      return null;
    }
    //  Parse the remainder of the line as a string array.
    String[] alphabetStrings = line.substring(line.indexOf(":") + 2).split(" ");
    alphabet = new char[alphabetStrings.length];

    //  Copy the string over to a char array.
    for (int i = 0; i < alphabetStrings.length; i++) {
      alphabet[i] = alphabetStrings[i].charAt(0);
    }

    return alphabet;
  }
}
