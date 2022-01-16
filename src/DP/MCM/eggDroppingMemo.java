package DP.MCM;

import java.util.HashMap;

public class eggDroppingMemo {
    static int t[][];

    eggDroppingMemo(int e,int f){
        t = new int[e+1][f+1];

        for(int i=0;i<e+1;i++){
            for(int j=0;j<f+1;j++){
                t[i][j]=-1;
            }
        }
    }

    public static void main(String args[]){
        int e = 2;
        int f = 6;
        eggDroppingMemo ee = new eggDroppingMemo(e,f);

        System.out.println("Ans : "+ solve(e,f));

    }
    static int solve(int e,int f){
        if(f==0 || f==1) return f;
        if(e==1) return f;

        if(t[e][f]!=-1){
            return t[e][f];
        }

        int min = Integer.MAX_VALUE;
        int low=0,high=0;
        for(int i=1;i<f+1;i++){
            // storing both calls values
            if(t[e-1][i-1] != -1){
                low = t[e-1][i-1];
            }else{
                low = solve(e-1,i-1);
                t[e-1][i-1] = low;
            }

            if(t[e][f-i] != -1){
                high = t[e][f-i];
            }else{
                high = solve(e,f-i);
                t[e][f-i] = high;
            }


            int temp = 1 + Integer.max(low,high);
            min = Integer.min(min,temp);
        }
        t[e][f] = min;
        return t[e][f];
    }
}
