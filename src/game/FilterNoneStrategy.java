package game;

import ch.aplu.jcardgame.*;

public class FilterNoneStrategy implements IFilterStrategy {

    private Hand hand;

    public FilterNoneStrategy(Hand hand, Whist.Suit lead, Whist.Suit trump) {
        this.hand = hand;
    }

    @Override
    public Hand filterHand() {
        return hand;
    }

}
