package de.nordakademie.cq;

import de.nordakademie.safe.Event;

public class BinarySearchTreeCQ {


    public static class Tree{
        Event key;
        private Tree left;
        private Tree right;

        protected Tree(double time, String event){
            key = new Event(time, event);
            left = null;
            right = null;
        }
        Tree root;

        public Tree(){

            root = null;
        }
        protected Tree deleteKey(Event key){

            return root = deleteRecursive(root, key.getTimestamp());
        }
        private Tree deleteRecursive(Tree root, double timestamp){
            if(null == root){
                return root;
            }
            if(timestamp < root.key.getTimestamp()){
                root.left = deleteRecursive(root.left, timestamp);
            } else if (timestamp > root.key.getTimestamp()) {
                root.right = deleteRecursive(root.right, timestamp);
            } else {
                if(null == root.left){
                    return root.right;
                } else if (null == root.right) {
                    return  root.left;
                }
//                root.key = minValue(root.right);
                root.right = deleteRecursive(root.right, root.key.getTimestamp());
            }
            return root;
        }
        protected double minValue(Event key){
            double minval = key.getTimestamp(); // ??
            while(null != root.left){
                minval = root.left.key.getTimestamp();
                root = root.left;
            }
            return minval;
        }
        protected void insert(Event key){

            root = insertRecursive(root, key);
        }
        private Tree insertRecursive(Tree root, Event key){
            if(null == root){
                root = new Tree(key.getTimestamp(), key.getEventDescription());
                return root;
            }
            if(key.getTimestamp() < root.key.getTimestamp()){
                root.left = insertRecursive(root.left, key);
            } else if (key.getTimestamp() > root.key.getTimestamp()) {
                root.right = insertRecursive(root.right, key);
            }
            return root;
        }
        protected void inorder(){
            inorderRecursive(root);
        }
        private void inorderRecursive(Tree root){
            if (null != root){
                inorderRecursive(root.left);
                inorderRecursive(root.right);
            }
        }
        protected boolean search(Event key){
            root = searchRecursive(root, key);
            if (null != root) {
                return true;
            } else {
                return false;
            }
        }
        private Tree searchRecursive(Tree root, Event key){
            if(null == root || root.key.getTimestamp() == key.getTimestamp()){
                return root;
            }
            if(root.key.getTimestamp() > key.getTimestamp()){
                return searchRecursive(root.left, key);
            }
            return searchRecursive(root.right, key);
        }
        public  int size(Tree node)
        {
            if (node == null)
                return 0;
            else
                return(size(node.left) + 1 + size(node.right));
        }
        public double smallestElement(Tree temp) {
            //Check whether tree is empty
            if (root == null) {
                return 0;
            } else {
                double leftMin, rightMin;
                //Min will store temp's data
                double min = temp.key.getTimestamp();

                //It will find smallest element in left subtree
                if (temp.left != null) {
                    leftMin = smallestElement(temp.left);
                    //If min is greater than leftMin then store the value of leftMin into min
                    min = Math.min(min, leftMin);
                }

                //It will find smallest element in right subtree
                if (temp.right != null) {
                    rightMin = smallestElement(temp.right);
                    //If min is greater than rightMin then store the value of rightMin into min
                    min = Math.min(min, rightMin);
                }
                return min;
            }
        }
    }
}
