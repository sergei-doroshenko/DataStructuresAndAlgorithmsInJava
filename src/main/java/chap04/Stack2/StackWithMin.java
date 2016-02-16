package chap04.Stack2;

import java.util.Arrays;

/**
 * Created by sergei on 2/16/16.
 */
public class StackWithMin {
    private int[] main = new int[10];
    private int[] red = new int[10];
    private int top;

    public void push(int i) {
        if (top > main.length) {
            throw new IllegalStateException("No empty space in stack.");
        }

        main[top] = i;
        red[top] = calculateMin();
        top++;
    }

    public int pop() {
        if (top == 0) {
            throw new IllegalStateException("No elements in stack.");
        }

        int result = main[top-1];
        main[top-1] = 0;
        red[top-1] = 0;
        top--;

        return result;
    }

    public int min() {
        return top == 0 ? main[top] : red[top-1];
    }

    private int calculateMin() {
        if (top == 0) {
            return main[top];
        }

        return main[top] < main[top-1] ? main[top] : red[top-1];
    }

    @Override
    public String toString() {
        return "main: " + Arrays.toString(main) + "\n red: " + Arrays.toString(red);
    }
}
