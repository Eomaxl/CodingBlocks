package com.lecture12.Tree;

import org.w3c.dom.Node;

public class BST {
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

    public boolean isBST() {
        return this.isBST(this.root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean isBST(Node node, int min, int max) {
        if(node == null) {
            return true;
        }
        if(node.data > max || node.data <min) {
            return false;
        } else if( !this.isBST(node.left, min, node.data)) {
            return false;
        } else if(!this.isBST(node.right, node.data, max)) {
            return false;
        }
        return true;
    }
}
