//Program for Node of the BinarySearchTree.
public class Nodes {
    int key;     //data value.
    Nodes parent; //points to the parent of the node.
    Nodes left;   //points to the left child of the node.
    Nodes right;  //points to the right child of the node.
    int height;  //height of the node.

    public Nodes(int data){
        this.key=data;
        this.parent=null;
        this.left=null;
        this.right=null;
        this.height=0;
    }
}
