package fi.jasteroids.states;

import fi.cebylfwk.math.Point2D;
import fi.cebylfwk.state.State;

import fi.jasteroids.entities.ShipEntity;

import java.io.IOException;

import java.net.URL;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
/**
 * MainGameState is the actual state where the game is played.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         State
 */
public class MainGameState extends State {
    private ShipEntity player;
    public MainGameState(String name) throws IOException {
        super(name);
        player = new ShipEntity(new URL(this.getClass().getResource(".") + "data/ship.png"));
        
        player.setPosition(new Point2D(Display.getWidth() / 2, Display.getHeight() / 2));
        this.addEntity(player);
    }

    @Override
    public void processKeyboardInput() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            this.finished = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
            player.moveUp(0.1);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            player.moveLeft(1.0);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
            player.moveRight(1.0);
        }
    }

    @Override
    public void initialize() {
        //Set blending states so images alpha is used also.
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glFrontFace(GL11.GL_CCW);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glEnable(GL11.GL_CULL_FACE);
    }
}
