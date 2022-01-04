package DP;

public class SubsetCountDP {
    static int t[][];

    SubsetCountDP(int n,int sum){
        this.t = new int[n+1][sum+1];
    }

    public static void main(String args[]){
        int arr[] = {2,3,5,6,8,10};
        int sum = 10;

        SubsetCountDP s = new SubsetCountDP(arr.length,sum);

        //Initialize
        for(int i=0;i<arr.length+1;i++){
            for(int j=0;j<sum+1;j++){
                if(i==0){
                    t[i][j]=0;
                }
                if(j==0){
                    t[i][j]=1;
                }
            }
        }

        System.out.println("Ans: "+s.subSetCount(arr,sum));
    }

    public int subSetCount(int[] arr,int sum){

        // i => arr elements
        // j => 0 to 10 (sum)

        for(int i=1;i<arr.length+1;i++){
            for(int j=1;j<sum+1;j++){
                if(arr[i-1]<=j){
                    t[i][j]= t[i-1][j-arr[i-1]] + t[i-1][j];
                }else{
                    t[i][j] = t[i-1][j];
                }
            }
        }
        return t[arr.length][sum];
    }
}
