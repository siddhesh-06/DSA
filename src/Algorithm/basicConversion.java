package Algorithm;

public class basicConversion {
    public static void main(String args[]){
        int a ='G';
        System.out.println(a);
        System.out.println('A');
        System.out.println('G'-'A');
    }

    static int charToInt(char a){
        return a - '0';
    }

    static char intToChar(int a){
        char ch = (char)a;
        return ch;
    }

    static String intToString(int a){
        String ans = String.valueOf(a);
        return ans;
    }

    static int StringToInt(String a){
        int ans = Integer.parseInt(a);
        return ans;
    }
}

