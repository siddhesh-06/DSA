package Array;

import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
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
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]){
        int matrix[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15,16},
        };

        List<Integer> arr = new ArrayList<>();
        int i = matrix.length;
        while (i>=0){
            int j = 0;
            while (j<matrix.length){

                i--;
            }
        }
        System.out.println(arr);
    }
    static String longestPalindrome(String s){
        int start=0,end=0;

        for(int i=0;i<s.length();i++){
            int even = expandFromCenter(s,i,i+1);
            int odd = expandFromCenter(s,i,i);
            int len = Math.max(even,odd);

            if(end-start<len){
                start = i -(len-1)/2;
                end = i +len/2;
            }
        }

        return s.substring(start,end+1);
    }

    static int expandFromCenter(String s,int i,int j){
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){
            i--;
            j++;
        }

        return j-i-1;
    }

    static int solveBinary(String s,int x,int y){
        char ch[] = s.toCharArray();
        if(x>y){
            sort1(ch);
        }else{
            sort0(ch);
        }
        int ans = (find_01(ch)*x) + (find_10(ch)*y);
        return ans;
    }
    static void sort0(char s[]){
        int j = -1;
        for(int i=0;i<s.length;i++){
            if(s[i]=='0'){
                j++;
                char temp = s[i];
                s[i] = s[j];
                s[j] = temp;
            }
        }
    }
    static void sort1(char s[]){
        int j = -1;
        for(int i=0;i<s.length;i++){
            if(s[i]=='1'){
                j++;
                char temp = s[i];
                s[i] = s[j];
                s[j] = temp;
            }
        }
    }
    static int find_01(char s[]){
        int temp = 0;
        for(int i=0;i<s.length-1;i++){
            if(s[i]=='0' && s[i+1]=='1'){
                temp++;
                i++;
            }
        }
        return temp;
    }
    static int find_10(char s[]){
        int temp = 0;
        for(int i=0;i<s.length-1;i++){
            if(s[i]=='1' && s[i+1]=='0'){
                temp++;
                i++;
            }
        }
        return temp;
    }



    //29] A1[] is subset of A2[]
    static String isSubset( long a1[], long a2[], long n, long m) {
        HashMap<Long,Integer> hm = new HashMap<>();

        for(int i =0;i<a1.length;i++){
            hm.put(a1[i],i);
        }
        int c = 0;
        for(int j=0;j<a2.length;j++){
            if(hm.containsKey(a2[j])){
                c++;
            }
        }

        if(n<m){
            if(c==n) return "Yes";
            else return "No";
        }else{
            if(c==m) return "Yes";
            else return "No";
        }
    }

    //27] Median in row wise matrix
    static int findMedian(ArrayList<ArrayList<Integer>> mt){
        int low = Integer.MIN_VALUE;
        int high = Integer.MAX_VALUE;
        int n = mt.size(); //row
        int m = mt.get(0).size(); // col

        while (low<=high){
            int mid = low + (high-low)/2;
            int cnt = 0;
            for(int i=0;i<n;i++){
                cnt+= countSmallerThanMedian(mt.get(i),mid);
            }
            if(cnt<= (n*m)/2) low=mid+1;
            else high=mid-1;
        }
        return low;
    }
    static int countSmallerThanMedian(ArrayList<Integer> row,int mid){
        int start = 0;
        int end = row.size()-1;
        while (start<=end){
            int md = start + (end-start)/2;
            if(row.get(md)<=mid){
                start=md+1;
            }else{
                end=mid-1;
            }
        }
        return start;
    }

    //28] Print matrix in spiral manner
    static void print2DMatrixSpiral(int arr[][]){
        int minr = 0;
        int minc = 0;
        int maxr = arr.length-1;
        int maxc = arr[0].length-1;

        int target = arr.length*arr[0].length;
        int count = 0;

        while (count<target){
            //left
            for(int i=minr, j=minc;i<=maxr && count<target;i++){
                System.out.println(arr[i][j]);
                count++;
            }
            minc++;

            //down
            for(int i=maxr, j=minc;j<=maxc && count<target;j++){
                System.out.println(arr[i][j]);
                count++;
            }
            maxr--;

            //right
            for(int i=maxr, j=maxc;i>=minr && count<target;i--){
                System.out.println(arr[i][j]);
                count++;
            }
            maxc--;

            //top
            for(int i=minr, j=maxc;j>=minc && count<target;j--){
                System.out.println(arr[i][j]);
                count++;
            }
            minr++;
        }
    }

    //26] MAX 1's in row
    static int rowWithMax1s(int arr[][], int n, int m) {
        int count = 0, maxCount = Integer.MIN_VALUE, idx = -1;
        for (int row=0;row<n;row++){
            for(int col=0;col<m;col++){
                count = leftOne(arr,row,col) + rightOne(arr,row,col) - 1;
                if(count!=0 && count>maxCount){
                    maxCount = count;
                    idx = row;
                }
            }
        }
        return idx;
    }
    static int leftOne(int arr[][],int row,int col){
        int start = 0;
        int end = arr.length-1;
        int res = 0;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(arr[row][mid]==1){
                res = mid;
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return res;
    }
    static int rightOne(int arr[][],int row,int col){
        int start = 0;
        int end = arr.length-1;
        int res = 0;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(arr[row][mid]==1){
                res = mid;
                start=mid+1;
            }else{
                start=mid+1;
            }
        }
        return res;
    }

    //25] Create N*M matrix and print it
    static void printNM_Matrix(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int mt[][] = new int[n][m];
        System.out.println("N, M : "+mt.length+" "+mt[0].length);
        for(int i=0;i<mt.length;i++){
            for(int j=0;j<mt[0].length;j++){
                mt[i][j] = sc.nextInt();
            }
        }

        for(int i=0;i<mt.length;i++){
            for(int j=0;j<mt[0].length;j++){
                System.out.print(mt[i][j]+" ");
            }
            System.out.println();
        }
    }

    //24] Triplet
    static boolean find3Numbers(int arr[], int n, int X) {
        // o(n^3)
        // o(n^2) + o(n)
        // o(n^2) + o(1)
        Arrays.sort(arr);
        for(int i=0;i<n-2;i++){
            int start = i+1;
            int end = n-1;
            while (start<end){
                if(arr[start]+arr[end]+arr[i]==X){
                    return true;
                }else if(arr[start]+arr[end]+arr[i]<X){
                    start++;
                }else{
                    end--;
                }
            }
        }
        return false;
    }

    //23] Find 2 repetitive el
    static List<Integer> findTwoRepe(int nums[]){
        List<Integer> ans = new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            int index = Math.abs(nums[i])-1;
            if(nums[index]<0) ans.add(index+1);
            nums[index] = -nums[index];
        }
        return ans;
    }

    //22] Min number of jumps to reach end
    static int minNoOfJumps(int arr[]){
        if (arr.length <= 1)
            return 0;

        if (arr[0] == 0)
            return -1;

        int maxReach = arr[0];
        int step = arr[0];
        int jump = 1;

        for (int i = 1; i < arr.length; i++) {
            if (i == arr.length - 1)
                return jump;
            maxReach = Math.max(maxReach, i + arr[i]); // actual jumps from position
            step--;
            if (step == 0) {
                jump++;
                if (i >= maxReach)
                    return -1;
                step = maxReach - i; //remainng step
            }
        }

        return -1;
    }

    //18] Ocuurence = k/n times
    static void findFTimesOcuurence(int arr[],int k){
        int f = arr.length/k;

        TreeMap<Integer,Integer> tm = new TreeMap<>();

        for(int val : arr) tm.put(val,tm.getOrDefault(val,0)+1);

        for(Map.Entry<Integer,Integer> m : tm.entrySet()){
            if( (int) m.getValue()>f){
                System.out.println(m.getKey());
            }
        }
    }

    //17] Minimum element in rotated sorted array
    static int findMinNumb(int arr[]){
        int low=0;int high=arr.length-1;

        while (low<high){ // if arr size is 1
            int mid=(low+high)/2;

            if(arr[mid]<arr[high]){
                high=mid;
            }else{
                low=mid+1;
            }
        }

        return arr[high];

        //complex: o(logn)
    }

    //16] Find longest consecutive subsequence
    static int findLongestSubseq(int arr[]){
        HashSet<Integer> hs = new HashSet<>();
        for(int val : arr) hs.add(val);
        int longStreak = 0;
        for(int i=0;i<arr.length;i++){
            if(!hs.contains(arr[i]-1)){
                int currentNum = arr[i];
                int currentStreak = 1;
                while(hs.contains(currentNum+1)){
                    currentNum+=1;
                    currentStreak+=1;
                }
                longStreak = Math.max(longStreak,currentStreak);
            }
        }
        return longStreak;
    }

    //15] Largest product in subarray
    static long largetProduct(int arr[],int n){

        // gfg
        long minVal = arr[0];
        long maxVal = arr[0];

        long maxProduct = arr[0];

        for (int i = 1; i < n; i++){
            if (arr[i] < 0)
            {
                long temp = maxVal;
                maxVal = minVal;
                minVal =temp;

            }
            maxVal = Math.max(arr[i], maxVal * arr[i]);
            minVal = Math.min(arr[i], minVal * arr[i]);
            maxProduct = Math.max(maxProduct, maxVal);
        }
        return maxProduct;

//        if(arr.length==0) return 0;
//
//        int res = 1, min = 1, max = 1;
//        for(int i=0;i<arr.length;i++){
//            if(arr[i]>0){
//                max = max * arr[i];
//                min = Math.min(min,1);
//            }else if(arr[i]==0){
//                min = max = 1;
//            }else{
//                min = min + max - (max=min);
//                min = min * arr[i];
//                max = Math.max(1, arr[i]*max);
//            }
//            res = Math.max(res,max);
//        }
//        return res;

        //brutforce method => o(n^2)

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

    //14] Factorial code: Linked list
    static void facorial(int n){
        Node tail = new Node(1);
        for(int i=2;i<=n;i++){
            multiply(tail,i);
        }
        print(tail);
    }
    static void multiply(Node tail,int i){
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
    static void print(Node tail){
        if(tail==null) return;
        print(tail.prev);
        System.out.print(tail.data);
    }

    //13] Largest sum in subarray
    static int largestSum(int arr[]){
        int csum = arr[0];
        int osum = arr[0];

        for(int i=1;i<arr.length;i++){
            if(csum>=0){
                csum+=arr[i];
            }else{
                csum=arr[i];
            }

            if(csum>osum){
                osum = csum;
            }
        }

        return osum;
    }

    //12] Subarray sum  == 0
    static boolean findsumZero(int arr[],int n) {
        Set<Integer> hs = new HashSet<Integer>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
        {
            sum += arr[i];

            if (arr[i] == 0
                    || sum == 0
                    || hs.contains(sum))
                return true;
            hs.add(sum);
        }
        return false;
    }

    //11]

    //10] Largest 3rd element o(n)
    static void largestThreeElements(int a[]){
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

    //9] First non repeating element
    static int firstNonRepeatElement(int a[]){
        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int i=0;i<a.length;i++){
            if(!hm.containsKey(a[i])){
                hm.put(a[i],1);
            }else{
                int res = hm.get(a[i]);
                hm.put(a[i],res+1);
            }
        }

        for(int i=0;i<a.length;i++){
            if(hm.get(a[i])==1){
                return a[i];
            }
        }
        return 0;

    }

    //8] First repeating element
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

    //7] Common ele in 3 sorted array
    static ArrayList<Integer> common_element(ArrayList<Integer>v1, ArrayList<Integer>v2) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer,Integer> hm = new HashMap<>();

        for(Integer val : v1){
            if(!hm.containsKey(val)){
                hm.put(val,1);
            }else{
                int res = hm.get(val);
                hm.put(val,res);
            }
        }
        for(Integer val : v2){
            if(hm.containsKey(val) && hm.get(val)>0){
                ans.add(val);
                hm.put(val, hm.get(val)-1);
            }
        }
        Collections.sort(ans);
        return ans;
    }

    //6] Quick sort
    static void sort(int nums[],int low,int hi){
        if(low>=hi) return;

        int start = low;
        int end = hi;
        int mid = start + (end - start)/2;
        int pivot = nums[mid];

        while(start<=end){
            if(nums[start]<pivot){
                start++;
            }
            if(nums[end]>pivot){
                end--;
            }
            if(start<=end){
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }

        //call recursion
        sort(nums,low,end);
        sort(nums,start,hi);

    }

    //5] Duplicate no in array
    static List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            int index = Math.abs(nums[i])-1;
            if(nums[index]<0) ans.add(index+1);
            nums[index] = -nums[index];
        }
        return ans;
    }

    //4] find pairs sum equal to given sum
    static int pairGivenSum(int arr[],int k){
        HashMap<Integer,Integer> m = new HashMap<>();
        int pairs = 0;

        for(int i=0;i<arr.length;i++){
            int diff = k - arr[i];
            if (m.containsKey(diff)) {
                pairs += m.get(diff);
            }
            if(m.containsKey(arr[i])){
                m.put(arr[i], m.get(arr[i])+1);
            }
            else{
                m.put(arr[i], 1);
            }
        }
        return pairs;

    }

    //3] 1-n sum missing element
    static int MissingNumber(int array[], int n) {
        int sum = (n*(n+1))/2;
        int sum1 = 0;
        for(int val: array){
            sum1+=val;
        }

        return sum-sum1;
    }

    //2] cyclic rotation by right
    void cyclicRotateByRight(int a[], int n){
        int b[] =new int[a.length];
        int k=0;
        int z=a.length-n;

        for(int i=z;i<a.length;i++){
            b[k]=a[i];
            k++;
        }

        for (int h=0;h<z;h++){
            b[k]=a[h];
            k++;
        }

        for(int j :b){
            System.out.print(j+", ");
        }


        //Complex: o(n)
    }

    //1] union and intersection of array
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

        // time => o(n); space => o(n+m)

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
    void intersectionOfArrays(int a[],int b[]){
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

        //sorting
//        Arrays.sort(a);
//        Arrays.sort(b);
//        HashSet<Integer> hs = new HashSet<>();
//        int i=0, j=0;
//        while(i<a.length && j<b.length){
//            if(a[i]<b[j]){
//                i++;
//            }else if(a[i]>b[j]){
//                j++;
//            }else{
//                hs.add(a[i]);
//                i++;
//                j++;
//            }
//        }
//
//        int ans[] = new int[hs.size()];
//        int k =0;
//        for(Integer val : hs){
//            ans[k]=val;
//            k++;
//        }
//
//        return ans;

    }

    // --------------- Level 2 ---------------



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
