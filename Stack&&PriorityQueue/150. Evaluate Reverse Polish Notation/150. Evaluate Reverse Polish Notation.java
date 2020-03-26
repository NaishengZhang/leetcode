class Solution {
    public int evalRPN(String[] tokens) {
        int res = 0;
        if (tokens == null || tokens.length == 0) {
            return res;
        }
         
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(x + y);
            } else if (token.equals("-")) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(y - x);
            } else if (token.equals("*")) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(x * y);
            } else if (token.equals("/")) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(y / x);
            } else {
                stack.add(Integer.parseInt(token));
            }
        }
        res = stack.pop();
        return res;
    }
}