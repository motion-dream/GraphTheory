import java.util.Arrays;

/**
 * Author 1: MirrorML
 * Date: November 21, 2023
 * Author 2: ZYH
 * Date: November 27, 2023
 */
class PrimAlgorithm {
    public static  int V = 5; // 顶点数

    public static void primMST(int graph[][]) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mstSet, false);

        key[0] = 0; // 选择第一个顶点作为起始点

        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    private static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private static void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

}
/*
Kruskal算法
 */
class KruskalAlgorithm {
    public static  int V = 5;

    public static void kruskal(int[][] graph) {
        int[] parent = new int[V];

        // 初始化parent数组，每个顶点的父节点初始为自身
        for (int i = 0; i < V; ++i)
            parent[i] = i;

        // 打印最小生成树的边
        System.out.println("Edge \tWeight");
        for (int count = 0; count < V - 1; ++count) {
            int minWeight = Integer.MAX_VALUE;
            int u = -1, v = -1;

            for (int i = 0; i < V; ++i) {
                for (int j = 0; j < V; ++j) {
                    if (find(parent, i) != find(parent, j) && graph[i][j] != 0 && graph[i][j] < minWeight) {
                        minWeight = graph[i][j];
                        u = i;
                        v = j;
                    }
                }
            }

            union(parent, u, v);
            System.out.println(u + " - " + v + "\t" + minWeight);
        }
    }

    private static int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        else {
            int F = find(parent,i);
            parent[i] = F;
            return F;

        }
    }

    private static void union(int[] parent, int x, int y) {
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        parent[xSet] = ySet;
    }
}
public class Min_Gen_Tree {
}
