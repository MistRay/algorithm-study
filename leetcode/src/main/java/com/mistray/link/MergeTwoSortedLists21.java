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
        // 当l1为空,返回l2
        if (l1 == null) {
            return l2;
        }

        // 当l2位空,返回l1
        if (l2 == null) {
            return l1;
        }

        // 当l1的值小于l2时
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l2);
            return l2;
        }

    }


}
