package Binary_Search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class binarySearch {
    public static void main(String args[]) {
//        int arr[][] = {
//                {10,20,30,40},
//                {15,25,35,45},
//                {27,29,37,45},
//                {32,33,39,50}
//                };
//        int m = 4, n = 4;
    }
    // 18] Allocate minimum number of pages
    static int findPages(int[] arr, int N, int key) {
        if (arr.length < key) return -1;
        int start = 0;
        int end = sumOFArray(arr);
        int res = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (isValid(arr, arr.length, key, mid)) {
                res = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return res;
    }
    static boolean isValid(int arr[], int n, int key, int max) {
        int student = 1;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                return false;

            if (sum + arr[i] > max) {
                student++;
                sum = arr[i];
                if (student > key) {
                    return false;
                }
            } else {
                sum += arr[i];
            }

        }
        return true;
    }
    static int sumOFArray(int arr[]) {
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        return sum;
    }

    // 17] Search in row wise and col wise in sorted array
    static ArrayList<Integer> searchInSortedMatrix(int arr[][], int key) {
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0;
        int j = arr.length - 1;
        while (i >= 0 && i < arr.length && j >= 0 && j < arr.length) {
            if (arr[i][j] == key) {
                ans.add(i);
                ans.add(j);
                return ans;
            } else if (arr[i][j] > key) {
                j--;
            } else if (arr[i][j] < key) {
                i++;
            }
        }
        ans.add(-1);
        ans.add(-1);
        return ans;
    }

    // 16] Max elemet in bitonic array
    // 15] Peak elemet
    static int peakElement(int arr[], int k) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid != 0 && mid != arr.length - 1) {
                if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                    return mid;
                } else if (arr[mid - 1] > arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (mid == 0) {
                if (arr[0] > arr[1]) {
                    return 0;
                } else {
                    return 1;
                }
            } else if (mid == arr.length - 1) {
                if (arr[arr.length - 1] > arr[arr.length - 2]) {
                    return arr[arr.length - 1];
                } else {
                    return arr[arr.length - 2];
                }
            }
        }

        return -1;
    }

    // 14] Min difference of key
    static int minDifference(int arr[], int k) {
        // One more way is (key - floor) and (key - ceil) check diff return ans minimum
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == k) {
                return 0;
            } else if (k < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        // New tech for ceil and floor = arr[end] : ceil >>>> arr[start] : floor
        int minSide = Math.abs(arr[end] - k);
        int maxSide = Math.abs(arr[start] - k);
        if (minSide < maxSide) return minSide;
        else return maxSide;
    }

    // 13] Infinite binary sorted array find 1st 1 position
    static int infiniteFirstOne(int arr[]) {
        int start = 0;
        int end = 1;

        while (true) {
            if (arr[end] < 1) {
                start = end;
                end = end * 2;
            }
        }

//        int index = firstOccurence(arr,1);

    }

    // 11] Searching in Infinite sorted array
    static void infiniteSorted(int arr[],int k){
        int start = 0;
        int end = 1;

        while (true){
            if(arr[end]<k){
                start = end;
                end = end*2;
            }else{
                break;
            }
        }

        System.out.println("Start : "+start);
        System.out.println("End : "+end);
//        int index = bs(arr,k,start,end);
//        return arr[index];

    }

    // 12] Find element position for inertion
    static int findPosition(int arr[],int k){
        // we can also use floor & ceil concept
        int start = 0;
        int end = arr.length-1;
        int res = -1;
        while(start<=end){
            int mid = start +(end-start)/2;
            if(k==arr[mid]){
                return mid;
            }else if(k<arr[mid]){
                res = mid; // save it
                end = mid - 1; // => and skipping all big elements end = mid - 1
            }else if(k>arr[mid]){
                start = mid + 1;
            }
        }
        return res;
    }

    // 11] Find next alphabet in sorted array
    static char nextAlphabet(char letters[],char k){
        int s = 0;
        int e = letters.length-1;
        int res = -1;
        while(s<=e){
            int mid = s + (e-s)/2;
            if(k==letters[mid]){
                s = mid +1;
            }else if(k<letters[mid]){
                res = mid;
                e = mid -1;
            }else if(k>letters[mid]){
                s = mid +1;
            }
        }

        return letters[res];
    }

    // 10] Find ceil element in sorted array
    static int ceilElement(int arr[],int k){
        //just larger
        int s = 0;
        int e = arr.length-1;
        int res = -1;
        while(s<=e){
            int mid = s + (e-s)/2;
            if(k==arr[mid]){
                return arr[mid];
            }else if(k<arr[mid]){
                res = arr[mid];
                e = mid -1;
            }else if(k>arr[mid]){
                s = mid +1;
            }
        }

        return res;
    }

    // 9] Find floor element in sorted array
    static int floorElement(int arr[],int k){
        // 7.5 => 7 is floor(less) and 8 is ceil(greater)
        int s = 0;
        int e = arr.length-1;
        int res = -1;
        while(s<=e){
            int mid = s + (e-s)/2;
            if(k==arr[mid]){
                return arr[mid];
            }else if(k<arr[mid]){
                e = mid -1;
            }else if(k>arr[mid]){
                res = arr[mid];
                s = mid +1;
            }
        }

        return res;
    }

    // 8] Nearly sorted
    static int neralySorted(int arr[],int k){
        int s = 0;
        int e = arr.length-1;

        while(s<=e){
            int mid = s + (e-s)/2;
            // check 3 possiblities
            if(arr[mid]==k) return mid;
            if(mid-1>=s && arr[mid-1]==k) return mid-1;
            if(mid+1<=e && arr[mid+1]==k) return mid+1;

            //change start nd end by 2
            if(k<arr[mid]){
                e = mid -2;
            }else if(k>arr[mid]){
                s = mid +2;
            }
        }

        return -1;
    }

    // 7] Find element in roteted sorted array
    static boolean findElement(int arr[],int k){
        int minIdx = minInSortedArray(arr);
        int ansLeft = bs(arr,k,0,minIdx-1);
        int ansRight = bs(arr,k,minIdx,arr.length-1);

        if(ansLeft>0) return true;
        if(ansRight>0) return true;
        return false;
    }

    // 6] Min in sorted rotated array
    static int minInSortedArray(int arr[]){
        int start = 0;
        int length = arr.length;
        int end = length-1;

        while(start<=end){
            int mid = start + (end-start)/2;
            int next = (mid+1)%length; // if mid is at end => start
            int prev = (mid+length-1)%length; // if mid is at start => end
            // search in unsorted array
            if(arr[mid]<=arr[next] && arr[mid]<=arr[prev]){
                return mid;
            }else if(arr[start]<=arr[mid]){
                start = mid +1;
            }else if(arr[mid]<=arr[end]){
                end = mid -1;
            }
        }
        return -1;
    }

    // 5]Count of element
    static int countElement(int arr[],int k){
        int minIdx = firstOccurence(arr,k);
        int maxIdx = lastOccurence(arr,k);
        return (maxIdx-minIdx) + 1;
    }

    // 3] First occurence
    static int firstOccurence(int arr[],int k){
        int l = 0,r = arr.length-1;
        int res = -1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(arr[mid]==k){
                res = mid;
                r = mid -1;
            }else if(k<arr[mid]){
                r = mid-1;
            }else{
                l = mid +1;
            }
        }

        return res;
    }

    // 2] Last occurence
    static int lastOccurence(int arr[],int k){
        int l = 0,r = arr.length-1;
        int res = -1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(arr[mid]==k){
                res = mid;
                l = mid +1;
            }else if(k<arr[mid]){
                r = mid-1;
            }else{
                l = mid +1;
            }
        }

        return res;
    }

    // 1] Binary search
    static int bs(int arr[],int k,int l,int r){
        while(l<=r){
            int mid = l + (r-l)/2;

            if(arr[mid]==k){
                return mid;
            }else if(k>arr[mid]){
                l=mid+1;
            }else{
                r=mid-1;
            }
        }
        return -1;
    }

}
