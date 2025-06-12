
package dataStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

public class DataStructuresTests {

    private Queue<Integer> queue;
    private Stack<Integer> stack;

    @BeforeEach
    public void setUp() {
        queue = new Queue<>();
        stack = new Stack<>();
    }

    @Test
    public void testQueueAdd() {
        queue.add(10);
        assertEquals(10, queue.getFirstNode().data);
    }

    @Test
    public void testQueueMultipleElements() {
        queue.add(1);
        queue.add(2);
        assertEquals(1, queue.getFirstNode().data);
    }

    @Test
    public void testStackAddAndIterator() {
        stack.add(5);
        stack.add(10);
        Iterator<Integer> it = stack.iterator();
        assertEquals(10, it.next());  // LIFO
        assertEquals(5, it.next());
    }

    @Test
    public void testStackIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.add(1);
        assertFalse(stack.isEmpty());
    }
}
