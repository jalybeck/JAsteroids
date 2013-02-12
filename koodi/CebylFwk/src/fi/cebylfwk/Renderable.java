package fi.cebylfwk;

import fi.cebylfwk.graphics.Renderer;

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
    /**
     * Renders the given renderab object.
     * 
     * @param r Renderer which will have all the rendering commands.
     * @param time Time which could be used in interpolation in rendering.
     */
    public void render(Renderer r, long time);
}
