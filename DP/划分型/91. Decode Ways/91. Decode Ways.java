class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            int t = arr[i - 1] - '0';
            if (t >= 1 && t <= 9) {
                dp[i] += dp[i - 1];
            }
            if (i >= 2) {
                t = (arr[i - 2] - '0') * 10 + arr[i - 1] - '0';
                if (t >= 10 && t <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }
}