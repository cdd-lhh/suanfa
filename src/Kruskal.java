

import java.util.*;

public class Kruskal {
/*
4 5
1 2 1
1 4 2
2 3 4
2 4 3
3 4 5
* */

    /**
     *   由于是以边来构造的MST的，就可以定义一和一条边连接两个顶点的结构
     */

    static class Edge {
        static class  DSU{
            int[] root; //根结点
            int[] size;
            public DSU(int n){
                root = new int[n];
                size = new int[n];
                //每个顶点的父亲都是自己
                for(int i = 0; i < n; i++){
                    root[i] = i;
                }
                /**
                 *  Arrays.fill(Object[ ] arr, Object obj);
                 *  向数组中传送一个相同对象。
                 *  Arrays.fill(int[ ] arr, int value);
                 *  向数组中传送一个相同value。
                 *  相当于for
                 *  初始化size数组
                 */
                Arrays.fill(size, 1);
            }
            //找到根结点
            public int find(int x){
                if(root[x] != x){   //判断root[x]是否被联合（找上级）
                    root[x] = find(root[x]);
                }
                return root[x];
            }
            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                //判断是否是一个连通分量
                if(rootX == rootY)
                    return;
                if (size[rootX] < size[rootY]) {
                    root[rootX] = rootY;
                    size[rootY]++;
                }else{
                    root[rootY] = rootX;
                    size[rootX]++;
                }
            }
        }
        int weight;
        int x,y;
        public Edge(int weight, int a, int b){
            this.weight = weight;
            this.x = a;
            this.y = b;
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        //创建Edge类型的数组用于保存
        Edge[] edges = new Edge[m];
        for(int i = 0; i < m; i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int w = scanner.nextInt();
            //存到数组
            edges[i] = new Edge(w, a, b);
        }
        //首先对边的权值进行升序排序
        Arrays.sort(edges,(a,b) -> a.weight - b.weight);
        int minimumCost = kruskal(edges);
        System.out.println(minimumCost);
    }

    /**
    *   传入数组，代表是一个图
    * */
    private static int kruskal(Edge[] edges){
        //并查集思想
        //初始化集合的大小
        Edge.DSU dsu = new Edge.DSU(10005);
        int minimumCost = 0;
        for(Edge e : edges){
            int x = e.x;
            int y = e.y;
            if(dsu.find(x) != dsu.find(y)){
                minimumCost += e.weight;
                dsu.union(x,y);
            }
        }
        return minimumCost;
    }
}
