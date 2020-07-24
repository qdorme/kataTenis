package kata.tennis;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    @Test
    void shouldReturnZeroPointsAtTheStart() {
        Game game = new Game();
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isZero();
        assertThat(score.getPlayer2()).isZero();
    }

    @Test
    void shouldReturnZeroFifteenPointsWhenPlayerTwoWinsFirstPoint() {
        Game game = new Game();
        game.pointWonByPlayer(2);
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isZero();
        assertThat(score.getPlayer2()).isEqualTo(15);
    }

    @Test
    void shouldReturnFifteenZeroPointsWhenPlayerOneWinsFirstPoint() {
        Game game = new Game();
        game.pointWonByPlayer(1);
        Pair score = game.currentSetScore();
        assertThat(score).isNotNull();
        assertThat(score.getPlayer1()).isEqualTo(15);
        assertThat(score.getPlayer2()).isZero();
    }
}
