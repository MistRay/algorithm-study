/**
 * Copyright © FEITIAN Technologies Co., Ltd. All Rights Reserved.
 *
 * @Date 2019年2月22日, 上午10:35:35
 */
package com.mistray;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 包装持有读写锁的map
 *
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray
 * @create 2019年10月14日 20:24
 * @Desc 读写锁
 * 逻辑: 给map加读写锁.读时加读锁,写时加写锁.
 */
public class ReadWriteMapWrapFactory {

    public static <K, V> Map<K, V> readWriteMap(Map<K, V> m) {
        return new ReadWriteMap<K, V>(m);
    }

    private static class ReadWriteMap<K, V> implements Map<K, V> {
        private final Map<K, V> map;

        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        private final Lock r = lock.readLock();

        private final Lock w = lock.writeLock();

        public ReadWriteMap(Map<K, V> map) {
            this.map = map;
        }

        public V put(K key, V value) {
            w.lock();
            try {
                return map.put(key, value);
            } finally {
                w.unlock();
            }
        }

        public V get(Object key) {
            r.lock();
            try {
                return map.get(key);
            } finally {
                r.unlock();
            }
        }

        public int size() {
            r.lock();
            try {
                return map.size();
            } finally {
                r.unlock();
            }
        }

        public boolean isEmpty() {
            r.lock();
            try {
                return map.isEmpty();
            } finally {
                r.unlock();
            }
        }

        public boolean containsKey(Object key) {
            r.lock();
            try {
                return map.containsKey(key);
            } finally {
                r.unlock();
            }
        }

        public boolean containsValue(Object value) {
            r.lock();
            try {
                return map.containsValue(value);
            } finally {
                r.unlock();
            }
        }

        public V remove(Object key) {
            w.lock();
            try {
                return map.remove(key);
            } finally {
                w.unlock();
            }
        }

        public void putAll(Map<? extends K, ? extends V> m) {
            w.lock();
            try {
                map.putAll(m);
            } finally {
                w.unlock();
            }
        }

        public void clear() {
            w.lock();
            try {
                map.clear();
            } finally {
                w.unlock();
            }
        }

        public Set<K> keySet() {
            r.lock();
            try {
                return map.keySet();
            } finally {
                r.unlock();
            }
        }

        public Collection<V> values() {
            r.lock();
            try {
                return map.values();
            } finally {
                r.unlock();
            }
        }

        public Set<Entry<K, V>> entrySet() {
            r.lock();
            try {
                return map.entrySet();
            } finally {
                r.unlock();
            }
        }
    }

}
