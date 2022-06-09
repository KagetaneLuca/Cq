package de.nordakademie.cq.cqImpl;


import de.nordakademie.model.event.impl.Event;

public class Splaytree {

    /*
    time mit timestamp eins zu eins ersetzen
    sortiert timestamp values zusammen addieren und das als time ersatz verwenden
        YYYY MM/10 DD/100 HH/1000 MM/10000
     */
    public static class Tree {
        Event key;
        private Tree left;
        private Tree right;

        private Tree parent;

        protected Tree(double time, String event) {
            key = new Event(time, event);
            left = null;
            right = null;
            parent = null;
        }

        Tree root;

        public Tree() {

            root = null;
        }

        protected Tree deleteKey(Event key) {

            return root = deleteRecursive(root, key.getTimestamp());
        }

        private Tree deleteRecursive(Tree root, double timestamp) {
            if (null == root) {
                return root;
            }
            if (timestamp < root.key.getTimestamp()) {
                root.left = deleteRecursive(root.left, timestamp);
            } else if (timestamp > root.key.getTimestamp()) {
                root.right = deleteRecursive(root.right, timestamp);
            } else {
                if (null == root.left) {
                    return root.right;
                } else if (null == root.right) {
                    return root.left;
                }
//                root.key = minValue(root.right);
                root.right = deleteRecursive(root.right, root.key.getTimestamp());
            }
            return root;
        }

        protected void insert(Event key) {
            root = insertRecursive(root, key);

        }

        private Tree insertRecursive(Tree root, Event key) {
            if (null == root) {
                root = new Tree(key.getTimestamp(), key.getEventDescription());
                return root;
            }
            if (key.getTimestamp() < root.key.getTimestamp()) {
                root.left = insertRecursive(root.left, key);
            } else if (key.getTimestamp() > root.key.getTimestamp()) {
                root.right = insertRecursive(root.right, key);
            }
            return root;
        }
        protected boolean search(Event key) {
            root = searchRecursive(root, key);
            if (null != root) {
                splay(root);
                return true;
            } else {
                return false;
            }
        }

        private Tree searchRecursive(Tree root, Event key) {
            if (null == root || root.key.getTimestamp() == key.getTimestamp()) {
                return root;
            }
            if (root.key.getTimestamp() > key.getTimestamp()) {
                return searchRecursive(root.left, key);
            }
            return searchRecursive(root.right, key);
        }

        public int size(Tree node) {
            if (node == null)
                return 0;
            else
                return (size(node.left) + 1 + size(node.right));
        }

        public Event smallestElement(Tree temp) {
            if (temp.left == null) {
                return temp.root.key;
            }
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp.key;
        }

        public Event maxElement(Tree temp) {
            while (temp.right != null) {
                temp = temp.right;
            }
            return temp.key;
        }

        private void leftRotate(Tree tree) {
            Tree rotate = tree.right;
            tree.right = rotate.left;
            if (rotate.left != null) {
                rotate.left.parent = tree;
            }
            rotate.parent = tree.parent;
            if (tree.parent == null) {
                this.root = rotate;
            } else if (tree == tree.parent.left) {
                tree.parent.left = rotate;
            } else {
                tree.parent.right = rotate;
            }
            rotate.left = tree;
            tree.parent = rotate;
        }

        private void rightRotate(Tree tree) {
            Tree rotate = tree.left;
            tree.left = rotate.right;
            if (rotate.right != null) {
                rotate.parent = tree;
            }
            rotate.parent = tree.parent;
            if (tree.parent == null) {
                this.root = rotate;
            } else if (tree == tree.parent.right) {
                tree.parent.right = rotate;
            } else {
                tree.parent.left = rotate;
            }
            rotate.right = tree;
            tree.parent = rotate;
        }

        private void splay(Tree tree) {
            while (tree.parent != null) {
                if (tree.parent.parent == null) {
                    if (tree == tree.parent.left) {
                        // zig rotation
                        rightRotate(tree.parent);
                    } else {
                        // zag rotation
                        leftRotate(tree.parent);
                    }
                } else if (tree == tree.parent.left && tree.parent == tree.parent.parent.left) {
                    // zig-zig rotation
                    rightRotate(tree.parent.parent);
                    rightRotate(tree.parent);
                } else if (tree == tree.parent.right && tree.parent == tree.parent.parent.right) {
                    // zag-zag rotation
                    leftRotate(tree.parent.parent);
                    leftRotate(tree.parent);
                } else if (tree == tree.parent.right && tree.parent == tree.parent.parent.left) {
                    // zig-zag rotation
                    leftRotate(tree.parent);
                    rightRotate(tree.parent);
                } else {
                    // zag-zig rotation
                    rightRotate(tree.parent);
                    leftRotate(tree.parent);
                }
            }
        }
    }
}
