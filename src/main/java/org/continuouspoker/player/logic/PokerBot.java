package org.continuouspoker.player.logic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.continuouspoker.player.model.Bet;
import org.continuouspoker.player.model.Rank;
import org.continuouspoker.player.model.Suit;
import org.continuouspoker.player.model.Table;

public class PokerBot {

    PokerTable table;
    PokerPlayer player;
    PokerDeck deck;

    public Bet decide(final Table table) {

        this.table = new PokerTable(table);
        this.player = this.table.getPlayer();
        this.deck = new PokerDeck(this.table, this.player);

        if (deck.isRoyalFlush()) {
            System.out.println(table);
            System.out.println("Royal Flush!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return createAllIn();
        } else if (deck.isStraightFlush()) {
            System.out.println(table);
            System.out.println("Straight Flush !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return createAllIn();
        } else if (deck.isStreet()) {
            System.out.println(table);
            System.out.println("Street!!!");
            return createRaiseBet();
        } else if (deck.getMultiples().size() > 0) {
            System.out.println("Pair!!!");
            return createRaiseBet();
        } else {
            System.out.println("...");
            return createFold();
        }
    }

    public boolean canCheck() {
        return table.canCheck();
    }

    public Bet createAllIn() {
        return new Bet().bet(player.player.getStack());
    }

    public Bet createRaiseBet() {
        return new Bet().bet(player.getRaiseBet(this.table));
    }

    public Bet createFold() {
        return new Bet().bet(0);
    }

}
