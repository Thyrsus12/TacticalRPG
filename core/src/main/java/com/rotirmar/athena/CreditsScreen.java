package com.rotirmar.athena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CreditsScreen implements Screen {
    private final SpriteBatch batch;
    private final Texture credits;
    private final Texture button;
    private final int screenWidthThird;
    private final int screenHeightThird;
    private final Game game;
    private final MenuScreen menuScreen;

    public CreditsScreen(SpriteBatch batch, int screenWidthThird, int screenHeightThird, Game game, MenuScreen menuScreen) {
        this.batch = batch;
        this.screenWidthThird = screenWidthThird;
        this.screenHeightThird = screenHeightThird;
        this.game = game;
        this.menuScreen = menuScreen;
        credits = new Texture("credits/credits-blue.png");
        button = new Texture("credits/b4.png");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(credits, 0, 0, screenWidthThird, screenHeightThird);
        batch.draw(button, screenWidthThird / 3f, screenHeightThird / 70f, MenuScreen.buttonWidth, MenuScreen.buttonHeight);
        batch.end();

        mouseInput();
    }

    private void mouseInput() {
        int clickX = Gdx.input.getX();
        int clickY = Gdx.input.getY();
        if (Gdx.input.justTouched()) {
            if (clickX > screenWidthThird / 3f && clickX < screenWidthThird / 3f + MenuScreen.buttonWidth) {
                if (clickY > (screenHeightThird - (screenHeightThird / 70f + MenuScreen.buttonHeight)) && clickY < (screenHeightThird - (screenHeightThird / 70f))) {
                    game.setScreen(menuScreen);
                }
            }
        }
    }

    @Override
    public void show() {

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
