package ailogic;

import java.util.ArrayList;
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
    private long uniqueId;
    private Node parent;
    private Node leftChild = null;
    private Node rightChild = null;
    private boolean stopAtNode = false;

    public Node(int id, long uniqueId, char[][] table, boolean isRoot)
    {
        nodeId = id;
        nodeTable = table;
        this.isRoot = isRoot;
        this.uniqueId = uniqueId;
        children = new HashSet<Node>();
        parent = null; // root will be null
    }

    public long getUniqueId()
    {
        return uniqueId;
    }

    protected boolean getStopAtNode()
    {
        return stopAtNode;
    }

    protected void setStopAtNode(boolean b)
    {
        stopAtNode = b;
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
        if(node.getUniqueId() > this.getUniqueId()) this.rightChild = node;
        this.leftChild = node;
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
