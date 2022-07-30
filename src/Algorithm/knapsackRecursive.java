package Algorithm;

public class knapsackRecursive {
    //Dp array
    static int t[][] = new int[5][8];

    public static void main(String args[]){
        int w[] ={1,3,4,5};
        int v[] ={1,4,5,7};
        int cap=7;
        int n=4;

        //Assign to -1
//        for(int i=0;i<n+1;i++){
//            for(int j=0;j<cap+1;j++){
//                t[i][j]=-1;
//            }
//        }

        knapsackRecursive k =new knapsackRecursive();
        System.out.println("Ans: "+k.recursiveKnap(w,v,cap,n));


    }

    public int recursiveKnap(int[] w, int[] v, int cap, int n){
        if(n==0 || cap==0){ //base condition
            return 0;       //op for bc
        }

//        if(t[n][cap]!=-1){
//            return t[n][cap];
//        }

        if(w[n-1]<=cap){
            return  Math.max(v[n-1]+recursiveKnap(w,v,cap-w[n-1],n-1),recursiveKnap(w,v,cap,n-1));
//            return t[n][cap];
        }else {
            return recursiveKnap(w, v, cap, n-1);
//            return t[n][cap];
        }

    }

}
