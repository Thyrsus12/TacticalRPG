package com.rotirmar.athena;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        SplashScreen splashScreen = new SplashScreen(batch, this);
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
