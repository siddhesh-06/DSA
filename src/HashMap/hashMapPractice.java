package HashMap;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class hashMapPractice {
    public static void main(String args[]){
        hashMapPractice h =new hashMapPractice();
        int arr[] = { 1, 1, 2, 2, 2, 3, 3, 3 };
        String str = "Hey bro bro";
        int a=3;
        int b=2;
        //System.out.println(h.appearsbTimes(arr,arr.length,a,b));

        System.out.println(h.distrubution(arr,arr.length));

    }

    public int distrubution(int arr[],int n){
        Arrays.sort(arr);
        int count=1;
        for(int i=1;i<n;i++){
            if(arr[i]>arr[i-1]){
                count++;
            }
        }
//        Set<Integer> s =new HashSet<>();
//        for (int i = 0; i < n; i++)
//            s.add(arr[i]);
//        return Math.min(s.size(), n / 2);

        return Math.min(count,n/2);
    }

    public void removeDuplicateWord(String s){
        // use to seprate word
        String[] words = s.split("\\s");

        for(int i =0;i<words.length;i++){
            for(int j=0;j<words.length;j++){
                if(words[i].equals(words[j])){
                    if(i!=j){
                        words[i]="";
                    }

                }
            }
        }
        for(String k: words){
            if(k!=""){
                System.out.print(k+" ");
            }
        }

    }

    public int appearsbTimes(int arr[],int n,int a,int b){
        HashSet<Integer> hs = new HashSet<>();
        int a_sum=0;
        int sum=0;
        for(int i=0;i<n;i++){
            if(!hs.contains(arr[i])){
                hs.add(arr[i]);
                a_sum+=arr[i];
            }
            sum+=arr[i];
        }
        a_sum=a*a_sum;
        return ((a_sum-sum)/(a-b));

    }

    public void findRepeatedNumber(int arr[],int l){
        HashSet<Integer> h =new HashSet<>();
        for(int i=0;i<l;i++){
            if(h.contains(arr[i])){
                System.out.println(arr[i]+", ");
            }
            h.add(arr[i]);
        }
    }
}
