package edai.cachedb;
public class MyTreeNode<T> {
    private T dataNode;
    private MyTreeNode<T> leftNode;
    private MyTreeNode<T> rightNode;

    public MyTreeNode(T data){
        this.dataNode = data;
    }
    public T getDataNode(){
        return this.dataNode;
    }

    public void setDataNode(T data){
        this.dataNode = data;
    }

    public void setLeftNode(MyTreeNode<T> lef){
        this.leftNode = lef;
    }

    public MyTreeNode<T> getLeftNode(){
        return this.leftNode;
    }

    public void setRightNode(MyTreeNode<T> right){
        this.rightNode = right;
    }

    public MyTreeNode<T> getRightNode(){
        return this.rightNode;
    }

}
