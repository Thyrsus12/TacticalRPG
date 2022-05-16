package utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ButtonExit extends Button {

    public ButtonExit(int x, int y) {
        super(x, y);
        texture = new Texture("buttonExit.png");
    }

    @Override
    public void operation() {
        Gdx.app.exit();
    }
}
