package Graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class gNode{
    int u, v, weight;

    gNode(){}
    gNode(int u, int v,int weight){
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getU(){ return u; }
    public int getV(){ return  v; }
    public int getWeight(){ return weight; }

}

class SortCompatator implements Comparator<gNode> {
    public int compare(gNode n1, gNode n2){
        if(n1.getWeight() < n2.getWeight()){
            return -1;
        }
        if(n1.getWeight() > n2.getWeight()){
            return 1;
        }
        return 0;
    }
}


public class kruskalAlgo {
    public static void main(String args[]){
        int n = 5;
        ArrayList<gNode> adj = new ArrayList<>();

        adj.add(new gNode(0,1,2));
        adj.add(new gNode(0,3,6));
        adj.add(new gNode(1,3,8));
        adj.add(new gNode(1,2,3));
        adj.add(new gNode(1,4,5));
        adj.add(new gNode(2,4,7));

        kruskalAlgo k = new kruskalAlgo();
        k.kruskal(adj, n);
    }

    public void kruskal(ArrayList<gNode> adj, int n){
        Collections.sort(adj, new SortCompatator());
        int par[] = new int[n];
        int rank[] = new int[n];

        for(int i=0;i<n;i++){
            par[i] = i;
            rank[i] = 0;
        }
        int costMst = 0;
        ArrayList<gNode> mst = new ArrayList<>();

        for(gNode it : adj){
            if(finPar(it.getU(),par) != finPar(it.getV(),par)){
                costMst += it.getWeight();
                mst.add(it);
                union(it.getU(),it.getV(),par,rank);
            }
        }
        System.out.println(costMst);
        for(gNode it: mst){
            System.out.println(it.getU() +" "+it.getV());
        }
    }

    public int finPar(int nd, int par[]){
        if(nd == par[nd]) return nd;
        return par[nd] = finPar(par[nd], par);
    }

    public void union(int u, int v, int par[], int rank[]){
        u = finPar(u, par);
        v = finPar(v, par);

        if(rank[u] < rank[v]){
            par[u] = v;
        }else if(rank[u] > rank[v]){
            par[v] = u;
        }else{
            par[v] = u;
            rank[u]++;
        }
    }
}
