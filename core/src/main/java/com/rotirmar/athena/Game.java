package com.rotirmar.athena;

import MapTileByTile.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {

    private SpriteBatch batch;
    private ScreenAdapter screen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        /*screen = new Screen(batch);
        setScreen(screen);*/
        screen = new Screen(batch);
        setScreen(screen);
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
