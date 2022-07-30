package DP.LCS;

public class longestCommonSubsequece {
    public static void main(String args[]){
        String x="abcdgh";
        String y="abedfhr";

        int n=x.length();
        int m=y.length();

        System.out.println("Ans : "+dpLCS(x,y,n,m));

    }

    //without memo
    static int lcs(String x,String y,int n,int m){
        if(n==0 || m==0) return 0;

        if((x.charAt(n-1)) == (y.charAt(m-1))){
            return 1+lcs(x,y,n-1,m-1);
        }else{
            return Integer.max(lcs(x,y,n-1,m), lcs(x,y,n,m-1));
        }
    }

    static int dpLCS(String x, String y, int n, int m){
        int t[][] = new int[n+1][m+1];

        for(int i=0;i<n+1;i++){
            for(int j=0;j<m+1;j++){
                if(i==0 || j==0){
                    t[i][j] = 0;
                }
            }
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(x.charAt(i-1)==y.charAt(j-1)){
                    t[i][j] = 1 + t[i-1][j-1];
                }else{
                    t[i][j] = Integer.max(t[i-1][j], t[i][j-1]);
                }
            }
        }

        return t[n][m];
    }
}
