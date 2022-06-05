package Binary_Search;

import java.util.ArrayList;

public class striver_bs {
    public static void main(String args[]){
        int arr[] = {1,2,3,8,10,15,20};
        //binarySearch(arr, 7);

        int mat[][] =
                {
                {1,2,3,4},
                {6,7,8,9},
                {12,11,13,15}
                };

        System.out.println(mat[0]);
    }


    //1] Median matrix
    public int countSmallerThanEqualToMid(ArrayList<Integer> row, int mid){
        int s = 0;
        int e = row.size() - 1;

        while (s<=e){
            int md = s + (e-s)/2;

            if(row.get(md)<=mid){
                s = md + 1;
            }else{
                e = md - 1;
            }
        }

        return s;
    }
    public int findMedian(ArrayList<ArrayList<Integer>> arr){
        int n = arr.size();
        int m = arr.get(0).size();

        int s = 1;
        int e = Integer.MAX_VALUE;

        while (s<=e){
            int mid = s + (e-s)/2;
            int cnt = 0;
            for(int i=0;i<n;i++){
                cnt += countSmallerThanEqualToMid(arr.get(i), mid);
            }
            if(cnt <= (n*m)/2) s = mid + 1;
            else e = mid - 1;
        }
         return s;
    }

    public static void binarySearch(int arr[], int key){
        int s = 0;
        int e = arr.length - 1;
        int res = -1;

        while (s<=e){
            int mid = s + (e-s)/2;

            if(arr[mid]==1){
                res = mid;
            }else if(key<arr[mid]){
                e = mid - 1;
            }else{
                s = mid + 1;
            }
        }

        System.out.println("Mid: "+res);
        System.out.println("Start: "+s);
        System.out.println("End: "+e);
    }
}


































