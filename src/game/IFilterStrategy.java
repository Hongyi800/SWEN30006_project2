package game;

import ch.aplu.jcardgame.*;

public interface IFilterStrategy {

        Hand filterHand(Hand hand, Whist.Suit trump, Whist.Suit lead);

}
