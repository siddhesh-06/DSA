package Bitwise;

public class bitwisePractice {
    public static void main(String args[]){
        resetithBit(10,2);
    }

    //5] Reset ith bit
    public static void resetithBit(int num, int i){
        //reset : 0 -> 0, 1 -> 1
        int a = ~(1<<(i-1));
        System.out.println(num&a);
    }

    //4] Set ith bit
    public static void setithBit(int num, int i){
        //set : 0 -> 1 , 1 -> 1
        System.out.println(num|1<<(i-1));
    }

    //3] Find ith bit
    public static void findithBit(int num, int i){
        System.out.println(num&1<<(i-1)); //add i-1 = 0 to right side
    }

    //2] XOR
    public static void findSingle(int nums[]){
        int ans = 0;
        for(int val : nums){
            ans = ans ^ val;
        }
        System.out.println(ans);
    }

    //1] Find odd or even
    public static void oddOrEven(int a){
        if((a&1)==1){
            System.out.println("Odd");
        }else{
            // if there is  0
            System.out.println("Even");
        }
    }
}
