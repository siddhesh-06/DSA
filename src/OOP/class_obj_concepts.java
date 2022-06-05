package OOP;

class anime{
    static String name = "Goku";
    int a;
    anime(){}
    anime(int a, String name){
        this.a = a;
        anime.name = name;
    }

    static void print(){

    }
}


public class class_obj_concepts {
    public static void main(String args[]){
        anime a = new anime();

        System.out.println(anime.name);
        anime a1 = new anime(2,"Sid");
        System.out.println(anime.name);
    }
}
