package Screens;

import Utilies.Image;
import Utilies.Render;
import Utilies.Resources;
import com.badlogic.gdx.Screen;

public class MenuScreen implements Screen {

    Image found;

    @Override
    public void show() {
        found = new Image(Resources.FONDOSMENU);
        found.setSize(1080, 720);
    }

    @Override
    public void render(float delta) {
        Render.batch.begin();
        found.draw();
        Render.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
