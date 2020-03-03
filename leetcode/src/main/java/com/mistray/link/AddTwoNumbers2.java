package com.mistray.link;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.link
 * @create 2020年03月02日 15:50
 * @Desc
 */
public class AddTwoNumbers2 {

    public static void main(String[] args) {
        ListNode node = new ListNode(5);
        node.next = new ListNode(5);
        node.next.next = new ListNode(5);

        ListNode node2 = new ListNode(6);
        node2.next = new ListNode(7);
        node2.next.next = new ListNode(6);


        addTwoNumbers(node,node2);
    }

    /**
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 输入：(5 -> 5 -> 3) + (6 -> 7 -> 2)
     * 输出：6 -> 3 -> 1
     * 原因：342 + 465 = 807
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

}
