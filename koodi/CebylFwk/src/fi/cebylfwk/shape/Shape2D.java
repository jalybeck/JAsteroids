package fi.cebylfwk.shape;

import fi.cebylfwk.Renderable;
import fi.cebylfwk.graphics.Texture;

/**
 * This represents visible renderable shape in
 * game developed with this framework.
 * For example sprite and rectangle could implement this
 * interface.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Shape2D extends Renderable {
    
    public void setTexture(Texture t, boolean expandToTextureSize);
    
    /** 
     * This method will bind the texture to current graphics context.
     * For example opengl context.
     */
    public void bindTexture();
    
    /** Color should be 32-bit with 4 bytes (RGBA) */
    public void setColor(float r, float g, float b, float a);
    
    /** Rotates shape on xy plane */
    public void rotate(float angle);
    
    /** Set shapes position on xy plane */
    public void setPosition(float x, float y);
    
    /** Scale object on xy plane */
    public void scale(float x, float y);
}
