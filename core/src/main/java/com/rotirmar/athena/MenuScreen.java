package com.rotirmar.athena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jFrame.Marco;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuScreen implements Screen {

    private int BUTTON_HEIGHT;
    private int BUTTON_WIDTH;

    private SpriteBatch batch;
    private Game game;
    private Texture newMap;
    private Texture continu;
    private Marco menu;
    private Texture backImage;
    private Sprite s;
    private Animation animation;
    private float time = 0f;


    public MenuScreen(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;
        newMap = new Texture("b1.png");
        continu = new Texture("b2.png");
        menu = new Marco();
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension screenSize = miPantalla.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        BUTTON_HEIGHT = (int) (((screenHeight / 3) * 2) * 0.068f);
        BUTTON_WIDTH = (int) (((screenWidth / 3) * 2) * 0.334f);

        backImage = new Texture("centro.png");
        s = new Sprite(backImage);
        s.setSize((screenWidth / 3) * 2, (screenHeight / 3) * 2);
        makeAnimation(backImage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension screenSize = miPantalla.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        time += Gdx.graphics.getDeltaTime(); //Time until the last render
        TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(time, true);
        batch.begin();
        batch.draw(currentFrame, 0, 0, (screenWidth / 3) * 2, (screenHeight / 3) * 2);
        //s.draw(batch);
        batch.draw(newMap, screenWidth / 4.5f, screenHeight / 2.3f, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.draw(continu, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 110, BUTTON_WIDTH, BUTTON_HEIGHT);
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
                    menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
                //game.setScreen(new GameScreen(batch));
                //this.dispose();
            } else if (clickY > 370 && clickY < 467) {
                System.out.println(menu.getNumCharacters());
                Gdx.app.exit();
            }
        }
    }

    private void makeAnimation(Texture t) {
        System.out.println(t.getWidth());
        int frames = t.getWidth() / 1920;
        TextureRegion[][] tmp = TextureRegion.split(t, t.getWidth() / frames, t.getHeight());
        TextureRegion[] regionMovement = new TextureRegion[frames];
        for (int i = 0; i < frames; i++) {
            regionMovement[i] = tmp[0][i];
        }
        animation = new Animation(0.3f, regionMovement);
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
