package kata.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static kata.tennis.Game.PLAYER_ONE;
import static kata.tennis.Game.PLAYER_TWO;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
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
        game.pointWonByPlayer(PLAYER_TWO);
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isZero();
        assertThat(score.getPlayer2()).isEqualTo(15);
    }

    @Test
    void shouldReturnFifteenZeroPointsWhenPlayerOneWinsFirstPoint() {
        game.pointWonByPlayer(PLAYER_ONE);
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(15);
        assertThat(score.getPlayer2()).isZero();
    }

    @Test
    void shouldReturnZeroThirtyPointsWhenPlayerTwoWinsFirstTwoPoint() {
        game.pointWonByPlayer(PLAYER_TWO);
        game.pointWonByPlayer(PLAYER_TWO);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isZero();
        assertThat(score.getPlayer2()).isEqualTo(30);
    }

    @Test
    void shouldReturnThirtyZeroPointsWhenPlayerOneWinsFirstTwoPoint() {
        game.pointWonByPlayer(PLAYER_ONE);
        game.pointWonByPlayer(PLAYER_ONE);
        Pair score = game.currentSetScore();
        assertThat(score.getPlayer1()).isEqualTo(30);
        assertThat(score.getPlayer2()).isZero();
    }
}
