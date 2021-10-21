package Stack;

import java.util.Scanner;

class twoStack{
    private int arr[];
    private int top1,top2;

    twoStack(int n){
        arr = new int[n];
        top1 = (arr.length/2)+1;
        top2 = arr.length/2;
    }

    public void push1(int data){
        if(top1<0){
            top1--;
            arr[top1] = data;
        }else{
            System.out.println("Overflow");
        }
    }

    public void push2(int data){

    }

    public void pop1(){

    }

    public void pop2(){

    }

    public void display1(){

    }
    public void display2(){

    }
}


public class array2Stack {
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter array size: ");
        int arrSize = sc.nextInt();
        twoStack ts = new twoStack(arrSize);
        boolean check=true;
        int c,input;
        while(check){
            System.out.println("1.Insert at stack1");
            System.out.println("2.Insert at stack2");
            System.out.println("3.Delete from stack1");
            System.out.println("4.Delete from stack2");
            System.out.println("5.Display");
            System.out.println("6.Exist");
            c= sc.nextInt();
            switch (c){
                case 1:
                    System.out.println("Enter data: ");
                    input=sc.nextInt();
                    ts.push1(input);
                    break;
                case 2:
                    System.out.println("Enter data: ");
                    input=sc.nextInt();
                    ts.push2(input);
                    break;
                case 3:
                    ts.pop1();
                    break;
                case 4:
                    ts.pop2();
                    break;
//                case 5:
//                    break;
                case 5:
                    check=false;
                    break;
                default:
                    System.out.println("Error :(");
            }

        }
    }
}
