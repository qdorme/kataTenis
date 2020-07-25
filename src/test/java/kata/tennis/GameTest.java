package kata.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static kata.tennis.Game.PLAYER_ONE;
import static kata.tennis.Game.PLAYER_TWO;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    private void playerWonPoints(Game game, int player, int times){
        IntStream.range(0,times).forEach(time -> game.pointWonByPlayer(player));
    }


    @Test
    void shouldReturnZeroPointsAtTheStart() {
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isZero();
        assertThat(score.getPlayer2()).isZero();
    }

    @Test
    void shouldReturnZeroFifteenPointsWhenPlayerTwoWinsFirstPoint() {
        playerWonPoints(game,PLAYER_TWO,1);
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isZero();
        assertThat(score.getPlayer2()).isEqualTo(15);
    }

    @Test
    void shouldReturnFifteenZeroPointsWhenPlayerOneWinsFirstPoint() {
        playerWonPoints(game,PLAYER_ONE,1);
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(15);
        assertThat(score.getPlayer2()).isZero();
    }

    @Test
    void shouldReturnZeroThirtyPointsWhenPlayerTwoWinsFirstTwoPoint() {
        playerWonPoints(game,PLAYER_TWO,2);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isZero();
        assertThat(score.getPlayer2()).isEqualTo(30);
    }

    @Test
    void shouldReturnThirtyZeroPointsWhenPlayerOneWinsFirstTwoPoint() {
        playerWonPoints(game,PLAYER_ONE,2);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isEqualTo(30);
        assertThat(score.getPlayer2()).isZero();
    }

    @Test
    void shouldReturnZeroFortyPointsWhenPlayerTwoWinsFirstThreePoint() {
        playerWonPoints(game,PLAYER_TWO,3);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isZero();
        assertThat(score.getPlayer2()).isEqualTo(40);
    }

    @Test
    void shouldReturnFortyZeroPointsWhenPlayerOneWinsFirstThreePoint() {
        playerWonPoints(game,PLAYER_ONE,3);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isEqualTo(40);
        assertThat(score.getPlayer2()).isZero();
    }
}
