package com.mistray.array;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

/**
 * @author ZJY(MistRay)
 * @Project algorithm-study
 * @Package com.mistray.array
 * @create 2020年05月21日 14:14
 * @Desc
 */
public class BestTimeToBuyAndSellStock121 {

    // 思路,只允许交易一次.维护一个最小值low，与每一天的股票价格做差.
    // 最终得到的价格就是,最大的差价.
//    public int maxProfit(int[] prices) {
//        int max = 0;
//        int low = Integer.MAX_VALUE;
//        for (int price : prices) {
//            if (price < low){
//                low = price;
//            }
//            max = Math.max(max, price - low);
//        }
//        return max;
//    }


//    dp[-1][k][0] = 0
//    解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
//    dp[-1][k][1] = -infinity
//    解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
//    dp[i][0][0] = 0
//    解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
//    dp[i][0][1] = -infinity
//    解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                // 解释：
                //   dp[i][0]
                // = max(dp[-1][0], dp[-1][1] + prices[i])
                // = max(0, -infinity + prices[i]) = 0
                dp[i][1] = -prices[i];
                //解释：
                //   dp[i][1]
                // = max(dp[-1][1], dp[-1][0] - prices[i])
                // = max(-infinity, 0 - prices[i])
                // = -prices[i]
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
        }
        return dp[n-1][0];
    }
}
