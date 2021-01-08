package com.lecture12.Tree;

import java.util.Scanner;

public class BinaryTree {
    private class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    Node root = null;
    int size = 0;

    BinaryTree(){
        Scanner sc = new Scanner(System.in);
        this.root = takeInput(sc, null, false);
    }

    private Node takeInput(Scanner sc, Node parent, boolean isLeft) {
        if(parent == null) {
            System.out.println("Enter the data for the root node :");
        } else {
            if(isLeft) {
                System.out.println("Enter the node for left child of :"+parent.data);
            } else {
                System.out.println("Enter the node for right child of :"+parent.data);
            }
        }
        int nodeData = sc.nextInt();
        Node node = new Node(nodeData, null, null);
        this.size++;
        boolean leftChoice = false;
        System.out.println("Do you want to have the left child :"+node.data);
        leftChoice = sc.nextBoolean();
        if(leftChoice){
            node.left = takeInput(sc, node, true);
        }
        boolean rightChoice = false;
        System.out.println("Do you want to have the right child :"+node.data);
        rightChoice = sc.nextBoolean();
        if(rightChoice){
            node.right = takeInput(sc, node, true);
        }
        return node;
    }

    public void display() {
        this.display(this.root);
    }

    private void display(Node node){
        String str = "";
        if(node.left != null){
            str = str + node.left.data + "=>";
        } else {
            str = str + "END";
        }
        str = str + node.data;
        if(node.right != null) {
            str = str + node.right.data + "<=";
        } else {
            str = str + "END";
        }
        System.out.println(str);
        if(node.left != null){
            this.display(node.left);
        }
        if(node.right != null) {
            this.display(node.right);
        }
    }
}
