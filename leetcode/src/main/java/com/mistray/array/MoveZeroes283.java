package com.mistray.array;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.array
 * @create 2021年03月03日 12:00
 * @Desc
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * 思路:
 * 1. 非零前移法
 *    有零直接略过,将后面的数和0替换,最后得到0的数量,将结尾对应数量的数变为0.
 *    使用一个指针遇到0就停,继续走,遇到非0就替换.
 * 2. 双指针法
 *
 */
public class MoveZeroes283 {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[index++] = nums[i];
            }
        }

        while(index < nums.length){
            nums[index ++] = 0;
        }
    }

    public static void main(String[] args) {
        MoveZeroes283 moveZeroes283 = new MoveZeroes283();
        int[] xxx = new int[]{0,1,0,3,12};
        moveZeroes283.moveZeroes(xxx);
        for (int i = 0; i < xxx.length; i++) {
            System.out.println(xxx[i]);
        }
    }
}
