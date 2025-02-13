// Created by Eric B. 20.12.2021 19:16
package de.ericzones.fantasygamefx.graphics;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {

    private final Map<TextureType, Image> textures = new HashMap<>();

    // Loading all the game texture on startup and stop the game with a message if some are missing
    public TextureManager() {
        try {
            textures.put(TextureType.ICON, new Image("icon.png"));
            textures.put(TextureType.BACKGROUND, new Image("background.png"));
            textures.put(TextureType.BACKGROUND_INFO, new Image("background_info.jpg"));
            textures.put(TextureType.HELP_ICON, new Image("pause.png"));
            textures.put(TextureType.GAME_OVER_ICON, new Image("game_over.png"));
            textures.put(TextureType.GRASS, new Image("grass.jpg"));
            textures.put(TextureType.WARRIOR, new Image("warrior.png"));
            textures.put(TextureType.WIZARD, new Image("wizard.png"));
            textures.put(TextureType.MONSTER, new Image("monster.png"));
            textures.put(TextureType.ITEM_NONE, new Image("item_none.png"));
            textures.put(TextureType.ABILITY_WIZARD, new Image("ability_wizard.png"));
            textures.put(TextureType.ABILITY_WIZARD_ACTIVE, new Image("ability_wizard_active.png"));
            textures.put(TextureType.ABILITY_WARRIOR, new Image("ability_warrior.png"));
            textures.put(TextureType.ABILITY_WARRIOR_ACTIVE, new Image("ability_warrior_active.png"));
        } catch (IllegalArgumentException e) {
            System.out.println(" [✘] One or more texture images are missing in the game directory");
            System.exit(0);
        }
    }

    public Image getTexture(TextureType type) {
        return this.textures.get(type);
    }

    public enum TextureType {
        ICON,
        BACKGROUND,
        BACKGROUND_INFO,
        HELP_ICON,
        GAME_OVER_ICON,
        GRASS,
        WARRIOR,
        WIZARD,
        MONSTER,
        ITEM_NONE,
        ABILITY_WIZARD,
        ABILITY_WIZARD_ACTIVE,
        ABILITY_WARRIOR,
        ABILITY_WARRIOR_ACTIVE;
    }

}
