package game;

import ch.aplu.jcardgame.*;

public class FilterTrumpSavingStrategy implements IFilterStrategy {

    private Hand hand;

    public FilterTrumpSavingStrategy(Hand hand) {
        setHand(hand);
    }

    @Override
    public Hand filterHand(Hand hand) {
        return null;
    }

    @Override
    public Hand getHand() {
        return null;
    }

    @Override
    public void setHand(Hand hand) {

    }
}
