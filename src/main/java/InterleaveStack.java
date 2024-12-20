import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class InterleaveStack {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> queue = new ArrayDeque<>();

        List<Integer> stackData = List.of(1, 2, 3, 4, 5, 6);
        int n = stackData.size();

        stackData.forEach(stack::addFirst);

        // [5, 4, 3, 2, 1] -> [1, 5, 2, 4, 3]

        // [5, 4, 3, 2, 1] -> (1, 2, 3, 4, 5)
        for (int i = 0; i < n; i++) {
            queue.addFirst(stack.removeFirst());
        }
        // (1, 2, 3, 4, 5) -> (4, 5, 1, 2, 3)
        for (int i = 0; i < n / 2; i++) {
            queue.addFirst(queue.removeLast());
        }

        // (4, 5, 1, 2, 3) -> (4, 5) [1, 2, 3]
        for (int i = 0; i < (n % 2 == 0 ? n / 2 : n / 2 + 1); i++) {
            stack.addFirst(queue.removeLast());
        }

        // (4, 5) [1, 2, 3] -> (3, 4, 2, 5, 1)
        for (int i = 0; i < n / 2; i++) {
            queue.addFirst(stack.removeFirst());
            queue.addFirst(queue.removeLast());
        }
        if (!stack.isEmpty()) {
            queue.addFirst(stack.removeFirst());
        }

//        (3, 4, 2, 5, 1) -> [1, 5, 2, 4, 3]
        while (!queue.isEmpty()) {
            stack.addFirst(queue.removeLast());
        }

        System.out.println(stack);
    }
}
