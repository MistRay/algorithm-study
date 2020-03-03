package com.mistray.link;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.link
 * @create 2020年03月02日 10:57
 * @Desc
 */
public class ReverseLinkedList206 {


    public static void main(String[] args) {
    }


    public static ListNode reverseList(ListNode head) {
        // 上一个节点
        ListNode pre = null;
        // 当前节点
        ListNode cur = head;
        // 承载下一个节点的临时节点
        ListNode tmp = null;

        while (cur != null) {
            // 将当前节点cur的下一个节点赋给临时节点tmp
            tmp = cur.next;
            // 将上一个节点赋给当前节点的下一个节点
            cur.next = pre;
            // 将pre和cur后移一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }


}
