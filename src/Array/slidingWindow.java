package Array;
import java.util.ArrayList;
import java.util.HashMap;

public class slidingWindow {
    public static void main(String args[]){
//        Identification =>
//            1)Array,String
//            2)Subarray,Substring
//            3)Kth no => window size :
//                               1)Fixed 2)Variable
//            4)Longest,Smallest
        int arr[] = {12, -1, -7, 8, -15, 30, 16, 28};
        negNumbersInWindow_K(arr,3);

    }

    //4] Count of anagrams
    static void countAnagram(String s,String ptr){
        int l=0, r=0;
        HashMap<Character,Integer> hm = new HashMap<>();
        for(int i=0;i<ptr.length();i++){
            if(!hm.containsKey(ptr.charAt(i))){
                hm.put(ptr.charAt(i),1);
            }else{
                hm.put(ptr.charAt(i),hm.get(ptr.charAt(i))+1);
            }
        }
        int count = hm.size();
        int k =count;
        while(r<s.length()){
            if(hm.containsKey(s.charAt(r))){
                hm.put(s.charAt(r),hm.get(s.charAt(r))-1);
            }
            if(hm.get(s.charAt(r))==0){
                count--;
            }

            if(r-l+1 < k){
                r++;
            }else if(r-l+1 == k){

            }
        }
    }

    //3] Fisrt -ve no in every window of k
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

    //2] Longest subarray of sum = k
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
