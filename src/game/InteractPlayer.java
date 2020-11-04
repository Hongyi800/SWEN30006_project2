package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class InteractPlayer extends Player{

	public InteractPlayer() {
	}

	@Override
	public Card getSelected(Whist.Suit lead, Whist.Suit trump) {
		return null;
	}

	@Override
	public String getPlayerType() {
		return HUMAN;
	}
}
