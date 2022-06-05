package Graph;

import java.util.*;

class Node{
    int first, second;
    Node(int first, int second){
        this.first = first;
        this.second = second;
    }
}
class pair{
    private int v;
    private  int weight;
    pair(int _v, int _weight){ v = _v;  weight = _weight; }
    int getV(){ return v; }
    int getWeight(){ return weight; }
}
class node implements Comparator<node>{
    private int v;
    private  int weight;

    node(){}
    node(int _v,int _weight){
        v = _v;
        weight = _weight;
    }

    public int getV() { return v; }
    public int getWeight() { return weight; }

    public int compare(node n1, node n2){
        if(n1.weight<n2.weight) return -1;
        if(n1.weight>n2.weight) return 1;
        return 0;
    }

}

public class graphPractice {
    public static void main(String args[]){

        int n = 5;
        ArrayList<ArrayList<node>> adj = new ArrayList<>();
        graphPractice g = new graphPractice();
        g.createGraph(n,adj);
        g.primsEfficient(n, adj);

//        int v = 5;
//        ArrayList<ArrayList<node>> adj = new ArrayList<>();
//
//        createGraphForDijstra(v, adj);
//        graphPractice gp = new graphPractice();
//        gp.dijkstraAlgo(v,0,adj);

//        int v = 7;
//        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//
//        // Initializing adj list
//        for(int i=0;i<v;i++){
//            adj.add(new ArrayList<Integer>());
//        }
//
//        // Insert edges
//        adj.get(0).add(1);
//        adj.get(1).add(0);
//
//        adj.get(1).add(2);
//        adj.get(2).add(1);
//
//        adj.get(2).add(3);
//        adj.get(3).add(2);
//
//        adj.get(4).add(3);
//        adj.get(3).add(4);
//
//        adj.get(4).add(5);
//        adj.get(5).add(4);
//
//        adj.get(4).add(6);
//        adj.get(6).add(4);
//
//        adj.get(1).add(6);
//        adj.get(6).add(1);
//
//        graphPractice g = new graphPractice();
//
//        if(g.bipartiteDFS(v,adj)) System.out.println("Yes Bipartite");
//        else System.out.println("Not Bipartite");

    }

    public  void createGraph(int n, ArrayList<ArrayList<node>> adj){

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<node>());

        adj.get(0).add(new node(1, 2));
        adj.get(1).add(new node(0, 2));

        adj.get(1).add(new node(2, 3));
        adj.get(2).add(new node(1, 3));

        adj.get(0).add(new node(3, 6));
        adj.get(3).add(new node(0, 6));

        adj.get(1).add(new node(3, 8));
        adj.get(3).add(new node(1, 8));

        adj.get(1).add(new node(4, 5));
        adj.get(4).add(new node(1, 5));

        adj.get(2).add(new node(4, 7));
        adj.get(4).add(new node(2, 7));

    }
    public static void createGraphForDijstra(int v, ArrayList<ArrayList<node>> adj){
        //Graph with weight

        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new node(1, 2));
        adj.get(1).add(new node(0, 2));

        adj.get(0).add(new node(3, 1));
        adj.get(3).add(new node(0, 1));

        adj.get(1).add(new node(2, 4));
        adj.get(2).add(new node(1, 4));

        adj.get(2).add(new node(3, 3));
        adj.get(3).add(new node(2, 3));

        adj.get(2).add(new node(4, 1));
        adj.get(4).add(new node(2, 1));

        adj.get(1).add(new node(4, 5));
        adj.get(4).add(new node(1, 5));

    }

    //BFS traversal
    public ArrayList<Integer> bfsTraversal(int v, ArrayList<ArrayList<Integer>> adj ){
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean vis[] = new boolean[v+1];
        Arrays.fill(vis, false);

        for(int i=1;i<=v;i++){
            if(vis[i]==false){
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                vis[i] = true;

                while (!q.isEmpty()){
                    Integer node = q.poll();
                    bfs.add(node);

                    for(Integer it : adj.get(node)){
                        if(vis[it]==false){
                            vis[it] = true;
                            q.add(it);
                        }
                    }
                }
            }
        }

        return bfs;
    }

    //DFS traversal
    public ArrayList<Integer> dfsTraversal(int v, ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> ds = new ArrayList<>();
        boolean vis[] = new boolean[v+1];
        Arrays.fill(vis, false);

        for(int i=1;i<=v;i++){
            if(vis[i]==false){
                dfs(i,vis,adj,ds);
            }
        }

        return ds;
    }
    public void dfs(int i, boolean[] vis, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> ds){
        ds.add(i);
        vis[i] = true;
        for (Integer it : adj.get(i)){
            if(vis[it] == false){
                dfs(it, vis, adj, ds);
            }
        }
    }

    //Cycle detection using BFS
    public boolean cycleDetectionBFS(int v, ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean vis[] = new boolean[v+1];
        Arrays.fill(vis, false);

        for(int i=1;i<=v;i++){
            if(vis[i]==false){
                if(cycleBFS(i, vis, adj)){
                    return true;
                }
            }
        }return false;
    }
    public boolean cycleBFS(int i, boolean vis[], ArrayList<ArrayList<Integer>> adj){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(i, -1));
        vis[i] = true;

        while (!q.isEmpty()){
            int node = q.peek().first; //child
            int par = q.peek().second; //parent
            q.remove();

            for(Integer it : adj.get(node)){
                if(vis[it]==false){
                    q.add(new Node(it, node));
                    vis[it] = true;
                }else if(par!=it){
                    return true;
                }
            }
        }

        return false;
    }

    //Cycle detection using DFS
    public boolean cycleDetectionDFS(int v, ArrayList<ArrayList<Integer>> adj){
        boolean vis[] = new boolean[v+1];
        Arrays.fill(vis, false);

        for(int i=1;i<=v;i++){
            if(vis[i]==false){
                if(cycleDFS(i, -1, vis, adj)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean cycleDFS(int node, int par, boolean vis[], ArrayList<ArrayList<Integer>> adj){
        vis[node] = true;
        for (Integer it : adj.get(node)){
            if(vis[it]==false){
                if(cycleDFS(it, node,vis,adj)) return true;
                else if (it != par) return true;
            }
        }
        return false;
    }

    //Check bipartite graph using BFS
    public boolean bipartiteBFS(int v, ArrayList<ArrayList<Integer>> adj){
        int color[] = new int[v];
        Arrays.fill(color, -1);

        for(int i=0;i<v;i++){
            if(color[i]==-1){
                if(!bfsCheck(i, color, adj)){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean bfsCheck(int node, int color[], ArrayList<ArrayList<Integer>> adj){
        Queue<Integer> q = new  LinkedList<>();
        q.add(node);
        color[node] = 1;

        while (!q.isEmpty()){
            Integer nd = q.poll();
            for(Integer it : adj.get(nd)){
                if(color[it]==-1){
                    color[it] = 1 - color[nd];
                    q.add(it);
                }else if(color[it]==color[nd]){
                    return false;
                }
            }
        }
        return true;
    }

    //Check bipartite graph using DFS
    public boolean bipartiteDFS(int v, ArrayList<ArrayList<Integer>> adj){
        int color[] = new int[v];
        Arrays.fill(color, -1);

        for(int i=0;i<v;i++){
            if(color[i]==-1){
                if(!dfsCheck(i, color, adj)){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean dfsCheck(int node, int color[], ArrayList<ArrayList<Integer>> adj){
        if(color[node]==-1) color[node] = 1;

        for(Integer it : adj.get(node)){
            if(color[it]==-1){
                color[it] = 1 - color[node];
                if(!dfsCheck(it, color, adj)){
                    return false;
                }
            }else if(color[node] == color[it]){
                return false;
            }
        }
        return true;
    }

    //Directed graph cycle detetction = DFS
    public boolean isCycleDFS(int v, ArrayList<ArrayList<Integer>> adj){
        int vis[] = new int[v];
        int dfsVis[] = new int[v];

        Arrays.fill(vis, 0);
        Arrays.fill(dfsVis, 0);

        for(int i=0;i<v;i++){
            if(vis[i]==0){
                if(!directedCycleCheck(i, vis, dfsVis, adj)){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean directedCycleCheck(int node, int vis[], int dfsVis[], ArrayList<ArrayList<Integer>> adj){
        vis[node] = 1;
        dfsVis[node] = 1;

        for(Integer it : adj.get(node)){
            if(vis[it]==0){
                if(directedCycleCheck(it, vis, dfsVis, adj)){
                    return true;
                }
            }else if(dfsVis[it]==1) return true;
        }
        dfsVis[node] = 0 ;
        return false;
    }

    //Directed grapth cycle detection = BFS (kah'ns algorithm)
    public boolean directedCycleCheckBFS(int v, ArrayList<ArrayList<Integer>> adj){
        int vis[] = new int[v];
        int inDegree[] = new int[v];

        Arrays.fill(vis,0);
        Arrays.fill(inDegree, 0);

        for(int i=0;i<v;i++){
            for(Integer it : adj.get(i)){
                inDegree[it]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<v;i++){
            if(inDegree[i]==0){
                q.offer(i);
            }
        }
        int cn = 0;
        while (!q.isEmpty()){
            int node = q.poll();
            cn++;

            for(Integer it : adj.get(node)){
                inDegree[it]--;
                if(inDegree[it]==0){
                    q.offer(it);
                }
            }
        }

        if(cn==v) return false;

        return true;
    }

    //Toplogy sort = DFS
    public ArrayList<Integer> topologySortDFS(int v, ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> ans = new ArrayList<>();
        int vis[] = new int[v];
        Stack<Integer> s = new Stack<>();

        Arrays.fill(vis, -1);

        for(int i=0;i<v;i++){
            if(vis[i]==-1){
                dfsTopo(i, s, vis, adj);
            }
        }
        while (!s.isEmpty()){
            ans.add(s.pop());
        }
        return ans;
    }
    public void dfsTopo(int node, Stack<Integer> s, int vis[], ArrayList<ArrayList<Integer>> adj){
        vis[node] = 1;

        for(Integer it : adj.get(node)){
            if(vis[it]==-1){
                vis[it] = 1;
                dfsTopo(it, s, vis, adj);
            }
        }
        s.push(node);
    }

    //Topology sort = BFS (Kah'ns algorithm)
    public ArrayList<Integer> toplogySortBFS(int v, ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> ans = new ArrayList<>();
        int inDegree[] = new int[v];
        Arrays.fill(inDegree, 0);

        //1] Filling indegree for all vertices
        for(int i=0;i<v;i++){
            for(Integer it : adj.get(i)){
                inDegree[it]++;
            }
        }

        //2] Create a queue and add vertices which has indegree zero
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<v;i++){
            if(inDegree[i]==0){
                q.add(i);
            }
        }

        while (!q.isEmpty()){
            Integer node = q.poll();
            ans.add(node);

            for(Integer it : adj.get(node)){
                inDegree[it]--;
                if(inDegree[it]==0){
                    q.add(it);
                }
            }
        }

        return ans;
    }

    //Shortest path in undirected graph
    public void shortestPathUndirectedBFS(int v, int src, ArrayList<ArrayList<Integer>> adj){
        int dist[] = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        dist[src] = 0;
        q.add(src);

        while (!q.isEmpty()){
            int node = q.poll();

            for(int it : adj.get(node)){
                if(dist[node] + 1 < dist[it]){  //All weights are 1 unit thats why we adding 1 in previous
                    dist[it] = dist[node] + 1;
                    q.add(it);
                }
            }
        }

        for(int i : dist){
            System.out.print(i +" ");
        }
    }

    //Shortest path in directed acyclic graph = DFS/BFS
    public void shortestPathDirectedAcyclic(int v, int src, ArrayList<ArrayList<pair>> adj){
        //1] Toposort => stack
        //2] => using stack perform main logic

        int vis[] = new int[v];
        Arrays.fill(vis, 0);
        Stack<Integer> s = new Stack<>();

        //Toposort
        for(int i=0;i<v;i++){
            if(vis[i]==0){
                dfsTopo1(i, s, vis, adj);
            }
        }

        int dist[] = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while (!s.isEmpty()){
            int node = s.pop();
            if(dist[node] != Integer.MAX_VALUE){
                for(pair it : adj.get(node)){
                    if(dist[node] + it.getWeight() < dist[it.getV()]){
                        dist[it.getV()] = dist[node] + it.getWeight();
                    }
                }
            }
        }

        for(int i=0;i<v;i++){
            if(dist[i]==Integer.MAX_VALUE){
                System.out.print("INF ");
            }else{
                System.out.print(dist[i] + " ");
            }
        }
    }
    public void dfsTopo1(int node, Stack<Integer> s, int vis[], ArrayList<ArrayList<pair>> adj){
        vis[node] = 1;
        for(pair it : adj.get(node)){
            if(it.getV()==0){
                dfsTopo1(it.getV(), s, vis, adj);
            }
        }
        s.push(node);
    }

    //Dijkstra's algorithm
    public void dijkstraAlgo(int v, int src, ArrayList<ArrayList<node>> adj){
        int dist[] = new int[v];
        Arrays.fill(dist, 100);
        dist[src] = 0;

        PriorityQueue<node> pq = new PriorityQueue<>(v, new node());
        pq.add(new node(src,0));

        while (!pq.isEmpty()){
            node n = pq.poll();

            for(node it : adj.get(n.getV())){
                if(dist[n.getV()] + it.getWeight() < dist[it.getV()]){
                    dist[it.getV()] = dist[n.getV()] + it.getWeight();
                    pq.add(new node(it.getV(), dist[it.getV()]));
                }
            }
        }

        for(int i=0;i<v;i++){
            System.out.print(dist[i]+" ,");
        }
    }

    //Prims algorithm
    public void primsAlgo(int n, ArrayList<ArrayList<node>> adj){
        int key[] = new int[n];
        int par[] = new int[n];
        boolean mst[] = new boolean[n];

        for(int i=0;i<n;i++){
            key[i] = Integer.MAX_VALUE;
            par[i] = -1;
            mst[i] = false;
        }
        key[0] = 0;

        for(int i=0;i<n-1;i++){
            int mini = Integer.MAX_VALUE, u = 0;
            // This loops helps to find min weight amoung nodes
            for(int v=0;v<n;v++){
                if(mst[v] == false && key[v] < mini){ // so from that node we willl check min weight
                    mini = key[v];
                    u = v;
                }
            }

            mst[u] = true;

            for(node it : adj.get(u)){
                if(mst[it.getV()] == false && it.getWeight() < key[it.getV()]){
                    par[it.getV()] = u;
                    key[it.getV()] = it.getWeight();
                }
            }
        }

        for(int i=1;i<n;i++){
            System.out.println(par[i]+" - "+i);
        }
    }
    public void primsEfficient(int n, ArrayList<ArrayList<node>> adj){
        int key[] = new int[n];
        int par[] = new int[n];
        boolean mst[] = new boolean[n];
        Arrays.fill(key, 1000);
        Arrays.fill(mst, false);

        PriorityQueue<node> pq = new PriorityQueue<>(n,new node());
        key[0] = 0;
        par[0] = -1;
        pq.add(new node(key[0], 0));

        while (!pq.isEmpty()){
            int u = pq.poll().getV();
            mst[u] = true;
            for(node it : adj.get(u)){
                if(mst[it.getV()] == false && it.getWeight() < key[it.getV()]){
                    key[it.getV()] = it.getWeight();
                    par[it.getV()] = u;
                    pq.add(new node(it.getV(), key[it.getV()]));
                }
            }
        }

        for(int i =1;i<n;i++){
            System.out.println(par[i] +"-"+i); //shortest path
        }
    }

    //Bridge in graph
    public void printBridges(ArrayList<ArrayList<Integer>> adj, int n){
        int vis[] = new int[n];
        int tin[] = new int[n];
        int low[] = new int[n];

        int timer = 0;
        for(int i=0;i<n;i++){
            if(vis[i]==0){
                dfsBridges(i, -1, vis, tin, low, adj, timer);
            }
        }
    }
    public void dfsBridges(int node, int parent, int vis[], int tin[], int low[], ArrayList<ArrayList<Integer>> adj, int timer){
        vis[node] = 1;
        tin[node] = low[node] = timer++;

        for(Integer it : adj.get(node)){
            if(it == parent) continue;

            if(vis[it] == 0){
                dfsBridges(it, node, vis, tin, low, adj, timer);
                low[node] = Math.min(low[node], low[it]);
                if(low[it] > tin[node]){
                    System.out.println(it +" "+node);
                }
            }else{
                low[node] = Math.min(low[node], tin[it]);
            }
        }
    }

    //Articulation point
    public void articulation(int v, ArrayList<ArrayList<Integer>> adj){
        int vis[] = new int[v];
        int tin[] = new int[v];
        int low[] = new int[v];

        int isArticulation[] = new int[v];
        int timer = 0;
        for(int i=0;i<v;i++){
            if(vis[i]==0){
                dfsArti(i,-1,adj,vis,tin,low,timer,isArticulation);
            }
        }

        for(int i: isArticulation){
            System.out.print(i+", ");
        }
    }
    public void dfsArti(int node, int parent, ArrayList<ArrayList<Integer>> adj, int vis[], int tin[], int low[], int timer, int isArticulation[]){
        vis[node] = 1;
        tin[node] = low[node] = timer++;

        int child = 0;
        for(Integer it : adj.get(node)){
            if(it == parent) continue;
            if(vis[it] == 0){
                dfsArti(it, node, adj, vis, tin, low, timer, isArticulation);
                low[it] = Math.min(low[it], low[node]);
                if(low[it] >= tin[node] && parent!=-1){
                    isArticulation[node] = 1;
                }
                child++;
            }else{
                low[node] = Math.min(low[node], tin[it]);
            }
        }
        if(parent!=-1 && child>1) isArticulation[node] = 1;
    }

    //Kosaraju's Algorithm
    public void kosaRaju(int v, ArrayList<ArrayList<Integer>> adj){
        //1] Toposort
        int vis[] = new int[v];
        Stack<Integer> s = new Stack<>();

        for(int i=0;i<v;i++){
            if(vis[i]==0){
                dfsRaju(i,vis,adj,s);
            }
        }

        //2] Transpose = Change the directions
        ArrayList<ArrayList<Integer>> trans = new ArrayList<>();
        for(int i=0;i<v;i++){
            trans.add(new ArrayList<>());
        }
        for(int i=0;i<v;i++){
            vis[i] = 0;
            for(Integer it : adj.get(i)){
                trans.get(it).add(i);
            }
        }

        //3] Call dfs
        while (!s.isEmpty()){
            int node = s.pop();
            if(vis[node]==0){
                System.out.print("SCC: ");
                revDfs(node, trans, vis);
                System.out.print("");
            }
        }

    }
    public void dfsRaju(int node, int vis[], ArrayList<ArrayList<Integer>> adj, Stack<Integer> s){
        vis[node] = 1;
        for(Integer it : adj.get(node)){
            if(vis[it]==0){
                dfsRaju(it, vis, adj, s);
            }
        }
        s.push(node);
    }
    public void revDfs(int node, ArrayList<ArrayList<Integer>> trans, int vis[]){
        vis[node] = 1;
        System.out.println(node+", ");
        for(Integer it : trans.get(node)){
            if(vis[it]==0){
                revDfs(it, trans, vis);
            }
        }
    }

    //Bellman ford Algorithm
    public void bellmanFord(int v, int src, ArrayList<gNode> adj){
        //1] Relax v-1 times
        //2] Relax one more time for detecting -ve cycle
        int dist[] = new int[v];
        Arrays.fill(dist, 1000);

        dist[src] = 0;
        for(int i=1;i<=v-1;i++){
            for(gNode nd : adj){
                if(dist[nd.getU()] + nd.getWeight() < dist[nd.getV()]){
                    dist[nd.getV()] = dist[nd.getU()] + nd.getWeight();
                }
            }
        }
        boolean isCycle = false;
        for(gNode nd : adj){
            if(dist[nd.getU()] + nd.getWeight() < dist[nd.getV()]){
                isCycle = true;
                System.out.println("Negative cycle is present");
                break;
            }
        }
        if(!isCycle){
            for(int i=0;i<v;i++){
                System.out.print(i +" "+dist[i]);
            }
        }
    }

}
