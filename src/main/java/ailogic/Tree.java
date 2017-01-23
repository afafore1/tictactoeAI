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
    protected static Queue<Node> wins = new LinkedList<Node>();
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
