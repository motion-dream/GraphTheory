import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: MirrorML
 * Date: November 21, 2023
 */
class HungarianAlgorithm {
    private static int[] match;

    public static int maxMatching(int[][] graph) {
        int m = graph.length;
        int n = graph[0].length;
        match = new int[n];
        Arrays.fill(match, -1);

        int result = 0;
        for (int i = 0; i < m; i++) {
            boolean[] visited = new boolean[n];
            if (findAugmentingPath(graph, i, visited)) {
                result++;
            }
        }

        return result;
    }

    private static boolean findAugmentingPath(int[][] graph, int u, boolean[] visited) {
        int n = graph[0].length;
        for (int v = 0; v < n; v++) {
            if (graph[u][v] == 1 && !visited[v]) {
                visited[v] = true;
                if (match[v] == -1 || findAugmentingPath(graph, match[v], visited)) {
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 0, 1},
                {0, 0, 1, 0}
        };

        int maxMatching = maxMatching(graph);
        System.out.println("Maximum Matching: " + maxMatching);
    }
}
/*
Hopcroft-Karp算法
 */

class HopcroftKarpAlgorithm {
    private int M = 0;
    private int N = 0;
    private int[][] adjMatrix;
    private int[] pairU;
    private int[] pairV;
    private int[] distance;

    public HopcroftKarpAlgorithm(int m, int n) {
            this.M = m;
            this.N = n;
            adjMatrix = new int[M + 1][N + 1];
            pairU = new int[M + 1];
            pairV = new int[N + 1];
            distance = new int[M + 1];

            Arrays.fill(pairU, 0);
            Arrays.fill(pairV, 0);
    }

    public void setAdjMatrix(int[][] matrix) {
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                adjMatrix[i][j] = matrix[i - 1][j - 1];
            }
        }
    }

    public int hopcroftKarpAlgorithm() {
        int result = 0;

        while (bfs()) {
            for (int u = 1; u <= M; u++) {
                if (pairU[u] == 0 && dfs(u)) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();

        for (int u = 1; u <= M; u++) {
            if (pairU[u] == 0) {
                distance[u] = 0;
                queue.offer(u);
            } else {
                distance[u] = Integer.MAX_VALUE;
            }
        }

        distance[0] = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            if (distance[u] < distance[0]) {
                for (int v = 1; v <= N; v++) {
                    if (adjMatrix[u][v] == 1 && distance[pairV[v]] == Integer.MAX_VALUE) {
                        distance[pairV[v]] = distance[u] + 1;
                        queue.offer(pairV[v]);
                    }
                }
            }
        }

        return (distance[0] != Integer.MAX_VALUE);
    }

    private boolean dfs(int u) {
        if (u != 0) {
            for (int v = 1; v <= N; v++) {
                if (adjMatrix[u][v] == 1 && distance[pairV[v]] == distance[u] + 1) {
                    if (dfs(pairV[v])) {
                        pairV[v] = u;
                        pairU[u] = v;
                        return true;
                    }
                }
            }
            distance[u] = Integer.MAX_VALUE;
            return false;
        }
        return true;
    }
}
public class Max_Match {
}
