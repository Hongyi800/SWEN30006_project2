package game;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private String  propertyName;
    private int nbPlayers;
    private int nbStartCards;
    private int winningScore;


    public PropertyReader(String propertyName){
        this.propertyName = propertyName;
    }

    // copied from swen30006 project 1
    private Properties setUpProperties() throws IOException {
        Properties properties = new Properties();
        // Default properties
        // automailProperties.setProperty("Robots", "Big,Careful,Standard,Weak");


        // Read properties
        FileReader inStream = null;
        try {
            inStream = new FileReader("whist.properties");
            properties.load(inStream);
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }

        setNbPlayers(Integer.parseInt(properties.getProperty("player_num")));

        setNbStartCards(Integer.parseInt(properties.getProperty("nbStartCards")));

        setWinningScore(Integer.parseInt(properties.getProperty("winningScore")));

        MAIL_MAX_WEIGHT = Integer.parseInt(automailProperties.getProperty("Mail_Max_Weight"));
        System.out.println("Maximum weight: " + MAIL_MAX_WEIGHT);
        // Last_Delivery_Time
        Clock.MAIL_RECEVING_LENGTH = Integer.parseInt(automailProperties.getProperty("Mail_Receving_Length"));
        System.out.println("Mail receving length: " + Clock.MAIL_RECEVING_LENGTH);
        // Overdrive ability
        DELIVER_FOOD = Boolean.parseBoolean(automailProperties.getProperty("DeliverFood"));
        OVERDRIVE_ENABLED = Boolean.parseBoolean(automailProperties.getProperty("Overdrive"));
        System.out.println("Overdrive enabled: " + OVERDRIVE_ENABLED);
        // Statistics tracking
        STATISTICS_ENABLED = Boolean.parseBoolean(automailProperties.getProperty("Statistics"));
        System.out.println("Statistics enabled: " + STATISTICS_ENABLED);
        // Robots
        NUM_ROBOTS = Integer.parseInt(automailProperties.getProperty("Robots"));
        System.out.print("#Robots: "); System.out.println(NUM_ROBOTS);
        assert(NUM_ROBOTS > 0);

        return automailProperties;
    }

    public int getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(int num) {
        this.nbPlayers = num;
    }

    public int getNbStartCards() {
        return nbStartCards;
    }

    public void setNbStartCards(int nbStartCards) {
        this.nbStartCards = nbStartCards;
    }

    public int getWinningScore() {
        return winningScore;
    }

    public void setWinningScore(int score) {
        this.winningScore = score;
    }
}
