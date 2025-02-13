// Created by Eric B. 25.11.2021 22:27
package de.ericzones.fantasygamefx.collectives.creatures.heroes;

import de.ericzones.fantasygamefx.collectives.creatures.heroes.utils.HeroType;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.utils.WizardAbility;
import de.ericzones.fantasygamefx.collectives.world.Location;
import de.ericzones.fantasygamefx.graphics.TextureManager;

public class Wizard extends Hero {

    public Wizard(int id, Location location, String name) {
        super(id, location, TextureManager.TextureType.WIZARD, 25, 4, name, HeroType.WIZARD, new WizardAbility(id));
    }

    public Wizard(int id, Location location, String name, double lifePoints, double hitPoints) {
        super(id, location, TextureManager.TextureType.WIZARD, lifePoints, hitPoints, name, HeroType.WIZARD, new WizardAbility(id));
    }

}
