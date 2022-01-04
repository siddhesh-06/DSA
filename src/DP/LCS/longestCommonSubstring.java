package DP.LCS;

public class longestCommonSubstring {
    static int t[][];

    longestCommonSubstring(int n,int m){
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
        String x="abcde";
        String y="abfce";

        int n=x.length();
        int m=y.length();

        longestCommonSubstring lc = new longestCommonSubstring(n,m);

        System.out.println("Ans : "+lcSubstring(x,y,n,m));

    }

    static int lcSubstring(String x,String y,int n,int m){
        int max = 0;
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(x.charAt(i-1) == y.charAt(j-1)){
                    t[i][j] = t[i-1][j-1] + 1;
                }else{
                    t[i][j]=0;
                }
                // Storing maximum length of substring
                if(max<t[i][j]){
                    max=t[i][j];
                }
            }
        }

        return max;
    }
}
