package DP.LCS;

public class sequencePatternMatching {
    public static void main(String args[]){
        String x = "AXY";
        String y = "ADXCPY";

        int m = x.length();
        int n = y.length();

        System.out.println("Ans : "+spm(x,y,m,n));
    }

    static boolean spm(String x,String y,int m,int n){
        int t[][] = new int[m+1][n+1];

        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0 || j==0){
                    t[i][j]=0;
                }
            }
        }

        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(x.charAt(i-1) == y.charAt(j-1)){
                    t[i][j] = 1+t[i-1][j-1];
                }else{
                    t[i][j] = Integer.max(t[i-1][j],t[i][j-1]);
                }
            }
        }

        if(m==t[m][n]){
            return true;
        }else{
            return false;
        }

    }
}
