package CircularQueue;

import java.util.ArrayList;
import java.util.Scanner;

class CircQueue{
    private int size,top,rear,arr[];

    ArrayList<Integer> a =new ArrayList<>();

    CircQueue(int size){
        this.size=size;
        arr = new int[size];
        this.top=-1;
        this.rear=-1;
    }

    public void enQueue(int data){

        if( (top==-1 && rear==size-1) || ( rear==(top-1)%(size-1) )){
            // 1] overflow
            System.out.println("Overflow");
        }else if(top==-1){
            // 2] first insertion
            top=0;
            rear=0;
            arr[rear] = data;
        }else if(rear==size-1 && top!=0){
            // 3] if rear goes to end & top not at 0
            rear=0;
            arr[rear] = data;
        }else{
            // 4] regular
            rear++;
            arr[rear] =data;
        }
    }
    public void deQueue(){

        if(top==-1){
            // 1] overflow
            System.out.println("Overflow");
        }
        if(top==rear){
            // 2] first insertion case
            top=rear=-1;
        }else if(top==size-1){
            // 3] last pointer condition
            top=0;
        }else{
            // 4] regular
            top++;
        }
    }

    public void display(){
        if(top==-1){
            System.out.println("Queue is empty");
        }else{
            if(rear>=top){
                for(int i =top;i<=rear;i++){
                    System.out.print(arr[i]+", ");
                }
                System.out.println(" ");
            }else{
                for(int i =top;i<size;i++){
                    System.out.print(arr[i]+", ");
                }

                for(int i =0;i<=rear;i++){
                    System.out.print(arr[i]+", ");
                }
                System.out.println();
            }
        }
    }
}

public class circularQueue {
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter array size: ");
        int arrSize = sc.nextInt();
        CircQueue cq = new CircQueue(arrSize);
        boolean check=true;
        int c,input;

        while(check){
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Display");
            System.out.println("4. Exist");
            c=sc.nextInt();
            switch(c){
                case 1:{
                    System.out.println("Enter element: ");
                    input=sc.nextInt();
                    cq.enQueue(input);
                    break;
                }
                case 2:{
                    cq.deQueue();
                    break;
                }
                case 3:{
                    cq.display();
                    break;
                }
                case 4:{
                    check=false;
                    break;
                }
                default:{
                    System.out.println("Error :(");
                }
            }
        }
    }
}
