package kata.tennis;

import java.util.Objects;

public class Game {

    private String playerOnePoints = Constants.ZERO;
    private String playerTwoPoints = Constants.ZERO;
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
        if (Constants.PLAYER_ONE.equals(player)) {
            playerOnePoints = updatePoint(playerOnePoints);
        }else{
            playerTwoPoints = updatePoint(playerTwoPoints);
        }
        if(hasBothPlayerReachedForty()){
            isGameInDeuce = Boolean.TRUE;
            playerOnePoints = Constants.DEUCE;
            playerTwoPoints = Constants.DEUCE;
        }
        if(oneOfPlayerTakeAdvantage(player)){
            theOtherBecome(player, Constants.EMPTY);
        }
        if(oneOfPlayerLooseAdvantage(player)){
            theOtherBecome(player, Constants.DEUCE);
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
        if(Constants.PLAYER_ONE.equals(player))
            return (playerOneSets == 6 && playerTwoSets <=4)
                   || (playerOneSets == 7 && !isGameInTieBreak)
                   || isGameInTieBreak && playerOneSets - playerTwoSets == 2;
        else
            return (playerTwoSets == 6 && playerOneSets <=4)
                   || (playerTwoSets == 7 && !isGameInTieBreak)
                   || isGameInTieBreak && playerTwoSets - playerOneSets == 2;
    }

    private void updateSet(Integer playerWhoWonPoint) {
        if(Constants.PLAYER_ONE.equals(playerWhoWonPoint))
            playerOneSets++;
        else
            playerTwoSets++;
        resetBothPlayersPoints();
    }

    private void resetBothPlayersPoints() {
        playerOnePoints= Constants.ZERO;
        playerTwoPoints= Constants.ZERO;
        isGameInDeuce=Boolean.FALSE;
    }

    private boolean oneOfPlayerWinGame() {
        return Constants.WIN_GAME.equals(playerOnePoints) || Constants.WIN_GAME.equals(playerTwoPoints);
    }

    private void theOtherBecome(Integer playerWhoWonPoint, String newScore) {
        if(Constants.PLAYER_ONE.equals(playerWhoWonPoint))
            playerTwoPoints = newScore;
        else
            playerOnePoints = newScore;
    }

    private boolean oneOfPlayerLooseAdvantage(Integer playerWhoWonPoint) {
        return Constants.PLAYER_TWO.equals(playerWhoWonPoint) && Constants.ADVANTAGE.equals(playerOnePoints)
               || Constants.PLAYER_ONE.equals(playerWhoWonPoint) && Constants.ADVANTAGE.equals(playerTwoPoints);
    }

    private boolean oneOfPlayerTakeAdvantage(Integer playerWhoWonPoint) {
        return Constants.PLAYER_ONE.equals(playerWhoWonPoint) && Constants.ADVANTAGE.equals(playerOnePoints)
               || Constants.PLAYER_TWO.equals(playerWhoWonPoint) && Constants.ADVANTAGE.equals(playerTwoPoints);
    }

    private boolean hasBothPlayerReachedForty() {
        return Constants.FORTY.equals(playerOnePoints) && Constants.FORTY.equals(playerTwoPoints);
    }

    private String updatePoint(String pointToUpdate){
        if(Constants.EMPTY.equals(pointToUpdate))
            return Constants.DEUCE;
        if(Constants.DEUCE.equals(pointToUpdate))
            return Constants.ADVANTAGE;
        if(Constants.FORTY.equals(pointToUpdate) || Constants.ADVANTAGE.equals(pointToUpdate))
            return Constants.WIN_GAME;
        if(Constants.THIRTY.equals(pointToUpdate))
            return Constants.FORTY;
        if(Constants.FIFTEEN.equals(pointToUpdate))
            return Constants.THIRTY;
        else
            return Constants.FIFTEEN;
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
