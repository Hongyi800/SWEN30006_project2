package game;

import java.util.ArrayList;
import java.util.Random;

import ch.aplu.jcardgame.*;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Player {

	public static final Random random = ThreadLocalRandom.current();
	private Hand hand;
	public final String HUMAN = "interactive";
	public final String NORMAL_NPC = "normal";
	public final String ADVANCED_NPC = "advanced";

	public final String RANDOM_SELECT = "random";  // random selection
	public final String SMART = "smart";  // smart selection
	public final String HIGHEST = "highest";  // highest rank selection


	public String printHand(ArrayList<Card> cards) {
		String out = "";
		for(int i = 0; i < cards.size(); i++) {
			out += cards.get(i).toString();
			if(i < cards.size()-1) out += ",";
		}
		return(out);
	}

	//get selected card
	public abstract Card getSelected(Whist.Suit lead, Whist.Suit trump, Hand originHand);

	public abstract String getPlayerType();


	public void setHands(Hand hand){
		this.hand = hand;
	}

	public void addToHand(Hand hand) {
		setHands(hand);
	}
}
