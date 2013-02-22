package fi.cebylfwk.graphics;
/**
 * Font interface.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Font {
    /**
     * Sets left/right correction.
     * 
     * @param on true|false
     */
    void setCorrection(boolean on);

    int getWidth(String whatchars);

    int getHeight();

    int getLineHeight();
    
    /**
     * Draws string to screen.
     * 
     * @param x starting position of string.
     * @param y starting position of string.
     * @param str String to be drawn.
     * @param scaleX x scaling factor.
     * @param scaleY y scaling factor.
     */
    void drawString(float x, float y, String str, float scaleX, float scaleY);
    
    /**
     * Draws string to screen.
     * 
     * @param x starting position of string.
     * @param y starting position of string.
     * @param str String to be drawn.
     * @param scaleX x scaling factor.
     * @param scaleY y scaling factor.
     * @param format alignment in screen, LEFT, CENTER, RIGHT
     */
    void drawString(float x, float y, String str, float scaleX, float scaleY, int format);

    /**
     * Draws string to screen.
     * 
     * @param x starting position of string.
     * @param y starting position of string.
     * @param str String to be drawn.
     * @param startIndex start index in string
     * @param endIndex end index in string
     * @param scaleX x scaling factor.
     * @param scaleY y scaling factor.
     * @param format alignment in screen, LEFT, CENTER, RIGHT
     */
    void drawString(float x, float y, String str, int startIndex, int endIndex, float scaleX, float scaleY,
                    int format);
    
    /**
     * Destroys object and frees resources.
     */
    void destroy();
}
