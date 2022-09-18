package BST;

import javax.swing.tree.TreeNode;
import java.util.*;

class bstIterator{
    public Stack<treeNode> s = new Stack<>();
    boolean reverse = false;

    bstIterator(treeNode root, boolean isReverse){
        pushAll(root);
        reverse = isReverse;
    }

    public boolean hasnext(){
        return !s.isEmpty();
    }

    public int next(){
        treeNode temp = s.pop();
        if(reverse) pushAll(temp.leftNode);
        else pushAll(temp.rightNode);
        return temp.data;
    }

    public void pushAll(treeNode root){
        while(root!=null){
            s.push(root);
            if(reverse==false) root = root.leftNode;
            else root = root.rightNode;
        }
    }

}

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
    treeNode first, prev, mid, last;

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
                    if (ptr.rightNode != null) { //for right
                        ptr = ptr.rightNode;
                    } else {
                        ptr.rightNode=newNode;
                        inserted=true;
                    }
                } else if (info < ptr.data) { //for left
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

    // Level wise - BFS
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

    // Balanced = AVL tree is treeNode
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

    // Maximum path sum
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
        while(cur!=null){
            if(isLeaf(cur) == false) ans.add(cur.data);
            if(cur.leftNode!=null){
                cur=cur.leftNode;
            }else{
                cur=cur.rightNode;
            }
        }
    }
    void addLeaves(treeNode root, ArrayList<Integer> ans){
        // Preorder -> rLR
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
    public ArrayList <Integer> boundary(treeNode node){
        ArrayList<Integer> ans = new ArrayList<>();
        if(node==null) return ans;
        if(isLeaf(node) == false) ans.add(node.data);

        leftBoundry(node,ans); //left side
        addLeaves(node,ans);    //bottom side
        rightBoundry(node,ans); //right side

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
    public ArrayList <Integer> bottomView(treeNode root) {
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
    static boolean symmetricORNot(treeNode root){
        return root==null || symmetricHelper(root.leftNode,root.rightNode);
   }
    static boolean symmetricHelper(treeNode left, treeNode right){
       if(left==null || right==null){
            return left==right;
        }
        if(left.data!=right.data) return false;
        return (symmetricHelper(left.leftNode, right.rightNode) && symmetricHelper(left.rightNode, right.leftNode));
    }

    // Path root -> node : Do inorder and collect all nodes
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

    // Lowest common ancestor : for both BT and BST
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

        while(!q.isEmpty()){
            int size = q.size();
            int min = q.peek().x;
            int first = 0, last = 0;

            for(int i=0;i<size;i++){
                int cur_id = q.peek().x - min;
                treeNode temp = q.peek().node;
                q.poll();

                if(i==0) first = cur_id;
                if(i==size-1) last = cur_id;

                if(temp.leftNode!=null){
                    q.offer(new pair(2*cur_id+1,temp.leftNode));
                }
                if(temp.rightNode!=null){
                    q.offer(new pair(2*cur_id+2, temp.rightNode));
                }
            }
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    }

    // Children sum property
    public static void childrenPathSum(treeNode root){
        if(root==null) return;
        int child = 0;
        if(root.leftNode!=null) child += root.leftNode.data;
        if(root.rightNode!=null) child +=root.rightNode.data;

        if(child>=root.data) root.data = child;
        else{
            if(root.leftNode!=null) root.leftNode.data = child;
            else root.rightNode.data = child;
        }

        childrenPathSum(root.leftNode);
        childrenPathSum(root.rightNode);

        int total = 0;
        if(root.leftNode!=null) total+= root.leftNode.data;
        if(root.rightNode!=null) total+= root.rightNode.data;
        if(root.leftNode!=null || root.rightNode!=null) root.data = total;

    }

    // Min time to burn bt from node

    // Print all nodes at dist k
    public List<Integer> distanceK(treeNode root, treeNode target, int k){
        Map<treeNode, treeNode> hm = new HashMap<>();
        markParent(root, hm);
        Map<treeNode, Boolean> vis = new HashMap<>();
        Queue<treeNode> q = new LinkedList<>();

        q.offer(target);
        vis.put(target, true);
        int curr_val = 0;

        while (!q.isEmpty()){
            int size = q.size();
            if(curr_val==k) break;
            curr_val++;

            for(int i=0;i<size;i++){
                treeNode node = q.poll();

                if(node.leftNode!=null && vis.get(node.leftNode)==null){
                    q.offer(node.leftNode);
                    vis.put(node.leftNode, true);
                }
                if(node.rightNode!=null && vis.get(node.rightNode)==null){
                    q.offer(node.rightNode);
                    vis.put(node.rightNode, true);
                }
                if(hm.get(node)!=null && vis.get(hm.get(node))==null){
                    q.offer(hm.get(node));
                    vis.put(hm.get(node), true);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()){
            ans.add(q.poll().data);
        }
        return ans;
    }
    public void markParent(treeNode root, Map<treeNode, treeNode> hm){
        Queue<treeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            treeNode node = q.poll();
            if(node.leftNode!=null){
                q.add(node.leftNode);
                hm.put(node.leftNode, node);
            }
            if(node.rightNode!=null){
                q.add(node.rightNode);
                hm.put(node.rightNode, node);
            }
        }
    }

    // Count node of complete binary tree
    public int countNodes(treeNode root) {
        if(root==null) return 0;

        int left = getLeftCount(root);
        int right = getRightCount(root);

        if(left==right) return ((2<<(left))-1); // formula : 2^(Height of tree)-1;
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
        // hash inorder
        for(int i=0;i<inorder.length;i++){
            hm.put(inorder[i], i);
        }
        treeNode root = buildTree(inorder,0 ,inorder.length-1,preorder,0,preorder.length-1,hm);
        return root;
    }
    static treeNode buildTree(int[] in, int ins, int ine, int[] pre, int pres, int pree, Map<Integer,Integer> hm){
        if(ins>ine || pres>pree) return null;
        treeNode root = new treeNode(pre[pres]);
        int inroot = hm.get(pre[pres]); //To find position
        int leftnum = inroot - ins; //count el in left side of root

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

    // Serialize and deserialize the BT
    static String serializeBT(treeNode root){
        if(root==null) return "";
        StringBuilder res = new StringBuilder();
        Queue<treeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            treeNode node = q.poll();
            if(node==null){
                res.append("n ");
                continue;
            }
            res.append(node.data+" ");
            q.offer(node.leftNode);
            q.offer(node.rightNode);
        }
        return res.toString();
    }
    static treeNode deserializeBT(String s){
        if(s=="") return null;
        Queue<treeNode> q = new LinkedList<>();
        String res[] = s.split(" ");
        treeNode root = new treeNode(Integer.parseInt(res[0]));
        q.offer(root);

        for(int i=1;i<res.length;i++){
            treeNode node = q.poll();
            if(!res[i].equals("n")){
                treeNode left = new treeNode(Integer.parseInt(res[i]));
                node.leftNode = left;
                q.offer(left);
            }
            if(!res[++i].equals("n")){
                treeNode right = new treeNode(Integer.parseInt(res[i]));
                node.rightNode = right;
                q.offer(right);
            }
        }

        return root;
    }

    // Morris Traversal => o(T) : o(n) & o(S) : o(1)
    static ArrayList<Integer> morrisTraversalInorder(treeNode root){
        ArrayList<Integer> inorder = new ArrayList<>();
        treeNode curr = root;
        while (curr!=null){
            if(curr.leftNode==null){
                inorder.add(curr.data);
                curr = curr.rightNode;
            }else if(curr.leftNode!=null){
                treeNode prev = curr.leftNode;
                while (prev.rightNode!=null && prev.rightNode!=curr){
                    prev = prev.rightNode;
                }
                if(prev.rightNode==null){
                    prev.rightNode = curr;
                    curr = curr.leftNode;
                }else{
                    // if thread exist then do this
                    prev.rightNode = null;
                    inorder.add(curr.data);
                    curr = curr.rightNode;
                }
            }
        }

        return inorder;
    }
    static ArrayList<Integer> morrisTraversalPreorder(treeNode root){
        ArrayList<Integer> preorder = new ArrayList<>();
        treeNode curr = root;

        while (curr!=null){
            if(curr.leftNode==null){
                preorder.add(curr.data);
                curr = curr.rightNode;
            }else if(curr.leftNode!=null){
                treeNode prev = curr.leftNode;
                while (prev.rightNode!=null && prev.rightNode!=curr){
                    prev = prev.rightNode;
                }
                if(prev.rightNode==null){
                    prev.rightNode = curr;
                    preorder.add(curr.data);
                    curr = curr.leftNode;
                }else{
                    // if thread exist then do this
                    prev.rightNode = null;
                    curr = curr.rightNode;
                }

            }
        }
        return preorder;
    }

    // Flatten a BT to LL
    static treeNode buildLL(treeNode root){
        treeNode curr = root;
        treeNode prev = null;
        while (curr!=null){
            if(curr.leftNode!=null){
                prev = curr.leftNode;
                while (prev.rightNode!=null) prev = prev.rightNode;
                prev.rightNode = curr.rightNode;
                curr.rightNode = curr.leftNode;
            }
            curr = curr.rightNode;
        }

        return root;
    }
    static  void buildLL1(treeNode root){
        Stack<treeNode> s = new Stack<>();
        s.push(root);

        while (!s.isEmpty()){
            treeNode node = s.pop();
            if(node.rightNode!=null){
                s.push(node.rightNode);
            }
            if(node.leftNode!=null){
                s.push(node.leftNode);
            }

            if(!s.isEmpty()){
                node.rightNode = s.peek();
            }
            node.leftNode = null;
        }
    }

    // Search in BST
    static treeNode searchInBST(treeNode root, int key){
        while (root!=null && root.data!=key){
            root = key<root.data ? root.leftNode : root.rightNode;
        }
        return root;
    }

    public treeNode searchBST(treeNode root, int val) {
        if (root==null) return null;

        while (root!=null){
            if(root.data==val) return root;
            if(root.data< val){
                root = root.leftNode;
            }else{
                root = root.rightNode;
            }
        }

        return null;
    }

    // Ceil in BST
    static int ceilInBST(treeNode node, int x){
        // Just large
        int ceil = -1;
        while(node!=null){
            if(node.data==x){
                ceil = x;
                return ceil;
            }
            if(node.data>x){
                ceil = node.data;
                node = node.leftNode;
            }else{
                node = node.rightNode;
            }
        }

        return ceil;
    }

    // Floor in BST
    static int floorInBST(treeNode root, int k){
        // Just small
        int floor = -1;
        if(root==null) return floor;

        while(root!=null){
            if(k==root.data){
                floor = root.data;
                return floor;
            }

            if(k>root.data){
                floor = root.data;
                root = root.rightNode;
            }else{
                root = root.leftNode;
            }
        }

        return floor;
    }

    // Insert in BST
    static treeNode insertBST(treeNode root, int k){
        if(root==null) return new treeNode(k);
        treeNode res = root;
        while (true){
            if(k>root.data){
                if(root.rightNode!=null){
                    root = root.rightNode;
                }else{
                    treeNode temp = new treeNode(k);
                    root.rightNode = temp;
                    break;
                }
            }else{
                if(root.leftNode!=null){
                    root = root.leftNode;
                }else{
                    treeNode temp = new treeNode(k);
                    root.leftNode = temp;
                }
            }
        }

        return res;
    }

    // Delete in BST
    static treeNode deleteNode(treeNode root,int k){
        if(root==null) return null;
        if(root.data==k) return helperDelete(root);

        treeNode node = root;
        while (root!=null){
            if(root.data>k){
                if(root.leftNode!=null && root.leftNode.data==k){
                    root.leftNode = helperDelete(root.leftNode);
                    break;
                }else{
                    root = root.leftNode;
                }
            }else{
                if(root.rightNode!=null && root.rightNode.data==k){
                    root.rightNode = helperDelete(root.rightNode);
                    break;
                }else{
                    root = root.rightNode;
                }
            }
        }
        return node;

    }
    static treeNode helperDelete(treeNode root){
        if(root.leftNode==null){
            return root.rightNode;
        }else if(root.rightNode==null){
            return root.leftNode;
        }else{
            treeNode rootRight = root.rightNode;
            treeNode exRight = extremeRight(root.leftNode);
            exRight.rightNode = rootRight;

            return root.leftNode;
        }
    }
    static treeNode extremeRight(treeNode root){
        if(root.rightNode==null) return root;
        return extremeRight(root.rightNode);
    }

    // kth smallest
    static int kthSmallest(treeNode root, int k){
        Stack<treeNode> s = new Stack<>();
        treeNode curr = root;
        s.push(root);
        int c = 0;

        while(true){
            if(curr!=null){
                s.push(curr);
                curr = curr.leftNode;
            }else{
                if(s.isEmpty()){
                    break;
                }
                curr = s.pop();
                c++;
                if(c==k) return curr.data;
                curr = curr.rightNode;
            }
        }

        return -1;
    }
    static int kthLargest(treeNode root, int k){
        Stack<treeNode> s = new Stack<>();
        treeNode curr = root;
        s.push(root);
        // find total nodes : n
        // kth largest = n- k; smallest
        int c = 0;

        while(true){
            if(curr!=null){
                s.push(curr);
                curr = curr.rightNode;
            }else{
                if(s.isEmpty()){
                    break;
                }
                curr = s.pop();
                c++;
                if(c==k) return curr.data;
                curr = curr.leftNode;
            }
        }

        return -1;
    }

    // Validate BST
    static boolean validateBST(treeNode root){
        // 1. Inorder
        // 2. Limit
//        ArrayList<Integer> ds = new ArrayList<>();
//        inOrderBST(root, ds);
//        for(int i=0;i<ds.size();i++){
//            if(ds.get(i)<ds.get(i+1)) continue;
//            return false;
//        }
//        return true;
    return inOrderBST1(root, Long.MIN_VALUE, Long.MAX_VALUE);
}
    static  boolean inOrderBST1(treeNode root, long min, long max){
        if(root==null) return true;

        if(root.data<=min || root.data>=max) return false;

        return inOrderBST1(root.leftNode,min,root.data) && inOrderBST1(root.rightNode, root.data,max);
    }
    static void inOrderBST(treeNode root, ArrayList<Integer> ds){
        if(root==null) return;

        inOrderBST(root.leftNode, ds);
        ds.add(root.data);
        inOrderBST(root.rightNode, ds);
    }


    // LCA (lowest common ancestor) of BST
    static treeNode lcaBST(treeNode root, treeNode p, treeNode q){
        // 3 cases =>
        // left side lie
        // right isde lie
        // left-right lie

        int curr = root.data;
        if(p.data>curr && q.data>curr){
            lcaBST(root.rightNode,p,q);
        }else if(curr>p.data && curr>q.data){
            lcaBST(root.leftNode,p,q);
        }else{
            return root;
        }
        return null;
    }

    // BST from preorder
    static treeNode bstFromPreorder(int[] arr, treeNode root){
        return bstPre(arr,Integer.MAX_VALUE,new int[]{0});
    }
    static treeNode bstPre(int[] arr, int max,int[] i){
        if(i[0]==arr.length || arr[i[0]]>max) return null;

        treeNode temp = new treeNode(arr[i[0]++]);

        temp.leftNode = bstPre(arr,temp.data,i);
        temp.rightNode = bstPre(arr,max,i);

        return temp;
    }

    // Two sum in BST
    static boolean twoSumBST(treeNode root,int k){
        bstIterator l = new bstIterator(root,false);
        bstIterator r = new bstIterator(root,true);

        int i = l.next();
        int j = r.next();

        while (i<j){
            if(i+j==k){
                return true;
            }else if(i+j<k){
                i = l.next();
            }else{
                j = r.next();
            }
        }

        return false;
    }

    // Recover bst
    public void recoverBST(treeNode root){
        first = mid = null;
        prev = new treeNode(Integer.MIN_VALUE);

        inorderRecover(root);
        if(first!=null && last!=null){
            int t = first.data;
            first.data = last.data;
            last.data = t;
        }else{
            int t = first.data;
            first.data = mid.data;
            mid.data = t;
        }

    }
    public void inorderRecover(treeNode root){
        if(root==null) return;
        inorderRecover(root.leftNode);

        if(prev!=null && root.data<prev.data){
            if(first==null){
                first = prev;
                mid = root;
            }else{
                last = root;
            }
        }
        prev = root;

        inorderRecover(root.rightNode);
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
