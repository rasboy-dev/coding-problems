import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LRUCache {
    int capacity;
    int size = 0;
    Map<Integer, Node> cache = new HashMap<>();
    PriorityQueue<Node> pQueue = new PriorityQueue<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            updateLastUsed(node);
            return cache.get(key).getValue();
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            updateLastUsed(node);
            node.setValue(value);
        } else {
            Node node = new Node(key, value);
            if (size == capacity) {
                Node lruNode = pQueue.peek();
                pQueue.remove(lruNode);
                cache.remove(node.key);
            }
            cache.put(key, node);
            pQueue.add(node);
        }
    }

    private void updateLastUsed(Node node) {
        pQueue.remove(node);
        node.setLastUsed(System.currentTimeMillis());
        pQueue.add(node);
    }

    private static class Node implements Comparable<Node> {
        int key;
        int value;
        long lastUsed;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.lastUsed = System.currentTimeMillis();
        }

        void setValue(int value) {
            this.value = value;
        }

        int getValue() {
            return this.value;
        }

        void setLastUsed(long millsUsed) {
            this.lastUsed = millsUsed;
        }

        long getLastUsed() {
            return this.lastUsed;
        }

        @Override
        public int compareTo(@NotNull Node o) {
            return Long.compare(lastUsed, o.lastUsed);
        }
    }
}