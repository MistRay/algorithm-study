package com.mistray.swapsort;

import com.mistray.model.SortModel;

/**
 * @author MistLight
 * @create 2018-11-19
 * @desc 快速排序->分治算法
 * 逻辑:
 * 1.i =L; j = R; 将基准数挖出形成第一个坑a[i]
 * 2.j--由后向前找比它小的数,找到后挖出此数填前一个坑a[i]中
 * 3.i++由前向后找比它大的数,找到后也挖出此数填到前一个坑a[j]中
 * 4.再重复执行2,3二步,直到i==j,将基准数填入a[i]中
 *
 * 时间复杂度:
 * 平均情况:O(nlog2n)
 * 最好情况:O(nlog2n)
 * 最坏情况:O(n^2)
 * 空间复杂度:O(nlog2n)
 * 稳定性:不稳定
 */
public class QuickSort {
    public static void main(String[] args) {
        Integer[] arr = {8, 34, 5, 92, 44, 2, 74, 12};
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

    private static int partition(Integer[] arr, Integer start, Integer end) {
        //arr[start]为挖的第一个坑arr[start]为8
        int key = arr[start];
        while (start < end) {
            // 从arr[end]开始向前找一个比arr[start]小或相等的数填到arr[end]小的数(arr[5])填到arr[start]的位置
            while (arr[end] >= key && end > start) {
                end--;
            }
            // 逆向查找找到[5]
            arr[start] = arr[end];
            // {2,34,5,92,44,2,74,12}
            SortModel.show(arr);
            // 这时又出现了一个新坑(arr[5]),从arr[start]开始向后找一个比arr[5]大的数(arr[1])填到arr[5]的位置
            while (arr[start] <= key && end > start) {
                start++;
            }
            // 正向查找找到[1]
            arr[end] = arr[start];
            SortModel.show(arr);
            // 所以会再次进入循环找[1]与[5]未确认的部分,直到start<end为止,将k填进对应的位置
        }
        arr[start] = key;
        return start;
    }

}
