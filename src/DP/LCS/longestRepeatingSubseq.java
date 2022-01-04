package DP.LCS;

public class longestRepeatingSubseq {

    public static void main(String args[]){
        String x = "AABEBCDD";
        String y = x;

        int m=x.length();
        int n=y.length();

        System.out.println("Ans : "+lrs(x,y,m,n));
    }

    static int lrs(String x,String y,int m,int n){
        int t[][] = new int[m+1][n+1];

        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if( i==0 || j==0 ){
                    t[i][j]=0;
                }
            }
        }

        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(x.charAt(i-1) == y.charAt(j-1) && i!=j ){
                    t[i][j] = 1+t[i-1][j-1];
                }else{
                    t[i][j] = Integer.max(t[i-1][j],t[i][j-1]);
                }
            }
        }

        return t[m][n];
    }

}
