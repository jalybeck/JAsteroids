package fi.jasteroids.states;

import fi.cebylfwk.state.State;

import fi.jasteroids.entities.FullScreenImageEntity;

import java.io.IOException;

import java.net.URL;

import org.lwjgl.input.Keyboard;
/**
 * IntroState is the first state shown
 * when game is started.
 * This just shows fullscreen image.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         State
 */
public class IntroState extends State {
    public IntroState(String name) throws IOException {
        super(name);
        this.addEntity(new FullScreenImageEntity(new URL(this.getClass().getResource(".") + "data/intro.png")));
    }

    @Override
    public void processKeyboardInput() {
        if(Keyboard.getNumKeyboardEvents() > 0) {
            this.finished = true;
        }
    }

    @Override
    public void initialize() {
    }
}
