package DP.LCS;

import java.util.Stack;

public class lpsDP {
    static int t[][];

    lpsDP(int m,int n){
        t=new int[m+1][n+1];

        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0 || j==0){
                    t[i][j]=0;
                }
            }
        }

    }

    public static void main(String args[]){
        String x = "agbcba";
        String y = "";

        for(int i=x.length()-1;i>=0;i--){
            y=y+x.charAt(i);
        }

        int m=x.length();
        int n=y.length();

        lpsDP lp = new lpsDP(m,n);

        System.out.println("Ans : "+lps(x,y,m,n));
    }

    static int lps(String x,String y, int m,int n){
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(x.charAt(i-1) == y.charAt(j-1)){
                    t[i][j] = 1+t[i-1][j-1];
                }else{
                    t[i][j] = Integer.max(t[i-1][j],t[i][j-1]);
                }
            }
        }

        return t[m][n];
    }
}
