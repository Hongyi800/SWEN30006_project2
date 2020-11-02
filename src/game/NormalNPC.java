package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class NormalNPC extends Player{
	public NormalNPC(Hand[] hands,Card select,int id) {
		setHands(hands);
		setSelect(select);
		setId(id);
		setType(1);
	}
}
