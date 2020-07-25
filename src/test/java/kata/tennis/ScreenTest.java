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

    @Test
    void shouldDisplay0Minus0AtTheStartOfAGame() {
        Screen screen = new Screen(out);
        Pair pair = Pair.builder().player1(ZERO).player2(ZERO).build();
        screen.display(pair);
        Mockito.verify(out).println("Player One 0-0 Player Two");
    }

    @Test
    void shouldDisplay0MinusFifteenWhenOnePointIsWonByPlayerTwo() {
        Screen screen = new Screen(out);
        Pair pair = Pair.builder().player1(ZERO).player2(FIFTEEN).build();
        screen.display(pair);
        Mockito.verify(out).println("Player One 0-15 Player Two");
    }
}
