package Graph;

public class disjointSet {
    int rank[] = new int[1000];
    int par[] = new int[1000];

    public static void main(String args[]){
        disjointSet dj = new disjointSet();

        dj.makeSet();
        int m = 5;
        while (m!=0){
            int u=0,v=0;
            dj.union(u, v);
            m++;
        }

        if(dj.findPar(2) != dj.findPar(3)){
            System.out.println("Different componet");
        }else{
            System.out.println("Same");
        }
    }

    public void makeSet(){
        for(int i=0;i<rank.length;i++){
            rank[i] = 0;
            par[i] = i;
        }
    }

    public int findPar(int node){
        if(node == par[node]){
            return node;
        }
        return par[node] = findPar(par[node]);
    }

    public void union(int u, int v){
        u = findPar(u);
        v = findPar(v);

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
