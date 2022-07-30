package Striver_SDE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.*;

class TreeNode{
    int val;
    TreeNode(int val){
        this.val = val;
    }
}

public class StriverArray {
    public static void main(String[] args) {
        int arr[] ={5,2,6,1};
        System.out.println(countSmaller(arr));
    }
    public static List<Integer> countSmaller(int[] arr) {
        int n = arr.length;
        if(n==1 || n==0) return new ArrayList<>(0);


        List<Integer> ds = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(i==n){
                ds.add(0);
                break;
            }
            int cnt = 0;
            for(int j=i+1;j<n;j++){
                if(arr[i]>arr[j]){
                    cnt++;
                }
            }
            ds.add(cnt);
        }

        return ds;
    }
    public static int numMatchingSubseq(String s, String[] words) {
        Map<Character,Queue<String>> hm = new HashMap<>();

        for(int i=0;i<s.length();i++){
            hm.putIfAbsent(s.charAt(i), new LinkedList<>());
        }

        for(String word : words){
            char ch = word.charAt(0);
            if(hm.containsKey(ch)){
                hm.get(ch).offer(word);
            }
        }
        int cnt = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            Queue<String> q = hm.get(ch);
            int size = q.size();

            for(int j=0;j<size;j++){
                String temp = q.poll();
                if(temp.substring(1).length()==0){
                    cnt++;
                }else{
                    char cht = temp.charAt(1);
                    if(hm.containsKey(cht)){
                        hm.get(cht).offer(temp.substring(1));
                    }
                }
            }
        }
        return cnt;
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int st[] = new int[n];
        st[0] = 1;

        for(int i=1;i<n;i++){
            st[i] = st[i-1] * nums[i-1];
        }

        int right = 1;
        for(int i=n-1;i>=0;i--){
            st[i] = st[i] * right;
            right = right * nums[i];
        }

        return st;
    }

    public int lps(String s){
        int n = s.length();

        int pie[]= new int[n];
        pie[0] = 0;
        int i=0,j=1;
        while (j<n){
            if(s.charAt(i)==s.charAt(j)){
                pie[j]=i+1;
                i++;
                j++;
            }else{
                if(i==0){
                    pie[j] = 0;
                    j++;
                }else{
                    i = pie[i-1];
                }
            }
        }

        return pie[n-1];
    }

    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        boolean seen[][] = new boolean[row][col];
        int ans = 0;

        for(int r=0;r<row;r++){
            for(int c=col;c<col;c++){
                ans = Math.max(ans, helper(r,c,seen,grid));
            }
        }
        return ans;

    }
    public int helper(int r, int c, boolean seen[][], int grid[][]){
        if(c<0 || r<0 || c>=grid[0].length || r>=grid.length || seen[r][c] || grid[r][c]==0) return 0;

        seen[r][c] = true;

        return (1 + helper(r+1,c,seen,grid) + helper(r-1,c,seen,grid) + helper(r,c+1,seen,grid) + helper(r,c-1,seen,grid));
    }
    public static String smallestWindow(String s, String p) {
        int n = s.length();
        int m = p.length();

        if(n==m) return s;

        String ans = "";
        HashMap<Character,Integer> hm1 = new HashMap<>();
        HashMap<Character,Integer> hm2 = new HashMap<>();

        for(int i=0;i<m;i++){
            char ch = p.charAt(i);
            hm2.put(ch, hm2.getOrDefault(ch, 0)+1);
        }

        int i=-1, j=-1;
        int mct = 0, dmt = p.length();
        while (true){
            boolean f1 = false;
            boolean f2 = false;
            // generating ans
            while (i<s.length()-1 && mct<dmt){
                i++;
                char ch = s.charAt(i);
                hm1.put(ch, hm1.getOrDefault(ch, 0)+1);

                if(hm1.getOrDefault(ch, 0) <= hm2.getOrDefault(ch, 0)){
                    mct++;
                }
                f1 = true;
            }

            // optimize leng
            while (j<i && mct==dmt){
                String ts = s.substring(j+1, i+1);

                if(ans.length()==0 || ts.length()<ans.length()){
                    ans = ts;
                }
                j++;
                char ch = s.charAt(j);

                if(hm1.get(ch)==1){
                    hm1.remove(ch);
                }else{
                    hm1.put(ch, hm1.get(ch)-1);
                }

                if(hm1.getOrDefault(ch, 0)<hm2.getOrDefault(ch, 0)){
                    mct--;
                }
                f2 = true;
            }

            if(f1==false && f2==false) break;
        }

        return ans;
    }
    // ------------ Array-4 ------------


    //5] Longest unique substring
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hs = new HashSet<>();
        int max = 0;
        int i = 0, j = 0;

        while (j<s.length()){
            if(!hs.contains(s.charAt(j))){
                hs.add(s.charAt(j));
                max = Math.max(max, hs.size());
                j++;
            }else{
                hs.remove(i);
                i++;
            }
        }

        return max;
    }

    //5] Count subarray xor is b
    public int solve(int[] A, int B) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int xor = 0, cnt = 0;
        for(int i=0;i<A.length;i++){
            xor = xor ^ A[i];
            if(hm.get(xor^B) != null){
                cnt += hm.get(xor^B);
            }
            if(xor==B){
                cnt++;
            }

            if(hm.get(xor) !=null){
                hm.put(xor, hm.get(xor)+1);
            }else{
                hm.put(xor, 1);
            }
        }
        return cnt;
    }

    //4] Count subaaray equal to 0
    int maxLen(int arr[], int n){
        HashMap<Integer,Integer> hm = new HashMap<>();
        int sum = 0, max = 0;
        hm.put(0, -1);
        int i = 0;
        while (i<n){
            sum += arr[i];
            if(!hm.containsKey(sum)){
                hm.put(sum ,i);
            }else{
                int len = i - hm.get(sum);
                max = Math.max(max, len);
            }
            i++;
        }
        return max;
    }

    //3] Longest Consecutive
    public static int longestConsecutive(int[] arr) {
        HashSet<Integer> hs = new HashSet<>();
        int result = 0;
        for(int val : arr) hs.add(val);

        for(int i=0;i<arr.length;i++){
            if(!hs.contains(arr[i]-1)){
                int currNum = arr[i];
                int currCounter = 1;
                while(hs.contains(currNum+1)){
                    currNum+=1;
                    currCounter+=1;
                }
                result = Math.max(result,currCounter);
            }
        }
        return result;
    }

    //2] 4 Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;
        for (int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int rem_sum = target - (nums[i]+nums[j]);
                int l = j+1;
                int r = n-1;

                while (l<r){
                    int sum = rem_sum - (nums[l]+nums[r]);
                    if(sum>target) r--;
                    else if(sum<target) l++;
                    else{
                        List<Integer> ds = new ArrayList<>();
                        ds.add(i);
                        ds.add(j);
                        ds.add(l);
                        ds.add(r);

                        while (l<r && nums[l]==ds.get(2)) ++l;
                        while (l<r && nums[r]==ds.get(3)) --r;

                    }
                }
                while (j+1<n && nums[j+1]==nums[j]) ++j;
            }
            while (i+1<n && nums[i+1]==nums[i]) ++i;
        }
        return ans;
    }

    //1] 2 sum

    // ------------ Array-3 ------------

    // Power
    public static double myPow(double x, int n) {
        double ans = 1.0;
        long nn = n;

        if(nn<0) nn = -1*nn;
        while (nn>0){
            if(x%2==1){
                ans = x*ans;
                nn = nn -1;
            }else{
                x = x*x;
                nn = nn/2;
            }
        }
        if(n<0) ans = 1.0/ans;

        return ans;
    }

    //5] Missing number

    //4] Find duplicate
    public int findDuplicate(int arr[]){
        if(arr.length<0) return -1;
        int slow = arr[0];
        int fast = arr[arr[0]];

        while (slow!=fast){
            slow = arr[slow];
            fast = arr[arr[fast]];
        }
        fast = 0;
        while (slow!=fast){
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;
    }

    //3] Merge two sorted array

    //2] Merge intervals
    public int[][] mergeIntervals(int arr[][]){
        List<int[]> ans = new ArrayList<>();

        if(arr.length==0){
            return ans.toArray(new int[0][]);
        }

        Arrays.sort(arr, (a,b)-> a[0]-b[0]);
        int start = arr[0][0];
        int end = arr[0][1];

        for(int i[] : arr){
            if(i[0]<=end){
                end = Math.max(end, i[1]);
            }else{
                ans.add(new int[]{start, end});
                start = i[0];
                end = i[1];
            }
        }
        ans.add(new int[]{start, end});

        return ans.toArray(new int[0][]);
    }

    //1] Rotate image
    public int[][] rotateImage(int mat[][]){
        int n = mat.length;
        int m = mat[0].length;
        //1.Transpose
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
        //2.Reverse all col's
        for(int i=0;i<n;i++){
            for(int j=0;j<m/2;j++){
                int temp = mat[i][j];
                mat[i][j] = mat[i][n-1-j];
                mat[i][n-1-j] = temp;
            }
        }

        return mat;
    }

    // ------------ Array-1 ------------

    //6] Stock buy and Sell
    public static void buySellStock(int price[]){
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i=0;i<price.length;i++){
            if(minPrice>price[i]){
                minPrice = price[i];
            }else if(maxProfit< price[i] - minPrice){
                maxProfit = price[i] - minPrice;
            }
        }
        System.out.println("Max : "+ maxProfit);
    }

    //5] Sort an array of 0’s 1’s 2’s

    //4] Kadane’s Algorithm
    public static int kadaneAlgo(int nums[]){
        int csum = nums[0];
        int osum = nums[0];

        for(int i=1;i<nums.length;i++){
            if(csum>=0){
                csum+=nums[i];
            }else{
                csum = nums[i];
            }

            if(csum>osum){
                osum = csum;
            }
        }

        return osum;
    }

    //3] Next Permutation
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n-2;
        while (i>=0 && nums[i]>=nums[i+1]){
            i--;
        }
        if(i>=0){
            int j = n-1;
            while(nums[j]<=nums[i]){
                j--;
            }
            swap(nums,i,j);
        }
        reverseArray(nums,i);
    }
    public static void reverseArray(int nums[], int i){
        int p = i+1, q = nums.length-1;
        while (p<q){
            int temp = nums[p];
            nums[p] = nums[q];
            nums[q] = temp;
            p++;
            q--;
        }
    }
    public static void swap(int nums[], int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //2] Pascal’s Triangle
    public static ArrayList<ArrayList<Integer>> pascalTriangle(int n){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if(n<0) return ans;

        for(int i=0;i<n;i++){
            ArrayList<Integer> ds = new ArrayList<>();
            for(int j=0;j<i+1;j++){
                if(j==0 || j==i){ // starting and last
                    ds.add(1);
                }else{
                    ds.add(ans.get(i-1).get(j-1) + ans.get(i-1).get(j));
                }
            }
            ans.add(new ArrayList<>(ds));
        }

        return ans;
    }

    //1] Set Matrix Zeroes
    public void setMatZero(int mat[][]){
        int n = mat.length;
        int m = mat[0].length;
        boolean fr = false, fc = false;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mat[i][j]==0){
                    if(i==0)  fr = true;
                    if(j==0) fc = true;

                    mat[i][0] = 0;
                    mat[0][j] = 0;
                }
            }
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(mat[i][0]==0 || mat[0][j]==0){
                    mat[i][j] = 0;
                }
            }
        }

        if(fr){
            for(int i=0;i<m;i++){
                mat[0][i] = 0;
            }
        }

        if(fc){
            for(int i=0;i<n;i++){
                mat[i][0] = 0;
            }
        }
    }

}
