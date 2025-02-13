// Created by Eric B. 25.11.2021 22:27
package de.ericbagus.fantasygamefx.collectives.creatures.heroes;

import de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils.HeroType;
import de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils.WarriorAbility;
import de.ericbagus.fantasygamefx.collectives.world.Location;
import de.ericbagus.fantasygamefx.graphics.TextureManager;

public class Warrior extends Hero {

    public Warrior(int id, Location location, String name) {
        super(id, location, TextureManager.TextureType.WARRIOR, 30, 3, name, HeroType.WARRIOR, new WarriorAbility(id));
    }

    public Warrior(int id, Location location, String name, double lifePoints, double hitPoints) {
        super(id, location, TextureManager.TextureType.WARRIOR, lifePoints, hitPoints, name, HeroType.WARRIOR, new WarriorAbility(id));
    }

}
