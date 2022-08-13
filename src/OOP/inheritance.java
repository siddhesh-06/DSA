package OOP;

class human{
    int color;
    private int age;

    public void eat(){
        System.out.println("Human eat");
    }

    public void run(){
        System.out.println("Human runnig");
    }
}

class men extends human{
    int name;

    public void eat(){
        System.out.println("Man eat");
    }

    public void play(){
        System.out.println("Man playing");
    }
}

public class inheritance {
    public static void main(String args[]){
        human m = new men(); // up cast => only give access of overrided method
        men p = (men) m; // down cast => gives parent methods also
        men mm = new men();

    }
}
