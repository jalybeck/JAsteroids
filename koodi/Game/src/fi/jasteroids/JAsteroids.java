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
    /** X resolution of the game. */
    private int xRes;
    /** Y resolution of the game. */
    private int yRes;
    /** Bits per pixel. */
    private int bpp;
    /** Is verticl sync enabled? */
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
    
    /**
     * Checks validity of command line parameters.
     * 
     * @param args
     */
    public static void checkCMDParams(String[] args) {
        boolean showUsage = false;
        if(args.length < 4) {

            showUsage = true;
        } else {
            for(int i=0;i<3;i++) {
                try {
                    int n = Integer.parseInt(args[i]);
                } catch(NumberFormatException e) {
                    showUsage = true;
                }
            }
            
                boolean b = Boolean.parseBoolean(args[3]);
           
        }
        if(showUsage) {
            showUsage();
            System.exit(0);
        }
    }
    
    /**
     * Shows valid command line parameters to start the game.
     */
    public static void showUsage() {
        System.out.println("JAsteroids v.0.9 (c) Jari Lybeck");
        System.out.println("Usage: java -jar -Djava.library.path=lib/lwjgl-2.8.3/native/<os_dir> -jar JAsteroids <xres> <yres> <bpp> <vSync>");
        System.out.println(" <os_dir>: windows, macosx, linux");
        System.out.println(" <xres>: x resolution");
        System.out.println(" <yres>: y resolution");
        System.out.println(" <bpp>: bits per pixel. 16,24,32");
        System.out.println(" <vSync>: is Vertical Sync enabled. true|false");
    }
    
    public static void main(String[] args) throws IOException, Exception {
        checkCMDParams(args);
        JAsteroids asteroids = new JAsteroids(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Boolean.parseBoolean(args[3]));
        
        asteroids.initialize();
        
        asteroids.run();
        
        asteroids.cleanup();
        
    }

}
