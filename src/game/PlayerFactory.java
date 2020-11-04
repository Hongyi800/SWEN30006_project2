package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class PlayerFactory {
	public final String HUMAN = "interactive";
	public final String NORMAL_NPC = "normal";
	public final String ADVANCED_NPC = "advanced";

	public Player createPlayer(String playerOption, String filterType, String selectType) {
		if(playerOption.equals(HUMAN)) {
			return new InteractPlayer(filterType, selectType);
		}else if(playerOption.contentEquals(NORMAL_NPC)) {
			return new NormalNPC(filterType, selectType);
		}else if(playerOption.contentEquals(ADVANCED_NPC)) {
			return new AdvancedNPC(filterType, selectType);
		}else {
			return null;
		}
	}

}
