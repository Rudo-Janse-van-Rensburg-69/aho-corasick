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
public class Queue {
    public Node head, tail;
    public Queue() {
    
    }
    
    public void enqueue(Node node){
        if(head == null){
            head = tail = node;
        }else{
            node.next = tail;
            tail = node;
        }
    }
    
    public Node dequeue(){
        Node temp; 
        if(head==tail){
            temp = head;
            head = tail = null;
        }else{
            temp = head;
            Node iterator = tail;
            while(iterator != null && iterator.next != head){
                iterator = iterator.next;
            }
            iterator.next = null;
            head = iterator;
        }
        return temp;
    }
    
    public boolean empty(){
        return (head == null);
    }
    
    public void emptyQueue(){
        head = tail = null;
    }
}
