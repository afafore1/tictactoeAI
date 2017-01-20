package ailogic;

import game.Game;

import java.util.*;

/**
 * Created by Ayomitunde on 1/17/2017.
 */
public class DecisionTree {
    private int MAX_TREE_LEVEL = 3;
    private char player;
    private int nodeId = 0;
    private Queue<Node> queue;

    public DecisionTree(char player, char[][] table)
    {
        this.player = switchPlayer(player);
        Node rootNode = new Node(nodeId, table, true);
        nodeId+= 1;
        queue = new LinkedList<Node>();
        queue.add(rootNode);
        buildNodeTable(rootNode);
    }

    private ArrayList<String> getFreeLocations(Node node)
    {
        return Game.freeLocations(node.getTable());
    }

    public void buildNodeTable(Node node)
    {
        node.markVisited();
        HashMap<Integer, HashSet<Node>> marker = new HashMap<Integer, HashSet<Node>>(); //this is just used to keep track.. better way to do this ?
        int lenToCheck = 3;
        while(!queue.isEmpty() && MAX_TREE_LEVEL > 0)
        {
            Node current = queue.remove();
            System.out.println("---------------------------------------------------------\n---------------------------------------------------------\n---------------------------------------------------------\nNew Child");
            buildChildNodeTable(current);
            if(marker.containsKey(current.getNodeId()))
            {
                HashSet<Node> children = current.getChildren();
                HashSet<Node> allChildren = new HashSet<Node>(children);
                allChildren.addAll(marker.get(current.getNodeId()));
                marker.put(current.getNodeId(), allChildren);
            }else
            {
                marker.put(current.getNodeId(), current.getChildren());
            }
            if(moveToNext(marker.get(current.getNodeId()), lenToCheck))
            {
                player = switchPlayer(player);
                nodeId+= 1;
                MAX_TREE_LEVEL--;
                lenToCheck *= 2;
            }
            displayTree(current);
            current.markVisited();
        }
    }

    public void buildChildNodeTable(Node node)
    {
        if(node.canAddChild())
        {
            ArrayList<String> freeLocations = getFreeLocations(node);
            int randomLocation = (int) (Math.random() * freeLocations.size());
            if (!freeLocations.isEmpty())
            {
                String chosenInput = freeLocations.get(randomLocation);
                char[][] oldTable = node.getTable();
                char[][] newTable = getNewTable(chosenInput, player, oldTable);
                Node childNode = new Node(nodeId, newTable, false);
                node.addChild(childNode);
                queue.add(childNode);
            }
            buildChildNodeTable(node);
        }
    }

    private boolean moveToNext(HashSet<Node> children, int lenToCheck)
    {
        if(children.size() == lenToCheck) return true;
        return false;
    }

    private boolean isAllMarked(int idToCheck, HashSet<Node> allChildren)
    {
        for(Node n : allChildren)
        {
            if(n.canAddChild() == true)
            {
                return false;
            }
        }
        return true;
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
        char [][] newTable = new char[table.length][table.length];
        for(int i = 0; i < table.length; i++)
        {
            for(int j = 0; j < table.length; j++)
            {
                newTable[i][j] = table[i][j];
            }
        }
        newTable[row][col] = value;
        return newTable;
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
