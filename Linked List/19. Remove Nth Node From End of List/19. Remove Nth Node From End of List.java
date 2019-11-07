/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        for (int i = 1; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;
        return dummy.next;
    }
}