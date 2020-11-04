package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class InteractPlayer extends Player{
	Card selected = null;

	public InteractPlayer(String filterType, String selectType) {
	}

	@Override
	public Card getSelected() {
		return null;
	}

	@Override
	public String getPlayerType() {
		return HUMAN;
	}
}
