package Algorithm;

import java.util.Scanner;

class node {
    public int u,v,wt;

    node(int u,int v,int wt){
        this.u=u;
        this.v=v;
        this.wt=wt;
    }

}

public class bellmonFordAlgo {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int v,e;

        System.out.print("Enter no of vertices: ");
        v=sc.nextInt();

        System.out.print("Enter no of edges : ");
        e=sc.nextInt();

        node edegeList[] = new node[e];

        for(int i=0;i<e;i++){

            int v1,v2,wt;
            System.out.print("V1 =>");
            v1=sc.nextInt();
            System.out.print("V2 =>");
            v2=sc.nextInt();

            System.out.print("WT=>");
            wt=sc.nextInt();

            edegeList[i] = new node(v1,v2,wt);
        }

        // Creating dist array
        int dist[] = new int[v];

        for(int i=0;i<v;i++){
            dist[i]=Integer.MAX_VALUE;
        }
        int src;
        System.out.print("Enter source vertex: ");
        src=sc.nextInt();

        dist[src-1]=0;

        for(int i=0;i<v-1;i++){ // Main diff v-1 loop
            for(int j=0;j<e;j++){
                if(dist[edegeList[j].u-1] + edegeList[j].wt < dist[edegeList[j].v-1]){
                    dist[edegeList[j].v-1] = dist[edegeList[j].u-1] + edegeList[j].wt ;
                }
            }
        }
        // Complexity : O(ve)

        boolean negativeCycle = false;

        // Checking
        for(int i=0;i<e;i++){
            if(dist[edegeList[i].u-1] + edegeList[i].wt < dist[edegeList[i].v-1]){
                negativeCycle = true;
                break;
            }
        }

        if(negativeCycle){
            System.out.println("Negative cycle present");
        }else{
            System.out.println("Shortest distance to all vertices are: ");
            for(int i=0;i<v;i++){
                System.out.println(i+1 +" => "+ dist[i]);
            }
        }

    }
}
