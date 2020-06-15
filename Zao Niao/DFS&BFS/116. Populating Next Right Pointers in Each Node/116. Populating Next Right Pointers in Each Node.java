/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
/*
DFS
Time: O(n)
Space: O(h)
*/
class Solution {
    public Node connect(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        root.left.next = root.right;
        root.right.next = root.next == null ? null : root.next.left;
        connect(root.left);
        connect(root.right);
        return root;
    }
}

/*
BFS 
链接下一层
Time: O(n)
Space: Queue的长度取决于最宽的一层，perfect binary tree最下一层包含n/2个节点
*/
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.left == null) {
                    continue;
                }
                cur.left.next = cur.right;
                cur.right.next = cur.next == null ? null : cur.next.left;
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return root;
    }
}

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.left == null) {
                    continue;
                }
                cur.left.next = cur.right;
                cur.right.next = cur.next == null ? null : cur.next.left;
                if (cur.left != null) { //不同点在于加queue前null check
                    queue.offer(cur.left);
                }
                if (cur.right != null) { //
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }
}

/*
链接当前层cur.next = queue.peek();
*/
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i < size - 1) {
                    cur.next = queue.peek();
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }
}

/*
BFS
Time O(n)
Space O(1) 使用已建立的next指针
*/
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        
        Node leftMost = root;
        
        while (leftMost.left != null) {
            Node cur = leftMost;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }
}