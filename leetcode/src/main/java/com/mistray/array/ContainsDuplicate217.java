package com.mistray.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.array
 * @create 2020年05月21日 15:08
 * @Desc
 */
public class ContainsDuplicate217 {
    // 思路 hashmap,hashset,排序
//    public boolean containsDuplicate(int[] nums) {
//        HashSet<Integer> set = new HashSet<>();
//        for (int num : nums) {
//            if (!set.contains(num)){
//                set.add(num);
//            }else{
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean containsDuplicate(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        int pre = nums[0] + 1;
        for (int num : nums) {
            if (pre == num) {
                return true;
            }
            pre = num;
        }
        return false;
    }
}
