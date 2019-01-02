import com.chinesecheckers.server.Player;
import com.chinesecheckers.server.game.GameData;

class ChineseCheckersTest {

    GameData createDummy2PlayerGameData(String[] nicks) {
        int numOfPlayers = 2;
        Player player1 = new Player(nicks[0]);
        Player player2 = new Player(nicks[1]);

        Player[] players = { player1, player2 };

        return new GameData(numOfPlayers, players);
    }

    GameData createDummy3PlayerGameData(String[] nicks) {
        int numOfPlayers = 3;
        Player player1 = new Player(nicks[0]);
        Player player2 = new Player(nicks[1]);
        Player player3 = new Player(nicks[2]);

        Player[] players = { player1, player2, player3 };

        return new GameData(numOfPlayers, players);
    }

    GameData createDummy4PlayerGameData(String[] nicks) {
        int numOfPlayers = 4;
        Player player1 = new Player(nicks[0]);
        Player player2 = new Player(nicks[1]);
        Player player3 = new Player(nicks[2]);
        Player player4 = new Player(nicks[3]);

        Player[] players = { player1, player2, player3, player4 };

        return new GameData(numOfPlayers, players);
    }

    GameData createDummy6PlayerGameData(String[] nicks) {
        int numOfPlayers = 6;
        Player player1 = new Player(nicks[0]);
        Player player2 = new Player(nicks[1]);
        Player player3 = new Player(nicks[2]);
        Player player4 = new Player(nicks[3]);
        Player player5 = new Player(nicks[4]);
        Player player6 = new Player(nicks[5]);

        Player[] players = { player1, player2, player3, player4, player5, player6 };

        return new GameData(numOfPlayers, players);
    }
}
