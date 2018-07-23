package org.mhdvsolutions.expansions.parties;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import me.clip.placeholderapi.expansion.Cleanable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public final class PartiesExpansion extends PlaceholderExpansion implements Cleanable {

    private final Map<String, Function<Player, String>> placeholders = new HashMap<>();

    @Override
    public String getIdentifier() {
        return "parties";
    }

    @Override
    public String getAuthor() {
        return "tmux";
    }

    @Override
    public String getVersion() {
        return getClass().getPackage().getImplementationVersion();
    }

    @Override
    public String getRequiredPlugin() {
        return "Parties";
    }

    @Override
    public boolean register() {
        if (canRegister()) {
            new PartiesPlaceholders(this);
            return super.register();
        }
        return false;
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null || !player.isOnline()) {
            return null;
        }
        return placeholders.getOrDefault(identifier, (player1) -> "").apply(player);
    }

    @Override
    public void cleanup(Player player) {
        placeholders.clear();
    }

    Map<String, Function<Player, String>> getPlaceholderRequests() {
        return placeholders;
    }

}
