import com.chinesecheckers.server.player.HumanPlayer;
import com.chinesecheckers.server.player.Player;
import com.chinesecheckers.server.board.Board;
import com.chinesecheckers.server.board.Field;
import com.chinesecheckers.server.game.GameData;

class ChineseCheckersTest {

    private String[] nicks = { "pat", "mat", "sat", "war", "jar", "fer" };

    GameData createDummy2PlayerGameData() {
        int numOfPlayers = 2;
        Player player1 = new HumanPlayer(nicks[0]);
        Player player2 = new HumanPlayer(nicks[1]);

        Player[] players = { player1, player2 };

        return new GameData(numOfPlayers, players);
    }

    GameData createDummy3PlayerGameData() {
        int numOfPlayers = 3;
        Player player1 = new HumanPlayer(nicks[0]);
        Player player2 = new HumanPlayer(nicks[1]);
        Player player3 = new HumanPlayer(nicks[2]);

        Player[] players = { player1, player2, player3 };

        return new GameData(numOfPlayers, players);
    }

    GameData createDummy4PlayerGameData() {
        int numOfPlayers = 4;
        Player player1 = new HumanPlayer(nicks[0]);
        Player player2 = new HumanPlayer(nicks[1]);
        Player player3 = new HumanPlayer(nicks[2]);
        Player player4 = new HumanPlayer(nicks[3]);

        Player[] players = { player1, player2, player3, player4 };

        return new GameData(numOfPlayers, players);
    }

    GameData createDummy6PlayerGameData() {
        int numOfPlayers = 6;
        Player player1 = new HumanPlayer(nicks[0]);
        Player player2 = new HumanPlayer(nicks[1]);
        Player player3 = new HumanPlayer(nicks[2]);
        Player player4 = new HumanPlayer(nicks[3]);
        Player player5 = new HumanPlayer(nicks[4]);
        Player player6 = new HumanPlayer(nicks[5]);

        Player[] players = { player1, player2, player3, player4, player5, player6 };

        return new GameData(numOfPlayers, players);
    }

    void printBoard(Board board) {

        Field[][] fields = board.getFields();

        for (Field[] field1 : fields) {
            for (Field field : field1) {
                if (field != null) {
                    if (field.getPlayer() == null) {
                        System.out.print('*');
                    } else if (field.getPlayer().getNick().equals(nicks[0])) {
                        System.out.print('P');
                    } else if (field.getPlayer().getNick().equals(nicks[1])) {
                        System.out.print('M');
                    } else if (field.getPlayer().getNick().equals(nicks[2])) {
                        System.out.print('S');
                    } else if (field.getPlayer().getNick().equals(nicks[3])) {
                        System.out.print('W');
                    } else if (field.getPlayer().getNick().equals(nicks[4])) {
                        System.out.print('J');
                    } else if (field.getPlayer().getNick().equals(nicks[5])) {
                        System.out.print('F');
                    }
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
