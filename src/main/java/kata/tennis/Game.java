package kata.tennis;

public class Game {

    private int point;

    public Pair currentSetScore() {
        return Pair.builder().player1(0).player2(point).build();
    }

    public void pointWonByPlayer() {
        point=15;
    }
}
