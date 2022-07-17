//Program to Implement the BinarySearchTree.


import java.util.*;

public class BinarySearchTree {
    int size;  //number of elements in the Tree.
    Nodes root; //root of the tree.

    public BinarySearchTree() {
        this.size = 0;
        this.root = null;
    }

    //function to find specific data in the Tree. If found the return that node otherwise return the last reached node.
    public Nodes find(int data) {
        Nodes temp = root;
        Nodes prev = null;
        if (temp == null) {
            return temp;
        }

        while (temp != null) {
            if (temp.key == data) {
                return temp;
            }

            if (data > temp.key) {
                prev = temp;
                temp = temp.right;
                continue;
            }
            prev = temp;
            temp = temp.left;
        }
        return prev;
    }

    //function add elements in the Tree.
    public void insert(int data) {
        System.out.println("Inserting data : " + data);
        Nodes node = new Nodes(data);
        Nodes parent = find(data);
        if (parent == null) {
            root = node;
            return;
        }
        node.parent = parent;
        node.height = node.parent.height + 1;
        size++;

        if (data > parent.key) {
            parent.right = node;
        } else {
            parent.left = node;
        }
    }

    //finds just the next big node than the given node.Works correctlly only when the given node is actually in the tree.
    //if the given key is largest then it returns the same node;
    public Nodes findNext(int data) {
        Nodes temp = find(data);
        if (temp.key != data) {
            System.out.println(data + " not present in the tree.");
            return null;
        }
        Nodes right = temp.right;
        Nodes parent = temp.parent;

        if (right != null) {
            while (right.left != null) {
                right = right.left;
            }
            return right;
        }

        while (parent != null) {
            if (parent.key > temp.key) {
                return parent;
            }
            parent = parent.parent;
        }
        return temp;
    }


    //return the numbers between data1 and data2, including data1 and data2 if they are present in the tree.
    public List<Nodes> rangeSearch(int data1, int data2) {
        System.out.println("Range Search between " + data1 + " and " + data2);
        List<Nodes> list = new ArrayList<Nodes>();
        Nodes node1 = find(data1);
        if (node1 == null) {
            return list;
        }

        while (node1.key <= data2) {
            if (node1.key >= data1) {
                list.add(node1);
            }
            Nodes temp = findNext(node1.key);
            if (temp == node1) {
                break;
            }
            node1 = temp;
        }
        return list;
    }

    //function to remove elements from the tree.
    public void delete(int data) {
        System.out.println("Remove  : " + data);
        Nodes temp = find(data);
        if (temp.key != data) {
            System.out.println(data + " is not present in the tree.");
            return;
        }
        if (temp.right == null) {
            if (temp.parent == null) {
                root = temp.left;
                if (root != null) {
                    root.parent = null;
                }
                temp = null;
                return;
            }
            if (temp.left != null) {
                temp.left.parent = temp.parent;
            }
            if (temp.parent.right == temp) {
                temp.parent.right = temp.left;
                temp = null;
                return;
            } else {
                temp.parent.left = temp.left;
                temp = null;
                return;
            }
        }

        Nodes nextTemp = findNext(temp.key);
        if (nextTemp.right != null) {
            nextTemp.right.parent = nextTemp.parent;
        }
        if (nextTemp.parent == null) {
            root = nextTemp.right;
        }
        if (nextTemp.parent != null && nextTemp.parent.left == nextTemp) {
            nextTemp.parent.left = nextTemp.right;
        }
        if (nextTemp.parent != null && nextTemp.parent.right == nextTemp) {
            nextTemp.parent.right = nextTemp.right;
        }


        if (temp.left != null) {
            temp.left.parent = nextTemp;
        }
        if (temp.right != null) {
            temp.right.parent = nextTemp;
        }
        nextTemp.left = temp.left;
        nextTemp.right = temp.right;
        nextTemp.parent = temp.parent;


        if (temp.parent == null) {
            root = nextTemp;
            temp = null;
            return;
        }
        if (temp.parent.right == temp) {
            temp.parent.right = nextTemp;
            temp = null;
            return;
        }
        temp.parent.left = nextTemp;
        temp = null;
    }

}
