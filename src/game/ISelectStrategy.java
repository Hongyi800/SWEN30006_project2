package game;

import ch.aplu.jcardgame.*;

public interface ISelectStrategy {
    public Card makeSelectStrategy(Hand hand);

    void getWinCardAndTrump(Card currentWinCard, Whist.Suit trump);
}
