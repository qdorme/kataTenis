package kata.tennis;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;
import static kata.tennis.Constants.*;

public class Game {

    private String playerOnePoints = ZERO;
    private String playerTwoPoints = ZERO;
    private final DisplayUnit displayUnit;
    private Boolean isGameInDeuce = FALSE;
    private Boolean isGameInTieBreak = FALSE;
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
            isGameInDeuce = TRUE;
            playerOnePoints = DEUCE;
            playerTwoPoints = DEUCE;
        }
        if(oneOfPlayerTakeAdvantage(player)){
            theOtherBecome(player, EMPTY);
        }
        if(oneOfPlayerLooseAdvantage(player)){
            theOtherBecome(player, DEUCE);
        }
        if(oneOfPlayerWinGame()){
            updateSet(player);
            if(hasBothPlayerReachedSixSets()){
                isGameInTieBreak = TRUE;
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
        playerOnePoints= ZERO;
        playerTwoPoints= ZERO;
        isGameInDeuce= FALSE;
    }

    private boolean oneOfPlayerWinGame() {
        return WIN_GAME.equals(playerOnePoints) || WIN_GAME.equals(playerTwoPoints);
    }

    private void theOtherBecome(Integer playerWhoWonPoint, String newScore) {
        if(PLAYER_ONE.equals(playerWhoWonPoint))
            playerTwoPoints = newScore;
        else
            playerOnePoints = newScore;
    }

    private boolean oneOfPlayerLooseAdvantage(Integer playerWhoWonPoint) {
        return PLAYER_TWO.equals(playerWhoWonPoint) && ADVANTAGE.equals(playerOnePoints)
               || PLAYER_ONE.equals(playerWhoWonPoint) && ADVANTAGE.equals(playerTwoPoints);
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
        if(nonNull(displayUnit))
            displayUnit.displayGameScore(Pair.builder().player1(playerOnePoints).player2(playerTwoPoints).build());
    }

    public boolean isGameInDeuce() {
        return isGameInDeuce;
    }

    public Pair currentSetScore() {
        return Pair.builder().player1(playerOneSets).player2(playerTwoSets).build();
    }

    public Integer winnerOfSetIs() {
        return setWinner;
    }

    public void displayPlayersSetScores() {
        if(nonNull(displayUnit))
            displayUnit.displaySetsScore(Pair.builder().player1(playerOneSets).player2(playerTwoSets).build(),setWinner);
    }

    public boolean isGameInTieBreak() {
        return isGameInTieBreak;
    }
}
