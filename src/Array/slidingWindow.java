package Array;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class slidingWindow {
    public static void main(String args[]) throws IOException{
        int arr[] = {1,3,-1,-3,5,3,6,7};
        System.out.println(maxSubarray(arr,3));

    }

    static int solve(String words[], String s){
        if(words.length<=0 || s.length()<=0) return 0;
        int c = 0; boolean flag = false;
        for(String s1 : words){
            for(int i=0;i<s1.length();i++){
                if(s.length()>=s1.length() && s.charAt(i)==s1.charAt(i)){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
            if(flag){
                flag = false;
                c++;
            }
        }
        return c;
    }

    // 7] Longest substring without repeating characters
    static int longestWithoutRepeat(String s){
        HashSet<Character> hs = new HashSet<>();
        if(s.length()==0) return 0;

        int i = 0, j = 0, ans = 0;
        while(j<s.length()){
            if(!hs.contains(s.charAt(j))){
                hs.add(s.charAt(j));
                ans = Math.max(ans, hs.size());
                j++;
            }else{
                hs.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    // 6] Longest substring with k unique char
    static int longestUnique(String s, int key){
        HashMap<Character, Integer> hm = new HashMap<>();
        int i =0, j = 0;
        int ans = 0;
        while (j<s.length()){
            char ch = s.charAt(j);
            if(!hm.containsKey(ch)){
                hm.put(ch, 1);
            }else{
                hm.put(ch, hm.get(ch)+1);
            }

            if(hm.size()<key){
                j++;
            }else if(hm.size()==key){
                ans = Math.max(ans, j-i+1);
                j++;
            }else if(hm.size()>key){
                while (hm.size()>key){
                    char rm = s.charAt(i);
                    hm.put(rm, hm.get(rm)-1);
                    if(hm.get(rm)==0) hm.remove(rm);
                    i++;
                }
                j++;
            }
        }
        return ans;
    }

    //5] Longest subarray of sum = k
    static int longestSubarrayOf_K(int arr[],int k){
        int i=0,j=0;
        int sum=0,max=0;

        while (j<arr.length){
            sum+=arr[j];
            if(sum<k){
                j++;
            }else if(sum==k){
                max = Math.max(max,j-i+1);
                j++;
            }else if(sum>k){
                while (sum>k){
                    sum=sum-arr[i];
                    i++;
                }
                j++;
            }
        }
        return max;
    }

    //4] Maximun of all subarray
    static int[] maxSubarray(int arr[], int k){
        if(k>arr.length){
            int ans[] = new int[1];
            int max = Integer.MIN_VALUE;
            for(int i : arr){
                max = Math.max(i,max);
            }
            ans[0] = max;
            //ans.add(max);
            return ans;
        }else{
            int ans[] = new int[arr.length-k+1];
            Queue<Integer> q = new LinkedList<>();
            int i=0,j=0,index=0;

            while (j<arr.length){
                while (q.size()>0 && arr[j]>q.peek()){
                    q.remove();
                }
                q.add(arr[j]);
                if(j-i+1 <k){
                    j++;
                }else if(j-i+1 == k){
                    ans[index] = q.peek();
                    //ans.add(q.peek());
                    if(q.peek()==arr[i]){
                        q.poll();
                    }
                    i++;
                    j++;
                    index++;
                }
            }

            return ans;
        }

    }

    //3] Count of anagrams
    static int countAnagram(String s,String ptr){
        int i=0, j=0;
        int k = ptr.length();
        HashMap<Character,Integer> hm = new HashMap<>();
        List<Integer> ans1 = new ArrayList<>();
        // Map for => Pattern
        for(int p=0;p<ptr.length();p++){
            if(!hm.containsKey(ptr.charAt(p))){
                hm.put(ptr.charAt(p),1);
            }else{
                hm.put(ptr.charAt(p),hm.get(ptr.charAt(p))+1);
            }
        }

        int count = hm.size();
        int ans=0;

        while (j<s.length()){
            char ch = s.charAt(j);
            if(hm.containsKey(ch)) {
                hm.put(ch,hm.get(ch)-1);
                if(hm.get(ch)==0) count--;
            }

            if(j-i+1 <k){

                j++;
            }else if(j-i+1 ==k){
                if(count==0){
                    ans++;
                    ans1.add(i);
                }
                char eli = s.charAt(i);
                if(hm.containsKey(eli)){
                    hm.put(eli,hm.get(eli)+1);
                    if(hm.get(eli)==1){
                        count++;
                    }
                }
                i++;
                j++;
            }
        }

        return ans;
    }

    //2] First -ve no in every window of k
    static void negNumbersInWindow_K(int arr[],int k){
        int i=0,j=0;
        ArrayList<Integer> ans = new ArrayList<>();
        while (j<arr.length){
            if(j-i+1 < k){
                j++;
            }else if(j-i+1 == k){
                int p = i;
                boolean insert = false;
                while(p<=j){
                    if(arr[p]<0){
                        ans.add(arr[p]);
                        insert=true;
                        break;
                    }
                    p++;
                }
                if(!insert) ans.add(0);
                i++;
                j++;
            }
        }

        System.out.println(ans);

    }

    //1] Max sum subarray of size K
    static int maxSubarray_K(int arr[],int k){
        int i=0,j=0;
        int n=arr.length;
        int sum=0,max=Integer.MIN_VALUE;

        while(j<n){
            sum+=arr[j];
            if(j-i+1 < k){
                j++;
            }else if(j-i+1 == k){
                max = Math.max(max,sum);
                sum=sum-arr[i];
                i++;
                j++;
            }
        }

        return max;
    }

}
