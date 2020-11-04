package game;

import ch.aplu.jcardgame.*;

import java.util.ArrayList;

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

        System.out.println("curLead:"+leadArray.getCardList().toString());
        System.out.println("curtrump:"+trumpArray.getCardList().toString());

        if(leadArray.isEmpty() && trumpArray.isEmpty()){
            return hand;
        } else{
//            int numLead = leadArray.getNumberOfCards();
//            for(int i = 0; i< numLead; i++){
//                System.out.println("curCard:"+leadArray.get(i));
//                leadArray.get(i).transfer(trumpArray, false);
//            }
            trumpArray.insert(leadArray,false);
            System.out.println("curcombine:"+trumpArray.getCardList().toString());

            return trumpArray;
        }
        //return hand;
    }

}
