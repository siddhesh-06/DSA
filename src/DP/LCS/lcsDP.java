package DP.LCS;

public class lcsDP {
    static int t[][];

    lcsDP(int n,int m){
        t=new int[n+1][m+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<m+1;j++){
                if(i==0 || j==0){
                    t[i][j]=0;
                }
            }
        }
    }

    public static void main(String args[]){
        String x="axc";
        String y="ahbgdc";

        int n=x.length();
        int m=y.length();

        lcsDP ml = new lcsDP(n,m);

        System.out.println("Ans : "+lcs(x,y,n,m));
    }

    static boolean lcs(String x,String y,int n,int m){
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(x.charAt(i-1) == y.charAt(j-1)){
                    t[i][j] =  1+t[i-1][j-1];
                }else{
                    t[i][j] =  Integer.max(t[i-1][j],t[i][j-1]);
                }
            }
        }
        if(t[n][m]>0){
            return true;
        }else {
            return false;
        }
       // return t[n][m];
    }
}

















