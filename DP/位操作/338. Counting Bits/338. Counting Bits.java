class Solution {
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            if (i == 0) {
                dp[i] = 0;
            }
            dp[i] = dp[i >> 1] + i % 2;
        }
        return dp;
    }
}

// x &= x - 1 结果为0则 x为2的指数，也就是二进制中只有一个1了
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popcount(i);
        return ans;
    }
    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count){
            System.out.println(x);
            x &= x - 1; //zeroing out the least significant nonzero bit   
        }
        return count;
    }
}