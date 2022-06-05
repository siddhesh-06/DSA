package DP.StriverDP;

import java.util.Arrays;

public class striverDP {
    public static void main(String args[]){
        int nums1[] = {1,2};
        int nums2[] = {3,4};

        int ans[] = new int[nums1.length+nums2.length];
        int j=0;
        for(int i=0;i<nums1.length;i++){
            ans[j] = nums1[i];
            j++;
        }
        for(int i=0;i<nums2.length;i++){
            ans[j] = nums2[i];
            j++;
        }

        Arrays.sort(ans);
        int n = ans.length-1;
        if(ans.length%2==0){
            int id1 = ans[n/2];
            int id2 = ans[(n/2)-1];

            System.out.println((id1+id2));

        }else{
            double res = 0.0;
            res = (double)ans[(n+1/2)];
            System.out.println(res/2);
        }
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
