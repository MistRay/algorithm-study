package com.mistray.string;

import com.sun.xml.internal.messaging.saaj.util.CharReader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.string
 * @create 2020年03月03日 17:36
 * @Desc
 */
public class ValidParentheses20 {

    public static void main(String[] args) {

    }


    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
        map.put('?', '?');
        for (char c : s.toCharArray()) {

        }
        return false;
    }
}
