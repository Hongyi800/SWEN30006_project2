package game;

public class SelectFactory{

    public final String RANDOM_SELECT = "random";  // random selection
    public final String SMART = "smart";  // smart selection
    public final String HIGHEST = "highest";  // highest rank selection

    public ISelectStrategy chooseSelectStrategy(String selectStrategy) {
        // choose which select method it will use

        switch (selectStrategy) {
            //do random selection
            case RANDOM_SELECT:
                return new SelectRandomStrategy();
            //do highest rank select
            case HIGHEST:
                return new SelectHighestRankStrategy();
            //do smart selection
            case SMART:
                return new SelectSmartStrategy();
        }

        return null;
    }
}
