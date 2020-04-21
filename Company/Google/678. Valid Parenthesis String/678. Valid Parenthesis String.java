// two stack time: O(n) space: O(n)
class Solution {
    public boolean checkValidString(String s) {
        if (s == null) {
            return false;
        }
        
        Deque<Integer> left = new ArrayDeque<>(); // index
        Deque<Integer> start = new ArrayDeque<>();
        char c = ' ';
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(') {
                left.push(i);
            } else if (c == '*') {
                start.push(i);
            } else {
                if (!left.isEmpty()) {
                    left.pop();
                } else {
                    if (!start.isEmpty()) {
                        start.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        
        while (!left.isEmpty() && !start.isEmpty()) {
            if (left.pop() > start.pop()) {
                return false;
            }
        }
        
        return left.isEmpty();
    }
}

// time: O(n) space O(1)
class Solution {
    public boolean checkValidString(String s) {
        if (s == null) {
            return false;
        }
        char[] arr = s.toCharArray();
        
        int low = 0, high = 0; // low -> 最少需要几个右括号， high -> 最多需要几个右括号， low最小为0
        
        for (char c : arr) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') { // 如果low等于0那么就不再变化
                if (low > 0) {
                    low--;
                }
                high--;
            } else {
                if (low > 0) { // 星号当作右括号
                    low--;
                }
                high++;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }
}
