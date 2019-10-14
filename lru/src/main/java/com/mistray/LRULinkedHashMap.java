package com.mistray;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现了LRU的LinkedHashMap
 *
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray
 * @create 2019年10月14日 20:24
 * @Desc LRU
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    /**
     * 建议数值 2^n-1(3 7 15 31...)
     */
    private final int maxCapacity;

    private static final float DEFAULT_LOAD_FACTOR = 1f;

    public LRULinkedHashMap(int maxCapacity) {
        super(maxCapacity + 1, DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = maxCapacity;
    }


    /**
     * LinkedHashMap自带的判断是否删除最老的元素方法，默认返回false，即不删除老数据
     * 我们要做的就是重写这个方法，当满足一定条件时删除老数据
     *
     * @param eldest 尾部元素
     * @return 是否删除尾部元素
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }
}
