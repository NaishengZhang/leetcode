class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> min;
    
    public MinStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if(min.empty() || x<= min.peek()) {
            min.push(x);
        }
    }
    
    public void pop() {
        if (stack.peek().equals(min.peek())) { //注意 要用equals，因为stack里存的是Integer
            min.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */