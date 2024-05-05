import java.util.*;

public class P1 {
    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 0));

        graph[2].add(new Edge(2, 4));
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));
        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));
        graph[5].add(new Edge(6, 5));
    }

    public static void BFS(ArrayList<Edge> graph[], boolean visited[]){
        Queue<Integer> q = new LinkedList<>();
        

        q.add(0);

        while(!q.isEmpty()){
            int curr = q.remove();
            if(visited[curr] == false) {
                System.out.print(curr+" ");
                visited[curr] = true;

                for(int i = 0; i < graph[curr].size(); i++){
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
    }

    public static void DFS(ArrayList<Edge> graph[], int curr, boolean visited[]){
        System.out.print(curr+" ");

        visited[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(visited[e.dest] == false){
                DFS(graph, e.dest, visited);
            }
        }
    }

    public static void main(String[] args) {

       int v = 7;
       ArrayList<Edge> graph[] = new ArrayList[v];

       boolean visited[] = new boolean[v];
       createGraph(graph);

       BFS(graph, visited);
       System.out.println();
    //    DFS(graph, 0, visited);
       System.out.println();
        
    }
    
}
