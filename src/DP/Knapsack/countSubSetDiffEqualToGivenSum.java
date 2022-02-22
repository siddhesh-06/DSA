package DP.Knapsack;

public class countSubSetDiffEqualToGivenSum {
    static int t[][];

    // Same as TARGET SUM problem
    countSubSetDiffEqualToGivenSum(int n,int sum){
        t = new int[n+1][sum+1];
    }

    public static void main(String args[]){
        int arr[]={1,1,2,3};

        int diff = 1;
        int arrSum = 0;
        // {1,1,2} - {3} => 1
        // {1,3} - {2,1} => 1
        // {3,1} - {1,2} => 1

        int s1 = 0;

        for(int i=0;i<arr.length;i++){
            arrSum+=arr[i];
        }
        // s1 -s2 = diff (1)
        // s1 + s2 = arrSum
        // s1 = (diff + arrSum) / 2
        s1 = (diff+arrSum)/2;

        countSubSetDiffEqualToGivenSum cs = new countSubSetDiffEqualToGivenSum(arr.length,s1);

        // Initialize
        for(int i=0;i<arr.length+1;i++){
            for(int j=0;j<s1+1;j++){
                if(i==0){
                    t[i][j]=0;
                }
                if(j==0){
                    t[i][j]=1;
                }
            }
        }

        System.out.println("Ans : "+cs.countSubset(arr,s1));

    }

    public int countSubset(int arr[],int s1){
        for(int i=1;i< arr.length+1;i++){
            for(int j=1;j<s1+1;j++){
                if(arr[i-1]<=j){
                    t[i][j]=t[i-1][j-arr[i-1]] + t[i-1][j];
                }else{
                    t[i][j]=t[i-1][j];
                }
            }
        }

        return t[arr.length][s1];
    }
}
