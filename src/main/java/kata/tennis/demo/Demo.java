package kata.tennis.demo;


import kata.tennis.Game;
import kata.tennis.Screen;

import java.util.stream.IntStream;

public class Demo {
    public static void main(String[] args) {
        Game game = new Game(new Screen(System.out));
        playerWonPoints(game,1,1);
        game.displayPlayersScores();
        game.displayPlayersSetScores();

        System.out.println("");
        playerWonSet(game,1,4);
        playerWonSet(game,2,4);
        playerWonPoints(game,1,3);
        playerWonPoints(game,2,4);
        playerWonPoints(game,1,1);
        game.displayPlayersScores();
        game.displayPlayersSetScores();

        System.out.println("");
        playerWonSet(game, 2,2);
        game.displayPlayersScores();
        game.displayPlayersSetScores();

        System.out.println("");
        game = new Game(new Screen(System.out));
        playerWonSet(game,1,5);
        playerWonSet(game,2,6);
        playerWonSet(game,1,1);
        playerWonSet(game,2,2);
        game.displayPlayersScores();
        game.displayPlayersSetScores();
    }

    static void playerWonPoints(Game game, int player, int times){
        IntStream.range(0,times).forEach(time -> game.pointWonByPlayer(player));
    }

    static void playerWonSet(Game game, int player, int times){
        IntStream.range(0,times).forEach(time -> playerWonPoints(game,player,4));
    }
}
