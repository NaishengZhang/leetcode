### 234. Palindrome Linked List

在数组或者字符串中，只需要前后两个指针进行移动以及判断即可。

但在LinkedList中，无法直接得到末尾的node，让末尾node向前移动。

所以就要将整个List分为左右两个部分，

```java
// even
1 -> 2 -> 3 -> 4
left half:  1 -> 2
right half: 3 -> 4
// odd
1 -> 2 -> 3 -> 4 -> 5
left half:  1 -> 2 -> 3
right half: 4 -> 5 
```

这就需要借助两个helper：

1. reverse() 原题：https://leetcode.com/problems/reverse-linked-list/solution/

```java
    private ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
    
```

2. findMid() 原题：https://leetcode.com/problems/middle-of-the-linked-list/

```java
    private ListNode findMid(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
```

