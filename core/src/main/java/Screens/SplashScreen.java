package Screens;

import Utilies.Render;
import Utilies.Resources;
import com.badlogic.gdx.Screen;
import Utilies.Image;

public class SplashScreen implements Screen {
    boolean fadeInTerminated = false, teminated = false;
    float a = 0;
    float contTime = 0, timeWait = 5;
    float contTimeTerminated = 0, timeTerminated = 5;
    Image fund;
    Game game;

    public SplashScreen(Game game) {
        this.game = game;
    }


    @Override
    public void show() {
        fund = new Image(Resources.FONDOSSPLASH);
        fund.setTransparent(1);
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla();
        Render.batch.begin();
        fund.draw();
        Render.batch.end();
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
        fund.setTransparent(a);

        if (teminated) {
            contTimeTerminated += 0.1f;
            if (contTimeTerminated > timeTerminated) {
                game.setScreen(new MenuScreen());
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
