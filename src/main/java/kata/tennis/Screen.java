package kata.tennis;

import java.io.PrintStream;

public class Screen implements DisplayUnit {
    PrintStream out;

    public Screen(PrintStream out) {
        this.out = out;
    }

    public void displayGameScore(Pair score) {
        if(Constants.WIN_GAME.equals(score.getPlayer1())){
            out.println(String.format("Player One %s", score.getPlayer1()));
        } else if(Constants.WIN_GAME.equals(score.getPlayer2())){
            out.println(String.format("Player Two %s", score.getPlayer2()));
        } else if(isThereADeuce(score)){
            out.println(Constants.DEUCE);
        } else if(Constants.ADVANTAGE.equals(score.getPlayer1())){
            out.println("Advantage to Player One");
        } else if(Constants.ADVANTAGE.equals(score.getPlayer2())){
            out.println("Advantage to Player Two");
        } else {
            out.println(String.format("Player One %s-%s Player Two", score.getPlayer1(), score.getPlayer2()));
        }
    }

    @Override
    public void displaySetsScore(Pair pair, Integer player) {
        out.println(String.format("Player One : %s\n" +
                                  "Player Two : %s",pair.getPlayer1(),pair.getPlayer2()));
        if(Constants.PLAYER_ONE.equals(player))
            out.println("Winner : Player One");
        if(Constants.PLAYER_TWO.equals(player))
            out.println("Winner : Player Two");
    }

    private boolean isThereADeuce(Pair score) {
        return Constants.DEUCE.equals(score.getPlayer1()) && Constants.DEUCE.equals(score.getPlayer2());
    }

}
