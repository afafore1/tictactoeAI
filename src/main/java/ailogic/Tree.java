package ailogic;
import game.Game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static ailogic.DecisionTree.aiCharacter;

/**
 * Created by Ayomitunde on 1/20/2017.
 */
public class Tree {
    protected static Queue<Node> wins = new LinkedList<>();
    protected static ArrayList<Node> loss = new ArrayList<Node>();
    protected static ArrayList<Node> draw = new ArrayList<Node>();
    protected static HashSet<Node> allNodes = new HashSet<Node>();
    protected static Node foundNode = null;
    private static Node winNode = null;
    private static Node lossNode = null;


    protected static boolean checkTableExist(char [][] tableToCheck)
    {
        for(Node node : allNodes)
        {
            if(Arrays.deepEquals(tableToCheck, node.getTable()))
            {
                foundNode = node;
                getWinNode(node);
                getLossNode(node);
                return true;
            }
        }
        return false;
    }

    protected static boolean currentIsLoss(Node node)
    {
        char playerCharacter = DecisionTree.switchPlayer(DecisionTree.aiCharacter);
        if(nodeIsWin(node, playerCharacter)) return true;
        return false;
    }

    protected static boolean nodeIsWin(Node node, char player)
    {
        if(Game.getWinner(node.getTable()) == player) return true;
        return false;
    }

    private static boolean isLegal(char[][] table, int row, int column)
    {
        if(table[row][column] != ' ') return false;
        return true;
    }

    // this is quite long and messy..
    protected static String getBlockPlayerWinLocation(char[][] table, char player) {
        for (int i = 0; i < table.length; i++) {
            if (table[1][i] == table[2][i] && table[1][i] == player) {
                if (isLegal(table, 0, i)) return "" + 0 + "" + i;
            }
            if (table[0][i] == table[2][i] && table[0][i] == player) {
                if (isLegal(table, 1, i)) return "" + 1 + "" + i;
            }
            if (table[0][i] == table[1][i] && table[0][i] == player) {
                if (isLegal(table, 2, i)) return "" + 2 + "" + i;
            }
            if (table[i][1] == table[i][2] && table[i][1] == player) {
                if (isLegal(table, i, 0)) return "" + i + "" + 0;
            }
            if (table[i][0] == table[i][2] && table[i][0] == player) {
                if (isLegal(table, i, 1)) return "" + i + "" + 1;
            }
            if (table[i][0] == table[i][1] && table[i][1] == player) {
                if (isLegal(table, i, 2)) return "" + i + "" + 2;
            }
        }
        if (table[1][1] == table[2][2] && table[1][1] == player) {
            if (isLegal(table, 0, 0)) return "" + 0 + "" + 0;
        }
        if (table[0][0] == table[2][2] && table[0][0] == player) {
            if (isLegal(table, 1, 1)) return "" + 1 + "" + 1;
        }
        if (table[0][0] == table[1][1] && table[1][1] == player) {
            if (isLegal(table, 2, 2)) return "" + 2 + "" + 2;
        }
        if (table[1][1] == table[2][0] && table[1][1] == player)
        {
            if(isLegal(table, 0,2)) return "" + 0 + ""+ 2;
        }
        if(table[0][2] == table[2][0] && table[0][2] == player)
        {
            if(isLegal(table, 1, 1)) return ""+1+""+1;
        }
        if (table[1][1] == table[0][2] && table[1][1] == player)
        {
            if(isLegal(table, 2,0)) return "" + 2 + ""+ 0;
        }
        return null;
    }

    private static boolean isEqual(char[][] table1, char[][] table2)
    {
        for(int i = 0; i < table1.length; i++)
        {
            for(int j = 0; j < table1.length; j++)
            {
                if(table1[i][j] != table2[i][j]) return false;
            }
        }
        return true;
    }

    private static void getNode(Node node, char character)
    {
        Queue<HashSet<Node>> children = new LinkedList<HashSet<Node>>();
        children.add(node.getChildren());
        while(!children.isEmpty())
        {
            HashSet<Node> group = children.remove();
            for(Node c : group)
            {
                if(Game.getWinner(c.getTable()) == character)
                {
                    if(character == aiCharacter)
                    {
                        winNode = c;
                    }else
                    {
                        lossNode = c;
                    }
                }else
                {
                    children.add(c.getChildren());
                }
            }
        }
    }

    protected static void getWinNode(Node node)
    {
        getNode(node, aiCharacter);
    }

    protected static void getLossNode(Node node)
    {
        getNode(node, DecisionTree.switchPlayer(aiCharacter));
    }

    protected static Node getLossNode()
    {
        return lossNode;
    }

    protected static Node getWinNode()
    {
        return winNode;
    }

    private static String getResourcePath()
    {
        try
        {
            URI resoucePathFile = System.class.getResource("/RESOURCE_PATH").toURI();
            String resourcePath = Files.readAllLines(Paths.get(resoucePathFile)).get(0);
            URI rootURI = new File("").toURI();
            URI resourceURI = new File(resourcePath).toURI();
            URI relativeResourceURI = rootURI.relativize(resourceURI);
            return relativeResourceURI.getPath();
        }catch(Exception e)
        {
            return null;
        }
    }
    protected static void saveTree()
    {
        String path = getResourcePath()+"/tree.ser";
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

        }catch(IOException ioe)
        {

        }
    }
}
