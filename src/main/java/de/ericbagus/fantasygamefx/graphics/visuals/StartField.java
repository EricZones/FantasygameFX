// Created by Eric B. 01.01.2022 19:12
package de.ericbagus.fantasygamefx.graphics.visuals;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils.HeroType;
import de.ericbagus.fantasygamefx.graphics.TextureManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class StartField {

    private final Rectangle rectangle;

    private final Label heroLabel;
    private final ImageView hero1, hero2;
    private final Button hero1Button, hero2Button;

    private final Label nicknameLabel;
    private final TextField textField;
    private final Button submitButton;
    private final Rectangle buttonBorder;

    private HeroType heroType;

    public StartField(StackPane stackPane) {
        this.rectangle = new Rectangle();
        rectangle.setFill(Color.WHITE);
        rectangle.setHeight(185);
        rectangle.setWidth(375);
        rectangle.setArcWidth(40);
        rectangle.setArcHeight(30);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(3);
        this.heroLabel = new Label();
        heroLabel.setAlignment(Pos.CENTER);
        heroLabel.setText("Choose your Hero");
        heroLabel.setPrefSize(400, 75);
        heroLabel.setFont(new Font(35));
        heroLabel.setTranslateY(-50);
        this.hero1 = new ImageView(Fantasygame.getWindowManager().getTextureManager().getTexture(TextureManager.TextureType.WARRIOR));
        hero1.setFitWidth(64); hero1.setFitHeight(64);
        hero1.setTranslateX(-75); hero1.setTranslateY(25);
        hero1Button = new Button();
        hero1Button.setBackground(null);
        hero1Button.setPrefSize(64, 64);
        hero1Button.setTranslateY(hero1.getTranslateY()); hero1Button.setTranslateX(hero1.getTranslateX());
        hero1Button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                heroType = HeroType.WARRIOR;
                changeField();
            }
        });
        this.hero2 = new ImageView(Fantasygame.getWindowManager().getTextureManager().getTexture(TextureManager.TextureType.WIZARD));
        hero2.setFitWidth(64); hero2.setFitHeight(64);
        hero2.setTranslateX(75); hero2.setTranslateY(25);
        hero2Button = new Button();
        hero2Button.setBackground(null);
        hero2Button.setPrefSize(64, 64);
        hero2Button.setTranslateY(hero2.getTranslateY()); hero2Button.setTranslateX(hero2.getTranslateX());
        hero2Button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                heroType = HeroType.WIZARD;
                changeField();
            }
        });

        nicknameLabel = new Label();
        nicknameLabel.setAlignment(Pos.CENTER);
        nicknameLabel.setText("Enter your nickname");
        nicknameLabel.setPrefSize(400, 75);
        nicknameLabel.setFont(new Font(35));
        nicknameLabel.setTranslateY(-50);
        nicknameLabel.setVisible(false);
        textField = new TextField();
        textField.setMaxWidth(200); textField.setMaxHeight(35);
        textField.setBackground(null);
        textField.setAlignment(Pos.CENTER);
        textField.setTranslateY(25);
        textField.setFont(new Font(20));
        textField.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 10 10 10 10; -fx-text-fill: #565656;");
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                if(textField.getText().length() > 10) {
                    String text = textField.getText().substring(0, 10);
                    textField.setText(text);
                    return;
                }
                if(!containsJustLetters(textField.getText())) {
                    String text = textField.getText().substring(0, textField.getLength()-1);
                    textField.setText(text);
                }
            }
        });
        textField.setVisible(false);
        submitButton = new Button();
        submitButton.setVisible(false);
        submitButton.setTranslateY(180);
        submitButton.setAlignment(Pos.CENTER);
        submitButton.setText("Confirm");
        submitButton.setFont(new Font(30));
        submitButton.setStyle("-fx-background-color: white;");
        submitButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                submitButton.setStyle("-fx-background-color: #E5E5E5;");
            }
        });
        submitButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                submitButton.setStyle("-fx-background-color: white;");
            }
        });
        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(textField.getText().length() < 3) {
                    textField.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 10 10 10 10; -fx-text-fill: #565656;");
                    return;
                }
                hideSecondField();
                Fantasygame.getGameplayManager().initiateGame(heroType, textField.getText());
            }
        });
        submitButton.setPrefWidth(175);
        submitButton.setPrefHeight(75);
        Rectangle clip = new Rectangle(submitButton.getPrefWidth(), submitButton.getPrefHeight());
        clip.setArcWidth(25);
        clip.setArcHeight(20);
        submitButton.setClip(clip);
        this.buttonBorder = new Rectangle(submitButton.getPrefWidth(), submitButton.getPrefHeight());
        buttonBorder.setFill(null);
        buttonBorder.setStroke(Color.BLACK);
        buttonBorder.setStrokeWidth(3);
        buttonBorder.setArcWidth(25);
        buttonBorder.setArcHeight(20);
        buttonBorder.setTranslateY(submitButton.getTranslateY());
        buttonBorder.setVisible(false);

        stackPane.getChildren().addAll(rectangle, heroLabel, hero1, hero2, hero1Button, hero2Button, nicknameLabel, textField, submitButton, buttonBorder);
    }

    public void showFirstField() {
        rectangle.setVisible(true);
        heroLabel.setVisible(true);
        hero1.setVisible(true);
        hero2.setVisible(true);
        hero1Button.setVisible(true);
        hero2Button.setVisible(true);
    }

    public void hideFirstField() {
        rectangle.setVisible(false);
        heroLabel.setVisible(false);
        hero1.setVisible(false);
        hero2.setVisible(false);
        hero1Button.setVisible(false);
        hero2Button.setVisible(false);
    }

    public void showSecondField() {
        rectangle.setVisible(true);
        nicknameLabel.setVisible(true);
        textField.setVisible(true);
        submitButton.setVisible(true);
        buttonBorder.setVisible(true);
    }

    public void hideSecondField() {
        rectangle.setVisible(false);
        nicknameLabel.setVisible(false);
        textField.setVisible(false);
        submitButton.setVisible(false);
        buttonBorder.setVisible(false);
    }

    public boolean isVisible() {
        return rectangle.isVisible();
    }

    private void changeField() {
        hideFirstField();
        showSecondField();
    }

    private boolean containsJustLetters(String string) {
        char[] chars = string.toCharArray();
        for (char current : chars) {
            if(!Character.isLetter(current)) {
                return false;
            }
        }
        return true;
    }


}
