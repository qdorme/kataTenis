package kata.tennis;

public class Game {
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;

    private int playerOnePoints;
    private int playerTwoPoints;

    public Pair currentSetScore() {
        return Pair.builder()
                .player1(playerOnePoints)
                .player2(playerTwoPoints).build();
    }

    public void pointWonByPlayer(int player){
        if (player == PLAYER_ONE) {
            playerOnePoints = updatePoint(playerOnePoints);
        }else{
            playerTwoPoints = updatePoint(playerTwoPoints);
        }
    }

    private int updatePoint(int pointToUdate){
        if(pointToUdate == 15)
            return 30;
        else
            return 15;
    }
}
