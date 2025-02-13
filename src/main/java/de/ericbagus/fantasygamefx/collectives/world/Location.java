// Created by Eric B. 25.11.2021 21:10
package de.ericbagus.fantasygamefx.collectives.world;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.collectives.creatures.Creature;
import de.ericbagus.fantasygamefx.graphics.WindowManager;

import java.util.Random;

public class Location {

    private int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveRandom() {
        int number = new Random().nextInt(4)+1;
        if(number == 1)
            moveRight();
        else if(number == 2)
            moveLeft();
        else if(number == 3)
            moveUp();
        else
            moveDown();
    }

    public boolean moveRight() {
        if(!isFieldAccessible(x+1, y)) return false;
        setX(getX()+1);
        updateGameField(new Location(x-1, y));
        return true;
    }

    public boolean moveLeft() {
        if(!isFieldAccessible(x-1, y)) return false;
        setX(getX()-1);
        updateGameField(new Location(x+1, y));
        return true;
    }

    public boolean moveUp() {
        if(!isFieldAccessible(x, y-1)) return false;
        setY(getY()-1);
        updateGameField(new Location(x, y+1));
        return true;
    }

    public boolean moveDown() {
        if(!isFieldAccessible(x, y+1)) return false;
        setY(getY()+1);
        updateGameField(new Location(x, y-1));
        return true;
    }

    // Check if a certain field is outside the map border or another creature is on it
    private boolean isFieldAccessible(int x, int y) {
        if(Fantasygame.getGameplayManager().isFreezed()) return false;
        GameMap gameMap = Fantasygame.getGameplayManager().getGameMap();
        if(y > gameMap.getMaxHeight() || y < 1 || x > gameMap.getMaxWidth() || x < 1) return false;
        if(!gameMap.isFieldFree(x, y)) return false;
        return true;
    }

    // Register the movement of a certain creature on the visualized game field
    private void updateGameField(Location previousLocation) {
        WindowManager windowManager = Fantasygame.getWindowManager();
        Creature creature = Fantasygame.getGameplayManager().getGameMap().getCreatureOnField(x, y);
        // Show the creature on the new field and reset the previous field
        windowManager.getGameField().updateField(previousLocation, creature);
    }

}
