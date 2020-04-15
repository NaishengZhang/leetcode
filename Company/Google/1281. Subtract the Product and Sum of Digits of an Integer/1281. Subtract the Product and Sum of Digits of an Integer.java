class Solution {
    public int subtractProductAndSum(int n) {
        int cur = 0;
        int sum = 0, product = 1;
        while (n > 0) {
            cur = n % 10;
            product *= cur;
            sum += cur;
            n = n / 10;
        }
        return product - sum;
    }
}