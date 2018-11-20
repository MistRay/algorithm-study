package com.mistray.model;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author MistRay
 * @create 2018-11-16
 * @desc 排序算法模版
 */
public class SortModel {

    /**
     *
     * @param v
     * @param w
     * @return
     * true  v < m
     * false v > m
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public  static void  show(Comparable[] a){
        StdOut.print("{");
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1){
                StdOut.print(a[i]);
            }else{
                StdOut.print(a[i] + ",");
            }
        }
        StdOut.print("}");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a){
        // 监测数组元素是否有序
        for (int i = 0; i < a.length; i++) {
            if (less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }
}
