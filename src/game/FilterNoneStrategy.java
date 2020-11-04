package game;

import ch.aplu.jcardgame.*;

public class FilterNoneStrategy implements IFilterStrategy {

    public FilterNoneStrategy() {

    }

    @Override
    public Hand filterHand(Hand hand,Whist.Suit trump,Whist.Suit lead) {
        return hand;
    }

}
