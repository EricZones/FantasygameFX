// Created by Eric B. 20.12.2021 17:50
package de.ericzones.fantasygamefx.graphics.visuals.gamefield;

import de.ericzones.fantasygamefx.Fantasygame;
import de.ericzones.fantasygamefx.collectives.world.Location;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.TimerTask;

public class GameFieldPart {

    private final Location location;
    private final ImageView backgroundImage;
    private final Rectangle rectangle;
    private final ImageView overlayImage;
    private final Label infoText;

    private final StackPane stackPane;

    public GameFieldPart(Location location, Image image) {
        this.location = location;
        this.backgroundImage = new ImageView(image);
        this.overlayImage = new ImageView();
        this.overlayImage.setVisible(false);
        this.rectangle = new Rectangle();
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setStrokeWidth(2);
        this.infoText = new Label();
        this.infoText.setVisible(false);
        this.infoText.setAlignment(Pos.CENTER);
        this.infoText.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 35));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setSpread(0.75);
        this.infoText.setEffect(dropShadow);
        this.stackPane = new StackPane(this.rectangle, new StackPane(this.backgroundImage, this.overlayImage, this.infoText));
    }

    public Location getLocation() {
        return this.location;
    }

    public ImageView getBackgroundImage() {
        return this.backgroundImage;
    }

    // Set the size of the whole game field part
    public void setSize(int size) {
        this.backgroundImage.setFitHeight(size);
        this.backgroundImage.setFitWidth(size);
        this.rectangle.setHeight(size);
        this.rectangle.setWidth(size);
        // Standard size of the creature image is 64*64 pixels, but if the size of the whole game field part is smaller, the image and the text must be smaller too
        if(size <= 64) {
            this.overlayImage.setFitHeight(size - 7);
            this.overlayImage.setFitWidth(size - 7);
            this.infoText.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 25));
        }
    }

    // Edit the background image
    public void setImage(Image image) {
        this.backgroundImage.setImage(image);
    }

    // Set the image of a creature
    public void addOverlayImage(Image image) {
        this.overlayImage.setImage(image);
        this.overlayImage.setVisible(true);
    }

    // Remove the image of a creature
    public void removeOverlayImage() {
        this.overlayImage.setImage(null);
        this.overlayImage.setVisible(false);
    }

    // Show a specific text on top of a creature image (healing info, damage info)
    public void showInfoText(String text, Color color) {
        this.infoText.setText(text);
        this.infoText.setTextFill(color);
        this.infoText.setVisible(true);
        // Hide the text after a specific time
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(infoText.getText().equals(text))
                    infoText.setVisible(false);
            }
        };
        Fantasygame.getGameplayManager().getGameTimer().schedule(timerTask, 150);
    }

    // Show the whole game field part
    public void show() {
        this.backgroundImage.setVisible(true);
        this.rectangle.setVisible(true);
    }

    // Hide the whole game field part
    public void hide() {
        this.backgroundImage.setVisible(false);
        this.rectangle.setVisible(false);
        this.overlayImage.setVisible(false);
        this.infoText.setVisible(false);
    }

    public StackPane getStackPane() {
        return stackPane;
    }

}
