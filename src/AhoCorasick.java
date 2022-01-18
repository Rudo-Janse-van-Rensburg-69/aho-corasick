/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aho.corasick;

/**
 *
 * @author rudo5
 */
public class AhoCorasick {
    final static Queue queue = new Queue();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        try {
            //Algorithm 2: construction of the goto function
            Trie trie = new Trie();
            String[] keywords = {"he", "she", "his", "hers"};
            for (int i = 0; i < keywords.length; i++) {
                trie.enter(keywords[i].toLowerCase());
            }
            
            for (int i = 0; i < trie.root.children.length; i++) {
                if (trie.Goto(trie.root, (char)('a'+i) ) == null) {
                    trie.root.children[i] = trie.root;
                }
            }
            //trie.determineFailures(trie.root);
            
            //Algorithm 3: construction of the failure function
            queue.emptyQueue();
            for (int i = 0; i < trie.root.children.length; i++) {
                if(trie.Goto(trie.root,i) != trie.root){
                    queue.enqueue(trie.Goto(trie.root,i)); 
                }
                while(!queue.empty()){
                    Node r = queue.dequeue();
                    for (int j = 0; j < r.children.length; j++) {
                        if(trie.Goto(r, i) != null){
                            queue.enqueue(trie.Goto(r, i));
                            Node state = trie.failure(r);
                            while(trie.Goto(state, j) == null){
                                state = trie.failure(state);
                            }
                        }
                    }
                }
            }
            Node r, state;
            while(!queue.empty()){
                r = queue.dequeue();
                for (int i = 0; i < r.children.length; i++) {
                    if(trie.Goto(r, i) != null){
                        queue.enqueue(trie.Goto(r, i));
                        state = trie.failure(r);
                        while(trie.Goto(state, i) == null){
                            state = trie.failure(state);
                        }
                        
                    }
                }
            }
        }catch(NullPointerException ne){
            System.out.println("Nullpointer Exception");
        }
        /*catch(ArrayIndexOutOfBoundsException aiob){
            System.out.println("ArrayIndexOutOfBoundsException");
        }*/

    }

}
