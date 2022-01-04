package DP.LCS;

public class minNoOfDelIns {
    static int t[][];

    minNoOfDelIns(int m,int n){
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
        String x="heap";
        String y="pea";

        int m= x.length();
        int n= y.length();

        minNoOfDelIns ms = new minNoOfDelIns(m,n);

        // we have to use here subsequence for minimium purpose but using substring
        // count will be max so thats why.
        System.out.println("Total Deletion : "+(m-lcsCount(x,y,m,n)));
        System.out.println("Total Insertion : "+(n-lcsCount(x,y,m,n)));

    }

    static int lcsCount(String x,String y,int m,int n){
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
