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

    @Test
    void shouldReturnZeroPointsAtTheStart() {
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(ZERO);
        assertThat(score.getPlayer2()).isEqualTo(ZERO);
    }

    @Test
    void shouldReturnZeroFifteenPointsWhenPlayerTwoWinsFirstPoint() {
        playerWonPoints(game,PLAYER_TWO,1);
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(ZERO);
        assertThat(score.getPlayer2()).isEqualTo(FIFTEEN);
    }

    @Test
    void shouldReturnFifteenZeroPointsWhenPlayerOneWinsFirstPoint() {
        playerWonPoints(game,PLAYER_ONE,1);
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(FIFTEEN);
        assertThat(score.getPlayer2()).isEqualTo(ZERO);
    }

    @Test
    void shouldReturnZeroThirtyPointsWhenPlayerTwoWinsFirstTwoPoint() {
        playerWonPoints(game,PLAYER_TWO,2);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isEqualTo(ZERO);
        assertThat(score.getPlayer2()).isEqualTo(THIRTY);
    }

    @Test
    void shouldReturnThirtyZeroPointsWhenPlayerOneWinsFirstTwoPoint() {
        playerWonPoints(game,PLAYER_ONE,2);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isEqualTo(THIRTY);
        assertThat(score.getPlayer2()).isEqualTo(ZERO);
    }

    @Test
    void shouldReturnZeroFortyPointsWhenPlayerTwoWinsFirstThreePoint() {
        playerWonPoints(game,PLAYER_TWO,3);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isEqualTo(ZERO);
        assertThat(score.getPlayer2()).isEqualTo(FORTY);
    }

    @Test
    void shouldReturnFortyZeroPointsWhenPlayerOneWinsFirstThreePoint() {
        playerWonPoints(game,PLAYER_ONE,3);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isEqualTo(FORTY);
        assertThat(score.getPlayer2()).isEqualTo(ZERO);
    }

    @Test
    void shouldReturnZeroPointAndWinGameWhenPlayerTwoWinsFourPoint() {
        playerWonPoints(game,PLAYER_TWO,4);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isEqualTo(ZERO);
        assertThat(score.getPlayer2()).isEqualTo(WIN_GAME);
    }

    @Test
    void shouldReturnWinGameAndZeroPointWhenPlayerOneWinsFourPoint() {
        playerWonPoints(game,PLAYER_ONE,4);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isEqualTo(WIN_GAME);
        assertThat(score.getPlayer2()).isEqualTo(ZERO);
    }

    @Test
    void shouldDisplayPlayersScores() {
        playerWonPoints(game,PLAYER_TWO,3);
        game.displayPlayersScores();
        verify(displayUnit).display(any(Pair.class));
    }

    @Test
    void shouldActivateDeuceRuleIfBothPlayersReach40Points() {
        playerWonPoints(game,PLAYER_ONE,3);
        playerWonPoints(game,PLAYER_TWO,3);
        assertThat(game.isCurrentGameInDeuce()).isTrue();
    }
}
