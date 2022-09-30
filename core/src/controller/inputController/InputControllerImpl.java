package controller.inputController;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import utilities.InputCommands;

public class InputControllerImpl implements InputController{
    @Override
    public void changeScene(Scene scene) {

    }

    @Override
    public void resetState() {

    }

    @Override
    public KeyCode getKeyMapped(InputCommands command) {
        return null;
    }

    @Override
    public void addCommandKeys(KeyCode key, InputCommands command) {

    }

    @Override
    public void isKeyPressed(KeyCode key) {

    }

    @Override
    public boolean isTaskActive(InputCommands task) {
        return false;
    }

    @Override
    public void setKeyState(KeyCode key, boolean state) {

    }

    @Override
    public void updatePlayerTasks() {

    }
}
