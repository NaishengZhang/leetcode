// Time: O(n) Memory: O(n)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 
            || prices.length == 1) {
            return 0;
        }
        int n = prices.length;
        int[] dp = new int[n];
        int res = 0;
        dp[0] = prices[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[i - 1], prices[i]);
            res = Math.max(res, prices[i] - dp[i - 1]);
        }
        
        return res;
    }
}

// Time: O(n) Memory: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 
            || prices.length == 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            res = Math.max(res, prices[i] - min);
        }
        
        return res;
    }
}