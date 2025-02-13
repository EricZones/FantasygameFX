package de.ericzones.fantasygamefx.collectives.creatures.heroes.utils;

public enum HeroType {

    WARRIOR("Warrior"),
    WIZARD("Wizard");

    private HeroType(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

}
