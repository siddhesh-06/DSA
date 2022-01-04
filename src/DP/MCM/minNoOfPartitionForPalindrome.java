package DP.MCM;

public class minNoOfPartitionForPalindrome {
    static int t[][];
    minNoOfPartitionForPalindrome(int n){
        t=new int[n+1][n+1];

        for(int i=0;i<n+1;i++){
            for(int j=0;j<n+1;j++){
                t[i][j] = -1;
            }
        }

    }
    public static void main(String args[]){
        String s = "nitik";

        minNoOfPartitionForPalindrome mn = new minNoOfPartitionForPalindrome(s.length());

        int i=0;
        int j=s.length()-1;

        System.out.println("Ans : "+ noOfPartition(s,i,j));
    }

    static boolean isPalindrome(String s,int i,int j){
        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    static int noOfPartition(String s,int i,int j){
        if(i>=j) {
            return 0;
        }
        if(isPalindrome(s,i,j) == true){
            return 0;
        }

        if(t[i][j] != -1){
            return t[i][j];
        }

        int min = Integer.MAX_VALUE;
        int left,right=0;
        for(int k=i;k<=j-1;k++){
            if(t[i][k] != -1){ // first left part
                left=t[i][k];
            }else{
                left=noOfPartition(s,i,k);
                t[i][k] = left;
            }

            if(t[k+1][j] != -1){ // second right part
                right=t[k+1][j];
            }else{
                right=noOfPartition(s,k+1,j);
                t[k+1][j] = right;
            }

            int temp = 1+left+right;
            min = Integer.min(min,temp);
        }
        return t[i][j] = min;
    }
}

