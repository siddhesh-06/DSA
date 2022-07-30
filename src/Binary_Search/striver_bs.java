package Binary_Search;

import com.sun.jdi.connect.Connector;
import com.sun.source.tree.BinaryTree;

import javax.swing.tree.TreeNode;
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


    //6] Kth element of 2 sorted array

    //5] Median of 2 sorted array

    //4] Find element in rotated sorted array
    public int fintElementInRotatedArray(int nums[], int target){
        int s = 0;
        int e = nums.length - 1;

        while (s<=e){
            int mid = s + (e-s)/2;
            if(nums[mid]==target) return mid;

            if(nums[s]<=nums[mid]){ // left part sorted
                if(target<=nums[mid] && target>=nums[s]){
                    e = mid - 1;
                }else{
                    s = mid +1;
                }
            }else { // right part sorted
                if(target>=nums[mid] && target<=nums[e]){
                    s = mid +1;
                }else{
                    e = mid -1;
                }
            }
        }
        return -1;
    }
    public int findMinInRotatedArray(int nums[]){
        int s = 0;
        int e = nums.length-1;
        while (s<e){
            int mid = s + (e-s)/2;
            if(nums[mid]==nums[e]){
                e--;
            }else if(nums[mid]>nums[e]) {
                s = mid + 1;
            }else{
                e = mid;
            }
        }
        return nums[e];
    }

    //3]
    public int findSingleElement(int arr[]){
        int s = 0;
        int e = arr.length - 2;
        while (s<=e){
            int mid = s + (e-s)/2;
            if(arr[mid]==arr[mid^1]){// odd -> prev (even) // even -> next (odd)
                // mid^1 check left side
                s = mid + 1; // if this satisfied means left side is OK check on right
            }else{
                e = mid - 1;
            }
        }

        return arr[s];
    }

    //2] Median matrix
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


































