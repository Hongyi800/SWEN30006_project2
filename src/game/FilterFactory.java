package game;

import ch.aplu.jcardgame.*;

public class FilterFactory {

    public final String NO_FILTERING= "none";
    public final String LEGAL = "legal";  // naive legal approach
    public final String TRUMP = "trump";  // trump saving approach

    public IFilterStrategy filter(String filterType, Hand hand, Whist.Suit lead, Whist.Suit trump){
        if(filterType.equals(NO_FILTERING)) {
            return new FilterNoneStrategy(hand, lead, trump);
        }else if(filterType.equals(LEGAL)) {
            return new FilterNaiveLegalStrategy(hand, lead, trump);
        }else if(filterType.equals(TRUMP)) {
            return new FilterTrumpSavingStrategy(hand, lead, trump);
        }else {
            return null;
        }
    }
}
