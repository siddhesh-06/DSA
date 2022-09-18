package DP.StriverDP;
import java.util.*;

public class Stocks {
    public static void main(String[] args) {

    }

    // 4. Stocks BUY SELL K times : same as 3


    // 3. Stcoks BUY SELL at most 2 transaction
    // Recursion
    public int maxProfit3(int prices[]){
        // You can buy sell anytime
        int n = prices.length;

        return helper2(0, 1, 2, prices);
    }
    public int helper2(int ind, int buy,int cap, int prices[]){
        if(ind==prices.length){
            return 0; // you haven't generate any profit
        }

        int profit = 0;
        if(buy==1){
            int not = 0 + helper2(ind+1, 1, cap, prices);
            int take = -prices[ind] + helper2(ind+1, 0, cap, prices);
            profit = Math.max(not, take);
        }else{
            int not = 0 + helper2(ind+1, 0, cap-1, prices);
            int take = prices[ind] + helper2(ind+1, 1, cap, prices);
            profit = Math.max(not, take);
        }

        return profit;
    }
    // DP
    public int maxProfit3DP(int prices[]){
        // You can buy sell anytime
        int n = prices.length;
        int cap = 2;

        int dp[][][] = new int[n+1][2][cap+1]; // why? +1 for base case

        for(int i=0;i<n+1;i++){
            for(int j=0;j<2;j++){
                dp[i][j][0] = 0;
            }
        }

        for(int i=0;i<2;i++){
            for(int j=0;j<cap+1;j++){
                dp[0][i][j] = 0;
            }
        }

        for(int ind=n-1;ind>=0;ind--){
            for(int buy=0;buy<2;buy++){
                for(int c=1;c<cap+1;c++){
                    int profit = 0;

                    if(buy==1){
                        int not = 0 + dp[ind+1][1][cap];
                        int take = -prices[ind] + dp[ind+1][0][cap];
                        profit = Math.max(not, take);
                    }else{
                        int not = 0 + dp[ind+1][0][cap-1];
                        int take = prices[ind] + dp[ind+1][1][cap];
                        profit = Math.max(not, take);
                    }
                    dp[ind][buy][cap] = profit;
                }
            }
        }

        return dp[0][1][2];

    }
    // SPACE-OPT
    public int maxProfit3SPACE(int prices[]){
        // You can buy sell anytime
        int n = prices.length;
        int cap = 2;

        int ahed[][] = new int[2][cap+1];
        int curr[][] = new int[2][cap+1];

        for(int ind=n-1;ind>=0;ind--){
            for(int buy=0;buy<2;buy++){
                for(int c=1;c<cap+1;c++){
                    int profit = 0;

                    if(buy==1){
                        int not = 0 + ahed[1][cap];
                        int take = -prices[ind] + ahed[0][cap];
                        profit = Math.max(not, take);
                    }else{
                        int not = 0 + ahed[0][cap-1];
                        int take = prices[ind] + ahed[1][cap];
                        profit = Math.max(not, take);
                    }
                    curr[buy][cap] = profit;
                }
            }
            ahed = curr.clone();
        }

        return ahed[1][2];

    }


    // 2. Stocks BUY SELL - 2
    // Recursion
    public int maxProfit2(int prices[]){
        // You can buy sell anytime
        int n = prices.length;

        return helper(0, 1,prices);
    }
    public int helper(int ind, int buy, int prices[]){
        if(ind==prices.length){
            return 0; // you haven't generate any profit
        }

        int profit = 0;
        if(buy==1){
            int not = 0 + helper(ind+1, 1, prices);
            int take = -prices[ind] + helper(ind+1, 0, prices);
            profit = Math.max(not, take);
        }else{
            int not = 0 + helper(ind+1, 0, prices);
            int take = prices[ind] + helper(ind+1, 1, prices);
            profit = Math.max(not, take);
        }

        return profit;
    }
    // DP
    public int maxProft2REC(int prices[]){
        int n = prices.length;

        int dp[][] = new int[n+1][2];
        dp[0][0] = dp[0][1] = 0;

        for(int ind=n-1;ind>=0;ind--){
            for(int buy=0;buy<2;buy++){
                int profit = 0;
                if(buy==1){
                    int not = 0 + dp[ind+1][1];
                    int take = -prices[ind] + dp[ind+1][0];
                    profit = Math.max(not, take);
                }else{
                    int not = 0 + dp[ind+1][0];
                    int take = prices[ind] + dp[ind+1][1];
                    profit = Math.max(not, take);
                }

                dp[ind][buy] = profit;
            }
        }

        return dp[0][1];
    }
    // SPACE-OPT
    public int maxProfitSPCAE(int prices[]){
        int n = prices.length;

        int ahead[] = new int[2];
        int curr[] = new int[2];
        ahead[0] = ahead[1] = 0;

        for(int ind=n-1;ind>=0;ind--){
            for(int buy=0;buy<2;buy++){
                int profit = 0;
                if(buy==1){
                    int not = 0 + ahead[1];
                    int take = -prices[ind] + ahead[0];
                    profit = Math.max(not, take);
                }else{
                    int not = 0 + ahead[0];
                    int take = prices[ind] + ahead[1];
                    profit = Math.max(not, take);
                }

                curr[buy] = profit;
            }

            ahead = curr.clone();
        }

        return ahead[1];
    }


    // 1. Stocks BUY SELL - 1
    public int maxProfit1(int prices[]){
        int n = prices.length;
        int minPrice = prices[0], maxP = 0;
        for(int i=1;i<n;i++){
            if(prices[i]<minPrice){
                minPrice = prices[i];
            }else{
                maxP = Math.max(maxP, prices[i]-minPrice);
            }
        }

        return maxP;
    }

}


















