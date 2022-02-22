package String;
import java.util.*;

public class stringPractice {
    public static int t[][];
    public static void main(String args[]){
        String s ="001";
        System.out.println(alternateBinary(s));
    }


    //27] Alternate binary string
    static int alternateBinary(String s){
        return Math.min(check(s,'0'),check(s,'1'));
    }
    static int check(String s,char ch){
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=ch){
               count++;
            }
            ch = reverse(ch);
        }
        return count;
    }
    static char reverse(char ch){
        return ch=='0' ? '1' : '0';
    }

    //23] Min rotation get same string
    static int minRoatation(String s){
        String temp = s+s;
        for(int i=1;i<=s.length();i++){
            String p = temp.substring(i,i+s.length());

            if(s.equals(p)){
                return i;
            }
        }
        return s.length();
    }

    //24] Second most repeated string in a sequence
    static String secondRepeated(String arr[]){
        HashMap<String,Integer> hm = new HashMap<>();
        int c=1, max1=0, max2=0;
        String ans = "";
        for(int i=0;i<arr.length;i++){
            if(!hm.containsKey(arr[i])){
                hm.put(arr[i],1);
            }else{
                hm.put(arr[i],hm.get(arr[i])+1);
            }
        }

        for(Map.Entry e: hm.entrySet()){
            int val = (int) e.getValue();
            if(val>max1){
                max2 = max1;
                max1=val;
            }else if(val!=max1 && val>max2){
                max2=val;
            }
        }

        for(Map.Entry e: hm.entrySet()){
            if((int) e.getValue()==max2){
                ans+=e.getKey();
            }
        }
        return ans;
    }

    //22] Word Break problem - [IMP]
    static boolean wordBreak(ArrayList<String> dict,String s){
        int dp[] = new int[s.length()];

        for(int i=0;i<dp.length;i++){
            for(int j=0;j<=i;j++){
                String w2check = s.substring(j,i+1);
                if(dict.contains(w2check)){
                    if(j>0){
                        dp[i] += dp[j-1];
                    }else{
                        dp[i] += 1;
                    }
                }
            }
        }

        return dp[s.length()-1]>0;
    }

    //21] Min no of brcket reversals needed to balanced
    static int mimNoOfReversals(String s){
        if(s.length()%2!=0) return -1;
        Stack<Character> st = new Stack<>();
        int op=0,cl=0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch=='{'){
                st.push(ch);
                op++;
            }else if(ch=='}' && !st.isEmpty() && st.peek()=='{'){
                st.pop();
                op--;
            }else cl++;
        }
        if(cl%2!=0) cl = (cl/2)+1;
        else cl=cl/2;

        if(op%2!=0) op= (op/2)+1;
        else op=op/2;

        return op+cl;
    }

    //20] Min no of swaps for bracket balancing
    static int minimumSwapForBrackets(String s){
        int op=0,cl=0,fault=0,swap=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==']'){
                cl++;
                fault = cl-op;
            }else{
                op++;
                if(fault>0){
                    swap+=fault;
                    fault--;
                }
            }
        }
        return swap;
    }

    //19] Bracket match
    static boolean ispar(String x){
        Stack<Character> st = new Stack<>();
        int i=0;

        while(i<x.length()){
            if(!st.isEmpty()){
                switch(x.charAt(i)){
                    case '}':
                        if(st.peek()=='{'){
                            st.pop();
                        }else{
                            st.push(x.charAt(i));
                        }
                        break;
                    case ')':
                        if(st.peek()=='('){
                            st.pop();
                        }else{
                            st.push(x.charAt(i));
                        }
                        break;
                    case ']':
                        if(st.peek()=='['){
                            st.pop();
                        }else{
                            st.push(x.charAt(i));
                        }
                        break;
                    default:
                        st.push(x.charAt(i));
                }
            }else{
                st.push(x.charAt(i));
            }
            i++;
        }

        if(st.isEmpty()){
            return true;
        }else{
            return false;
        }

    }

    //18] Convert mobile no to keypad
    static String numberToKeypad(String s){
        String str[] = {"2","22","222",
                "3","33","333",
                "4","44","444",
                "5","55","555",
                "6","66","666",
                "7","77","777","7777",
                "8","88","888",
                "9","99","999","9999"
        };

        int n = s.length();
        String op = "";
        for(int i=0;i<n;i++){
            if(s.charAt(i)== ' '){
                op+="0";
            }else{
                int pt = s.charAt(i) - 'A';
                op += str[pt];

            }
        }
        return op;
    }

    //17] All expresions conversion
    static String prefixToInfix(String s){
        Stack<String> st = new Stack<>();
        for(int i=s.length()-1;i>=0;i--){
            if(checkOperator(s.charAt(i))){
                String ch1 = st.pop();
                String ch2 = st.pop();
                String temp = "("+ch1+s.charAt(i)+ch2+")";
                st.push(temp);
            }else{
                st.push(s.charAt(i)+"");
            }
        }
        String ans = st.peek();
        return ans;
    }
    static boolean checkOperator(char c){
        switch (c){
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
                return true;
        }
        return false;
    }

    //16] Next permutation
    static void nextPermutation(int[] nums) {
        // 13542
        int n = nums.length;

        int i = n-2;
        while(i>=0 && nums[i]>=nums[i+1]) i--;
        if(i>=0){ //checking is there any break point
            int j = n-1;
            while(nums[j]<=nums[i]){
                j--;
            }
            swap(nums,i,j);
        }
        // if no then only reverse array beacuase given array should be last permutation
        reverse(nums,i+1,n-1);
    }
    static void swap(int arr[], int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static void reverse(int arr[],int i,int j){
        while(i<j){
            swap(arr,i,j);
            i++;
            j--;
        }
    }

    //15] EDIT distance
    static int editDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        return dpSolve(s,t,m,n);
    }
    static int dpSolve(String s,String t,int m,int n){
        int dp[][] = new int[m+1][n+1];

        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0) dp[i][j] = j;
                else if(j==0) dp[i][j] = i;
                else if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int temp = Math.min(dp[i-1][j],dp[i][j-1]); //Insert Delete
                    dp[i][j] = 1 + Math.min(temp,dp[i-1][j-1]); //Replace
                }
            }
        }

        return dp[m][n];
    }

    //12] Longest common subsequence
    static int lcm(String s1,String s2){
        int m = s1.length();
        int n = s2.length();

        // Initialize dp
        int t[][] = new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0 || j==0) t[i][j] = 0;
            }
        }

        //Perform logic
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    t[i][j] = 1 + t[i-1][j-1];
                }else{
                    t[i][j] = Math.max(t[i-1][j],t[i][j-1]);
                }
            }
        }

        return t[m][n];
    }

    //11] Number of Substrings with count of each character as “K”
    static int longestSubstring_K(String s,int k){
        int i = 0,j=0;
        HashMap<Character,Integer> hm = new HashMap<>();
        int min = Integer.MAX_VALUE;

        while (j<s.length()){
            if(!hm.containsKey(s.charAt(j))){
                hm.put(s.charAt(j),1);
            }else{
                hm.put(s.charAt(j),hm.get(s.charAt(j))+1);
            }
            if(hm.size()<k){
                j++;
            }else if(hm.size()==k){
                min = Math.min(min,j-i+1);  //window size => j-i+1
                j++;
            }else if(hm.size()>k){
                while (hm.size()>k){
                    hm.put(s.charAt(i),hm.get(s.charAt(i))-1);
                    if(hm.get(s.charAt(i))==0){
                        hm.remove(s.charAt(i));
                    }
                    i++;
                }
                j++;
            }
        }

        return min;
    }

    //10] Smallest window that contains all chars
    static int smallWindow_distinct(String s){
        HashSet<Character> hs = new HashSet<>();
        for(char c : s.toCharArray()) hs.add(c);
        int k = hs.size();
        HashMap<Character,Integer> hm = new HashMap<>();
        int min = Integer.MAX_VALUE;
        hm.put(s.charAt(0),1);
        int i=0,j=1,c=0;
        c++;

        while (i<=j && j<s.length()){
            if(c<k){
                if(!hm.containsKey(s.charAt(j))){
                    c++;
                    hm.put(s.charAt(j),1);
                }else{
                    hm.put(s.charAt(j),hm.get(s.charAt(j))+1);
                }
                j++;
            }else if(c==k){
                min = Math.min(min,j-i);
                if(hm.get(s.charAt(i))==1) c--;
                hm.put(s.charAt(i),hm.get(s.charAt(i))-1);
                i++;
            }
        }
        while(c==k){
            min = Math.min(min,j-i);
            if(hm.get(s.charAt(i))==1) c--;
            hm.put(s.charAt(i),hm.get(i)-1);
            i++;
        }

        return min;
    }

    //9] Rearrange char in str that no 2 adj are same
    static int checkAdjacentChar(String s){
        int max = Integer.MIN_VALUE;
        HashMap<Character,Integer> hm = new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(!hm.containsKey(s.charAt(i))){
                hm.put(s.charAt(i),1);
            }else{
                hm.put(s.charAt(i),hm.get(s.charAt(i))+1);
            }
            max = Math.max(max,hm.get(s.charAt(i)));
        }

        if(max<=s.length()-max+1) return 1;
        else return 0;
    }

    //8] Split the Binary string into two substring with equal 0’s and 1’s.
    static int balancedStringSplit(String s) {
        int c = 0, c0 = 0, c1 = 0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0'){
                c0++; // 0 => count
            }else{
                c1++; // 1 => count
            }

            if(c0==c1){
                c++;  // when both are equals
            }
        }

        if(c0!=c1) return -1;
        return c;
    }

    //7] Permutations of a given string
    static List<String> find_permutation(String S) {
        List<String> ans = new ArrayList<>();
        givePermu(S,0,"",ans);
        return ans;
    }
    static void givePermu(String s, int idx, String s1, List<String> ans) {
        if(s.length()==0){
            ans.add(s1);
            return;
        }
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            String ros = s.substring(0,i) + s.substring(i+1);
            givePermu(ros,i,s1+ch,ans);
        }
    }

    //6] Print all Subsequences of a string.
    static List<String> allSubsequences(String str){
        List<String> ans = new ArrayList<>();
        solveSubsequence(str,"",ans);
        return ans;
    }
    static void solveSubsequence(String str,String ds,List<String> ans){
        if(str.length()==0){
            ans.add(ds);
            return;
        }
        char ch = str.charAt(0);
        String ros = str.substring(1);

        // if char add
        solveSubsequence(ros,ds+ch,ans);

        // if not
        solveSubsequence(ros,ds,ans);
    }

    //5] Longest Repeating Subsequence
    static int LongestRepeatingSubsequence(String str){
        String str2 = str;
        dpMatrix(str.length(),str2.length());
        int ans = solve(str,str2);
        return ans;
    }
    static void dpMatrix(int m,int n){
        t = new int[m+1][n+1];

        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0 || j==0){
                    t[i][j] = 0;
                }
            }
        }
    }
    static int solve(String str, String str2){
        int m = str.length();
        int n = str2.length();

        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(str.charAt(i-1) == str2.charAt(j-1) && i!=j ){
                    t[i][j] = t[i-1][j-1] + 1;
                }else{
                    t[i][j] = Math.max(t[i][j-1],t[i-1][j]);
                }
            }
        }
        return t[m][n];

    }

    //4] Longest palindrome => odd-even method
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

    //3] Valid shuffle or not
    static boolean isValidSuffle(String a,String b,String c){
        int i=0, j=0, k=0;

        while(k<c.length()){
            if(i<a.length() && a.charAt(i)==c.charAt(k)){
                i++;
            }else if(j<b.length() && b.charAt(j)==c.charAt(k)){
                j++;
            }else{
               return false;
            }
            k++;
        }
        return true;
    }

    //2] Remove duplicate Char
    public static void removeDup(String a){

        // Here we dont use HashSet because it can't maintain order of elements.
        // but, LinkedHashSet will maintain the order of elements.
        // HashSet arrange according to acci value of that char

        LinkedHashSet<Character> h =new LinkedHashSet<>();

        for(int i=0;i<a.length();i++){
            h.add(a.charAt(i));
        }
        String temp = "";
        for(char i: h){
            temp+=i;
        }
        System.out.println(temp);

        //Use iterator to print all elements in HashSet
//        Iterator I = h.iterator();
//        while(I.hasNext()){
//            System.out.print(I.next());
//        }
    }

    //1] One string is a rotation of another.
    public static void rotationString(String a,String b){
        if(a.length()==b.length() && (a+b).indexOf(b) != -1){
            System.out.println("Rotaion of another");
        }else{
            System.out.println("Not rotation of another.");
        }
    }

    // --------- Level 2 Problems ----------


    //12] Reverse String
    public String reverseString(String a){
        String reverse="";
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)=='1'){
                reverse=reverse+'0';
            }else if(a.charAt(i)=='0'){
                reverse=reverse+'1';
            }
        }
        return reverse;
    }

    //11] Reverse string using stack
    static void reverseStringUsingStack(String s){
        int l = s.length();
        Stack<Character> st=new Stack<>();
        for(int i =0;i<l;i++){
            st.push(s.charAt(i));
        }
        while(!st.isEmpty()){
            System.out.print(st.peek());
            st.pop();
        }
    }

    //10] Repeated string
    public static long repeatedString(String s, long n) {
        long numOfS = n/s.length();
        long rest = n % s.length();

        if(!s.contains("a")) return 0;
        return s.length()>n? aCounter(s, rest)
                : numOfS*aCounter(s, s.length()) + aCounter(s, rest);
    }
    static long aCounter(String s, long end) {
        int a=0;
        for (int i = 0; i < end; i++) {
            if (s.charAt(i) == 'a') a++;
        }
        return a;
    }

    //8] Remove duplicate word
    static void removeDuplicate(String str){
        HashSet<Character> h = new HashSet<>();
        int l = str.length();
        char strArray[] = str.toCharArray();
        for(int i=0;i<l;i++){
            h.add(strArray[i]);
        }
        String ans="";
        for(char i: h){
            ans=ans+i;
        }
        System.out.println(ans);
    }

    //7] Palindrome
    static boolean palindrome(String str){
        int i=0,j=str.length()-1;
        while(i<j){
            if(str.charAt(i)!= str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //6] String reverse by word
    static void reverseByWord(String str){
        //Complex: o(n)
        Stack<Character> st=new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ')
                st.push(str.charAt(i));
            else {
                while (st.empty() == false) {
                    System.out.print(st.pop());
                }
                System.out.print(" ");
            }
        }
        while (st.empty() == false) {
            System.out.print(st.pop());
        }
    }

    //5] Brackets Match
    static void matchBrackets(String input){
        Stack<Character> stacky = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if (!stacky.isEmpty()) {
                switch(input.charAt(i)) {
                    case '}' : {
                        if (stacky.peek() == '{') {
                            stacky.pop();
                        }else{
                            stacky.push(input.charAt(i));
                        }
                    }break;
                    case ']' : {
                        if (stacky.peek() == '[') {
                            stacky.pop();
                        }else{
                            stacky.push(input.charAt(i));
                        }
                    } break;
                    case ')' : {
                        if (stacky.peek() == '(') {
                            stacky.pop();
                        }else{
                            stacky.push(input.charAt(i));
                        }
                    }break;
                    default: stacky.push(input.charAt(i));
                }
            } else {
                stacky.push(input.charAt(i));
            }
        }
        if(stacky.isEmpty()){
            System.out.println("Valid");
        }else{
            System.out.println("Invalid");
        }
    }

    //4] Anagram string
    static boolean anagramStr(String a,String b){
        //A] Complex: o(nlon(n)+n) => o(n)
        if(a.length()!=b.length()) return false;

        char s1[]=a.toLowerCase().toCharArray();
        char s2[]=b.toLowerCase().toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);

        for(int i=0;i<a.length();i++){
            if(s1[i]!=s2[i]) return false;
        }
        return true;
    }

    //3] Duplicate characters
    static void duplicateChar(String a){
        //Complex: o(n^2)
        char b[]=a.toCharArray();
        System.out.print("Duplicate char are: ");
        for(int i=0;i<b.length;i++){
            for(int j=i+1;j<b.length;j++){
                if(b[i]==b[j]){
                    System.out.print(b[j]+",");
                }
            }
        }
    }

    //2] Remove white spaces in array
    static String removeWhiteSpace(String a){
        //Complex: o(n)
        a=a.replace(" ","");
        return a;
    }

    //1] Find no of char in string
    static void noOfEachChar(String a){
        //Complex: o(n^2)
        char check;
        int i=0;
        while(i<a.length()){
            int c=0,j=0;
            check=a.charAt(i);
            while(j<a.length()){
                if(check==a.charAt(j)){
                    c++;
                }
                j++;
            }
            System.out.println("No of occurence of "+check+": "+c);
            i++;
        }
    }
}
