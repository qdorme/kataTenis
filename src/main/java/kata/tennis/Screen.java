package kata.tennis;

import java.io.PrintStream;

public class Screen {
    PrintStream out;

    public Screen(PrintStream out) {
        this.out = out;
    }

    public void display(Pair pair) {
        out.println(String.format("Player One %s-%s Player Two",pair.getPlayer1(),pair.getPlayer2()));
    }

}
