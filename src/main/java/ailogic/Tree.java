package ailogic;

import game.Game;

import java.util.*;

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


    protected static boolean checkTableExist(char [][] tableToCheck)
    {
        for(Node node : allNodes)
        {
            if(Arrays.deepEquals(tableToCheck, node.getTable()))
            {
                foundNode = node;
                getWinNode(node);
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

    protected static void getWinNode(Node node)
    {
        Queue<HashSet<Node>> children = new LinkedList<HashSet<Node>>();
        children.add(node.getChildren());
        while(!children.isEmpty())
        {
            HashSet<Node> group = children.remove();
            for(Node c : group)
            {
                if(Game.getWinner(c.getTable()) == DecisionTree.aiCharacter)
                {
                    winNode = c;
                }else
                {
                    children.add(c.getChildren());
                }
            }
        }
    }

    protected static Node getWinNode()
    {
        return winNode;
    }
}
