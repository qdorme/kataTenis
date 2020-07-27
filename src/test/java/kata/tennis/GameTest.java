package kata.tennis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameTest {
    Game game;

    @Mock
    DisplayUnit displayUnit;

    @BeforeEach
    void setUp() {
        game = new Game(displayUnit);
    }

    @AfterEach
    void tearDown() {

    }

    private void playerWonPoints(Game game, Integer player, int times){
        IntStream.range(0,times).forEach(time -> game.pointWonByPlayer(player));
    }

    private void playerWonSet(Game game, Integer player, int times){
        IntStream.range(0,times).forEach(time -> playerWonPoints(game,player,4));
    }

    @Test
    void shouldReturnZeroPointsAtTheBeginning() {
        Pair score = game.currentGameScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(Constants.ZERO);
        assertThat(score.getPlayer2()).isEqualTo(Constants.ZERO);
    }

    @Test
    void shouldReturnZeroFifteenPointsWhenPlayerTwoWinsFirstPoint() {
        playerWonPoints(game, Constants.PLAYER_TWO,1);
        Pair score = game.currentGameScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(Constants.ZERO);
        assertThat(score.getPlayer2()).isEqualTo(Constants.FIFTEEN);
    }

    @Test
    void shouldReturnFifteenZeroPointsWhenPlayerOneWinsFirstPoint() {
        playerWonPoints(game, Constants.PLAYER_ONE,1);
        Pair score = game.currentGameScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(Constants.FIFTEEN);
        assertThat(score.getPlayer2()).isEqualTo(Constants.ZERO);
    }

    @Test
    void shouldReturnZeroThirtyPointsWhenPlayerTwoWinsFirstTwoPoint() {
        playerWonPoints(game, Constants.PLAYER_TWO,2);
        Pair score = game.currentGameScore();
        assertThat(score.getPlayer1()).isEqualTo(Constants.ZERO);
        assertThat(score.getPlayer2()).isEqualTo(Constants.THIRTY);
    }

    @Test
    void shouldReturnThirtyZeroPointsWhenPlayerOneWinsFirstTwoPoint() {
        playerWonPoints(game, Constants.PLAYER_ONE,2);
        Pair score = game.currentGameScore();
        assertThat(score.getPlayer1()).isEqualTo(Constants.THIRTY);
        assertThat(score.getPlayer2()).isEqualTo(Constants.ZERO);
    }

    @Test
    void shouldReturnZeroFortyPointsWhenPlayerTwoWinsFirstThreePoint() {
        playerWonPoints(game, Constants.PLAYER_TWO,3);
        Pair score = game.currentGameScore();
        assertThat(score.getPlayer1()).isEqualTo(Constants.ZERO);
        assertThat(score.getPlayer2()).isEqualTo(Constants.FORTY);
    }

    @Test
    void shouldReturnFortyZeroPointsWhenPlayerOneWinsFirstThreePoint() {
        playerWonPoints(game, Constants.PLAYER_ONE,3);
        Pair score = game.currentGameScore();
        assertThat(score.getPlayer1()).isEqualTo(Constants.FORTY);
        assertThat(score.getPlayer2()).isEqualTo(Constants.ZERO);
    }

    @Test
    void shouldDisplayPlayersScores() {
        playerWonPoints(game, Constants.PLAYER_TWO,3);
        game.displayPlayersScores();
        verify(displayUnit).displayGameScore(any(Pair.class));
    }

    @Test
    void shouldActivateDeuceRuleIfBothPlayersReach40Points() {
        playerWonPoints(game, Constants.PLAYER_ONE,3);
        playerWonPoints(game, Constants.PLAYER_TWO,3);
        Pair score = game.currentGameScore();
        assertThat(game.isGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(Constants.DEUCE);
        assertThat(score.getPlayer2()).isEqualTo(Constants.DEUCE);
    }

    @Test
    void shouldReturnAdvantageToPlayerOneIfHeWinPointWhileDeuce() {
        playerWonPoints(game, Constants.PLAYER_ONE,3);
        playerWonPoints(game, Constants.PLAYER_TWO,3);
        playerWonPoints(game, Constants.PLAYER_ONE,1);
        Pair score = game.currentGameScore();
        assertThat(game.isGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(Constants.ADVANTAGE);
        assertThat(score.getPlayer2()).isEqualTo(Constants.EMPTY);
    }

    @Test
    void shouldReturnAdvantageToPlayerTwoIfHeWinPointWhileDeuce() {
        playerWonPoints(game, Constants.PLAYER_ONE,3);
        playerWonPoints(game, Constants.PLAYER_TWO,4);
        Pair score = game.currentGameScore();
        assertThat(game.isGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(Constants.EMPTY);
        assertThat(score.getPlayer2()).isEqualTo(Constants.ADVANTAGE);
    }

    @Test
    void shouldReturnDeuceWhenPlayerOneLooseAdvantage() {
        playerWonPoints(game, Constants.PLAYER_ONE,3);
        playerWonPoints(game, Constants.PLAYER_TWO,3);
        playerWonPoints(game, Constants.PLAYER_ONE,1);
        playerWonPoints(game, Constants.PLAYER_TWO,1);
        Pair score = game.currentGameScore();
        assertThat(game.isGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(Constants.DEUCE);
        assertThat(score.getPlayer2()).isEqualTo(Constants.DEUCE);
    }

    @Test
    void shouldReturnDeuceWhenPlayerTwoLooseAdvantage() {
        playerWonPoints(game, Constants.PLAYER_ONE,3);
        playerWonPoints(game, Constants.PLAYER_TWO,4);
        playerWonPoints(game, Constants.PLAYER_ONE,1);
        Pair score = game.currentGameScore();
        assertThat(game.isGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(Constants.DEUCE);
        assertThat(score.getPlayer2()).isEqualTo(Constants.DEUCE);
    }

    @Test
    void shouldReturnZeoSetsAtTheBeginning() {
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat((Integer)currentSetScore.getPlayer1()).isZero();
        assertThat((Integer)currentSetScore.getPlayer2()).isZero();
    }

    @Test
    void shouldReturnOneZeroIfPlayerOneWinFirstSetScore() {
        playerWonPoints(game, Constants.PLAYER_ONE,4);
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat(currentSetScore.getPlayer1()).isEqualTo(1);
        assertThat((Integer)currentSetScore.getPlayer2()).isZero();
    }

    @Test
    void ShouldReturnOneOneIfBothPlayerWinASet() {
        playerWonSet(game, Constants.PLAYER_ONE,1);
        playerWonSet(game, Constants.PLAYER_TWO,1);
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat(currentSetScore.getPlayer1()).isEqualTo(1);
        assertThat(currentSetScore.getPlayer2()).isEqualTo(1);
    }

    @Test
    void ShouldReturnPlayerTwoAsSetWinnerIfHeReachSixSetWhilePlayerTwoIsUnderFive() {
        playerWonSet(game, Constants.PLAYER_ONE,4);
        playerWonSet(game, Constants.PLAYER_TWO,6);
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat(currentSetScore.getPlayer1()).isEqualTo(4);
        assertThat(currentSetScore.getPlayer2()).isEqualTo(6);
        assertThat(game.winnerOfSetIs()).isEqualTo(Constants.PLAYER_TWO);
    }

    @Test
    void ShouldReturnPlayerOneAsSetWinnerIfHeReachSixSetWhilePlayerTwoIsUnderFive() {
        playerWonSet(game, Constants.PLAYER_TWO,4);
        playerWonSet(game, Constants.PLAYER_ONE,6);
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat(currentSetScore.getPlayer1()).isEqualTo(6);
        assertThat(currentSetScore.getPlayer2()).isEqualTo(4);
        assertThat(game.winnerOfSetIs()).isEqualTo(Constants.PLAYER_ONE);
    }

    @Test
    void ShouldReturnPlayerOneAsSetWinnerAfterReachingSevenSet() {
        playerWonSet(game, Constants.PLAYER_ONE,5);
        playerWonSet(game, Constants.PLAYER_TWO,5);
        playerWonSet(game, Constants.PLAYER_ONE,2);
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat(currentSetScore.getPlayer1()).isEqualTo(7);
        assertThat(currentSetScore.getPlayer2()).isEqualTo(5);
        assertThat(game.winnerOfSetIs()).isEqualTo(Constants.PLAYER_ONE);
    }

    @Test
    void ShouldReturnPlayerTwoAsSetWinnerAfterReachingSevenSet() {
        playerWonSet(game, Constants.PLAYER_ONE,5);
        playerWonSet(game, Constants.PLAYER_TWO,7);
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat(currentSetScore.getPlayer1()).isEqualTo(5);
        assertThat(currentSetScore.getPlayer2()).isEqualTo(7);
        assertThat(game.winnerOfSetIs()).isEqualTo(Constants.PLAYER_TWO);
    }

    @Test
    void shouldDisplayPlayersSetScores() {
        playerWonSet(game, Constants.PLAYER_TWO,6);
        game.displayPlayersSetScores();
        verify(displayUnit).displaySetsScore(any(Pair.class),any(Integer.class));
    }

    @Test
    void shouldNotActivateTieBreakRuleIfEachPlayerScoreSixSet() {
        playerWonSet(game, Constants.PLAYER_ONE,4);
        playerWonSet(game, Constants.PLAYER_TWO,5);
        assertThat(game.isGameInTieBreak()).isFalse();
    }

    @Test
    void shouldActivateTieBreakRuleIfEachPlayerScoreSixSet() {
        playerWonSet(game, Constants.PLAYER_ONE,4);
        playerWonSet(game, Constants.PLAYER_TWO,5);
        playerWonSet(game, Constants.PLAYER_ONE,2);
        playerWonSet(game, Constants.PLAYER_TWO,1);
        assertThat(game.isGameInTieBreak()).isTrue();
    }

    @Test
    void shouldNotHaveAWinnerIfTieBreakActivatedAndOnePlayerReachSevenSet() {
        playerWonSet(game, Constants.PLAYER_ONE,4);
        playerWonSet(game, Constants.PLAYER_TWO,5);
        playerWonSet(game, Constants.PLAYER_ONE,2);
        playerWonSet(game, Constants.PLAYER_TWO,2);
        assertThat(game.isGameInTieBreak()).isTrue();
        assertThat(game.winnerOfSetIs()).isNull();
    }

    @Test
    void ShouldHavePlayerOneAsWinnerIfTieBreakIsActiveAndTwoSetMoreThanPlayerTwo() {
        playerWonSet(game, Constants.PLAYER_ONE,4);
        playerWonSet(game, Constants.PLAYER_TWO,5);
        playerWonSet(game, Constants.PLAYER_ONE,2);
        playerWonSet(game, Constants.PLAYER_TWO,2);
        playerWonSet(game, Constants.PLAYER_ONE,3);
        assertThat(game.isGameInTieBreak()).isTrue();
        assertThat(game.winnerOfSetIs()).isEqualTo(Constants.PLAYER_ONE);
    }

    @Test
    void ShouldHavePlayerTwoAsWinnerIfTieBreakIsActiveAndTwoSetMoreThanPlayerOne() {
        playerWonSet(game, Constants.PLAYER_ONE,4);
        playerWonSet(game, Constants.PLAYER_TWO,5);
        playerWonSet(game, Constants.PLAYER_ONE,2);
        playerWonSet(game, Constants.PLAYER_TWO,2);
        playerWonSet(game, Constants.PLAYER_ONE,1);
        playerWonSet(game, Constants.PLAYER_TWO,2);
        assertThat(game.isGameInTieBreak()).isTrue();
        assertThat(game.winnerOfSetIs()).isEqualTo(Constants.PLAYER_TWO);
    }
}
