package src;
public class BSTNode {
    // initalize instance variables
    private Record item;
    private BSTNode parent;
    private BSTNode left;
    private BSTNode right;
    // initalize constructor
    public BSTNode(Record item) {
        this.item = item;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
    // set setters and getters
    public Record getRecord() {
        return this.item;
    }

    public void setRecord(Record d) {
        this.item = d;
    }

    public BSTNode getLeftChild() {
        return this.left;
    }

    public BSTNode getRightChild() {
        return this.right;
    }

    public BSTNode getParent() {
        return this.parent;
    }

    public void setLeftChild(BSTNode u) {
        this.left = u;
        if (u != null) {
            // set parent to the current instance of the class
            u.setParent(this);
        }
    }

    public void setRightChild(BSTNode u) {
        this.right = u;
        if(u != null) {
            u.setParent(this);
        }
    }

    public void setParent(BSTNode u) {
        this.parent = u;
    }

    public boolean isLeaf() {
        // a node is a leaf is it's two children are null
        if (this.left == null && this.right == null)
            return true;
        return false;
    }
}
