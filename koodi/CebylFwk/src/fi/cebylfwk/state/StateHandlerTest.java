package fi.cebylfwk.state;

import java.lang.reflect.Field;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StateHandlerTest {
    StateHandler stateHandler;
    
    //Mock classes for testing StateHandler
    private class IntroStateMock extends State {

        private IntroStateMock(String name) {
            super(name);
        }

        @Override
        public void render(long time) {
            throw new UnsupportedOperationException("For StateHandlerTest - do not implement!");
        }

        @Override
        public void update(long time) {
            //Just do simple mockup finish condition
            if(time > 10) {
                this.finished = true;
            }
        }
    }
    
    private class MainGameStateMock extends State {

        private MainGameStateMock(String name) {
            super(name);
        }

        @Override
        public void render(long time) {
            throw new UnsupportedOperationException("For StateHandlerTest - do not implement!");
        }

        @Override
        public void update(long time) {
            //Just do simple mockup finish condition
            if(time > 1000) {
                this.finished = true;
            }
        }
    }
    
    private class GameOverStateMock extends State {

        private GameOverStateMock(String name) {
            super(name);
        }

        @Override
        public void render(long time) {
            throw new UnsupportedOperationException("For StateHandlerTest - do not implement!");
        }

        @Override
        public void update(long time) {
            throw new UnsupportedOperationException("For StateHandlerTest - do not implement!");
        }
    }    
    
    private Object getPrivateField(String name) throws NoSuchFieldException, IllegalAccessException {
        //Use reflection to access private field states
        Field field = stateHandler.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(stateHandler);
        
    }
    
    @Before
    public void setUp() throws Exception {
        stateHandler = new StateHandler();
        
        stateHandler.addState(new IntroStateMock("Intro"));
        stateHandler.addState(new MainGameStateMock("MainGameState"));
        stateHandler.addState(new GameOverStateMock("GameOverState"));
    }

    @After
    public void tearDown() throws Exception {
        stateHandler = null;
    }

    @Test
    public void testAddState() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        LinkedList<State> states = (LinkedList<State>)getPrivateField("states");        
        
        //States should be in order of insertion in LinkedList
        State introState = states.get(0);
        State mainGameState = states.get(1);
        State gameOverState = states.get(2);
        
        assertEquals("State at index 0 is not instance of IntroStateMock!",true,introState instanceof IntroStateMock);
        assertEquals("State at index 1 is not instance of MainGameStateMock!",true,mainGameState instanceof MainGameStateMock);
        assertEquals("State at index 2 is not instance of GameOverStateMock!",true,gameOverState instanceof GameOverStateMock);
    }
    
    @Test
    public void testGetNextState() {
        State state = stateHandler.getNextState();
        
        assertEquals("First state should be IntroStateMock!",true,state instanceof IntroStateMock);
        
        //Set time < 10 to test that finish condition wont trigger up
        state.update(5);
        state = stateHandler.getNextState();
        assertEquals("State should be IntroStateMock after calling state.update(5)!",true,state instanceof IntroStateMock);
        
        //Trigger finish condition by setting time to 100
        state.update(100);
        state = stateHandler.getNextState();
        assertEquals("State should be MainGameStateMock after calling state.update(100)!",true,state instanceof MainGameStateMock);
        
        //Trigger finish condition by setting time to 1000
        state.update(1100);
        state = stateHandler.getNextState();
        assertEquals("State should be GameOverStateMock after calling state.update(1100)!",true,state instanceof GameOverStateMock);        
        
    }
    
    @Test
    public void testGetCurrentState() {
        State state = stateHandler.getNextState();
        assertEquals("Current state should be IntroStateMock!",true,stateHandler.getCurrentState() instanceof IntroStateMock);
        
        //Set time < 10 to test that finish condition wont trigger up
        state.update(15);
        state = stateHandler.getNextState();
        
        assertEquals("getCurrentState() should return MainGameStateMock after calling state.update(15)!",true,stateHandler.getCurrentState() instanceof MainGameStateMock);
    }

    @Test
    public void testGetPreviousState() {
        //Get first state which is IntroStateMock
        State state = stateHandler.getNextState();
        
        //Set time < 10 to test that finish condition wont trigger up
        state.update(15);
        state = stateHandler.getNextState();
        
        assertEquals("getPreviousState() should return IntroStateMock after calling state.update(15)!",true,stateHandler.getPreviousState() instanceof IntroStateMock);

    }

}
