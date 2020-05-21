package com.mistray.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.array
 * @create 2020年05月21日 13:36
 * @Desc
 */
public class MajorityElement169 {

    // 思路:由于,如果数组中必然存在大于[n/2]个数的元素的话
    // 可以先进行排序,取中间的数,必然是这个元素
    // 例: 1 1 1 2
    // 也可以使用hash的方式.
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return  nums[nums.length/2];
    }
}
