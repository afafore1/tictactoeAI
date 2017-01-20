package game;

import ailogic.DecisionTree;

import java.awt.*;
import java.util.Scanner;

/**
 * Created by Ayomitunde on 1/17/2017.
 */
public class GamePlayer {
    private static String playAI(Game game, char player)
    {
        if(game.getWinner(game.getTable()) == ' ' || game.isTie(game.getTable()))
        {
            if(game.isTie(game.getTable()))
            {
                return "Game is a Tie";
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter row/col location: ");
            String input = scanner.nextLine();
            int row = Integer.parseInt(String.valueOf(input.charAt(0)));
            int col = Integer.parseInt(String.valueOf(input.charAt(1)));
            if(game.isLegal(row, col))
            {
                char[][] gameTable = game.getTable();
                game.update(player, row, col, gameTable);
                if(game.isTie(game.getTable()))
                {
                    return "Game is a Tie";
                }
                DecisionTree decisionTree = new DecisionTree(player, gameTable);
                System.out.println("Computer is thinking ... !");
                String computerInput = decisionTree.getNextMove();
                if(computerInput == null)
                {
                    System.out.println("I don't have a move to make!!\nI give up");
                    System.exit(1);
                }
                row = Integer.parseInt(String.valueOf(computerInput.charAt(0)));
                col = Integer.parseInt(String.valueOf(computerInput.charAt(1)));
                char computerPlayer = Game.switchPlayer(player);
                game.update(computerPlayer, row, col, gameTable);
                System.out.println("\nPlayed "+computerPlayer+" location "+row+""+col);
            }else
            {
                System.out.println("Location "+row+""+col+" has been taken!\nLocations available are "+game.freeLocations(game.getTable()));
            }

            playAI(game, player);
        }
        System.out.println("Printing game board");
        game.displayBoard(game.getTable());
        return String.valueOf(game.getWinner(game.getTable()));
    }

    private static String playGame(Game game, char player)
    {
        if(game.getWinner(game.getTable()) == ' ' || game.isTie(game.getTable()))
        {
            if(game.isTie(game.getTable()))
            {
                return "Game is a Tie";
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter row/col location: ");
            String input = scanner.nextLine();
            int row = Integer.parseInt(String.valueOf(input.charAt(0)));
            int col = Integer.parseInt(String.valueOf(input.charAt(1)));
            if(game.isLegal(row, col))
            {
                game.update(player, row, col, game.getTable());
                player = game.switchPlayer(player);
            }else
            {
                System.out.println("Location "+row+""+col+" has been taken!\nLocations available are "+game.freeLocations(game.getTable()));
            }

            playGame(game, player);
        }
        return String.valueOf(game.getWinner(game.getTable()));
    }

    public static void main(String [] args)
    {
        Game game = new Game();
        char player = Math.random() > .5 ? game.getPlayero() : game.getPlayerx();
        playAI(game, player);
        System.out.println("Winner is "+game.getWinner(game.getTable()));
    }
}
