package Graph;

import java.util.ArrayList;

public class adjacencyList {
    public static void main(String args[]){
        int n = 3, m = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<Integer>());
        }

        // Edge 1-2
        adj.get(1).add(2);
        adj.get(2).add(1);

        // Edge 2-3
        adj.get(2).add(3);
        adj.get(3).add(2);

        // Edge 1-3
        adj.get(1).add(3);
        adj.get(3).add(1);

        for(int i=1;i<n;i++){
            for(int j=0;j<adj.get(i).size();j++){
                System.out.println(adj.get(i).get(j)+ "");
            }
            System.out.println();
        }

    }
}
