package edai.cachedb;

public class MyBinaryTree<T extends Comparable<T>>{
    private MyTreeNode<T> root;

    public boolean isEmpty(){
        return root == null;
    }
    public MyTreeNode<T> getRoot(){
        return this.root;
    }
    public MyTreeNode<T> search(T value){
        if(this.root == null) return null;
        return searchInTree(root, value);
    }

    private MyTreeNode<T> searchInTree(MyTreeNode<T> currentNode, T value) {
        if(currentNode == null) return null;

        int valueWay = value.compareTo(currentNode.getDataNode());

        if(valueWay == 0){
            return currentNode;
        }
        else if(valueWay < 0){
            return searchInTree(currentNode.getLeftNode(), value);
        }
        else{
            return searchInTree(currentNode.getRightNode(), value);
        }

    }
}
