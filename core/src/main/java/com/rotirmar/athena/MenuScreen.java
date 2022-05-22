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
    private Texture credits;
    private Marco menu;
    private Texture backImage;
    private Sprite s;
    private Animation animation;
    private float time = 0f;
    private int screenWidth;
    private int screenHeight;


    public MenuScreen(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;
        newMap = new Texture("b1.png");
        continu = new Texture("b2.png");
        credits = new Texture("b3.png");
        menu = new Marco();
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension screenSize = miPantalla.getScreenSize();
        screenHeight = screenSize.height;
        screenWidth = screenSize.width;
        //Cada boton ocupa 6.8% de alto y un 33.4% de la pantalla. Se hace una regla de tres para calcular su tamaño segun la pantalla actual
        BUTTON_HEIGHT = (int) (((screenHeight / 3) * 2) * 0.068f);
        BUTTON_WIDTH = (int) (((screenWidth / 3) * 2) * 0.334f);

        backImage = new Texture("centro.png");
        s = new Sprite(backImage);
        s.setSize((screenWidth / 3) * 2, (screenHeight / 3) * 2);
        makeAnimation(backImage);

        System.out.println(screenHeight / 2.3f + "pintando altura");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime(); //Time until the last render
        TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(time, true);
        batch.begin();
        batch.draw(currentFrame, 0, 0, (screenWidth / 3) * 2, (screenHeight / 3) * 2);
        //s.draw(batch);
        batch.draw(newMap, screenWidth / 4.5f, screenHeight / 2.3f, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.draw(continu, screenWidth / 4.5f, screenHeight / 2.7f, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.draw(credits, screenWidth / 4.5f, screenHeight / 3.3f, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.draw(new Texture("rojo.png"), screenWidth / 4.5f, screenHeight / 2.3f, 10, 10);
        batch.draw(new Texture("rojo.png"), screenWidth / 4.5f, screenHeight / 2.7f, 10, 10);
        batch.draw(new Texture("rojo.png"), screenWidth / 4.5f, screenHeight / 3.3f, 10, 10);
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

    //EL 0,0 de las coordenadas del raton estan arriba a la izquierda
    private void mouseInput() {
        int clickX, clickY;
        clickX = Gdx.input.getX();
        clickY = Gdx.input.getY();
        if (Gdx.input.justTouched()) {
            System.out.println(screenHeight + " altura pantalla");
            System.out.println(clickY + "coordenadas del raton");
            if (clickX > screenWidth / 4.5f && clickX < screenWidth / 4.5f + BUTTON_WIDTH) {
                if (clickY > screenHeight - (screenHeight / 2.3f) && clickY < screenHeight - (screenHeight / 2.3f + BUTTON_HEIGHT)) {
                    menu.setVisible(true);
                    menu.setResizable(false);
                    menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
                //game.setScreen(new GameScreen(batch));
                //this.dispose();
            } /*else if (clickY > 370 && clickY < 467) {
                System.out.println(menu.getNumCharacters());
                Gdx.app.exit();
            }*/
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
