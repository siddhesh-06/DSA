package Graph;

public class adjacencyMat {
    public static void main(String args[]){
        int n = 3, m = 3;
        int adj[][] = new int[m+1][m+1];

        //edge 1<->2
        adj[1][2] = 1;
        adj[2][1] = 1;

        //edge 2<->3
        adj[2][3] = 1;
        adj[3][2] = 1;

        //edge 2<->3
        adj[1][3] = 1;
        adj[3][1] = 1;

        for(int i=0;i<adj.length;i++){
            for(int j=0;j<adj.length;j++){
                adj[i][j] = 1;
                adj[j][i] = 1;
            }
        }

    }
}
