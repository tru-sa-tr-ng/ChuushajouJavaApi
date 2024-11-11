package com.app.chuushajou.libs;

import java.util.Map;
import java.util.LinkedHashMap;

public class ResMap {
    public static <K, V> Map<K, V> of(Object... keyValues) {
        Map<K, V> map = new LinkedHashMap<>(); // Sử dụng LinkedHashMap để giữ thứ tự
        if (keyValues.length % 2 != 0) {
            throw new IllegalArgumentException("Số lượng tham số phải là số chẵn (key-value pairs).");
        }

        for (int i = 0; i < keyValues.length; i += 2) {
            K key = (K) keyValues[i];
            V value = (V) keyValues[i + 1];
            map.put(key, value);
        }
        return map;
    }
}


