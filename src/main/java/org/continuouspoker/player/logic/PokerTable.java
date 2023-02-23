package org.continuouspoker.player.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.continuouspoker.player.model.Player;
import org.continuouspoker.player.model.Table;

class PokerTable {

    final Table table;

    public PokerTable(final Table table) {
        this.table = table;
    }

    public PokerPlayer getPlayer() {
        return new PokerPlayer(table.getPlayers().get(table.getActivePlayer()));
    }

    public List<PokerCard> getCards() {
        return table.getCommunityCards().stream().map(PokerCard::new).collect(Collectors.toList());
    }

}
