package utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RegionGiver {

    public static TextureRegion getRegion(Boolean selected, String name) {
        Texture blocks;
        if (!selected)
            blocks  = new Texture("blockTextures.png");
        else
            blocks  = new Texture("blockTexturesSelected.png");

        switch (name) {
            case "halfDarkMarble":
                return new TextureRegion(blocks, 32, 32);
            case "grass":
                return new TextureRegion(blocks, 224, 96, 32, 32);
            case "flowers":
                return new TextureRegion(blocks, 192, 128, 32, 32);
            case "water":
                return new TextureRegion(blocks, 192, 160, 32, 32);
            case "lava":
                return new TextureRegion(blocks, 256, 160, 32, 32);
            default:
                break;
        }
        return null;
    }
}