package com.mistray.insertion;

import com.mistray.model.SortModel;

/**
 * @author MistRay
 * @create 2018-11-16
 * @desc 插入排序
 * 逻辑:
 * 相当于将一个无序数组,拆成两个数组,其中一个有序另一个无序,数组a元素数量开始为1,数组b元素数量为n-1
 * 然后将数组b的元素依次有序添加到数组a中,最后数组b的元素会为0,数组a的元素数为n,最后数组a会完全有序
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
