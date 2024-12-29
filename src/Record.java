package src;
// public class record creates the record variable for an element in the ordered dictionary

public class Record {
    // initalize instance variables
    private Key theKey;
    private String data;
    // initalize constructor
    public Record(Key k, String theData) {
        this.theKey = k;
        this.data = theData;
    }

    public Key getKey() {
        return theKey;
    }

    public String getDataItem() {
        return data;
    }
}
