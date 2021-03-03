package com.mistray.bit;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.bit
 * @create 2021年03月03日 11:44
 * @Desc
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * 思路:
 * 使用亦或(同位不同为true),得到中间变量
 * 将中间变量每一位不同的个数相加,最终即可得到汉明距离
 */
public class HammingDistance461 {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int sum = 0;
        while (z > 0){
            sum += z & 1;
            z = z >> 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        HammingDistance461 hammingDistance461 = new HammingDistance461();
        System.out.println(hammingDistance461.hammingDistance(1,4));
    }
}
