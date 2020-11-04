package game;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

// This class is used to read properties and setup players
public class PropertyReader {

    private ArrayList<Player> players;
    private PlayerFactory playerFactory;
    private String propertyName;
    private int nbPlayers;

    // setup game
    public PropertyReader(String propertyName){
        this.propertyName = propertyName;
    }

    public Properties setUpProperties() throws IOException {
        Properties properties = new Properties();

        // Read properties
        FileReader inStream = null;
        try {
            inStream = new FileReader(propertyName);
            properties.load(inStream);
        } finally {
            if (inStream != null) {
                inStream.close();
            }
        }

        // setup players
        nbPlayers = Integer.parseInt(properties.getProperty("player_num"));
        playerFactory = new PlayerFactory();
        players = new ArrayList<>(nbPlayers);

        String playerType;
        String filterType;
        String selectType;

        for (int i = 0; i < nbPlayers; i++) {
            // get the type of this player
            playerType = properties.getProperty("player_" + i);
            // get the filter strategy of this player
            filterType = properties.getProperty("filter_" + i);
            // get the select strategy of this player
            selectType = properties.getProperty("select_" + i);

            System.out.println("playerType: " + playerType);
            System.out.println("filterType: " + filterType);
            System.out.println("selectType: " + selectType);

            players.add(i, playerFactory.createPlayer(playerType, filterType, selectType));
            System.out.println(players);
        }
        return properties;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
