package com.lecture12.Tree;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

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

    private int height(Node node) {
        //Base Condition
        if(node == null){
            return -1;
        }
        int lheight = this.height(node.left);
        int rheight = this.height(node.right);

        return Math.max(lheight,rheight) + 1;
    }

    public void PreOrder(Node root) {
        if(root != null){
            System.out.print(root.data+" ");
        }
        PreOrder(root.left);
        PreOrder(root.right);
    }

    public ArrayList<Integer> preOrderTraversal(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<Node> s = new Stack<Node>();
        s.push(root);
        while(!s.isEmpty()){
            Node temp = s.pop();
            res.add(temp.data);

            if(temp.right != null)
                s.push(temp.right);

            if(temp.left != null)
                s.push(temp.left);
        }
        return res;
    }

    public void InOrder(Node root){
        if(root != null){
            InOrder(root.left);
            System.out.println(root.data);
            InOrder(root.right);
        }
    }

    public ArrayList<Integer> inOrder(Node root){
        ArrayList<Integer> res = new ArrayList<Integer>();
        Stack<Node> s = new Stack<Node>();
        Node currNode = root;
        boolean done = false;
        while(!done) {
            if(currNode != null){
                s.push(currNode);
                currNode = currNode.left;
            } else {
                if(s.isEmpty()) {
                    done = true;
                } else {
                    currNode = s.pop();
                    res.add(currNode.data);
                    currNode = currNode.right;
                }
            }
        }
        return res;
    }
}
