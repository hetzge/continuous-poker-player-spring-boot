package org.continuouspoker.player.logic;

import java.util.Objects;

import org.continuouspoker.player.model.Card;

public class PokerCard {

    final Card card;

    public PokerCard(final Card card) {
        this.card = card;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PokerCard pokerCard = (PokerCard) o;
        return Objects.equals(card, pokerCard.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card);
    }

}
