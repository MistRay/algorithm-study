package com.mistray.selection;

import com.mistray.model.SortModel;

/**
 * @author MistLight
 * @create 2018-11-16
 * @desc 选择排序
 *
 * 逻辑:遍历数组,找到最小的值放在最左边
 */
public class Selection {

    public static void main(String[] args) {
        Integer[] a = {7,2,3,511,54,23,8,12};
        Selection.selectionSort(a);
        SortModel.show(a);
    }

    public static void selectionSort(Integer[] a){
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int k = i;
            // 找出最小下标的位置
            for (int j = i +1; j < n; j++) {
                if (a[j]  < a[k]){
                    k = j;
                }
            }
            // 将最小值和数组的第一个位置进行互换
            if (k > i){
                int tmp = a[i];
                a[i] = a[k];
                a[k] = tmp;
            }
            SortModel.show(a);
        }
    }
}
