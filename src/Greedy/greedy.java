package Greedy;

import java.util.*;


class Item{
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}
class meetings{
    int start, end, pos;
    meetings(){}
    meetings(int start, int end, int pos){
        this.start = start;
        this.end = end;
        this.pos = pos;
    }
}
class Job{
    int id, deadline, profit;
    Job(){}
    Job(int id, int deadline, int profit){
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}
class sortMeetings implements Comparator<meetings>{
    public int compare(meetings m1, meetings m2){
        if(m2.end > m1.end) return -1;
        else if(m2.end < m1.end) return 1;
        else if(m2.pos > m2.pos) return -1;
        return 1;
    }
}
class sortingDec implements Comparator<Item>{
    public int compare(Item o1, Item o2){
        double f1 = (double)o1.value/(double)o1.weight;
        double f2 = (double)o2.value/(double)o2.weight;

        if(f1<f2) return 1;
        else if(f1>f2) return -1;
        else return 0;
    }
}

public class greedy {
    public static void main(String args[]){
        System.out.println(findMinimumCoins(13));
    }

    //6] Activity selection
    // => Same as N meetings

    //5] Find min no of coins
    public static int findMinimumCoins(int amount){
        int coins[] = {1, 2, 5, 10, 20, 50, 100, 500, 1000};

        int totalCoin = 0;
        int count = 0;

        for(int i=8;i>=0;i--){
            if(amount - coins[i] >= 0){
                while(amount - coins[i] >= 0){
                    amount -= coins[i];
                    count++;
                }

                if(amount==0) break;
            }
        }

        return count;
    }

    //4] Fractional Knapsack
    public static double frackKnap(Item arr[], int W, int n){
        Arrays.sort(arr, new sortingDec());
        int currCapacity = 0;
        double totalProfit = 0.0;

        for(int i=0;i<n;i++){
            if(currCapacity + arr[i].weight <= W){
                currCapacity += arr[i].weight;
                totalProfit += arr[i].value;
            }else{
                int diff = W - currCapacity;
                totalProfit = ((double) arr[i].value/(double) arr[i].weight)*diff;
            }
        }

        return totalProfit;
    }

    //3] Minimum Platforms
    public static int calculateMinPatforms(int at[], int dt[], int n) {
        Arrays.sort(at);
        Arrays.sort(dt);

        int plt_needed = 1;
        int result = 1;

        int i = 1, j = 0;
        while(i<n && j<n){
            if(at[i] <= dt[j]){
                plt_needed++;
                i++;
            }else if(at[i] > dt[j]){
                plt_needed--;
                j++;
            }

            if(plt_needed > result){
                result = plt_needed;
            }
        }
        return result;
    }

    //2] Job sequence
    int[] JobScheduling(Job arr[], int n){
        Arrays.sort(arr, (o1, o2) -> o2.profit - o1.profit);
        int maxi = 0;
        for(int i=0;i<n;i++){
            if(maxi<arr[i].deadline){
                maxi = arr[i].deadline;
            }
        }

        int res[] = new int[maxi+1];
        for(int i=1;i<res.length;i++){
            res[i] = -1;
        }

        int countP = 0, totalP = 0;
        for (int i=0;i<n;i++){
            for(int j=arr[i].deadline;j>0;j--){
                res[j] = arr[i].id;
                totalP += arr[i].profit;
                countP++;
                break;
            }
        }

        int ans[] = new int[2];
        ans[0] = countP;
        ans[1] = totalP;

        return ans;
    }

    //1] N meetings
    public static int maxMeetings(int start[], int end[], int n){
        ArrayList<meetings> m = new ArrayList<>();
        for(int i=0;i<start.length;i++){
            m.add(new meetings(start[i], end[i], i+1));
        }
        Collections.sort(m, new sortMeetings());

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(m.get(0).pos);
        int limit = m.get(0).end;
        for(int i=1;i<start.length;i++){
            if(m.get(i).start > limit){
                limit = m.get(i).end;
                ans.add(m.get(i).pos);
            }
        }

        return ans.size();
    }


}
