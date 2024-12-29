package src;


public class Key {
    // initalize instance variables
    private String label;
    private int type;
    // initalize constructor
    public Key(String theLabel, int theType) {
        this.label = theLabel.toLowerCase();
        this.type = theType;
    }

    public String getLabel() {
        return label;
    }

    public int getType() {
        return type;
    }
    // compare to compares key lexiographically and compares the types
    public int compareTo(Key k) {
        // First, compare by label
        int labelComparison = label.compareTo(k.label);
        
        // If labels are different, return the result of label comparison
        if (labelComparison != 0) {
            return labelComparison;
        }
    
        // If labels are the same, compare by type
        if (this.type < k.type) {
            return -1; // Current object's type is less than the other
        } else if (this.type > k.type) {
            return 1;  // Current object's type is greater than the other
        }
    
        // If both label and type are equal
        return 0;
    }
    

  


}
