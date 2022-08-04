package PriorityQueue;

import java.util.Scanner;

class priorityQueue{
    int front,rear;
    int arr[] = new int[5];

    priorityQueue(){
        front = -1;
        rear = -1;
    }

    void insert(int input){
        int j=0;
        if(rear==5-1){
            System.out.println("Overflow");
        }else{
            if(front==-1){
                front=0;
                rear=0;
                System.out.println(rear);
                arr[rear]=input;
            }else{
                // Insertion sort
                rear=rear+1;
                arr[rear]=input;
                j=rear-1;
                while ( j>=front && arr[j]>input){
                    arr[j+1]=arr[j];
                    j--;
                }
                arr[j+1]=input;
            }
        }
    }

    void delete(){
        if(front == -1 || rear == -1){
            System.out.println("Queue is empty");
        }else{
            front++; //remove top priority element
        }
    }

    void display(){
        for (int i=front;i<=rear;i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println(" ");
    }

}


public class priorityQueueOper {
    public static void main(String args[]){
        boolean check=true;
        int n,input;
        Scanner sc=new Scanner(System.in);
        priorityQueue p =new priorityQueue();
        while (check){
            System.out.println("1.Insert");
            System.out.println("2.Delete");
            System.out.println("3.Display");
            n =  sc.nextInt();

            switch (n){
                case 1:{
                    System.out.print("Enter value: ");
                    input = sc.nextInt();
                    p.insert(input);
                    break;
                }
                case 2:{
                    p.delete();
                    break;
                }
                case 3:{
                    p.display();
                    break;
                }
                default:{
                    System.out.println("Error");
                }
            }
        }
    }
}
