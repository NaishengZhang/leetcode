// intuitively Time O(n), Memory O(n)
class Solution {
    public boolean isPalindrome(int x) {
        String s1 = String.valueOf(x);
        
        StringBuilder sb = new StringBuilder();
        for (int i = s1.length() - 1; i >=0; i--) {
            sb.append(s1.charAt(i));
        }
        return sb.toString().equals(s1);
    }
}

//without converting to string.
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int temp = x;
        int res = 0;
        int cur = -1;
        while (x > 0) {
            cur = x % 10;
            res = res * 10 + cur;
            x /= 10;
        }
        return res == temp;
    }
}