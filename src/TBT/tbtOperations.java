package TBT;
import java.util.Scanner;

class treeNode{
    int info;
    treeNode left,right;
    int leftBit,rightBit;

    treeNode(){
        info=0;
        left=right=null;
        leftBit=rightBit=0;
    }
}

class tbt extends treeNode{
    treeNode root;

    tbt(){
        root = new treeNode();
        root.info=0;
        root.left=root;
        root.right=root;
        root.leftBit=0;
        root.rightBit=1;
    }

    public void insert(int data){

    }
}

public class tbtOperations {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        tbt t = new tbt();
        int c, data;
        boolean check = true;
        while (check) {
            System.out.println("1.Insert: ");
            System.out.println("2.Display: ");
            System.out.println("3.Exist: ");
            c = sc.nextInt();
            switch (c) {
                case 1: {
                    System.out.print("Enter element:");
                    data=sc.nextInt();
                    t.insert(data);
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    System.out.println("Error :(");
                }
            }
        }
    }
}
