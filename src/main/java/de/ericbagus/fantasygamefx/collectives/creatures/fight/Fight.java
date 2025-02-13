// Created by Eric B. 28.11.2021 11:48
package de.ericbagus.fantasygamefx.collectives.creatures.fight;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.collectives.creatures.Creature;
import de.ericbagus.fantasygamefx.collectives.creatures.heroes.Hero;

public class Fight {

    private final Creature attacker, enemy;

    public Fight(Creature attacker, Creature enemy) {
        this.attacker = attacker;
        this.enemy = enemy;
        attack();
    }

    private void attack() {
        double damage = getCreatureDamage(attacker);
        enemy.setDamage(damage);
        // Check if the enemy is already dead after the hit from the attacker
        if(enemy.getLifePoints() <= 0) {
            setDeadCreature(enemy);
            if(attacker instanceof Hero)
                ((Hero) attacker).addKill();
            return;
        }
        // Stop process if the enemy is a hero, because heroes shouldn't damage by themselves (It's the case if a monster hits you first)
        if(enemy instanceof Hero) return;
        damage = getCreatureDamage(enemy);
        attacker.setDamage(damage);
        // Check if the attacker is already dead after the hit from the enemy
        if(attacker.getLifePoints() <= 0)
            setDeadCreature(attacker);
    }

    // Update the field of a dead creature with the creature manager method or check if the hero died and stop the game
    private void setDeadCreature(Creature creature) {
        if(!(creature instanceof Hero)) {
            Fantasygame.getGameplayManager().getCreatureManager().removeCreature(creature);
            return;
        }
        Fantasygame.getWindowManager().getEndingField().show();
    }

    // Calculate the damage of a creature with their standard damage and their weapon
    private double getCreatureDamage(Creature creature) {
        if(!(creature instanceof Hero) || ((Hero) creature).getWeapon() == null) return creature.getHitPoints();
        return ((Hero) creature).getWeapon().getStrength()+creature.getHitPoints();
    }

}
