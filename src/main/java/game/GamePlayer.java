package game;

import ailogic.DecisionTree;
import ailogic.Node;
import ailogic.Tree;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Ayomitunde on 1/17/2017.
 */
public class GamePlayer {
    private static HashSet<char[][]> savedStates = new HashSet<>();

    public static String playAI(Game game, char player, String guiInput) {
        if (game.getWinner(game.getTable()) == ' ' || game.isTie(game.getTable()))
        {
            if (game.isTie(game.getTable())) {
                JOptionPane.showMessageDialog(null, "Game is a Tie");
            }
            int row = Integer.parseInt(String.valueOf(guiInput.charAt(0)));
            int col = Integer.parseInt(String.valueOf(guiInput.charAt(1)));

            if (game.isLegal(row, col))
            {
                char[][] gameTable = game.getTable();
                game.update(player, row, col, gameTable);
                if (game.isTie(game.getTable()))
                {
                    JOptionPane.showMessageDialog(null, "Game is a Tie");
                }
                else if (game.getWinner(game.getTable()) == ' ')
                {
                    DecisionTree decisionTree = new DecisionTree(player, gameTable);
                    savedStates.add(gameTable);
                    String computerInput = decisionTree.getNextMove();
                    if (computerInput == null)
                    {
                        JOptionPane.showMessageDialog(null, "I don't have a move to make!!\nI give up");
                        System.exit(1);
                    }
                    row = Integer.parseInt(String.valueOf(computerInput.charAt(0)));
                    col = Integer.parseInt(String.valueOf(computerInput.charAt(1)));
                    game.update(DecisionTree.aiCharacter, row, col, gameTable);
                    return computerInput;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Location " + row + "" + col + " has been taken!\nLocations available are " + game.freeLocations(game.getTable()));
            }
        }
        else
        {
            if(Game.getWinner(game.getTable()) == player)
            {
                JOptionPane.showMessageDialog(null, "AI Lost!!");
            }else
            {
                JOptionPane.showMessageDialog(null, "AI Won!!");
            }
            System.exit(1);
        }
        return null;
    }

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
                }else if(game.getWinner(game.getTable()) == ' ')
                {
                    DecisionTree decisionTree = new DecisionTree(player, gameTable);
                    savedStates.add(gameTable);
                    System.out.println("Computer is thinking ... !");
                    String computerInput = decisionTree.getNextMove();
                    if(computerInput == null)
                    {
                        System.out.println("I don't have a move to make!!\nI give up");
                        System.exit(1);
                    }
                    row = Integer.parseInt(String.valueOf(computerInput.charAt(0)));
                    col = Integer.parseInt(String.valueOf(computerInput.charAt(1)));
                    System.out.println("=================\nActual Game Board\n=================\n");
                    game.update(DecisionTree.aiCharacter, row, col, gameTable);
                    System.out.println("\nPlayed "+DecisionTree.aiCharacter+" location "+row+""+col);
                }else
                {
                    return String.valueOf(game.getWinner(game.getTable()));
                }
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
        DecisionTree.aiCharacter = Game.switchPlayer(player);
        playAI(game, player);
        System.out.println("Winner is "+game.getWinner(game.getTable()));
    }
}
