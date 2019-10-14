package com.mistray;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray
 * @create 2019年10月14日 20:39
 * @Desc
 */
public class LruExecutor {

    public static void main(String[] args) {
        LRULinkedHashMap<String, Object> lruMap = new LRULinkedHashMap<String, Object>(8191);
        lruMap.put("111",222);
    }
}

