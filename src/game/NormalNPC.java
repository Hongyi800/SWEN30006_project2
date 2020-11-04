package game;

import ch.aplu.jcardgame.*;


public class NormalNPC extends Player{

	public NormalNPC(String filterType, String selectType) {
	}

	public Card randomCard(Hand hand){
		int x = random.nextInt(hand.getNumberOfCards());
		return hand.get(x);
	}

	@Override
	public Card getSelected() {
		return randomCard(getHand());
	}

	@Override
	public String getPlayerType() {
		return NORMAL_NPC;
	}
}
