package Striver_SDE;
import java.lang.reflect.Array;
import java.util.*;

public class recursionsProblems {
    public static void main(String args[]){
        int arr[] = {2,3};
        System.out.println(subsetsSum(arr));
    }

    public long zeroFilledSubarray(int[] nums) {
        int n = nums.length;
        long zero = 0;
        for(int i=0;i<n;i++){
            if(nums[i]==0){
                zero++;
            }
        }

        long pairs = 0;
        for(int i=0;i<n-1;i++){
            if(nums[i]==nums[i+1]){
                pairs++;
            }
        }

        long single = zero - (pairs*2);
        if(single<0){
            return (pairs*2) + pairs;
        }
        return (pairs*2) + pairs + single;
    }

    //10] All subsequences
    public static ArrayList<ArrayList<Integer>>  getAllSubsequences(int arr[]){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> ds = new ArrayList<>();
        findSubseq(arr,0,ans,ds);
        return ans;
    }
    public static void findSubseq(int arr[], int ind, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> ds){
        if(ind==arr.length){
            ans.add(new ArrayList<>(ds));
            return;
        }
        ds.add(arr[ind]);
        findSubseq(arr,ind+1,ans,ds);
        ds.remove(ds.size()-1);

        findSubseq(arr, ind+1, ans, ds);
    }

    //9] All permutation
    public static List<List<Integer>> getAllPermutations(int nums[]){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        boolean track[] = new boolean[nums.length];
        return ans;
    }
    public static void allPermu(int nums[], List<Integer> ds, List<List<Integer>> ans, boolean track[]){
        if(ds.size()==nums.length){
            ans.add(new ArrayList<>(ds));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(track[i]) continue;
            track[i] = true;
            ds.add(nums[i]);
            allPermu(nums,ds,ans,track);
            ds.remove(ds.size()-1);
            track[i] = false;
        }
    }

    // 8] Suduku solver valid
    static boolean isValidSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return false;
       return solveSuduku(board);
    }
    static boolean solveSuduku(char[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == '.'){
                    for(char c='1';c<='9';c++){
                        if(isValid(board,i,j,c)){
                            board[i][j] = c;
                            if(solveSuduku(board)){
                                return true;
                            }else{
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    static boolean isValid(char[][] board, int row, int col, char c) {
        for(int i=0;i<9;i++){
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            // In matrix 3*3
            if(board[3 * (row/3) + i /3 ][3 * (col/3) + i%3] == c) return false;
        }
        return true;
    }

    // 7] Get kth permutation
    public String getPermutation(int n, int k) {
        int fact = 1;
        List<Integer> numbers = new ArrayList<>();

        for(int i=1;i<n;i++){
            fact = fact * i;
            numbers.add(i);
        }
        numbers.add(n);
        String ans = "";
        k = k-1;

        while (true){
            ans = ans + numbers.get(k/fact);
            numbers.remove(k/fact);

            if(numbers.size()==0) break;

            k = k % fact;
            fact = fact / numbers.size();
        }
        return ans;
    }

    // 6] Palindrome partition
    static public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> ds = new ArrayList<>();
        checkPartition(s,0,res,ds);
        return res;
    }
    static void checkPartition(String s,int idx,List<List<String>> res,List<String> ds){
        if(idx==s.length()){
            res.add(new ArrayList<>(ds));
            return;
        }
        for(int i=idx;i<s.length();i++){
            if(palindrome(s,idx,i)){
                ds.add(s.substring(idx,i+1));
                checkPartition(s,i+1,res,ds);
                ds.remove(ds.size()-1);
            }
        }

    }
    static boolean palindrome(String s,int i,int j){
        while(i<=j){
            if(s.charAt(i++)!=s.charAt(j--)){
                return false;
            }
        }
        return true;
    }

    // 5] Subset sum - 1
    static List<Integer> subsetsSum(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        findSubsetSum(0,nums,0,ans);
        Collections.sort(ans);
        return ans;
    }
    static void findSubsetSum(int idx,int nums[],int sum,List<Integer> ans){
        if(idx==nums.length){
            ans.add(sum);
            return;
        }
        findSubsetSum(idx+1,nums,sum+nums[idx],ans); // left calling
        findSubsetSum(idx+1,nums,sum,ans); // right calling
    }

    // 4] Subset - 2
    static List<List<Integer>> subsets2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        findSubset2(0,nums,ans,new ArrayList<>());
        return ans;
    }
    static void findSubset2(int idx,int nums[],List<List<Integer>> ans,List<Integer> ds){
        //Duplication avoid
        ans.add(new ArrayList<>(ds));

        for(int i= idx;i<nums.length;i++){
            if(i!=idx  && nums[i]==nums[i-1]) continue;

            ds.add(nums[i]);
            findSubset(i+1,nums,ans,ds);
            ds.remove(ds.size()-1);
        }
    }

    // 3] Subset - 1 : // Generate all possisble subsets
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        findSubset(0,nums,ans,new ArrayList<>());
        return ans;
    }
    static void findSubset(int idx,int nums[],List<List<Integer>> ans,List<Integer> ds){
        ans.add(new ArrayList<>(ds));
        for(int i= idx;i<nums.length;i++){
            ds.add(nums[i]);
            findSubset(i+1,nums,ans,ds);
            ds.remove(ds.size()-1);
        }

    }

    // 2] Combination sum - 2
    static List<List<Integer>> combinationsSum2(int canditates[],int target){
        // ans
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(canditates);
        findCombinations2(0,canditates,target,ans,new ArrayList<>());
        return ans;
    }
    static void findCombinations2(int idx,int arr[],int target,List<List<Integer>> ans,List<Integer> ds){
        // bs
        if(target==0){
            ans.add(new ArrayList<>(ds));
            return;
        }

        for(int i=idx;i<arr.length;i++){
            // only for this condition, loop will not work
            if(i>idx && arr[i]==arr[i-1]) continue;
            // whole loop will terminate
            if(arr[i]>target) break;

            ds.add(arr[i]);
            findCombinations2(i+1,arr,target-arr[i],ans,ds);
            ds.remove(ds.size()-1);
        }
    }

    // 1] Combination sum - 1
    static List<List<Integer>> combinationSum(int candi[],int target){
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(0,candi,target,ans, new ArrayList<>());
        return ans;
    }
    static void findCombinations(int idx,int arr[],int target,List<List<Integer>> ans,List<Integer> ds){
        if(idx==arr.length){
            if(target==0){
                ans.add(new ArrayList<>(ds));
            }
            return;
        }

        if(arr[idx]<=target){
            ds.add(arr[idx]);
            findCombinations(idx,arr,target-arr[idx],ans,ds);
            ds.remove(ds.size()-1);
        }
        findCombinations(idx+1,arr,target,ans,ds);
    }

}
