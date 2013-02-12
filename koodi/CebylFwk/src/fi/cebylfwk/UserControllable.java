package fi.cebylfwk;

/**
 * Entities which should be controlled by user
 * should implement this interface.
 * This interface does not take into account the
 * which device is used to control the player(s).
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface UserControllable {
    /**
     * Return true, if this is currently active user controllable item.
     * @return true|false
     */
    boolean isCurrent();
    
    /**
     * Move Left logic should be put here.
     * 
     * @param value
     */
    void moveLeft(double value);
    
    /**
     * Move right logic should be put here.
     * 
     * @param value
     */
    void moveRight(double value);
    
    /**
     * Move up logic should be put here.
     * 
     * @param value
     */
    void moveUp(double value);
    
    /**
     * Move down logic should be put here.
     * @param value
     */
    void moveDown(double value);
}
