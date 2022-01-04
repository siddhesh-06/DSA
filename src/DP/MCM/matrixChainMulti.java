package DP.MCM;

public class matrixChainMulti {
    public static void main(String args[]){
        int arr[] = {10,30,5,60};

        int i=1;
        int j=arr.length-1;

        System.out.println("Ans : "+solveMCM(arr,i,j));
    }

    static int solveMCM(int arr[],int i,int j){
        if(i>=j){
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for(int k=i;k<=j-1;k++){
            int temp = solveMCM(arr,i,k) + solveMCM(arr,k+1,j) + (arr[i-1]*arr[k]*arr[j]);

            min = Integer.min(min,temp);
        }

        return min;
    }
}
