/*
这道题题意不清
简单来说就是给一个数组w，其中每个element代表对应index的权重，然后randomly pick index。
w = [1,3,4,6]
index0,1,2,3
acc=[1,4,8,14]
然后随机一个数 x，x=1则对应index=0 x在2-4则对应index=1，以此类推
binary search
Time O(logn)
Space O(n)
*/
class Solution {
    private int[] acc;
    public Solution(int[] w) {
        acc = w;
        for (int i = 1; i < acc.length; i++) {
            acc[i] += acc[i - 1];
        }
    }
    
    public int pickIndex() {
        Random r = new Random();
        int target = r.nextInt(acc[acc.length - 1]) + 1;
        int start = 0;
        int end = acc.length - 1;
        while (start + 1 < end)  {
            int mid = start + (end - start) / 2;
            if (target <= acc[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (target <= acc[start]) {
            return start;
        }
        return end;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

/*
总体的策略就是 根据随机数的值来找对应的index，这样可以想到用treemap
*/