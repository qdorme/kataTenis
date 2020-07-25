package kata.tennis;

import java.io.PrintStream;

public class Screen {
    PrintStream out;

    public Screen(PrintStream out) {
        this.out = out;
    }

    public void display(Pair pair) {
        out.println("Player One 0-0 Player Two");
    }

}
