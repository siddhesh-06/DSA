package Stack;

import java.util.*;

public class stackPractice {
    public static void main(String args[]){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(0);
        arr.add(2);
        arr.add(1);
        arr.add(2);
        arr.add(0);

        int a[] = {4,1,2};
        int b[] = {1,3,4,2};
        System.out.println(nextGreater(a,b));
    }

    static void sortStack(Stack<Integer> s){
        if(s.isEmpty()){
            return;
        }
        int num = s.peek();
        s.pop();
        sortStack(s);

        sortedInsert(s,num);
    }

    static void sortedInsert(Stack<Integer> s, int num){
        if(s.isEmpty() || (!s.isEmpty() && num > s.peek())){
            s.push(num);
            return;
        }
        int n = s.peek();
        s.pop();

        sortedInsert(s,num);
        s.push(n);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int res[] = new int[n-k+1];
        int idx = 0;

        Deque<Integer> q = new LinkedList<>();

        for(int i=0;i<n;i++){
            //if there is max but out of window then remove it
            while(!q.isEmpty() && q.peek()==i-k){
                q.poll();
            }

            //remove from the last
            while(!q.isEmpty() && nums[i] > nums[q.peekLast()]){
                q.pollLast();
            }
            q.offer(i);

            //if window hit the size
            if(i>=k-1){
                res[idx++] = nums[q.peek()];
            }
        }

        return res;
    }

    public int orangesRotting(int[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int count_fresh = 0;

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==2){
                    q.offer(new int[]{i,j});
                }
                if(grid[i][j]!=0){
                    count_fresh++;
                }
            }
        }

        if(count_fresh==0) return 0;
        int count_min = 0, cnt = 0;
        int dx[] = {0,0,1,-1};
        int dy[] = {1,-1,0,0};

        while (!q.isEmpty()){
            int size = q.size();
            cnt += size;

            for(int i=0;i<size;i++){
                int point[] = q.poll();
                //moving four sides
                for(int j=0;j<4;j++){
                    int x = point[0] + dx[j];
                    int y = point[1] + dy[j];

                    if(x<0 || y<0 || x>=row || y>=col || grid[x][y]==2 || grid[x][y]==0) continue;

                    grid[x][y] = 2;
                    q.offer(new int[]{x,y});
                }
            }
            if(q.size()!=0){
                count_min++;
            }
        }
        return count_fresh==cnt ? count_min : -1;
    }

    public static int[] nextGreater(int nums1[], int nums2[]){
        int ans[] = new int[nums1.length];
        Stack<Integer> s = new Stack<>();
        HashMap<Integer, Integer> hm = new HashMap();

        for(int val : nums2){
            if(!s.isEmpty() && val>s.peek()){
                hm.put(s.pop(), val);
            }
            s.push(val);
        }

        int ind = 0;
        for(int val : nums1){
            ans[ind++] = hm.getOrDefault(val, -1);
        }

        return ans;
    }

    public static ArrayList<Integer> nextSmaller(ArrayList<Integer> arr, int n){
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        s.push(-1);
        // 1 0 2 1 2 0
        // 0 -1 1 0 0 -1

        for(int i=n-1;i>=0;i--){
            int el = arr.get(i);
            while(el<=s.peek()){
                s.pop();
            }
            ans.add(s.peek());
            s.push(el);
        }
        Collections.reverse(ans);
        return ans;

    }

    // LRU => DLL ,Map => head ---priority----------------------- end
    // Map => size => {int, address}

}

