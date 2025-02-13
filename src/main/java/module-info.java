module de.ericbagus.fantasygamefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens de.ericbagus.fantasygamefx to javafx.fxml;
    exports de.ericbagus.fantasygamefx;
    exports de.ericbagus.fantasygamefx.gameplay;
    exports de.ericbagus.fantasygamefx.graphics;
    exports de.ericbagus.fantasygamefx.collectives.world;
    exports de.ericbagus.fantasygamefx.collectives.creatures;
    exports de.ericbagus.fantasygamefx.graphics.visuals;
    exports de.ericbagus.fantasygamefx.collectives.creatures.fight;
    exports de.ericbagus.fantasygamefx.collectives.creatures.heroes;
    exports de.ericbagus.fantasygamefx.graphics.visuals.gamefield;
    exports de.ericbagus.fantasygamefx.collectives.creatures.monster;
    exports de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils;

}