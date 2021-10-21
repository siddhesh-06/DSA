package Algorithm;

import java.util.Arrays;
import java.util.Scanner;

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
                final_value += st[i].profit;

            }else{

                double remain = m - currentW;
                final_value += st[i].p_w * remain;

            }
        }
        System.out.println("Final value: "+final_value);


    }
}
