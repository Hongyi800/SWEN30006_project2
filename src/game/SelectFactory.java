package game;

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import static game.Whist.randomCard;

public class SelectFactory{
    public static ISelectStrategy chooseSelectStrategy(String selectStrategy) {
        // choose which select method it will use
        switch (selectStrategy) {
            //do random selection
            case "random":
                return new SelectRandomStrategy();
            //do highest rank select
            case "HighestRank":
                return new SelectHighestRankStrategy();
            //do smart selection
            case "smart":
                return new SelectSmartStrategy();
        }
        return null;
    }
}
