package DP.LCS;

public class minNoOfDelForPalindrome {
    static int t[][];
    minNoOfDelForPalindrome(int m,int n){
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
        String x = "abc";
        String y = "";

        for(int i=x.length()-1;i>=0;i--){
            y=y+x.charAt(i);
        }

        int m=x.length();
        int n=y.length();
        minNoOfDelForPalindrome mp = new minNoOfDelForPalindrome(m,n);

        System.out.println("No of deletion :"+minNoDeletion(x,y,m,n));
    }

    static int minNoDeletion(String x,String y,int m,int n){
        // Is also use for min no of insertion to make palindrome
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(x.charAt(i-1) == y.charAt(j-1)){
                    t[i][j] = 1+t[i-1][j-1];
                }else{
                    t[i][j] = Integer.max(t[i-1][j],t[i][j-1]);
                }
            }
        }

        return m-t[m][n];
    }
}












