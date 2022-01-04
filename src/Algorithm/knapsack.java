package Algorithm;

import java.util.Arrays;
import java.util.Scanner;

//Divide and Conquer algorithm works by dividing a problem into subproblems, conquer by solving each subproblem recursively and then combine these solutions to get solution of the main problem.
//Whereas DP is a optimization technique for solving problems in an optimised manner by dividing problem into smaller subproblems and then evaluating and storing their results and constructing an optimal solution for main problem from computed information.
// The most important difference in Divide and Conquer strategy is that the subproblems are independent of each other. When a problem is divided into subproblems, they do not overlap which is why each subproblem is to be solved only once.
//Whereas in DP, a subproblem solved as part of a bigger problem may be required to be solved again as part of another subproblem (concept of overlapping subproblem), so the results of a subproblem is solved and stored so that the next time it is encountered, the result is simply fetched and returned.

class storage{
    int profit,weight;
    double p_w;

    storage ( int p,int w){
        this.profit=p;
        this.weight=w;
        this.p_w=(double) p/w;
    }

}

public class knapsack {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int m,obj,p,w;

        // P => 60,100,120
        // W => 10,20,30
        // Cap => 50

        System.out.print("Enter capacity of bag: ");
        m=sc.nextInt();

        System.out.print("Enter no of objects: ");
        obj=sc.nextInt();

        storage[] st = new storage[obj];

        for(int i=0;i<obj;i++){
            System.out.print("Enter profit: ");
            p=sc.nextInt();
            System.out.print("Enter weight: ");
            w=sc.nextInt();
            st[i] = new storage(p,w);
        }

        //sorting
        Arrays.sort(st,(o1, o2) ->
                Double.compare(o2.p_w, o1.p_w)
        );

        System.out.println("");
        System.out.println("-----Data-Set------");
        System.out.println("");

        System.out.print("Profit ");
        for(storage i: st){
            System.out.print(i.profit+" ");
        }
        System.out.println();

        System.out.print("Weight ");
        for(storage i: st){
            System.out.print(i.weight+" ");
        }
        System.out.println();

        System.out.print("P_W ");
        for(storage i: st){
            System.out.print(i.p_w+" ");
        }

        int currentW=0 ;
        double final_value=0.0;

        for(int i =0;i<obj;i++){
            if(currentW +st[i].weight <=m){
                currentW = currentW + st[i].weight;
                final_value = final_value + st[i].profit;
            }else{
                double remain = m - currentW;
                final_value += st[i].p_w * remain;
            }
        }
        System.out.println();
        System.out.println("Final value: "+final_value);


    }
}
