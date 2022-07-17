import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    BinarySearchTree tree = new BinarySearchTree();

    {
        tree.insert(10);
        tree.insert(20);
        tree.insert(8);
        tree.insert(22);
        tree.insert(9);
    }

    @Test
    public void shouldFindData() {
        Assertions.assertEquals(8, tree.find(8).key);
    }

    @Test
    public void shouldInsertData() {
        tree.insert(28);
        Assertions.assertEquals(28, tree.find(28).key);
    }

    @Test
    public void shouldFindNext() {
        Assertions.assertEquals(22, tree.findNext(20)
                .key);
    }

    @Test
    public void shouldRangeSearch() {
        List<Nodes> list;
        Nodes start = tree.find(9);
        int end = 23;
        list = tree.rangeSearch(start.key, end);

        for (Nodes node : list
        ) {
            assertEquals(node.key, tree.find(node.key)
                    .key);
            System.out.println("Find: " + node.key);
        }

    }

    @Test
    public void shouldDelete() {
        Nodes temp = tree.find(22);
        tree.delete(temp.key);
        Assertions.assertNotEquals(temp.key,
                tree.find(temp.key).key);
        for (Nodes node : tree.rangeSearch(0, 50)
        ) {
            System.out.println("Tree have " + node.key);
        }
    }
}