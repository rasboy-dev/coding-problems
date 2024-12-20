import kotlin.Pair;

import java.io.*;
import java.util.*;

public class CheapestPath {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/cheapest_path"));
        int n = Integer.parseInt(reader.readLine());
        Graph graph = new Graph();
        while (true) {
            String line = reader.readLine();
            System.out.println(line);
            if (line == null) {
                break;
            }
            Integer[] edge = Arrays.stream(line.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            graph.addEdge(edge[0], edge[1], edge[2]);
        }

        Map<Integer, Integer> dists = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            dists.put(i, Integer.MAX_VALUE);
        }
        dists.put(1, 0);
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparing(Pair<Integer, Integer>::getSecond));
        pq.add(new Pair<>(1, 0));

        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> next = pq.poll();
            if (visited.contains(next.getFirst()))
                continue;
            visited.add(next.getFirst());
            for (Pair<Integer, Integer> neighbor : graph.getNeighbors(next.getFirst())) {
                if (dists.get(neighbor.getFirst()) > dists.get(next.getFirst()) + neighbor.getSecond()) {
                    dists.put(neighbor.getFirst(), dists.get(next.getFirst()) + neighbor.getSecond());
                    pq.add(new Pair<>(neighbor.getFirst(), dists.get(next.getFirst()) + neighbor.getSecond()));
                }
            }
        }
        System.out.println(dists.get(5));
    }

    private static class Graph {
         Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();

         public void addEdge(int from, int to, int dist) {
             if (!graph.containsKey(from)) {
                 graph.put(from, new ArrayList<>());
             }
             graph.get(from).add(new Pair<>(to, dist));
           }

        public List<Pair<Integer, Integer>> getNeighbors(int v) {
             if (!graph.containsKey(v)) {
                 return List.of();
             }
             return graph.get(v);
        }
    }
}
