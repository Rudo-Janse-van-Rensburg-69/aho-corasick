/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aho.corasick;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudo5
 */
public class Trie {

    final static int ALPHABET_SIZE = 26;
    public Node root;
    private List<Node> nodeList = new ArrayList<Node>();

    public Trie() {
        root = new Node();
    }

    public void enter(String word) {
        word = word.toLowerCase();
        Node state = root;
        int j = 0;
        while (Goto(state, word.charAt(j)) != null) {
            state = Goto(state, word.charAt(j));
            j++;
        }
        for (int p = j; p < word.length(); p++) {
            //boolean leaf, char letter
            Node newState = new Node((p == word.length() - 1), word.charAt(p));
            state.children[word.charAt(p) - 'a'] = newState;
            state = newState;
        }
        state.output = word;
        
    }

    public Node Goto(Node node, char letter) {
        return node.children[letter - 'a'];
    }

    public Node Goto(Node node, int letter) {
        return node.children[letter];
    }

    public void output(Node node) {
        System.out.println(node.output);
    }

    public int positionOfNextNode(Node ancestor, Node descendant) {
        //if direct descendant
        if (ancestor != null) {
            if (Goto(ancestor, descendant.letter) == descendant) {
                return (descendant.letter - 'a');
            } else {
                //otherwise, determine if there are any paths leading from ancestor to descendant
                for (int i = 0; i < ancestor.children.length; i++) {
                    if (positionOfNextNode(Goto(ancestor, i), descendant) >= 0) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public int depth(Node node) {
        if (node == root) {
            return 0;
        } else {
            int depth = 0;
            Node iterator = root;
            while (iterator != node) {
                ++depth;
                iterator = Goto(iterator, positionOfNextNode(iterator, node));
            }
            return depth;
        }
    }

    public Queue NodesWithdDepth(int d, Node node, Node target, Queue queue) {
        if (depth(node) == d && node.children[target.letter - 'a'] != target) {
            queue.enqueue(node);
        } else {
            for (int i = 0; i < node.children.length; i++) {
                if (Goto(node, i) != null && Goto(node, i) != root) {
                    queue = NodesWithdDepth(d, Goto(node, i), target, queue);
                }
            }
        }
        return queue;
    }

    

    public Node parent(Node node) {
        if (node == root) {
            return root;
        } else {
            Node iter = root;
            while (iter != null && Goto(iter, node.letter) != node) {
                int pos = positionOfNextNode(iter, node);
                iter = iter.children[pos];
            }
            return iter;
        }

    }

    public Node failure(Node node) {
        if (depth(node) == 1) return root; 
        else return Goto(failure(parent(node)),node.letter);
    }

}
