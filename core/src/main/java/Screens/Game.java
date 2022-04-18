package Screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {

    private SpriteBatch batch;
    private SplashScreen splashScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        splashScreen = new SplashScreen(batch);
        setScreen(splashScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
