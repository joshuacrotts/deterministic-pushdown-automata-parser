
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a state for the DPDA algorithm.
 *
 * @author Joshua
 */
public class State
{
    private int              stateID     = -1;
    private boolean          isFinal     = false;
    private List<Transition> transitions = null;

    public State ( int stateID )
    {
        this.transitions = new ArrayList<>();
        this.stateID     = stateID;
    }

    /**
     * Adds a transition to the list of transitions for this respective state.
     * @param fromState
     * @param toState
     * @param inputSymbol
     * @param popChar
     * @param pushChar
     * @return
     */
    public State addTransition ( int fromState, int toState, char inputSymbol, char popChar, char pushChar )
    {
        this.transitions.add( new Transition( fromState, toState, inputSymbol, popChar, pushChar ) );
        return this;
    }

    /**
     * Retrieves the next transition object for a specific input symbol and char.
     * Both have to match one of the transitions inside of the list of transitions.
     * @param inputSymbol
     * @param popChar
     * @return
     */
    public Transition getNextTransition ( int inputSymbol , char popChar )
    {
        for ( int i = 0 ; i < transitions.size() ; i++ )
        {
            Transition trans = transitions.get( i );
            if ( trans.getPopSymbol() == popChar && trans.getInputSymbol() == inputSymbol )
            {
                return trans;
            }
        }
        return null;
    }

    public int getStateID ()
    {
        return stateID;
    }

    public void setStateID ( int _stateID )
    {
        this.stateID = _stateID;
    }

    public boolean isIsFinal ()
    {
        return isFinal;
    }

    public void setIsFinal ( boolean _isFinal )
    {
        this.isFinal = _isFinal;
    }

    public List<Transition> getTransitions ()
    {
        return transitions;
    }

    public void setTransitions ( List<Transition> _transitions )
    {
        this.transitions = _transitions;
    }

}
