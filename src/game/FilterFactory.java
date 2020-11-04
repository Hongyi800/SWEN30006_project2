package game;

import ch.aplu.jcardgame.*;

public class FilterFactory {
    IFilterStrategy iFilterStrategy;

    public Hand filter(String filterType, Hand hand, Whist.Suit trump, Whist.Suit lead){
        if(filterType.equals("none")) {

            iFilterStrategy = new FilterNoneStrategy();

        }else if(filterType.equals("legal")) {

            iFilterStrategy = new FilterNaiveLegalStrategy();

        }else if(filterType.equals("trump")) {

            iFilterStrategy = new FilterTrumpSavingStrategy();

        }else {
            return null;
        }
        return iFilterStrategy.filterHand(hand,trump,lead);
    }
}
