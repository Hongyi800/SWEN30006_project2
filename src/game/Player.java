package game;

import java.util.ArrayList;
import java.util.Random;

import ch.aplu.jcardgame.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Player {
	static final Random random = ThreadLocalRandom.current();
	private Hand hand;

	public String printHand(ArrayList<Card> cards) {
		String out = "";
		for(int i = 0; i < cards.size(); i++) {
			out += cards.get(i).toString();
			if(i < cards.size()-1) out += ",";
		}
		return(out);
	}

	//get and set select card
	public abstract Card getSelected();


	public Hand getHand() {
		return this.hand;
	}
	public void setHands(Hand hand){
		this.hand = hand;
	}
	public void addToHand(Hand hand) {
		setHands(hand);
	}






}
