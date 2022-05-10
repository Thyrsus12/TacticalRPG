package Screens;

import Input_Output.Input;
import Utilies.Image;
import Utilies.Render;
import Utilies.Resources;
import Utilies.Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class MenuScreen implements Screen {

    Image found;
    Text option1, option2, option3, option4;
    Input i = new Input(this);
    float tiempo = 0;

    int opt = 1;


    @Override
    public void show() {
        found = new Image(Resources.FONDOSMENU);
        found.setSize(1080, 720);

        Gdx.input.setInputProcessor(i);

        option1 = new Text(Resources.FUENTEMENU, 60, Color.BLACK, false);
        option1.setTexto("Nueva partida");
        option1.setPosition(400, 400);

        option2 = new Text(Resources.FUENTEMENU, 60, Color.BLACK, false);
        option2.setTexto("Cargar partida");
        option2.setPosition(400, 300);

        option3 = new Text(Resources.FUENTEMENU, 60, Color.BLACK, false);
        option3.setTexto("Opciones");
        option3.setPosition(450, 200);

        option4 = new Text(Resources.FUENTEMENU, 60, Color.BLACK, false);
        option4.setTexto("Salir");
        option4.setPosition(500, 100);


    }

    @Override
    public void render(float delta) {
        Render.batch.begin();
        Render.limpiarPantalla();
        //found.draw();
        option1.draw();
        option2.draw();
        option3.draw();
        option4.draw();
        Render.batch.end();

        tiempo += delta;

        if (i.isDown()) {
            if (tiempo > 0.09f) {
                tiempo = 0;
                opt++;
                if (opt > 4) {
                    opt = 1;
                }
            }
        }
        if (opt == 1) {
            option1.setColor(Color.YELLOW);
            option2.setColor(Color.WHITE);
            option3.setColor(Color.WHITE);
            option4.setColor(Color.WHITE);
        } else if (opt == 2) {
            option1.setColor(Color.WHITE);
            option2.setColor(Color.YELLOW);
            option3.setColor(Color.WHITE);
            option4.setColor(Color.WHITE);
        } else if (opt == 3) {
            option1.setColor(Color.WHITE);
            option2.setColor(Color.WHITE);
            option3.setColor(Color.YELLOW);
            option4.setColor(Color.WHITE);
        } else if (opt == 4) {
            option1.setColor(Color.WHITE);
            option2.setColor(Color.WHITE);
            option3.setColor(Color.WHITE);
            option4.setColor(Color.YELLOW);
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

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }
}
