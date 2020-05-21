package com.mistray.string;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.string
 * @create 2020年05月21日 10:57
 * @Desc
 */
public class ReverseWordsInAString557 {

    public static void main(String[] args) {
        ReverseWordsInAString557 reverseWordsInAString557 = new ReverseWordsInAString557();
        reverseWordsInAString557.reverseWords("12");
    }

    public String reverseWords(String s) {
        String[] sp = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s1 : sp) {
            char[] chars = s1.toCharArray();
            for (int i = chars.length; i > 0; i--) {
                sb.append(chars[i - 1]);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
