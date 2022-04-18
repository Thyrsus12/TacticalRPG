package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rotirmar.athena.Image;

public class SplashScreen implements Screen {
    private SpriteBatch batch;
    boolean fadeInTerminated = false, teminated = false;
    float a = 0;
    float contTime = 0, timeWait = 5;
    float contTimeTerminated = 0, timeTerminated = 5;
    Image fund;


    public SplashScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        fund = new Image("little_owl.png");
        batch = new SpriteBatch();
        fund.setTransparent(1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        fund.draw();
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
                }
            }
        }
        fund.setTransparent(a);

        if (teminated) {
            contTimeTerminated += 0.1f;
            if (contTimeTerminated > timeTerminated) {
                System.out.println("Cambio pantalla");
            }
        }
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
