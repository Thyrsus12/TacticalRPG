package com.rotirmar.athena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jFrame.Marco;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuScreen implements Screen {

    private final int BUTTON_HEIGHT = 100;
    private final int BUTTON_WIDTH = 300;

    private SpriteBatch batch;
    private Game game;
    private Texture play;
    private Texture exit;
    private Marco menu;


    public MenuScreen(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;
        play = new Texture("playButtonTexture.png");
        exit = new Texture("exitButtonTexture.png");
        menu = new Marco();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(play, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.draw(exit, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 110, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.end();

        mouseInput();

        if (!menu.getVisible()) {
            ArrayList<Integer> numCharacters = menu.getNumCharacters();
            int mapSize = menu.getSizeMap();
            String mapType = menu.getTypeMap();
            System.out.println(mapSize);
            game.setScreen(new GameScreen(batch, numCharacters, mapSize, mapType));
        }
    }

    private void mouseInput() {
        int clickX, clickY;
        clickX = Gdx.input.getX();
        clickY = Gdx.input.getY();
        if (Gdx.input.justTouched()) {
            if (clickX > 467 && clickX < 747) {
                if (clickY > 260 && clickY < 355) {
                    menu.setVisible(true);
                    menu.setResizable(false);
                    menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                //game.setScreen(new GameScreen(batch));
                //this.dispose();
            } else if (clickY > 370 && clickY < 467) {
                System.out.println(menu.getNumCharacters());
                Gdx.app.exit();
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
