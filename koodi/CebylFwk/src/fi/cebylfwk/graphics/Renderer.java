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
}
