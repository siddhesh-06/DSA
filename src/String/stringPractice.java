package String;

import java.util.*;
import java.util.regex.Pattern;

public class stringPractice {
    public static void main(String args[]){

        Scanner sc =new Scanner(System.in);
        stringPractice s = new stringPractice();
        s.reverseByWord("Let's take LeetCode contest");

    }

    // Level 2 Problems

    //3]

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
    void matchBrackets(String input){
//        Stack<Character> stacky = new Stack<>();
//        for (int i = 0; i < input.length(); i++) {
//            if (!stacky.isEmpty()) {
//                switch(input.charAt(i)) {
//                    case '}' : if (stacky.peek() == '{') {
//                        stacky.pop();
//                    } break;
//                    case ']' : if (stacky.peek() == '[') {
//                        stacky.pop();
//                    } break;
//                    case ')' : if (stacky.peek() == '(') {
//                        stacky.pop();
//                    } break;
//                    default: stacky.push(input.charAt(i));
//                }
//            } else {
//                stacky.push(input.charAt(i));
//            }
//        }
//        if(stacky.isEmpty()){
//            System.out.println("Valid");
//        }else{
//            System.out.println("Invalid");
//        }
        Stack<Character> st=new Stack<>();
        for(int i=0;i<input.length();i++){
            if(!st.empty()){
                switch (input.charAt(i)){
                    case ']': if (st.peek() == '['){
                        st.pop();
                    }break;
                    case '}': if (st.peek() == '{'){
                        st.pop();
                    }break;
                    case ')': if (st.peek() == '('){
                        st.pop();
                    }break;
                    default:
                        st.push(input.charAt(i));
                }
            }else{
                st.push(input.charAt(i));
            }
        }
        if(st.isEmpty()){
            System.out.println("Valid");
        }else{
            System.out.println("Invalid");
        }
    }

    //4] Anagram string
    boolean anagramStr(String a,String b){
        //A] Complex: o(nlon(n))
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
