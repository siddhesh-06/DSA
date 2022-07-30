package DP.StriverDP;

import java.util.*;

class reverse implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        if(o2>o1) return 1;
        return -1;
    }
}

public class striverDP {

    public static void main(String args[]){
        int arr[][] = {{3,50},{7,10},{12,25}};
        System.out.println(calculateTax(arr,10));
    }

    public static double calculateTax(int[][] brackets, int income) {
        if(income==0) return 0.0;
        int n = brackets.length;

        double ans =0.0;
        if(income>brackets[0][0]){
            ans += (brackets[0][0]*(brackets[0][1]/100.00));
            income -= brackets[0][0];
        }else{
            ans += (income*((brackets[0][1]/100)));
            income -= income;
        }

        for(int i=1;i<n;i++){
            double diff = brackets[i][0] - brackets[i-1][0];
            if(income>diff){
                ans += (diff*((brackets[i][1]/100.00)));
                income -= diff;
            }else{
                ans += (income*((brackets[i][1]/100.00)));
                income -= income;
            }
        }

        return ans;

    }

    //Count subset no
    //https://www.codingninjas.com/codestudio/problems/number-of-subsets_3952532?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=1
    public static int findWays(int num[], int tar) {
        int n = num.length;

        int prev[] = new int[tar+1];
        int curr[] = new int[tar+1];
        // Base case
        prev[0] = curr[0] = 1;
        if(num[0]<=tar) prev[num[0]] = 1;

        // Loop
        for(int i=1;i<n;i++){
            for(int j=0;j<tar+1;j++){
                int notPick = prev[j];
                int pick = 0;
                if(num[i]<=j){
                    pick = prev[j-num[i]];
                }

                curr[j] = notPick + pick;
            }

            prev = curr.clone();
        }

        return prev[tar];
    }

    // Subset min diff
    public static int subsetSumToKDP(int n, int k, int arr[], int totalSum){
        boolean dp[][] = new boolean[n][k+1];

         for(int i=0;i<n;i++){
             dp[i][0] = true;
         }

        if(arr[0]<=k) dp[0][arr[0]] = true;

        for(int i=1;i<n;i++){
            for(int j=1;j<=k;j++){
                boolean notTake = dp[i-1][j];
                boolean take = false;
                if(j>=arr[i]){
                    take = dp[i-1][j-arr[i]];
                }
                dp[i][j] = take || notTake;
            }
        }

        // pick up last row
        int min = Integer.MAX_VALUE;
        for(int i=0;i<k+1;i++){
            if(dp[n-1][i]==true){
                min = Math.min(min, Math.abs(k-totalSum-k));
            }
        }


        return min;
    }

    // Subset partition
    public static boolean subsetSumToK(int n, int k, int arr[]){
        //boolean dp[][] = new boolean[n][k+1];
        boolean prev[] = new boolean[k+1];
        boolean curr[] = new boolean[k+1];
        Arrays.fill(prev, false);
        Arrays.fill(curr, false);

//         for(int i=0;i<n;i++){
//             dp[i][0] = true;
//         }
        prev[0] = curr[0] = true;

        if(arr[0]<=k) prev[arr[0]] = true;

        for(int i=1;i<n;i++){
            for(int j=1;j<=k;j++){
                // i- 1 => prev
                // i => curr
                boolean notTake = prev[j];
                boolean take = false;
                if(j>=arr[i]){
                    take = prev[j-arr[i]];
                }
                curr[j] = take || notTake;
            }
            prev = curr;
        }

        return prev[k];
    }

    public static boolean subsetSumToKRecursion(int n, int k, int arr[]){
        return subsetSumK(arr,n,k);
    }
    private static boolean subsetSumK(int[] arr, int n, int target) {
        if(target==0) return true;
        if(n==0) return (arr[0]==target);

        boolean notTaken = subsetSumK(arr,n-1,target);
        boolean taken = false;
        if(target<=arr[n]){
            taken = subsetSumK(arr,n-1,target-arr[n]);
        }

        return (notTaken || taken);
    }


    //4] Subsequence not adj
    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        int t[] = new int[nums.size()+1];
        for(int i=0;i<t.length;i++){
            t[i] = -1;
        }
        return helper(nums, nums.size()-1,t);
    }
    public static int helper(ArrayList<Integer> nums, int ind, int t[]){
        if(ind==0) return nums.get(ind);

        if(ind<0) return 0;

        if(t[ind]!=-1) return t[ind];
        int taken = nums.get(ind) + helper(nums, ind-2, t);
        int notTaken = 0 + helper(nums, ind-1, t);

        t[ind] = Math.max(taken, notTaken);

        return t[ind];
    }

    //3] Frog jumps
    public int fJump(int n, int heights[]){
        if(n==0) return 0;

        int fs = fJump(n-1, heights) + Math.abs(heights[n] - heights[n-1]);
        int ss = Integer.MAX_VALUE;
        if(n>1) ss = fJump(n-2, heights) + Math.abs(heights[n] - heights[n-2]);
        return Math.min(fs, ss);
    }
    public int fJump1(int n, int heights[], int dp[]){
        if(n==0) return 0;
        if(dp[n] != -1) return dp[n];

        int fs = fJump(n-1, heights) + Math.abs(heights[n] - heights[n-1]);
        int ss = Integer.MAX_VALUE;
        if(n>1) ss = fJump(n-2, heights) + Math.abs(heights[n] - heights[n-2]);

        return dp[n] = Math.min(fs, ss);
    }
    public int fJump2(int n, int height[], int dp[]){
        dp[0] = 0;

        for(int i=1;i<n;i++){
            int fs = dp[i-1] + Math.abs(height[i] - height[i-1]);
            int ss = Integer.MAX_VALUE;
            if(i>1) ss = dp[i-2] + Math.abs(height[i] - height[i-2]);
            dp[i] = Math.min(fs, ss);
        }

        return dp[n-1];
    }
    public int fJump3(int n, int heights[], int dp[], int k){
        //When frog have k jumps
        if(n==0) return 0;
        int minJump = Integer.MAX_VALUE;

        for(int j=1;j<=k;j++){
            int jump = fJump3(n-j,heights,dp,k) + Math.abs(heights[n] + heights[n-j]);
            minJump = Math.min(minJump, jump);
        }

        return minJump;

    }

    //2] Climbing stairs
    public static int climb(int n){
        return countWays(0,n);
    }
    public static int countWays(int i, int n){
        if(i==0) return 1;
        if(i==1) return 1;

        int left = countWays(i+1,n);
        int right = countWays(i+2, n);
        return left + right;
    }

    //1] Fibunacci
    public static int fibu1(int n){
        if(n<=1){
            return n;
        }

        return fibu1(n-1) + fibu1(n-2);
    }
    public static int fibu2(int n, int dp[]){
        if(n<=1){
            return n;
        }

        if(dp[n] != -1) return dp[n];

        return dp[n] = fibu1(n-1) + fibu1(n-2);
    }
    public static int fibu3(int n, int dp[]){
        dp[0] = 0;
        dp[1] = 1;

        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

}
