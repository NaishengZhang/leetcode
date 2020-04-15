/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
    
};
*/
// iteration + stack
class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        
        ArrayDeque<Node> stack = new ArrayDeque<>();
        
        Node dummy = new Node();
        dummy.next = head;
        head.prev = dummy;
        stack.push(head);
        
        Node cur, pre = dummy;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            pre.next = cur;
            cur.prev = pre;
            if (cur.next != null) {
                stack.push(cur.next);
            }
            
            if (cur.child != null) {
                stack.push(cur.child);
                cur.child = null;
            }
            pre = cur;
        }
        
        dummy.next.prev = null;
        return dummy.next;
    }
}


//recursive
class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        Node dummy = new Node();
        dummy.next = head;
        head.prev = dummy;
        
        dfs(dummy, head);
        //detach dummy node from the head node
        dummy.next.prev = null;
        return dummy.next;
    }
    
    private Node dfs(Node pre, Node cur) { // return the tail of flatten list
        if (cur == null) {
            return pre;
        }
        System.out.println("cur " + cur.val);
        pre.next = cur;
        cur.prev = pre;
        Node temp = cur.next;
        
        Node tail = dfs(cur, cur.child);
        System.out.println("tail " + tail.val);
        cur.child = null;
        return dfs(tail, temp);
        
    }
}