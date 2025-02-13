// Created by Eric B. 31.12.2021 18:25
package de.ericzones.fantasygamefx.graphics.visuals;

import de.ericzones.fantasygamefx.Fantasygame;
import de.ericzones.fantasygamefx.graphics.TextureManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class EndingField {

    private final Rectangle rectangle;
    private final Label label;
    private final ImageView image;
    private final Button button;
    private final Rectangle buttonBorder;

    public EndingField(StackPane stackPane) {
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
        label.setText("Game Over");
        label.setPrefSize(400, 75);
        label.setFont(new Font(50));
        label.setTranslateY(40);
        label.setVisible(false);
        image = new ImageView(Fantasygame.getWindowManager().getTextureManager().getTexture(TextureManager.TextureType.GAME_OVER_ICON));
        image.setFitHeight(64);
        image.setFitWidth(64);
        image.setTranslateY(-30);
        image.setVisible(false);
        this.button = new Button();
        button.setVisible(false);
        button.setTranslateY(180);
        button.setAlignment(Pos.CENTER);
        button.setText("Play Again");
        button.setFont(new Font(30));
        button.setStyle("-fx-background-color: white;");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setStyle("-fx-background-color: #E5E5E5;");
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setStyle("-fx-background-color: white;");
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hide();
                Fantasygame.getGameplayManager().restartGame();
            }
        });
        button.setPrefWidth(200);
        button.setPrefHeight(75);
        Rectangle clip = new Rectangle(button.getPrefWidth(), button.getPrefHeight());
        clip.setArcWidth(25);
        clip.setArcHeight(20);
        button.setClip(clip);
        this.buttonBorder = new Rectangle(button.getPrefWidth(), button.getPrefHeight());
        buttonBorder.setFill(null);
        buttonBorder.setStroke(Color.BLACK);
        buttonBorder.setStrokeWidth(3);
        buttonBorder.setArcWidth(25);
        buttonBorder.setArcHeight(20);
        buttonBorder.setVisible(false);
        buttonBorder.setTranslateY(button.getTranslateY());

        stackPane.getChildren().add(rectangle);
        stackPane.getChildren().add(label);
        stackPane.getChildren().add(image);
        stackPane.getChildren().add(new StackPane(button, buttonBorder));
    }

    public void show() {
        rectangle.setVisible(true);
        label.setVisible(true);
        image.setVisible(true);
        button.setVisible(true);
        buttonBorder.setVisible(true);
    }

    public void hide() {
        rectangle.setVisible(false);
        label.setVisible(false);
        image.setVisible(false);
        button.setVisible(false);
        buttonBorder.setVisible(false);
    }

    public boolean isVisible() {
        return rectangle.isVisible();
    }

}
