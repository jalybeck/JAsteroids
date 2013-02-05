package fi.cebylfwk.lwjgl;

import fi.cebylfwk.graphics.Image;
import fi.cebylfwk.graphics.Renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
/**
 * LWJGL implementation of Renderer interface.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class RendererLWJGL implements Renderer {
    public RendererLWJGL() {
        super();
    }

    @Override
    public void drawImage(float x, float y, float xScale, float yScale, float rotAngle, Image img) {
        GL11.glPushMatrix();
        
        //Put object back to upper corner
        //TODO: Abstract away the Display.getDisplayMode().getHeight()
        GL11.glTranslatef(0.0f + x,Display.getDisplayMode().getHeight() - y, 0.0f);
        
        // rotate square according to angle around z-axis
        // we negate the rotation angle to get clockwise rotation
        // in opengl positive is CCW (Counter Clock Wise)
        GL11.glRotatef(-rotAngle, 0, 0, 1.0f);
        
        // scale the square
        GL11.glScalef(xScale, yScale, 1.0f);    
        
        // render the square
        GL11.glBegin(GL11.GL_QUADS);
        
        GL11.glTexCoord2f(0.0f, 0.0f); 
        GL11.glVertex2f(-1.0f, 1.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(-1.0f, -1.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(1.0f, -1.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(1.0f, 1.0f);
        GL11.glEnd();
        
        GL11.glPopMatrix();          
    }

    @Override
    public void drawFullScreenImage(Image img) {
    }

    @Override
    public void clear(float r, float g, float b, float a) {
        GL11.glClearColor(r,g,b,a);
        // clear the screen
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
    }

    @Override
    public void clear(float r, float g, float b) {
        this.clear(r,g,b,1.0f);
    }
    
    public static void main(String[] args) throws LWJGLException {
        /*
        Display.setDisplayMode(mode);

        // Create a fullscreen window with 1:1 orthographic 2D projection (default)
        Display.setTitle(gameTitle);
        Display.setFullscreen(fullscreen);

        // Enable vsync if we can (due to how OpenGL works, it cannot be guarenteed to always work)
        Display.setVSyncEnabled(vSync);

        // Create default display of 640x480
        Display.create();

        // Put the window into orthographic projection mode with 1:1 pixel ratio.
        // We haven't used GLU here to do this to avoid an unnecessary dependency.
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, Display.getDisplayMode().getWidth(), 0.0,
                     Display.getDisplayMode().getHeight(), -1.0, 1.0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(),
                        Display.getDisplayMode().getHeight());


        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
        }
        Display.destroy();
*/
    }
}
