package Stack;

import java.util.ArrayList;
import java.util.List;

public class MaxStack {

    public static void main(String[] args) {

    }
    public static List<Integer> getMax(List<String> operations) {
        // Write your code here
        // Build monotonic stack : which give max elemenet;
        List<Integer> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        int n = operations.size();

        for(int i=0;i<n;i++){
            String s = operations.get(i);
            String ch[] = s.split(" ");

            String choice = ch[0];
            int val = 0;
            if(choice.equals("1")){
                val = Integer.parseInt(ch[1]);
            }
            int maxi[] = new int[1];

            switch (choice){
                case "1":
                    addElement(ds, val, maxi);
                    break;
                case "2":
                    removeTop(ds, maxi);
                    break;
                case "3":
                    System.out.println(maxi[0]);
                    ans.add(maxi[0]);
                    break;
            }
        }

        return ans;

    }
    public static void addElement(List<Integer> ds, int val, int maxi[]){
        if(ds.isEmpty()){
            maxi[0] = val;
            ds.add(val);
            return;
        }

        if(val>maxi[0]){
            ds.add(2*val -maxi[0]);
            maxi[0] = val;
        }else{
            ds.add(val);
        }

    }
    public static void removeTop(List<Integer> ds, int maxi[]){
        if(ds.isEmpty()){
            return;
        }

        int t = ds.get(ds.size()-1);
        ds.remove(ds.size()-1);

        if(t>maxi[0]){
            maxi[0] = 2*maxi[0] -t;
        }else{
            return;
        }
    }
}
