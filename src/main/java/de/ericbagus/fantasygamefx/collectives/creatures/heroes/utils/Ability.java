// Created by Eric B. 31.12.2021 22:11
package de.ericbagus.fantasygamefx.collectives.creatures.heroes.utils;

import de.ericbagus.fantasygamefx.Fantasygame;
import de.ericbagus.fantasygamefx.graphics.TextureManager;

import java.util.TimerTask;

public abstract class Ability {

    protected final int heroId;
    private final TextureManager.TextureType texture;
    private final TextureManager.TextureType statusTexture;

    protected boolean active;
    protected boolean delayed;

    private TimerTask timerTask;
    private int timerSeconds;

    public Ability(int heroId, TextureManager.TextureType texture, TextureManager.TextureType statusTexture) {
        this.heroId = heroId;
        this.texture = texture;
        this.statusTexture = statusTexture;
    }

    public TextureManager.TextureType getTexture() {
        return texture;
    }

    public TextureManager.TextureType getStatusTexture() {
        return statusTexture;
    }

    // Check if the ability is currently active
    public boolean isActive() {
        return active;
    }

    // Check if the ability is currently delayed and not available
    public boolean isDelayed() {
        return delayed;
    }

    // Just a placeholder method to let the extending classes override this method
    public void setActive() {
    }

    // Start timer until the ability could be used again
    protected void startDelayScheduler() {
        timerSeconds = 60;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                // Pause timer seconds if the game is freezed
                if(Fantasygame.getGameplayManager().isFreezed()) return;
                timerSeconds--;
                // If the ability delay seconds aren't expired return
                if(timerSeconds > 0) return;
                // If the hero is dead stop the timer
                if(Fantasygame.getGameplayManager().getCreatureManager().getCreature(heroId) == null) {
                    timerTask.cancel();
                    return;
                }
                // Set the ability not delayed anymore and update the available ability in the visualized info panel
                delayed = false;
                Fantasygame.getWindowManager().getInfoField().setAbilityTexture(texture);
                // Cancel the ability delay timer
                timerTask.cancel();
            }
        };
        Fantasygame.getGameplayManager().getGameTimer().schedule(timerTask, 1000, 1000);
    }

}
