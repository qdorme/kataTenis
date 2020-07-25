package kata.tennis;

import java.io.PrintStream;

public class Screen {
    PrintStream out;

    public Screen(PrintStream out) {
        this.out = out;
    }

    public void display(Pair pair) {
        if(Game.FIFTEEN.equals(pair.getPlayer2()))
            out.println("Player One 0-15 Player Two");
        else
            out.println("Player One 0-0 Player Two");
    }

}
