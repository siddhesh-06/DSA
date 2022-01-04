package DP;

public class rodCuttingDP {
    static int t[][];

    rodCuttingDP(int n,int length){
        t = new int[n+1][length+1];
    }

    public static void main(String args[]){
        int n=7; // road size
        int rod[] = new int[n];
        for(int i=0;i<n;i++){
            rod[i]=i+1;
        }

        //int rod[] ={1,2,3,4,5,6,7};
        int price[] ={1,3,3,3,4,4,6};

        rodCuttingDP rd =new rodCuttingDP(rod.length+1,price.length+1);

        for(int i=0;i<rod.length+1;i++){
            for(int j=0;j<price.length+1;j++){
                if(i==0 || j==0){
                    t[i][j]=0;
                }
            }
        }
        System.out.println("Ans : "+rd.maxRodPrice(rod,price,n));

    }

    public int maxRodPrice(int rod[],int price[],int n){
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(rod[i-1]<=j){
                    t[i][j]= Integer.max(price[i-1]+t[i][j-rod[i-1]],t[i-1][j]);
                }else{
                    t[i][j] = t[i-1][j];
                }
            }
        }

        return t[n][n];
    }
}
