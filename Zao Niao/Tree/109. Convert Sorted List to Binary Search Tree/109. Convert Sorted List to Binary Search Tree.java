/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
Time: 计算中间元素 O(n/2), 对于原数组每一半都要计算中间元素，表面是O(n^2)，实际是nlogn
Space: O(logn)
*/
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return helper(head);
    }
    
    private TreeNode helper(ListNode head) {
        // list is empty return null
        if (head == null) {
            return null;
        }
        ListNode mid = findMiddle(head);
        TreeNode root = new TreeNode(mid.val);

        // base case, there is just one element in the list
        if (head == mid) {
            return root;
        }
        
        root.left = helper(head);
        root.right = helper(mid.next);
        return root;
    }
    
    private ListNode findMiddle(ListNode node) {
        ListNode slow = node, fast = node, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //断掉mid左半部分
        if (pre != null) {
            pre.next = null;
        }
        return slow;
    }
}
/*
空间换时间
还可以将linkednode转化为数组，因为数组计算中间原始是O(1)，总的时间复杂度就是O(n)
空间复杂度O(n)因为要new一个数组
*/
class Solution {

  private List<Integer> values;

  public Solution() {
    this.values = new ArrayList<Integer>();
  }

  private void mapListToValues(ListNode head) {
    while (head != null) {
      this.values.add(head.val);
      head = head.next;
    }
  }

  private TreeNode convertListToBST(int left, int right) {
    // Invalid case
    if (left > right) {
      return null;
    }

    // Middle element forms the root.
    int mid = (left + right) / 2;
    TreeNode node = new TreeNode(this.values.get(mid));

    // Base case for when there is only one element left in the array
    if (left == right) {
      return node;
    }

    // Recursively form BST on the two halves
    node.left = convertListToBST(left, mid - 1);
    node.right = convertListToBST(mid + 1, right);
    return node;
  }

  public TreeNode sortedListToBST(ListNode head) {

    // Form an array out of the given linked list and then
    // use the array to form the BST.
    this.mapListToValues(head);

    // Convert the array to
    return convertListToBST(0, this.values.size() - 1);
  }
}
/*
list的排序顺序就是inorder traversal的结果，那么先计算出一共有几个node，然后在中序遍历的同时，移动head来构建当前treenode。
Time O(n)
Space O(logn)
*/
class Solution {
    ListNode head;
    public TreeNode sortedListToBST(ListNode head) {
        int length = findSize(head);
        this.head = head;
        return convert(0, length - 1);
    }
    
    private TreeNode convert(int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = l + (r - l) / 2;
        TreeNode left = convert(l, mid - 1);
        
        TreeNode node = new TreeNode(head.val);
        head = head.next;
        
        TreeNode right = convert(mid + 1, r);
        
        node.left = left;
        node.right = right;
        return node;
    }
    
    private int findSize(ListNode head) {
        int cnt = 0;
        while (head != null) {
            head = head.next;
            cnt++;
        }
        return cnt;
    }

}