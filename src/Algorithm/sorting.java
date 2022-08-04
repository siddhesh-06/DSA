package Algorithm;

import java.util.*;

public class sorting {
    public static void main(String args[]){
        int arr[] = {2,1,6,4,5,3};
        int ans[] = {1,2,3,4,5,6};
    }

    //5] Merge sort
    public static void mergeSort(int arr[], int s, int e){
        //base condition
        if(s>=e) return;

        int mid = (s+e)/2;

        //left
        mergeSort(arr, s, mid);

        //right
        mergeSort(arr,mid+1,e);

        merge(arr,s,e);
    }
    public static void merge(int arr[], int s, int e){
        int mid = (s+e)/2;

        int len1 = mid-s +1;
        int len2 = e-mid;


        int first[] = new int[len1];
        int sec[] = new int[len2];

        int k = s;
        for(int i=0;i<len1;i++){
            first[i] = arr[k++];
        }
        k = mid+1;
        for(int i=0;i<len2;i++){
            sec[i] = arr[k++];
        }


        // merge two sorted array
        int ind1 = 0, ind2 = 0;
        k = s;

        while (ind1<len1 && ind2<len2){
            if(first[ind1]<sec[ind2]){
                arr[k++] = first[ind1++];
            }else{
                arr[k++] = sec[ind2++];
            }
        }

        while (ind1<len1) arr[k++] = first[ind1++];
        while (ind2<len2) arr[k++] = sec[ind2++];

    }

    //4] Quick sort
    public static int[] quick(int arr[]){
        int n = arr.length;

        for(int i=0;i<n;i++){
            int pivot =arr[i];

            for(int j=0;j<i;j++){
                if(arr[j]>pivot){
                    int temp = arr[j];
                    arr[j] = pivot;
                    pivot = temp;
                }
            }

            for(int j=i+1;j<n;j++){
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

    //3] Selection sort
    public static int[] selection(int arr[]){
        int n = arr.length;
        for(int i=0;i<n-1;i++){
            int min = i;
            for(int j=i+1;j<n;j++){
                if(arr[min]>arr[j]){
                    min = j;
                }
            }
            if (min!=i){
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }

        return arr;
    }

    //2] Insertion sort
    public static int[] insertion(int arr[]){
        int n =arr.length;

        for(int i=1;i<n;i++){
            int key = arr[i];
            int j = i-1;

            while (j>=0 && arr[j]>key){
                arr[j+1] = arr[j];
                j=j-1;
            }
            arr[j+1] = key;
        }

        return arr;
    }

    //1] Bubble sort
    public static int[] bubble(int arr[]){
        int n = arr.length;

        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return arr;
    }
}










