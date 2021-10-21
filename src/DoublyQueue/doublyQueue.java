package DoublyQueue;

class doubQueue{
    private int front,rear,size;
    private int arr[];

    doubQueue(int size){
        arr = new int[size];
        this.size=size;
        front=-1;
        rear=0;
    }

    public boolean isFull(){
        return((front==-1 && rear==size-1) || (front==rear+1));
    }

    public boolean isEmpty(){
        if(front==-1){
            return true;
        }
        return false;
    }

    public void insertAtFront(int data){
        if(isFull()){
            System.out.println("Overflow");
        }else{
            if(front==-1){
                front=0;
                rear=0;
            }else if(front==0){
                front=size-1;
            }else {
                front=front-1;
            }
            arr[front]=data;
        }
    }

    public void insertAtRear(int data){
        if(isFull()){
            System.out.println("Overflow");
        }else{
            if(front==-1){
                front=0;
                rear=0;
            }else if(rear==size-1){
                rear=0;
            }else {
                rear=rear+1;
            }

            arr[rear] = data;
        }
    }

    public void deleteFront(){
        int x=-124;
        String x1 = Integer.toString(x);
        String temp="";
        for(int i=x1.length()-1;i>=0;i--){
            temp=temp+x1.charAt(i);
        }

        int ans = Integer.parseInt(temp);
        System.out.println(ans);
    }

    public void deleteRear(int data){

    }
}

public class doublyQueue {

    public static void main(String args[]){
        doubQueue d =new doubQueue(5);
        d.deleteFront();
    }
}
