package fi.cebylfwk.state;

import fi.cebylfwk.Renderable;
import fi.cebylfwk.Updateable;

/**
 * Base class for every game state in the game.
 * State implementation classes should extend this.
 * 
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public abstract class State implements Renderable, Updateable {
    private String name;
    protected boolean finished;
    
    public State(String name) {
        this.name = name;
    }
    
    public boolean isFinished() {
        return this.finished;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public abstract void render(long time);

    @Override
    public abstract void update(long time);
}
