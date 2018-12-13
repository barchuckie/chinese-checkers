import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.game.GameData;

import java.io.IOException;
import java.net.Socket;

class ChineseCheckersTest {

    GameData createDummy3PlayerGameData(String[] nicks) {
        try {
            int numOfPlayers = 3;
            Player player1 = new Player(nicks[0], new Socket("127.0.0.1", 8901));
            Player player2 = new Player(nicks[1], new Socket("127.0.0.1", 8901));
            Player player3 = new Player(nicks[2], new Socket("127.0.0.1", 8901));

            Player[] players = {player1, player2, player3};

            return new GameData(numOfPlayers, players);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    GameData createDummy2PlayerGameData(String[] nicks) {
        try {
            int numOfPlayers = 2;
            Player player1 = new Player(nicks[0], new Socket("127.0.0.1", 8901));
            Player player2 = new Player(nicks[1], new Socket("127.0.0.1", 8901));

            Player[] players = { player1, player2 };

            return new GameData(numOfPlayers, players);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
