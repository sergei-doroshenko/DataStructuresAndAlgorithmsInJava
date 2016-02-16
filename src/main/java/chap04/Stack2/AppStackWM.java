package chap04.Stack2;

/**
 * Created by sergei on 2/16/16.
 */
public class AppStackWM {
    public static void main(String[] args) {
        StackWithMin stack = new StackWithMin();
        stack.push(20);
        stack.push(10);
        stack.push(30);

        pritnInfo(stack);
        stack.push(40);

        pritnInfo(stack);

        stack.push(5);
        pritnInfo(stack);

        System.out.println("pop: " + stack.pop());
        pritnInfo(stack);


        System.out.println("pop: " + stack.pop());
        System.out.println("pop: " + stack.pop());

        pritnInfo(stack);

        System.out.println("pop: " + stack.pop());
        System.out.println("pop: " + stack.pop());

        pritnInfo(stack);
    }

    public static void pritnInfo(StackWithMin stack) {
        System.out.println(stack);
        System.out.println("min: " + stack.min());
    }
}
