package DP.MCM;

public class memoMCM {
    static int t[][];

    memoMCM(int n){
        t=new int[n+1][n+1];

        for(int i=0;i<n+1;i++){
            for(int j=0;j<n+1;j++){
                t[i][j] = -1;
            }
        }
    }

    public static void main(String args[]){
        int arr[] = {10,30,5,60};
        memoMCM mm = new memoMCM(arr.length);

        int i=1;
        int j=arr.length-1;

        System.out.println("Ans : "+solveMCM(arr,i,j));
    }

    static int solveMCM(int arr[],int i,int j){
        if(i>=j){
            return 0;
        }

        if(t[i][j] != -1){
            return t[i][j];
        }

        int min = Integer.MAX_VALUE;
        for(int k=i;k<=j-1;k++){
            int temp = solveMCM(arr,i,k) + solveMCM(arr,k+1,j) + (arr[i-1]*arr[k]*arr[j]);
            if(temp<min){
                min=temp;
            }
        }

        return t[i][j] = min;
    }
}
