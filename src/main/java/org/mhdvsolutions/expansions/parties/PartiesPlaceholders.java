package org.mhdvsolutions.expansions.parties;

import com.alessiodp.partiesapi.Parties;
import com.alessiodp.partiesapi.interfaces.PartiesAPI;
import com.alessiodp.partiesapi.objects.Party;
import com.alessiodp.partiesapi.objects.PartyPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import me.clip.placeholderapi.PlaceholderAPIPlugin;

final class PartiesPlaceholders {

    private final PartiesAPI api;

    PartiesPlaceholders(final PartiesExpansion expansion) {
        this.api = Parties.getApi();
        Map<String, Function<Player, String>> placeholders = new HashMap<>();
        placeholders.put("party_name", this::partyName);
        placeholders.put("party_leader", this::partyLeader);
        placeholders.put("party_fixed", this::partyFixed);
        placeholders.put("party_description", this::partyDescription);
        placeholders.put("party_motd", this::partyMotd);
        placeholders.put("party_home_x", this::partyHomeX);
        placeholders.put("party_home_y", this::partyHomeY);
        placeholders.put("party_home_z", this::partyHomeZ);
        placeholders.put("party_prefix", this::partyPrefix);
        placeholders.put("party_suffix", this::partySuffix);
        placeholders.put("party_color", this::partyColor);
        placeholders.put("party_kills", this::partyKills);
        placeholders.put("party_password", this::partyPassword);

        placeholders.put("player_name_timestamp", this::playerNameTimestamp);
        placeholders.put("player_rank", this::playerRank);
        placeholders.put("player_party_name", this::playerPartyName);
        placeholders.put("player_is_spy", this::playerSpy);
        placeholders.put("player_is_preventnotify", this::playerPreventNotify);
        expansion.getPlaceholderRequests().putAll(placeholders);
    }

    private PartyPlayer getPartyPlayer(final Player player) {
        return api.getPartyPlayer(player.getUniqueId());
    }

    private Party getParty(final Player player) {
        PartyPlayer partyPlayer = getPartyPlayer(player);
        if (partyPlayer != null) {
            String partyName = partyPlayer.getPartyName();
            return partyName != null ? api.getParty(partyName) : null;
        }
        return null;
    }

    private String partyName(final Player player) {
        Party party = getParty(player);
        return party != null ? party.getName() : "";
    }

    private String partyLeader(final Player player) {
        Party party = getParty(player);
        return party != null ? Bukkit.getOfflinePlayer(party.getLeader()).getName() : "";
    }

    private String partyFixed(final Player player) {
        Party party = getParty(player);
        return party != null ? bool(party.isFixed()) : "";
    }

    private String partyDescription(final Player player) {
        Party party = getParty(player);
        return party != null ? party.getDescription() : "";
    }

    private String partyMotd(final Player player) {
        Party party = getParty(player);
        return party != null ? party.getMotd() : "";
    }

    private String partyHomeX(final Player player) {
        Party party = getParty(player);
        return party != null ? Integer.toString(party.getHome().getBlockX()) : "";
    }

    private String partyHomeY(final Player player) {
        Party party = getParty(player);
        return party != null ? Integer.toString(party.getHome().getBlockY()) : "";
    }

    private String partyHomeZ(final Player player) {
        Party party = getParty(player);
        return party != null ? Integer.toString(party.getHome().getBlockZ()) : "";
    }

    private String partyPrefix(final Player player) {
        Party party = getParty(player);
        return party != null ? party.getPrefix() : "";
    }

    private String partySuffix(final Player player) {
        Party party = getParty(player);
        return party != null ? party.getSuffix() : "";
    }

    private String partyColor(final Player player) {
        Party party = getParty(player);
        return party != null ? party.getColor().getName() : "";
    }

    private String partyKills(final Player player) {
        Party party = getParty(player);
        return party != null ? Integer.toString(party.getKills()) : "";
    }

    private String partyPassword(final Player player) {
        Party party = getParty(player);
        return party != null ? party.getPassword() : "";
    }

    private String playerNameTimestamp(final Player player) {
        PartyPlayer partyPlayer = getPartyPlayer(player);
        return partyPlayer != null ? Long.toString(partyPlayer.getNameTimestamp()) : "";
    }

    private String playerRank(final Player player) {
        PartyPlayer partyPlayer = getPartyPlayer(player);
        return partyPlayer != null ? Integer.toString(partyPlayer.getRank()) : "";
    }

    private String playerPartyName(final Player player) {
        PartyPlayer partyPlayer = getPartyPlayer(player);
        return partyPlayer != null ? partyPlayer.getPartyName() : "";
    }

    private String playerSpy(final Player player) {
        PartyPlayer partyPlayer = getPartyPlayer(player);
        return partyPlayer != null ? bool(partyPlayer.isSpy()) : "";
    }

    private String playerPreventNotify(final Player player) {
        PartyPlayer partyPlayer = getPartyPlayer(player);
        return partyPlayer != null ? bool(partyPlayer.isPreventNotify()) : "";
    }

    private String bool(boolean value) {
        return value ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
    }

}
