package ailogic;

import java.util.HashSet;

/**
 * Created by Ayomitunde on 1/17/2017.
 */
public class Node {
    private HashSet<Node> children;
    private char [][] nodeTable;
    private boolean isRoot;
    private boolean isVisited = false;
    private int nodeId;
    private Node parent;

    public Node(int id, char[][] table, boolean isRoot)
    {
        nodeId = id;
        nodeTable = table;
        this.isRoot = isRoot;
        children = new HashSet<Node>();
        parent = null; // root will be null
    }

    public void setParent(Node p)
    {
        parent = p;
    }

    public Node getParent()
    {
        return parent;
    }

    public HashSet<Node> getChildren()
    {
        return children;
    }

    public boolean canAddChild()
    {
        if(this.isRoot)
        {
            return children.size() < 3;
        }
        return children.size() < 2;
    }

    public void addChild(Node node)
    {
        children.add(node);
    }

    public char[][] getTable()
    {
        return nodeTable;
    }

    public void markVisited()
    {
        isVisited = true;
    }

    public boolean isVisited()
    {
        return isVisited;
    }

    public boolean isRoot()
    {
        return isRoot;
    }

    public int getNodeId(){return nodeId; }
}
