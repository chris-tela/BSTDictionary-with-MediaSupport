package src;

public class BinarySearchTree {
    // initalize root node
    private BSTNode root;
    public BinarySearchTree() {
        this.root = null;
    }

    public BSTNode getRoot() {
        return root;
    }
    // get returns the node given the key, starting from root r
    public BSTNode get(BSTNode r, Key k) {
        // if root is null, then return null, as there are no nodes in the tree
        if(r == null) {
            return null;
        }
        // if key is equal to the current node, then return node, as it has been found
        if (r.getRecord().getKey().compareTo(k) == 0) {
            return r;
        }

        else {
            // if current node is 'less' than the node to be found, then search for the right child
            if (r.getRecord().getKey().compareTo(k) < 0) {
                return get(r.getRightChild(), k);
            }
            // otherwise, search less
            else {
                return get(r.getLeftChild(), k);
                }
            }
        }

    /*Adds the record to the binary search tree with
root r. Throws a DictionaryException if the tree already stores a record with the same
key as d.
 */
    public void insert(BSTNode r, Record d) throws DictionaryException {
        // if root is null, or record is null, then create a new node and return
        if (root == null || root.getRecord() == null) {
            root = new BSTNode(d);
            root.setRecord(d);
            return;
        }
        // cmp variable is either -1, 0, or 1, depedning on whether the current node is less than or greater than the node to be inserted
        int cmp = d.getKey().compareTo(r.getRecord().getKey());
        // if less
        if (cmp < 0) {
            // if leftChlild is null, then set new left child, otherwise, keep searching
            if (r.getLeftChild() == null) r.setLeftChild(new BSTNode(d));
            else insert(r.getLeftChild(), d);
        } else if (cmp > 0) {
            // if rightChild is null, then set new right child, otherwise, keep searching
            if (r.getRightChild() == null) r.setRightChild(new BSTNode(d));
            else insert(r.getRightChild(), d);
        // if cmp is 0, then the record already exists, tso throw exception
        } else throw new DictionaryException("Record already exists");
    }



/*
 * public void remove (BSTNode r, Key k): Deletes the node with the given key from the tree
with root r. Throws a DictionaryException if the tree does not store a record with the
given key.
 */
public void remove(BSTNode r, Key k) throws DictionaryException {
    // Locate the node to remove
    BSTNode p = get(r, k);
    // if get is null, then there is no node to remove, so throw exceptioj
    if (p == null) {
        throw new DictionaryException("Tree does not store record!");
    }

     // Case 1: Node is a leaf (both children are null)
    if (p.getLeftChild() == null && p.getRightChild() == null) {
        if (p.getParent() != null) {
            BSTNode parent = p.getParent();
            if (parent.getLeftChild() == p) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
        }
        else {
            p.setRecord(null);
            p = null;
        }
        return;
    }

     // Case 2: Node has two children
    if (p.getLeftChild() != null && p.getRightChild() != null) {
        // Find in-order successor (smallest node in the right subtree)
        BSTNode successor = smallest(p.getRightChild());
        p.setRecord(successor.getRecord()); // Copy successor's record to `p`
        remove(p.getRightChild(), successor.getRecord().getKey()); // Recursively remove successor
        return;
    }

    // case 3: Node has only one child
    BSTNode child;

    if(p.getLeftChild() != null) {child = p.getLeftChild();}
    else {
        child = p.getRightChild();
    }
    if (p.getParent() != null) {
        BSTNode parent = p.getParent();
        if (parent.getLeftChild() == p) {
            parent.setLeftChild(child);
        } else {
            parent.setRightChild(child);
        }
        child.setParent(parent); // Update the child's parent reference
        return;
    }

    else {
        child.setParent(null);
    }
    
    }


    


    



    /*
     * public BSTNode successor(BSTNode r, Key k): Returns the node storing the successor of
the given key in the tree with root r; returns null if the successor does not exist.
     */
    public BSTNode successor(BSTNode r, Key k) {
        // get node
        BSTNode p = get(r, k);
        if(p == null) { 
            return null;
        }
        // if a right child exist, then find the smallest right child in the subtree
        if(p.getRightChild() != null) {
            return smallest(p.getRightChild());
        }
        
        else {
            // if right child doesnt exist, get parent, and keep linearlly searching through parents until a record is lesser than the current node
            p = p.getParent();
            while(p.getParent() != null && p.getRecord().getKey().compareTo(k) < 0) {
                p = p.getParent();
            }
            // return successor
            return p;          
        }
    }
    /*
     *  public BSTNode predecessor(BSTNode r, Key k): Returns the node storing the predecessor
of the given key in the tree with root r; returns null if the predecessor does not exist.
     */

    public BSTNode predecessor(BSTNode r, Key k) {
        // get node
        BSTNode p = get(r,k);

        if(p == null) {
            return null;
        }
        // if left child isnt null, then find largest in the left child subtree
        if(p.getLeftChild() != null) {
            return largest(p.getLeftChild());
        }
        else {
            // get parent
            p = p.getParent();
            // linearlly search through parents until a record is greater than 0
            while(p.getParent() != null && p.getRecord().getKey().compareTo(k) > 0) {
                p = p.getParent();
            }
            return p;
        }
    }


    /* • public BSTNode smallest(BSTNode r): Returns the node with the smallest key in tree with
root r. */

    public BSTNode smallest(BSTNode r) {
        if(r == null) {
            return null;
        }
        // keep looking through left child subtrees until it is a leaf node, then return smallest node
        else {
            BSTNode p = r;
            while(p.getLeftChild() != null) {
                p = p.getLeftChild();
            }
            return p;
        }
    }

    /*• public BSTNode largest (BSTNode r): Returns the node with the largest key in tree with
root r
 */

    public BSTNode largest(BSTNode r) {
        if(r == null) {return null;}
        else {
            BSTNode p = r;
            // keep looking through right child subtrees until it is a leaf node, then return largest node
            while(!p.isLeaf()) {
                p = p.getRightChild();
            }
            return p;
        }
    }


}
