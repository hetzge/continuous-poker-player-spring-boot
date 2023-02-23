package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.Bet;
import org.continuouspoker.player.model.Table;

public class PokerBot {

   PokerTable table;
   PokerPlayer player;

   public Bet decide(final Table table) {
      System.out.println(table);
      this.table = new PokerTable(table);
      this.player = this.table.getPlayer();
      return createRaiseBet();
   }

   public boolean canCheck() {
      return table.canCheck();
   }

   public Bet createCheckBet() {
      return new Bet().bet(0);
   }

   public Bet createRaiseBet() {
      return new Bet().bet(player.getRaiseBet(this.table));
   }

   public Bet createFold() {
      return new Bet().bet(0);
   }

}
