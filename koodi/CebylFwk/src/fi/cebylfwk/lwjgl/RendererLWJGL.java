package fi.cebylfwk.lwjgl;

import fi.cebylfwk.graphics.Color;
import fi.cebylfwk.graphics.Font;
import fi.cebylfwk.graphics.Image;
import fi.cebylfwk.graphics.Renderer;

import java.util.HashMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.EXTAbgr;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * LWJGL implementation of Renderer interface.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public class RendererLWJGL implements Renderer {
    
    /** Cache for the textures.
     *  So the textures wont be binded and loaded more than needed.
     */
    private HashMap<Image,Integer> textureCache;
    
    public RendererLWJGL() {
        super();
        textureCache = new HashMap<Image,Integer>();
    }
    
    /**
     * Queries display given display mode and returns it if found.
     * 
     * @param resX X resolution.
     * @param resY Y resolution.
     * @param bpp Bits per Pixel (16,24,32)
     * @return
     * @throws Exception
     */
    private DisplayMode queryDisplayMode(int resX, int resY, int bpp) throws Exception {
        DisplayMode mode = null;

        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes();

            for (int i = 0; i < modes.length; i++) {
                if ((modes[i].getWidth() == resX) &&
                    (modes[i].getHeight() == resY)) {
                    mode = modes[i];
                    break;
                }
            }
        } catch (LWJGLException e) {
            throw new Exception(e);
        }

        if (mode == null) {
            throw new Exception("Unable to set screen resolution to: " +
                                resX + "x" + resY + "x" + bpp);
        }

        return mode;        
    }
    
    @Override
    public void initialize(int resX, int resY, int bpp, boolean fullScreen, boolean vSync) throws Exception {
        DisplayMode mode = queryDisplayMode(resX,resY,bpp);
        
        Display.setDisplayMode(mode);

        // Create a fullscreen window with 1:1 orthographic 2D projection (default)
        Display.setFullscreen(fullScreen);

        // Enable vsync if we can (due to how OpenGL works, it cannot be guarenteed to always work)
        Display.setVSyncEnabled(vSync);

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
    
    @Override
    public void drawImage(float x, float y, float xScale, float yScale, float rotAngle, float txStart, float txEnd,
                          float tyStart, float tyEnd, Image img) {
        internalDrawImage(x, y, xScale, yScale, rotAngle, img, true, true, txStart, txEnd, tyStart, tyEnd);
    }
    
    @Override
    public void drawImage(float x, float y, float xScale, float yScale, float rotAngle, Image img) {
        float txStart = 0.0f;
        float txEnd = 1.0f;
        float tyStart = 0.0f;
        float tyEnd = 1.0f;
        
        internalDrawImage(x, y, xScale, yScale, rotAngle, img, true, true, txStart, txEnd, tyStart, tyEnd);
    }
    
    @Override
    public void drawImage(float x, float y, float rotAngle, Image img) {
        float txStart = 0.0f;
        float txEnd = 1.0f;
        float tyStart = 0.0f;
        float tyEnd = 1.0f;        
        internalDrawImage(x, y, img.getWidth(), img.getHeight(), rotAngle, img, true, true, txStart, txEnd, tyStart, tyEnd);
    }

    @Override
    public void drawImage(float x, float y, Image img) {
        float txStart = 0.0f;
        float txEnd = 1.0f;
        float tyStart = 0.0f;
        float tyEnd = 1.0f;
        
        internalDrawImage(x, y, img.getWidth(), img.getHeight(), 0, img, true, false, txStart, txEnd, tyStart, tyEnd);
    }
    
    /**
     * Used internally to bind the texture to current OpenGL context.
     * Also caches textures in hashmap.
     * 
     * @param img Texture image data.
     */
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
            
           // Linear Filtering
           GL11.glTexParameteri(GL11.GL_TEXTURE_2D, 
                                GL11.GL_TEXTURE_MIN_FILTER,
                                GL11.GL_LINEAR);
           GL11.glTexParameteri(GL11.GL_TEXTURE_2D, 
                                GL11.GL_TEXTURE_MAG_FILTER,
                                GL11.GL_LINEAR);
           
           GL11.glTexParameteri(GL11.GL_TEXTURE_2D, 
                                GL11.GL_TEXTURE_WRAP_S,
                                GL12.GL_CLAMP_TO_EDGE);
           GL11.glTexParameteri(GL11.GL_TEXTURE_2D, 
                                GL11.GL_TEXTURE_WRAP_T,
                                GL12.GL_CLAMP_TO_EDGE);
           
           int internalTextureformat = GL11.GL_RGBA;
           int textureFormat = GL12.GL_BGR;
           int texturePixelData = GL11.GL_UNSIGNED_BYTE;
           
           switch(img.getColorFormat()) {
               case BYTE_BGR:
                   textureFormat = GL12.GL_BGR;
                   texturePixelData = GL11.GL_UNSIGNED_BYTE;
                   break;
               case BYTE_ABGR:
                   textureFormat = EXTAbgr.GL_ABGR_EXT;
                   texturePixelData = GL11.GL_UNSIGNED_BYTE;
                   break;
               case INT_BGR:
                   textureFormat = GL12.GL_BGR;
                   texturePixelData = GL11.GL_UNSIGNED_INT;
                   break;
               case INT_ARGB:
                   textureFormat = GL12.GL_BGRA;
                   texturePixelData = GL11.GL_UNSIGNED_INT;
                   break;
           }
           
           // Load texture to OpenGL side
           GL11.glTexImage2D(GL11.GL_TEXTURE_2D,
                             0,
                             internalTextureformat,
                             img.getWidth(),
                             img.getHeight(),
                             0,
                             textureFormat,
                             texturePixelData,
                             img.getResourceData());    
            
            this.textureCache.put(img,textureID);   
        }        
        
    }
    
    /**
     * Used internally to draw the image.
     * Multipurpose image drawing method, which is used by
     * public draw methods.
     * 
     * 
     * @param x X Position of the image.
     * @param y Y position of the image.
     * @param xScale x scaling factor of the image.
     * @param yScale y scaling factor of the image.
     * @param rotAngle Rotation angle in degrees.
     * @param img Image data which should be drawn.
     * @param scale Is scaling enabled?
     * @param rotate Is rotation enabled?
     * @param uOffset x texture offset
     * @param vOffset y texture offset
     */
    private void internalDrawImage(float x, float y, float xScale, float yScale, float rotAngle, Image img, boolean scale, boolean rotate, float txStart, float txEnd, float tyStart, float tyEnd ) {
        GL11.glPushAttrib(GL11.GL_CURRENT_BIT);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
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
        
        internalRenderSquare(txStart, txEnd, tyStart, tyEnd);
        
        GL11.glPopMatrix();
        
        GL11.glPopAttrib();
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        
    }
    
    /**
     * Used internally render the square.
     * 
     * @param xStart x start position of texture coordinate.
     * @param xEnd   x ending position of texture coordinate.
     * @param yStart y start position of texture coordinate.
     * @param yEnd   y ending position of texture coordinate.
     */
    private void internalRenderSquare(float xStart, float xEnd, float yStart, float yEnd) {
        GL11.glBegin(GL11.GL_QUADS);
        
        GL11.glTexCoord2f(xStart, yStart); 
        GL11.glVertex2f(-1.0f, 1.0f);
        GL11.glTexCoord2f(xStart, yEnd);
        GL11.glVertex2f(-1.0f, -1.0f);
        GL11.glTexCoord2f(xEnd, yEnd);
        GL11.glVertex2f(1.0f, -1.0f);
        GL11.glTexCoord2f(xEnd, yStart);
        GL11.glVertex2f(1.0f, 1.0f);        
        
        GL11.glEnd();
    }
    
    @Override
    public void drawFullScreenImage(Image img) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPushAttrib(GL11.GL_CURRENT_BIT);
        
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
        GL11.glPopAttrib();
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }
    
    @Override
    public void drawLineRectangle(float x, float y, float width, float height, Color col) {
        float left = width * -1.0f;
        float top = height * 1.0f;
        float right = -left;
        float bottom = -top;
        GL11.glPushAttrib(GL11.GL_CURRENT_BIT);
        
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        GL11.glPushMatrix();
        
        GL11.glLoadIdentity();
        
        GL11.glTranslatef(0.0f + x,Display.getDisplayMode().getHeight() - y, 0.0f);
        
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glColor3f(col.getRed(),col.getGreen(),col.getBlue());
        GL11.glVertex2f(left, top);
        GL11.glVertex2f(left, bottom);
        GL11.glVertex2f(right, bottom);
        GL11.glVertex2f(right, top);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
        GL11.glPopMatrix();

        GL11.glPopAttrib();
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

    @Override
    public void clear(Color color) {
        this.clear(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha());
    }
    
    @Override
    public void drawString(String str, int xPos, int yPos, Font font, Color color) {
        
        GL11.glPushAttrib(GL11.GL_CURRENT_BIT);
        GL11.glBlendFunc(GL11.GL_ONE,GL11.GL_ONE);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, Display.getDisplayMode().getWidth(), 0.0,
                   Display.getDisplayMode().getHeight(), -1.0, 1.0);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(),
                      Display.getDisplayMode().getHeight());
        
        GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
        font.drawString(xPos, Display.getDisplayMode().getHeight() - font.getHeight() - yPos, str, 1.0f, 1.0f);
        
        // switch to projection mode
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        // switch back to modelview mode
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        //this.setDepthTest(true);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glDisable(GL11.GL_BLEND);
        
        GL11.glPopAttrib();
    }

}
