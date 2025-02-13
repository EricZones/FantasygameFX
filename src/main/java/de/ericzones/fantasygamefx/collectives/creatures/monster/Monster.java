package de.ericzones.fantasygamefx.collectives.creatures.monster;

import de.ericzones.fantasygamefx.collectives.creatures.Creature;
import de.ericzones.fantasygamefx.collectives.world.Location;
import de.ericzones.fantasygamefx.graphics.TextureManager;

public class Monster extends Creature {

    // Unnecessary to set it as an attribute, but useful in a more complex AI later
    private final MonsterAI monsterAI;

    public Monster(int id, Location location) {
        super(id, location, TextureManager.TextureType.MONSTER, 10, 1);
        this.monsterAI = new MonsterAI(id);
    }

    public Monster(int id, Location location, double lifePoints, double hitPoints) {
        super(id, location, TextureManager.TextureType.MONSTER, lifePoints, hitPoints);
        this.monsterAI = new MonsterAI(id);
    }

}
