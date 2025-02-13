// Created by Eric B. 25.11.2021 22:13
package de.ericzones.fantasygamefx.collectives.tools.weapons;

public class Sword implements Weapon {

    private final long strength;
    private final String material;
    private long durability;

    public Sword(long strength, String material) {
        this.strength = strength;
        this.material = material;
        this.durability = 10;
    }

    @Override
    public long getStrength() {
        return strength;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    public long getDurability() {
        return durability;
    }

    public void setDurability(long durability) {
        this.durability = durability;
    }

}
