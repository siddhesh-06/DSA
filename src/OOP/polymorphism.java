package OOP;

class A{
    public void hey(){
        System.out.println("Class A");
    }
}


class B extends A{
   public void hey(){
        System.out.println("Class B");
    }
   public void hey(int a){
        System.out.println(a);
    }
}

public class polymorphism {
    public static void main(String args[]){
        B o =new B();
        o.hey();
    }
}
