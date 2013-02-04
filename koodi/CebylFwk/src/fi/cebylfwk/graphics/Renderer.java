package fi.cebylfwk.graphics;

import fi.cebylfwk.shape.Quad;
import fi.cebylfwk.shape.Shape2D;

/**
 * Interface for all the graphics rendering methods.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Renderer {
    public void drawImage(float x, float y, float xScale, float yScale, float rotAngle, Image img);
    public void drawFullScreenImage(Image img);
    public void clear(float r, float g, float b, float a);
    public void clear(float r, float g, float b);
}
