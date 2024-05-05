import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(int src, int dest) {
        adjacencyList.putIfAbsent(src, new ArrayList<>());
        adjacencyList.putIfAbsent(dest, new ArrayList<>());
        adjacencyList.get(src).add(dest);
        adjacencyList.get(dest).add(src);
    }

    private void dfsHelper(int vertex, Set<Integer> visited, int level) {
        visited.add(vertex);
        System.out.println("DFS visited node: " + vertex + " at level: " + level);

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited, level + 1);
            }
        }
    }

    public void dfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        dfsHelper(startVertex, visited, 0);
    }

    public void bfs(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> levels = new HashMap<>();

        queue.add(startVertex);
        visited.add(startVertex);
        levels.put(startVertex, 0);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            int currentLevel = levels.get(currentVertex);
            System.out.println("BFS visited node: " + currentVertex + " at level: " + currentLevel);

            for (int neighbor : adjacencyList.get(currentVertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    levels.put(neighbor, currentLevel + 1);
                }
            }
        }
    }
}

public class DFS_BFS {
    public static void main(String[] args) {
        Graph graph = new Graph();
        
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);
        
        System.out.println("Depth First Search (DFS):");
        graph.dfs(1); // Start DFS from node 1

        System.out.println("\nBreadth First Search (BFS):");
        graph.bfs(1); // Start BFS from node 1
    }
}
