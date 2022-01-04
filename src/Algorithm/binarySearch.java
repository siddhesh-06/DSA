package Algorithm;

public class binarySearch {
    public static void main(String args[]){
        int arr[][] = {{1,2,5},{11,14,19},{2,6,16}};
        System.out.println(arr[1].length);
    }

    static boolean bs(int arr[],int t,int l,int r){
        while(l<=r){
            int mid = (l+r)/2;

            if(arr[mid]==t){
                return true;
            }else if(t>arr[mid]){
                l=mid+1;
            }else{
                r=mid-1;
            }
        }
        return false;
    }
}
