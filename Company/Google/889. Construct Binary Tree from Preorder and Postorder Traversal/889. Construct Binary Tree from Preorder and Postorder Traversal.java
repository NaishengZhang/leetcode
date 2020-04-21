/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    int[] pre;
    int[] post;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        int idx = 0;
        for (int num : post) {
            map.put(num, idx++);
        }
        return build(0, 0, pre.length);
        
    }
    
    private TreeNode build(int i, int j, int size) { // i是per中root的index， size是子数组长度
        if (size <=0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[i]);
        if(size == 1){
            return root;
        }
        int index = map.get(pre[i + 1]);
        int l = index - j + 1;
        root.left = build(i + 1, j, l);
        root.right = build(i + l + 1, index + 1, size - l - 1);
        return root;
    }
    
}