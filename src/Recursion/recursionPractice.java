package Recursion;
import java.util.*;

public class recursionPractice {
    static String codes[] = {"-",".;","abc","def","ghi","jkl","mno","pqrs","tu","vwx","yz"};
    static int di[] = {-1,0,1,0};
    static int dj[] = {0,-1,0,1};

    public static void main(String args[]){
        printKepad("22233","");
    }

    // 25] Permutation - 2
    static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean track[] = new boolean[nums.length];
        List<Integer> ds = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums,track,ans,ds);
        return ans;
    }
    static void helper(int[] nums, boolean[] track, List<List<Integer>> ans, List<Integer> ds){
        if(ds.size()==nums.length){
            ans.add(new ArrayList<>(ds));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(track[i]) continue;
            track[i] = true;
            ds.add(nums[i]);
            helper(nums,track,ans,ds);
            ds.remove(ds.size()-1);
            track[i] = false;

            while(i+1 < nums.length && nums[i] == nums[i+1]){
                i++;
            }
        }
    }

    // 24] Word search in board
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == word.charAt(0)){
                    boolean res = explore(board,i,j,0,word);
                    if(res) return true;
                }
            }
        }
        return false;
    }
    static boolean explore(char[][] board,int i,int j,int si,String word){
        if(si == word.length()) return true;
        if( i<0 || j<0 || i>=board.length || j>=board[0].length) return false;
        if(board[i][j] != word.charAt(si)) return false;

        char myChar = board[i][j];
        board[i][j] = '$';

        for(int d = 0;d<4;d++){
            boolean res = explore(board,i+di[d],j+dj[d],si+1,word);
            if(res){
                board[i][j] = myChar;
                return true;
            }
        }
        board[i][j] = myChar;
        return false;
    }

    // 23] Print knight tour
    static void knightTour(int chess[][], int r,int c,int move){
        if(r<0 || c<0 || r>=chess.length || c>=chess.length || chess[r][c] >0){
            return;
        }else if(move == chess.length * chess.length){
            chess[r][c] = move;
            displayBoard(chess);
            chess[r][c] = 0;
            return;
        }

        chess[r][c] = 1;
        knightTour(chess,r-2,c+1,move+1);
        knightTour(chess,r-1,c+2,move+1);
        knightTour(chess,r+1,c+2,move+1);
        knightTour(chess,r+2,c+1,move+1);
        knightTour(chess,r+2,c-1,move+1);
        knightTour(chess,r+1,c-2,move+1);
        knightTour(chess,r-1,c-2,move+1);
        knightTour(chess,r-2,c-1,move+1);
        chess[r][c] = 0;
    }
    static void displayBoard(int chess[][]){
        for(int i=0;i<chess.length;i++){
            for(int j=0;j<chess.length;j++){
                System.out.print(chess[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 23] NQueen
    static void NQueen(int chess[][], String qsf, int row){
        if(row==chess.length){
            System.out.println(qsf);
            return;
        }
        for(int col=0;col<chess.length;col++){
            if(isSafeForQueen(chess,row,col)){
                chess[row][col] = 1;
                NQueen(chess,qsf + row+"-"+col+", ",row+1);
                chess[row][col] = 0;
            }
        }

    }
    static boolean isSafeForQueen(int chess[][],int row,int col){
        for(int i=row-1,j=col;i>=0;i--){
            if(chess[i][j]==1) return false;
        }
        for(int i=row-1,j=col-1;i>=0 && j>= 0;i--,j--){
            if(chess[i][j]==1) return false;
        }

        for(int i=row-1,j=col+1;i>=0 && j<chess.length;i--,j++){
            if(chess[i][j]==1) return false;
        }
        return true;
    }

    // 22] Subset print
    static void subsetPrint(int arr[], int idx,String set,int sos,int t){
        if(idx==arr.length){
            if(sos==t){
                System.out.println(set + ".");
            }
            return;
        }

        subsetPrint(arr,idx+1,set+arr[idx]+" ,",sos + arr[idx],t);
        subsetPrint(arr,idx+1,set,sos,t);
    }

    // 21] Flooding
    static void floodPath(int maze[][],int row,int col,String ans,boolean visited[][]){

        // Input
//        int maze[][] = new int[4][4];
//        for(int i=0;i<4;i++){
//            for(int j=0;j<4;j++){
//                maze[i][j] = 0;
//            }
//        }
//
//        maze[0][1] = 1;
//        maze[0][3] = 1;
//        maze[1][1] = 1;
//        maze[1][2] = 1;
//        maze[3][1] = 1;
//        boolean visited[][] = new boolean[4][4];
//        floodPath(maze,0,0,"",visited);

        if(row<0 || col<0 || row==maze.length || col==maze[0].length || maze[row][col]==1 || visited[row][col]==true){
            return;
        }

        if(row==maze.length-1 && col==maze[0].length-1){
            System.out.println(ans);
            return;
        }

        visited[row][col] = true;
        floodPath(maze,row-1,col,ans+"t",visited);
        floodPath(maze,row,col-1,ans+"l",visited);
        floodPath(maze,row+1,col,ans+"d",visited);
        floodPath(maze,row,col+1,ans+"r",visited);
        visited[row][col] = false;
    }

    // 20] Encodding
    static void encodding(String ques,String ans){
        if(ques.length()==0){
            System.out.println(ans);
            return;
        }else if(ques.length()==1){
            // for ONE digit
            char ch = ques.charAt(0);
            if(ch=='0'){
                return;
            }else{
                int chv = ch -'0';
                char code = (char)('a' + chv - 1);
                System.out.println(ans+code);
            }
        }else{
            // for TWO digit
            char ch = ques.charAt(0);
            String roq = ques.substring(1);

            if(ch=='0'){
                return;
            }else{
                int chv = ch -'0';
                char code = (char)('a' + chv - 1);
                encodding(roq,ans+code);
            }

            String ch12 = ques.substring(0,2);
            String roq12 = ques.substring(2);

            int ch12v = Integer.parseInt(ch12);
            if(ch12v<=26){
                char code = (char) ('a'+ch12v-1);
                encodding(roq12,ans+code);
            }
        }
    }

    // 19] Permutation
    static void permu(String str,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return;
        }

        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            String ros = str.substring(0,i) + str.substring(i+1);
            permu(ros,ans+ch);
        }
    }

    // 18] Print maze with jump
    static void printJumpsMaze(int sr,int sc,int dr,int dc,String ans){
        if(sr==dr && sc==dc){
            System.out.println(ans);
            return;
        }
        for(int i=1;i<=dc-sc;i++){
            printJumpsMaze(sr,sc+i,dr,dc,ans+"h"+i);
        }
        for(int i=1;i<=dr-sr;i++){
            printJumpsMaze(sr+i,sc,dr,dc,ans+"r"+i);
        }
        for(int i=1;i<=dr-sr && i<=dc-sc;i++){
            printJumpsMaze(sr+i,sc+i,dr,dc,ans+"d"+i);
        }
    }

    // 17] Print maze paths
    static void printMazePaths(int sr,int sc,int dr,int dc,String ans){
//        if(sr>dr || sc>dc) return;
        if(sr==dr && sc==dc){
            System.out.println(ans);
            return;
        }
        if(sr<dr) printMazePaths(sr+1,sc,dr,dc,ans+"r");
        if(sc<dc) printMazePaths(sr,sc+1,dr,dc,ans+"h");



    }

    // 16] Print stairs
    static void printStairs(int n,String ans){
        if(n==0){
            System.out.println(ans);
            return;
        }
        if(n<0) return;

        printStairs(n-1,ans+1);
        printStairs(n-2,ans+2);
        printStairs(n-3,ans+3);
    }

    // 15] Print Keypad Combinations
    static void printKepad(String que,String ans){
        if(que.length()==0){
            System.out.println(ans);
            return;
        }

        char ch = que.charAt(0);
        String ros = que.substring(1);

        String codeStr = codes[ch-'0'];
        for(int i=0;i<codeStr.length();i++){
            printKepad(ros,ans+codeStr.charAt(i));
        }

    }

    // 14] Print subsequence
    static void printSeq(String ques, String ans){
        if(ques.length()==0){
            System.out.println(ans);
            return;
        }

        char ch = ques.charAt(0);
        String ros = ques.substring(1);

        printSeq(ros,ans+ch);
        printSeq(ros,ans+"");
    }

    // 13] Maze path adv
    static ArrayList<String> mazePathsAdv(int sr,int sc,int dr,int dc){

        if(sr==dr && sc==dc){
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        ArrayList<String> paths = new ArrayList<>();

        // Col wise
        for(int mh=1;mh<=dc-sc;mh++){
            ArrayList<String> hpaths = mazePaths(sr,sc+mh,dr,dc);
            for(String hpath: hpaths){
                paths.add("h"+mh+hpath);
            }
        }

        // Row wise
        for(int mr=1;mr<=dr-sr;mr++){
            ArrayList<String> rpaths = mazePaths(sr+mr,sc,dr,dc);
            for(String rpath: rpaths){
                paths.add("r"+mr+rpath);
            }
        }

        // Diagonal wise
        for(int md=1;md<=dr-sr && md<=dc-sc;md++){
            ArrayList<String> dpaths = mazePaths(sr+md,sc+md,dr,dc);
            for(String dpath: dpaths){
                paths.add("d"+md+dpath);
            }
        }

        return paths;
    }

    // 12] Maze paths
    static ArrayList<String> mazePaths(int sr,int sc,int dr,int dc){

        if(sr==dr && sc==dc){
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        ArrayList<String> hpaths = new ArrayList<>();
        ArrayList<String> rpaths = new ArrayList<>();

        if(sr<dr){
            rpaths = mazePaths(sr+1,sc,dr,dc);
        }
        if(sc<dc){
            hpaths = mazePaths(sr,sc+1,dr,dc);
        }

        ArrayList<String> paths = new ArrayList<>();

        for(String path1 : hpaths){
            paths.add("h"+path1);
        }
        for(String path2 : rpaths){
            paths.add("r"+path2);
        }
        return paths;
    }

    // 11] Get stairs paths
    static ArrayList<String> getPaths(int n){
        if(n==0){
            ArrayList<String> res = new ArrayList<>();
            res.add("");
            return res;
        }else if(n<0){
            ArrayList<String> res = new ArrayList<>();
            return res;
        }

        ArrayList<String> path1 = getPaths(n-1);
        ArrayList<String> path2 = getPaths(n-2);
        ArrayList<String> path3 = getPaths(n-3);

        ArrayList<String> paths = new ArrayList<>();

        for(String p:path1){
            paths.add(1+p);
        }
        for(String p:path2){
            paths.add(2+p);
        }
        for(String p:path3){
            paths.add(3+p);
        }

        return paths;
    }

    // 10] Keypad combinations
    static ArrayList<String> getKeypadCombi(String s){

        if(s.length() == 0) { // Main core logic of recursion is based on what it teturns
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = s.charAt(0);
        String ros = s.substring(1);
        ArrayList<String> rres = getKeypadCombi(ros);

        ArrayList<String>  mres = new ArrayList<>();
        String codeforch = codes[ch-'0']; // ch-'0'
        for(int i=0;i<codeforch.length();i++){
//            char chcode = codeforch.charAt(i);
            for(String rstr:rres){
                mres.add(codeforch.charAt(i) + rstr);
            }
        }
        return mres;
    }

    // 9] Get subsequences
    static ArrayList<String> getSubseq(String s){
        if(s.length()==0){
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        char ch = s.charAt(0);
        String ros = s.substring(1);
        ArrayList<String > rres = getSubseq(ros); // we got to the end then it return Array of " "

        // ---------------------------------------

        ArrayList<String> mres = new ArrayList<>();
        for(String rstr: rres){
            mres.add(""+rstr);
        }
        for(String rstr : rres){
            mres.add(ch+rstr);
        }
        return mres;
    }

    // 8] Tower of hanoi
    static void tower(int n, int t1id, int t2id, int t3id){
        if(n==0){
            return;
        }

        tower(n-1,t1id,t3id,t2id);
        System.out.println(n+ "[" + t1id + "->" + t2id +"]");
        tower(n-1,t3id,t2id,t1id);

    }

    // 7] All index's
    static int[] allIndexs(int arr[], int i, int x,int c){
        if(i==arr.length) return new int[c];
        if(arr[i]==x){
            int temp[] = allIndexs(arr,i+1,x,c+1);
            temp[c]=i;
            return temp;
        }else{
            int temp[] = allIndexs(arr,i+1,x,c);
            return temp;
        }
    }

    // 6] Last index
    static int lastIndex(int arr[],int i,int x){
        if(i== arr.length) return -1;
        int temp = lastIndex(arr,i+1,x);
        if(temp == -1){
            if(arr[i]==x){
                return i;
            }else{
                return -1;
            }
        }else{
            return temp;
        }

    }

    // 5] First index
    static int firstIndex(int arr[], int i, int x){
        if(i==arr.length) return -1;

        int temp=firstIndex(arr,i+1,x);
        if(arr[i]==x){
            return i;
        }else{
            return temp;
        }

    }

    // 4] Max array
    static int maxArray(int[] arr,int i){
        if(i==arr.length-1) return arr[i];
        int temp = maxArray(arr,i+1);
        if(temp<arr[i]) temp = arr[i];

        return temp;
    }

    // 3] power
    static int powerX(int x,int n){
        if(n==0) return 1;
        int st = powerX(x,n-1);
        return x * st;
    }

    // 2]
    static int factorial(int n){
        if(n<1) return 1;

        int st = factorial(n-1);
        return n * st;
    }

    // 1]
    static void printIncDec(int n){
        if(n==0) return;

        System.out.println(n);
        printIncDec(n-1);
        System.out.println(n);
    }
}

