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
/*
利用二叉树的性质，中序遍历的结果是一个sorted array，对于这道题来说，只需要在almost sorted array中找到两个swapped的node（返回node.val），
再通过中序遍历找到这两个值对应的node，并交换value
time: O(2n) 两轮中序遍历
space: O(n)
*/
class Solution {
    public void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        if (root == null) {
            return;
        }
        inorder(nums, root);
        int[] swap = findTwoSwappedNodes(nums);
        recover(swap, root);
    }
    
    private void inorder(List<Integer> nums, TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(nums, root.left);
        nums.add(root.val);
        inorder(nums, root.right);
    }
    private int[] findTwoSwappedNodes(List<Integer> nums) {
        int x = -1, y = -1;
        
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                if (x == -1) {
                    x = nums.get(i);
                }
                y = nums.get(i + 1);
            }
        }
        return new int[]{x, y};
    }
    private void recover(int[] swap, TreeNode root) {
        if (root == null) {
            return;
        }
        recover(swap, root.left);
        if (root.val == swap[0] || root.val == swap[1]) {
            root.val = root.val == swap[0] ? swap[1] : swap[0];
        }
        recover(swap,root.right);
        
    }
}

/*
可以通过在中序遍历的过程中，就找到两个swapped element。 time: O(2n) -> O(n) best case O(1)，space：O(n) -> O(h)
iterative inorder traversal
*/
class Solution {
    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pre = null, cur = root;
        
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && cur.val < pre.val) {
                if (x == null) {
                    x = pre;
                }
                y = cur;
            }
            pre = cur;
            cur = cur.right;
        }
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }
}

/*
recursive inorder traversal
*/
class Solution {
    TreeNode x = null, y = null, pre = null;
    
    public void recoverTree(TreeNode root) {
        dfs(root);
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (pre != null && node.val < pre.val) {
            if (x == null) {
                x = pre;
            }
            y = node;
        }
        pre = node;
        dfs(node.right);
    }
}