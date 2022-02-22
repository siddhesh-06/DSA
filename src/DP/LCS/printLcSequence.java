package DP.LCS;

import java.util.Stack;

public class printLcSequence {
    static int t[][];

    printLcSequence(int m,int n){
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
        String x="abcf";
        String y="abcdaf";

        int m=x.length();
        int n=y.length();
        printLcSequence p = new printLcSequence(m,n);
        System.out.println("Ans : "+printLcs(x,y,m,n));
    }

    static String  printLcs(String x,String y,int m,int n){

        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(x.charAt(i-1) == y.charAt(j-1)){
                    t[i][j] = 1+t[i-1][j-1];
                }else{
                    t[i][j]=Integer.max(t[i-1][j],t[i][j-1]);
                }
            }
        }

//      return t[m][n];
        String ans="";
        int i=m,j=n;
        while(i>0 && j>0){
            if(x.charAt(i-1) == y.charAt(j-1)){
                ans=ans+x.charAt(i-1);
                i--;
                j--;
            }else{
                if(t[i][j-1] > t[i-1][j]){
                    j--;
                }else{
                    i--;
                }
            }
        }

        // Reverse the string
        Stack <Character> s = new Stack<>();
        String temp="";
        for(int k=0;k<ans.length();k++){
            s.push(ans.charAt(k));
        }
        for(int k=0;k<ans.length();k++){
            temp=temp+s.pop();
        }
        return temp;
    }
}
