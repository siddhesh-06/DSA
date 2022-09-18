package DP.StriverDP;

public class Knapsack {
    public static void main(String args[]){

    }
    //Rod cutting

    //Space-Opti
    public static int cutRodDPSPACE(int price[], int n) {
        int m = price.length;
        // Use only horizontal row
        int prev[] = new int[n+1];
        int curr[] = new int[n+1];

        for(int i=0;i<n+1;i++){
            prev[i] = i*price[0];
        }

        for(int i=1;i<m;i++){
            for(int j=0;j<n+1;j++){
                int not = 0+ prev[j];
                int take = Integer.MIN_VALUE;
                int rodl = i+1;
                if(rodl<=j){
                    take = price[i] + curr[j-rodl];
                }

                curr[j] = Math.max(not, take);
            }
            prev = curr.clone();
        }

        return prev[n];
    }

    //DP
    public static int cutRodDP(int price[], int n) {
        int m = price.length;

        int dp[][] = new int[m][n+1]; // n+1 why? extra for base case

        for(int i=0;i<n+1;i++){
            dp[0][i] = i*price[0];
        }

        for(int i=1;i<m;i++){
            for(int j=0;j<n+1;j++){
                int not = 0+ dp[i-1][j];
                int take = Integer.MIN_VALUE;
                int rodl = i+1;
                if(rodl<=j){
                    take = price[i] + dp[i][j-rodl];
                }

                dp[i][j] = Math.max(not, take);
            }
        }

        return dp[m-1][n];
    }

    //Recursive
    public static int rodREC(int price[], int n, int ind){  // n = n, ind = n-1
        //n = 5 , price = [2,5,7,8,10]
        if(ind==0){               //  [1,1,1,1,1]
            return (n*price[0]); // [2,2,2,2,2] = 2*5 = 10 , if n=5 then price will be
        }

        int not = 0 + rodREC(price, n, ind-1);

        int rodL = ind+1;

        int take = Integer.MIN_VALUE;
        if(rodL<=n){
            take = price[ind] + rodREC(price, n-rodL, ind);
        }

        return Math.max(not, take);
    }

    //==============================================================================================


    //Unbounded knapsack

    //Space-Opti
    public static int unboundedKnapsack(int l, int w, int[] profit, int[] weight) {
        int n = weight.length;
        int dp[][] = new int[n][w+1];

        int prev[] = new int[w+1];
        int curr[] = new int[w+1];

        for(int i=0;i<w+1;i++){
            prev[i] = (i/weight[0])*profit[0];
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<w+1;j++){
                int not = prev[j];
                int take = Integer.MIN_VALUE;
                if(weight[i]<=j){
                    take = profit[i] + curr[j-weight[i]];
                }

                curr[j] = Math.max(take, not);
            }
            prev = curr.clone();
        }

        return curr[w];
    }
    //DP
    public static int unboundedKnapsackDP(int l, int w, int[] profit, int[] weight) {
        int n = weight.length;
        int dp[][] = new int[n][w+1];

        for(int i=0;i<w+1;i++){
            dp[0][i] = (i/weight[0])*profit[0];
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<w+1;j++){
                int not = dp[i-1][j];
                int take = Integer.MIN_VALUE;
                if(weight[i]<=j){
                    take = profit[i] + dp[i][j-weight[i]];
                }

                dp[i][j] = Math.max(take, not);
            }
        }

        return dp[n-1][w];
    }
    //Recursive
    public static int unboundedREC(int wt[], int val[], int ind, int cap){
        if(ind==0){
            return ((cap/wt[0])*val[0]);
        }

        int notTake = 0+ unboundedREC(wt, val, ind-1, cap);
        int take = Integer.MIN_VALUE;
        if(wt[ind]<=cap){
            take =val[ind]+ unboundedREC(wt, val, ind, cap-wt[ind]);
        }

        return Math.max(take, notTake);
    }


    //==============================================================================================


    //Bounded knapsack

    //Space-Opt
    public int knapsackSPACE(int wt[], int val[], int cap, int ind){
        int n = wt.length;

        int[] prev = new int[cap+1];
        int[] curr = new int[cap+1];

        for(int i=wt[0];i<=cap;i++){
            prev[i] = val[0];
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<cap+1;j++){
                int notTaken = 0 + prev[j];
                int taken = Integer.MIN_VALUE;
                if(wt[i]<=j){
                    taken = val[ind] + prev[j-wt[i]];
                }
                curr[j] = Math.max(taken,notTaken);
            }
            prev = curr.clone();
        }

        return prev[cap];
    }
    // DP
    public int knapsackDP(int wt[], int val[], int cap, int ind){
        int n = wt.length;
        int dp[][] = new int[n][cap+1];

        for(int i=wt[0];i<=cap;i++){
            dp[0][i] = val[0];
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<cap+1;j++){
                int notTaken = 0 + dp[i-1][j];
                int taken = Integer.MIN_VALUE;
                if(wt[i]<=j){
                    taken = val[ind] + dp[i-1][j-wt[i]];
                }
                dp[i][j] = Math.max(taken,notTaken);
            }
        }

        return dp[n-1][cap];
    }
    //Recursion
    public int knapsack(int wt[], int val[], int cap, int ind){
        if(ind==0){
            if(wt[0]<=cap) return val[0];
            else return 0;
        }

        int notTake = 0 + knapsack(wt, val, cap, ind-1);
        int taken = Integer.MIN_VALUE;
        if(wt[ind]<=cap){
            taken = val[ind] + knapsack(wt, val, cap-wt[ind], ind);
        }

        return Math.max(notTake,taken);
    }
}
