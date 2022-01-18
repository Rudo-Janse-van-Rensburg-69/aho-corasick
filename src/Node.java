package aho.corasick;

public class Node {
    private static int number = 0;
    public int node_number;
    public char letter;
    public final static int ALPHABET_SIZE = 26;
    public boolean leaf;
    public Node children[];
    public Node suffixes[];
    public String output;
    public Node next;
    public Node(boolean leaf, char letter) {
        node_number = number++;
        this.letter = letter;
        this.leaf= leaf;
        children = new Node[ALPHABET_SIZE];
        suffixes = new Node[ALPHABET_SIZE];
    }
    
    public Node(){
        node_number = number++;
        children = new Node[ALPHABET_SIZE];
        suffixes = new Node[ALPHABET_SIZE];
        this.leaf = false;
    }
   
    public String toString(){
        return "Node Information:\n"+
                "-----------------\n"+
                "Letter  :   "+this.letter+"\n"+
                "Number  :   "+this.node_number+"\n"+
                 "-----------------\n";
    }
   
}
