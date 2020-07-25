package kata.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static kata.tennis.Game.*;

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
        Pair pair = Pair.builder().player1(ZERO).player2(ZERO).build();
        screen.display(pair);
        Mockito.verify(out).println("Player One 0-0 Player Two");
    }

    @Test
    void shouldDisplay0Minus15WhenOnePointIsWonByPlayerTwo() {
        Pair pair = Pair.builder().player1(ZERO).player2(FIFTEEN).build();
        screen.display(pair);
        Mockito.verify(out).println("Player One 0-15 Player Two");
    }

    @Test
    void shouldDisplay15Minus0WhenOnePointIsWonByPlayerOne() {
        Pair pair = Pair.builder().player1(FIFTEEN).player2(ZERO).build();
        screen.display(pair);
        Mockito.verify(out).println("Player One 15-0 Player Two");
    }

    @Test
    void shouldDisplayPlayerOneAsGameWinner() {
        Pair pair = Pair.builder().player1(WIN_GAME).player2(ZERO).build();
        screen.display(pair);
        Mockito.verify(out).println("Player One Win game");
    }

    @Test
    void shouldDisplayPlayerTwoAsGameWinner() {
        Pair pair = Pair.builder().player1(ZERO).player2(WIN_GAME).build();
        screen.display(pair);
        Mockito.verify(out).println("Player Two Win game");
    }
}
