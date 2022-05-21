package com.rotirmar.athena;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //GameScreen game = new GameScreen(batch);
        MenuScreen menuScreen = new MenuScreen(batch, this);
        setScreen(menuScreen);
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
