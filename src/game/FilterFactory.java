package game;

import ch.aplu.jcardgame.*;

public class FilterFactory {
    IFilterStrategy iFilterStrategy;
    //filter type
    //0: none filter
    //1: NaiveLegal
    //2: trumpSaving
    public Hand filter(int filterType, Hand hand){
        if(filterType == 0) {

            iFilterStrategy = new FilterNoneStrategy(hand);

        }else if(filterType == 1) {

            iFilterStrategy = new FilterNaiveLegalStrategy(hand);

        }else if(filterType == 2) {

            iFilterStrategy = new FilterTrumpSavingStrategy(hand);

        }else {
            return null;
        }
        return iFilterStrategy.filterHand(hand);
    }
}
