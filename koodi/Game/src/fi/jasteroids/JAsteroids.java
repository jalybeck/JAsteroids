package fi.jasteroids;

import fi.cebylfwk.lwjgl.GameLWJGL;

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
    public JAsteroids() {
        super();
    }
    
    /**
     * Initializes the resolution and the games states.
     * 
     * @throws IOException
     * @throws Exception
     */
    public void initialize() throws IOException, Exception {
        this.initialize(1280, 720, 32, false, "JAsteroids", 60, true);
        
        this.addState(new IntroState("First State"));
        this.addState(new MainGameState("Second State"));
    }
    
    public static void main(String[] args) throws IOException, Exception {
        JAsteroids asteroids = new JAsteroids();
        
        asteroids.initialize();
        
        asteroids.run();
        
        asteroids.cleanup();
        
    }

}
