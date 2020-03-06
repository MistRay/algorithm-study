package com.mistray.link;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.link
 * @create 2020年03月02日 11:41
 * @Desc
 */
public class MergeTwoSortedLists21 {


    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val > l2.val) {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        } else {
            l1.next = mergeTwoLists(l2, l1.next);
            return l1;
        }
    }


}
