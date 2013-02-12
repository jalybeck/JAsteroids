package fi.cebylfwk.state;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Handles and maintains state transitions in the game.
 * LinkedList keeps states in order of insertion.
 *
 * TODO: Currently this class does not support more than one active
 * state. This does not matter in simple games, but for more complex
 * games this class must be changed.
 *
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class StateHandler {
    private LinkedList<State> states;
    private State currentState;
    private State previousState;
    
    public StateHandler() {
        states = new LinkedList<State>();
        currentState = null;
        previousState = null;
    }
    
    public void addState(State state) {
        states.add(state);
    }
    
    public State getCurrentState() {
        return currentState;
    }
    
    public State getPreviousState() {
        return previousState;
    }
    
    /** 
     * Game calls this every frame.
     * This method keeps track the state finish condition.
     * If state is finished next state from the linked list is activated and
     * finished state is released.
     * 
     * @return next active state. null if there are no more states.
     */
    public State getNextState() {
        try {
            if(currentState != null) {
                if(currentState.isFinished())  {
                    previousState = currentState;
                    currentState = states.removeFirst();
                    
                    currentState.initialize();
                    previousState.release();
                }
            } else {
                currentState = states.removeFirst();
                currentState.initialize();
            }
        } catch(NoSuchElementException e) {
            //no elements left return null
            return null;
        }
        return currentState;
    }
}
