package com.mistray.link;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.link
 * @create 2020年05月21日 10:44
 * @Desc
 */
public class DeleteNodeInALinkedList237 {

    // 思路:因为无法访问上一个节点,所以要把当前节点变成下一个节点,然后把下一个节点干掉.
    public void deleteNode(ListNode node) {
        // 因为无法访问前一个结点，所以可以把要删除的结点的后一个结点的值前移
        node.val = node.next.val;
        // 然后删除掉后一个结点
        node.next = node.next.next;
    }

}
