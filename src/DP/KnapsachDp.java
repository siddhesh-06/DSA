package DP;

import Algorithm.knapsackRecursive;
// 3 types  :
//    0/1 (bounded) knapsack => single time taking elements
//    Fractional knapsack => Greedy approach
//    Unbounded knapsack => multiple times taking elements
public class KnapsachDp {

    //Dp array
    static int t[][] = new int[5][8];  //n*m

    public static void main(String args[]){
        int w[] ={1,3,4,5};
        int v[] ={1,4,5,7};
        int n=4;
        int cap=7;

        knapsackRecursive k =new knapsackRecursive();
        System.out.println("Ans: "+k.recursiveKnap(w,v,cap,n));

    }

    public int max(int a,int b){
        return  a>b ? a : b;
    }

    public int recursiveKnap(int[] w, int[] v, int cap, int n){

        // Initialize to ans of base condition
        for(int i=0;i<n+1;i++){
            for(int j=0;j<cap+1;j++){
                if(i==0 || j==0){
                    t[i][j]=0;
                }
            }
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<cap+1;j++){
                if(w[i-1]<=j){
                    //  v[i-1]+t[i][j-w[i-1]] for unbounded knapsack
                    t[i][j] = max(v[i-1]+t[i-1][j-w[i-1]],t[i-1][j]);
                }else {
                    t[i][j] =t[i-1][j];
                }
            }
        }

        return t[n][cap];
    }

}
