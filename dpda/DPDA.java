package dpda;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class represents a deterministic pushdown finite automata.
 *
 * @author Joshua
 */
public class DPDA {

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
  private boolean hasEpsilonTransition = true;
  private String inputString;
  private State currState;
  private List<Transition> currTransList;
  private Transition currTrans;

  public DPDA(int numOfStates) {
    this.stack = new Stack<>();
    this.states = new ArrayList<>();
    this.acceptingStates = new ArrayList<>();

    for (int i = 1; i <= numOfStates; i++) {
      this.states.add(new State((i)));
    }
    this.states.get(0).setCurrent(true);
  }

  /**
   * Indicates one step in a DPDA transition. We take the current string, and
   * parse it one character at a time until there are no more chars to parse.
   *
   * @return
   */
  public boolean stepString() {
    //  Firstly, we go ahead and initialize a blank state to the starting one.
    if (this.currState == null) {
      this.currState = this.startState;
    }

    //  Next, if we don't have valid chars in the input string, just return false.
    if (!this.hasValidSymbols(this.inputString)) {
      this.setCharPos(this.getInputString().length() - 1);
      return false;
    }

    //  If we're at the end of our string, go here.
    //  There are two possible outcomes: 
    //  1. The string had zero chars to begin with and we check if the 
    //     start state is an accepting state.
    //  2. The string has > 0 chars, and we need to check and see if there
    //     exists an epsilon transition on the state that we're currently
    //     standing on. If there is, go to the next state.
    if (this.getCharPos() >= this.getInputString().length()) {

      //  Then, if we're currently ON the start state AND the start state is an accepting state
      //  IFF the string is empty, return true.
      if (this.currState == this.startState && this.acceptingStates.contains(this.startState)) {
        return true;
      }

      //  If there is one more transition left, we can just brute-force
      //  search for it until we hit it.
      this.currTransList = this.currState.getTransitions();
      for (Transition t : this.currTransList) {
        if (currState.getStateID() + 1 == t.getToStateID()
                && t.getInputSymbol() == 'e'
                && t.getPopSymbol() == '$'
                && this.peek() == t.getPopSymbol()) {
          t.setVisited(true);
          this.hasEpsilonTransition = false;
          this.setCurrTrans(t);
          this.currState = this.getStateObject(t.getToStateID());
          this.currState.setCurrent(true);
          this.deselectAllStates(this.currState);
          this.pop();

          return true;
        }
      }

      this.hasEpsilonTransition = false;
      return false;
    }

    boolean accepts = false;

    //  For each character, grab its corresponding state
    //  transition function key/values.
    this.currTransList = this.currState.getTransitions();
    int t;

    //  For every rule in the transition table, we need to verify
    //  that the state we're on, as well as the stack and the
    //  input char meet a certain criteria.
    for (t = 0; t < this.currTransList.size(); t++) {
      Transition curr = this.currTransList.get(t);
      if (this.currState == this.getStateObject(curr.getFromState())
              && (this.getInputString().charAt(this.getCharPos()) == curr.getInputSymbol() || curr.getInputSymbol() == 'e')
              && ((!this.stack.isEmpty() && this.stack.peek() == curr.getPopSymbol()) || curr.getPopSymbol() == 'e')) {
        accepts = true;
        break;
      }
    }

    //  If none of the rules above are satisifed, we automatically reject,
    //  we also indicate to the GUI that the charPos is at the end of the string,
    //  and thus cannot proceed any further.
    if (!accepts) {
      System.out.println("Not accepted.");
      this.setCharPos(this.inputString.length());

      return false;
    }

    //  Grabs the transition we just used and assigns it to our instance var.
    this.setCurrTrans(this.currTransList.get(t));

    //  If the input symbol is NOT an epsilon, then we can advance the
    //  pointer in the string because this indicates we are not just
    //  transitioning in the char (we are actually either pushing or popping
    //  some other char.
    if (this.currTrans.getInputSymbol() != 'e') {
      setCharPos(getCharPos() + 1);
    }

    //  If we are popping, then this symbol canNOT be epsilon.
    if (this.currTrans.getPopSymbol() != 'e') {
      if (!this.stack.isEmpty()) {
        this.stack.pop();
      } else {
        System.out.println("ERROR! Tried to pop an empty stack.");
        return false;
      }
    }

    //  Finally, we update the currentState pointer.
    this.currState = this.getStateObject(this.currTrans.getToStateID());

    //  We also need to update the stack to push whatever the current
    //  transition function calls for.
    if (this.currTrans.getPushSymbol() != 'e') {
      this.push(this.currTrans.getPushSymbol());
    }

    //  Sets the current transition to have been visited (thus marking it
    //  with a blue line to signify the path, deselects every node in the 
    //  graph and sets the current one to the orange color. Finally,
    //  we check to see if it's a final state.
    this.currTransList.get(t).setVisited(true);
    this.deselectAllStates(currState);
    this.currState.setCurrent(true);
    return this.acceptingStates.contains(currState);
  }

  /**
   * Iterate through the string. If any of its characters are not present in the
   * alphabet specified by the DPDA, we return false.
   *
   * This code is directly ripped from my DFA program.
   *
   * @param s
   * @return
   */
  public boolean hasValidSymbols(String s) {
    boolean hasValidChars;

    for (int i = 0; i < s.length(); i++) {
      hasValidChars = false;

      for (int j = 0; j < this.alphabet.length; j++) {
        if (s.charAt(i) == this.alphabet[j]) {
          //
          //  If we find a matching character, we know we're good
          //  for this char in the string.
          //
          hasValidChars = true;
          break;
        }
      }

      //  If we don't find a match, return false early.
      if (!hasValidChars) {
        return false;
      }
    }

    return true;
  }

  public char peek() {
    return stack.peek();
  }

  public char pop() {
    return stack.pop();
  }

  public void push(char ch) {
    this.stack.push(ch);
  }

  public State getStateObject(int stateID) {
    for (int i = 0; i < states.size(); i++) {
      State state = states.get(i);
      if (state.getStateID() == stateID) {
        return state;
      }
    }
    return null;
  }

  private void deselectAllStates(State currState) {
    for (int i = 0; i < this.states.size(); i++) {
      if (currState != this.states.get(i)) {
        this.states.get(i).setCurrent(false);
      }
    }
  }

  /**
   * Clears the stack, removes all blue transition lines, and sets the current
   * state to be the starting state.
   */
  public void resetDPDA() {
    this.stack.clear();
    for (State s : states) {
      s.setCurrent(false);
      List<Transition> transitions = s.getTransitions();
      for (Transition t : transitions) {
        t.setVisited(false);
      }
    }

    this.states.get(0).setCurrent(true);
    this.currTransList = null;
    this.setCurrTrans(null);
    this.currState = null;
    this.hasEpsilonTransition = true;
    this.setCharPos(0);
  }

  /**
   * If our current parsing string is empty, then we can display a check-mark
   * beside the label denoting if it is accepted or not.
   *
   * @return
   */
  public boolean doneParsing() {
    if (this.getInputString() == null) {
      return false;
    }
    return this.getInputString().substring(getCharPos()).isEmpty();
  }

  public Stack<Character> getStack() {
    return this.stack;
  }

  public int getStackSize() {
    return this.stack.size();
  }

  public int getNumberOfStates() {
    return this.states.size();
  }

  public void setAlphabet(char[] alphabet) {
    this.alphabet = alphabet;
  }

  public char[] getAlphabet() {
    return this.alphabet;
  }

  public void setStartState(int stateID) {
    this.startState = this.getStateObject(stateID);
  }

  public void setFinalStates(ArrayList<State> finalStates) {
    this.acceptingStates = finalStates;
  }

  public boolean isCurrentStateFinal() {
    return this.acceptingStates.contains(this.currState);
  }

  /**
   * @return the charPos
   */
  public int getCharPos() {
    return charPos;
  }

  /**
   * @param charPos the charPos to set
   */
  public void setCharPos(int charPos) {
    this.charPos = charPos;
  }

  /**
   * @return the currTrans
   */
  public Transition getCurrTrans() {
    return currTrans;
  }

  /**
   * @param currTrans the currTrans to set
   */
  public void setCurrTrans(Transition currTrans) {
    this.currTrans = currTrans;
  }

  /**
   * @return the inputString
   */
  public String getInputString() {
    return inputString;
  }

  /**
   * @param inputString the inputString to set
   */
  public void setInputString(String inputString) {
    this.inputString = inputString;
  }

  public boolean hasEpsilonTransition() {
    return this.hasEpsilonTransition;
  }
}
