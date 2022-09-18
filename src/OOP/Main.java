package OOP;
import java.util.*;

class Parent {
    String name;

    // A method which prints the data of the parent class
    void showMessage()
    {
        System.out.println("Parent method is called");
    }
    void older(){
        System.out.println("Older");
    }
}

// Child class
class Child extends Parent {
    int age;

    // Performing overriding
    @Override
    void showMessage()
    {
        System.out.println("Child method is called");
    }
}



public class Main {
    public static void main(String[] args)
    {
        Parent p = new Child();
        p.name = "Shubham";


        // Performing Downcasting Implicitly
        //Child c = new Parent(); // it gives compile-time error

        // Performing Downcasting Explicitly
        Child c = (Child)p;

        c.age = 18;
        System.out.println(c.name);
        System.out.println(c.age);
        c.showMessage();
    }

    public static void palinDromeNum(){
        int n = 454;
        int temp = n, res = 0, sum = 0;
        while (n>0){
            res = n%10;
            sum = (sum*10) + res;
            n = n /10;
        }
        if(sum==temp) System.out.println("YES");
        else System.out.println("NO");
    }
    public static void trap(int[] height) {
        int n = height.length;
        //prefix
        int prefix[] = new int[n];
        prefix[0] = height[0];
        int p = prefix[0];
        for(int i=1;i<n;i++){
            if(prefix[i-1]<height[i]){
                p = height[i];
                prefix[i] = height[i];
            }else{
                prefix[i] = p;
            }
        }
        System.out.println(Arrays.toString(prefix));

        //suffix
        int suffix[] = new int[n];
        suffix[n-1] = height[n-1];
        int s = suffix[n-1];
        for(int i=n-2;i>=0;i--){
            if(suffix[i+1]<height[i]){
                s = height[i];
                suffix[i] = height[i];
            }else{
                suffix[i] = s;
            }
        }
        System.out.println(Arrays.toString(suffix));
        int ans = 0;
        for(int i=0;i<n;i++){
            ans += Math.min(suffix[i], prefix[i]) - height[i];
        }
        System.out.println(ans);
    }

}


















