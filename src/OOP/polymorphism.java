package OOP;

class A{

    A(){
        System.out.println("It is A");
    }

    void call(){
        System.out.println("call by a");
    }

    void run(){
        System.out.println("run by a");
    }

}


class B extends A{
    B(){
        //super();

        System.out.println("It is B");
    }
//    {
//        System.out.println("Init here");
//    }

    void call(){
        System.out.println("call by b");
    }
    void paly(){
        System.out.println("call by b");
    }
}

public class polymorphism {
    public static void main(String args[]){
        //B o =new B();
        A obj = new B();
    }
}
