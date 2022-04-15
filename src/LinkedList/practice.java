package LinkedList;
import java.util.HashSet;

class Node {
    int data;
    Node next,prev;
    Node(){};
    Node(int d)
    {
        next = null;
        prev = null;
        data = d;
    }
}

public class practice {
    public static void main(String args[]){
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(3);
        Node six = new Node(2);
        Node seven = new Node(1);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        boolean condition = isPalindrome(one);
        System.out.println("isPalidrome :" + condition);
    }

    //27] Roatate by k nodes
    static Node roatedByK(Node head, int k){
        if(head==null || head.next==null || k==0) return head;
        Node temp = head;
        int c = 1;
        while (temp.next!=null){
            c++;
            temp = temp.next;
        }
        temp.next = head;
        k = k%c; // if k is larger than c
        k = c-k;
        while (k>0){
            k--;
            temp = temp.next;
        }
        head = temp.next;
        temp.next = null;

        return head;
    }

    //25] Count triplet in sorted dll
    static int countTriplet(Node head, int x){
        Node curr=null,first=null,last=null;
        while (last.next!=null){
            last = last.next;
        }
        int count = 0;
        for(curr = head; curr!=null; curr = curr.next){
            first = curr.next;
            count += countPairs(first,last,x-curr.data);
        }

        return count;

    }
    static int countPairs(Node first, Node second,int val){
        int count = 0;
        while (first!=null && second!=null && first!=second && second.next!=first){
            if((first.data + second.data) == val){
                count ++;

                first = first.next;
                second = second.next;
            }else if((first.data + second.data) > val){
                second = second.prev;
            }else {
                first = first.next;
            }
        }
        return count;
    }

    //24] Print pairs of given sum in dll
    static void printPairs(Node head, int k){
        Node first = head;
        Node last = head;
        boolean found = false;
        while (last.next!=null) last = last.next;
        while (first!=null && last!=null && first!=last && last.next!=first){
            if((first.data + last.data) == k){
                found = true;
                System.out.println("( "+first.data+", "+last.data+" )");
                first = first.next;
                last = last.prev;
            }else if((first.data + last.data) > k){
                last = last.prev;
            }else {
                first = first.next;
            }
        }
        if (!found) System.out.println("Not found :(");
    }

    //23] Reverse dll
    static Node reverseDLL(Node head){
        if(head==null && head.next==null){
            return head;
        }
        Node curr = head;
        while (curr.next!=null){
            curr=curr.next;
        }
        head=curr;
        curr.next=curr.prev;
        curr.prev=null;
        curr=curr.next;
        while (curr.next!=null){
            Node temp = curr.next;
            curr.next = curr.prev;
            curr.prev = temp;
            curr=curr.next;
        }
        curr.prev = curr.next;
        curr.next = null;
        return head;
    }

    //22] First last node
    static Node firstlastNode(Node head){
        // common base case
        if(head==null && head.next==null) return head;

        Node temp = head;
        while (temp.next!=head){
            temp=temp.next;
        }

        int data = temp.data;
        temp.data = head.data;
        head.data = data;
        return head;
    }

    //20] Delete cicular ll using key
    static Node getDeleted(Node head,int key){
        if(head==null) return null;

        Node curr = head, prev = new Node();
        while (curr.data!=key){
            if(curr.next==head){
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        //if there is only one head
        if(curr==head && curr.next==head){
            head=null;
            return head;
        }

        if(curr!=head){
            prev = head;
            while (prev.next != head){
                prev = prev.next;
            }
            head = curr.next;
            prev.next = head;
        }else if(curr.next==head){
            prev.next = head;
        }else{
            prev.next = curr.next;
        }
        return head;
    }

    //19] Split into two CLL
    static void splitCircularLL(Node head,Node head1,Node head2){
        Node slow = head;
        Node fast = head.next;

        while (fast!=head && fast.next!=head){
            slow=slow.next;
            fast=fast.next.next;
        }

        head1 = head;
        head2 = slow.next;
        slow.next = head1;
        Node cur = head2;
        while (cur.next!=head){
            cur=cur.next;
        }

        cur.next = head2;
    }

    //18] LL is circular or not
    static boolean isCircular(Node head){
        if(head==null){
            return true;
        }

        Node temp = head;
        while(temp.next!=head){
            temp=temp.next;
            if(temp==null){
                return false;
            }
        }

        return true;
    }

    //17] Get middle
    static int getMiddle(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        return slow.data;
    }

    //14] Intersection pt of 2 ll
    static int intersectPoint(Node head1, Node head2){
        int c1 = getCount(head1);
        int c2 = getCount(head2);
        int d = 0;
        if(c1>c2){
            d = c1 - c2;
            return _getIntersection(d,head1,head2);
        }else{
            d = c2 - c1;
            return _getIntersection(d,head2,head1);
        }

    }
    static int _getIntersection(int d,Node head1, Node head2){
        Node curr1 = head1;
        Node curr2 = head2;
        for(int i=0;i<d;i++){
            if(curr1==null){
                return -1;
            }
            curr1 = curr1.next;
        }

        while(curr1!=null && curr2!=null){
            if(curr1.data==curr2.data){
                return curr1.data;
            }

            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return -1;
    }
    static int getCount(Node head){
        Node temp = head;
        int c = 0;
        while(temp!=head){
            c++;
            temp = temp.next;
        }

        return c;
    }

    static int intersectOt(Node head1, Node head2){
        Node curr1 = head1;
        Node curr2 = head2;

        if(curr1==null || curr2==null){
            return -1;
        }

        while (curr1!=curr2){
            curr1 = curr1.next;
            curr2 = curr2.next;

            if(curr1==curr2){
                return curr1.data;
            }

            if(curr1==null){
                curr1 = head2;
            }
            if(curr2==null){
                curr2 = head1;
            }
        }

        return -1;
    }

    //13] Merge sort
    static Node mergeSort(Node head){
        //base case
        if(head==null || head.next==null){
            return head;
        }

        //find mid
        Node mid = findMid(head);

        //divide both
        Node left = head;
        Node right = mid.next;
        mid.next = null;

        //recursive call
        left = mergeSort(left);
        right = mergeSort(right);

        //merge both left right
        Node result = merge(left, right);
        return head;
    }
    static Node findMid(Node head){
        Node slow = head;
        Node fast = head;

        while (fast!=null  && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        return slow;
    }
    static Node merge(Node left, Node right){
        if(left==null) return right;
        if(right==null) return left;
        Node ans = new Node(-1);
        Node temp = ans;
        while (left!=null && right!=null){
            if(left.data < right.data){
                temp.next = left;
                temp = left;
                left = left.next;
            }else{
                temp.next=right;
                temp=right;
                right=right.next;
            }
        }

        while (left!=null){
            temp.next = left;
            temp = left;
            left = left.next;
        }
        while (right!=null){
            temp.next=right;
            temp=right;
            right=right.next;
        }

        ans = ans.next;
        return ans;
    }

    //12] Intersection two ll
    static Node intersection(Node head1, Node head2){
        Node ans = new Node();
        HashSet<Integer> hs = new HashSet<>();

        Node curr = head1;
        while (curr!=null){
            int ds = curr.data;
            hs.add(ds);
            curr=curr.next;
        }

        curr = head2;
        while (curr!=null){
            int ds = curr.data;
            if(hs.contains(ds)){
                Node new_node = new Node(ds);
                ans.next=new_node;
                ans=new_node;
            }
            curr=curr.next;
        }

        return ans;
    }

    //11] Add 1 to ll
    public static Node addOne(Node head){
        Node nhead= reverse(head);
        Node head1=null;
        Node tail1=null;

        int carry=0;
        int sum=0;
        int dummy=1;

        while(nhead!=null){
            sum=dummy+nhead.data+carry;
            if(sum>9){
                carry=sum/10;
                sum=sum%10;
            }else{
                carry =0;
            }

            //creation new ll
            Node newhead = new Node(sum);
            if(head1==null){
                head1=newhead;
                tail1=newhead;
            }else{
                tail1.next=newhead;
                tail1=newhead;
            }
            //after one iteration make dummy = 0
            dummy=0;
            nhead=nhead.next;
        }
        // for 999
        if(carry!=0){
            Node newhead =new Node(carry);
            tail1.next=newhead;
            tail1=newhead;
        }

        tail1.next=null;
        return reverse(head1);
    }

    //10] Join end to start
    static Node startFromEnd(Node head){
        Node cur = head;
        Node pre = head.next;
        while (cur.next.next!=null){
            cur=cur.next;
            pre=pre.next;
        }
        cur.next=null;
        pre.next=head;
        head=pre.next;
        return head;

    }

    //9] Unorder list
    public Node removeDuplicates(Node head){
        HashSet<Integer> hs = new HashSet<>();

        Node cur = head;
        Node prev = null;

        while(cur!=null){
            int ds = cur.data;
            if(hs.contains(ds)){
                prev.next =cur.next;
            }else{
                hs.add(ds);
                prev=cur;
            }
            cur=cur.next;
        }

        return head;

    }

    //8] Remove duplicates from sorted LL
    static Node rmSorted(Node head){
        Node cur = head;

        while(cur!=null){
            if((cur.next!=null) && cur.data==cur.next.data){
                Node next_next = cur.next.next;
                cur.next = next_next;
            }else{
                cur=cur.next;
            }
        }

        return head;
//        Node cur = head;
//        while(cur!=null){
//            Node temp = cur;
//            while(temp!=null && temp.data== cur.data){
//                temp=temp.next;
//            }
//            cur.next=temp;
//            cur=cur.next;
//        }
//
//        return head;
    }

    //7] Delete LL
    static Node deleteLL(Node head){
        head=null;
        return head;
    }

    //6] length of loop  in LL
    static int lengthOfLoop(Node head){
        if(head==null || head.next==null){
            return 0;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return countLoopLen(slow);
            }
        }

        return 0;

    }
    static int countLoopLen(Node slow) {
        int count = 1;
        Node temp = slow;
        while (temp!=slow){
            count++;
            temp=temp.next;
        }
        return count;
    }

    //5] Detect loop
    static boolean detectLoop(Node head){
        if(head==null || head.next==null){
            return false;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }

        return false;
    }

    //4] Reverse by size k
    static Node reverseKGroup(Node head, int k) {
        if(head==null && k==1) return head;

        Node ans = new Node(0);
        ans.next = head;
        Node temp = head;

        int c = 0;
        while(temp!=null){
            temp = temp.next;
            c++;
        }

        Node prev = ans;
        Node cur = ans;
        Node nex = ans;

        while(c>=k){
            cur = prev.next;
            nex = cur.next;

            // k-1 operations
            for(int i=1;i<k;i++){
                cur.next = nex.next;
                nex.next = prev.next;
                prev.next = nex;
                nex = cur.next;
            }
            prev = cur;
            c-=k;
        }

        return ans.next;
    }

    //3] Reverse
    static Node reverse(Node head){
        Node pre=null;
        Node nex=null;

        while (head!=null){
            nex=head.next;
            head.next=pre;
            pre=head;
            head=nex;
        }

        return pre;
    }
    static Node reverseRecursion(Node head){
        if(head==null || head.next==null){
            return head;
        }

        Node res = reverseRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }

    //2] Palindrome
    static boolean isPalindrome(Node head){
        if(head==null && head.next==null){
            return true;
        }

        Node slow = head;
        Node fast = head;

        // middle node
        while (fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        // reverse
        slow.next = reverse(slow.next);
        slow=slow.next;

        while (slow != null) {
            if(head.data!= slow.data){
                return false;
            }
            head = head.next;
            slow = slow.next;
        }

        return true;
    }

    //1] From Nth node
    static int lastNthNode(Node head,int n){
        if(head==null) return head.data;
        int len = 0;

        Node temp=head;
        while(temp!=null){
            len++;
            temp=temp.next;
        }

        if(n>len) return -1;
        temp = head;

        for(int i=1;i<(len-n+1);i++){
            temp=temp.next;
        }

        return temp.data;
    }
}
