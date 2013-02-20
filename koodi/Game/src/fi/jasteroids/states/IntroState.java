package fi.jasteroids.states;

import fi.cebylfwk.state.State;

import fi.jasteroids.entities.FullScreenImageEntity;

import java.io.IOException;

import java.net.URL;

import java.util.Collections;
import java.util.Map;

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
        this.addEntity(new FullScreenImageEntity(this.getClass().getResource("data/intro.png")));
    }

    @Override
    public void processKeyboardInput() {
        if(Keyboard.getNumKeyboardEvents() > 0) {
            this.finished = true;
        } else {
            this.exhaustKeyboardBuffer();
        }
    }

    @Override
    public void initialize(Map<String, String> parameters) {
    }


    @Override
    public Map<String, String> getParametersForNextState() {
        return Collections.emptyMap();
    }
}
