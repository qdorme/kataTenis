package kata.tennis;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Pair {
    private Integer player1;
    private Integer player2;
}
