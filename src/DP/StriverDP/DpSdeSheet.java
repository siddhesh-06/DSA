package DP.StriverDP;

import java.util.*;

public class DpSdeSheet {
    public static void main(String[] args) {

    }

    //9] Coin change : All ways
    public static long countWaysToMakeChange(int denominations[], int value){
        int n = denominations.length;
        long t[][] = new long[n+1][value+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<value+1;j++){
                t[i][j] = -1;
            }
        }
        return findLowestCoins(denominations, n-1, value, t);
    }
    public static long findLowestCoins(int coins[], int n, int amt, long t[][]){
        if(n==0){
            if(amt%coins[0]==0){
                return 1;
            }else{
                return 0;
            }
        }

        if(t[n][amt]!=-1) return t[n][amt];

        long notTaken = findLowestCoins(coins, n-1, amt, t);
        long taken = 0;
        if(coins[n]<=amt){
            taken = findLowestCoins(coins, n, amt-coins[n], t);
        }

        t[n][amt] = notTaken + taken;
        return t[n][amt];
    }

    //8] Minimum sum path in the matrix, (count paths and similar type do, also backtrack to find the Minimum path)
    public int minPathSum(int[][] grid) {
        int m = grid.length-1;
        int n = grid[0].length-1;
        HashMap<String, Integer> hm = new HashMap<>();
        return helper(grid, m, n, hm);
    }
    public int helper(int grid[][], int m, int n, HashMap<String,Integer> hm){
        String key = m+"_"+n;
        if(hm.containsKey(key)) return hm.get(key);

        if(m<0 || n<0) return Integer.MAX_VALUE;
        if(m==0 && n==0) return grid[0][0];

        int val = grid[m][n] + Integer.min(helper(grid,m-1,n,hm),helper(grid,m,n-1,hm));
        hm.put(key, val);
        return hm.get(key);

    }

    //7] Matrix Chain Multiplication

    //6] Maximum sum increasing subsequence

    //5] EDIT distance
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        return dpSolve(word1,word2,m,n);
    }
    public int dpSolve(String s,String t,int m,int n){
        int dp[][] = new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0){
                    dp[i][j] = j;
                }
                if(j==0){
                    dp[i][j] = i;
                }
            }
        }

        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(s.charAt(m-1)==t.charAt(n-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int temp = Math.min(dp[i-1][j], dp[i][j-1]);
                    dp[i][j] = Math.min(temp, dp[i-1][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    //4] 0-1 Knapsack

    //3] Longest common subsequence
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int t[][] = new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0 || j==0){
                    t[i][j] = 1;
                }
            }
        }

        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    t[i][j] = 1 + t[i-1][j-1];
                }else{
                    t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
                }
            }
        }

        return t[m][n]-1;
    }

    //2] Longest increasing subsequence

    //1] Max product subarray
    public int maxProduct(int nums[]){
        int ans=nums[0];
        int min = ans;
        int max = ans;

        for(int i=1;i<nums.length;i++){
            if(nums[i]<0){
                //swap
                max = max^min;
                min = max^min;
                max = max^min;
            }
            max = Math.max(nums[i], max*nums[i]);
            min = Math.min(nums[i], min*nums[i]);
            ans = Math.max(ans, max);
        }

        return ans;

    }
}
