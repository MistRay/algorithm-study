package com.mistray.insertion;

import com.mistray.model.SortModel;

/**
 * @author MistRay
 * @create 2018-11-19
 * @desc 希尔排序
 * 逻辑:　　希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * 时间复杂度:
 * 平均情况:O(n^1.3)
 * 最好情况:O(n)
 * 最坏情况:O(n^2)
 * 空间复杂度:O(1)
 * 稳定性:不稳定
 */
public class ShellSort {

    public static void main(String[] args) {
        Integer[] a = {5, 1, 9, 22, 15, 3, 8};
        ShellSort.shellSort(a);
        System.out.println("==================================");
        Integer[] b = {3, 6, 1, 13, 2, 32, 15};
        ShellSort.shellSort2(b);
    }


    public static void shellSort(Comparable[] a) {
        // 增量gap,并逐步缩小增量
        for (int gap = a.length; gap > 0; gap /= 2) {
            // 从第gap个元素逐个对其所在组进行插入排序操作
            for (int i = gap; i < a.length; i++) {
                int j = i;
                // 当a[j]和a[j-gap]
                while (j - gap >= 0 && SortModel.less(a[j], a[j - gap])) {
                    // 交换两个元素的位置
                    SortModel.exch(a, j, j - gap);
                    j -= gap;
                }
                SortModel.show(a);
            }

            SortModel.show(a);
        }
    }

    public static void shellSort2(Comparable[] a) {
        // 将a[]按升序排序
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            // 1,4,13,40,121,364,1093
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // 将数组变为h有序
            for (int i = h; i < N; i++) {
                // 将a[i] 插入到a[i-h],a[i-2*h],a[i-3*h]..之中
                for (int j = i; j >= h && SortModel.less(a[j], a[j - h]); j -= h) {
                    SortModel.exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
        SortModel.show(a);
    }

}
