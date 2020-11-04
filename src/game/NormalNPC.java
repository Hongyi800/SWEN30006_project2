package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;


public class NormalNPC extends Player{

	public NormalNPC() {
	}

	public Card randomCard(Hand hand){
		int x = random.nextInt(hand.getNumberOfCards());
		return hand.get(x);
	}

	@Override
	public Card getSelected() {
		return randomCard(getHand());
	}
}
