package fi.cebylfwk.state;

import fi.cebylfwk.Renderable;
import fi.cebylfwk.Updateable;
import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.manager.EntityManager;

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
    private EntityManager em;

    private State() {
        em = new EntityManager();
    }

    public State(String name) {
        this();
        this.name = name;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public String getName() {
        return name;
    }

    public void addEntity(Entity e) {
        this.em.addEntity(e);
    }

    @Override
    public void render(Renderer r, long time) {
        em.render(r, time);
    }

    @Override
    public void update(long time) {
        processKeyboardInput();
        em.update(time);
    }
    
    public abstract void processKeyboardInput();
}
