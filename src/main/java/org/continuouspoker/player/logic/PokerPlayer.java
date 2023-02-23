package org.continuouspoker.player.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.continuouspoker.player.model.Player;

public class PokerPlayer {

    final Player player;

    public PokerPlayer(final Player player) {
        this.player = player;
    }

    public List<PokerCard> getCards() {
        return this.player.getCards().stream().map(PokerCard::new).collect(Collectors.toList());
    }

    public int getRaiseBet(PokerTable table) {
        return Math.max(table.table.getMinimumBet(), player.getBet() + table.table.getMinimumRaise());
    }

}
