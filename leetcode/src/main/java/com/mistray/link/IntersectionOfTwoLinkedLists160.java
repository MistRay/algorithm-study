package com.mistray.link;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.link
 * @create 2020年03月03日 15:48
 * @Desc
 */
public class IntersectionOfTwoLinkedLists160 {

    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a.next;
            if (a == null) {
                a = headB;
            }
            b = b.next;
            if (b == null) {
                b = headA;
            }
        }
        return a;
    }
}
