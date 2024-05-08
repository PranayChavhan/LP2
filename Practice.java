import java.util.*;

public class Practice{

    public static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int src, int dest, int wt){
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
        
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

    
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter no of edges: ");
        int edges = sc.nextInt();

        System.out.println("0 1");
        for(int i = 0; i < edges; i++){
            System.out.print("Edge "+(i+1)+": ");
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int wt = sc.nextInt();

            if(src < graph.length && dest < graph.length){
                graph[src].add(new Edge(src, dest, wt));

            }else{
                System.out.println("Invalid");
            }
        }
        sc.close();
    }



    static class Pair implements Comparable<Pair>{
        int v;
        int wt;
        public Pair(int v, int wt){
            this.v = v;
            this.wt = wt;
        }

        @Override
        public int compareTo(Pair p2){
            return this.wt - p2.wt;
        }
    }


    public static void primAlgo(ArrayList<Edge> graph[]){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[graph.length];

        pq.add(new Pair(0, 0));
        int cost = 0;

        while(!pq.isEmpty()){
            Pair p = 
        }
    }
    public static void main(String[] args) {
        int v = 4;

        ArrayList<Edge> graph[] = new ArrayList[v];

        createGraph(graph);
        primAlgo(graph);
    }
}