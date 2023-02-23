package org.continuouspoker.player.logic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.continuouspoker.player.model.Rank;
import org.continuouspoker.player.model.Suit;

public class PokerDeck {

    final List<PokerCard> cards;

    public PokerDeck(PokerTable table, PokerPlayer player) {
        this.cards = Stream.concat(table.getCards().stream(), player.getCards().stream()).collect(Collectors.toList());
    }

    public boolean isRoyalFlush() {
        return isSameColor() && Arrays
                .asList(Rank.values())
                .stream()
                .filter(rank -> rank.ordinal() < Rank._9.ordinal())
                .allMatch(rank -> contains(rank));
    }

    public boolean isStraightFlush() {
        final List<PokerCard> cards = this.cards.stream()
                .sorted(Comparator.comparing(card -> card.card.getRank().ordinal()))
                .collect(Collectors.toList());
        int counter = 0;
        PokerCard lastCard = null;
        for (PokerCard card : cards) {
            if (lastCard == null) {
                counter++;
                lastCard = card;
                continue;
            }
            boolean isSameSuit = card.card.getSuit().equals(lastCard.card.getSuit());
            boolean isHigher = card.card.getRank().ordinal() < lastCard.card.getRank().ordinal();
            if (isSameSuit && isHigher) {
                counter++;
                if (counter >= 5) {
                    return true;
                }
            } else {
                counter = 0;
            }
            lastCard = card;
        }
        return false;
    }

    public boolean isStreet() {
        final List<PokerCard> cards = this.cards.stream()
                .sorted(Comparator.comparing(card -> card.card.getRank().ordinal()))
                .collect(Collectors.toList());
        int counter = 0;
        PokerCard lastCard = null;
        for (PokerCard card : cards) {
            if (lastCard == null) {
                counter++;
                lastCard = card;
                continue;
            }
            boolean isHigher = card.card.getRank().ordinal() < lastCard.card.getRank().ordinal();
            if (isHigher) {
                counter++;
                if (counter >= 5) {
                    return true;
                }
            } else {
                counter = 0;
            }
            lastCard = card;
        }
        return false;
    }

    public boolean contains(Rank rank) {
        return cards.stream().anyMatch(card -> card.card.getRank().equals(rank));
    }

    public boolean isSameColor() {
        final Suit suit = cards.stream().findFirst().get().card.getSuit();
        return cards.stream().allMatch(card -> suit.equals(card.card));
    }

    public Map<Rank, List<PokerCard>> getMultiples() {
        return this.cards.stream()
                .collect(Collectors.groupingBy(card -> {
                    return card.card.getRank();
                }))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
