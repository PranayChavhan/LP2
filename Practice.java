import java.util.*;

public class Practice{
    public static void main(String[] args) {
        
        int[][] maze = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };

        if(AStarMazeSolver.solveMaze(maze, 1, 2, 4, 4)){
            AStarMazeSolver.printMaze(maze);
        }else{
            System.out.println("Path not found");
        }
    }
}

class Node implements Comparable<Node>{
    int x, y, g, h, f;
    Node parent;

    Node(int x, int y, Node parent, int g, int h){
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.g = g;
        this.h = h;
        this.f = g + f;
    }

    @Override

    public int compareTo(Node n2){
        return this.f - n2.f;
    }
}
class AStarMazeSolver {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int manhattanDist(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    public static void markPath(Node node, int[][] maze){
        Node current = node;
    
        while( current != null){
            maze[current.x][current.y] = 2;
            current = current.parent;
        }
    }
    
    public static void printMaze(int[][] maze){
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; i++){
                System.out.println(maze[i][j] == 2 ? "* " : maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean solveMaze(int[][] maze, int startX, int startY, int endX, int endY){
        int n = maze.length;
        int m = maze[0].length;

        PriorityQueue<Node> openList = new PriorityQueue<>();

        boolean[][] closeList = new boolean[n][m];

        openList.add(new Node(startX, startY, null, 0, manhattanDist(startX, startY, endX, endY)));

        while(!openList.isEmpty()){
            Node current = openList.remove();

            if(current.x == endX && current.y == endY){
                markPath(current, maze);
                return true;
            }

            closeList[current.x][current.y] = true;

            for(int i = 0; i < 4; i++){
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && maze[nextX][nextY] == 0 && !closeList[nextX][nextY]){
                    int g = current.g + 1;
                    int h = manhattanDist(nextX, nextY, endX, endY);
                    openList.add(new Node(nextX, nextY, current, g, h));
                }
            }
        }
        return false;
    }
}