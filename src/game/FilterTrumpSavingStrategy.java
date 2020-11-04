package game;

import ch.aplu.jcardgame.*;

public class FilterTrumpSavingStrategy implements IFilterStrategy {

    public FilterTrumpSavingStrategy() {

    }

    @Override
    public Hand filterHand(Hand originHand,Whist.Suit trump,Whist.Suit lead) {
        Hand leadArray = originHand.extractCardsWithSuit(lead);
        Hand trumpArray = originHand.extractCardsWithSuit(trump);
        if(leadArray.isEmpty()&&trumpArray.isEmpty()){
            return originHand;
        }else if(!leadArray.isEmpty()){
            return leadArray;
        }else{
            return trumpArray;
        }
    }

}
