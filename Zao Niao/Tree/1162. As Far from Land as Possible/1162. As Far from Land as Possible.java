/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        helper(root);
        return root;
    }
    private void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        
        helper(node.left);
        helper(node.right);
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        
        
    }
}

// more clean
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        
        return root;
    }
}
//下边两种写法，相当于先序遍历 也相当于dfs，将对root的操作有res.add(root.val) 变为 交换左右孩子

/*
Iteration1
*/
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return root;
    }
}

/*
Iteration2
*/
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            TreeNode temp = cur.right;
            cur.right = cur.left;
            cur.left = temp;
            
            if (cur.right != null) {
                stack.push(cur.right);
            }
            
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return root;
    }
}

/*
上边用dfs写的，那么相应的也就可以用bfs实现。 dfs用stack， bfs用queue
*/
class Solution {
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return root;
        }
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur.left != null) queue.offer(cur.left);
            if(cur.right != null) queue.offer(cur.right);
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
        }
        return root;
    }
}

class Solution {
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                continue;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
        }
        return root;
    }
}

