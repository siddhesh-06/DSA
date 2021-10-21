package CircularLinkedList;

import java.util.Scanner;

class node{
    int data;
    node next;
    node prev;
}

class circularLinkList{
    node start=new node();

    circularLinkList(){
        start=null;
    }

    public void insertAtBeg(int info){
        node n=new node();
        n.data=info;

        if(start==null){
            start=n;
        }else{
            n.next=start;
            start.prev=n;
            start=n;
        }
    }
    public void insertAtEnd(int info){
        node n=new node();
        n.data=info;

        if(start==null){
            start=n;
        }else{
            node temp=start;

            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=n;
            n.prev=temp;
        }
    }

    public void insertAtAnyPos(int info,int p){
        node n=new node();
        n.data=info;

        if(start==null && p==0){
            start=n;
        }else{
            if(p==0){
                n.next=start;
                start.prev=n;
                start=n;
            }else{
                node temp=start;
                node q=null;
                for(int i=0;i<p;i++){
                    q=temp;
                    temp=temp.next;
                }
                q.next=n;
                n.prev=q;
                q=n;
                q.next=temp;
                temp.prev=q;
            }
        }
    }

    public void deleteAtBeg(){
        if (start == null) {
            System.out.println("List is sempty");
        }else{
            start=start.next;
        }
    }

    public void deleteAtEnd(){
        if(start==null){
            System.out.println("List is empty");
        }else{
            node temp=start;
            while(temp.next!=null){
                temp=temp.next;
            }
            node p=temp.prev;
            temp=p;
            temp.next=null;
        }
    }

    public void deleteAtAnyPos(int p){
        if(start==null){
            System.out.println("List is empty");
        }else{
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
                temp.next.prev=q;

            }
        }
    }

    public int lengthOfList(){
        if(start==null){
            return 0;
        }else{
            node temp=start;
            int c=0;
            while(temp.next!=null){
                c++;
                temp=temp.next;
            }
            return c+1;
        }
    }

    public void display(){
        node temp=start;

        if(temp==null){
            System.out.println("List is empty");
        }else{
            while(temp!=null){
                System.out.print(temp.data+",");
                temp=temp.next;
            }
        }
        System.out.println(" ");
    }


    public void revDisplay(){
        node temp=start;

        if(temp==null){
            System.out.println("List is empty");
        }else{
            while(temp.next!=null){
                temp=temp.next;
            }
            while(temp.prev!=null){
                System.out.print(temp.data+",");
                temp=temp.prev;
            }
        }
        System.out.print(start.data);
        System.out.println(" ");
    }
}


public class circularLinkedList {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        circularLinkList ll=new circularLinkList();
        boolean check=true;
        int c,info,p;
        while(check){
            System.out.println("1.Insert at begining: ");
            System.out.println("2.Insert at end: ");
            System.out.println("3.Insert at any position: ");
            System.out.println("4.Delete from begining: ");
            System.out.println("5.Delete from end: ");
            System.out.println("6.Delete from any position: ");
            System.out.println("7.Length of list: ");
            System.out.println("8.Display reverse: ");
            System.out.println("9.Display: ");
            c=sc.nextInt();
            switch (c){
                case 1:{
                    System.out.print("Enter input: ");
                    info=sc.nextInt();
                    ll.insertAtBeg(info);
                    break;
                } case 2:{
                    System.out.print("Enter input: ");
                    info=sc.nextInt();
                    ll.insertAtEnd(info);
                    break;
                } case 3:{
                    System.out.print("Enter input: ");
                    info=sc.nextInt();
                    System.out.println("Enter position: ");
                    p=sc.nextInt();
                    ll.insertAtAnyPos(info,p);
                    break;
                } case 4:{
                    ll.deleteAtBeg();
                    break;
                }case 5:{
                    ll.deleteAtEnd();
                    break;
                } case 6:{
                    System.out.println("Enter position: ");
                    p=sc.nextInt();
                    ll.deleteAtAnyPos(p);
                    break;
                }case 7:{
                    System.out.println("Legth of list: "+ll.lengthOfList());
                    break;
                }case 8:{
                    ll.revDisplay();
                    break;
                } case 9:{
                    ll.display();
                    break;
                }
                default:{
                    System.out.println("Error :(");
                }
            }
        }
    }
}
