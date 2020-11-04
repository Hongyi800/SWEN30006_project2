package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class PlayerFactory {

<<<<<<< HEAD
=======
	public Player createPlayer(String playerOption, String filterType, String selectType) {
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
>>>>>>> 2ebff9921bb993fe4e64731df82d2e1f27d48a3b
}
