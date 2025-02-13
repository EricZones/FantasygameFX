module de.ericzones.fantasygamefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens de.ericzones.fantasygamefx to javafx.fxml;
    exports de.ericzones.fantasygamefx;
    exports de.ericzones.fantasygamefx.gameplay;
    exports de.ericzones.fantasygamefx.graphics;
    exports de.ericzones.fantasygamefx.collectives.world;
    exports de.ericzones.fantasygamefx.collectives.creatures;
    exports de.ericzones.fantasygamefx.graphics.visuals;
    exports de.ericzones.fantasygamefx.collectives.creatures.fight;
    exports de.ericzones.fantasygamefx.collectives.creatures.heroes;
    exports de.ericzones.fantasygamefx.graphics.visuals.gamefield;
    exports de.ericzones.fantasygamefx.collectives.creatures.monster;
    exports de.ericzones.fantasygamefx.collectives.creatures.heroes.utils;

}