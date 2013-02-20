package fi.cebylfwk.state;

import fi.cebylfwk.Renderable;
import fi.cebylfwk.Updateable;
import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.Color;
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
    private boolean clearScreen;
    private Color clearColor;
    private int FPS;

    private State() {
        em = new EntityManager();
        clearScreen = true;
        clearColor = new Color(0,0,0,1);
    }
    
    /**
     * State initialization should happen here.
     * For example you can load resources or set OpenGL commands here.
     * 
     * This is called first in StateHandler when state changes to this state.
     * 
     * @see StateHandler
     */
    public abstract void initialize();

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
    
    public void setClearScreen(boolean flag) {
        this.clearScreen = flag;
    }
    
    public void setClearColor(Color color) {
        this.clearColor = color;
    }
    
    @Override
    public void render(Renderer r, long time) {
        if(clearScreen)
            r.clear(this.clearColor);
        em.render(r, time);
    }

    @Override
    public void update(long time) {
        processKeyboardInput();
        em.update(time);
    }
    
    public void release() {
        em.clear();
    }
    
    public void setFPS(int fps) {
        this.FPS = fps;
    }
    
    public int getFPS() {
        return FPS;
    }
    
    public abstract void processKeyboardInput();
}
