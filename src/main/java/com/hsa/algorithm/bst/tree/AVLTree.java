package com.hsa.algorithm.bst.tree;

import static java.lang.Math.max;

public class AVLTree {


  Node root;

  public Node getRoot() {
    return root;
  }

  public void setRoot(Node root) {
    this.root = root;
  }

  public Node search(Node root, int key) {

    if (root == null || root.getKey() == key) {
      return root;
    }
    if (root.getKey() < key) {
      return search(root.getRight(), key);
    }

    return search(root.getLeft(), key);
  }

  public Node insert(Node node, int key) {

    if (node == null) {
      return (new Node(key));
    }

    if (key < node.getKey()) {
      node.setLeft(insert(node.getLeft(), key));
    } else if (key > node.getKey()) {
      node.setRight(insert(node.getRight(), key));
    } else {
      return node;
    }

    node.setHeight(1 + max(height(node.getLeft()),
        height(node.getRight())));

    int balance = getBalance(node);

    // Left Left Case
    if (balance > 1 && key < node.getLeft().getKey()) {
      return rightRotate(node);
    }

    // Right Right Case
    if (balance < -1 && key > node.getRight().getKey()) {
      return leftRotate(node);
    }

    // Left Right Case
    if (balance > 1 && key > node.getLeft().getKey()) {
      node.setLeft(leftRotate(node.getLeft()));
      return rightRotate(node);
    }

    // Right Left Case
    if (balance < -1 && key < node.getRight().getKey()) {
      node.setRight(rightRotate(node.getRight()));
      return leftRotate(node);
    }

    return node;
  }


  public Node deleteNode(Node root, int key) {

    if (root == null) {
      return root;
    }

    if (key < root.getKey()) {
      root.setLeft(deleteNode(root.getLeft(), key));
    } else if (key > root.getKey()) {
      root.setRight(deleteNode(root.right, key));
    } else {

      if ((root.getLeft() == null) || (root.getRight() == null)) {
        Node temp = null;
        if (temp == root.getLeft()) {
          temp = root.getRight();
        } else {
          temp = root.getLeft();
        }

        if (temp == null) {
          temp = root;
          root = null;
        } else {
          root = temp;
        }
      } else {

        Node temp = minValueNode(root.getRight());

        root.setKey(temp.getKey());

        root.setRight(deleteNode(root.getRight(), temp.getKey()));
      }
    }

    if (root == null) {
      return root;
    }

    root.setHeight(max(height(root.getLeft()), height(root.getRight())) + 1);

    int balance = getBalance(root);

    // Left Left Case
    if (balance > 1 && getBalance(root.getLeft()) >= 0) {
      return rightRotate(root);
    }

    // Left Right Case
    if (balance > 1 && getBalance(root.getLeft()) < 0) {
      root.setLeft(leftRotate(root.getLeft()));
      return rightRotate(root);
    }

    // Right Right Case
    if (balance < -1 && getBalance(root.getRight()) <= 0) {
      return leftRotate(root);
    }

    // Right Left Case
    if (balance < -1 && getBalance(root.getRight()) > 0) {
      root.setRight(rightRotate(root.getRight()));
      return leftRotate(root);
    }

    return root;
  }

  int height(Node node) {
    if (node == null) {
      return 0;
    }

    return node.getHeight();
  }

  Node rightRotate(Node y) {
    Node x = y.getLeft();
    Node T2 = x.getRight();

    x.setRight(y);
    y.setLeft(T2);

    y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
    x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

    return x;
  }

  Node leftRotate(Node x) {
    Node y = x.getRight();
    Node T2 = y.getLeft();

    y.setLeft(x);
    x.setRight(T2);

    x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
    y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

    return y;
  }

  Node minValueNode(Node node) {
    Node current = node;

    /* loop down to find the leftmost leaf */
    while (current.left != null) {
      current = current.left;
    }

    return current;
  }

  int getBalance(Node node) {
    if (node == null) {
      return 0;
    }

    return height(node.getLeft()) - height(node.getRight());
  }


}
