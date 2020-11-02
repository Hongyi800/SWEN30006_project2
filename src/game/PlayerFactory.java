package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class PlayerFactory {
	public Player newPlayer(int type,Hand[] hands,Card select,int id) {
		if(type == 0) {
			return new InteractPlayer(hands,select,id);
		}else if(type == 1) {
			return new NormalNPC(hands,select,id);
		}else if(type == 2) {
			return new AdvancedNPC(hands,select,id);
		}else {
			return null;
		}
	}
}
