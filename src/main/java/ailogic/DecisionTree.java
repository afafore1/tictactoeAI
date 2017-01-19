package ailogic;

import game.Game;

import java.util.ArrayList;

/**
 * Created by Ayomitunde on 1/17/2017.
 */
public class DecisionTree {
    private int MAX_TREE_LEVEL = 3;
    private char player;
    private int nodeId = 0;

    public DecisionTree(char player, char[][] table)
    {
        this.player = player;
        Node rootNode = new Node(nodeId, table, true);
        nodeId+= 1;
        rootNode.markVisited();
        buildNodeTable(rootNode);
    }

    private ArrayList<String> getFreeLocations(Node node)
    {
        return Game.freeLocations(node.getTable());
    }

    public void buildNodeTable(Node node)
    {
        if(node.canAddChild())
        {
            displayTree(node);
            ArrayList<String> freeLocations = getFreeLocations(node);
            int randomLocation = (int) (Math.random() * freeLocations.size());
            player = switchPlayer(player);
            if(!freeLocations.isEmpty())
            {
                String chosenInput = freeLocations.get(randomLocation);
                char[][] oldTable = node.getTable();
                char [][] newTable = getNewTable(chosenInput, player, oldTable);
                Node childNode = new Node(nodeId, newTable, false);
                node.addChild(childNode);
            }
            buildNodeTable(node);
        }else
        {
            nodeId+= 1;
            if(MAX_TREE_LEVEL < 0)
            {
                for(Node n : node.getChildren())
                {
                    if(!n.isVisited())
                    {
                        n.markVisited();
                        buildNodeTable(n);
                    }
                }
                MAX_TREE_LEVEL--;
            }
        }
    }

    public void displayTree(Node node)
    {
        displayBoard(node);
        for(Node n : node.getChildren())
        {
            displayBoard(n);
        }
    }

    public char [][] getNewTable(String input, char value, char [][] table)
    {
        int row = Integer.parseInt(String.valueOf(input.charAt(0)));
        int col = Integer.parseInt(String.valueOf(input.charAt(1)));
        table[row][col] = value;
        return table;
    }

    public char Evaluate(char [][] table)
    {
        return Game.getWinner(table);
    }

    public String getMove()
    {
        return "";
    }

    public static void displayBoard(Node node) {
        char[][] nodeTable = node.getTable();
        System.out.println("--------------------------------"+node.getNodeId()+"--------------------------------");
        System.out.println("    " + nodeTable[0][0] + "|" + nodeTable[0][1] + "|" + nodeTable[0][2]);
        System.out.println("   --+-+--");
        System.out.println("    " + nodeTable[1][0] + "|" + nodeTable[1][1] + "|" + nodeTable[1][2]);
        System.out.println("   --+-+--");
        System.out.println("    " + nodeTable[2][0] + "|" + nodeTable[2][1] + "|" + nodeTable[2][2]);
        System.out.println("===========================================");
    }

    public static char switchPlayer(char player)
    {
        return player == 'o' ? 'x' : 'o';
    }

}
