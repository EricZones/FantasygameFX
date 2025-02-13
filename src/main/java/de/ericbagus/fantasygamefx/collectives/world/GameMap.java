package de.ericbagus.fantasygamefx.collectives.world;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.collectives.creatures.Creature;
import de.ericbagus.fantasygamefx.collectives.creatures.CreatureManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMap {

    private final int maxHeight;
    private final int maxWidth;

    public GameMap(int maxHeight, int maxWidth) {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    public boolean isFieldFree(int x, int y) {
        CreatureManager creatureManager = Fantasygame.getGameplayManager().getCreatureManager();
        if (creatureManager.getActiveCreatures().stream().anyMatch(creature -> creature.getLocation().getX() == x && creature.getLocation().getY() == y)) return false;
        return true;
    }

    public Creature getCreatureOnField(int x, int y) {
        CreatureManager creatureManager = Fantasygame.getGameplayManager().getCreatureManager();
        return creatureManager.getActiveCreatures().stream().filter(creature -> creature.getLocation().getX() == x && creature.getLocation().getY() == y).findFirst().orElse(null);
    }

    public List<Creature> getNearCreatures(int x, int y) {
        List<Creature> creatures = new ArrayList<>();
        if(!isFieldFree(x+1, y))
            creatures.add(getCreatureOnField(x+1, y));
        if(!isFieldFree(x-1, y))
            creatures.add(getCreatureOnField(x-1, y));
        if(!isFieldFree(x, y+1))
            creatures.add(getCreatureOnField(x, y+1));
        if(!isFieldFree(x, y-1))
            creatures.add(getCreatureOnField(x, y-1));
        return creatures;
    }

    public Location getRandomLocation() {
        int randomX = new Random().nextInt(maxWidth)+1, randomY = new Random().nextInt(maxHeight)+1;
        while (!isFieldFree(randomX, randomY)) {
            randomX = new Random().nextInt(maxWidth)+1;
            randomY = new Random().nextInt(maxHeight)+1;
        }
        return new Location(randomX, randomY);
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

}
