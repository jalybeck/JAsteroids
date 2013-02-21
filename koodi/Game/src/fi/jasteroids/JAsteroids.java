package fi.jasteroids;

import fi.cebylfwk.lwjgl.GameLWJGL;

import fi.jasteroids.states.HighScoreState;
import fi.jasteroids.states.IntroState;

import fi.jasteroids.states.MainGameState;

import java.io.IOException;

/**
 * JAsteroids main game class.
 * This is starting point of the game.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         GameLWJGL
 */

public class JAsteroids extends GameLWJGL {
    private int xRes, yRes, bpp;
    private boolean vSync;
    public JAsteroids(int xRes, int yRes, int bpp, boolean vSync) {
        super();
        
        this.xRes = xRes;
        this.yRes = yRes;
        this.bpp = bpp;
        this.vSync = vSync;
    }
    
    /**
     * Initializes the resolution and the games states.
     * 
     * @throws IOException
     * @throws Exception
     */
    public void initialize() throws IOException, Exception {
        this.initialize(this.xRes, this.yRes, this.bpp, false, "JAsteroids", 60, this.vSync);
        
        this.addState(new IntroState("First State"));
        this.addState(new MainGameState("Second State"));
        this.addState(new HighScoreState("Last State"));
    }
    
    public static void main(String[] args) throws IOException, Exception {
        JAsteroids asteroids = new JAsteroids(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Boolean.parseBoolean(args[3]));
        
        asteroids.initialize();
        
        asteroids.run();
        
        asteroids.cleanup();
        
    }

}
