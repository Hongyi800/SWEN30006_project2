package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class InteractPlayer extends Player{
	public InteractPlayer(Hand[] hands,Card select,int id) {
		setHands(hands);
		setSelect(select);
		setId(id);
		setType(0);
	}
	
}
