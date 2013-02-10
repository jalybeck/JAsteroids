package fi.cebylfwk.lwjgl;

import fi.cebylfwk.graphics.Image;
import fi.cebylfwk.graphics.Renderer;

import java.util.HashMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * LWJGL implementation of Renderer interface.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class RendererLWJGL implements Renderer {
    private HashMap<Image,Integer> textureCache;
    private static float testx, testy;
    public RendererLWJGL() {
        super();
        this.textureCache = new HashMap<Image,Integer>();
    }

    @Override
    public void drawImage(float x, float y, float xScale, float yScale, float rotAngle, Image img) {
        internalDrawImage(x, y, xScale, yScale, rotAngle, img, true, true);
    }
    
    @Override
    public void drawImage(float x, float y, float rotAngle, Image img) {
        internalDrawImage(x, y, img.getWidth(), img.getHeight(), rotAngle, img, true, true);
    }

    @Override
    public void drawImage(float x, float y, Image img) {
        internalDrawImage(x, y, img.getWidth(), img.getHeight(), 0, img, true, false);
    }
    
    private void internalBindTexture(Image img) {
        //Bind texture and set texture parameters
        
        int textureID;
        /*
         * If texture is found from cache we dont need to load it from the RAM to VIDRAM anymore.
         * We just bind the texture and render the square.
         */
        if(this.textureCache.containsKey(img)) {
            textureID = this.textureCache.get(img);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            
        } else {
            textureID = GL11.glGenTextures();
          
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            System.out.println(img.getColorFormat());
            int glColFormat;
            switch(img.getColorFormat()) {
                case BYTE_ABGR:
                    glColFormat = GL11.GL_RGBA;
                    break;
                case BYTE_BGR:
                    glColFormat = GL11.GL_RGB;
                    break;
                default:
                    glColFormat = GL11.GL_RGBA;
            }
            
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB8, img.getWidth(), img.getHeight(), 0, glColFormat, GL11.GL_UNSIGNED_BYTE, img.getResourceData());
            
            this.textureCache.put(img,textureID);   
        }        
        
    }
    
    private void internalDrawImage(float x, float y, float xScale, float yScale, float rotAngle, Image img, boolean scale, boolean rotate) {
        
        this.internalBindTexture(img);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        GL11.glPushMatrix();
        
        //Put object back to upper corner
        //TODO: Abstract away the Display.getDisplayMode().getHeight()
        GL11.glTranslatef(0.0f + x,Display.getDisplayMode().getHeight() - y, 0.0f);
        
        // rotate square according to angle around z-axis
        // we negate the rotation angle to get clockwise rotation
        // in opengl positive is CCW (Counter Clock Wise)
        if(rotate)
            GL11.glRotatef(-rotAngle, 0, 0, 1.0f);
        
        // scale the square
        if(scale)
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
        //Set orthogonal projection to match display resolution
        GL11.glMatrixMode( GL11.GL_PROJECTION );
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glOrtho( 0, Display.getWidth(), 0, Display.getHeight(), -1, 1 );
        
        GL11.glMatrixMode( GL11.GL_MODELVIEW );
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        
        //Bind texture
        this.internalBindTexture(img);        

        // render the square
        GL11.glBegin(GL11.GL_QUADS);
        
        GL11.glTexCoord2f(0.0f, 0.0f); 
        GL11.glVertex2f(0.0f, Display.getHeight());
        
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(Display.getWidth(),Display.getHeight());
        
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(Display.getWidth(), 0.0f);
        
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(0.0f, 0.0f);
        
        GL11.glEnd();
        
        //Pop matrix states
        GL11.glMatrixMode( GL11.GL_PROJECTION );
        GL11.glPopMatrix();

        GL11.glMatrixMode( GL11.GL_MODELVIEW );
        GL11.glPopMatrix(); 

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
