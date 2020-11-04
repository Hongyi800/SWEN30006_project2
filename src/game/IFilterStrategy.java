package game;

import ch.aplu.jcardgame.*;

public interface IFilterStrategy {
        Hand hand = null;
        Hand filterHand(Hand hand);

        Hand getHand();
        void setHand(Hand hand);
}
