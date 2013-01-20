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
    public ByteBuffer getResourceData();
    
    public void release();
}
