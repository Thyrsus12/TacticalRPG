package com.rotirmar.athena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class SplashScreen implements Screen {

    private SpriteBatch batch;
    private Game game;
    private Texture logo;
    private Sprite s;

    int screenWidthThird;
    int screenHeightThird;

    private boolean fadeInTerminated = false, terminated = false;
    private float a = 0;
    private float contTime = 0, timeWait = 1;
    private float contTimeTerminated = 0, timeTerminated = 1;

    public SplashScreen(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;

        Toolkit display = Toolkit.getDefaultToolkit();
        Dimension screenSize = display.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        screenWidthThird = (screenWidth / 3) * 2;
        screenHeightThird = (screenHeight / 3) * 2;
    }

    @Override
    public void show() {
        logo = new Texture("logo-splash-screen.png");
        s = new Sprite(logo);
        s.setBounds(0, 0, screenWidthThird, screenHeightThird);
        setTransparent(0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        s.draw(batch);
        batch.end();

        processFade();
    }

    private void processFade() {
        if (!fadeInTerminated) {
            a += 0.03f;
            if (a > 1) {
                a = 1;
                fadeInTerminated = true;
            }
        } else {
            contTime += 0.05f;
            if (contTime > timeWait) {
                a -= 0.03f;
                if (a < 0) {
                    a = 0;
                    terminated = true;
                }
            }
        }
        setTransparent(a);

        if (terminated) {
            contTimeTerminated += 0.1f;
            if (contTimeTerminated > timeTerminated) {
                game.setScreen(new MenuScreen(batch, game));
            }
        }
    }

    public void setTransparent(float a) {
        s.setAlpha(a);
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
