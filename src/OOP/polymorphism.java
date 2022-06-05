package OOP;

class A{

    A(){
        System.out.println("It is A");
    }
}


class B extends A{
    B(){
        super();

        System.out.println("It is B");
    }
    {
        System.out.println("Init here");
    }
}

public class polymorphism {
    public static void main(String args[]){
        B o =new B();
    }
}
