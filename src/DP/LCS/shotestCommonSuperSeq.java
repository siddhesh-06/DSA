package DP.LCS;

public class shotestCommonSuperSeq {
    static int t[][];

    shotestCommonSuperSeq(int m,int n){
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
        String x="geek";
        String y="eke";
        // ans = geeke = 5 length

        int m=x.length();
        int n=y.length();

        shotestCommonSuperSeq ss = new shotestCommonSuperSeq(m,n);

        System.out.println("Ans : "+scSuperSeq(x,y,m,n));
    }

    static int scSuperSeq(String x,String y,int m,int n){
        int totalLeng = m+n;
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(x.charAt(i-1) == y.charAt(j-1)){
                    t[i][j] = 1+t[i-1][j-1];
                }else{
                    t[i][j] = Integer.max(t[i-1][j],t[i][j-1]);
                }
            }
        }
        int ans = totalLeng-t[m][n];
        return ans;
    }

}
