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

    @Test
    void shouldDisplay0Minus0AtTheStartOfAGame() {
        Screen screen = new Screen(out);
        Pair pair = Pair.builder().player1("0").player2("0").build();
        screen.display(pair);
        Mockito.verify(out).println("Player One 0-0 Player Two");
    }

}
