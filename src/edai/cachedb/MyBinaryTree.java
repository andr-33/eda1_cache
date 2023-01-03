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

    public void insert(T value){
        MyTreeNode<T> newNode = new MyTreeNode<>(value);
        if(root == null) root = newNode;
        else{
            insertNewNode(root, newNode);
        }
    }

    private void insertNewNode(MyTreeNode<T> fatherNode, MyTreeNode<T> sonNode) {
        assert fatherNode != null; //Condicion de salida de la recursividad

        int nodeWay =  fatherNode.getDataNode().compareTo(sonNode.getDataNode());

        if(nodeWay == 0){
            fatherNode.setDataNode(sonNode.getDataNode());
            return;
        }
        else if (nodeWay > 0) {
            if(fatherNode.getLeftNode() == null){
                fatherNode.setLeftNode(sonNode);
            }
            else{
                insertNewNode(fatherNode.getLeftNode(),sonNode);
            }
        }
        else{
            if(fatherNode.getRightNode() == null){
                fatherNode.setRightNode(sonNode);
            }
            else{
                insertNewNode(fatherNode.getRightNode(), sonNode);
            }
        }
    }

    public int size(){
        if(root == null) return 0;
        return countNodes(root);
    }

    private int countNodes(MyTreeNode<T> currentNode) {
        if(currentNode == null) return 0;

        return 1 + countNodes(currentNode.getLeftNode()) + countNodes(currentNode.getRightNode());
    }

    public Object[] listData() {
        Object[] output = new Object[size()];
        fillArrayInOrder(root, output, 0);
        return output;
    }

    private int fillArrayInOrder(MyTreeNode<T> currentNode, Object[] output, int index) {
        if (currentNode == null) return 0;

        final int leftCount = fillArrayInOrder(currentNode.getLeftNode(), output, index);

        final int currentIndex = index + leftCount;
        output[currentIndex] = currentNode.getDataNode();

        final int firstRightIndex = currentIndex + 1;
        final int rightCount = fillArrayInOrder(currentNode.getRightNode(), output, firstRightIndex);

        return 1 + leftCount + rightCount;
    }

    public void remove(T value) {
        MyTreeNode<T> nodeToRemove = search(value);
        if (nodeToRemove == null) return;

        MyTreeNode<T> parent = findParentNode(nodeToRemove);
        final boolean hasLeft = nodeToRemove.getLeftNode() != null;
        final boolean hasRight = nodeToRemove.getRightNode() != null;

        if (hasRight && hasLeft) {
            removeNodeWithTwoChildren(parent, nodeToRemove);
        } else {
            removeNodeWithZeroOrOneChild(parent, nodeToRemove);
        }
    }

    private void removeNodeWithTwoChildren(MyTreeNode<T> parent, MyTreeNode<T> nodeToRemove) {
        MyTreeNode<T> replacement = findMostLeftNode(nodeToRemove.getRightNode());

        removeNodeWithZeroOrOneChild(findParentNode(replacement), replacement);

        replacement.setLeftNode(nodeToRemove.getLeftNode());
        replacement.setRightNode(nodeToRemove.getRightNode());

        if (parent != null) {
            if (parent.getLeftNode() == nodeToRemove) {
                parent.setLeftNode(replacement);
            } else if (parent.getRightNode() == nodeToRemove) {
                parent.setRightNode(replacement);
            }
        } else {
            root = replacement;
        }
    }

    private void removeNodeWithZeroOrOneChild(MyTreeNode<T> fatherNode, MyTreeNode<T> nodeToRemove){
        MyTreeNode<T> replacement = nodeToRemove.getLeftNode() != null ?
                nodeToRemove.getLeftNode() :
                nodeToRemove.getRightNode();

        if (fatherNode != null) {
            if (fatherNode.getLeftNode() == nodeToRemove) {
                fatherNode.setLeftNode(replacement);
            } else if (fatherNode.getRightNode() == nodeToRemove) {
                fatherNode.setRightNode(replacement);
            }
        } else {
            this.root = replacement;
        }
    }

    private MyTreeNode<T> findMostLeftNode(MyTreeNode<T> visitedNode) {
        if (visitedNode.getLeftNode() == null) return visitedNode;

        return findMostLeftNode(visitedNode.getLeftNode());
    }

    private MyTreeNode<T> findParentNode(MyTreeNode<T> nodeToRemove) {
        return findParentFromRoot(root, nodeToRemove);
    }

    private MyTreeNode<T> findParentFromRoot(MyTreeNode<T> fatherNode, MyTreeNode<T> node) {
        if(fatherNode == null) return null;

        if (fatherNode.getRightNode() == node || fatherNode.getLeftNode() == node) {
            return fatherNode;
        }

        int searchWay = node.getDataNode().compareTo(fatherNode.getDataNode());

        if (searchWay<0) {
            return findParentFromRoot(fatherNode.getLeftNode(), node);
        } else {
            return findParentFromRoot(fatherNode.getRightNode(), node);
        }
    }
}
