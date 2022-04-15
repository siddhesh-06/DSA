package BST;

import kotlin.Pair;

import java.util.*;

class treeNode{
    int data;
    treeNode rightNode;
    treeNode leftNode;

    treeNode(){
        data=0;
        rightNode=null;
        leftNode=null;
    }
    treeNode(int _data){
        data=_data;
        rightNode=null;
        leftNode=null;
    }
}

class pair{
    int x;
    treeNode node;

    pair(int _x, treeNode _node){
        x = _x;
        node = _node;
    }
}


class bst extends treeNode{
    treeNode root,loc,par;

    //methods
    public void insert(int info){
        treeNode newNode = new treeNode();
        newNode.data=info;

        if(root==null){
            root=newNode;
            rightNode=null;
            leftNode=null;
        }else{
            boolean inserted=false;
            treeNode ptr=root;
            while (!inserted) {
                if (info > ptr.data) {
                    if (ptr.rightNode != null) {
                        ptr = ptr.rightNode;
                    } else {
                        ptr.rightNode=newNode;
                        inserted=true;
                    }
                } else if (info < ptr.data) {
                    if (ptr.leftNode != null) {
                        ptr=ptr.leftNode;
                    } else {
                        ptr.leftNode=newNode;
                        inserted=true;
                    }
                } else {
                    System.out.println("Element alredy inserted");
                    break;
                }
            }
        }
    }

    public void search(int info){
        treeNode ptr=root;
        loc=null;
        par=null;
        System.out.println(ptr.data);
        if(root==null){
            System.out.println("Tree is empty");
        }else {
            while (ptr!=null){
                if(ptr.data==info){
                    System.out.println("Element is found");
                    loc=ptr;
                    break;
                }else if(info>ptr.data){
                    par=ptr;
                    ptr=ptr.rightNode;
                    loc=ptr;
                }else{
                    par=ptr;
                    ptr=ptr.leftNode;
                    loc=ptr;
                }
            }
            if(loc==null){
                System.out.println("Not found");
            }
        }
    }

    public int height(){
       return bstHeight(root);
    }

    public int bstHeight(treeNode root){
        if(root==null){
            return -1;
        }else{
            int left=bstHeight(root.leftNode);
            int right=bstHeight(root.rightNode);
            if(left>right){
                return  left+1;
            }else{
                return right+1;
            }
        }

    }

    //Inorder
    public void display(){
        inorder(root);
    }

    public void inorder(treeNode root){
       if(root!=null){
           inorder(root.leftNode);
           System.out.print(root.data+", ");
           inorder(root.rightNode);
       }
    }

    public void leaf() {
        Queue<treeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
            while (!q.isEmpty()) {
                treeNode node = q.poll(); //remove & return head of queue
                if (node.leftNode == null && node.rightNode == null) {
                    System.out.println(node.data + ", ");
                } else {

                    if (node.leftNode != null) q.add(node.leftNode);
                    if (node.rightNode != null) q.add(node.rightNode);

                }
            }
        } else {
            System.out.print("No tree");
        }
        System.out.println(" ");
    }

    public void delete0(){
        if(loc!=null){
            if(par==null){
                root=null;
            }else{
                if(loc == par.rightNode){
                    par.rightNode=null;
                }else{
                    par.leftNode=null;
                }
            }
            loc=null;
        }
    }

    public void delete2(int info){
        search(info);
        if(loc!=null){
            treeNode ptr1 = null;
            treeNode ptr2 = null;
            //when loc dont have both child
            if(loc.leftNode==null && loc.rightNode==null){
                delete0();
            }else if(loc.rightNode != null && loc.leftNode != null ){
                ptr2=loc.rightNode;
                ptr1=loc;
                while(ptr2.rightNode!=null){
                    ptr1=ptr2;
                    ptr2=ptr2.leftNode;
                }

            }
        }
    }

    public void parentsWithChild(){
        Queue<treeNode> q = new LinkedList<>();

        if(root!=null){
            q.add(root);
            while (!q.isEmpty()){
                treeNode node=q.poll();
                System.out.println(node.data+": ");
                if(node.leftNode!=null){
                    System.out.println(node.leftNode.data);
                    q.add(node.leftNode);
                }
                if(node.rightNode!=null){
                    System.out.println(node.rightNode.data);
                    q.add(node.rightNode);
                }
            }
        }else{
            System.out.println("No tree");
        }
    }

    //Level wise - BFS
    public List<List<Integer>> levelwise(){
        Queue<treeNode> q = new LinkedList<>();
        List<List<Integer>> wraplist = new LinkedList<>();
        if(root==null) return wraplist;

        q.offer(root);
        // to add() method since this method
        // does not throws an exception when the capacity of the container is full since it returns false.
        while(!q.isEmpty()){
            int levelNum = q.size();
            List<Integer> sublist = new LinkedList<>();
            for(int i=0;i<levelNum;i++){
                if(q.peek().leftNode!=null) q.offer(q.peek().leftNode);
                if(q.peek().rightNode!=null) q.offer(q.peek().rightNode);
                sublist.add(q.poll().data);
            }
            wraplist.add(new ArrayList<>(sublist));
        }
        return wraplist;
    }

    // Interative DFS
    public List<Integer> preOrder(){
        List<Integer> preorder = new ArrayList<Integer>();
        if(root==null) return preorder;
        Stack<treeNode> s = new Stack<>();
        s.push(root);

        while (!s.isEmpty()){
            root = s.pop();
            preorder.add(root.data);
            if(root.rightNode != null) s.push(root.rightNode);
            if(root.leftNode != null) s.push(root.leftNode);
        }
        return preorder;
    }
    public List<Integer> inOrder(){
        List<Integer> inorder = new ArrayList<Integer>();
        Stack<treeNode> st = new Stack<>();
        treeNode temp = root;

        while(true){
            if(temp!=null){
                st.push(temp);
                temp=temp.leftNode;
            }else{
                if(st.isEmpty()){
                    break;
                }
                temp = st.pop();
                inorder.add(temp.data);
                temp=temp.rightNode;
            }
        }
        return inorder;
    }
    public List<Integer> postOrder(){
        List<Integer> ds = new ArrayList<>();
        Stack<treeNode> st = new Stack<>();
        treeNode cur = root;
        while (cur!=null || !st.isEmpty()){
            if(cur!=null){
                st.push(cur);
                cur=cur.leftNode;
            }else{
                treeNode temp = st.peek().rightNode;
                if(temp==null){
                    temp = st.pop();
                    ds.add(temp.data);
                    while (!st.isEmpty() && temp==st.peek().rightNode){
                        temp=st.pop();
                        ds.add(temp.data);
                    }
                }else{
                    cur=temp;
                }
            }
        }
        return ds;
    }

    // Depth or height
    public int maxDepth(treeNode root) {
        if(root==null) return 0;
        int l = maxDepth(root.leftNode);
        int r = maxDepth(root.rightNode);

        return 1 + Math.max(l,r);
    }

    // Balanced
    public boolean isBalanced(treeNode root) {
        return dfsHeight(root) != -1;
    }
    private int dfsHeight(treeNode root){
        if(root==null) return 0;

        int l = dfsHeight(root.leftNode);
        if(l==-1) return -1;

        int r = dfsHeight(root.rightNode);
        if(r==-1) return -1;

        if(Math.abs(l-r)>1) return -1;  // Main condition for balance we req 1

        return 1+Math.max(l,r);
    }

    // Diameter => Distance between 2 nodes
    public int diameterOfBinaryTree(treeNode root) {
        int max[] = new int[1];
        getDiameter(root,max);

        return max[0];
    }
    private int getDiameter(treeNode root, int max[]){
        if(root==null) return 0;

        int l = getDiameter(root.leftNode, max);
        int r = getDiameter(root.rightNode, max);

        max[0] = Math.max(max[0], (l+r)); // Diameter become whose l+r is max

        return 1 + Math.max(l,r);
    }

    // Maximum path sum => Max Dist betn 2 nodes
    //int max = Integer.MIN_VALUE;
    public int maxPathSum(treeNode root) {
        int max[] = new int[1];
        helper(root, max);
        return max[0];
    }
    int helper(treeNode root, int max[]) {
        if (root == null) return 0;

        int left = Math.max(helper(root.leftNode,max), 0); // use zeo to handle -ve weights
        int right = Math.max(helper(root.rightNode,max), 0);

        max[0] = Math.max(max[0], root.data + left + right);

        return root.data + Math.max(left, right);
    }

    // Check 2 tree's are identical or not
    public boolean isSameTree(treeNode root1,treeNode root2){
        if(root1==null || root2==null) return (root1==root2);
         // pre-order traversal is rLR
        return (root1.data==root2.data && isSameTree(root1.leftNode,root2.leftNode) && isSameTree(root1.rightNode,root2.rightNode));
    }

    // Zig zag traversal
    public List<List<Integer>> zigzagLevelOrder(treeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;

        Queue<treeNode> que = new LinkedList<>();
        que.add(root);
        boolean leftToRight = true;

        while(!que.isEmpty()){
            int size = que.size();
            List<Integer> row = new ArrayList<>(size);

            for(int i=0;i<size;i++){
                treeNode node = que.peek();
                que.remove();

                if(leftToRight){
                    row.add(node.data);
                }else{
                    // add reverse
                    row.add(0,node.data);
                }

                if(node.leftNode!=null) que.add(node.leftNode);
                if(node.rightNode!=null) que.add(node.rightNode);
            }
            leftToRight = !leftToRight;
            result.add(row);
        }
        return result;
    }

    // Boundry traversal
    boolean isLeaf(treeNode root){
        return (root.leftNode==null && root.rightNode==null);
    }
    void leftBoundry(treeNode root, ArrayList<Integer> ans){
        treeNode cur = root.leftNode;
        if(cur!=null){
            if(isLeaf(cur) == false) ans.add(cur.data);
            if(cur.leftNode!=null){
                cur=cur.leftNode;
            }else{
                cur=cur.rightNode;
            }
        }
    }
    void addLeaves(treeNode root, ArrayList<Integer> ans){
        // Inorder -> rLR
        if(isLeaf(root)){
            ans.add(root.data);
            return;
        }

        if(root.leftNode!=null) addLeaves(root.leftNode,ans);
        if(root.rightNode!=null) addLeaves(root.rightNode,ans);
    }
    void rightBoundry(treeNode root, ArrayList<Integer> ans){
        treeNode cur = root.rightNode;
        ArrayList<Integer> temp = new ArrayList<>();
        while(cur!=null){
            if(isLeaf(cur)==false) temp.add(cur.data);
            if(cur.rightNode!=null) cur = cur.rightNode;
            else cur = cur.leftNode;
        }
        for(int i=temp.size()-1;i>=0;i--){
            ans.add(temp.get(i));
        }
    }
    ArrayList <Integer> boundary(treeNode node){
        ArrayList<Integer> ans = new ArrayList<>();
        if(node==null) return ans;
        if(isLeaf(node) == false) ans.add(node.data);
        leftBoundry(node,ans);
        addLeaves(node,ans);
        rightBoundry(node,ans);
        return ans;
    }

    //Top view
    static ArrayList<Integer> topView(treeNode root){
        // Here we have used BFS - Level wise because in depth wise we have to use height concept
        // to determine which one is first or top
        ArrayList<Integer> ans = new ArrayList<>();
        if(root==null) return ans;

        TreeMap<Integer,Integer> hm = new TreeMap<>();
        Queue<pair> q = new LinkedList<>();

        q.offer(new pair(0, root));

        while(!q.isEmpty()){
            pair temp = q.poll();
            treeNode nd = temp.node;
            int x = temp.x;

            // because we want top view so prevent from overwrite value
            if(!hm.containsKey(x)){
                hm.put(x, nd.data);
            }

            if(nd.leftNode!=null) q.offer(new pair(x-1, nd.leftNode));
            if(nd.rightNode!=null) q.offer(new pair(x+1, nd.rightNode));
        }

        for(Map.Entry<Integer, Integer> entry : hm.entrySet()){
            ans.add(entry.getValue());
        }

        return ans;
    }

    //Bottom view
    public ArrayList <Integer> bottomView(treeNode root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root==null) return ans;

        TreeMap<Integer,Integer> tm = new TreeMap<>();
        Queue<pair> q = new LinkedList<>();

        q.offer(new pair(0, root));

        while(!q.isEmpty()){
            pair temp = q.poll();
            treeNode nd = temp.node;
            int x = temp.x;

            tm.put(x, nd.data);

            if(nd.leftNode!=null) q.offer(new pair(x-1, nd.leftNode));
            if(nd.rightNode!=null) q.offer(new pair(x+1, nd.rightNode));

        }

        for(Map.Entry<Integer,Integer> entry : tm.entrySet()){
            ans.add(entry.getValue());
        }

        return ans;
    }

    // Right view
    public List<Integer> rightSideView(treeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;

        reversePreOrder(root, 0,ans);

        return ans;
    }
    static void reversePreOrder(treeNode root, int level, List<Integer> ans){
        if(root==null){
            return;
        }
        if(level==ans.size()){
            ans.add(root.data);
        }
        reversePreOrder(root.rightNode, level+1, ans);
        reversePreOrder(root.leftNode, level+1, ans);
    }

    // Left view
    public List<Integer> leftSideView(treeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;

        preOrder(root, 0,ans);

        return ans;
    }
    static void preOrder(treeNode root, int level, List<Integer> ans){
        if(root==null){
            return;
        }
        if(level==ans.size()){
            ans.add(root.data);
        }
        preOrder(root.leftNode, level+1, ans);
        preOrder(root.rightNode, level+1, ans);
    }

    // Symmetric bt
//    static boolean symmetricORNot(treeNode root){
//        return root==null || symmetricHelper(root.leftNode,root.rightNode);
//    }
//    static boolean symmetricHelper(treeNode left, treeNode right){
//        if(left==null || right==null){
//            return left==right;
//        }
//        if(left.data==right.data) return true;
//        return symmetricORNot(left.leftNode, right.rightNode) || symmetricORNot(left.rightNode, right.leftNode);
//    }

    // Path root -> node
    public ArrayList<Integer> pathRootToNode(treeNode root,treeNode key){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root==null) return ans;
        pathHelper(root, ans,key);
        return ans;
    }
    static void pathHelper(treeNode root, ArrayList<Integer> ans,treeNode key){
        if(root==null){
            return;
        }

        if(root.data!=key.data){
            ans.add(root.data);
            pathHelper(root.leftNode, ans, key);
            ans.remove(ans.size()-1);
            pathHelper(root.rightNode, ans, key);
        }else{
            ans.add(root.data);
            return;
        }
    }
    static boolean getPath(treeNode root, ArrayList<Integer> ans, treeNode key){
        if(root==null){
            return false;
        }
        ans.add(root.data);
        if(root.data==key.data) return true;

        if(getPath(root.leftNode, ans, key) || getPath(root.rightNode, ans, key)){
            return true;
        }
        ans.remove(ans.size()-1);
        return false;
    }

    // Lowest common ancestor
    public treeNode lowestCommonAncestor(treeNode root, treeNode p, treeNode q) {
        //base case
        if (root == null || root == p || root == q) {
            return root;
        }
        treeNode left = lowestCommonAncestor(root.leftNode, p, q);
        treeNode right = lowestCommonAncestor(root.rightNode, p, q);

        //result
        if(left == null) {
            return right;
        }
        else if(right == null) {
            return left;
        }
        else { //both left and right are not null, we found our result
            return root;
        }
    }

    // Max width of binary tree
    public static int maxWidth(treeNode root){
        int ans = 0;
        if(root==null) return ans;

        Queue<pair> q = new LinkedList<>();
        q.offer(new pair(0,root));

        while (!q.isEmpty()){
            int size = q.size();
            int min = q.peek().x;
            int first = 0, last = 0;

            for(int i=0;i<size;i++){
                int curr = q.peek().x - min;
                treeNode temp = q.peek().node;
                q.poll();

                if(i==0) first = curr;
                if(i==size-1) last = curr;

                if(temp.leftNode!=null) q.offer(new pair(2*curr+1, temp.leftNode));
                if(temp.rightNode!=null) q.offer(new pair(2*curr+2, temp.rightNode));

            }
            ans = Math.max(ans, first-last+1);
        }
        return ans;
    }

    // Children sum property
    public static void childrenPathSum(treeNode root){
        if(root==null) return;
        int child = 0;
        if(root.leftNode!=null) child+= root.leftNode.data;
        if(root.rightNode!=null) child+= root.rightNode.data;

        if(child>=root.data) root.data = child;
        else{
            if (root.leftNode!=null) root.leftNode.data = root.data;
            else root.rightNode.data = root.data;
        }

        childrenPathSum(root.leftNode);
        childrenPathSum(root.rightNode);

        int total = 0;
        if(root.leftNode!=null) total+= root.leftNode.data;
        else if(root.rightNode!=null) total+= root.rightNode.data;
        else if(root.leftNode!=null || root.rightNode!=null) root.data = total;

    }

    // Min time to burn bt from node


    // Count node of complete binary tree
    public int countNodes(treeNode root) {
        if(root==null) return 0;

        int left = getLeftCount(root);
        int right = getRightCount(root);

        if(left==right) return ((2<<(left))-1);
        else{
            return countNodes(root.leftNode) + countNodes(root.rightNode) + 1;
        }
    }
    static int getLeftCount(treeNode root){
        int c = 0;
        while(root.leftNode!=null){
            c++;
            root = root.leftNode;
        }

        return c;
    }
    static int getRightCount(treeNode root){
        int c = 0;
        while(root.rightNode!=null){
            c++;
            root = root.rightNode;
        }

        return c;
    }

    // Binary tree from INORDER, PREORDER
    static treeNode createBT(int[] preorder, int[] inorder){
        Map<Integer, Integer> hm = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            hm.put(inorder[i], i);
        }
        treeNode root = buildTree(inorder,0,inorder.length-1,preorder,0,preorder.length-1,hm);
        return root;
    }
    static treeNode buildTree(int[] in, int ins, int ine, int[] pre, int pres, int pree, Map<Integer,Integer> hm){
        if(ins>ine || pres>pree) return null;
        treeNode root = new treeNode(pre[pres]);
        int inroot = hm.get(pre[pres]);
        int leftnum = inroot - ins;

        root.leftNode = buildTree(in,ins,inroot-1,pre,pres+1,pree+leftnum,hm);
        root.rightNode = buildTree(in,inroot+1,ine,pre,pres+leftnum+1,pree,hm);
        return root;
    }

    // Binary tre from INORDER, POSTORDER
    static treeNode createBT2(int[] inorder, int[] postorder){
        Map<Integer, Integer> hm = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            hm.put(inorder[i],i);
        }
        treeNode root = buildTree2(inorder,0,inorder.length-1,postorder,0,postorder.length-1, hm);
        return root;
    }
    static treeNode buildTree2(int[] in, int inS, int inE, int[] post, int postS, int postE, Map<Integer, Integer> hm){
        if(inS>inE || postS>postE) return null;

        treeNode root = new treeNode(post[postE]);
        int inroot = hm.get(post[postE]);
        int leftNums = inroot - inS;

        root.leftNode = buildTree2(in,inS,inroot-1,post,postS,postE+leftNums-1,hm);
        root.rightNode = buildTree2(in,inroot+1,inE,post,postS+leftNums,postE-1,hm);
        return root;
    }
}


public class binarySearchTree {
    public static void main(String args[]){
        bst b =new bst();
        Scanner sc =new Scanner(System.in);
        int c,info;
        boolean check=true;
        while (check){
            System.out.println("1.Insert element: ");
            System.out.println("2.Display: ");
            System.out.println("3.Search: ");
            System.out.println("4.Height: ");
            System.out.println("5.Delete: ");
            System.out.println("6.Leaf nodes: ");
            System.out.println("7.Leaf nodes: ");
            System.out.println("8.Exist: ");
            c=sc.nextInt();
            switch (c){
                case 1:{
                    System.out.print("Enter value: ");
                    info=sc.nextInt();
                    b.insert(info);
                    break;
                }
                case 2:{
                    b.display();
                    System.out.println("");
                    break;
                }
                case 3:{
                    System.out.print("Enter element: ");
                    info=sc.nextInt();
                    b.search(info);
                    break;
                }
                case 4:{
                    System.out.println("Height is: "+b.height());
                    break;
                }
                case 5:{
                    System.out.println("Enter Element: ");
                    info=sc.nextInt();
                    b.delete2(info);
                    break;
                }
                case 6:{
                    b.leaf();
                    break;
                }
                case 7:{
                    System.out.println(b.postOrder());
                    break;
                }
                case 8:{
                    check=false;
                    break;
                }
                default:{
                    System.out.println("Error");
                }
            }
        }
    }
}
