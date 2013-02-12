package fi.cebylfwk;

import fi.cebylfwk.state.State;

/**
 * Base interface of the game.
 * Every new game can implement this or
 * abstract base class.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 */
public interface Game {
    /**
     * Initializes game to given resolution.
     * This should be first method to be called.
     * 
     * @param resX x resolution.
     * @param resY y resolution.
     * @param bpp  bits per pixel (16,24,32)
     * @param fullscreen Fullscreen enabled.
     * @param gameTitle Game title which is shown on window border.
     * @param frameRate Framerate what this game is capped to.
     * @param vSync Vertical sync enabled, if true.
     * @throws Exception
     */
    public void initialize(int resX, int resY, int bpp, boolean fullscreen, String gameTitle,
                           int frameRate, boolean vSync) throws Exception;
    
    /**
     * This will start main game loop.
     */
    public void run();
    
    /**
     * This should be called when game is finished.
     * Here should be cleaned all the game resources.s
     */
    public void cleanup();
    
    /**
     * Adds state to the game. States are run on added order.
     * When one state finishes the next one will be run.
     * 
     * @param st State to be added.
     */
    public void addState(State st);
}
