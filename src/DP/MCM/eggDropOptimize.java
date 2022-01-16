package DP.MCM;

import java.util.HashMap;

public class eggDropOptimize {
    static int t[][];

    eggDropOptimize(int e,int f){
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
        eggDropOptimize ee = new eggDropOptimize(e,f);

        System.out.println("Ans : "+ solve(e,f));

    }
    static int solve(int e,int f){
        if(f==0 || f==1) return f;
        if(e==1) return f;

        if(t[e][f]!=-1){
            return t[e][f];
        }

        int min = Integer.MAX_VALUE;
        int ans = min;
        int low=1,high=f;
        while(low<=high){
            int mid = (low+high)/2;
            int left = solve(e-1,mid-1);
            int right = solve(e,f-mid);

            int temp = 1+ Math.max(left,right);
            if(left<right){
                low=mid+1;
            }else{
                high=mid-1;
            }

            ans = Math.min(ans,temp);
        }

        return t[e][f] = ans;
    }
}
