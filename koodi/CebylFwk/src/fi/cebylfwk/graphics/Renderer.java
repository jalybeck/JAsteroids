package fi.cebylfwk.graphics;

/**
 * Interface for all the graphics rendering methods.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Renderer {
    
    /**
     * Initializes renderer with given resolution parameters.
     *
     * @param resX
     * @param resY
     * @param bpp bits per pixel
     */
    public void initialize(int resX, int resY, int bpp, boolean fullScreen, boolean vSync) throws Exception;
    /**
     * Draws image on 2D screen.
     *
     * @param x position on 2D screen
     * @param y position on 2D screen
     * @param xScale x scaling factor
     * @param yScale y scaling factor
     * @param rotAngle rotation angle in degrees 
     * @param img renderable image
     * @param txStart x start position in texture between 0.0f - 1.0f
     * @param txEnd x end position in texture between 0.0f - 1.0f
     * @param tyStart y start position in texture between 0.0f - 1.0f
     * @param tyEnd y end position in texture between 0.0f - 1.0f
     */
    public void drawImage(float x, float y, float xScale, float yScale, float rotAngle, float txStart, float txEnd, float tyStart, float tyEnd, Image img);
    
    /**
     * Draws image on 2D screen.
     *
     * @param x position on 2D screen
     * @param y position on 2D screen
     * @param xScale x scaling factor
     * @param yScale y scaling factor
     * @param rotAngle rotation angle in degrees 
     * @param img renderable image
     */
    public void drawImage(float x, float y, float xScale, float yScale, float rotAngle, Image img);
    
    /**
     * Draws image on 2D screen.
     *
     * @param x position on 2D screen
     * @param y position on 2D screen
     * @param rotAngle rotation angle in degrees 
     * @param img renderable image
     */
    public void drawImage(float x, float y, float rotAngle, Image img);
    
    
    /**
     *
     * @param x position on 2D screen
     * @param y position on 2D screen
     * @param img renderable image
     */
    public void drawImage(float x, float y, Image img);
    
    /**
     * Draws fullscreen image of parameter img.
     * 
     * @param img renderable image
     */
    public void drawFullScreenImage(Image img);
    
    /**
     * Draws line rectangle with given color.
     * 
     * @param x x position of upper left corner
     * @param y y position of upper left corner
     * @param width width of x side
     * @param height height of y side
     * @param col
     */
    public void drawLineRectangle(float x, float y, float width, float height, Color col);
    
    /**
     * Clears screen with given color and alpha.
     * 
     * @param r
     * @param g
     * @param b
     * @param a
     */
    public void clear(float r, float g, float b, float a);
    
    /**
     * Clears screen with given color. Alpha is preset to 1.0.
     *
     * @param r
     * @param g
     * @param b
     */
    public void clear(float r, float g, float b);
    /**
     * Clears screen with given color.
     *
     * @param color
     */
    public void clear(Color color);
    
    /**
     * Draws string of text using selected font and color.
     * 
     * @param str String of text
     * @param xPos X position of string
     * @param yPos Y position of string
     */
    public void drawString(String str, int xPos, int yPos, Font font, Color color);
}
