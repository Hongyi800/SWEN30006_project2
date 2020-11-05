package game;

import ch.aplu.jcardgame.*;

public class FilterNaiveLegalStrategy implements IFilterStrategy {

    private Hand hand;
    private Whist.Suit lead;
    private Whist.Suit trump;

    public FilterNaiveLegalStrategy(Hand hand, Whist.Suit lead, Whist.Suit trump) {
        this.lead = lead;
        this.trump = trump;
        this.hand = hand;
    }

    @Override
    public Hand filterHand() {
        Hand leadArray = hand.extractCardsWithSuit(lead);
        Hand trumpArray = hand.extractCardsWithSuit(trump);

        if(leadArray.isEmpty() && trumpArray.isEmpty()){
            return hand;
        } else{
            trumpArray.insert(leadArray,false);
            return trumpArray;
        }
    }

}
