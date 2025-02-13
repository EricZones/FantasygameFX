// Created by Eric B. 08.12.2021 17:03
package de.ericzones.fantasygamefx.graphics;

import de.ericzones.fantasygamefx.Fantasygame;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.Hero;
import de.ericzones.fantasygamefx.gameplay.GameplayManager;
import de.ericzones.fantasygamefx.graphics.visuals.EndingField;
import de.ericzones.fantasygamefx.graphics.visuals.StartField;
import de.ericzones.fantasygamefx.graphics.visuals.gamefield.GameField;
import de.ericzones.fantasygamefx.graphics.visuals.InfoField;
import de.ericzones.fantasygamefx.graphics.visuals.PauseField;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class WindowManager extends Application {

    private static TextureManager textureManager;
    private static Stage stage;
    private static double height, width;

    private static GameField gameField;
    private static PauseField pauseField;
    private static InfoField infoField;
    private static EndingField endingField;
    private static StartField startField;

    private final String title = "Fantasygame";

    // Needed to launch the method "launch();" from JavaFX by the Fantasygame class
    public void launchWindow() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        WindowManager.stage = stage;
        // Lowering the game screen size to set it non-fullsize (Only 75% of the screen)
        height = Screen.getPrimary().getBounds().getHeight()*0.75;
        width = Screen.getPrimary().getBounds().getWidth()*0.75;
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane, width, height);
        stage.setTitle(title);
        stage.setScene(scene);
        // Set the game non-resizable
        stage.setResizable(false);
        // Adding a game close event, preventing inadvertently (aus versehen) closing the game
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        // Adding a game focus event, which pauses the game if you are currently active in another program
        stage.focusedProperty().addListener(getFocusWindowEvent());
        // Adding a key press event registering game actions
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new ControlListener().getKeyEventListener());
        // Set the stage always on top one time, otherwise you wouldn't be able to open another program while playing
        stage.setAlwaysOnTop(true);
        stage.show();
        stage.setAlwaysOnTop(false);
        prepareVisualizedGame(stackPane);
    }

    // Initializing every graphic/visual managing class and the classes for specific visual parts
    private void prepareVisualizedGame(StackPane stackPane) {
        textureManager = new TextureManager();
        // Set the icon of the game after initializing the texture manager
        stage.getIcons().add(textureManager.getTexture(TextureManager.TextureType.ICON));
        // Load the background of the whole game
        loadBackground(stackPane);
        GameplayManager gameplayManager = Fantasygame.getGameplayManager();
        // Setting the size of the game field in the middle with the sizes set in the gameplay manager
        gameField = new GameField(gameplayManager.getGameMap().getMaxWidth(), gameplayManager.getGameMap().getMaxHeight(), stackPane);
        pauseField = new PauseField(stackPane);
        infoField = new InfoField(stackPane);
        endingField = new EndingField(stackPane);
        startField = new StartField(stackPane);
    }

    // Method executed by the gameplay manager to show the ingame visuals not until the game is fully loaded
    public void startVisualizedGame(Hero hero) {
        gameField.show();
        infoField.show();
        // Update the information in the info panels next to the game field by the data of the hero
        infoField.setHeroType(hero.getHeroType().getName());
        infoField.setNickname(hero.getName());
        infoField.setLifePoints(hero.getLifePoints());
        infoField.setKills(hero.getKills());
        infoField.setAbilityTexture(hero.getAbility().getTexture());
        infoField.setAbilityStatusTexture(hero.getAbility().getStatusTexture());
    }

    public TextureManager getTextureManager() {
        return textureManager;
    }

    public PauseField getPauseField() {
        return pauseField;
    }

    public InfoField getInfoField() {
        return infoField;
    }

    public GameField getGameField() {
        return gameField;
    }

    public EndingField getEndingField() {
        return endingField;
    }

    public StartField getStartField() {
        return startField;
    }

    public Stage getStage() {
        return stage;
    }

    public String getTitle() {
        return title;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    // Setting up the window close event
    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fantasygame | quit game");
        alert.setContentText("Your progress won't be saved");
        alert.initOwner(stage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();
        if(res.isPresent()) {
            if(res.get().equals(ButtonType.CANCEL))
                event.consume();
            else if(res.get().equals(ButtonType.OK))
                System.exit(0);
        }
    }

    // Setting up the focus window event
    private ChangeListener<Boolean> getFocusWindowEvent() {
        return (observableValue, aBoolean, t1) -> {
            if(stage.isFocused()) {
                if(Fantasygame.getGameplayManager().isManuallyFreezed()) return;
                if(Fantasygame.getGameplayManager().isFreezed())
                    Fantasygame.getGameplayManager().resumeGame();
            } else {
                if(!Fantasygame.getGameplayManager().isFreezed())
                    Fantasygame.getGameplayManager().freezeGame(false);
            }
        };
    }

    private void loadBackground(StackPane stackPane) {
        ImageView image = new ImageView(textureManager.getTexture(TextureManager.TextureType.BACKGROUND));
        image.setFitHeight(height);
        image.setFitWidth(width);
        stackPane.getChildren().add(image);
    }

}
