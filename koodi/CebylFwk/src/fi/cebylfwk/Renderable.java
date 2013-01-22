package fi.cebylfwk;

/**
 * Every game component which should be rendered to screen,
 * should implement this interface.
 * The render method will be called periodically in
 * games busy loop.
 * 
 * 
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Renderable {
    public void render(long time);
}
