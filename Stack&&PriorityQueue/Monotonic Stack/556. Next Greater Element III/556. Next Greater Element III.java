class Solution {
    // Next permutation
    // xxxx14321 -> 4321已经是这个子数组最大的排列，这个数找下一个比它刚大一点点的数，就是从1后边找刚比1大的数2，
    // 然后1，2交换位置 xxxx24311 ->再找2之后的最小的排列 xxxx21143。
    public int nextGreaterElement(int num) {
        
        String s = String.valueOf(num);
        char[] c = s.toCharArray();
        int n = c.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = c[i] - '0';
        }
        
        int small = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                small = i;
                break;
            }
        }
        if (small == Integer.MIN_VALUE) {
            return -1;
        }
        
        int large = Integer.MIN_VALUE;
        for(int i = n - 1; i >=0; i--) {
            if (arr[i] > arr[small]) {
                large = i;
                break;
            }
        }
        
        swap(arr, small, large);
        
        reverse(arr, small + 1, n - 1);
        
        long res = 0;
        for(int i = 0; i < n ; i++) {
            res = res * 10 + arr[i];
        }
        
        return res > Integer.MAX_VALUE ? -1 : (int) res;
        
    }
    
    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }
    
    private void swap(int[] arr, int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;        
    }
}