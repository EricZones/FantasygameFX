package de.ericbagus.fantasygamefx.graphics.visuals.gamefield;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.collectives.creatures.Creature;
import de.ericbagus.fantasygamefx.collectives.world.Location;
import de.ericbagus.fantasygamefx.graphics.TextureManager;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;

public class GameField {

    private final int rows, columns;
    private final List<GameFieldPart> gameFieldParts = new ArrayList<>(); // The multiple fields in the whole game field

    private GridPane gridPane; // The game field itself
    private Rectangle rectangle; // The border around the game field

    public GameField(int rows, int columns, StackPane stackPane) {
        this.rows = rows;
        this.columns = columns;
        createField(stackPane);
        // Hide the game field first, because at the beginning just the hero-selecting window is shown
        hide();
    }

    // Create the game field
    private void createField(StackPane stackPane) {
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        // Creating multiple fields of the game field (rows * columns)
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                GameFieldPart gameFieldPart = new GameFieldPart(new Location(i+1, j+1), Fantasygame.getWindowManager().getTextureManager().getTexture(TextureManager.TextureType.GRASS));
                // Setting the size of the game fields dynamic to the monitor resolution
                gameFieldPart.setSize((int) (Fantasygame.getWindowManager().getWidth()*0.0425));
                gridPane.add(gameFieldPart.getStackPane(), i, j);
                gameFieldParts.add(gameFieldPart);
            }
        }
        stackPane.getChildren().add(gridPane);
        // Setting the font size of the border around the game field dynamic to the monitor resolution
        if(Screen.getPrimary().getBounds().getWidth() == 2560)
            rectangle = new Rectangle(Fantasygame.getWindowManager().getWidth()*0.39, Fantasygame.getWindowManager().getWidth()*0.39);
        else
            rectangle = new Rectangle(Fantasygame.getWindowManager().getWidth()*0.395, Fantasygame.getWindowManager().getWidth()*0.395);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(5);
        rectangle.setArcWidth(15);
        rectangle.setArcHeight(15);
        rectangle.setFill(null);
        stackPane.getChildren().add(rectangle);
    }

    // Get a specific field in the game field with the location of a creature
    public GameFieldPart getFieldByLocation(Location location) {
        return gameFieldParts.stream().filter(gameFieldPart -> gameFieldPart.getLocation().getX() == location.getX() && gameFieldPart.getLocation().getY() == location.getY()).findFirst().orElse(null);
    }

    // Reset a game field (Remove the image of a creature)
    public void clearField(Location location) {
        getFieldByLocation(location).removeOverlayImage();
    }

    // Reset one game field and show a creature image on another one
    public void updateField(Location previousLocation, Creature creature) {
        getFieldByLocation(creature.getLocation()).addOverlayImage(Fantasygame.getWindowManager().getTextureManager().getTexture(creature.getTexture()));
        if(previousLocation != null)
            clearField(previousLocation);
    }

    // Show the whole game field and its parts
    public void show() {
        this.rectangle.setVisible(true);
        this.gridPane.setVisible(true);
        this.gameFieldParts.forEach(GameFieldPart::show);
    }

    // Hide the whole game field and its parts
    public void hide() {
        this.rectangle.setVisible(false);
        this.gridPane.setVisible(false);
        this.gameFieldParts.forEach(GameFieldPart::hide);
    }

}