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

    // public static void createGraph(ArrayList<Edge> graph[]){
    //     for(int i = 0; i < graph.length; i++){
    //         graph[i] = new ArrayList<>();
    //     }

    //     graph[0].add(new Edge(0, 1));
    //     graph[0].add(new Edge(0, 2));
    //     graph[1].add(new Edge(1, 0));
    //     graph[1].add(new Edge(1, 3));
    //     graph[2].add(new Edge(2, 0));

    //     graph[2].add(new Edge(2, 4));
    //     graph[3].add(new Edge(3, 1));
    //     graph[3].add(new Edge(3, 4));
    //     graph[3].add(new Edge(3, 5));
    //     graph[4].add(new Edge(4, 2));
    //     graph[4].add(new Edge(4, 3));
    //     graph[4].add(new Edge(4, 5));
    //     graph[5].add(new Edge(5, 3));
    //     graph[5].add(new Edge(5, 4));
    //     graph[5].add(new Edge(5, 6));
    //     graph[5].add(new Edge(6, 5));
    // }

    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of edges: ");

        int edge = sc.nextInt();

        System.out.println("Enter each edge as 'source destination' (e.g. '0 1' for an edge from vertex 0 to vertex 1):");
        for(int i = 0; i < edge; i++){
            System.out.print("Edge " + (i+1) + ": ");
            int src = sc.nextInt();
            int dest = sc.nextInt();

            if(src < graph.length && dest < graph.length ){
                graph[src].add(new Edge(src, dest));
            }else{
                System.out.println("Invalid vertices. Please enter valid vertex numbers between 0 and " + (graph.length - 1));
                i--; // Decrement to allow re-entering the invalid edge
            }
        }
        sc.close();
    }


 
    public static void BFS(ArrayList<Edge> graph[], boolean visited[], int start){
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> LevelQueue = new LinkedList<>();

        
        LevelQueue.add(0);
        q.add(start);

        int levels[] = new int[graph.length];

      System.out.println("BFS Traversal");
        while(!q.isEmpty()){
            int curr = q.remove();
            int level = LevelQueue.remove();
      
            if(visited[curr] == false){
                System.out.println("Node: " + curr + " | Level: " + level);
                visited[curr] = true;

                for(int i = 0; i < graph[curr].size(); i++){
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);

                    LevelQueue.add(level+1);
                    levels[e.dest] = level+1;
                }

            }
          
        }
    }

    // O(v+e)
    public static void DFS(ArrayList<Edge> graph[], boolean visitedDFS[], int curr, int level){
        System.out.println("Node: " + curr + " | Level: " + level);

        visitedDFS[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(visitedDFS[e.dest] == false){
                DFS(graph, visitedDFS, e.dest, level+1);
            }
        }
    }
    // O(v^v)
    public static void modifiedDFS(ArrayList<Edge> graph[], boolean visited[], int curr, int target, String path){
        if(curr == target){
            System.out.println(path);
            return;
        }

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(visited[e.dest] == false){
                visited[curr] = true;
                modifiedDFS(graph, visited, e.dest, target, path+e.dest);
                visited[curr] = false;
            }
        }
    }

    public static void topologicalSortUtil(ArrayList<Edge> graph[], int curr, boolean visited[], Stack<Integer> stack){
        visited[curr] = true;

        for(int i = 0; i < graph[curr].size(); i++){
            Edge e = graph[curr].get(i);

            if(!visited[e.dest]){
                topologicalSortUtil(graph, e.dest, visited, stack);
            }
        }

        stack.push(curr);
    }

    public static void topologicalSort(ArrayList<Edge> graph[], int v ){
        boolean visited[] = new boolean[v];

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < v; i++){
            if(!visited[i]){
                topologicalSortUtil(graph, i, visited, stack);
            }
        }

        while(!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
    }

    public static void main(String[] args) {

       int v = 7;
       int start = 0;
       int target = 5;
       String path = "";
       ArrayList<Edge> graph[] = new ArrayList[v];

       boolean visited[] = new boolean[v];
       boolean visitedDFS[] =  new boolean[v];
        int level = 0;
       createGraph(graph);

    //    for(int i = 0; i < v; i++){
    //     if(visited[i] == false){
    //         BFS(graph, visited, start);
    //     }
    //    }
       
    //    System.out.println();
    //    for(int i = 0; i < v; i++){
    //     if(visited[i] == false){
    //         DFS(graph, start, visited);
    //     }
    //    }

    DFS(graph, visitedDFS, start, level);

    // modifiedDFS(graph, new boolean[v], start, target, "0");

    // topologicalSort(graph, v);
       
       System.out.println();
        
    }
    
}
