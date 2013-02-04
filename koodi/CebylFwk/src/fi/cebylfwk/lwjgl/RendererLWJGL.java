package fi.cebylfwk.lwjgl;

import fi.cebylfwk.graphics.Image;
import fi.cebylfwk.graphics.Renderer;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class RendererLWJGL implements Renderer {
    public RendererLWJGL() {
        super();
    }

    @Override
    public void drawImage(float x, float y, float xScale, float yScale, float rotAngle, Image img) {
        GL11.glPushMatrix();
        
        //Put object back to upper corner
        //TODO: Abstract away the Disay.getDisplayMode().getHeight()
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
}
