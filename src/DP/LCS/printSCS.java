package DP.LCS;

import java.util.Stack;

public class printSCS {
    static int t[][];
    printSCS(int m,int n){
        t=new int[m+1][n+1];

        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0 || j==0){
                    t[i][j] = 0;
                }
            }
        }

    }
    public static void main(String args[]){
        String x = "acbcf";
        String y = "abcdaf";

        int m=x.length();
        int n=y.length();

        printSCS ps = new printSCS(m,n);

        System.out.println("Ans : "+print(x,y,m,n));

    }

    static String reverseString(String temp){
        Stack<Character> s = new Stack<>();
        for(int i=0;i<temp.length();i++){
            s.push(temp.charAt(i));
        }
        String ans="";
        for(int i=0;i<temp.length();i++){
            ans=ans+s.pop();
        }
        return ans;
    }

    static String print(String x,String y,int m,int n){
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(x.charAt(i-1) == y.charAt(j-1)){
                    t[i][j] = 1+t[i-1][j-1];
                }else{
                    t[i][j] = Integer.max(t[i-1][j],t[i][j-1]);
                }
            }
        }

        String temp="";
        int i=m,j=n;
        while(i>0 && j>0){
            if(x.charAt(i-1) == y.charAt(j-1)){
                temp=temp+x.charAt(i-1);
                i--;
                j--;
            }else{
                if(t[i][j-1] > t[i-1][j]){
                    temp=temp+y.charAt(j-1);
                    j--;
                }else if(t[i-1][j]>t[i][j-1]){
                    temp=temp+x.charAt(i-1);
                    i--;
                }
            }
        }

        while(i>0){
            temp=temp+x.charAt(i-1);
            i--;
        }

        while(j>0){
            temp=temp+y.charAt(j-1);
            j--;
        }


        return  reverseString(temp);

    }
}








