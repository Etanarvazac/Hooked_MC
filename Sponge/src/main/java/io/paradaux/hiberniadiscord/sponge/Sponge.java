package io.paradaux.hiberniadiscord.sponge;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

public class Sponge {

    @Inject private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
    }
}
