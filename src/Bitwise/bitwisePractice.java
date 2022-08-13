package Bitwise;

public class bitwisePractice {
    public static void main(String args[]){
        noOfDigitInBase(4584,10);
    }

    //10] power of 2 or not

    //9] nth row sum in pascal triangle
    public static void nthRowPascalSum(int n){
        System.out.println(1<<(n-1));
    }

    //8] No of digit in base b
    public static void noOfDigitInBase(int num, int base){
        // Formula : log b(num)+1;
        // eg log 2 10 = 3.32 +1 = 4 digit

        int ans = (int) (Math.log(num)/Math.log(base))+1;
        System.out.println(ans);
    }

    //7] nth magic no
    public static void magicNo(int num){
        int ans = 0;
        int base = 5;

        while (num>0){
            int last = num&1;
            num = num>>1;
            ans += (last*base);
            base = base*5;
        }

        System.out.println(ans);
    }

    //6] Find first set bit from right side
    public static void rightMostSetBit(int num){
        int cnt =1;
        while ((num&1)!=1){
            cnt++;
            num = (num>>1);
        }
        System.out.println(cnt);
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
        System.out.println(num| (1<<(i-1)));
    }

    //3] Find ith bit
    public static void findithBit(int num, int i){
        System.out.println(num& (1<<(i-1)) ); //add i-1 = 0 to right side
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
