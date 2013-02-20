package fi.cebylfwk.graphics;
/**
 * Font interface.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Font {
    void setCorrection(boolean on);

    int getWidth(String whatchars);

    int getHeight();

    int getLineHeight();

    void drawString(float x, float y, String whatchars, float scaleX, float scaleY);

    void drawString(float x, float y, String whatchars, float scaleX, float scaleY, int format);

    void drawString(float x, float y, String whatchars, int startIndex, int endIndex, float scaleX, float scaleY,
                    int format);

    void destroy();
}
