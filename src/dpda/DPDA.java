package dpda;

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
    private char[] alphabet = null;

    //
    //  ArrayList of states.
    //
    private ArrayList<State> states = null;

    //
    //  Accepting states.
    //
    private ArrayList<State> acceptingStates = null;

    //
    //  Stack where symbols from the transition
    //  rules/function are pushed and popped accordingly.
    //  Epsilons are not pushed nor popped.
    //
    private Stack<Character> stack = null;

    private State startState = null;

    //
    //  Variables for stepping through the view.
    //
    private int charPos = 0;
    private State currState;
    private List<Transition> currTransList;
    private Transition currTrans;
    private String inputString;

    public DPDA ( int numOfStates )
    {
        this.stack = new Stack<>();
        this.states = new ArrayList<>();
        this.acceptingStates = new ArrayList<>();

        for ( int i = 0 ; i < numOfStates ; i++ )
        {
            this.states.add( new State( ( i + 1 ) ) );
        }
        this.states.get( 0 ).setCurrent( true );
    }

    public boolean stepString ()
    {
        if ( !this.hasValidSymbols( this.inputString ) )
        {
            this.setCharPos( this.getInputString().length() - 1 );
            return false;
        }
        if ( this.getCharPos() >= this.getInputString().length() )
        {
            //  If there is one more transition left, we can just brute-force
            //  search for it until we hit it.
            this.currTransList = this.currState.getTransitions();
            for ( Transition t : this.currTransList )
            {
                if ( currState.getStateID() + 1 == t.getToStateID()
                        && t.getInputSymbol() == 'e'
                        && t.getPopSymbol() == '$'
                        && this.peek() == t.getPopSymbol() )
                {
                    t.setVisited( true );
                    this.setCurrTrans( t );
                    this.currState = this.getStateObject( t.getToStateID() );
                    this.currState.setCurrent( true );
                    this.deselectAllStates( this.currState );
                    return true;
                }
            }

            return false;
        }

        if ( this.currState == null )
        {
            this.currState = this.startState;
        }

        boolean accepts = false;

        //  For each character, grab its corresponding state
        //  transition function key/values.
        this.currTransList = this.currState.getTransitions();
        int t;

        //  For every rule in the transition table, we need to verify
        //  that the state we're on, as well as the stack and the
        //  input char meet a certain criteria.
        for ( t = 0 ; t < this.currTransList.size() ; t++ )
        {
            if ( this.currState == this.getStateObject( this.currTransList.get( t ).getFromState() )
                    && ( this.getInputString().charAt( this.getCharPos() ) == this.currTransList.get( t ).getInputSymbol() || this.currTransList.get( t ).getInputSymbol() == 'e' )
                    && ( ( !this.stack.isEmpty() && this.stack.peek() == this.currTransList.get( t ).getPopSymbol() ) || this.currTransList.get( t ).getPopSymbol() == 'e' ) )
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

        this.setCurrTrans( this.currTransList.get( t ) );

        //  If the input symbol is NOT an epsilon, then we can advance the
        //  pointer in the string because this indicates we are not just
        //  transitioning in the char (we are actually either pushing or popping
        //  some other char.
        if ( currTransList.get( t ).getInputSymbol() != 'e' )
        {
            setCharPos( getCharPos() + 1 );
        }

        //  If we are popping, then this symbol canNOT be epsilon.
        if ( currTransList.get( t ).getPopSymbol() != 'e' )
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
        currState = this.getStateObject( currTransList.get( t ).getToStateID() );

        //  We also need to update the stack to push whatever the current
        //  transition function calls for.
        if ( currTransList.get( t ).getPushSymbol() != 'e' )
        {
            this.push( currTransList.get( t ).getPushSymbol() );
        }
        this.currTransList.get( t ).setVisited( true );
        this.deselectAllStates( currState );
        this.currState.setCurrent( true );
        return this.acceptingStates.contains( currState );
    }

    /**
     * Deterministically parses through the inputString to see if it meets the
     * criteria for this specific deterministic pushdown automata. This is for
     * the console-only parsing version where the user isn't allowed to go
     * step-by-step.
     *
     * @param inputString
     * @return
     */
    public boolean acceptsString ( String inputString )
    {
        if ( inputString.isEmpty() || !this.hasValidSymbols( inputString ) )
        {
            return false;
        }

        boolean accepts = false;
        State currentState = this.startState;
        List<Transition> transitions = null;
        Transition transition = null;

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
    public boolean hasValidSymbols ( String s )
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

    private void deselectAllStates ( State currState )
    {
        for ( int i = 0 ; i < this.states.size() ; i++ )
        {
            if ( currState != this.states.get( i ) )
            {
                this.states.get( i ).setCurrent( false );
            }
        }
    }

    public void resetDPDA ()
    {
        this.stack.clear();
        for ( State s : states )
        {
            s.setCurrent( false );
            List<Transition> transitions = s.getTransitions();
            for ( Transition t : transitions )
            {
                t.setVisited( false );
            }
        }

        this.states.get( 0 ).setCurrent( true );
        this.currTransList = null;
        this.setCurrTrans( null );
        this.currState = null;
        this.setCharPos( 0 );
    }

    /**
     * If our current parsing string is empty, then we can display a check-mark
     * beside the label denoting if it is accepted or not.
     *
     * @return
     */
    public boolean doneParsing ()
    {
        if ( this.getInputString() == null )
        {
            return false;
        }
        return this.getInputString().substring( getCharPos() ).isEmpty();
    }

    public Stack<Character> getStack ()
    {
        return this.stack;
    }

    public int getStackSize ()
    {
        return this.stack.size();
    }

    public int getNumberOfStates ()
    {
        return this.states.size();
    }

    public void setAlphabet ( char[] alphabet )
    {
        this.alphabet = alphabet;
    }

    public char[] getAlphabet ()
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

    public boolean isCurrentStateFinal ()
    {
        return this.acceptingStates.contains( this.currState );
    }

    /**
     * @return the charPos
     */
    public int getCharPos ()
    {
        return charPos;
    }

    /**
     * @param charPos the charPos to set
     */
    public void setCharPos ( int charPos )
    {
        this.charPos = charPos;
    }

    /**
     * @return the currTrans
     */
    public Transition getCurrTrans ()
    {
        return currTrans;
    }

    /**
     * @param currTrans the currTrans to set
     */
    public void setCurrTrans ( Transition currTrans )
    {
        this.currTrans = currTrans;
    }

    /**
     * @return the inputString
     */
    public String getInputString ()
    {
        return inputString;
    }

    /**
     * @param inputString the inputString to set
     */
    public void setInputString ( String inputString )
    {
        this.inputString = inputString;
    }
}
