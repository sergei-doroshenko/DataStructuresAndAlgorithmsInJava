package chap04;

import chap04.Deque.DequeArr;
import chap04.Stack.StackXInt;
import junit.framework.TestCase;

/**
 * Created by user on 20.01.2015.
 */
public class DequeTest extends TestCase {

    public void testA() {
        DequeArr deque = new DequeArr(3);
        deque.insertRight(1);
        deque.insertRight(2);

        assertEquals(1, deque.removeLeft());
        assertEquals(2, deque.removeLeft());

    }

    public void testStack() {
        StackXInt stack = new StackXInt(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        // stack must be: 4 - 3 - 2 - 1
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }


}
