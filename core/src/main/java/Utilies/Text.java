package Utilies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Text {
    private BitmapFont font;
    private int x = 0, y = 0;
    private String text = "";

    public Text(String routeFont, int dimension, Color color, boolean shadow) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Resources.FUENTEMENU));
        FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = dimension;
        parameter.color = color;
        font = generator.generateFont(parameter);
    }

    public void draw() {
        font.draw(Render.batch, text, x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color) {
        font.setColor(color);
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTexto() {
        return text;
    }

    public void setTexto(String texto) {
        this.text = texto;
    }
}
