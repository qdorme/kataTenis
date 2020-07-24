package kata.tennis;

public class Game {

    private int playerOnePoints;
    private int playerTwoPoints;

    public Pair currentSetScore() {
        return Pair.builder()
                .player1(playerOnePoints)
                .player2(playerTwoPoints).build();
    }

    public void pointWonByPlayer(int i) {
        if(i==1)
            playerOnePoints = 15;
        else
            playerTwoPoints =15;
    }
}
