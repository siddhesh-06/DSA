package Patterns;

public class patternQuetions {
    public static void main(String[] args) {

    }

    public static void patt5(){
        int z = 3;
        for(int i=1;i<=3;i++){
            for(int k=1;k<=3-i;k++){
                System.out.print(" ");
            }
            for(int j=i;j>=1;j--){
                System.out.print(j);
            }
            for(int j=2;j<=i;j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void patt4(){
        int n = 4;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }

        for(int i=n-1;i>=0;i--){
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void patt3(){
        int n =5;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void patt2(){
        int n = 4;
        for(int i=4;i>=1;i--){
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void patt1(){
        int n = 4;
        int k = 1;
        for(int i=1;i<=4;i++){
            for(int j=1;j<=i;j++){
                System.out.print(k);
                if(i!=j) System.out.print("*");
                k++;
            }
            System.out.println();
        }
        k = k-n;
        int temp = k;
        for(int i=4;i>=1;i--){
            for(int j=1;j<=i;j++){
                System.out.print(temp);
                if(i==j) continue;
                else System.out.print("*");
                temp++;
            }
            System.out.println();
            k = k -(i-1);
            temp = k;

        }
    }
}
