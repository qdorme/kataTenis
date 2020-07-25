package kata.tennis;

import java.io.PrintStream;

public class Screen implements DisplayUnit {
    PrintStream out;

    public Screen(PrintStream out) {
        this.out = out;
    }

    public void display(Pair pair) {
        if(Game.WIN_GAME.equals(pair.getPlayer1())){
            out.println(String.format("Player One %s", pair.getPlayer1()));
        } else if(Game.WIN_GAME.equals(pair.getPlayer2())){
            out.println(String.format("Player Two %s", pair.getPlayer2()));
        } else {
            out.println(String.format("Player One %s-%s Player Two", pair.getPlayer1(), pair.getPlayer2()));
        }
    }

}
