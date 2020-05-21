package com.mistray.string;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.string
 * @create 2020年05月21日 11:09
 * @Desc
 */
public class ReverseString242 {

    public static void main(String[] args) {
        char[] s= {'1','2','3'};
        ReverseString242 reverseString242 = new ReverseString242();
        reverseString242.reverseString(s);
        System.out.println(s);
    }

    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < s.length/2; i++, j--) {
            char tmp = s[j];
            s[j] = s[i];
            s[i] = tmp;
        }
    }
}
