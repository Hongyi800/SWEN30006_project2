package game;

import ch.aplu.jcardgame.*;

import java.util.ArrayList;

public class FilterNaiveLegalStrategy implements IFilterStrategy {

    public FilterNaiveLegalStrategy() {

    }

    @Override
    public Hand filterHand(Hand originHand, Whist.Suit trump,Whist.Suit lead) {
        Hand leadArray = originHand.extractCardsWithSuit(lead);
        Hand trumpArray = originHand.extractCardsWithSuit(trump);
        if(leadArray.isEmpty()&&trumpArray.isEmpty()){
            return originHand;
        }else{
            int numLead = leadArray.getNumberOfCards();
            for(int i = 0; i< numLead; i++){
                leadArray.get(i).transfer(trumpArray, false);
            }
            return trumpArray;
        }
    }

}
