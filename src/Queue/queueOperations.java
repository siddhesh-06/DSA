package Queue;

import java.util.Scanner;

class node{
    int data;
    node next;
}

class queue{
    node top,rear;
    queue(){
        top=null;
        rear=null;
    }

    public void enqueue(int info){
        node n=new node();
        n.data=info;

        if(isEmpty()){
            top=n;
            rear=n;
        }else{
            n.next=top;
            top=n;
        }
    }

    public void dequeue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
        }else {
            node p=top;
            node q=null;

            while(p.next!=null){
                q=p;
                p=p.next;
            }
            q.next=null;
            rear=q;
        }
    }


    public int isRear(){
        return rear.data;
    }


    public boolean isEmpty(){
        if(top==null && rear==null){
            return true;
        }
        return false;
    }


    public void display(){
        if(isEmpty()){
            System.out.println("Queue is empty");
        }else{
            node temp=top;
            while(temp!=null){
                System.out.print(temp.data+",");
                temp=temp.next;
            }
            System.out.println(" ");
        }
    }

}

public class queueOperations{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        queue q=new queue();
        boolean check=true;
        int c,info;
        while(check){
            System.out.println("1.Enqueue");
            System.out.println("2.Dequeue");
            System.out.println("3.Top element");
            System.out.println("4.Display");
            System.out.println("5.Exist");
            c=sc.nextInt();

            switch (c){
                case 1:{
                    System.out.print("Enter input: ");
                    info=sc.nextInt();
                    q.enqueue(info);
                    break;
                } case 2:{
                    q.dequeue();
                    break;
                }case 3:{
                    if(q.isEmpty()){
                        System.out.println("Queue is empty");
                    }else{
                        System.out.println("rear element: "+q.isRear());
                    }
                    break;
                }case 4:{
                    q.display();
                    break;
                }case 5:{
                    check=false;
                    System.out.println("Thank U :)");
                    break;
                }
                default:{
                    System.out.println("Error");
                }
            }
        }
    }
}
