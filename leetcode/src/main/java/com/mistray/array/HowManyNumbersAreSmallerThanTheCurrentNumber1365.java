package com.mistray.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.array
 * @create 2020年03月02日 17:39
 * @Desc
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber1365 {

    public static void main(String[] args) {
        int[] nums = {8, 1, 2, 2, 3};
        int[] ints = smallerNumbersThanCurrent(nums);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);

        }
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int length = nums.length;
        int[] temp = new int[length];
        // 复制并排序
        System.arraycopy(nums, 0, temp, 0, length);
        Arrays.sort(temp);
        Map<Integer, Integer> map = new HashMap<>();
        // temp是有序的
        for (int i = 0, num = 0, t = temp[0]; i < temp.length; i++) {
            // t用来临时存储temp中相邻的重复数据
            if (temp[i] == t) {
                // 如果map中不包含temp[i]
                if (!map.containsKey(temp[i])) {
                    // 将该元素插入到map中.
                    map.put(temp[i], 0);
                }
            } else {
                map.put(temp[i], num);
                t = temp[i];
            }
            num++;
        }
        int[] res = new int[length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = map.get(nums[i]);
        }
        return res;
    }
}
