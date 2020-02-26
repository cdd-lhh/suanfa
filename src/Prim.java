import java.util.*;

public class Prim{
    static class Pair{
        int weight;
        int y;
        public Pair(int w, int b){
            this.weight = w;
            this.y = b;
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int nodes = scanner.nextInt();  //定义输入结点个数
        int edges = scanner.nextInt();  //输入边条数
        /**
         *  用邻接表存储无向图结构
         *  Pair 表示初始点连接的后继点以及权值
         */
        Map<Integer, List<Pair>> grahp = new HashMap<>();
        for(int i = 0; i < edges; i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int w = scanner.nextInt();
            grahp.putIfAbsent(a, new ArrayList<>());
            grahp.putIfAbsent(b, new ArrayList<>());
            grahp.get(a).add(new Pair(w,b));
            grahp.get(b).add(new Pair(w,a));
        }
        /**
         *  prim 方法 1表示从图的1结点开始，可以是2，3，4
         *  传入的值是起始结点和这个邻接图
         */
         int minimumCost = prim(1, grahp);
        System.out.println(minimumCost);
    }
    private static int prim(int x, Map<Integer, List<Pair>> grahp){
        boolean[] visited = new boolean[100005];
        //用小顶堆队列实现升序
        PriorityQueue<Pair> heap = new PriorityQueue<>((a, b) ->a.weight - b.weight);
        /**
         *  将自己与自己的权值offer到优先队列
         */
        heap.offer(new Pair(0, x));
        //初始化权值=0
        int minimumCost = 0;
        while(!heap.isEmpty()){
            //poll()方法用于检索或获取和删除队列的第一个元素或队列头部的元素
            Pair p = heap.poll();
            /**
             *  由于优先队列中第一个点被poll删除，
             *  x指向初始点的后继结点，第一次是自己连自己，所以后继就是初始结点
             */
            x = p.y;
            System.out.println(x);  //输出连接的后继结点
            if(visited[x]) {
                continue;
            }
            visited[x] = true;
            minimumCost += p.weight;
            System.out.println("minimumCost" + minimumCost);
//            System.out.print(x);
            for(int i = 0; i < grahp.get(x).size(); i++){
                if(!visited[grahp.get(x).get(i).y]){
                    heap.offer(grahp.get(x).get(i));
                }
            }
        }
        return minimumCost;
    }
}