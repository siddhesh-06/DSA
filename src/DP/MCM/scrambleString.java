package DP.MCM;

import java.util.HashMap;

public class scrambleString {
    static HashMap<String,Boolean> hs = new HashMap<>();
    public static void main(String args[]){
        String a = "great";
        String b = "rgeat";


        if(a.length()==b.length()){
            boolean ans=solve(a,b);
            System.out.println(ans);
        }else{
            System.out.println("false");
        }
    }

    static boolean solve(String a,String b){
        if(a==" " && b==" ") return true;
        int m = a.length();
        if(a.equals(b)) return true;
        if(a.length()<=1 || b.length()<=1) return false;

        String temp = a+"_"+b;
        if(hs.containsKey(temp)){
            return hs.get(temp);
        }

        int n = a.length();
        boolean flag = false;

        for(int i=1;i<=n-1;i++){

            boolean cod1 = (solve(a.substring(0,i),b.substring(i,n))) && (solve(a.substring(i,n),b.substring(0,i)));
            boolean cod2 = (solve(a.substring(0,i),b.substring(0,i)) ) && (solve(a.substring(i,n),b.substring(i,n)));
            if(cod1 || cod2){
                flag=true;
                break;
            }
        }
        hs.put(temp,flag);
        return flag;
    }
}
