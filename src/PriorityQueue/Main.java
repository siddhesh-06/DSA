package PriorityQueue;

import java.util.ArrayList;

class PriorityQueue{
    ArrayList<Integer> data;

    PriorityQueue(){
        data = new ArrayList<>();
    }

    //Add = o(logn)
    public void add(int val){
        data.add(val);
        unHeapify(data.size()-1);
    }
    private void unHeapify(int i){
        if(i==0) return;

        int parInd = (i-1)/2;
        if(data.get(i)<data.get(parInd)){
            swap(i, parInd);
            unHeapify(parInd);
        }
    }

    //Remove = o(logn)
    public int remove(){
        if(this.size()==0){
            System.out.println("Under flow");
            return -1;
        }
        swap(0, data.size()-1);
        int val = data.remove(data.size()-1);
        downHeapify(0);
        return val;
    }
    private void downHeapify(int pi){
        int mini = pi;

        int leftInd = (pi+1)*2;
        if(leftInd<data.size() && data.get(leftInd)<data.get(mini)){
            mini = leftInd;
        }

        int rightInd = (pi+2)*2;
        if(leftInd<data.size() && data.get(rightInd)<data.get(mini)){
            mini = rightInd;
        }

        if(mini!=pi){
            swap(mini, pi);
            downHeapify(mini);
        }


    }

    //Peek
    public int peek(){
        if(this.size()==0){
            System.out.println("Underflow");
        }
        return data.get(0);
    }

    ///Helpers
    private void swap(int i, int j){
        int ival = data.get(i);
        int jval = data.get(j);
        data.set(i, jval);
        data.set(j, ival);
    }
    public int size(){
        return data.size();
    }
}



public class Main {
    public static void main(String args[]){
        int arr[] = {1,5,10,2,58,16,47,2,15};
        PriorityQueue pq= new PriorityQueue();
        for(int i=0;i<arr.length;i++){
            pq.add(arr[i]);
        }
    }
}
