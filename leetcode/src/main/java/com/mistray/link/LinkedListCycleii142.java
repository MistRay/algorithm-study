package com.mistray.link;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.link
 * @create 2020年03月03日 16:23
 * @Desc
 */
public class LinkedListCycleii142 {
    public static void main(String[] args) {

    }

    // 思路 :
    // 快慢指针
    // 快指针一次走两格,慢指针一次走一格
    // 如果相遇,说明有环
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast.next == null || fast.next.next == null) {
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
