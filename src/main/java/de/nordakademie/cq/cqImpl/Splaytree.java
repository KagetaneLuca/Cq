package de.nordakademie.cq.cqImpl;


import de.nordakademie.model.event.impl.Event;
/*
   time mit timestamp eins zu eins ersetzen
   sortiert timestamp values zusammen addieren und das als time ersatz verwenden
       YYYY MM/10 DD/100 HH/1000 MM/10000
    */
class Node {
    Event key;
    Node left;
    Node right;
    Node parent;
    protected Node(double time, String event) {
        key = new Event(time, event);
        left = null;
        right = null;
        parent = null;
    }

public static class Splaytree {
    Node root;
    public Splaytree(){
     root = null;
 }

    public void deleteEvent(Event key){
        deleteKey(this.root, key);
    }

        private void deleteKey(Node node, Event key) {
            Node rightChild = null;
            Node leftChild = null;
            while (node != null && node.key.getTimestamp() != key.getTimestamp()){
                if (node.key.getTimestamp() < key.getTimestamp()) { // warum wird das gemacht? wirkt wie der fehler
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
            if (null != node) {
                splay(node);
                if (node.left == null)
                {
                    // When no left side subtree of root node
                    this.root = node.right;
                }
                else if (node.right == null)
                {
                    // When no right side subtree of root node
                    this.root = node.left;
                }
                else{
                    node.left.parent = null;
                    node.right.parent = null;
                    rightChild = node.right;
                    leftChild = node.left;
                    this.root = null;
                    while (node.right != null)
                    {
                        node = node.right;
                    }
                    splay(node);
                    node.right = rightChild;
                    rightChild.parent = node;
                    this.root = node;
                }

            }
            if (this.root != null)
            {
                this.root.parent = null;
            }
        }


    private Node join(Node s, Node t){
        if (s == null) {
            return t;
        }

        if (t == null) {
            return s;
        }
        Node x = maximum(s);
        splay(x);
        x.right = t;
        t.parent = x;
        return x;
    }
    public Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

        protected void insert(Event event) {
        Node node = new Node(event.getTimestamp(), event.getEventDescription());
            Node y = null;
            Node x = this.root;

            while (x != null) {
                y = x;
                if (node.key.getTimestamp() < x.key.getTimestamp()) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
            // y is parent of x
            node.parent = y;
            if (y == null) {
                root = node;
            } else if (node.key.getTimestamp() < y.key.getTimestamp()) {
                y.left = node;
            } else {
                y.right = node;
            }
            // splay node
            splay(node);
        }

        public Node search(Node node, Event key) {
            if (null == node || node.key.getTimestamp() == key.getTimestamp()) {
                return node;
            }
            if (node.key.getTimestamp() > key.getTimestamp()) {
                return search(node.left, key);
            }
            return search(node.right, key);
        }

        public int size(Node node) {
            if ( node == null)
                return 0;
            else
                return (size(node.left) + 1 + size(node.right));
        }

        public Node minimum() {
        Node node = this.root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
        }

        private void leftRotate(Node node) {
            Node rotate = node.right;
            node.right = rotate.left;
            if (rotate.left != null) {
                rotate.left.parent = node;
            }
            rotate.parent = node.parent;
            if (null == node.parent) {
                this.root = rotate;
            } else if (node == node.parent.left) {
                node.parent.left = rotate;
            } else {
                node.parent.right = rotate;
            }
            rotate.left = node;
            node.parent = rotate;
        }

        private void rightRotate(Node node) {
            Node rotate = node.left;
            node.left = rotate.right;
            if (rotate.right != null) {
                rotate.right.parent = node;
            }
            rotate.parent = node.parent;
            if (node.parent == null) {
                this.root = rotate;
            } else if (node == node.parent.right) {
                node.parent.right = rotate;
            } else {
                node.parent.left = rotate;
            }
            rotate.right = node;
            node.parent = rotate;
        }

        private void splay(Node node) {
            while (null != node.parent) {
                if (null == node.parent.parent) {
                    if (node == node.parent.left) {
                        // zig rotation
                        rightRotate(node.parent);
                    } else {
                        // zag rotation
                        leftRotate(node.parent);
                    }
                } else if (node == node.parent.left && node.parent == node.parent.parent.left) {
                    // zig-zig rotation
                    rightRotate(node.parent.parent);
                    rightRotate(node.parent);
                } else if (node == node.parent.right && node.parent == node.parent.parent.right) {
                    // zag-zag rotation
                    leftRotate(node.parent.parent);
                    leftRotate(node.parent);
                } else if (node == node.parent.right && node.parent == node.parent.parent.left) {
                    // zig-zag rotation
                    leftRotate(node.parent);
                    rightRotate(node.parent);
                } else {
                    // zag-zig rotation
                    rightRotate(node.parent);
                    leftRotate(node.parent);
                }
            }
        }
    }
}
