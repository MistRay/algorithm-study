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
        while (start < end) {
            // 找到比key大的
            while (arr[end] >= key && end > start) {
                end--;
            }
            // 把key值放入arr[start]
            arr[start] = arr[end];
            SortModel.show(arr);
            while (arr[start] <= key && end > start) {
                start++;
            }
            // 把arr[start] 放入 arr[end]
            arr[end] = arr[start];
            SortModel.show(arr);
        }
        arr[start] = key;
        SortModel.show(arr);
        return start;
    }

}
