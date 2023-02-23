package org.continuouspoker.player.logic;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.continuouspoker.player.model.Bet;
import org.continuouspoker.player.model.Rank;
import org.continuouspoker.player.model.Table;

public class PokerBot {

   PokerTable table;
   PokerPlayer player;

   public Bet decide(final Table table) {
      System.out.println(table);
      this.table = new PokerTable(table);
      this.player = this.table.getPlayer();

      final Map<Rank, List<PokerCard>> pairs = Stream
              .concat(this.table.getCards().stream(), this.player.getCards().stream())
              .collect(Collectors.groupingBy(card -> {
                 return card.card.getRank();
              })).entrySet().stream().filter(entry -> entry.getValue().size() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

      if(pairs.size() > 0) {
         System.out.println("Pair!!!");
         return createAllIn();
      } else {
         System.out.println("...");
         return createRaiseBet();
      }
   }

   public boolean canCheck() {
      return table.canCheck();
   }

   public Bet createCheckBet() {
      return new Bet().bet(0);
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
