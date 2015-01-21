package chap04;

import chap04.Stack.StackXInt;
import org.junit.Assert;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Created by user on 21.01.2015.
 */
@RunWith(Theories.class)
public class DequeTestWithDataPoint extends Assert {

    @DataPoint
    public static Object[] data = new Object[] {4, 3, 2, 1};

    @Theory
    public void deque1(final Object...testData) {
        StackXInt stack = new StackXInt(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        // stack must be: 4 - 3 - 2 - 1
        assertEquals(testData[0], stack.pop());
        assertEquals(testData[1], stack.pop());
        assertEquals(testData[2], stack.pop());
        assertEquals(testData[3], stack.pop());

    }
}
