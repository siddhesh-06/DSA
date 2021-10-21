package RegularExp;

public class Main {
    public static void main(String args[]){
        String string="I am string. Yes, Iam,";
        System.out.println(string);
        String yourString=string.replaceAll("I","You");
        String a="abcdefghabcd"; //You have to replace only which are present at start
        //1]
        System.out.println(a.replaceAll("^abcd","AB")); //Use ^

        System.out.println(a.matches("abcdefghabcd")); //required full string to match

        //2]
        System.out.println(a.replaceAll("abcd$","THE END")); //$ use for end

        //3]

        System.out.println(a.replaceAll("[abc]","X")); // [] contain char which are going to replace
        // [] also case sensative a!=A
        //4]
        String b="harry";
        System.out.println(b.replaceAll("[Hh]","H"));

        //5]
        String newA="abcdeAAAfghabcd12345";
        System.out.println(newA.replaceAll("[^ab]","X")); //here ^ act as negation/not

        //6]
        System.out.println(newA.replaceAll("[a-e2-5]","X"));
        //replace a to e => {a,b,c,d,c,e} & 2 to 5 => {2,3,4,5}
        //but here A to E not replace due to case sensitivity

        //7]
        System.out.println(newA.replaceAll("(?i)[a-e2-5]","X"));
        //Using (?i) also check for capital letters from a to e && A to E

        //8]
        System.out.println(newA.replaceAll("\\d","X"));
        // \\d => 0-9 no replace by X
    }
}
