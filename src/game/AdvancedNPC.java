package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class AdvancedNPC extends Player{

	public AdvancedNPC(String filterType, String selectType) {
	}

	@Override
	public Card getSelected() {
		return null;
	}

	@Override
	public String getPlayerType() {
		return ADVANCED_NPC;
	}

}
