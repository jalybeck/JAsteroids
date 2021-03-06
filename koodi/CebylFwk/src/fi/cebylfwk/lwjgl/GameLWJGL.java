package fi.cebylfwk.lwjgl;

import fi.cebylfwk.Game;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.state.State;
import fi.cebylfwk.state.StateHandler;

import org.lwjgl.opengl.Display;

/**
 * This is LWJGL implementation of Game interface.
 * This is abstract base class which can be used as
 * basis for the games.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public abstract class GameLWJGL implements Game {
    /** StateHandler which keeps track of game states. */
    private StateHandler sth;
    
    /** Game title which is shown on game window. */
    private String gameTitle;
    
    /** Frames per seconds(FPS) to which game is locked to. */
    private int frameRate;
    
    /** X resolution. */
    private int resX;
    
    /** Y resolution. */
    private int resY;
    
    /** Is game running in fullscreen mode? */
    private boolean fullscreen;
    
    /** Has game been finished */
    private boolean finished;
    
    /** Renderer interface which is used for all the drawing. */
    Renderer renderer;
    
    /** Time in nanoseconds. */
    private long time;
    
    /** Time of lastframe in nanoseconds. */
    private long lastFrameTime;

    public GameLWJGL() {
        super();
        sth = new StateHandler();
        renderer = new RendererLWJGL();
    }

    @Override
    public void initialize(int resX, int resY, int bpp, boolean fullscreen, String gameTitle, int frameRate,
                           boolean vSync) throws Exception {
        this.gameTitle = gameTitle;
        this.frameRate = frameRate;
        this.resX = resX;
        this.resY = resY;
        this.fullscreen = fullscreen;
        
        
        // get color from desktop
        //int desktopBPP = Display.getDisplayMode().getBitsPerPixel();
        
        renderer.initialize(resX, resY, bpp, fullscreen, vSync);
        Display.setTitle(gameTitle);
    }
    
    @Override
    public void run() {
        while (!this.finished) {
            this.time = System.nanoTime();
            long delta = time - this.lastFrameTime;
            this.lastFrameTime = time;
            
            State st = sth.getNextState();
            
            if(st != null) {
                //Swaps offscreen buffer to visible one, polls keyboard and mouse.
                //Must be called atleast once per iteration.
            
                if (Display.isCloseRequested()) {
                    this.finished = true;
                }
                else if (Display.isActive()) { //Window is on foreground
                    
                    st.update(delta);
                    st.render(this.renderer, delta);

                }
                else { //window at background
                    st.update(delta);
    
                    if (Display.isVisible() || Display.isDirty()) {
                         st.render(this.renderer, delta);
                    }
                }
                Display.update();
                //Use framerate synchronization of LWJGL library
                Display.sync(st.getFPS());
            } else {
                this.finished = true;
            }
            
        }        
    }

    @Override
    public void cleanup() {
        Display.destroy();
    }

    @Override
    public void addState(State st) {
        if(st.getFPS()<=0) {
            st.setFPS(this.frameRate);
        }        
        sth.addState(st);
    }
}
