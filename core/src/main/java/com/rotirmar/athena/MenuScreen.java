package com.rotirmar.athena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import utilities.MenuJFrame;

import javax.swing.*;

public class MenuScreen extends JFrame implements Screen {

    private final int BUTTON_HEIGHT = 100;
    private final int BUTTON_WIDTH = 300;

    private SpriteBatch batch;
    private Game game;
    private Texture play;
    private Texture exit;
    private MenuJFrame menu;


    public MenuScreen(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;
        menu = new MenuJFrame();
        play = new Texture("playButtonTexture.png");
        exit = new Texture("exitButtonTexture.png");
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
    }

    private void mouseInput() {
        int clickX, clickY;
        clickX = Gdx.input.getX();
        clickY = Gdx.input.getY();
        if (Gdx.input.justTouched()) {
            if (clickX > 467 && clickX < 747) {
                if (clickY > 260 && clickY < 355) {
                    menu.setVisible(true);
                    menu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    //game.setScreen(new GameScreen(batch));
                    //this.dispose();
                } else if (clickY > 370 && clickY < 467) {
                    Gdx.app.exit();
                }
            }
            //System.out.println("X: " +  + " Y: " + Gdx.input.getY());
            /*game.setScreen(new GameScreen(batch));
            this.dispose();*/
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
