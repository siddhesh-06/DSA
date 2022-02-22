package DP.Knapsack;

public class EqualSumPartitionDP {
    static boolean t[][];

    EqualSumPartitionDP(int n,int cap){
        t= new boolean[n][cap];
    }

    public static void main(String args[]){
        int arr[] ={1,5,11,5};
        int sum=0;

        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }

        EqualSumPartitionDP e = new EqualSumPartitionDP(arr.length+1,sum+1);

        //Initialize
        for(int i=0;i<arr.length+1;i++){
            for(int j=0;j<sum+1;j++){
                if(i==0){
                   t[i][j]=false;
                }
                if(j==0){
                    t[i][j]=true;
                }
            }
        }

        if(sum%2!=0){
            // Not even
            System.out.println("False");
        }else{
            // For even
            System.out.println(e.subset(arr,sum/2));
        }

    }

    public boolean subset(int w[],int cap){
        for(int i=1;i<w.length+1;i++){
            for(int j=1;j<cap+1;j++){

                if(w[i-1]<=j){
                    t[i][j] = t[i-1][j-w[i-1]] || t[i-1][j];
                }else{
                    t[i][j]= t[i-1][j];
                }
            }
        }
        return t[w.length][cap];
    }
}
