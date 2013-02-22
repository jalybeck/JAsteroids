package fi.cebylfwk.state;

import fi.cebylfwk.Renderable;
import fi.cebylfwk.Updateable;
import fi.cebylfwk.component.Entity;
import fi.cebylfwk.graphics.Color;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.manager.EntityManager;

import java.util.Map;

import org.lwjgl.input.Keyboard;

/**
 * Base class for every game state in the game.
 * State implementation classes should extend this.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public abstract class State implements Renderable, Updateable {
    /** State name. */
    private String name;
    /** Has state been finished? */
    protected boolean finished;
    /** EntityManager which holds all the entities for the state. */
    private EntityManager em;
    /** Should screen be cleared? */
    private boolean clearScreen;
    /** Clearing color. */
    private Color clearColor;
    /** States frames per second, which state is locked to. */
    private int FPS;

    private State() {
        em = new EntityManager();
        clearScreen = true;
        clearColor = new Color(0, 0, 0, 1);
    }

    /**
     * State initialization should happen here.
     * For example you can load resources or set OpenGL commands here.
     *
     * This is called first in StateHandler when state changes to this state.
     *
     *
     * @param parameters this should be populated by previous state so states can pass parameters from previous to next.
     * @see StateHandler
     */
    public abstract void initialize(Map<String, String> parameters);

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
    
    /**
     * Adds entity to this state.
     * 
     * @param e
     */
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
        if (clearScreen)
            r.clear(this.clearColor);
        em.render(r, time);
    }

    @Override
    public void update(long time) {
        processKeyboardInput();
        em.update(time);
    }
    
    /**
     * Releases all the resources.
     */
    public void release() {
        em.clear();
    }

    public void setFPS(int fps) {
        this.FPS = fps;
    }

    public int getFPS() {
        return FPS;
    }
    
    
    /**
     * Converts nanoseconds to seconds.
     * 
     * @param timeInNanos
     * @return Seconds
     */
    protected float fromNanosToSeconds(long timeInNanos) {
        return timeInNanos / 1000000000.0f;
    }
    
    /**
     * Exhaust keyboard buffer so there is no keyboard presses in buffer after this methdo call.
     */
    protected void exhaustKeyboardBuffer() {
        //Exhaust keyboard buffer by looping thru it
        while(Keyboard.next()) {
            int key = Keyboard.getEventKey();
        }
    }
    
    public abstract Map<String,String> getParametersForNextState();
    
    /**
     * Keyboard handling.
     */
    public abstract void processKeyboardInput();
}
