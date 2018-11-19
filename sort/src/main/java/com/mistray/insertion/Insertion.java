package com.mistray.insertion;

import com.mistray.model.SortModel;

/**
 * @author MistRay
 * @create 2018-11-16
 * @desc 插入排序
 * 时间复杂度:
 * 平均情况:O(n^2)
 * 最好情况:O(n)
 * 最坏情况:O(n^2)
 * 空间复杂度:O(1)
 * 稳定性:稳定
 */
public class Insertion {

    public static void main(String[] args) {
        Integer[] ins = {4, 3, 2, 5, 7, 22, 59, 72, 35, 34};
        Insertion.insertionSort(ins);
    }

    public static void insertionSort(Integer[] ins) {
        int n = ins.length;
        int i, j;
        for (i = 1; i < n; i++) {
            // temp为本次循环待插入有序列表中的数
            int temp = ins[i];
            // 寻找temp插入有序列表的正确位置
            for (j = i - 1; j >= 0 && ins[j] > temp; j--) {
                //元素后移，为插入temp做准备
                ins[j + 1] = ins[j];
            }
            // 插入temp
            ins[j + 1] = temp;
            SortModel.show(ins);
        }

    }
}
