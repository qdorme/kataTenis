package kata.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
class ScreenTest {
    @Mock
    PrintStream out;

    Screen screen;

    @BeforeEach
    void setUp() {
        screen = new Screen(out);
    }

    @Test
    void shouldDisplay0Minus0AtTheStartOfAGame() {
        Pair pair = Pair.builder().player1(Constants.ZERO).player2(Constants.ZERO).build();
        screen.displayGameScore(pair);
        Mockito.verify(out).println("Player One 0-0 Player Two");
    }

    @Test
    void shouldDisplay0Minus15WhenOnePointIsWonByPlayerTwo() {
        Pair pair = Pair.builder().player1(Constants.ZERO).player2(Constants.FIFTEEN).build();
        screen.displayGameScore(pair);
        Mockito.verify(out).println("Player One 0-15 Player Two");
    }

    @Test
    void shouldDisplay15Minus0WhenOnePointIsWonByPlayerOne() {
        Pair pair = Pair.builder().player1(Constants.FIFTEEN).player2(Constants.ZERO).build();
        screen.displayGameScore(pair);
        Mockito.verify(out).println("Player One 15-0 Player Two");
    }

    @Test
    void shouldDisplayPlayerOneAsGameWinner() {
        Pair pair = Pair.builder().player1(Constants.WIN_GAME).player2(Constants.ZERO).build();
        screen.displayGameScore(pair);
        Mockito.verify(out).println("Player One Win game");
    }

    @Test
    void shouldDisplayPlayerTwoAsGameWinner() {
        Pair pair = Pair.builder().player1(Constants.ZERO).player2(Constants.WIN_GAME).build();
        screen.displayGameScore(pair);
        Mockito.verify(out).println("Player Two Win game");
    }

    @Test
    void shouldDisplayDeuceIfBothPlayersHave40Points() {
        Pair pair = Pair.builder().player1(Constants.DEUCE).player2(Constants.DEUCE).build();
        screen.displayGameScore(pair);
        Mockito.verify(out).println(Constants.DEUCE);
    }

    @Test
    void ShouldDisplayAdvantageToPlayerOneIfHeHasIt() {
        Pair pair = Pair.builder().player1(Constants.ADVANTAGE).player2(Constants.EMPTY).build();
        screen.displayGameScore(pair);
        Mockito.verify(out).println("Advantage to Player One");
    }

    @Test
    void ShouldDisplayAdvantageToPlayerTwoIfHeHasIt() {
        Pair pair = Pair.builder().player1(Constants.EMPTY).player2(Constants.ADVANTAGE).build();
        screen.displayGameScore(pair);
        Mockito.verify(out).println("Advantage to Player Two");
    }

    @Test
    void shouldDisplay0SetAtTheStartOfAMatch() {
        Pair pair = Pair.builder().player1(0).player2(0).build();
        screen.displaySetsScore(pair, 0);
        Mockito.verify(out).println("Player One : 0\n" +
                                    "Player Two : 0");
    }

    @Test
    void shouldDisplay1And0SetAtWhenPlayerOneWinFirstSet() {
        Pair pair = Pair.builder().player1(1).player2(0).build();
        screen.displaySetsScore(pair, 0);
        Mockito.verify(out).println("Player One : 1\n" +
                                    "Player Two : 0");
    }

    @Test
    void shouldDisplay1And2SetAtWhenPlayerOneWinFirstSetAndPlayerTwoBothFollowing() {
        Pair pair = Pair.builder().player1(1).player2(2).build();
        screen.displaySetsScore(pair, 0);
        Mockito.verify(out).println("Player One : 1\n" +
                                    "Player Two : 2");
    }

    @Test
    void shouldDisplaySetScoresAndPlayerTwoAsWinner() {
        Pair pair = Pair.builder().player1(5).player2(7).build();
        screen.displaySetsScore(pair, Constants.PLAYER_TWO);
        Mockito.verify(out).println("Player One : 5\n" +
                                    "Player Two : 7");
        Mockito.verify(out).println("Winner : Player Two");
    }

    @Test
    void shouldDisplaySetScoresAndPlayerOneAsWinner() {
        Pair pair = Pair.builder().player1(7).player2(5).build();
        screen.displaySetsScore(pair, Constants.PLAYER_ONE);
        Mockito.verify(out).println("Player One : 7\n" +
                                    "Player Two : 5");
        Mockito.verify(out).println("Winner : Player One");
    }
}
