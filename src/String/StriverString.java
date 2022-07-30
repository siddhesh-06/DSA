package String;


import java.util.Arrays;
import java.util.Locale;

public class StriverString {
    public static void main(String[] args) {
      //  System.out.println(longestPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(naive("abc"));
    }

    //10] Check palindrome
    public boolean isAnagram(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }
    public boolean isAnagram2(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }

        char s1[] = s.toCharArray();
        Arrays.sort(s1);
        char s2[] = t.toCharArray();
        Arrays.sort(s2);

        for(int i=0;i<s.length();i++){
            if(s1[i]!=s2[i]){
                return false;
            }
        }
        return true;

    }

    //9] Min char for palindrome

    // KMP approch

    // Naive approch
    public static int naive(String s){
        int cnt = 0;
        int flag = 0;

        while (s.length()>0){
            if(isPalindrome(s)){
                flag = 1;
                break;
            }else{
                cnt++;
                s = s.substring(0, s.length()-1);
            }
        }
        if(flag==1){
            return cnt;
        }
        return -1;
    }
    public static boolean isPalindrome(String str){
        int i = 0, j = str.length()-1;
        while (i<=j){
            if(str.charAt(i++)!=str.charAt(j--)){
                return false;
            }
        }
        return true;
    }
    // DP
    public static int minCharsforPalindrome(String str) {
        String rev_str = "";
        for(int i=str.length()-1;i>=0;i--){
            rev_str += str.charAt(i);
        }

        int m = str.length();
        int n = str.length();

        int t[][] = new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0 || j==0){
                    t[i][j] = 0;
                }
            }
        }

        return helper(str,rev_str,m,n,t);

    }
    public static int helper(String x, String y, int m, int n, int t[][]){
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(x.charAt(i-1)==y.charAt(j-1)){
                    t[i][j] = 1 + t[i-1][j-1];
                }else{
                    t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
                }
            }
        }

        return m - t[m][n];
    }

    //8] KMP str matching algo

    //7] Z-Function

    //6] Rabin Karp

    //5] Longest prefix
    public static String longestPrefix(String strs[]){
        if(strs.length==0) return "";

        String pref = strs[0];
        String res = "";

        for(int i=1;i<strs.length;i++){
            if(strs[i].indexOf(pref)!=0){
                pref = pref.substring(0, pref.length()-1);
                if(pref.isEmpty()) return "";
            }
        }

        return pref;

    }

    //4] Implement ATOI/STRSTR

    //3] Roman to Int
    public static int romanToInt(String s){
        int nums[] = new int[s.length()];
        for(int i=0;i<s.length();i++){
            switch (s.charAt(i)){
                case 'M':
                    nums[i]=1000;
                    break;
                case 'D':
                    nums[i]=500;
                    break;
                case 'C':
                    nums[i]=100;
                    break;
                case 'L':
                    nums[i]=50;
                    break;
                case 'X' :
                    nums[i]=10;
                    break;
                case 'V':
                    nums[i]=5;
                    break;
                case 'I':
                    nums[i]=1;
                    break;
            }
        }
        int ans = 0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]<nums[i+1]){
                ans -= nums[i]; // VI : (5+1) = 6 but IV : (5-1) = 4
            }else{
                ans += nums[i];
            }
        }
        return ans + nums[nums.length-1];
    }

    //2] Longest palindrome
    public String longestPalindrome(String s){
        int start = 0, end = 0;

        for(int i=0;i<s.length();i++){
            int even = expandFromMiddle(s, i, i+1);
            int odd = expandFromMiddle(s, i, i);
            int len = Math.max(start, end); // len of longest pal string

            if(end - start < len){ // if previous string is small update new one
                start = i + (len-1)/2; // calculating starting ind of new longest str
                end = i + len/2; // calculating ending ind of new longest str
            }
        }

        return s.substring(start, end+1);
    }
    public int expandFromMiddle(String s, int i , int j){
        while (i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){
            i--;
            j++;
        }

        return j-i-1; // returning length of pal string
    }

    //1] Reverse word string
    public String reverseWord(String s){
        String s1[] = s.split(" ");
        String ans = "";

        for(int i=s1.length-1;i>=0;i--){
            if(s1[i].equals("") || s1[i].equals(" ")) continue;
            ans += s1[i]+" ";
        }

        return  ans.trim();
    }
}
