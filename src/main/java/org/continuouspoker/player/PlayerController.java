package org.continuouspoker.player;

import org.continuouspoker.player.api.DefaultApiDelegate;
import org.continuouspoker.player.model.Bet;
import org.continuouspoker.player.model.Table;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.continuouspoker.player.logic.PokerBot;

@Service
public class PlayerController implements DefaultApiDelegate {

   @Override
   public ResponseEntity<Bet> getBet(final Table table) {
      final PokerBot player = new PokerBot();
      return ResponseEntity.ok(player.decide(table));
   }

}
