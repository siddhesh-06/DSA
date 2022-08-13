package DP.StriverDP;

public class Coins {
    public static void main(String[] args) {
        int arr[] = {1,2,3};
        int val = 4;

        System.out.println(countWaysToMakeChange(arr,val));
    }

    //2] How many ways using coins to target

    // Space-Opt
    public static long countWaysToMakeChangeSPACE(int coins[], int value){
        int n = coins.length;
        long prev[] = new long[value+1];
        long curr[] = new long[value+1];

        for(int i=0;i<value+1;i++){
            if(i%coins[0]==0) prev[i] = 1;
            else prev[i] = 0;
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<value+1;j++){
                long notTake = prev[j];
                long take = 0;
                if(coins[i]<=j){
                    take = curr[j-coins[i]];
                }
                curr[j] = (take+notTake);
            }
            prev = curr.clone();
        }

        return prev[value];
    }
    //Dp
    public static long countWaysToMakeChange(int coins[], int value){
        int n = coins.length;
        long dp[][] = new long[n][value+1];
        for(int i=0;i<value+1;i++){
            if(i%coins[0]==0) dp[0][i] = 1;
            else dp[0][i] = 0;
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<value+1;j++){
                long notTake = dp[i-1][j];
                long take = 0;
                if(coins[i]<=j){
                    take = dp[i][j-coins[i]];
                }
                dp[i][j] = (take+notTake);
            }
        }

        return dp[n-1][value];
    }
    //Recursion
    public static long countWaysToMakeChange(int denominations[], int value, int n){
        if(n==0){
            if(value%denominations[0]==0) return 1;
            else return 0;
        }

        long notTake = countWaysToMakeChange(denominations, value,n-1);
        long take = 0;
        if(denominations[n]<=value){
            take = countWaysToMakeChange(denominations, value-denominations[n],n);
        }

        return (notTake+take);
    }

    //=======================================================================================

    //1] Min coins to target

    // Space-Opt
    public int minimumElementsSPACE(int num[], int x) {
        int prev[] = new int[x + 1];
        int curr[] = new int[x + 1];

        for (int i = 0; i < x + 1; i++) {
            if (i % num[0] == 0) prev[i] = i / num[0];
            else prev[i] = 100000000;
        }

        for (int i = 1; i < num.length; i++) {
            for (int j = 0; j < x + 1; j++) {
                int notTake = 0 + prev[j];
                int take = 100000000;
                if (num[i] <= j) {
                    take = 1 + curr[j - num[i]];
                }
                curr[j] = Math.min(take, notTake);
            }

            prev = curr.clone();
        }

        if (prev[x] >= 100000000) return -1;
        else return prev[x];
    }
    // DP
    public static int minimumElements(int num[], int x) {
        int dp[][] = new int[num.length][x+1];

        for(int i=0;i<x+1;i++){
            if(i%num[0]==0) dp[0][i] = i/num[0];
            else dp[0][i] = 100000000;
        }

        for(int i=1;i<num.length;i++){
            for(int j=0;j<x+1;j++){
                int notTake = 0 + dp[i-1][j];
                int take = 100000000;
                if(num[i]<=j){
                    take = 1 + dp[i][j-num[i]];
                }
                dp[i][j] = Math.min(take, notTake);
            }
        }

        if(dp[num.length-1][x]>=100000000) return -1;
        else return dp[num.length-1][x];

    }
    // Recursion
    public static int helper(int nums[], int target, int ind){
        if(ind==0){
            if(target%nums[0]==0) return target/nums[0];
            return 100000000;
        }
        int notTake = 0 + helper(nums, target, ind-1);
        int take = 100000000; //when you finding min ways assign max vice versa
        if(nums[ind]<=target){
            take = 1 + helper(nums, target-nums[ind], ind);
        }

        return Math.min(take, notTake);
    }
}
