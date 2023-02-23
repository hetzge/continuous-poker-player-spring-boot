package org.continuouspoker.player.logic;

import java.util.Arrays;
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

    public Bet decide(final Table table) {
        System.out.println(table);
        this.table = new PokerTable(table);
        this.player = this.table.getPlayer();

        final Map<Rank, List<PokerCard>> pairs = allCards()
                .collect(Collectors.groupingBy(card -> {
                    return card.card.getRank();
                }))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (isRoyalFlush()) {
            System.out.println("Royal Flush!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return createAllIn();
        } else if (pairs.size() > 0) {
            System.out.println("Pair!!!");
            return createRaiseBet();
        } else {
            System.out.println("...");
            return createFold();
        }
    }

    private Stream<PokerCard> allCards() {
        return Stream
                .concat(this.table.getCards().stream(), this.player.getCards().stream());
    }

    public boolean isRoyalFlush() {
        return isSameColor() && Arrays.asList(Rank.values()).stream()
                .filter(rank -> rank.ordinal() < Rank._9.ordinal())
                .allMatch(rank -> contains(rank));
    }

    public boolean contains(Rank rank) {
        return allCards().anyMatch(card -> card.card.getRank().equals(rank));
    }

    public boolean isSameColor() {
        final Suit suit = allCards().findFirst().get().card.getSuit();
        return allCards().allMatch(card -> suit.equals(card.card));
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
