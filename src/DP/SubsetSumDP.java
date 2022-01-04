package DP;

public class SubsetSumDP {
   static boolean t[][];

    SubsetSumDP(int n,int cap){
        t = new boolean[n][cap];
    }

    public static void main(String args[]){
        int w[] ={2,3,7,8,10};
        int cap=11;

        SubsetSumDP s = new SubsetSumDP(w.length+1,cap+1);

        for(int i=0;i<w.length+1;i++){
            for(int j=0;j<cap+1;j++){
                if(i==0){
                    t[i][j]=false;
                }
                if(j==0){
                    t[i][j]=true;
                }
            }
        }

        System.out.println("Ans: "+s.subset(w,cap,w.length));

    }

    public boolean subset(int w[],int cap,int n){
        for(int i=1;i<w.length+1;i++){
            for(int j=1;j<cap+1;j++){

                if(w[i-1]<=j){
                    t[i][j] = t[i-1][j-w[i-1]] || t[i-1][j];
                }else{
                    t[i][j]= t[i-1][j];
                }
            }
        }
        return t[n][cap];
    }
}
