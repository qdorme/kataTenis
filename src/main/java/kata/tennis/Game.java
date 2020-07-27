package kata.tennis;

import java.util.Objects;

public class Game {
    public static final Integer PLAYER_ONE = 1;
    public static final Integer PLAYER_TWO = 2;

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
    private final DisplayUnit displayUnit;
    private Boolean isGameInDeuce = Boolean.FALSE;
    private Boolean isGameInTieBreak = Boolean.FALSE;
    private Integer playerOneSets = 0;
    private Integer playerTwoSets = 0;
    private Integer setWinner;


    public Game(DisplayUnit displayUnit){
        this.displayUnit = displayUnit;
    }

    public Pair currentGameScore() {
        return Pair.builder()
                .player1(playerOnePoints)
                .player2(playerTwoPoints).build();
    }

    public void pointWonByPlayer(Integer player){
        if (PLAYER_ONE.equals(player)) {
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
            if(hasBothPlayerReachedSixSets()){
                isGameInTieBreak = Boolean.TRUE;
            }
            if(hasPlayerWonSet(player)){
                setWinner = player;
            }
        }
    }

    private boolean hasBothPlayerReachedSixSets() {
        return playerOneSets == 6 && playerTwoSets == 6;
    }

    private boolean hasPlayerWonSet(Integer player) {
        if(PLAYER_ONE.equals(player))
            return (playerOneSets == 6 && playerTwoSets <=4)
                   || (playerOneSets == 7 && !isGameInTieBreak)
                   || isGameInTieBreak && playerOneSets - playerTwoSets == 2;
        else
            return (playerTwoSets == 6 && playerOneSets <=4)
                   || (playerTwoSets == 7 && !isGameInTieBreak)
                   || isGameInTieBreak && playerTwoSets - playerOneSets == 2;
    }

    private void updateSet(Integer playerWhoWonPoint) {
        if(PLAYER_ONE.equals(playerWhoWonPoint))
            playerOneSets++;
        else
            playerTwoSets++;
        resetBothPlayersPoints();
    }

    private void resetBothPlayersPoints() {
        playerOnePoints=ZERO;
        playerTwoPoints=ZERO;
        isGameInDeuce=Boolean.FALSE;
    }

    private boolean oneOfPlayerWinGame() {
        return WIN_GAME.equals(playerOnePoints) || WIN_GAME.equals(playerTwoPoints);
    }

    private void theOtherBecomeDeuce(Integer playerWhoWonPoint) {
        if(PLAYER_ONE.equals(playerWhoWonPoint))
            playerTwoPoints = DEUCE;
        else
            playerOnePoints = DEUCE;
    }

    private boolean oneOfPlayerLooseAdvantage(Integer playerWhoWonPoint) {
        return PLAYER_TWO.equals(playerWhoWonPoint) && ADVANTAGE.equals(playerOnePoints)
               || PLAYER_ONE.equals(playerWhoWonPoint) && ADVANTAGE.equals(playerTwoPoints);
    }

    private void theOtherBecomeEmpty(Integer playerWhoWonPoint) {
        if(PLAYER_ONE.equals(playerWhoWonPoint))
            playerTwoPoints = EMPTY;
        else
            playerOnePoints = EMPTY;
    }

    private boolean oneOfPlayerTakeAdvantage(Integer playerWhoWonPoint) {
        return PLAYER_ONE.equals(playerWhoWonPoint) && ADVANTAGE.equals(playerOnePoints)
               || PLAYER_TWO.equals(playerWhoWonPoint) && ADVANTAGE.equals(playerTwoPoints);
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

    public Integer winnerOfSetIs() {
        return setWinner;
    }

    public void displayPlayersSetScores() {
        if(Objects.nonNull(displayUnit))
            displayUnit.displaySetsScore(Pair.builder().player1(playerOneSets).player2(playerTwoSets).build(),setWinner);
    }

    public boolean isCurrentGameInTieBreak() {
        return isGameInTieBreak;
    }
}
