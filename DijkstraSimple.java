import java.util.*;
public class DijkstraSimple {
 // Method to run Dijkstra’s algorithm
 public static void dijkstra(int[][] graph, int source) {
 int n = graph.length;
 // dist[i] will hold the shortest distance from source to i
 int[] dist = new int[n];
 // visited[i] will be true when the shortest distance to i is finalized
 boolean[] visited = new boolean[n];
 // Initialize all distances to infinity (a big number)
 for (int i = 0; i < n; i++) {
 dist[i] = Integer.MAX_VALUE;
 visited[i] = false;
 }
 // Distance from source to itself is 0
 dist[source] = 0;
 // Find shortest path for all vertices
 for (int count = 0; count < n - 1; count++) {
 // Pick the smallest distance vertex from unvisited set
 int u = minDistance(dist, visited);
 // Mark this vertex as visited
 visited[u] = true;
 // Update distance value of the neighbors of the picked vertex
 for (int v = 0; v < n; v++) {
 // Update only if there is an edge, 
 // and the new distance is smaller
 if (!visited[v] && graph[u][v] != 0 &&
 dist[u] != Integer.MAX_VALUE &&
 dist[u] + graph[u][v] < dist[v]) {
 dist[v] = dist[u] + graph[u][v];
 }
 }
 }
 // Print the result
 System.out.println("Vertex\tDistance from Source");
 for (int i = 0; i < n; i++) {
 System.out.println(i + "\t\t" + dist[i]);
 }
 }
 // Helper method to find the vertex with minimum distance
 private static int minDistance(int[] dist, boolean[] visited) {
 int min = Integer.MAX_VALUE;
 int minIndex = -1;
 for (int i = 0; i < dist.length; i++) {
 if (!visited[i] && dist[i] <= min) {
 min = dist[i];
 minIndex = i;
 }
 }
 return minIndex;
 }
 public static void main(String[] args) {
 // Example graph represented as a matrix
 // 0 means no direct edge
 int[][] graph = {
 { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
 { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
 { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
 { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
 { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
 { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
 { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
 { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
 { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
 };
 // Run Dijkstra from source vertex 0
 dijkstra(graph, 0);
 }
}