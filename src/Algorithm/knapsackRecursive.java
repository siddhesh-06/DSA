package Algorithm;

import java.beans.PropertyEditorSupport;

public class knapsackRecursive {
    public static void main(String args[]){
        int a[] ={2,3,1};
        int b[] ={15,5,3};
        knapsackRecursive k =new knapsackRecursive();
        k.recursiveKnap(b,a,5,3);
        System.out.println(k.recursiveKnap(a,b,5,3));
    }

    public int max(int a,int b){
        return  a>b ? a : b;
    }

    public int recursiveKnap(int wt[],int val[],int cap,int n){
        if(n==0 || cap==0){
            return 0;
        }

        if(wt[n-1] <= cap){
            return max(val[n-1] + recursiveKnap(wt,val,cap-wt[n-1],n-1),recursiveKnap(wt,val,cap,n-1));
        }else if(wt[n-1]>cap){
            return recursiveKnap(wt,val,cap,n-1);
        }

        return 0;
    }

}
