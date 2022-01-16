package BST;

class binaryT {
    int data;
    binaryT left;
    binaryT right;

    binaryT(int data){
        this.data=data;
        this.right=null;
        this.left=null;
    }
}


public class bastOperations{
    public static void main(String args[]){

        binaryT root = new binaryT(10);
        root.data=10;
        root.left.data = 20;
        root.right.data =30;
        root.left.left.data=5;
        root.left.right.data=15;

        System.out.println(max(root));
    }

    static binaryT remove (binaryT node,int data){
        if(node == null){
            return null;
        }
        if(data< node.data){
            node.left=remove(node.left,data);
        }else if(data> node.data){
            node.right = remove(node.right,data);
        }else{
            //work
            if(node.left!=null || node.right!=null){
                int lmax = max(node.left);
                node.data = lmax;
                node.left = remove(node.left,lmax);
                return node;
            }else if(node.left!=null){
                return node.left;
            }else if(node.right!=null){
                return node.right;
            }else{
                return null;
            }
        }
        return node;
    }

    static binaryT add (binaryT node,int data){
        if(node == null){
            return new binaryT(data);
        }
       if(data< node.data){
           node.left = add(node.left,data);
       }else if(data> node.data){
           node.right = add(node.right,data);
       }
       return node;
    }

    static int max (binaryT node){
        if(node.right != null){
            return max(node.right);
        }else{
            return node.data;
        }
    }

    static int min (binaryT node){
        if(node.left != null){
            return max(node.left);
        }else{
            return node.data;
        }
    }

    static boolean find (binaryT node,int data){
        if(node==null) return false;
        if(data< node.data){
            return find(node.left,data);
        }else if(data > node.data){
            return find(node.right,data);
        }else{
            return true;
        }
    }
}
