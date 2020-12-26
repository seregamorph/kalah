package com.backbase.kalah.model;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.SortedMap;
import java.util.TreeMap;

@UtilityClass
class PitUtils {

    static SortedMap<Integer, Integer> pits(int... pits) {
        assert pits.length == 14;
        val map = new TreeMap<Integer, Integer>();
        for (int i = 0; i < 14; i++) {
            map.put(i + 1, pits[i]);
        }
        return map;
    }
}
