package String;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class stringPractice {
    public static int t[][];
    public static void main(String args[]){
        String a = "abc";
        System.out.println(find_permutation(a));
    }

    static String sortSentence(String s){
        String arr[] = s.split(" ");
        String temp[] = new String[arr.length];
        for(String w : arr){
            int index = Integer.parseInt(w.substring(w.length()-1)) -1;
            temp[index] = w.substring(0,w.length()-1);
        }
        String ans = "";
        for(String ros : temp){
            ans+=" "+ros;
        }
        return ans.substring(0,ans.length()-1);

    }

    //10]
    static int findSubString( String str) {
        int c = 0;
        String str1 = str.toLowerCase();
        HashMap<Integer,Character> hm = new HashMap<>();
        for(int i=0;i<str.length();i++){
            if(!hm.containsValue(str1.charAt(i))){
                hm.put(i,str1.charAt(i));
            }else{
                c++;
            }
        }
        return c;
    }

    //8] Split the Binary string into two substring with equal 0’s and 1’s.
    static int balancedStringSplit(String s) {
        int c = 0, c0 = 0, c1 = 0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0'){
                c0++;
            }else{
                c1++;
            }

            if(c0==c1){
                c++;
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
        solveSubsequence(str.substring(1),ds+str.charAt(0),ans);
        solveSubsequence(str.substring(1),ds,ans);
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
    static String longestPalin(String S){
        if(S==null || S.length()<1) return "";

        int start =0 ,end=0;
        for(int i=0;i<S.length();i++){
            int len1 = expandFromMiddle(S,i,i);
            int len2 = expandFromMiddle(S,i,i+1);
            int len = Math.max(len1,len2);
            if(len > end - start){
                start = i - (len-1)/2;
                end = i + (len/2);
            }
        }
        return S.substring(start,end+1);
    }
    static int expandFromMiddle(String s,int left,int right){
        if(s==null || left>right) return 0;

        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1; // index of that pt
    }

    //2] Remove duplicate Char
    public static void removeDup(String a){

        // Here we dont use HashSet because it can't maintain order of elements.
        // but, LinkedHashSet should maintain the order of elements.

        LinkedHashSet<Character> h =new LinkedHashSet<>();

        for(int i=0;i<a.length();i++){
            h.add(a.charAt(i));
        }
        StringBuilder temp= new StringBuilder();
        for(char i: h){
            temp.append(i);
            System.out.print(i);
        }

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

    public void chef(int n){

    }

    //11] Reverse string using stack
    public void reverseStringUsingStack(String s){
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

    private static long aCounter(String s, long end) {
        int a=0;
        for (int i = 0; i < end; i++) {
            if (s.charAt(i) == 'a') a++;
        }
        return a;
    }

    //8] Remove duplicate word
    void removeDuplicate(String str){
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
    boolean palindrome(String str){
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
    void reverseByWord(String str){
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
    boolean anagramStr(String a,String b){
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
    void duplicateChar(String a){
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
    String removeWhiteSpace(String a){
        //Complex: o(n)
        a=a.replace(" ","");
        return a;
    }

    //1] Find no of char in string
    void noOfEachChar(String a){
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
