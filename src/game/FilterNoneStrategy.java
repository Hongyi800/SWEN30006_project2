package game;

import ch.aplu.jcardgame.*;

public class FilterNoneStrategy implements IFilterStrategy {

    private Hand hand;

    public FilterNoneStrategy(Hand hand) {
        setHand(hand);
    }

    @Override
    public Hand filterHand(Hand hand) {
        return hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
