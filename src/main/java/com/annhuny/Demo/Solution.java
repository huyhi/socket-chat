package com.annhuny.Demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int item : deck) {
            int val = map.getOrDefault(item, 0);
            map.put(item, val + 1);
        }

        boolean flag = true;
        int cmp = 0;
        for (int item : map.values()) {
            if (flag) {
                flag = false;
                cmp = item;
            } else {
                if (cmp != item) {
                    return false;
                }
            }
        }

        return true;
    }
}
