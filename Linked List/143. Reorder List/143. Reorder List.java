/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode l1 = head;
        ListNode pre = findMiddle(head);
        ListNode mid = pre.next;
        pre.next = null;
        ListNode l2 = reverse(mid);
        ListNode nxt1 = null;
        ListNode nxt2 = null;
        while (l1 != null && l2 !=null) {
            nxt1 = l1.next;
            nxt2 = l2.next;
            l1.next = l2;
            if (nxt1 == null) {
                break;
            }
            l2.next = nxt1;
            l1 = nxt1;
            l2 = nxt2;
        }
        
    }
    
    public ListNode reverse(ListNode head) {
        ListNode pre = null, nxt = head.next;
        while (head != null) {
            nxt = head.next;
            head.next =pre;
            pre = head;
            head = nxt;
        }
        return pre;
    }
    
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head, pre = head;
        
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }
}