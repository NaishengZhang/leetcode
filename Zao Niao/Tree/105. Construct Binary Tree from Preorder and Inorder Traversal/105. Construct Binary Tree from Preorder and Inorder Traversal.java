/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
time O(n)
space O(n)
*/
class Solution {
    Map<Integer, Integer> indexMap = new HashMap<>(); // <value, index>
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }
    private TreeNode buildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        // base case
        if (inLeft > inRight) {
            return null;
        }
        int value = preorder[preLeft];
        TreeNode root = new TreeNode(value);
        int rootIndex = indexMap.get(value);
        int leftSize = rootIndex - inLeft;
        int rightSize = inRight - rootIndex;
        root.left = buildTree(preorder, inorder, preLeft + 1, preLeft + leftSize, inLeft, rootIndex - 1);
        
        root.right = buildTree(preorder, inorder, preLeft + leftSize + 1, preRight, rootIndex + 1, inRight);
        return root;
    }
}

//ref https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34543/Simple-O(n)
// 不是很理解
class Solution {
    private int in = 0;
    private int pre = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }
    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre == preorder.length) return null; // pre走到preorder末尾
        if (inorder[in] == stop) { // in指针走到了停止点
            in++; // stop点废弃了，in推进一位
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(preorder, inorder, node.val);
        // 左子树的停止点是当前的根节点的值
        node.right = build(preorder, inorder, stop);
        // 右子树的停止点是当前树的停止点
        return node;        
    }
}