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
    
    public void release() {
        Display.destroy();
    }
        
    public static void main(String[] args) throws LWJGLException, Exception {
        RendererLWJGLTest t = new RendererLWJGLTest();
        RendererLWJGL rend = new RendererLWJGL();
        final String testDataPath="testdata/";
        GameImage image = new GameImage(new URL(t.getClass().getResource(testDataPath) + "red16x16.png"));
        GameImage fullScreenImage = new GameImage(new URL(t.getClass().getResource(testDataPath) + "uvtestmap.png"));
        
        
        rend.initialize(1280,720,32,false,true);

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
