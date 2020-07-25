package kata.tennis;

import java.io.PrintStream;

import static kata.tennis.Game.DEUCE;
import static kata.tennis.Game.WIN_GAME;

public class Screen implements DisplayUnit {
    PrintStream out;

    public Screen(PrintStream out) {
        this.out = out;
    }

    public void display(Pair score) {
        if(WIN_GAME.equals(score.getPlayer1())){
            out.println(String.format("Player One %s", score.getPlayer1()));
        } else if(WIN_GAME.equals(score.getPlayer2())){
            out.println(String.format("Player Two %s", score.getPlayer2()));
        } else if(isThereADeuce(score)){
            out.println(DEUCE);
        } else {
            out.println(String.format("Player One %s-%s Player Two", score.getPlayer1(), score.getPlayer2()));
        }
    }

    private boolean isThereADeuce(Pair score) {
        return DEUCE.equals(score.getPlayer1()) && DEUCE.equals(score.getPlayer2());
    }

}
