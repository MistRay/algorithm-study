package com.mistray.string;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.string
 * @create 2020年03月03日 9:56
 * @Desc
 */
public class MinimumWindowSubstring76 {

    public static void main(String[] args) {
        String s = minWindow("ADOBECODEBANC", "ABB");
        System.out.println(s);
//        System.out.println("123456".substring(3, 4));

    }


    /*
        给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
        示例：
        输入: S = "ADOBECODEBANC", T = "ABC"
        输出: "BANC"
        说明：
        如果 S 中不存这样的子串，则返回空字符串 ""。
        如果 S 中存在这样的子串，我们保证它是唯一的答案。*/
    public static String minWindow(String s, String t) {
        int start = 0, end = 0, minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int[] window = new int[128];
        int[] needs = new int[128];
        int tSize = 0;
        for (char c : t.toCharArray()) {
            if (needs[c] == 0){
                tSize ++;
            }
            needs[c] = needs[c] + 1;
        }
        int match = 0;
        // 当右指针大于s的长度时结束
        while (right < s.length()) {

            char c1 = s.charAt(right);
            // 如果包含,就将包含的元素放进window的map中,并且匹配数+1
            if (needs[c1] != 0) {
                window[c1] = window[c1] + 1;
                if (needs[c1] == window[c1]) {
                    match++;
                }
            }
            right++;
            // 当实际匹配数和需要匹配数确定
            while (match == tSize) {
                if (right - left < minLen) {
                    // 更新最小子串的位置和长度
                    start = left;
                    end = right;
                    minLen = right - left;
                }
                char c2 = s.charAt(left);
                if (needs[c2] != 0) {
                    window[c2] = window[c2] - 1;
                    if (window[c2] < needs[c2]) {
                        match--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }


}
