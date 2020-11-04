package game;

import java.util.ArrayList;

import ch.aplu.jcardgame.*;

public abstract class Player {
	private Hand hand;
	private Card select;
	private int id;
	private int type;

	public Card takeTurn() {
		return select;
	}

	public void printState() {
		System.out.println("Player "+getId()+" play: "+getSelected().toString()+" from ["+printHand(hand.getCardList())+"]");
	}

	public String printHand(ArrayList<Card> cards) {
		String out = "";
		for(int i = 0; i < cards.size(); i++) {
			out += cards.get(i).toString();
			if(i < cards.size()-1) out += ",";
		}
		return(out);
	}

	//get and set select card
	public Card getSelected() {
		return select;
	}
	public void setSelect(Card select) {
		this.select = select;
	}

	//get and set player id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	//get and set hand
	public Hand getHand() {
		return hand;
	}

	public void setHands(Hand hand) {
		this.hand = hand;
	}

	//get and set player type
	//0: interactive player
	//1: normal NPC
	//2: advanced NPC
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


}
