package com.mistray.link;

import java.util.ArrayList;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.link
 * @create 2020年03月04日 15:02
 * @Desc
 */
public class PalindromeLinkedList234 {


    public static void main(String[] args) {
        System.out.println(2%2);
    }

    // 双指针法
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null){
            return true;
        }
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode fast = head;
        ListNode slow = head;
        int count = 1;
        while (fast != null && fast.next != null) {
            list.add(slow);
            slow = slow.next;
            fast = fast.next.next;
            if (fast != null) {
                count = count + 2;
            } else {
                count++;
            }
        }
        ListNode tmp;
        if (count % 2 == 0) {
            tmp = slow;
        } else {
            tmp = slow.next.next;
        }
        for (int i = list.size() - 1; i > -1; i--) {
            if (tmp.val != list.get(i).val) {
                return false;
            }
            tmp = tmp.next;
        }
        return true;
    }

    // 复制数组双指针法
    public boolean isPalindrome2(ListNode head) {
        ArrayList<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        for (int i = 0, f = list.size() - 1; i < list.size() / 2; i++, f--) {
            if (list.get(i).val != list.get(f).val) {
                return false;
            }
        }
        return true;
    }
}
