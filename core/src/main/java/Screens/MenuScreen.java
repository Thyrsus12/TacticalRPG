package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import utilities.Button;
import utilities.ButtonExit;
import utilities.ButtonPlay;

public class MenuScreen implements Screen {

    private SpriteBatch batch;
    private Button exit, play;

    public MenuScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        Texture texture = new Texture("buttonExit.png");
        int centerY = Gdx.graphics.getHeight() / 2 - texture.getHeight() / 2;
        int centerX = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2;
        exit = new ButtonExit(centerX, centerY - 200);
        play = new ButtonPlay(centerX, centerY - 100);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        exit.update();
        play.update();

        batch.begin();
        exit.draw(batch);
        play.draw(batch);
        batch.end();
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
