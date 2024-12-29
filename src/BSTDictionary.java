package src;

public class BSTDictionary implements BSTDictionaryADT{
    private BinarySearchTree bst;
    public BSTDictionary() {
        this.bst = new BinarySearchTree();
    }
    /* Returns the Record object with the Key as k, or it returns null if such 
       a Record is not in the dictionary. */
    public Record get(Key k) {
        BSTNode node = bst.get(bst.getRoot(), k);
        if(node == null) { return null;}
        return node.getRecord();
        
    }

    @Override
      /* Inserts the Record d into the ordered dictionary. It throws a DictionaryException 
       if a Record with the same Key attribute as d is already in the dictionary. */
    public void put(Record d) throws DictionaryException {
        bst.insert(bst.getRoot(), d);

    }



    @Override
    /* Removes the Record with Key k from the dictionary. It throws a
DictionaryException if the Record is not in the dictionary. */
    public void remove(Key k) throws DictionaryException {
        bst.remove(bst.getRoot(), k);
    }

    @Override
    public Record successor(Key k) {
        BSTNode node = bst.successor(bst.getRoot(), k);
        if(node == null) { return null;} return node.getRecord();
    }

    @Override
    public Record predecessor(Key k) {
        BSTNode node = bst.predecessor(bst.getRoot(), k);
        if(node == null) return null; return node.getRecord();
    }

    @Override
    public Record smallest() {
        BSTNode node = bst.smallest(bst.getRoot());
        if(node == null) return null; return node.getRecord();
    }

    @Override
    public Record largest() {
        BSTNode node = bst.largest(bst.getRoot());
        if(node == null) return null; return node.getRecord();
    }

    
}
