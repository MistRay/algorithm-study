package com.mistray.merge;

import com.mistray.model.SortModel;

import java.util.Arrays;

/**
 * @author MistRay
 * @create 2018-11-20
 * @desc 归并排序
 * 第一步：申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 * 第二步：设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * 第三步：比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 * 重复步骤3直到某一指针超出序列尾
 * 将另一序列剩下的所有元素直接复制到合并序列尾
 */
public class MergeSort {

    public static void main(String[] args) {
        Integer[] array = {35, 24, 86, 12, 95, 58, 35, 42, 21, 73};
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(Integer[] array, Integer start, Integer end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) >> 1;
        // 递归实现归并排序
        sort(array, start, mid);
        sort(array, mid + 1, end);
        mergerSort(array, start, mid, end);
    }

    // 将两个有序序列归并为一个有序序列(二路归并)
    private static void mergerSort(Integer[] array, Integer start, Integer mid, Integer end) {
        // 定义一个临时数组，用来存储排序后的结果
        int[] arr = new int[end + 1];
        // 临时数组的索引
        int low = start;
        int left = start;

        int center = mid + 1;
        // 取出最小值放入临时数组中
        while (start <= mid && center <= end) {
            arr[low++] = array[start] > array[center] ? array[center++] : array[start++];
        }
        SortModel.show(array);

        // 若还有段序列不为空，则将其加入临时数组末尾
        while (start <= mid) {
            arr[low++] = array[start++];
        }
        SortModel.show(array);

        while (center <= end) {
            arr[low++] = array[center++];
        }
        SortModel.show(array);
        // 将临时数组中的值copy到原数组中
        for (int i = left; i <= end; i++) {
            array[i] = arr[i];
        }
    }
}
