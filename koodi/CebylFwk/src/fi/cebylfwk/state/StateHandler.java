package fi.cebylfwk.state;

import java.util.LinkedList;

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
     * @return next active state. null if there are no more states.
     */
    public State getNextState() {
        if(currentState != null) {
            if(currentState.isFinished())  {
                previousState = currentState;
                currentState = states.removeFirst();
            }
        } else {
            currentState = states.removeFirst();
        }
        
        return currentState;
    }
}
