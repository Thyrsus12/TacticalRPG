package Screens;

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


    @Override
    public void show() {
        found = new Image(Resources.FONDOSMENU);
        found.setSize(1080, 720);

        option1 = new Text(Resources.FUENTEMENU, 60, Color.BLACK, false);
        option1.setTexto("Nueva partida");
        option1.setPosition(400, 400);

        option2 = new Text(Resources.FUENTEMENU, 60, Color.BLACK, false);
        option2.setTexto("Cargar partida");
        option2.setPosition(400, 350);

        option3 = new Text(Resources.FUENTEMENU, 60, Color.BLACK, false);
        option3.setTexto("Opciones");
        option3.setPosition(450, 300);

        option4 = new Text(Resources.FUENTEMENU, 60, Color.BLACK, false);
        option4.setTexto("Salir");
        option4.setPosition(500, 250);

        //Creación de la fuente para las letras del menú


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
