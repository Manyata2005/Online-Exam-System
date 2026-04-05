import java.util.*;

public class Dijkstra {

    static final int INF = 9999;

    static int minDistance(int dist[], boolean visited[], int n) {
        int min = INF;
        int index = -1;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        }
        return index;
    }

    static void dijkstra(int graph[][], int src, int n) {

        int dist[] = new int[n];
        boolean visited[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            visited[i] = false;
        }

        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {

            int u = minDistance(dist, visited, n);
            visited[u] = true;

            for (int v = 0; v < n; v++) {

                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] != INF &&
                    dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("Shortest Distance from Source:");

        for (int i = 0; i < n; i++) {
            System.out.println("Node " + i + " : " + dist[i]);
        }
    }

    public static void main(String args[]) {

        int graph[][] = {
            {0, 2, 4, 0},
            {2, 0, 1, 7},
            {4, 1, 0, 3},
            {0, 7, 3, 0}
        };

        int n = 4;

        dijkstra(graph, 0, n);
    }
}