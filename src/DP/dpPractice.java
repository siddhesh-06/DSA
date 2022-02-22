package DP;

public class dpPractice {
    public static void main(String args[]){
        int price[] = {10,20,30};
        System.out.println(buSell2Tran(price));
    }

    static int buSell2Tran(int price[]){
        //Moving to right
        int mpist = 0;
        int leafp = price[0];
        int dpr[] = new int[price.length];

        for(int i=1;i<price.length;i++){
            if(price[i]<leafp){
                leafp = price[i];
            }
            mpist = price[i] - leafp;
            if(mpist>dpr[i-1]){
                dpr[i] = mpist;
            }else{
                dpr[i] = dpr[i-1];
            }
        }

        //Moving to left
        int maxbt = 0;
        int maxat = price[price.length-1];
        int dpl[] = new int[price.length];

        for (int i = price.length-2;i>=0;i--){
            if(price[i]>maxbt){
                maxat = price[i];
            }
            mpist = maxat - price[i];
            if(mpist>dpl[i+1]){
                dpl[i] = mpist;
            }else{
                dpl[i] = dpl[i+1];
            }
        }
        int op = 0;
        for(int i=0;i<price.length;i++){
            if(op< dpl[i]+dpr[i]){
                op = dpl[i] + dpr[i];
            }
        }
        return op;
    }


    // For one transaction
    static int buSellProfit(int val[]){
        int lsf = Integer.MAX_VALUE;
        int cp = 0;
        int op = 0;

        for(int i=0;i<val.length;i++){
            if(val[i]<lsf){
                lsf = val[i];
            }
            cp = val[i] + lsf;

            if(cp>op){
                op=cp;
            }
        }
        return op;
    }
}
