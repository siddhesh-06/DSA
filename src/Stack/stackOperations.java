package Stack;

import java.util.Scanner;

class node{
    int data;
    node next;
}

class stack extends node{
    node top;
    stack(){
        top=null;
    }

    public void push(int info){ // insert at begining
        node n=new node();
        n.data=info;

        if(isEmpty()){
            n.next=null;
            top=n;
        }else{
            n.next=top;
            top=n;
        }
    }

    public void pop(){
        if(isEmpty()){
            System.out.println("Stack is empty");
        }else{
            top=top.next;
        }
    }

    public int isTop(){
        return top.data;
    }

    public boolean isEmpty(){
        if(top==null){
            return true;
        }
        return false;
    }

    public void display() {
        node temp=top;
        if(isEmpty()){
            System.out.println("Stack is empty");
        }else{
            while (temp != null) {
                System.out.print(temp.data + ",");
                temp = temp.next;
            }
        }
        System.out.println(" ");
    }
}



public class stackOperations {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        stack s=new stack();
        boolean check=true;
        int c,info;

        while (check){
            System.out.println("1.Push");
            System.out.println("2.Pop");
            System.out.println("3.Top element");
            System.out.println("4.Display");
            System.out.println("5.Exist");
            c=sc.nextInt();

            switch (c){
                case 1:{
                    System.out.print("Enter input: ");
                    info=sc.nextInt();
                    s.push(info);
                    break;
                }case 2:{
                    s.pop();
                    break;
                }case 3:{
                    if(s.isEmpty()){
                        System.out.println("Stack is empty");
                    }else{
                        System.out.println("Top element is: "+s.isTop());
                    }
                    break;
                }case 4:{
                    s.display();
                    break;
                } case 5:{
                    check=false;
                    System.out.println("Thank U :)");
                    break;
                }default:{
                    System.out.println("Error");
                }
            }
        }
    }
}
