import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prim{

    static int max = Integer.MAX_VALUE; //max为int的最大值

    public static void Prim(int[][] map,int Vnum){
        //设置序号从0开始的结点
        char[] c = new char[]{'a','b','c','d','e','f','g','h','i'};
        //存到集合的最小权值
        int[] lowCost = new int[Vnum];
        //存放前驱结点
        int[] pre = new int[Vnum];
        //存储加入结点的顺序
        List<Character> list = new ArrayList<>();
        int i,j,min,minid,sum=0;
        for(i = 1;i < Vnum;i++){
            /**
             *  初始第0个点与邻接点相连，后继点为1
             *  lowCost数组中的第0个位置存放0--1的权值
             *  i：1 的前驱为0
             **/
            lowCost[i] = map[0][i];
            pre[i] = 0;
        }
        list.add(c[0]);
        //一共需要加n-1个节点到这个集合中，循环次数
        /**
         *  把输入的数据转换为邻接矩阵
         *                { 0, 10, MAX, MAX, MAX, 11, MAX, MAX, MAX },
         *                { 10, 0, 18, MAX, MAX, MAX, 16, MAX, 12 },
         *                { MAX, MAX, 0, 22, MAX, MAX, MAX, MAX, 8 },
         *                { MAX, MAX, 22, 0, 20, MAX, MAX, 16, 21 },
         *                { MAX, MAX, MAX, 20, 0, 26, MAX, 7, MAX },
         *                { 11, MAX, MAX, MAX, 26, 0, 17, MAX, MAX },
         *                { MAX, 16, MAX, MAX, MAX, 17, 0, 19, MAX },
         *                { MAX, MAX, MAX, 16, 7, MAX, 19, 0, MAX },
         *                { MAX, 12, 8, 21, MAX, MAX, MAX, MAX, 0 }
         *
         *
         */

        for(i = 1;i < Vnum;i++){
            min = max;  //两个不相邻的点为max
            minid = 0;  //自己到自己的权值为0
            for(j = 1;j < Vnum;j++){
                if(lowCost[j] != 0 && lowCost[j] < min){
                    min = lowCost[j];
                    minid = j;
                }
            }
            if(minid == 0){
                return;
            }
            list.add(c[minid]);
            lowCost[minid] = 0;
            sum += min;
            System.out.println(c[pre[minid]]+"到"+c[minid]+" 权值："+min);
            for(j = 1;j < Vnum;j++){
                if(lowCost[j] != 0 && lowCost[j] > map[minid][j]){
                    lowCost[j] = map[minid][j];
                    pre[j] = minid;
                }
            }
        }
        System.out.println("sum:"+sum);
    }




    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int Vnum = scanner.nextInt();   //定义顶点数
        int Enum = scanner.nextInt();   //定义边条数
        int[][] map = new int[Vnum][Vnum];
        for(int i = 0; i < Vnum; i++){  //初始化邻接表
            for(int j = 0; j < Vnum; j++){
                //结点自己到自己权值为0
                if(i == j){
                    map[i][j] = 0;
                }else{
                    map[i][j] = max;
                }
            }
        }
        for(int i = 0; i < Enum; i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int w = scanner.nextInt();
            map[a][b] = w;
            map[b][a] = w;
        }
        Prim(map,Vnum); //进入Prim方法
    }
}