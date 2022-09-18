import java.util.*;

public class Main {
    public static void main(String[] args){
        String words[] = {"a", ""};
        System.out.println(palindromePairs(words));
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<String> hs = new HashSet<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) continue;
                String curr = words[i]+words[j];
                int l = curr.length();
                int w1 = words[i].length();
                int w2 = words[j].length();

                if(isPalindrome(curr)){
                    if(!hs.contains(curr)){
                        List<Integer> ds = new ArrayList<>();
                        ds.add(i);
                        ds.add(j);
                        ans.add(ds);
                    }else if(l==w1 || l==w2){
                        List<Integer> ds = new ArrayList<>();
                        ds.add(i);
                        ds.add(j);
                        ans.add(ds);
                    }
                    hs.add(curr);
                }
            }
        }

        return ans;
    }

    public static boolean isPalindrome(String str){
        int s = 0;
        int e = str.length()-1;

        while (s<=e){
            if(str.charAt(s)!=str.charAt(e)){
                return false;
            }
            s++;
            e--;
        }
        return true;
    }
}

























