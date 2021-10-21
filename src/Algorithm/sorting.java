package Algorithm;

import java.util.*;

public class sorting {
    public static void main(String args[]){
        sorting obj = new sorting();
        String a="xaxbbbxx";

        System.out.println(obj.anagram25(a));
    }

    public int anagram25(String s) {
        // Write your code here
        int c=0;
        int len =s.length();
        if(len%2 != 0) return -1;

        int mid = len/2;


        String s1=s.substring(0,mid);
        String s2=s.substring(mid,len);

        Arrays.sort(s1.toCharArray());
        Arrays.sort(s2.toCharArray());

        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)) {
                c++;
            }
        }
        return c;
    }

    public int[] quick(int arr[],int l){
        for(int i=0;i<l;i++){
            int pivot =arr[i];

            for(int j=0;i<i;j++){
                if(arr[j]>pivot){
                    int temp = arr[j];
                    arr[j] = pivot;
                    pivot = temp;
                }
            }

            for(int j=i+1;j<l;j++){
                if(arr[j]<pivot){
                    int temp = pivot;
                    pivot = arr[j];
                    arr[j] = temp;
                }
            }
            arr[i]=pivot;
        }
        return arr;
    }

    public int[] bubble(int arr[],int l){
        for(int i=0;i<l;i++){

            for(int j=0;j<l-i;j++){
                if(arr[j+1]<arr[j]){
                    int temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }


    public int[] insertion(int arr[],int l){
        // run time sort
        int key=0;
        int j=0;
        for(int i=0;i<l;i++){
            key = arr[i];
            j=i-1;
            while(j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
        return arr;
    }

    public int[] selection(int arr[],int l){
        // placed min element first
        for(int i=0;i<l;i++){
            int min =i;

            for(int j=i+1;j<l;j++){
                if(arr[j]<arr[min]){
                    min = j;
                }
            }
            if(min!=i){
                int temp=arr[min];
                arr[min]=arr[i];
                arr[i]=temp;
            }
        }
        return arr;
    }
}
