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
    public static final String ADVANTAGE = "ADVANTAGE";
    public static final String EMPTY = "";

    private String playerOnePoints = ZERO;
    private String playerTwoPoints = ZERO;
    private DisplayUnit displayUnit;
    private Boolean isGameInDeuce = Boolean.FALSE;
    private int playerOneSets = 0;
    private int playerTwoSets = 0;


    public Game(DisplayUnit displayUnit){
        this.displayUnit = displayUnit;
    }

    public Pair currentGameScore() {
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
        if(oneOfPlayerTakeAdvantage(player)){
            theOtherBecomeEmpty(player);
        }
        if(oneOfPlayerLooseAdvantage(player)){
            theOtherBecomeDeuce(player);
        }
        if(oneOfPlayerWinGame()){
            updateSet(player);
        }
    }

    private void updateSet(int playerWhoWonPoint) {
        if(PLAYER_ONE == playerWhoWonPoint)
            playerOneSets++;
        else
            playerTwoSets++;
    }

    private boolean oneOfPlayerWinGame() {
        return WIN_GAME.equals(playerOnePoints) || WIN_GAME.equals(playerTwoPoints);
    }

    private void theOtherBecomeDeuce(int playerWhoWonPoint) {
        if(playerWhoWonPoint == PLAYER_ONE)
            playerTwoPoints = DEUCE;
        else
            playerOnePoints = DEUCE;
    }

    private boolean oneOfPlayerLooseAdvantage(int playerWhoWonPoint) {
        return playerWhoWonPoint == PLAYER_TWO && ADVANTAGE.equals(playerOnePoints)
               || playerWhoWonPoint == PLAYER_ONE && ADVANTAGE.equals(playerTwoPoints);
    }

    private void theOtherBecomeEmpty(int playerWhoWonPoint) {
        if(playerWhoWonPoint == PLAYER_ONE)
            playerTwoPoints = EMPTY;
        else
            playerOnePoints = EMPTY;
    }

    private boolean oneOfPlayerTakeAdvantage(int playerWhoWonPoint) {
        return playerWhoWonPoint == PLAYER_ONE && ADVANTAGE.equals(playerOnePoints)
               || playerWhoWonPoint == PLAYER_TWO && ADVANTAGE.equals(playerTwoPoints);
    }

    private boolean hasBothPlayerReachedForty() {
        return FORTY.equals(playerOnePoints) && FORTY.equals(playerTwoPoints);
    }

    private String updatePoint(String pointToUpdate){
        if(EMPTY.equals(pointToUpdate))
            return DEUCE;
        if(DEUCE.equals(pointToUpdate))
            return ADVANTAGE;
        if(FORTY.equals(pointToUpdate) || ADVANTAGE.equals(pointToUpdate))
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
            displayUnit.displayGameScore(Pair.builder().player1(playerOnePoints).player2(playerTwoPoints).build());
    }

    public boolean isCurrentGameInDeuce() {
        return isGameInDeuce;
    }

    public Pair currentSetScore() {
        return Pair.builder().player1(playerOneSets).player2(playerTwoSets).build();
    }
}
