package test.fi.cebylfwk.graphics;

import fi.cebylfwk.graphics.GameImage;
import fi.cebylfwk.lwjgl.RendererLWJGL;

import java.net.URL;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Manual tests for testing renderer methods.
 */
public class RendererLWJGLTest {
    public RendererLWJGLTest() {
        super();
    }
    
    public DisplayMode queryDisplayMode(int width, int height, int bpp) throws Exception {
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
    
    public void setDisplayMode(int width, int height, int bpp) throws LWJGLException, Exception {
        DisplayMode mode = queryDisplayMode(width,height,bpp);
        
        Display.setDisplayMode(mode);

        // Create a fullscreen window with 1:1 orthographic 2D projection (default)
        Display.setTitle("RendererLWJGLTest");
        Display.setFullscreen(false);

        // Enable vsync if we can (due to how OpenGL works, it cannot be guarenteed to always work)
        Display.setVSyncEnabled(true);

        // Create default display of 640x480
        Display.create();

        // Put the window into orthographic projection mode with 1:1 pixel ratio.
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, Display.getDisplayMode().getWidth(), 0.0,
                     Display.getDisplayMode().getHeight(), -1.0, 1.0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(),
                        Display.getDisplayMode().getHeight());

        
        //Set opengl states
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        

    }
    
    public void release() {
        Display.destroy();
    }
        
    public static void main(String[] args) throws LWJGLException, Exception {
        RendererLWJGLTest t = new RendererLWJGLTest();
        RendererLWJGL rend = new RendererLWJGL();
        final String testDataPath="testdata/";
        GameImage image = new GameImage(new URL(t.getClass().getResource(testDataPath) + "red16x16.png"));
        GameImage fullScreenImage = new GameImage(new URL(t.getClass().getResource(testDataPath) + "uvtestmap.png"));
        
        
        t.setDisplayMode(640,480,32);
        int rot=0;
        boolean finished = false;
        while(!finished) {
            
            if(Display.isCloseRequested() || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                finished = true;
            }
            
            rend.clear(0, 0, 0);
            rend.drawFullScreenImage(fullScreenImage);
            rend.drawImage(image.getWidth(), image.getHeight(), rot++, image);

            
            Display.update();
            Display.sync(60);
        }
        
        t.release();
    }
}
