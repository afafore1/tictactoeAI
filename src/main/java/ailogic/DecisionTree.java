package ailogic;

import game.Game;

import java.util.*;

/**
 * Created by Ayomitunde on 1/17/2017.
 */
public class DecisionTree {
    Node rootNode = null;
    private int MAX_TREE_LEVEL = 8;
    private char player;
    public static char aiCharacter;
    private int nodeId = 0;
    private Queue<Node> queue;

    public DecisionTree(char player, char[][] table)
    {
        this.player = switchPlayer(player);
        rootNode = new Node(nodeId, createNodeId(table), table, true);
        nodeId+= 1;
        queue = new LinkedList<Node>();
        queue.add(rootNode);
    }


    private long createNodeId(char[][] table)
    {
        String nodeId = "";
        for(int i = 0; i < table.length; i++)
        {
            for(int j = 0; j < table.length; j++)
            {
                if(table[i][j] != ' ')
                {
                    nodeId+=i+""+j;
                }
            }
        }
        return Long.parseLong(nodeId);
    }

    private ArrayList<String> getFreeLocations(Node node)
    {
        return Game.freeLocations(node.getTable());
    }

    public void buildNodeTable()
    {
        HashMap<Integer, HashSet<Node>> marker = new HashMap<Integer, HashSet<Node>>(); //this is just used to keep track.. better way to do this ?
        int lenToCheck = 3;
        while(!queue.isEmpty() && MAX_TREE_LEVEL > 0 && Tree.wins.isEmpty())
        {
            Node current = queue.remove();
            if(current.isVisited()) continue;
            //System.out.println("---------------------------------------------------------\n---------------------------------------------------------\n---------------------------------------------------------\nNew Child");
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
                Node childNode = new Node(nodeId, createNodeId(newTable), newTable, false);
                Tree.allNodes.add(childNode); // any child created will be added here
                node.addChild(childNode);
                childNode.setParent(node);
                queue.add(childNode);
                if(Game.getWinner(newTable) == aiCharacter)
                {
                    Tree.wins.add(childNode);
                }else if(Game.getWinner(newTable) == switchPlayer(aiCharacter))
                {
                    Tree.loss.add(childNode);
                }else if(Game.isTie(newTable))
                {
                    Tree.draw.add(childNode);
                    childNode.markVisited();
                }
            }
            buildChildNodeTable(node);
        }
    }

    private boolean moveToNext(HashSet<Node> children, int lenToCheck)
    {
        if(children.size() == lenToCheck) return true;
        return false;
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

    public String getNextMove()
    {
        if(Tree.checkTableExist(rootNode.getTable()))
        {
            Node winNode = Tree.getWinNode();
            if(winNode != null)
            {
                char[][] lookUpTable = getNodeToPlay(winNode, Tree.foundNode);
                return getNextMove(lookUpTable);
            }
        }
        System.out.println("There is no way to win for AI.. rebuilding...");
        buildNodeTable();
        if(!Tree.wins.isEmpty())
        {
            Node winNode = Tree.wins.get(0);
            Tree.wins.remove(0);
            char[][] lookUpTable = getNodeToPlay(winNode, rootNode);
            System.out.println("AI found a way to win!"+Arrays.deepToString(winNode.getTable()));
            return getNextMove(lookUpTable);
        }
        else if(!Tree.loss.isEmpty())
        {
            Node lossNode = Tree.loss.get(0);
            // have to avoid loss
        }
        int randomIndex = (int)(Math.random() * rootNode.getChildren().size());
        char[][] lookUpTable = new ArrayList<Node>(rootNode.getChildren()).get(randomIndex).getTable(); // this is quite messy.. fix it!
        System.out.println("Just playing around\nUsing this table "+Arrays.deepToString(lookUpTable));
        System.out.println("Game Table is "+Arrays.deepToString(rootNode.getTable()));
        return getNextMove(lookUpTable);
    }

    private char[][] getNodeToPlay(Node winNode, Node stopNode)
    {
        Node parent = winNode.getParent();
        if(parent.equals(stopNode) || stopNode.isRoot()) return winNode.getTable();
        return getNodeToPlay(parent, stopNode);
    }

    public String getNextMove(char[][] lookUpTable)
    {
        char[][] gameTable = rootNode.getTable();
        for(int i = 0; i < lookUpTable.length; i++)
        {
            for(int j = 0; j < lookUpTable.length; j++)
            {
                if(lookUpTable[i][j] != gameTable[i][j])
                {
                    return ""+i+""+j;
                }
            }
        }
        return null;
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
