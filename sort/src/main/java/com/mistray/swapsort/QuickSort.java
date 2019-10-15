package com.mistray.swapsort;
import com.mistray.model.SortModel;

import java.util.Random;

/**
 * @author MistRay
 * @create 2018-11-19
 * @desc 快速排序->分治算法
 * 逻辑:
 * 1.i =L; j = R; 将基准数挖出形成第一个坑a[i]
 * 2.j--由后向前找比它小的数,找到后挖出此数填前一个坑a[i]中
 * 3.i++由前向后找比它大的数,找到后也挖出此数填到前一个坑a[j]中
 * 4.再重复执行2,3二步,直到i==j,将基准数填入a[i]中
 * <p>
 * 时间复杂度:
 * 平均情况:O(nlog2n)
 * 最好情况:O(nlog2n)
 * 最坏情况:O(n^2)
 * 空间复杂度:O(nlog2n)
 * 稳定性:不稳定
 */
public class QuickSort {
    public static void main(String[] args) {
        Integer[] arr = new Integer[8];
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            arr[i] = random.nextInt(150);
        }
        SortModel.show(arr);
        QuickSort.quickSort(arr, 0, arr.length - 1);
        SortModel.show(arr);
    }

    /**
     * 递归的方式实现快速排序
     *
     * @param arr 无序数组a
     */
    public static void quickSort(Integer[] arr, Integer start, Integer end) {
        if (start < end) {
            int index = partition(arr, start, end);
            quickSort(arr, start, index - 1);
            quickSort(arr, index + 1, end);
            SortModel.show(arr);
        }
    }


    /**
     * 根据基准数分治
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int partition(Integer[] arr, Integer start, Integer end) {
        // 将key作为基准数
        int key = arr[start];
        // 从尾向头找,大于key的元素不动,将小于key的值与key替换位置
        // 再从头向尾找,小于key的元素不动,将大于key的值与上面找到的arr[end]替换位置
        // 直到索引号相同,说明数组中元素全部被检索一遍,key左边的都是比key小的,key右边都是比key大的.
        while (start < end) {
            SortModel.show(arr);
            // 从尾向头找,找到比key小的就结束
            while (arr[end] >= key && end > start) {
                end--;
            }
            // 把上面找到的比key小的值放入arr[start]
            arr[start] = arr[end];
            // 从头向尾找,找到比key大的就结束
            while (arr[start] <= key && end > start) {
                start++;
            }
            // 把上面找到的比key大的值放入 arr[end]
            arr[end] = arr[start];
            SortModel.show(arr);
        }
        // 最终end=start,说明了整个数组已经被扫描了一遍
        arr[end] = key;
        SortModel.show(arr);
        return start;
    }

}
