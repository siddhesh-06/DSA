package DP.MCM;

public class eggDroppingDP {
    public static void main(String args[]){
        int eggs = 2;
        int f = 6;
        System.out.println("Ans : "+solve(eggs,f));
    }

    static int solve(int eggs,int f){
        if(f==0 || f==1) return f;
        if(eggs == 1) return f;

        int min = Integer.MAX_VALUE;
        for(int k=1;k<=f;k++){
            int temp = 1 + Integer.max(solve(eggs-1,k-1),solve(eggs,f-k));
            min = Integer.min(min,temp);
        }

        return min;
    }
}
