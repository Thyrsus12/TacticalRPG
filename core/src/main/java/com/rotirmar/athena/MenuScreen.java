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

    public static int BUTTON_HEIGHT;
    public static int BUTTON_WIDTH;

    private SpriteBatch batch;
    private Game game;
    private Texture newMap;
    private Texture continu;
    private Texture credits;
    private Marco menu;
    private Texture backImage;
    private Animation animation;
    private float time = 0f;
    private int screenWidthThird;
    private int screenHeightThird;

    public MenuScreen(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;
        newMap = new Texture("b1.png");
        continu = new Texture("b2.png");
        credits = new Texture("b3.png");
        menu = new Marco();
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
        Dimension screenSize = miPantalla.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        screenHeightThird = (screenHeight / 3) * 2;
        screenWidthThird = (screenWidth / 3) * 2;
        //Cada boton ocupa 6.8% de alto y un 33.4% de la pantalla. Se hace una regla de tres para calcular su tamaño segun la pantalla actual
        BUTTON_HEIGHT = (int) (screenHeightThird * 0.068f);
        BUTTON_WIDTH = (int) (screenWidthThird * 0.334f);

        backImage = new Texture("fondo-menu.png");
        makeAnimation(backImage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime(); //Time until the last render
        TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(time, true);
        batch.begin();
        batch.draw(currentFrame, 0, 0, screenWidthThird, screenHeightThird);
        batch.draw(newMap, screenWidthThird / 3f, screenHeightThird / 1.55f, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.draw(continu, screenWidthThird / 3f, screenHeightThird / 1.8f, BUTTON_WIDTH, BUTTON_HEIGHT);
        batch.draw(credits, screenWidthThird / 3f, screenHeightThird / 2.15f, BUTTON_WIDTH, BUTTON_HEIGHT);

        batch.end();

        mouseInput();

        if (!menu.getVisible()) {
            ArrayList<Integer> numCharacters = menu.getNumCharacters();
            int mapSize = menu.getSizeMap();
            String mapType = menu.getTypeMap();
            game.setScreen(new GameScreen(batch, numCharacters, mapSize, mapType));
        }
    }

    //EL 0,0 de las coordenadas del raton estan arriba a la izquierda
    private void mouseInput() {
        int clickX, clickY;
        clickX = Gdx.input.getX();
        clickY = Gdx.input.getY();
        if (Gdx.input.justTouched()) {
            //Las coordenadas del eje X del boton empieza en su izquierda y termina en su izquiera mas la anchura del boton
            if (clickX > screenWidthThird / 3f && clickX < screenWidthThird / 3f + BUTTON_WIDTH) {
                /*Como las posiciones son las que se utilizan para pintar empiezan abajo-izquierda y las del raton empiezan arriba-izquierda
                 * para recoger clicks hayq ue invertirlas restando al total(altura pantalla) donde las quieres poner (posicion donde se pintan)*/
                if (clickY > (screenHeightThird - (screenHeightThird / 1.55f + BUTTON_HEIGHT)) && clickY < (screenHeightThird - (screenHeightThird / 1.55f))) {
                    menu.setVisible(true);
                    menu.setResizable(false);
                    menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else if (clickY > (screenHeightThird - (screenHeightThird / 1.8f + BUTTON_HEIGHT)) && clickY < (screenHeightThird - (screenHeightThird / 1.8f))) {
                    System.out.println("Continuar");
                } else if (clickY > (screenHeightThird - (screenHeightThird / 2.15f + BUTTON_HEIGHT)) && clickY < (screenHeightThird - (screenHeightThird / 2.15f))) {
                    game.setScreen(new CreditsScreen(batch, screenWidthThird, screenHeightThird, game, this));
                }
            }
        }
    }

    private void makeAnimation(Texture t) {
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
