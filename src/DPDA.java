
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class represents a deterministic pushdown finite automata.
 *
 * @author Joshua
 */
public class DPDA
{
    //
    //  Char for the alphabet to verify the user
    //  input against.
    //
    private char[]           alphabet        = null;

    //
    //  ArrayList of states.
    //
    private ArrayList<State> states          = null;

    //
    //  Accepting states.
    //
    private ArrayList<State> acceptingStates = null;

    //
    //  Stack where symbols from the transition
    //  rules/function are pushed and popped accordingly.
    //  Epsilons are not pushed nor popped.
    //
    private Stack<Character> stack           = null;

    private State            startState      = null;

    public DPDA ( int numOfStates )
    {
        this.stack           = new Stack<>();
        this.states          = new ArrayList<>();
        this.acceptingStates = new ArrayList<>();

        for ( int i = 0 ; i < numOfStates ; i++ )
        {
            this.states.add( new State( ( i + 1 ) ) );
        }
    }

    /**
     * Deterministically parses through the inputString to see if it meets
     * the criteria for this specific deterministic pushdown automata.
     * @param inputString
     * @return
     */
    public boolean acceptsString ( String inputString )
    {
        if ( inputString.isEmpty() || !this.hasValidSymbols( inputString ))
        {
            return false;
        }

        boolean          accepts      = false;
        State            currentState = this.startState;
        List<Transition> transitions  = null;
        Transition       transition   = null;

        //  Loop through each character in the string.
        for ( int i = 0 ; i < inputString.length() ; )
        {

            //  For each character, grab its corresponding state
            //  transition function key/values.
            transitions = currentState.getTransitions();
            int t;

            //  For every rule in the transition table, we need to verify
            //  that the state we're on, as well as the stack and the
            //  input char meet a certain criteria.
            for ( t = 0 ; t < transitions.size() ; t++ )
            {
                if ( currentState == this.getStateObject( transitions.get( t ).getFromState() )
                        && ( inputString.charAt( i ) == transitions.get( t ).getInputSymbol() || transitions.get( t ).getInputSymbol() == 'e' )
                        && ( ( !this.stack.isEmpty() && this.stack.peek() == transitions.get( t ).getPopSymbol() ) || transitions.get( t ).getPopSymbol() == 'e' ) )
                {
                    accepts = true;
                    break;
                }
            }

            //  If none of the rules above are satisifed, we automatically reject.
            if ( !accepts )
            {
                System.out.println( "Not accepted." );
                return false;
            }

            //  If the input symbol is NOT an epsilon, then we can advance the
            //  pointer in the string because this indicates we are not just
            //  transitioning in the char (we are actually either pushing or popping
            //  some other char.
            if ( transitions.get( t ).getInputSymbol() != 'e' )
            {
                i++;
            }

            //  If we are popping, then this symbol canNOT be epsilon.
            if ( transitions.get( t ).getPopSymbol() != 'e' )
            {
                if ( !this.stack.isEmpty() )
                {
                    this.stack.pop();
                }
                else
                {
                    System.out.println( "ERROR! Tried to pop an empty stack." );
                    return false;
                }
            }

            //  Finally, we update the currentState pointer.
            currentState = this.getStateObject( transitions.get( t ).getToStateID() );

            //  We also need to update the stack to push whatever the current
            //  transition function calls for.
            if ( transitions.get( t ).getPushSymbol() != 'e' )
            {
                this.push( transitions.get( t ).getPushSymbol() );
            }

            System.out.println( transitions.get( t ) + "\t\tStack: " + this.stack );
        }

        //  If there is one more transition left, we can just brute-force
        //  search for it until we hit it.
        transitions = currentState.getTransitions();

        for ( Transition t : transitions )
        {
            if ( currentState.getStateID() + 1 == t.getToStateID()
                    && t.getInputSymbol() == 'e'
                    && t.getPopSymbol() == '$'
                    && this.peek() == t.getPopSymbol() )
            {
                return true;
            }
        }

        return this.acceptingStates.contains( currentState );
    }

    /**
     * Iterate through the string. If any of its characters are not present in
     * the alphabet specified by the DPDA, we return false.
     *
     * This code is directly ripped from my DFA program.
     *
     * @param s
     * @return
     */
    public boolean hasValidSymbols (String s)
    {
        boolean hasValidChars;

        for ( int i = 0 ; i < s.length() ; i++ )
        {
            hasValidChars = false;
            char c = s.charAt( i );

            for ( int j = 0 ; j < this.alphabet.length ; j++ )
            {
                if ( s.charAt( i ) == this.alphabet[ j ] )
                {
                    //
                    //  If we find a matching character, we know we're good
                    //  for this char in the string.
                    //
                    hasValidChars = true;
                    break;
                }
            }

            //  If we don't find a match, return false early.
            if ( !hasValidChars )
            {
                return false;
            }
        }

        return true;
    }

    public char peek ()
    {
        return stack.peek();
    }

    public char pop ()
    {
        return stack.pop();
    }

    public void push ( char ch )
    {
        this.stack.push( ch );
    }

    public int getNumberOfStates ()
    {
        return this.states.size();
    }

    public void setAlphabet ( char[] alphabet )
    {
        this.alphabet = alphabet;
    }

    public char[] getAlphabet()
    {
        return this.alphabet;
    }

    public void setStartState ( int stateID )
    {
        this.startState = this.getStateObject( stateID );
    }

    public void setFinalStates ( ArrayList<State> finalStates )
    {
        this.acceptingStates = finalStates;
    }

    public State getStateObject ( int stateID )
    {
        for ( int i = 0 ; i < states.size() ; i++ )
        {
            State state = states.get( i );
            if ( state.getStateID() == stateID )
            {
                return state;
            }
        }

        return null;
    }
}
