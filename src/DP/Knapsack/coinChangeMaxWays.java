package DP.Knapsack;

public class coinChangeMaxWays {
    static int t[][];

    coinChangeMaxWays(int coinsSize,int sum){
        t=new int[coinsSize+1][sum+1];
    }

    public static void main(String args[]){
        int coins[] = {1,2,5};
        int sum=5;

        coinChangeMaxWays cm = new coinChangeMaxWays(coins.length,sum);

        for(int i=0;i<coins.length+1;i++){
            for(int j=0;j<sum+1;j++){
                if(i==0){ // row will => 0
                    t[i][j]=0;
                }
                if(j==0){ //col will => 1
                    t[i][j]=1;
                }
            }
        }

        System.out.println("No of ways : "+cm.maxWays(coins,sum));
    }

    public int maxWays(int coins[],int sum){

        for(int i=1;i<coins.length+1;i++){
            for(int j=1;j<sum+1;j++){
                if(coins[i-1]<=j){
                    t[i][j]= t[i][j-coins[i-1]] + t[i-1][j];
                }else{
                    t[i][j]= t[i-1][j];
                }
            }
        }

        return t[coins.length][sum];
    }
}
