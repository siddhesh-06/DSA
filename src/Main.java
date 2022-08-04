import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        Scanner sc = new Scanner(System.in);

        //int test_case = Integer.parseInt(br.readLine());
        int test_case = sc.nextInt();
        for(int test = 0;test<test_case;test++){
            long m = sc.nextLong();

            long ans = 0;
            int ind = -1;
            for(int i=0;i<=9;i++){
                long pow = (long) Math.pow(10,i);
                if(m<pow){
                    ind = i;
                    break;
                }
            }

            ans = m- (long) Math.pow(10,ind-1) ;
            System.out.println(ans);
        }

    }

}


























