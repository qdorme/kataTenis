package kata.tennis;

import java.io.PrintStream;

import static kata.tennis.Game.*;

public class Screen implements DisplayUnit {
    PrintStream out;

    public Screen(PrintStream out) {
        this.out = out;
    }

    public void displayGameScore(Pair score) {
        if(WIN_GAME.equals(score.getPlayer1())){
            out.println(String.format("Player One %s", score.getPlayer1()));
        } else if(WIN_GAME.equals(score.getPlayer2())){
            out.println(String.format("Player Two %s", score.getPlayer2()));
        } else if(isThereADeuce(score)){
            out.println(DEUCE);
        } else if(ADVANTAGE.equals(score.getPlayer1())){
            out.println("Advantage to Player One");
        } else if(ADVANTAGE.equals(score.getPlayer2())){
            out.println("Advantage to Player Two");
        } else {
            out.println(String.format("Player One %s-%s Player Two", score.getPlayer1(), score.getPlayer2()));
        }
    }

    @Override
    public void displaySetsScore(Pair pair, Integer player) {
        out.println(String.format("Player One : %s\n" +
                                  "Player Two : %s",pair.getPlayer1(),pair.getPlayer2()));
        if(PLAYER_ONE.equals(player))
            out.println("Winner : Player One");
        if(PLAYER_TWO.equals(player))
            out.println("Winner : Player Two");
    }

    private boolean isThereADeuce(Pair score) {
        return DEUCE.equals(score.getPlayer1()) && DEUCE.equals(score.getPlayer2());
    }

}
