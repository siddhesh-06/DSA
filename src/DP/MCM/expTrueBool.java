package DP.MCM;


import java.util.HashMap;

public class expTrueBool {
    static public HashMap<String,Integer> mp;

    expTrueBool(){
        mp = new HashMap<>();
    }

    public static void main(String args[]){

        expTrueBool ex = new expTrueBool();
        String s = "T^F&T";
        int i=0;
        int j=s.length()-1;

        System.out.println("Ans : "+solve(s,i,j,true));
    }

    static int solve(String s,int i,int j,boolean isTrue){

        if(i>j) return 0;
        if(i==j){
           if(isTrue == true){
               return 1;
           }else{
               return 0;
           }
        }
        String temp ="";
        temp = temp + Integer.toString(i)+" "+Integer.toString(j)+" "+Boolean.toString(isTrue);

       if(mp.containsKey(temp)){
           return mp.get(temp);
       }


        int res=0;
        for(int k=i+1;k<=j-1;k+=2){
            int lt=solve(s,i,k-1,true);
            int rt=solve(s,i,k-1,false);

            int lf=solve(s,k+1,j,true);
            int rf=solve(s,k+1,j,false);

            switch (s.charAt(k)){
                case '|':
                    if(isTrue == true){
                        res=res+ (lt*rt) +(lf*rt) + (lt*rf);
                    }else {
                        res=res+ (lf*lf);
                    }
                    break;
                case '&':
                    if(isTrue == true){
                        res=res+ (lt*rt);
                    }else{
                        res=res+ (lf*rf) + (lt*rf) + (rt*lf);
                    }
                    break;
                case '^':
                    if(isTrue == true){
                        res=res+ (lt*rf) + (lf*rt);
                    }else{
                        res=res+ (lt*rt) + (lf*rf);
                    }
                    break;
                default:
                    return -1;
            }
        }
        mp.put(temp,res);
        return mp.get(temp);
    }
}










