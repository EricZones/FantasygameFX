// Created by Eric B. 19.12.2021 20:33
package de.ericbagus.fantasygamefx.graphics.visuals;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.graphics.TextureManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class PauseField {

    private final Rectangle rectangle;
    private final Label label;
    private final ImageView image;

    public PauseField(StackPane stackPane) {
        this.rectangle = new Rectangle();
        rectangle.setFill(Color.WHITE);
        rectangle.setHeight(185);
        rectangle.setWidth(375);
        rectangle.setArcWidth(40);
        rectangle.setArcHeight(30);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        rectangle.setVisible(false);
        this.label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setText("Paused");
        label.setPrefSize(200, 75);
        label.setFont(new Font(50));
        label.setTranslateY(40);
        label.setVisible(false);
        image = new ImageView(Fantasygame.getWindowManager().getTextureManager().getTexture(TextureManager.TextureType.HELP_ICON));
        image.setFitHeight(64);
        image.setFitWidth(64);
        image.setTranslateY(-30);
        image.setVisible(false);
        stackPane.getChildren().add(rectangle);
        stackPane.getChildren().add(label);
        stackPane.getChildren().add(image);
    }

    public void show() {
        rectangle.setVisible(true);
        label.setVisible(true);
        image.setVisible(true);
        String pausedTitle = Fantasygame.getWindowManager().getTitle() + " (Paused)";
        Fantasygame.getWindowManager().getStage().setTitle(pausedTitle);
    }

    public void hide() {
        rectangle.setVisible(false);
        label.setVisible(false);
        image.setVisible(false);
        Fantasygame.getWindowManager().getStage().setTitle(Fantasygame.getWindowManager().getTitle());
    }

}
