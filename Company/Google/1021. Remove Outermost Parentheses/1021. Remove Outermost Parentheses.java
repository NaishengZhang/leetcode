//关键点是左右括号数量相等，则是一个primitive, 然后跳过privmitive第一个和最后一个括号
// Time: O(n) Space: O(n) or O(1) excluding output string
class Solution {
    public String removeOuterParentheses(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        
        int opened = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (c == '(') {
                opened++;
                if (opened != 1) { // skip the first ( of one primitive
                    sb.append(c);
                }
            } else {
                opened--;
                if (opened != 0) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}

// Time: O(n) Space: O(n)
class Solution {
    public String removeOuterParentheses(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        int n = S.length();
        Deque<Character> stack = new ArrayDeque<>();
        char c = ' ';
        int start = 0, end = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++){
            c = S.charAt(i);
            if (c == '(') {
                if (stack.isEmpty()) {
                    start = i;
                }
                stack.push(c);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    end = i;
                    sb.append(S.substring(start + 1, end));
                }
            }
        }
        return sb.toString();
    }
}