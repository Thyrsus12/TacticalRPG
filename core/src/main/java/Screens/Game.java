package Screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Utilies.Render;

public class Game extends com.badlogic.gdx.Game {

    private SplashScreen splashScreen;

    @Override
    public void create() {
        Render.batch = new SpriteBatch();
        this.setScreen(new SplashScreen(this));

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        Render.batch.dispose();
    }
}
