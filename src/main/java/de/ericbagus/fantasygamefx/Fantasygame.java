package de.ericbagus.fantasygamefx;

import de.ericbagus.fantasygamefx.gameplay.GameplayManager;
import de.ericbagus.fantasygamefx.graphics.WindowManager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Fantasygame {

    private static final String gameVersion = "0.5";
    private static final String timeZone = "+1";

    private static GameplayManager gameplayManager;
    private static WindowManager windowManager;

    public static void main(String[] args) {
        registerObjects();
    }

    private static void registerObjects() {
        // Initializing the manager of gameplay mechanics
        gameplayManager = new GameplayManager();
        // Initializing and launching the graphics/visualization (JavaFX)
        windowManager = new WindowManager();
        windowManager.launchWindow();
    }

    // Not necessary but useful
    public static String getCurrentDateTime() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm").format(LocalDateTime.now(ZoneId.of(timeZone)));
    }

    // Useful for game releases in the future for example
    public static String getGameVersion() {
        return gameVersion;
    }

    public static GameplayManager getGameplayManager() {
        return gameplayManager;
    }

    public static WindowManager getWindowManager() {
        return windowManager;
    }

}
