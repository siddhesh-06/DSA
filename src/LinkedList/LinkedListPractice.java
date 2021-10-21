package LinkedList;

import java.util.Scanner;

class node {
    int data;
    node next;
}

class linkedList{
    node start=new node();
    node last=new node();

    linkedList(){
        start=null;
        last=null;
    }

    public void insertAtBeg(int info){
        node n=new node();
        n.data=info;

        if(start==null){
            n.next=null;
            last=n;
            start=n;
        }else{
            n.next=start;
            start=n;
        }
    }

    public void insertAtEnd(int info){
        node n=new node();
        n.data=info;

        if(start==null){
            start=n;
        }else{
            node p;
            p=start;
            while(p.next!=null){
                p=p.next;
            }
            p.next=n;
        }
    }

    public void insertAtAnyPos(int p,int info){
        node n=new node();
        n.data=info;

        if(start==null && p==0){
            start=n;
        }else{
            if(p==0){
                n.next=start;
                start=n;
            }else{
                node temp=start,q=null;

                for(int i=1;i<=p;i++){
                    q=temp;
                    temp=temp.next;
                }
                q.next=n;
                n.next=temp;
            }
        }
    }

    public void deleteFromBeg(){
        if(start==null){
            System.out.println("Linked list is empty");
        }else{
            if(start.next==null){
                start=null;
            }else{
                start=start.next;
            }
        }
    }

    public void deleteFromEnd(){
        if(start==null){
            System.out.println("Linked list is empty");
        }else{
            node p=null;
            node temp=start;
            while(temp.next!=null){
                p=temp;
                temp=temp.next;
            }
            p.next=null;
        }
    }

    public void deleteAtAny(int p){
        if(p==0){
            start=start.next;
        }else{
            node temp=start;
            node q=null;
            for(int i=0;i<p;i++){
                q=temp;
                temp=temp.next;
            }
            q.next=temp.next;
        }
    }

    public int lengthOfList(){
        int l=0;
        if(start==null){
            return l;
        }else {
            node ptr;
            ptr = start;
            while (ptr != null) {
                l++;
                ptr = ptr.next;
            }
            return l;
        }
    }

    public void minMax(){
        if(start==null){
            System.out.println("List is empty :(");
        }else{
            int length=lengthOfList();
            node temp=start;
            int min=temp.data,max=temp.data;
            for(int i=0;i<length;i++){
                if(temp.data>max)   max=temp.data;
                if(temp.data<min)   min=temp.data;
                temp=temp.next;
            }
            System.out.println("Min: "+min+" Max: "+max);
        }
    }

    public void reverse(){
        node newNode = null;
        while(start!=null){
            node next = start.next;
            start.next=newNode;
            newNode=start;
            start=next;
        }
        node temp =start;
        while (temp!=null){
            System.out.print(start.data+", ");
            temp=temp.next;
        }
    }

    public void display(){
        if(start==null){
            System.out.println("Empty");
        }else {
            node ptr;
            ptr = start;
            while (ptr != null) {
                System.out.print(ptr.data + ",");
                ptr = ptr.next;
            }
        }
        System.out.println(" ");
    }
}


public class LinkedListPractice {
    public static void main(String args[]){
        linkedList l=new linkedList();
        Scanner sc=new Scanner(System.in);

        boolean c=true;
        while(c){
            System.out.println("1.Insert at beg");
            System.out.println("2.Insert at end");
            System.out.println("3.Insert at any position");
            System.out.println("4.Delete from beg");
            System.out.println("5.Delete from end");
            System.out.println("6.Delete from any position");
            System.out.println("7.Min & Max elemnt in list");
            System.out.println("8.Length of list");
            System.out.println("9.Display");
            System.out.println("10.Reverse Display");
            System.out.println("11.Exist");
            System.out.print("Enter choice: ");
            int choice=sc.nextInt();
            int input,position;
            switch (choice){
                case 1:
                    System.out.print("Enter data: ");
                    input=sc.nextInt();
                    l.insertAtBeg(input);
                    break;
                case 2:
                    System.out.print("Enter data: ");
                    input=sc.nextInt();
                    l.insertAtEnd(input);
                    break;
                case 3:
                    System.out.print("Enter data: ");
                    input=sc.nextInt();
                    System.out.print("Enter position: ");
                    position=sc.nextInt();
                    l.insertAtAnyPos(position,input);
                    break;
                case 4:
                    l.deleteFromBeg();
                    break;
                case 5:
                    l.deleteFromEnd();
                    break;
                case 6:
                    System.out.print("Enter position: ");
                    position=sc.nextInt();
                    l.deleteAtAny(position);
                    break;
                case 7:
                    l.minMax();
                    break;
                case 8:
                    System.out.println("Length of list: "+l.lengthOfList());
                    break;
                case 9:
                    l.display();
                    break;
                case 10:
                    l.reverse();
                    break;
                case 11:
                    c=false;
                    break;
                default:
                    System.out.println("Error");
            }
        }

    }
}
