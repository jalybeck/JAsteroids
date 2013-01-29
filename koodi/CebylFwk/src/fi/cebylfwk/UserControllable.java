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
    boolean isCurrent();
    
    void turnLeft(double angle);
    void turnRight(double angle);
    
    void accelerate(double value);
    void decelerate(double value);
}
