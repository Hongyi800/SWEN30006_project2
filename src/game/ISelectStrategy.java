package game;

import ch.aplu.jcardgame.*;

public interface ISelectStrategy {
    Card makeSelectStrategy(Hand hand);

    void getWinCardAndTrump(Card currentWinCard, Whist.Suit trump);
}
