package OOP;

class Hero{
    public  String name;
    public  int age;
    Hero(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void print(){
        System.out.println("Name :"+name);
        System.out.println("Age :"+age);
    }
}

public class basic_constructor {
    public static void main(String args[]){
        Hero hero1 = new Hero("Sid",19);
        Hero hero2 = hero1;

        hero1.name = "Om";
        hero1.print();
        hero2.print();
    }
}
