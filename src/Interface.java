package src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class Interface {
    public static void main(String[] args) throws IOException, MultimediaException, DictionaryException {
        // if length of arguments is less than 1, then no file was inputted
        if(args.length < 1) { 
            System.out.println("No input file specified");
            return;
        }
        // fileName is the argument given
        String fileName = args[0];
        // create dictionary instance
        BSTDictionary dictionary = new BSTDictionary();
        try {
            
            BufferedReader in = new BufferedReader(new FileReader(fileName));

            String line = "";
            int iterator = 1;
            int type = 1;
            String label = "";
            String data = "";
            String fileType;
            // iterate through each line in file given until it ends
            while((line = in.readLine()) != null) {
                fileType = "";
                // if iterator is an even number, then get record data
                if(iterator % 2 == 0) {
                    data = line;
                    // switch case depending on the first letter of the line, change type accordingly
                    switch(data.charAt(0)) {
                            case '-':
                                type = 3;
                                break;
                            case '+':
                                type = 4;
                                break;
                            case '*':
                                type = 5;
                                break;
                            case '/':
                                type = 2;
                                break;
                            default:
                                type = 1;
                                break;
                        }
                    // createa tokenizer that delimits a '.', to find fileType ex. jpg, html
                    StringTokenizer tokenizer = new StringTokenizer(data, ".");

                    while (tokenizer.hasMoreTokens()) {
                        fileType = tokenizer.nextToken().toLowerCase();
                    }
                    if(fileType != null) {
                        // switch case that sets type accordingly
                        switch(fileType) {
                            case "gif":
                                type = 7;
                                break;
                            case "jpg":
                                type = 6;
                                break;
                            case "html":
                                type = 8;
                                break;
                        }
                    }

                    // if type is between 2 and 5, then remove the first letter, so that the files can be ran
                    if(type >= 2 && type <= 5) {
                        data = data.substring(1);
                    }
                    // put instance in dictionary with information gathered
                    dictionary.put(new Record(new Key(label, type), data));
                    }   
                else {
                    label = line.toLowerCase();
                }
                // linearlly increase iterator
                iterator += 1;

            }
        }
        catch (DictionaryException e) {
            System.out.println("Dictionary Exception");
        }

        String input = "";
        StringReader keyboard = new StringReader();

        while(!input.equals("exit")) {
            Key key;
            int type;
            String label = "";
            input = keyboard.read("Enter next command: ");
            StringTokenizer tokenizer = new StringTokenizer(input, " ");
            String command = tokenizer.nextToken();
            if(tokenizer.hasMoreTokens())
                label = tokenizer.nextToken().toLowerCase();
            // switch case depending on what the user enters as the first word
            switch(command) {
                // if "define" is the frist word given
                case "define":
                    // create key instance given label, and assuming type is 1
                    key = new Key(label, 1);
                    // if key exists, then get data item and print it out
                    if(dictionary.get(key) != null) {
                        System.out.println(dictionary.get(key).getDataItem());
                    }
                    // otherwise, key does not exist, so output that accordingyl
                    else {
                        System.out.println("The word " + label + " is not in the dictionary");
                    }
                    break;
                case "translate":
                    // create key instance given label, assuming type is 2
                    key = new Key(label, 2);
                    // if key exists, get data item and print it out
                    if(dictionary.get(key) != null) {
                        System.out.println(dictionary.get(key).getDataItem());
                    }
                    else {
                        System.out.println("There is no definition for the word " + label);
                    }
                    break;
                case "sound":
                    key = new Key(label, 3);
                    // if key exists, then play sound
                    if(dictionary.get(key) != null) {
                        try {
                            SoundPlayer sound = new SoundPlayer();
                            sound.play(dictionary.get(key).getDataItem());
                        }
                        catch(MultimediaException e) {}
                    }
                    // otherwise, output error
                    else {
                        System.out.println("There is no sound file for " + label);
                    }
                    break;
                case "play":
                    key = new Key(label, 4);
                    // if key exists, play sound
                    if(dictionary.get(key) != null) {
                        SoundPlayer sound = new SoundPlayer();
                        sound.play(dictionary.get(key).getDataItem());
                    }
                    else {
                        System.out.println("There is no music file for " + label);
                    }
                    break;
                case "say":
                    key = new Key(label, 5);
                    // if key exists, play sound
                    if(dictionary.get(key) != null) {
                        SoundPlayer sound = new SoundPlayer();
                        sound.play(dictionary.get(key).getDataItem());
                    }
                    else {
                        System.out.println("There is no voice file for " + label);
                    }
                    break;
                case "show":
                    key = new Key(label, 6);
                    // if key exists, display picture
                    if(dictionary.get(key) != null) {
                        PictureViewer picture = new PictureViewer();
                        picture.show(dictionary.get(key).getDataItem());
                    }
                    else {
                        System.out.println("There is no image file for " + label);
                    }
                    break;
                case "animate":
                    key = new Key(label, 7);
                    // if key exists, display picture
                    if(dictionary.get(key) != null) {
                        PictureViewer picture = new PictureViewer();
                        picture.show(dictionary.get(key).getDataItem());
                    }
                    else {
                        System.out.println("There is no image file for " + label);
                    }
                    break;
                case "browse":
                    key = new Key(label, 8);
                    // if key exists, display url
                    if(dictionary.get(key) != null) {
                        ShowHTML html = new ShowHTML();
                        html.show(dictionary.get(key).getDataItem());
                    }
                    else {
                        System.out.println("There is no webpage called " + label);
                    }
                    break;
                case "delete":
                    // get type
                    type = Integer.parseInt(tokenizer.nextToken());
                    // create key given label and type
                    key = new Key(label, type);
                    // if node exists, remove it 
                    if(dictionary.get(key) != null) {
                        dictionary.remove(key);
                    }
                    // otherwise, print error
                    else {
                        System.out.println("No record in the ordered dictionary has key (" + label + "," + type + ")");
                    }
                    break;
                case "add":
                // get type, and record_string 
                    type = Integer.parseInt(tokenizer.nextToken());
                    String record_string = "";
                    while(tokenizer.hasMoreTokens()) {
                        // as string can be multiple words long, account it using a while loop that checks if the tokenizer has more tokens
                        record_string += tokenizer.nextToken();
                        record_string += " ";
                    }
                    // create new key and record
                    key = new Key(label, type);
                    Record record = new Record(key, record_string);
                    // put into dictoinary
                    dictionary.put(record);
                    break;
                case "list":
                    // label = prefix
                    boolean printed = false;
                    Record iter = dictionary.smallest();
                    // create labels array that gathers all words with prefix
                    ArrayList<String> labels = new ArrayList<>();
                    // get successor record and largest
                    Record successor = dictionary.successor(iter.getKey());
                    Record largest = dictionary.largest();
                    // while successor is a valid node, and while the successor has not reach largest
                    while(successor != null && successor.getDataItem() != largest.getDataItem()) {
                        String key_label = successor.getKey().getLabel();
                        // check if label starts with the prefix, if so, add it to array
                        if(key_label.startsWith(label)) {
                            printed = true;
                            labels.add(key_label);
                        }
                        // linerally iterate through successors
                        iter = successor;
                        successor = dictionary.successor(iter.getKey());
                    }
                    if(!printed)
                        System.out.println("No label attributes in the ordered dictionary start with the prefix " + label);
                    else {
                        // if labels array size is 1, then simply print it
                        if(labels.size() == 1) {
                            System.out.println(labels.get(0));
                        }
                        else {
                            // create a for loop if labels is greater than 1
                            for(int i = 0; i < labels.size(); i ++) {
                                if(i == labels.size() - 1) {
                                    // if last instance is reached, do not print comma
                                    System.out.print(labels.get(i));
                                }
                                else {
                                    System.out.print(labels.get(i) + ", ");
                                }
                            }
                            System.out.println("");
                        }
                    }
                    break;

                case "first":
                    // get smallest key, and print all attributes
                    Record small = dictionary.smallest();
                    Key small_key = small.getKey();
                    System.out.println(small_key.getLabel() + "," + small_key.getType() + "," + small.getDataItem());
                    break;
                case "last":
                // get largest key, and print all attributes
                    Record large = dictionary.largest();
                    Key large_key = large.getKey();
                    System.out.println(large_key.getLabel() + "," + large_key.getType() + "," + large.getDataItem());
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;

            }   



            
        }
        
    }

}