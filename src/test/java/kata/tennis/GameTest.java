package kata.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.IntStream;

import static kata.tennis.Game.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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

    private void playerWonPoints(Game game, int player, int times){
        IntStream.range(0,times).forEach(time -> game.pointWonByPlayer(player));
    }

    private void playerWonSet(Game game, int player, int times){
        IntStream.range(0,times).forEach(time -> playerWonPoints(game,player,4));
    }

    @Test
    void shouldReturnZeroPointsAtTheBeginning() {
        Pair score = game.currentGameScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(ZERO);
        assertThat(score.getPlayer2()).isEqualTo(ZERO);
    }

    @Test
    void shouldReturnZeroFifteenPointsWhenPlayerTwoWinsFirstPoint() {
        playerWonPoints(game,PLAYER_TWO,1);
        Pair score = game.currentGameScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(ZERO);
        assertThat(score.getPlayer2()).isEqualTo(FIFTEEN);
    }

    @Test
    void shouldReturnFifteenZeroPointsWhenPlayerOneWinsFirstPoint() {
        playerWonPoints(game,PLAYER_ONE,1);
        Pair score = game.currentGameScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(FIFTEEN);
        assertThat(score.getPlayer2()).isEqualTo(ZERO);
    }

    @Test
    void shouldReturnZeroThirtyPointsWhenPlayerTwoWinsFirstTwoPoint() {
        playerWonPoints(game,PLAYER_TWO,2);
        Pair score = game.currentGameScore();
        assertThat(score.getPlayer1()).isEqualTo(ZERO);
        assertThat(score.getPlayer2()).isEqualTo(THIRTY);
    }

    @Test
    void shouldReturnThirtyZeroPointsWhenPlayerOneWinsFirstTwoPoint() {
        playerWonPoints(game,PLAYER_ONE,2);
        Pair score = game.currentGameScore();
        assertThat(score.getPlayer1()).isEqualTo(THIRTY);
        assertThat(score.getPlayer2()).isEqualTo(ZERO);
    }

    @Test
    void shouldReturnZeroFortyPointsWhenPlayerTwoWinsFirstThreePoint() {
        playerWonPoints(game,PLAYER_TWO,3);
        Pair score = game.currentGameScore();
        assertThat(score.getPlayer1()).isEqualTo(ZERO);
        assertThat(score.getPlayer2()).isEqualTo(FORTY);
    }

    @Test
    void shouldReturnFortyZeroPointsWhenPlayerOneWinsFirstThreePoint() {
        playerWonPoints(game,PLAYER_ONE,3);
        Pair score = game.currentGameScore();
        assertThat(score.getPlayer1()).isEqualTo(FORTY);
        assertThat(score.getPlayer2()).isEqualTo(ZERO);
    }

    @Test
    void shouldDisplayPlayersScores() {
        playerWonPoints(game,PLAYER_TWO,3);
        game.displayPlayersScores();
        verify(displayUnit).displayGameScore(any(Pair.class));
    }

    @Test
    void shouldActivateDeuceRuleIfBothPlayersReach40Points() {
        playerWonPoints(game,PLAYER_ONE,3);
        playerWonPoints(game,PLAYER_TWO,3);
        Pair score = game.currentGameScore();
        assertThat(game.isCurrentGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(DEUCE);
        assertThat(score.getPlayer2()).isEqualTo(DEUCE);
    }

    @Test
    void shouldReturnAdvantageToPlayerOneIfHeWinPointWhileDeuce() {
        playerWonPoints(game,PLAYER_ONE,3);
        playerWonPoints(game,PLAYER_TWO,3);
        playerWonPoints(game,PLAYER_ONE,1);
        Pair score = game.currentGameScore();
        assertThat(game.isCurrentGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(ADVANTAGE);
        assertThat(score.getPlayer2()).isEqualTo(EMPTY);
    }

    @Test
    void shouldReturnAdvantageToPlayerTwoIfHeWinPointWhileDeuce() {
        playerWonPoints(game,PLAYER_ONE,3);
        playerWonPoints(game,PLAYER_TWO,4);
        Pair score = game.currentGameScore();
        assertThat(game.isCurrentGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(EMPTY);
        assertThat(score.getPlayer2()).isEqualTo(ADVANTAGE);
    }

    @Test
    void shouldReturnDeuceWhenPlayerOneLooseAdvantage() {
        playerWonPoints(game,PLAYER_ONE,3);
        playerWonPoints(game,PLAYER_TWO,3);
        playerWonPoints(game,PLAYER_ONE,1);
        playerWonPoints(game,PLAYER_TWO,1);
        Pair score = game.currentGameScore();
        assertThat(game.isCurrentGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(DEUCE);
        assertThat(score.getPlayer2()).isEqualTo(DEUCE);
    }

    @Test
    void shouldReturnDeuceWhenPlayerTwoLooseAdvantage() {
        playerWonPoints(game,PLAYER_ONE,3);
        playerWonPoints(game,PLAYER_TWO,4);
        playerWonPoints(game,PLAYER_ONE,1);
        Pair score = game.currentGameScore();
        assertThat(game.isCurrentGameInDeuce()).isTrue();
        assertThat(score.getPlayer1()).isEqualTo(DEUCE);
        assertThat(score.getPlayer2()).isEqualTo(DEUCE);
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
        playerWonPoints(game,PLAYER_ONE,4);
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat(currentSetScore.getPlayer1()).isEqualTo(1);
        assertThat((Integer)currentSetScore.getPlayer2()).isZero();
    }

    @Test
    void ShouldReturnOneOneIfBothPlayerWinASet() {
        playerWonSet(game,PLAYER_ONE,1);
        playerWonSet(game,PLAYER_TWO,1);
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat(currentSetScore.getPlayer1()).isEqualTo(1);
        assertThat(currentSetScore.getPlayer2()).isEqualTo(1);
    }

    @Test
    void ShouldReturnPlayerOneAsSetWinnerIfHeReachSixSetWhilePlayerTwoIsUnderFive() {
        playerWonSet(game,PLAYER_ONE,4);
        playerWonSet(game,PLAYER_TWO,6);
        Pair currentSetScore = game.currentSetScore();
        assertThat(currentSetScore).isNotNull();
        assertThat(currentSetScore.getPlayer1()).isEqualTo(4);
        assertThat(currentSetScore.getPlayer2()).isEqualTo(6);
        assertThat(game.winnerOfSetIs()).isEqualTo(PLAYER_TWO);
    }
}
