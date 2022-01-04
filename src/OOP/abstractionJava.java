package OOP;

abstract class car{

    private String color;
    final int modelNo=5;

    car(String color){
        this.color=color;
    }

    //concrete methods :
    // 1) getter setter
    // 2) using final & static keyword

    String getColor(){
        return this.color;
    }

    int getModelNo(){
        return this.modelNo;
    }

    final void speed(){
        System.out.println("Speed up: "+getModelNo());
    }

    static void breakDown(){
        System.out.println("Stop");
    }

    abstract void run(int n);
    // Only you can declare methods
    // For method "abstract" required  keyword compulsory
}

class bmw extends car{

    bmw(){
        super("red");
    }

    // You have to implement all methods which are present in abstract class
    void run(int n){
        System.out.println("Run: "+n);
    }

}


public class abstractionJava {
    public static void main(String args[]){
        car c=new bmw();
        c.speed();
    }
}
