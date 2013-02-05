package fi.cebylfwk.lwjgl;

import fi.cebylfwk.Game;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.state.State;
import fi.cebylfwk.state.StateHandler;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * This is LWJGL implementation of Game interface.
 * This is abstract base class which can be used as
 * basis for the games.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public abstract class GameLWJGL implements Game {
    private StateHandler sth;
    private String gameTitle;
    private int frameRate;
    private int resX;
    private int resY;
    private boolean fullscreen;
    private boolean finished;
    
    Renderer renderer;
    private long time;
    private long lastFrameTime;

    public GameLWJGL() {
        super();
        sth = new StateHandler();
        renderer = new RendererLWJGL();
    }

    @Override
    public void initialize(int resX, int resY, boolean fullscreen, String gameTitle, int frameRate,
                           boolean vSync) throws Exception {
        this.gameTitle = gameTitle;
        this.frameRate = frameRate;
        this.resX = resX;
        this.resY = resY;
        this.fullscreen = fullscreen;

        // get color from desktop
        int desktopBPP = Display.getDisplayMode().getBitsPerPixel();
        
        // find a display mode
        DisplayMode mode = getDisplayMode(resX, resY, desktopBPP);

        Display.setDisplayMode(mode);

        Display.setTitle(gameTitle);
        Display.setFullscreen(fullscreen);

        // Enable vertical sync
        Display.setVSyncEnabled(vSync);

        //Create display with selected resolution with
        //setDisplayMode(mode)
        Display.create();

        //Create orthographic projection, so we can render our sprites and images as a 2D
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, Display.getDisplayMode().getWidth(), 0.0,
                     Display.getDisplayMode().getHeight(), -1.0, 1.0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(),
                        Display.getDisplayMode().getHeight());        
    }

    private DisplayMode getDisplayMode(int width, int height,
                                       int bpp) throws Exception {
        DisplayMode mode = null;

        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes();

            for (int i = 0; i < modes.length; i++) {
                if ((modes[i].getWidth() == width) &&
                    (modes[i].getHeight() == height)) {
                    mode = modes[i];
                    break;
                }
            }
        } catch (LWJGLException e) {
            throw new Exception(e);
        }

        if (mode == null) {
            throw new Exception("Unable to set screen resolution to: " +
                                width + "x" + height + "x" + bpp);
        }

        return mode;
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
                Display.update();
    
                if (Display.isCloseRequested()) {
                    this.finished = true;
                }
                else if (Display.isActive()) { //Window is on foreground
                    
                    st.update(delta);
                    st.render(this.renderer, delta);
                    
                    //Use framerate synchronization of LWJGL library
                    Display.sync(this.frameRate);
                }
                else { //window at background
                    st.update(delta);
    
                    if (Display.isVisible() || Display.isDirty()) {
                         st.render(this.renderer, delta);
                    }
                }
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
        sth.addState(st);
    }
}
