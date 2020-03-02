class MaxStack {
    
    Deque<Integer> stack;
    Deque<Integer> max;
    
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new ArrayDeque<>();
        max = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack.push(x);
        
        if (max.isEmpty() || x >= max.peek()) {
            max.push(x);
        }
    }
    
    public int pop() {
        int x  = stack.pop();
        if (x == max.peek()) {
            max.pop();
        }
        return x;
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return max.peek();
    }
    
    public int popMax() { //关键要使用 top() pop() push（）方法
        Deque<Integer> buffer = new ArrayDeque<>();
        int m = peekMax(); 
        while (top() != m) {
            buffer.push(pop());
        }
        pop();
        while (!buffer.isEmpty()) {
            push(buffer.pop());
        }
        return m;
        
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */