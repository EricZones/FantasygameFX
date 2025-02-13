// Created by Eric B. 25.11.2021 22:27
package de.ericzones.fantasygamefx.collectives.creatures.heroes;

import de.ericzones.fantasygamefx.collectives.creatures.heroes.utils.HeroType;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.utils.WarriorAbility;
import de.ericzones.fantasygamefx.collectives.world.Location;
import de.ericzones.fantasygamefx.graphics.TextureManager;

public class Warrior extends Hero {

    public Warrior(int id, Location location, String name) {
        super(id, location, TextureManager.TextureType.WARRIOR, 30, 3, name, HeroType.WARRIOR, new WarriorAbility(id));
    }

    public Warrior(int id, Location location, String name, double lifePoints, double hitPoints) {
        super(id, location, TextureManager.TextureType.WARRIOR, lifePoints, hitPoints, name, HeroType.WARRIOR, new WarriorAbility(id));
    }

}
