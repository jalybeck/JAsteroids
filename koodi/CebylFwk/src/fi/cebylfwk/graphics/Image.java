package fi.cebylfwk.graphics;

import fi.cebylfwk.Resource;

/**
 * Every image resource should implement this
 * resource. This interface tries to abstract out the
 * picture handling.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Image extends Resource {
    /**
     * INT_RGB: RGB packed to integer, no alpha
     * INT_ARGB: ARGB packed to integer
     * INT_BGR: BGR packed to integer, no alpha
     * BYTE_BGR: BGR each component is 1 byte, no alpha
     * BYTE_ABGR: ABGR each component is 1 byte
     * BYTE_INDEXED: indexed color model
     */
    public enum ColorFormat { INT_RGB, INT_ARGB, INT_BGR, BYTE_BGR, BYTE_ABGR, BYTE_INDEXED, NOT_SUPPORTED };
    
    int getWidth();
    int getHeight();
    int getBytes();
    
    ColorFormat getColorFormat();
    
    /**
     * Returns new Image from given (x,y) position and width and height of current image.
     * 
     * @param x position in image
     * @param y position in image
     * @param width how many pixels in x direction
     * @param height how many pixels in y direction
     * @return Image
     */
    Image getImageRegion(int x, int y, int width, int height);
}
