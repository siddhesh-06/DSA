package DP.Knapsack;

public class miniNoOfCoins {
    static int t[][];

    miniNoOfCoins(int coinSize,int sum){
        t=new int[coinSize+1][sum+1];
    }

    public static void main(String args[]){
        int coins[] = {2};
        int sum=3;

        miniNoOfCoins mn = new miniNoOfCoins(coins.length,sum);

        // Initialize
        for(int i=0;i<coins.length+1;i++){
            for(int j=0;j<sum+1;j++){
                if(j==0){
                    t[i][j]=0;
                }
                if(i==0){
                    t[i][j]= Integer.MAX_VALUE-1;
                }
            }
        }

        for(int j=1;j<sum+1;j++){
            if(j%coins[0]==0){
                t[1][j]=j/coins[0];
            }else{
                t[1][j]=-1;
            }
        }

        System.out.println("Min coins are : "+ mn.minCoinsRequired(coins,sum));
    }

    public int minCoinsRequired(int coins[],int sum){
        for(int i=2;i<coins.length+1;i++){
            for(int j=1;j<sum+1;j++){
                if(coins[i-1]<=j){
                    t[i][j] = Integer.min(1+t[i][j-coins[i-1]],t[i-1][j]);
                }else{
                    t[i][j]=t[i-1][j];
                }
            }
        }
        return t[coins.length][sum];
    }
}
