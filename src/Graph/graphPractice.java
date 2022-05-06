package Graph;

import java.util.*;

class Node{
    int first, second;
    Node(int first, int second){
        this.first = first;
        this.second = second;
    }
}

public class graphPractice {
    public static void main(String args[]){

        int v = 7;
        int edges = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Initializing adj list
        for(int i=1;i<=v;i++){
            adj.add(new ArrayList<>());
        }

        // Insert edges


    }

    // BFS traversal
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

    // DFS traversal
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
            int node = q.peek().first;
            int par = q.peek().second;
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

    //Cycle detection using BFS
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
}
