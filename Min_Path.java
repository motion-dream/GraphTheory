import java.util.Arrays;
/**
 * Author: MirrorML
 * Date: November 21, 2023
 */
class DijkstraAlgorithm {
    private static final int INF = Integer.MAX_VALUE; // 代表无穷大

    public static void dijkstra(int[][] graph, int start) {
        int vertices = graph.length;
        int[] distance = new int[vertices];
        boolean[] visited = new boolean[vertices];

        // 初始化距离数组，将起始点到各点的距离设置为无穷大
        Arrays.fill(distance, INF);

        // 设置起始点到自身的距离为0
        distance[start] = 0;

        for (int count = 0; count < vertices - 1; count++) {
            int u = minDistance(distance, visited);

            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && graph[u][v] != 0 && distance[u] != INF && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }

        // 打印最短路径信息
        printSolution(distance);
    }

    private static int minDistance(int[] distance, boolean[] visited) {
        int min = INF, minIndex = -1;
        int vertices = distance.length;

        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private static void printSolution(int[] distance) {
        int vertices = distance.length;
        System.out.println("顶点\t最短距离");

        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t" + distance[i]);
        }
    }

}

class BellmanFordAlgorithm {
    public static void bellmanFord(int[][] graph, int start) {
        int vertices = graph.length;
        int[] distance = new int[vertices];

        // 初始化距离数组，将起始点到各点的距离设置为无穷大
        Arrays.fill(distance, Integer.MAX_VALUE);

        // 设置起始点到自身的距离为0
        distance[start] = 0;

        // 对每条边进行V-1次松弛操作
        for (int i = 1; i < vertices; ++i) {
            for (int u = 0; u < vertices; ++u) {
                for (int v = 0; v < vertices; ++v) {
                    if (graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                        distance[v] = distance[u] + graph[u][v];
                    }
                }
            }
        }

        // 检查是否存在负权回路
        for (int u = 0; u < vertices; ++u) {
            for (int v = 0; v < vertices; ++v) {
                if (graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                    System.out.println("图中存在负权回路");
                    return;
                }
            }
        }

        // 打印最短路径信息
        printSolution(distance);
    }

    private static void printSolution(int[] distance) {
        int vertices = distance.length;
        System.out.println("顶点\t最短距离");
        for (int i = 0; i < vertices; ++i)
            System.out.println(i + "\t" + distance[i]);
    }

}

public class Min_Path {
}
