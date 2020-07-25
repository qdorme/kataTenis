package kata.tennis;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Pair {
    private Object player1;
    private Object player2;
}
