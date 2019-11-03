
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

    private final int fromState;
    private final int toStateID;
    private final char inputSymbol;
    private final char popChar;
    private final char pushChar;

    public Transition ( int fromState , int toStateID , char inputSymbol , char popChar , char pushChar )
    {
        this.fromState = fromState;
        this.toStateID = toStateID;
        this.inputSymbol = inputSymbol;
        this.popChar = popChar;
        this.pushChar = pushChar;
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
