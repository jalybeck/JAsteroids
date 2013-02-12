package fi.cebylfwk;

import java.nio.ByteBuffer;

/**
 * All game resources should implement this interface.
 * For example Image, Texture etc.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Resource {
    /**
     * Returns raw resource data.
     * 
     * @return ByteBuffer which could hold raw resource data.
     */
    public ByteBuffer getResourceData();
    
    /**
     * Releases resource.
     */
    public void release();
}
