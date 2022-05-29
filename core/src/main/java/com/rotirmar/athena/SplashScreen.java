package com.rotirmar.athena;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {

    private SpriteBatch batch;
    private Game game;
    private Texture logo;
    private Sprite s;

    private boolean fadeInTerminated = false, teminated = false;
    private float a = 0;
    private float contTime = 0, timeWait = 5;
    private float contTimeTerminated = 0, timeTerminated = 5;

    public SplashScreen(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;
        logo = new Texture("logo-splash-screen.png");
        s = new Sprite(logo);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        //batch.draw(s, 0, 0);
        batch.end();
        processFade();
    }

    private void processFade() {
        if (!fadeInTerminated) {
            a += 0.01f;
            if (a > 1) {
                a = 1;
                fadeInTerminated = true;
            }
        } else {
            contTime += 0.05f;
            if (contTime > timeWait) {
                a -= 0.01f;
                if (a < 0) {
                    a = 0;
                    teminated = true;
                }
            }
        }
        setTransparent(a);

        if (teminated) {
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
