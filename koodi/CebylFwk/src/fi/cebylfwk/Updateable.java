package fi.cebylfwk;

/**
 * Every game component which has logic should implement this.
 * The update() method will be called periodically 
 * in games busy loop.
 * 
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Updateable {
    /**
     * Updates the game logic.
     * 
     * @param time Time can be used when updating the logic, so it runs same speed on every machine.
     */
    public void update(long time);
}
