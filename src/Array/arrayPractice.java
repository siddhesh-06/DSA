package Array;

import java.util.*;

class Node {
    int data;
    Node prev;

    Node(int data){
        this.data=data;
        this.prev=null;
    }

}

public class arrayPractice {
    public static void main(String args[]){
        int a[] = {2, 3, 4, 5, -1, 0};
        arrayPractice obj =new arrayPractice();
        obj.largetProduct(a);
    }

    //20] Largest product in subarray
    void largetProduct(int arr[]){

        //using kadane's


        //brutforce method
//        int msum=0;
//
//        for(int i=0;i<arr.length;i++){
//            int sum=arr[i];
//            for(int j=i+1;j<arr.length;j++){
//                sum=sum*arr[j];
//                if(sum>msum){
//                    msum=sum;
//                }
//            }
//        }
//        System.out.println("Product is: "+msum);
    }

    //19] Largest sum in subarray
    void largestSum(int arr[]){
        //kadane's algorithm

        int csum=arr[0];
        int osum=arr[0];

        for(int i=1;i<arr.length;i++){
            if(csum>=0){
                csum+=arr[i];
            }else{
                csum=arr[i];
            }

            if(csum>osum){
                osum=csum;
            }
        }

        System.out.println("Sum is: "+osum);

        // Brute force

//        int csum=arr[0];
//        int msum=csum;
//
//        for(int i=0;i<arr.length;i++){
//            csum=arr[i];
//
//            if(csum>msum){
//                msum=csum;
//            }
//
//            for(int j=i+1;i<arr.length;j++){
//                csum=csum+arr[i];
//                if(csum>msum){
//                    msum=csum;
//                }
//            }
//        }
//        System.out.println("Sum is: "+msum);

    }

    //18] Factorial code: Linked list
    void facorial(int n){
        Node tail = new Node(1);
        for(int i=2;i<=n;i++){
            multiply(tail,i);
        }
        print(tail);
    }

    void multiply(Node tail,int i){
        Node temp=tail;
        Node prevNode=tail;
        int carry=0;
        while (temp!=null){
            int data= (temp.data * i)+carry;
            temp.data=data%10;
            carry=data/10;
            prevNode=temp;
            temp=temp.prev;
        }

        while(carry!=0){
            prevNode.prev=new Node((int) (carry%10));
            carry/=10;
            prevNode=prevNode.prev;
        }

    }

    void print(Node tail){
        if(tail==null){
            return;        }
        print(tail.prev);
        System.out.print(tail.data);
    }


    //17] Subarray
    boolean subArray(int a[]){
        Set<Integer> h =new HashSet<>();
        int sum =0;

        for(int i=0;i<a.length;i++){
            sum+=a[i];
            if(a[i]==0 || sum == 0 || h.contains(sum)){
                return true;
            }
            h.add(sum);
        }
        return false;
    }

    //16] Alternative +ve & -ve
    void  alternative(int a[]){
        Arrays.sort(a);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+", ");
        }
    }

    //15]Largest 3rd element o(n)
    void largestThreeElements(int a[]){
        int first,second,third;
        first=second=third=0;

        for(int i=0;i<a.length;i++){
            if(first<a[i]){
                third = second;
                second = first;
                first = a[i];
            }else if(second<a[i]){
                third = second;
                second = a[i];
            }else if(third<a[i]){
                third = a[i];
            }
        }
        System.out.println("First,second & third : "+first+" "+second+" "+third);
    }

    //14]First non repeating element
    void firstNonRepeatElement(int a[]){
        //Try using hashMap
        for(int i=0;i<a.length;i++){
            int c=0;
            for(int j=0;j<a.length;j++){
                if(a[i]==a[j]){
                    c++;
                }
            }
            if(c==1){
                System.out.println(a[i]);
            }
        }
    }

    //13]First repeating element
    void firstRepeatElement(int a[]){
        HashSet<Integer> h = new HashSet<>();
        int c=0;

        for(int i=0;i<a.length;i++){
            if(h.contains(a[i])){
                c=i;
                break;
            }else{
                h.add(a[i]);
            }
        }

        if(c!=0){
            System.out.println(a[c]);
        }else{
            System.out.println("There is no repeating element in array");
        }
    }

    //12] Common el in 2 sorted array
    void commonElementInArray(int a[],int b[]){
        HashSet<Integer> h = new HashSet<>();

        for(int i=0;i<a.length;i++){
            h.add(a[i]);
        }

        for(int j=0;j<b.length;j++){
            if(h.contains(b[j])){
                System.out.print(b[j]+", ");
            }
        }
    }

    //11] Duplicate no in array
    void duplicateNo(int a[]){
        LinkedHashSet<Integer> arr = new LinkedHashSet<>();
        for(int i=0;i<a.length;i++){
            int c=0;
            for(int j=0;j<a.length;j++){
                if(a[i]==a[j]){
                    c++;
                    if(a[i] == a[j] && c>1){
                        arr.add(a[i]);
                    }
                }
            }
        }
        System.out.println(arr);
    }

    //10] Find disapper number
    void findDisapperNumber(int a[]){
        //sum formula: n(n+1)/2
        int n = a.length+1;
        int sumOfN = n*(n+1)/2;
        System.out.println(sumOfN);
        int sum =0 ;
        for(int i=0;i<a.length;i++){
            sum+=a[i];
        }
        System.out.println("Missing element: "+(sumOfN-sum));
    }

    //9] Intersection of array
    void intersectionOfArray(int a[],int b[]){
        //using hashset
        HashSet<Integer> h =new HashSet<>();
        for(int i=0;i<a.length;i++){
            h.add(a[i]); //hash can't take negative values
        }
        for(int i=0;i<b.length;i++){
            if(h.contains(b[i])){
                System.out.print(b[i]+", ");
            }
        }
    }
    //8] cyclic rotation by one
    void cyclicRotateByOne(int a[]){
        int b[] =new int[a.length];
        b[0] = a[a.length-1];

        for(int i=0;i<a.length-1;i++){
            b[i+1] = a[i];
        }

        for(int i=0;i<b.length;i++){
            System.out.print(b[i]+", ");
        }

        //Complex: o(n)
    }


    //7] union of array
    void unionOfArray(int a[],int b[]){
        //2]Using Map
        Map<Integer,Integer> mp =new HashMap<>();

        for(int i=0;i<a.length;i++){
            mp.put(a[i],i);
        }
        for(int j=0;j<b.length;j++){
            mp.put(b[j],j);
        }

        System.out.println(mp.keySet()); //return key values;

        //A]check length 2.check same values //3.display count
//        int count = 0;
//        for(int i=0;i<a.length;i++){
//            for(int j=0;j<b.length;j++){
//                if(a[i]==b[j]){
//                    count++;
//                }
//            }
//        }
//        int u = b.length -count;
//        count = a.length+u;
//        System.out.println("Union count: "+count);
        //complex: o(n^2)
    }

    //6] return count of repeted no
    int repeatNoInArray(int [] a,int check){
        int count=0;
        for(int i=0;i<a.length;i++){
            if(a[i]==check){
                count++;
            }
        }
        return count;
    }

    //5] return difference between max & min
    int diffMaxMin(int a[]){
        //B] Complex: o(nlogn)
//        Arrays.sort(a);
//        return a[a.length-1]-a[0];

        //A] Complex: o(n)
        int min=a[0],max=a[0];
        for(int i=0;i<a.length;i++){
            if(max<a[i]) max=a[i];
            if(min>a[i]) min=a[i];
        }
        return (max-min);
    }

    //4] kth max and min
    void kth_MinMax(int a[],int n1,int n2){
        //complex: o(nlogn)
        Arrays.sort(a);
        System.out.println(n1 +"th min: "+a[n1-1] +" "+ n2+"th max: "+a[a.length-n2]);
    }

    //3] Move all the negative elements to one side of the array.
    void negToposi(int a[]){
        Arrays.sort(a);
        int c=0;
        for(int i=0;i<a.length;i++){
            if(a[i]<0){
                c++;
            }
        }
        //copy array
        int b[]= new int[a.length];
        for(int i = 0;i<b.length;i++){
            b[i]=a[i];
        }
        //shifting
        int z=c-1;
        for(int i=0;i<c;i++){
                b[i]=a[z];
                z--;
        }

        for(int i=0;i<b.length;i++){
            System.out.print(b[i]+",");
        }
    }
    //2] sort array 0 ,1
    void arraySort(int a[]){
        int b[]=new int[a.length];
        int z=0,o=0,t=0;
        for(int i=0;i<a.length;i++){
            if(0==a[i]){
               z++;
            }else if(1==a[i]){
                o++;
            }else if(2==a[i]){
                t++;
            }
        }
        for(int i=0;i<z;i++){
            b[i]=0;
        }
        for(int i=z;i<z+o;i++){
            b[i]=1;
        }
        for(int i=z+o;i<z+o+t;i++){
            b[i]=2;
        }

        for(int i=0;i<b.length;i++){
            System.out.print(b[i]+",");
        }
    }
    //1] prime no in range
    void primeNumbersInRange(int n){
        int c;
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=2;i<=n;i++){
            c=0;
            for(int j= 2;j<=n;j++){
                if(i%j == 0){
                    c++;
                }
            }
            if(c==1){
                a.add(i);
            }
        }

        for(int i: a){
            System.out.println(i);
        }
        System.out.println("Total no: "+a.size());
    }
}
