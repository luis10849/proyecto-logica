/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.LinkedList;
import java.util.Optional;

/**
 *
 * @author Lenovo
 */
/**
 * @author raidentrance
 *
 */
public class Node<T>{
    private T value;
    private Node left;
    private Node right;
 
    public Node(T value) {
        this.value = value;
    }
 
    public T getValue() {
        return value;
    }
 
    public void setValue(T value) {
        this.value = value;
    }
 
    public Node getLeft() {
        return left;
    }
 
    public void setLeft(Node left) {
        this.left = left;
    }
 
    public Node getRight() {
        return right;
    }
 
    public void setRight(Node right) {
        this.right = right;
    }
 
    public void add(T elemento,Integer value) {
        if (value < 0) {
            if (left != null) {
                left.add(elemento,value);
            } else {
                left = new Node(value);
            }
        } else {
            if (right != null) {
                right.add(elemento,value);
            } else {
                right = new Node(elemento);
            }
        }
    }
    
 
   /**
    *  public Optional<Node> find(Integer value) {
        if (value == this.value) {
            return Optional.of(this);
        } else if (value < this.value) {
            if (this.left != null) {
                return this.left.find(value);
            } else {
                return Optional.empty();
            }
        } else {
            if (this.right != null) {
                return this.right.find(value);
            } else {
                return Optional.empty();
            }
        }
    }
    * @param value
    * @return 
    */
   
 
    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.println(value);
        if (right != null) {
            right.printInOrder();
        }
    }
 
    public void printPreOrder() {
        System.out.println(value);
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
    }
 
    public void printPosOrder() {
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
        System.out.println(value);
    }
     public LinkedList postOrden() {
        LinkedList rec = new LinkedList();
        postorden(this, rec);
        return rec;
    }
    public void postorden(Node aux, LinkedList recorrido) {
        if (aux != null) {
            postorden(aux.getLeft(), recorrido);
            postorden(aux.getRight(), recorrido);
            recorrido.add(aux.getValue());
        }
    }
 
    @Override
    public String toString() {
        return "Node [value=" + value + ", left=" + left + ", right=" + right + "]";
    }
 
}

