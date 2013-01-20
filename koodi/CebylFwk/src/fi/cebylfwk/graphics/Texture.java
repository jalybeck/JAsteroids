package fi.cebylfwk.graphics;

import fi.cebylfwk.Resource;

/**
 * Texture used by graphics adapter.
 * This is lowest level graphics object 
 * in this framework.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Texture extends Resource {
    
    public void set(String textureName, Image img);
    public String getTextureName();
    public Image getTextureImage();
    
    
    /** Activate texture for next draw calls */
    public void bind();
    
}