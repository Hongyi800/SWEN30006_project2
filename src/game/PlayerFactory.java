package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class PlayerFactory {
	public Player createPlayer(String playerOption,int filterType, int selectType,Hand hand) {
		if(playerOption.contentEquals("interactive")) {
			return new InteractPlayer(hand);
		}else if(playerOption.contentEquals("normal")) {
			return new NormalNPC(hand);
		}else if(playerOption.contentEquals("advanced")) {
			return new AdvancedNPC();
		}else {
			return null;
		}
	}
}
