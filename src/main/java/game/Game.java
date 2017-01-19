package game;
import java.util.ArrayList;

/**
 * Created by Ayomitunde on 1/17/2017.
 */
public class Game {
    static final char EMPTY = ' ';
    static final char PLAYERX = 'x';
    static final char PLAYERO = 'o';

    private char [][] Initialtable;

    public Game()
    {
        Initialtable = new char[3][3];
        initializeGame();
    }

    private void initializeGame()
    {
        for(int i = 0; i < Initialtable.length; i++)
        {
            for(int j = 0; j < Initialtable.length; j++)
            {
                Initialtable[i][j] = ' ';
            }
        }
    }

    protected char getPlayerx()
    {
        return PLAYERX;
    }

    protected char getPlayero()
    {
        return PLAYERO;
    }

    protected static char switchPlayer(char player)
    {
        return player == PLAYERO ? PLAYERX : PLAYERO;
    }

    protected boolean isLegal(int row, int column)
    {
        if(row > 2 || row < 0 || column > 2 || column < 0 || Initialtable[row][column] != ' ') return false;
        return true;
    }

    protected char[][] getTable()
    {
        return this.Initialtable;
    }

    protected void update(char player, int row, int column, char[][] table)
    {
        table[row][column] = player;
        displayBoard(table);
    }

    protected boolean isTie(char[][] table)
    {
        for(int i = 0; i < table.length; i++)
        {
            for(int j = 0; j < table.length; j++)
            {
                if(table[i][j] == ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static char getWinner(char [][] table)
    {
        if(checkColForWin(table) != ' ') return checkColForWin(table);
        if(checkRowsForWin(table) != ' ') return checkRowsForWin(table);
        if(checkDigForWin(table) != ' ') return checkDigForWin(table);
        return ' ';
    }

    public static char checkRowsForWin(char [][] table)
    {
        for(int i = 0; i < table.length; i++)
        {
            if(checkRowCol(table[i][0], table[i][1], table[i][2]) == true)return table[i][0];
        }
        return ' ';
    }

    public static char checkColForWin(char [][] table)
    {
        for(int i = 0; i < table.length; i++)
        {
            if(checkRowCol(table[0][i], table[1][i], table[2][i]) == true) return table[0][i];
        }
        return ' ';
    }

    public static char checkDigForWin(char [][] table)
    {
        if((checkRowCol(table[0][0], table[1][1], table[2][2]) == true) || (checkRowCol(table[0][2], table[1][1], table[2][0]) == true))
        {
            return table[1][1];
        }
        return ' ';
    }

    public static ArrayList<String> freeLocations(char [][] table)
    {
        ArrayList<String> freeLocations = new ArrayList<String>();
        for(int i = 0; i < table.length; i++)
        {
            for(int j = 0; j < table.length; j++)
            {
                if(table[i][j] == ' ')
                {
                    String freeLoc = ""+i+""+j;
                    freeLocations.add(freeLoc);
                }
            }
        }
        return freeLocations;
    }

    public static boolean checkRowCol(char c1, char c2, char c3)
    {
        return ((c1 != ' ') && (c1 == c2) && (c2 == c3));
    }

    public static void displayBoard(char [][] table) {

        System.out.println("    " + table[0][0] + "|" + table[0][1] + "|" + table[0][2]);
        System.out.println("   --+-+--");
        System.out.println("    " + table[1][0] + "|" + table[1][1] + "|" + table[1][2]);
        System.out.println("   --+-+--");
        System.out.println("    " + table[2][0] + "|" + table[2][1] + "|" + table[2][2]);
        System.out.println("===========================================\n===========================================");
    }

}
