// Created by Eric B. 25.11.2021 22:27
package de.ericbagus.fantasygamefx.collectives.creatures.heroes;

import de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils.HeroType;
import de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils.WizardAbility;
import de.ericbagus.fantasygamefx.collectives.world.Location;
import de.ericbagus.fantasygamefx.graphics.TextureManager;

public class Wizard extends Hero {

    public Wizard(int id, Location location, String name) {
        super(id, location, TextureManager.TextureType.WIZARD, 25, 4, name, HeroType.WIZARD, new WizardAbility(id));
    }

    public Wizard(int id, Location location, String name, double lifePoints, double hitPoints) {
        super(id, location, TextureManager.TextureType.WIZARD, lifePoints, hitPoints, name, HeroType.WIZARD, new WizardAbility(id));
    }

}
