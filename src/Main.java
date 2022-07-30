
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
            int n = sc.nextInt();
            String s = sc.next();

            HashSet<Character> hs = new HashSet<>();

            boolean flag = true;
            for(int i=0;i<n-1;i++){
                if(s.charAt(i)==s.charAt(i+1)) continue;
                if(!hs.contains(s.charAt(i))){
                    hs.add(s.charAt(i));
                }else{
                    flag = false;
                    break;
                }
            }
            if(hs.contains(s.charAt(n-1))) flag = false;

            if(flag){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }

    }

}


























