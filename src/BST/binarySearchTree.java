package BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class treeNode{
    int data;
    treeNode rightNode;
    treeNode leftNode;

    treeNode(){
        data=0;
        rightNode=null;
        leftNode=null;
    }
}

class bst extends treeNode{
    treeNode root,loc,par;

    //methods
    public void insert(int info){
        treeNode newNode = new treeNode();
        newNode.data=info;

        if(root==null){
            root=newNode;
            rightNode=null;
            leftNode=null;
        }else{
            boolean inserted=false;
            treeNode ptr=root;
            while (!inserted) {
                if (info > ptr.data) {
                    if (ptr.rightNode != null) {
                        ptr = ptr.rightNode;
                    } else {
                        ptr.rightNode=newNode;
                        inserted=true;
                    }
                } else if (info < ptr.data) {
                    if (ptr.leftNode != null) {
                        ptr=ptr.leftNode;
                    } else {
                        ptr.leftNode=newNode;
                        inserted=true;
                    }
                } else {
                    System.out.println("Element alredy inserted");
                    break;
                }
            }
        }
    }

    public void search(int info){
        treeNode ptr=root;
        loc=null;
        par=null;
        System.out.println(ptr.data);
        if(root==null){
            System.out.println("Tree is empty");
        }else {
            while (ptr!=null){
                if(ptr.data==info){
                    System.out.println("Element is found");
                    loc=ptr;
                    break;
                }else if(info>ptr.data){
                    par=ptr;
                    ptr=ptr.rightNode;
                    loc=ptr;
                }else{
                    par=ptr;
                    ptr=ptr.leftNode;
                    loc=ptr;
                }
            }
            if(loc==null){
                System.out.println("Not found");
            }
        }
    }

    public int height(){
       return bstHeight(root);
    }

    public int bstHeight(treeNode root){
        if(root==null){
            return -1;
        }else{
            int left=bstHeight(root.leftNode);
            int right=bstHeight(root.rightNode);
            if(left>right){
                return  left+1;
            }else{
                return right+1;
            }
        }

    }

    //Inorder
    public void display(){
        inorder(root);
    }

    public void inorder(treeNode root){
       if(root!=null){
           inorder(root.leftNode);
           System.out.print(root.data+", ");
           inorder(root.rightNode);
       }
    }

    public void leaf() {
        Queue<treeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
            while (!q.isEmpty()) {
                treeNode node = q.poll(); //remove & return head of queue
                if (node.leftNode == null && node.rightNode == null) {
                    System.out.println(node.data + ", ");
                } else {

                    if (node.leftNode != null) q.add(node.leftNode);
                    if (node.rightNode != null) q.add(node.rightNode);

                }
            }
        } else {
            System.out.print("No tree");
        }
        System.out.println(" ");
    }


    public void delete0(){
        if(loc!=null){
            if(par==null){
                root=null;
            }else{
                if(loc == par.rightNode){
                    par.rightNode=null;
                }else{
                    par.leftNode=null;
                }
            }
            loc=null;
        }
    }

    public void delete2(int info){
        search(info);
        if(loc!=null){
            treeNode ptr1 = null;
            treeNode ptr2 = null;
            //when loc dont have both child
            if(loc.leftNode==null && loc.rightNode==null){
                delete0();
            }else if(loc.rightNode != null && loc.leftNode != null ){
                ptr2=loc.rightNode;
                ptr1=loc;
                while(ptr2.rightNode!=null){
                    ptr1=ptr2;
                    ptr2=ptr2.leftNode;
                }

            }
        }
    }

    public void parentsWithChild(){
        Queue<treeNode> q = new LinkedList<>();

        if(root!=null){
            q.add(root);
            while (!q.isEmpty()){
                treeNode node=q.poll();
                System.out.println(node.data+": ");
                if(node.leftNode!=null){
                    System.out.println(node.leftNode.data);
                    q.add(node.leftNode);
                }
                if(node.rightNode!=null){
                    System.out.println(node.rightNode.data);
                    q.add(node.rightNode);
                }
            }
        }else{
            System.out.println("No tree");
        }
    }
}


public class binarySearchTree {
    public static void main(String args[]){
        bst b =new bst();
        Scanner sc =new Scanner(System.in);
        int c,info;
        boolean check=true;
        while (check){
            System.out.println("1.Insert element: ");
            System.out.println("2.Display: ");
            System.out.println("3.Search: ");
            System.out.println("4.Height: ");
            System.out.println("5.Delete: ");
            System.out.println("6.Leaf nodes: ");
            System.out.println("7.Leaf nodes: ");
            System.out.println("8.Exist: ");
            c=sc.nextInt();
            switch (c){
                case 1:{
                    System.out.print("Enter value: ");
                    info=sc.nextInt();
                    b.insert(info);
                    break;
                }
                case 2:{
                    b.display();
                    System.out.println("");
                    break;
                }
                case 3:{
                    System.out.print("Enter element: ");
                    info=sc.nextInt();
                    b.search(info);
                    break;
                }
                case 4:{
                    System.out.println("Height is: "+b.height());
                    break;
                }
                case 5:{
                    System.out.println("Enter Element: ");
                    info=sc.nextInt();
                    b.delete2(info);
                    break;
                }
                case 6:{
                    b.leaf();
                    break;
                }
                case 7:{
                    b.parentsWithChild();
                    break;
                }
                case 8:{
                    check=false;
                    break;
                }
                default:{
                    System.out.println("Error");
                }
            }
        }
    }
}
