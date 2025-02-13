package de.ericzones.fantasygamefx.collectives.creatures;

import de.ericzones.fantasygamefx.Fantasygame;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.Hero;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.utils.HeroType;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.Warrior;
import de.ericzones.fantasygamefx.collectives.creatures.heroes.Wizard;
import de.ericzones.fantasygamefx.collectives.creatures.monster.Monster;
import de.ericzones.fantasygamefx.collectives.world.Location;
import de.ericzones.fantasygamefx.graphics.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class CreatureManager {

    private final List<Creature> activeCreatures = new ArrayList<>();
    private int currentId = 1; // Every creature gets an ID to identify it

    public List<Creature> getActiveCreatures() {
        return this.activeCreatures;
    }

    public void summonMonster(Location location) {
        Monster monster = new Monster(currentId, location);
        this.activeCreatures.add(monster);
        this.currentId++;
        updateGameField(monster, false);
    }

    public void summonMonster(Location location, int lifePoints, int hitPoints) {
        Monster monster = new Monster(currentId, location, lifePoints, hitPoints);
        this.activeCreatures.add(monster);
        this.currentId++;
        updateGameField(monster, false);
    }

    public Hero summonHero(HeroType type, Location location, String name) {
        Hero hero = null;
        switch (type) {
            case WARRIOR:
                hero = new Warrior(currentId, location, name);
                break;
            case WIZARD:
                hero = new Wizard(currentId, location, name);
                break;
        }
        this.activeCreatures.add(hero);
        this.currentId++;
        updateGameField(hero, false);
        return hero;
    }

    public void removeCreature(Creature creature) {
        this.activeCreatures.remove(creature);
        updateGameField(creature, true);
    }

    public void removeAllCreatures() {
        List<Creature> creatures = new ArrayList<>(activeCreatures);
        for(Creature current : creatures)
            removeCreature(current);
    }

    public Creature getCreature(int id) {
        return this.activeCreatures.stream().filter(creature -> creature.getId() == id).findFirst().orElse(null);
    }

    public List<Monster> getMonsters() {
        List<Monster> monsters = new ArrayList<>();
        activeCreatures.stream().filter(creature -> creature instanceof Monster).toList().forEach(monster -> monsters.add((Monster) monster));
        return monsters;
    }

    public List<Hero> getHeroes() {
        List<Hero> heroes = new ArrayList<>();
        activeCreatures.stream().filter(creature -> creature instanceof Hero).toList().forEach(hero -> heroes.add((Hero) hero));
        return heroes;
    }

    // Currently set creature limit to 10
    public boolean isCreatureLimitReached() {
        if(this.activeCreatures.size() >= 10) return true;
        return false;
    }

    // Updating the visualized game field with the position of a newly spawned monster and reset the field of a dead monster
    private void updateGameField(Creature creature, boolean dead) {
        WindowManager windowManager = Fantasygame.getWindowManager();
        if(windowManager == null) return;
        if(dead) {
            windowManager.getGameField().clearField(creature.getLocation());
            return;
        }
        windowManager.getGameField().updateField(null, creature);
    }

}
