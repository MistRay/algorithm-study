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

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }
}
