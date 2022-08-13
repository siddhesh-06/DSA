import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        Scanner sc = new Scanner(System.in);

//        //int test_case = Integer.parseInt(br.readLine());
//        long test_case = sc.nextLong();
//
//        for (long test = 0; test < test_case; test++) {
//
//        }



    }

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        int b = buses.length;
        int p = passengers.length;

        Arrays.sort(buses);
        Arrays.sort(passengers);
        boolean vis[] = new boolean[p];
        Arrays.fill(vis, false);
        int pass = -1;

        for(int i=0;i<b;i++){
            int bus = buses[i];
            int st = capacity;

            for(int j=0;j<p;j++){
                if(vis[j]==false){
                    if(passengers[j]<=bus && st>0){
                        vis[i] = true;
                        st--;
                        pass = passengers[i];
                    }else break;
                }
            }
        }
        return pass;
    }


}

























