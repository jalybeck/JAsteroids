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
    public void update(long time);
}
