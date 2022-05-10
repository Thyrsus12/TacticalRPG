package Input_Output;

import Screens.MenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {

    private boolean up = false, down = false;
    private float temp;
    MenuScreen app;


    public Input(MenuScreen app) {
        this.app = app;
    }

    @Override
    public boolean keyDown(int keycode) {

        temp = app.getTiempo();
        temp = 0.09f;

        if (keycode == com.badlogic.gdx.Input.Keys.DOWN) {
            down = true;
        }
        if (keycode == com.badlogic.gdx.Input.Keys.UP) {
            up = true;
        }
        return false;

    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == com.badlogic.gdx.Input.Keys.DOWN) {
            down = false;
        }
        if (keycode == com.badlogic.gdx.Input.Keys.UP) {
            up = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }


}
