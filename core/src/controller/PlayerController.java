package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.*;

public class PlayerController  {
    // TODO Create event listener class
    private List<EventListener> listeners = new ArrayList<EventListener>();

    EventHandler<KeyEvent> keyboardInputs = pressedButton -> {
        /* TODO
        * Check what key was pressed
        * Do the correct action in model
        * Also signal Event to view
        * or just keep in memory what key is pressed
        * */
    };
}
