package com.rotirmar.athena;

import Screens.MenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {

    private SpriteBatch batch;
    private ScreenAdapter screen;


    @Override
    public void create() {
        batch = new SpriteBatch();
        screen = new Screen(batch);
        MenuScreen menuScreen = new MenuScreen(batch);
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
