package DLL;

import java.util.Scanner;

class node {
    int data;
    node next;
    node prev;
}

class DCLL{
    private node start,last;

    DCLL(){
        start=null;
        last=null;
    }

    public void insertAtBegin(int info){
        node n =new node();
        n.data=info;
        if(start==null){
            start=n;
            n.next=start;
            n.prev=start;
        }else{
            node temp=start;
            while(temp.next!=start){
                temp=temp.next;
            }
            temp.next=n;
            n.prev=temp;
            start.prev=n;
            n.next=start;
            start=n;
        }
    }

    public void insertEnd(int info){
        node n = new node();
        n.data = info;

        if(start==null){
            start=n;
            n.next=start;
            n.prev=start;
        }else{
            node temp =start;
            temp.prev=n;
            while(temp.next!=start){
                temp=temp.next;
            }
            temp.next=n;
            n.prev=temp;
            n.next=start;
        }
    }

    public void deleteBegin(){
        if(start==null){
            System.out.println("DCLL is empty");
        }else if(start.next==start){
            start=null;
        }else{
            node temp = start;
            while(temp.next!=start){
                temp=temp.next;
            }
            start=start.next;
            start.prev=temp;
            temp.next=start;
        }
    }

    public void deleteEnd(){
        if(start==null){
            System.out.println("DCLL is empty");
        }else if(start.next==start){
            start=null;
        }else{
            node temp =start;
            node p =null;
            while(temp.next!=start){
                temp=temp.next;
            }
            p =temp.prev;
            start.prev=p;
            p.next=start;
        }
    }

    public int getLength(){
        int length=0;
        node temp=start;
        while(temp!=null){
            length++;
            temp=temp.next;
        }
        return length;
    }

    public void display(){
        node temp=start;
        if(start==null){
            System.out.println("DCLL is empty");
        }else{
            while(temp.next!=start){
                System.out.print(temp.data+", ");
                temp=temp.next;
            }
            System.out.print(temp.data+", ");
        }
        System.out.println(" ");
    }
}

public class DoublyCircularLinkedList {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        boolean check=true;
        int c,input;
        DCLL d = new DCLL();
        while(check){
            System.out.println("1. Insert at begining: ");
            System.out.println("2. Insert at ending: ");
            System.out.println("3. Delete from begning: ");
            System.out.println("4. Delete from ending: ");
            System.out.println("5. Display: ");
            System.out.println("6. Length: ");
            System.out.println("7. Exist: ");
            c=sc.nextInt();
            switch (c){
                case 1:
                    System.out.print("Enter data: ");
                    input=sc.nextInt();
                    d.insertAtBegin(input);
                    break;
                case 2:
                    System.out.print("Enter data: ");
                    input=sc.nextInt();
                    d.insertEnd(input);
                    break;
                case 3:
                    d.deleteBegin();
                    break;
                case 4:
                    d.deleteEnd();
                    break;
                case 5:
                    d.display();
                    break;
                case 6:
                    System.out.println("Length is: "+d.getLength());
                    break;
                case 7:
                    check=false;
                    break;
                default:
                    System.out.println("Error :(");
            }
        }
    }

}
