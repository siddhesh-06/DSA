package DP.LCS;

public class memoLCS {
    static int t[][];

    memoLCS(int n,int m){
        t=new int[n+1][m+1];

        for(int i=0;i<n+1;i++){
            for(int j=0;j<m+1;j++){
                t[i][j]=-1;
            }
        }
    }

    public static void main(String args[]){
        String x="abcdgh";
        String y="abedfhr";

        int n=x.length();
        int m=y.length();

        memoLCS ml = new memoLCS(n,m);

        System.out.println("Ans : "+lcs(x,y,n,m));

    }

    public static int lcs(String x,String y, int n, int m){

        if(n==0 || m==0){
            return 0;
        }

        if(t[n][m]!=-1){
            return t[n][m];
        }

        if(x.charAt(n-1)==y.charAt(m-1)){
            t[n][m] = 1+ lcs( x, y,n-1,m-1);
            return t[n][m];
        }else{
            t[n][m] = Integer.max(lcs(x,y,n-1,m),lcs(x,y,n,m-1));
            return t[n][m];
        }
    }


}
