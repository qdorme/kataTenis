package kata.tennis;

import java.util.Objects;

public class Game {
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;

    public static final String ZERO = "0";
    public static final String FIFTEEN = "15";
    public static final String THIRTY = "30";
    public static final String FORTY = "40";
    public static final String WIN_GAME = "Win game";
    public static final String DEUCE = "DEUCE";

    private String playerOnePoints = ZERO;
    private String playerTwoPoints = ZERO;
    private DisplayUnit displayUnit;
    private Boolean isGameInDeuce = Boolean.FALSE;

    public Game(DisplayUnit displayUnit){
        this.displayUnit = displayUnit;
    }

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
        if(hasBothPlayerReachedForty()){
            isGameInDeuce = Boolean.TRUE;
            playerOnePoints = DEUCE;
            playerTwoPoints = DEUCE;
        }
    }

    private boolean hasBothPlayerReachedForty() {
        return FORTY.equals(playerOnePoints) && FORTY.equals(playerTwoPoints);
    }

    private String updatePoint(String pointToUpdate){
        if(FORTY.equals(pointToUpdate))
            return WIN_GAME;
        if(THIRTY.equals(pointToUpdate))
            return FORTY;
        if(FIFTEEN.equals(pointToUpdate))
            return THIRTY;
        else
            return FIFTEEN;
    }

    public void displayPlayersScores(){
        if(Objects.nonNull(displayUnit))
            displayUnit.display(Pair.builder().player1(playerOnePoints).player2(playerTwoPoints).build());
    }

    public boolean isCurrentGameInDeuce() {
        return isGameInDeuce;
    }
}
