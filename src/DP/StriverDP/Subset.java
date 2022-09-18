package DP.StriverDP;

import java.util.HashSet;

public class Subset {
    public static void main(String[] args) {
        int arr[] = {4,3,2,1};
        System.out.println(subsetSumToKDP(4, 5, arr));
        HashSet<Integer> hs = new HashSet<>();

    }

    // 4] Count subsets with sum k
    // Recursion
    public static int findWays(int num[], int tar) {
        return helper2(num.length-1, tar, num);
    }
    public static int helper2(int ind, int target, int num[]){
        if(target==0) return 1;

        if(ind==0){
            if(num[0]==target) return 1;
            else return 0;
        }

        int not = helper2(ind-1, target, num);
        int take = 0;
        if(num[ind]<=target) take = helper2(ind-1, target-num[ind], num);

        return not+take;
    }
    // DP
    public static int findWaysDP(int num[], int tar) {
        int n = num.length;
        int dp[][] = new int[n][tar+1];

        for(int i=0;i<n;i++){
            dp[i][0] = 1;
        }
        if(num[0]<=tar) dp[0][num[0]] = 1;

        for(int ind=1;ind<n;ind++){
            for(int target=1;target<tar+1;target++){
                int not = dp[ind-1][target];
                int take = 0;
                if(num[ind]<=target) take = dp[ind-1][target-num[ind]];

                dp[ind][target] = not+take;
            }
        }

        return dp[n-1][tar];
    }
    // SPACE-OPTI
    public static int findWaysSPACE(int num[], int tar) {
        int n = num.length;
        int prev[] = new int[tar+1];
        int curr[] = new int[tar+1];

        prev[0] = curr[0] = 1;
        if(num[0]<=tar) prev[num[0]] = 1;

        for(int ind=1;ind<n;ind++){
            for(int target=1;target<tar+1;target++){
                int not = prev[target];
                int take = 0;
                if(num[ind]<=target) take = prev[target-num[ind]];

                curr[target] = not+take;
            }
            prev = curr.clone();
        }

        return prev[tar];
    }


    // 3] Partiton into 2 subset with min abs sum diff
    public static int minSubsetSumDifference(int[] arr, int n) {
        int total_sum = 0;
        for(int val : arr) total_sum += val;

        boolean dp[][] = new boolean[n][total_sum+1];

        for(int i=0;i<n;i++) dp[0][i] = true;
        if(arr[0]<=total_sum) dp[0][arr[0]] = true;

        for(int i=1;i<n;i++){
            for(int j=1;j<total_sum+1;j++){
                boolean not = dp[i-1][j];
                boolean take = false;
                if(arr[i]<=j) take = dp[i-1][j-arr[i]];

                dp[i][j] = not || take;
            }
        }
        int min = Integer.MAX_VALUE;

        for(int i=0;i<(total_sum+1)/2;i++){
            if(dp[n-1][i]){
                int s1 = i;
                int s2 = total_sum-s1;
                min = Math.min(min, Math.abs(s2-s1));
            }
        }

        return min;
    }


    // 2] Equal sum partition (sum1 == sum2)
    public static boolean canPartition(int[] arr, int n) {
        int total_sum = 0;
        for(int i=0;i<n;i++){
            total_sum += arr[i];
        }
        int sum = total_sum/2;
        return helper(0, sum, arr);
         //same as subset
    }


    // 1] Subset sum equal to target
    //  Recursion
    public static boolean subsetSumToK(int n, int k, int arr[]){
        return helper(n-1, k, arr);
    }
    public static boolean helper(int ind, int target, int arr[]){
        if(ind==0) return arr[0]==target;
        if(target==0) return true;

        boolean not = helper(ind+1, target, arr);
        boolean take = false;
        if(arr[ind]<=target){
            take = helper(ind+1, target-arr[ind], arr);
        }

        return take || not;
    }
    // DP
    public static boolean subsetSumToKDP(int n, int k, int arr[]){
        boolean dp[][] = new boolean[n][k+1];
        if(arr[0]<=k) dp[0][arr[0]] = true;  // why? because target==arr[0]

        for(int ind=1;ind<n;ind++){
            for(int target=1;target<k+1;target++){
                boolean not = dp[ind-1][target];
                boolean take = false;
                if(arr[ind]<=target){
                    take = dp[ind-1][target-arr[ind]];
                }
                dp[ind][target] = not||take;
            }
        }
        return dp[n-1][k];
    }
    // SPACE-OPTI
    public static boolean subsetSumToKSPACE(int n, int k, int arr[]){

        boolean prev[] = new boolean[k+1];
        boolean curr[] = new boolean[k+1];

        prev[0] = curr[0] = true;
        if(arr[0]<=k) prev[arr[0]] = true;

        for(int ind=1;ind<n;ind++){
            for(int target=1;target<k+1;target++){
                boolean not = prev[target];
                boolean take = false;
                if(arr[ind]<=target){
                    take = prev[target-arr[ind]];
                }
                curr[target] = not||take;
            }
            prev = curr.clone();
        }

        return prev[k];
    }
}
