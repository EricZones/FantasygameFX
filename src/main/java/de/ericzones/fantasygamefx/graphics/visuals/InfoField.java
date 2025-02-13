// Created by Eric B. 20.12.2021 21:13
package de.ericzones.fantasygamefx.graphics.visuals;

import de.ericzones.fantasygamefx.Fantasygame;
import de.ericzones.fantasygamefx.graphics.TextureManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class InfoField {

    private Rectangle leftRectangle, rightRectangle;
    private ImageView leftBackground, rightBackground;
    private List<Label> headlines;

    private Label heroType, nickname, lifePoints, kills;
    private ImageView weaponImage, abilityImage, abilityStatusImage;

    public InfoField(StackPane stackPane) {
        createLeftWindow(stackPane);
        createRightWindow(stackPane);
        createInfoHeadlines(stackPane);
        createInfoLabels(stackPane);
        createInfoImages(stackPane);
        hide();
    }

    private void createRightWindow(StackPane stackPane) {
        rightRectangle = new Rectangle();
        rightRectangle.setFill(null);
        // Setting the size and the position of the right window dynamic to the game screen size
        rightRectangle.setHeight(Fantasygame.getWindowManager().getHeight()*0.55);
        rightRectangle.setWidth(Fantasygame.getWindowManager().getWidth()*0.2);
        rightRectangle.setTranslateX((Fantasygame.getWindowManager().getWidth()/2)*0.65);
        rightRectangle.setArcWidth(40);
        rightRectangle.setArcHeight(30);
        rightRectangle.setStroke(Color.BLACK);
        rightRectangle.setStrokeWidth(3);
        rightBackground = new ImageView(Fantasygame.getWindowManager().getTextureManager().getTexture(TextureManager.TextureType.BACKGROUND_INFO));
        rightBackground.setFitHeight(Fantasygame.getWindowManager().getHeight()*0.55);
        rightBackground.setFitWidth(Fantasygame.getWindowManager().getWidth()*0.2);
        rightBackground.setTranslateX((Fantasygame.getWindowManager().getWidth()/2)*0.65);
        rightBackground.setOpacity(0.75);
        Rectangle clip = new Rectangle(rightBackground.getFitWidth(), rightBackground.getFitHeight());
        clip.setArcWidth(40);
        clip.setArcHeight(30);
        rightBackground.setClip(clip);
        stackPane.getChildren().add(new StackPane(rightBackground, rightRectangle));
    }

    private void createLeftWindow(StackPane stackPane) {
        leftRectangle = new Rectangle();
        leftRectangle.setFill(null);
        // Setting the size and the position of the left window dynamic to the game screen size
        leftRectangle.setHeight(Fantasygame.getWindowManager().getHeight()*0.55);
        leftRectangle.setWidth(Fantasygame.getWindowManager().getWidth()*0.2);
        leftRectangle.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        leftRectangle.setArcWidth(40);
        leftRectangle.setArcHeight(30);
        leftRectangle.setStroke(Color.BLACK);
        leftRectangle.setStrokeWidth(3);
        leftBackground = new ImageView(Fantasygame.getWindowManager().getTextureManager().getTexture(TextureManager.TextureType.BACKGROUND_INFO));
        leftBackground.setFitHeight(Fantasygame.getWindowManager().getHeight()*0.55);
        leftBackground.setFitWidth(Fantasygame.getWindowManager().getWidth()*0.2);
        leftBackground.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        leftBackground.setOpacity(0.75);
        Rectangle clip = new Rectangle(leftBackground.getFitWidth(), leftBackground.getFitHeight());
        clip.setArcWidth(40);
        clip.setArcHeight(30);
        leftBackground.setClip(clip);
        stackPane.getChildren().add(new StackPane(leftBackground, leftRectangle));
    }

    private void createInfoHeadlines(StackPane stackPane) {
        headlines = new ArrayList<>();
        Label informationHeadline = new Label();
        informationHeadline.setFont(new Font(35));
        informationHeadline.setText("• Information •");
        informationHeadline.setAlignment(Pos.CENTER);
        informationHeadline.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        informationHeadline.setTranslateY(-(Fantasygame.getWindowManager().getHeight()/2)*0.5);
        Label heroTypeHeadline = new Label();
        heroTypeHeadline.setFont(new Font(25));
        heroTypeHeadline.setText("• Hero •");
        heroTypeHeadline.setAlignment(Pos.CENTER);
        heroTypeHeadline.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        heroTypeHeadline.setTranslateY(-(Fantasygame.getWindowManager().getHeight()/2)*0.35);
        Label nicknameHeadline = new Label();
        nicknameHeadline.setFont(new Font(25));
        nicknameHeadline.setText("• Nickname •");
        nicknameHeadline.setAlignment(Pos.CENTER);
        nicknameHeadline.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        nicknameHeadline.setTranslateY(-(Fantasygame.getWindowManager().getHeight()/2)*0.15);
        Label lifePointsHeadline = new Label();
        lifePointsHeadline.setFont(new Font(25));
        lifePointsHeadline.setText("• Lifepoints •");
        lifePointsHeadline.setAlignment(Pos.CENTER);
        lifePointsHeadline.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        lifePointsHeadline.setTranslateY((Fantasygame.getWindowManager().getHeight()/2)*0.05);
        Label killsHeadline = new Label();
        killsHeadline.setFont(new Font(25));
        killsHeadline.setText("• Kills •");
        killsHeadline.setAlignment(Pos.CENTER);
        killsHeadline.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        killsHeadline.setTranslateY((Fantasygame.getWindowManager().getHeight()/2)*0.26);
        Label inventoryHeadline = new Label();
        inventoryHeadline.setFont(new Font(35));
        inventoryHeadline.setText("• Inventory •");
        inventoryHeadline.setAlignment(Pos.CENTER);
        inventoryHeadline.setTranslateX((Fantasygame.getWindowManager().getWidth()/2)*0.65);
        inventoryHeadline.setTranslateY(-(Fantasygame.getWindowManager().getHeight()/2)*0.5);
        Label weaponHeadline = new Label();
        weaponHeadline.setFont(new Font(25));
        weaponHeadline.setText("• Weapon •");
        weaponHeadline.setAlignment(Pos.CENTER);
        weaponHeadline.setTranslateX((Fantasygame.getWindowManager().getWidth()/2)*0.65);
        weaponHeadline.setTranslateY(-(Fantasygame.getWindowManager().getHeight()/2)*0.35);
        Label abilityHeadline = new Label();
        abilityHeadline.setFont(new Font(25));
        abilityHeadline.setText("• Ability •");
        abilityHeadline.setAlignment(Pos.CENTER);
        abilityHeadline.setTranslateX((Fantasygame.getWindowManager().getWidth()/2)*0.65);
        abilityHeadline.setTranslateY((Fantasygame.getWindowManager().getHeight()/2)*0.05);
        stackPane.getChildren().addAll(informationHeadline, heroTypeHeadline, nicknameHeadline, lifePointsHeadline, killsHeadline, inventoryHeadline, weaponHeadline, abilityHeadline);
        headlines.addAll(List.of(informationHeadline, heroTypeHeadline, nicknameHeadline, lifePointsHeadline, killsHeadline, inventoryHeadline, weaponHeadline, abilityHeadline));
    }

    private void createInfoLabels(StackPane stackPane) {
        heroType = new Label();
        heroType.setFont(new Font(20));
        heroType.setText("...HeroType...");
        heroType.setAlignment(Pos.CENTER);
        heroType.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        heroType.setTranslateY(-(Fantasygame.getWindowManager().getHeight()/2)*0.29);
        heroType.setVisible(false);

        nickname = new Label();
        nickname.setFont(new Font(20));
        nickname.setText("...Nickname...");
        nickname.setAlignment(Pos.CENTER);
        nickname.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        nickname.setTranslateY(-(Fantasygame.getWindowManager().getHeight()/2)*0.09);
        nickname.setVisible(false);

        lifePoints = new Label();
        lifePoints.setFont(new Font(20));
        lifePoints.setText("...LifePoints...");
        lifePoints.setAlignment(Pos.CENTER);
        lifePoints.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        lifePoints.setTranslateY((Fantasygame.getWindowManager().getHeight()/2)*0.11);
        lifePoints.setVisible(false);

        kills = new Label();
        kills.setFont(new Font(20));
        kills.setText("...Kills...");
        kills.setAlignment(Pos.CENTER);
        kills.setTranslateX(-(Fantasygame.getWindowManager().getWidth()/2)*0.65);
        kills.setTranslateY((Fantasygame.getWindowManager().getHeight()/2)*0.31);
        kills.setVisible(false);
        stackPane.getChildren().add(heroType);
        stackPane.getChildren().add(nickname);
        stackPane.getChildren().add(lifePoints);
        stackPane.getChildren().add(kills);
    }

    private void createInfoImages(StackPane stackPane) {
        TextureManager textureManager = Fantasygame.getWindowManager().getTextureManager();
        weaponImage = new ImageView(textureManager.getTexture(TextureManager.TextureType.ITEM_NONE));
        weaponImage.setFitWidth(64); weaponImage.setFitHeight(64);
        weaponImage.setTranslateX((Fantasygame.getWindowManager().getWidth()/2)*0.65); weaponImage.setTranslateY(-(Fantasygame.getWindowManager().getHeight()/2)*0.2);
        abilityImage = new ImageView(textureManager.getTexture(TextureManager.TextureType.ITEM_NONE));
        abilityImage.setFitWidth(64); abilityImage.setFitHeight(64);
        abilityImage.setTranslateX((Fantasygame.getWindowManager().getWidth()/2)*0.65); abilityImage.setTranslateY((Fantasygame.getWindowManager().getHeight()/2)*0.2);
        abilityStatusImage = new ImageView();
        abilityStatusImage.setFitWidth(110); abilityStatusImage.setFitHeight(110);
        abilityStatusImage.setTranslateX((Fantasygame.getWindowManager().getWidth()/2)*0.65); abilityStatusImage.setTranslateY((Fantasygame.getWindowManager().getHeight()/2)*0.2);
        abilityStatusImage.setVisible(false);
        stackPane.getChildren().addAll(weaponImage, abilityStatusImage, abilityImage);
    }

    public void setHeroType(String heroType) {
        this.heroType.setText(heroType);
        this.heroType.setVisible(true);
    }

    public void setNickname(String nickname) {
        this.nickname.setText(nickname);
        this.nickname.setVisible(true);
    }

    public void setLifePoints(double lifePoints) {
        this.lifePoints.setText(String.valueOf(lifePoints));
        this.lifePoints.setVisible(true);
    }

    public void setKills(int kills) {
        this.kills.setText(String.valueOf(kills));
        this.kills.setVisible(true);
    }

    public void toggleAbilityStatus(boolean visible) {
        abilityStatusImage.setVisible(visible);
    }

    public void setWeaponTexture(TextureManager.TextureType textureType) {
        this.weaponImage.setImage(Fantasygame.getWindowManager().getTextureManager().getTexture(textureType));
    }

    public void setAbilityTexture(TextureManager.TextureType textureType) {
        this.abilityImage.setImage(Fantasygame.getWindowManager().getTextureManager().getTexture(textureType));
    }

    public void setAbilityStatusTexture(TextureManager.TextureType textureType) {
        this.abilityStatusImage.setImage(Fantasygame.getWindowManager().getTextureManager().getTexture(textureType));
    }

    public void show() {
        this.leftRectangle.setVisible(true);
        this.rightRectangle.setVisible(true);
        this.leftBackground.setVisible(true);
        this.rightBackground.setVisible(true);
        this.headlines.forEach(label -> label.setVisible(true));
        this.heroType.setVisible(true);
        this.nickname.setVisible(true);
        this.lifePoints.setVisible(true);
        this.kills.setVisible(true);
        this.weaponImage.setVisible(true);
        this.abilityImage.setVisible(true);
    }
    
    public void hide() {
        this.leftRectangle.setVisible(false);
        this.rightRectangle.setVisible(false);
        this.leftBackground.setVisible(false);
        this.rightBackground.setVisible(false);
        this.headlines.forEach(label -> label.setVisible(false));
        this.heroType.setVisible(false);
        this.nickname.setVisible(false);
        this.lifePoints.setVisible(false);
        this.kills.setVisible(false);
        this.weaponImage.setVisible(false);
        this.abilityImage.setVisible(false);
        this.abilityStatusImage.setVisible(false);
    }

}
