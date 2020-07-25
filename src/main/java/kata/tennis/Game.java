package kata.tennis;

public class Game {
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;

    private String playerOnePoints = "0";
    private String playerTwoPoints = "0";

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

    private String updatePoint(String pointToUpdate){
        if("40".equals(pointToUpdate))
            return "Win game";
        if("30".equals(pointToUpdate))
            return "40";
        if("15".equals(pointToUpdate))
            return "30";
        else
            return "15";
    }
}
