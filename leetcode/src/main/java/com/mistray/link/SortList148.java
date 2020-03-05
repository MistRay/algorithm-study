package com.mistray.link;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.link
 * @create 2020年03月04日 16:44
 * @Desc
 */
public class SortList148 {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head, slow = head;
        while (fast.next != null) {
            fast = fast.next.next;
            if (fast == null) {
                break;
            }
            slow = slow.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(right);
        return mergeTwoList(l1, l2);
    }


    // 该函数可以合并两个链表,并返回头结点
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val > l2.val) {
            l2.next = mergeTwoList(l2.next, l2);
            return l2;
        } else {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        }
    }
}
