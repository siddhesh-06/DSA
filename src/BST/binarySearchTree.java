package BST;

import javax.swing.tree.TreeNode;
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

    public List<List<Integer>> levelwise(){
        Queue<treeNode> q = new LinkedList<>();
        List<List<Integer>> wraplist = new LinkedList<>();
        if(root==null) return wraplist;

        q.offer(root);
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
//    public int maxDepth(TreeNode root) {
//        if(root==null) return 0;
//        int l = maxDepth(root.left);
//        int r = maxDepth(root.right);
//
//        return 1 + Math.max(l,r);
//    }
    // Balanced
//    public boolean isBalanced(TreeNode root) {
//        return dfsHeight(root) != -1;
//    }
//
//    private int dfsHeight(TreeNode root){
//        if(root==null) return 0;
//
//        int l = dfsHeight(root.left);
//        if(l==-1) return -1;

//        int r = dfsHeight(root.right);
//        if(r==-1) return -1;
//
//        if(Math.abs(l-r)>1) return -1;  // Main condition
//
//        return 1+Math.max(l,r);
//
//    }

    // Diameter => Distance between 2 nodes
//    public int diameterOfBinaryTree(TreeNode root) {
//        int max[] = new int[1];
//        getDiameter(root,max);
//
//        return max[0];
//    }
//
//    private int getDiameter(TreeNode root, int max[]){
//        if(root==null) return 0;
//
//        int l = getDiameter(root.left, max);
//        int r = getDiameter(root.right, max);
//
//        max[0] = Math.max(max[0], (l+r)); // Diameter become whose l+r is max
//
//        return 1 + Math.max(l,r);
//    }

    // Maximum path sum => Max Dist betn 2 nodes
//    int max = Integer.MIN_VALUE;
//
//    public int maxPathSum(TreeNode root) {
//        helper(root);
//        return max;
//    }
//
//    int helper(TreeNode root) {
//        if (root == null) return 0;
//
//        int left = Math.max(helper(root.left), 0); // use zeo to handle -ve weights
//        int right = Math.max(helper(root.right), 0);
//
//        max = Math.max(max, root.val + left + right);
//
//        return root.val + Math.max(left, right);
//    }
    // Check 2 tree's are identical or not
//    public boolean isSameTree(TreeNode root1,TreeNode root2){
//        if(root1==null || root2==null) return (root1==root2);
//         // pre-order traversal is rLR
//        return (root1.val==root2.val && isSameTree(root1.left,root2.left) && isSameTree(root1.right,root2.right));
//    }

    // Zig zag traversal
//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<>();
//        if(root==null) return result;
//
//        Queue<TreeNode> que = new LinkedList<>();
//        que.add(root);
//        boolean leftToRight = true;
//
//        while(!que.isEmpty()){
//            int size = que.size();
//            List<Integer> row = new ArrayList<>(size);
//
//            for(int i=0;i<size;i++){
//                TreeNode node = que.peek();
//                que.remove();
//
//                if(leftToRight){
//                    row.add(node.val);
//                }else{
//                    row.add(0,node.val);
//                }
//
//                if(node.left!=null) que.add(node.left);
//                if(node.right!=null) que.add(node.right);
//            }
//            leftToRight = !leftToRight;
//            result.add(row);
//        }
//        return result;
//    }
    // Boundry traversal
//    boolean isLeaf(Node root){
//        return (root.left==null && root.right==null);
//    }
//    void leftBoundry(Node root, ArrayList<Integer> ans){
//        Node cur = root.left;
//        if(cur!=null){
//            if(isLeaf(cur) == false) ans.add(cur.data);
//            if(cur.left!=null){
//                cur=cur.left;
//            }else{
//                cur=cur.right;
//            }
//        }
//    }
//    void addLeaves(Node root, ArrayList<Integer> ans){
//        if(isLeaf(root)){
//            ans.add(root.data);
//            return;
//        }
//
//        if(root.left!=null) addLeaves(root.left,ans);
//        if(root.right!=null) addLeaves(root.right,ans);
//    }
//    void rightBoundry(Node root, ArrayList<Integer> ans){
//        Node cur = root.right;
//        ArrayList<Integer> temp = new ArrayList<>();
//        while(cur!=null){
//            if(isLeaf(cur)==false) temp.add(cur.data);
//            if(cur.right!=null) cur = cur.right;
    //        else cur = cur.left;
//        }
//        for(int i=temp.size()-1;i>=0;i--){
//            ans.add(temp.get(i));
//        }
//    }
//    ArrayList <Integer> boundary(Node node){
//        ArrayList<Integer> ans = new ArrayList<>();
//        if(node==null) return ans;
//        if(isLeaf(node) == false) ans.add(node.data);
//        leftBoundry(node,ans);
//        addLeaves(node,ans);
//        rightBoundry(node,ans);
//        return ans;
//    }
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
