public class bitManipulation {
    public static void main(String args[]){
        int arr[] = {2,1,2,5,1,4,4,7,3,3};
        //findOutNumber(arr);
        int a = 364;
        int c = 1;
        while (true){
            if((a&1)==1) break;
            a = a>>1;
            c++;
        }
        System.out.println(c);
    }
    // 1] duplicate array 2 no are unique
    static void findOutNumber(int arr[]){
        int xor = 0; // 1 xor 1 = 0;
        // same-same = 0
        // diff = 1

        for(int val : arr){
            xor = xor ^ val;
        }

        int count = 0;
        while (xor == (int)xor){
            if((xor&1)==1) break;
            count++;
            xor = xor>>1;
        }

        int xor1 = 0, xor2 = 0;
        for (int val : arr){
            if((val&(1<<count)) == (int)(val&(1<<count)) ){
                xor1 = xor1 ^ val;
            }else{
                xor2 = xor2 ^ val;
            }
        }

        System.out.println(xor1 + " " + xor2);

    }
}
