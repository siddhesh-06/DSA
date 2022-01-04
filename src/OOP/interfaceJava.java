package OOP;

// Variables: Whereas variables in interface are final, public and static.
// Methods: public by default

// Interfaces are used to implement abstraction. So the question arises why use interfaces when we have abstract classes?
// The reason is, abstract classes may contain non-final VARIABLES, whereas VARIABLES in interface are final, public and static

// After JDK8,
// 1] We can now add default implementation for interface methods.
// 2] We can now define static methods in interfaces which can be called independently without an object.

// An interface can extends another interface or interfaces (more than one interface)

// From Java 9 onwards, interfaces can contain following also
// 1]Static methods
// 2]Private methods
// 3]Private Static methods

interface player{
    public int a=10;

    default void run(int n){
        System.out.println("Run: "+n);
    }

    default int getA(){
        return  this.a;
    }

//      We can declared public, private methods
//    private void walk() {
//        System.out.println("Byyy");
//    }

    void walk();
    void fight();
}


class cricket implements player {

    @Override
    public void walk() {
        System.out.println("Walking");
    }

    @Override
    public void fight() {
        System.out.println("Fighting");
    }
}

public class interfaceJava {
    public static void main(String args[]){

    }
}
