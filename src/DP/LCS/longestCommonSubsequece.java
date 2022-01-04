package DP.LCS;

public class longestCommonSubsequece {
    public static void main(String args[]){
        String x="abcdgh";
        String y="abedfhr";

        int n=x.length();
        int m=y.length();

        System.out.println("Ans : "+lcs(x,y,n,m));

    }

    static int lcs(String x,String y,int n,int m){
        if(n==0 || m==0) return 0;

        if((x.charAt(n-1)) == (y.charAt(m-1))){
            return 1+lcs(x,y,n-1,m-1);
        }else{
            return Integer.max(lcs(x,y,n-1,m), lcs(x,y,n,m-1));
        }
    }
}
