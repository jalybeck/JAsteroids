package fi.cebylfwk;

 /**
  * Base interface of the game.
  * Every new game can implement this or
  * abstract base class.
  * 
  * @author      Jari Lybeck
  * @version     %I%, %G%
  */
public interface Game {
    public void initialize(int resX, int resY, boolean fullscreen, String gameTitle,
                           int frameRate, boolean vSync) throws Exception;

    public void run();

    public void cleanup();
}
