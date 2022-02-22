package DP.Knapsack;

public class minSubsetDiffDP {

    static boolean t[][];

    minSubsetDiffDP(int n,int sum){
        t = new boolean[n+1][sum+1];
    }

    public static void main(String args[]){
        int arr[]= {1,2,7};
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum=sum+arr[i];
        }

        minSubsetDiffDP m = new minSubsetDiffDP(arr.length,sum);

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

        System.out.println("Ans : "+m.subSet(arr,sum));


    }

    public int subSet(int arr[],int sum){

        //Set Matrix
        for(int i=1;i<arr.length+1;i++){
            for(int j=1;j<sum+1;j++){
                if(arr[i-1]<=j){
                    t[i][j]=t[i-1][j-arr[i-1]] || t[i-1][j];
                }else{
                    t[i][j]=t[i-1][j];
                }
            }
        }

        int min=Integer.MAX_VALUE;
        for(int i=0;i<(sum+1)/2;i++){
            if(t[arr.length][i]==true){
                min = Math.min(min,sum-2*i);
            }
        }

        return min;


    }


}
