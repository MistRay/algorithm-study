package com.mistray.link;

import java.util.List;

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

    // 思路: 如果两个链表的尾相同的话,说明有相交
    // 得到链表a,b的长度,让长的链表先跑size长-size短的长度,然后判断.
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int ASize = 0;
        int BSize = 0;
        ListNode A = headA;
        ListNode B = headB;
        ListNode lastA = null;
        ListNode lastB = null;
        while (A != null || B != null) {
            if (A != null) {
                if (A.next == null) {
                    lastA = A;
                }
                ASize++;
                A = A.next;
            }
            if (B != null) {
                if (B.next == null) {
                    lastB = B;
                }
                BSize++;
                B = B.next;
            }
        }
        if (lastA == lastB) {
            lastA = headA;
            lastB = headB;
            if (ASize - BSize > 0) {
                for (int i = 0; i < ASize - BSize; i++) {
                    lastA = lastA.next;
                }
            } else {
                for (int i = 0; i < BSize - ASize; i++) {
                    lastB = lastB.next;
                }
            }
            while (lastA != null || lastB != null) {
                if (lastA == lastB) {
                    return lastA;
                }
                lastA = lastA.next;
                lastB = lastB.next;
            }
        }
        return null;
    }



    // 使用a + c + b = b + c + a 的公式,a跑完指向b的头,b跑完指向a的头.
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        // 当a!=b进入循环
        // 跳出循环的条件有两个,1.a==b==null 2.a==b!=null
        while (a != b) {
            if (a == null) {
                a = headB;
            }else{
                a = a.next;
            }

            if (b == null) {
                b = headA;
            }else{
                b = b.next;
            }
        }
        return a;
    }

}
