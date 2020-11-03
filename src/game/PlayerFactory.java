package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class PlayerFactory {
	public Player makePlayer(String playerOption) {
		if(playerOption.contentEquals("interactive")) {
			return new InteractPlayer();
		}else if(playerOption.contentEquals("normal")) {
			return new NormalNPC();
		}else if(playerOption.contentEquals("advanced")) {
			return new AdvancedNPC();
		}else {
			return null;
		}
	}
}
