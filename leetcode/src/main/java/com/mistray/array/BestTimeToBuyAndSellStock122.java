package com.mistray.array;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.array
 * @create 2020年05月21日 14:14
 * @Desc
 */
public class BestTimeToBuyAndSellStock122 {

    // 思路,贪心算法,大于就加上,小于就不管,最后就可以算出最大利润
    public int maxProfit(int[] prices) {
        int max = 0;
        int pre = prices[0];
        for (int price : prices) {
            if (price - pre > 0){
                max = max + price - pre;
            }
            pre = price;
        }
        return max;
    }
}
